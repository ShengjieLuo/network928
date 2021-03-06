import java.util.HashMap
import org.apache.kafka.clients.producer.{ProducerConfig, KafkaProducer, ProducerRecord}
import java.util.concurrent.{Executors, ExecutorService}
import java.net.{DatagramPacket,DatagramSocket,InetAddress}
import java.util.concurrent._
import java.util.concurrent.ConcurrentLinkedQueue

object LogUDPProducerConcurrent{
 
  class SharePool{
    var poolCount:Int = 0;
    def send(){this.synchronized{poolCount = poolCount - 1;}; if (poolCount>100){println("Num:" + poolCount.toString + "\tSend Package");} }
    def receive(){this.synchronized{poolCount = poolCount + 1;}; if (poolCount>100){println("Num:" + poolCount.toString+ "\tReceive Package");} }
  }

  class Sender(producer:KafkaProducer[String,String],topic:String,fg:Int,queue:ConcurrentLinkedQueue[DatagramPacket],pool:SharePool) extends Runnable{
    def run(){
      while (true){
        while (!queue.isEmpty()){
          val packet = queue.poll()
          val sentence:String = new String(packet.getData(),0,packet.getLength()-1)
          if (fg == 1) println(Thread.currentThread().getName())
          if (fg == 2) println(topic+": "+sentence)
          val message = new ProducerRecord[String, String](topic, null, sentence)
          producer.send(message)
	  pool.send()
        }
      }
    }
  }

  class SenderSimple(producer:KafkaProducer[String,String],topic:String,queue:ConcurrentLinkedQueue[DatagramPacket]) extends Runnable{
    def run(){
      while (true)
      {
        while (!queue.isEmpty()){
          val packet = queue.poll()
          val sentence:String = new String(packet.getData(),0,packet.getLength()-1)
          val message = new ProducerRecord[String, String](topic, null, sentence)
          producer.send(message)
        }
      }
    }
  }
  
    
  def main(args: Array[String]) {
    val Array(broker,flag1,flag2,topic,port,mode,queueType) = args

    /*Defualt Configuration
    val topic:String = "test"
    val broker:String = "spark-master:9092"
    val flag1 = 3 //Show the screen flag
    val flag2 = 8 //Use Fixed Pool Thread Number
    val port:Int = 9999
    val mode:Int = 1 //Debug Mode*/

    val portNum:Int = port.toInt
    val fg1:Int = flag1.toInt
    val ThreadNum:Int = flag2.toInt
    val modeNum:Int = mode.toInt        

    val props = new HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)// Send some messages
    val serverSocket:DatagramSocket = new DatagramSocket(portNum)
    val receiveData = new Array[Byte](1024)
    val concurrentQueue:ConcurrentLinkedQueue[DatagramPacket] = new ConcurrentLinkedQueue[DatagramPacket]()
    val threadPool:ExecutorService = Executors.newFixedThreadPool(ThreadNum)
    val dataPool:SharePool = new SharePool()
    if (modeNum == 1) {
      for (i <- 0 to (ThreadNum-1)){threadPool.execute(new Sender(producer,topic,fg1,concurrentQueue,dataPool))}
    }else{
      for (i <- 0 to (ThreadNum-1)){threadPool.execute(new SenderSimple(producer,topic,concurrentQueue))}
    }
    
    if (modeNum == 1){  
      while(true)
        {
          val receivePacket:DatagramPacket = new DatagramPacket(receiveData, receiveData.length)
          serverSocket.receive(receivePacket)
          concurrentQueue.offer(receivePacket)
          dataPool.receive()
        }
    }else{
      while(true)
        {
          val receivePacket:DatagramPacket = new DatagramPacket(receiveData, receiveData.length)
          serverSocket.receive(receivePacket)
          concurrentQueue.offer(receivePacket)
        }
    }
  }
}
