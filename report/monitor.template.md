## Monitor Report

Monitor Function is used to monitor the network flow from the backbone router. In this report, we would include four message resources,
1. DRQ: DNS request package
2. DRS: DNS response package
3. HRQ: HTTP request package
4. HRS: HTTP response package

### DRQ report
#### Overall situation:
DNS request report is the package sent to the DNS server to query the IP address of the URL.

Total Number of the DRQ package:{Monitor::all:number}

#### Who send the DRQ package?
{Monitor::DRQ::table1}
{Monitor::DRQ::table2}
{Monitor::DRQ::image1}
{Monitor::DRQ::image2}

#### Who receive the DRQ package?
{Monitor::DRQ::table3}
{Monitor::DRQ::table4}
{Monitor::DRQ::image3}
{Monitor::DRQ::image4}

### DRS report
#### Overall situation:
DNS response report is the package reponsed by the DNS server. DNS server would inform the IP address to the server who sent the DNS query request.

#### Who send the DRS package?
{Monitor::DRS::table1}
{Monitor::DRS::table2}
{Monitor::DRS::image1}
{Monitor::DRS::image2}

#### Who receive the DRS package?
{Monitor::DRS::table3}
{Monitor::DRS::table4}
{Monitor::DRS::image3}
{Monitor::DRS::image4}

### HRQ report
#### Overall situation:
Http request package is one of the most popular package type in Network. For example, if you want to get a net page from www.baidu.com, you would send a HTTP request to the baidu server to fetch the net page.

#### Who send the HRQ package?
{Monitor::HRQ::table1}
{Monitor::HRQ::table2}
{Monitor::HRQ::image1}
{Monitor::DRS::image2}

#### Who receive the HRQ package?
{Monitor::DRS::table3}
{Monitor::DRS::table4}
{Monitor::DRS::image3}
{Monitor::DRS::image4}

### HRS report
#### Overall situation:
HTTP response package is one of the most popular package type in Internet. For example, if you want to get a net page from www.baidu.com, then baidu server would send you a HTTP response package including the netpage you want.

#### Who send the HRQ package?
{Monitor::HRQ::table1}
{Monitor::HRQ::table2}
{Monitor::HRQ::image1}
{Monitor::DRS::image2}

#### Who receive the HRQ package?
{Monitor::DRS::table3}
{Monitor::DRS::table4}
{Monitor::DRS::image3}
{Monitor::DRS::image4}