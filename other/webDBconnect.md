#WebUI数据库连接说明

##主机信息
IP:10.255.0.11 （可以在交大校内访问）

User: u928

Password: hadoop928

Root-pwd: hadoop928

安装目录: /usr/local/web

##数据库信息
Port: 3306

Username: root

Password: 123456

Database: web

##查询语句
Online 查询

select * from web.onHRSips WHERE id LIKE “2017-01-10 01%;

Offline 查询

select * from web.ofHRSips

##字段说明
https://github.com/ShengjieLuo/DBNS/blob/master/script/webSQL.sh

提示：部分数据库某分钟内的统计值可能为空，即没有条目item，这是由于在该分钟内数据过于分散，即使出现最多的条目也没有超过统计阈值。Web开发者请注意处理这个边界条件。

##样例: 在线分析数据库查询
select * from web.onHRSips;

结果每分钟刷新，在表格的尾部增加当前时间段内的统计结果，每分钟10项。请注意时间差异，这是北京时间2017-01-10 21:20的统计结果。

| id               | IPSource        | count |
|------------------|-----------------|-------|
| 2017-01-10 05:20 | 202.121.223.13  |   816 |
| 2017-01-10 05:20 | 210.35.64.3     |   325 |
| 2017-01-10 05:20 | 111.7.163.26    |   197 |
| 2017-01-10 05:20 | 122.228.199.114 |   196 |
| 2017-01-10 05:20 | 113.31.27.206   |   192 |
| 2017-01-10 05:20 | 210.35.115.110  |   189 |
| 2017-01-10 05:20 | 61.130.28.141   |   181 |
| 2017-01-10 05:20 | 60.210.10.50    |   170 |
| 2017-01-10 05:20 | 115.63.103.29   |   166 |
| 2017-01-10 05:20 | 202.121.64.132  |   149 |
| 2017-01-10 05:21 | 202.121.223.13  |  1010 |
| 2017-01-10 05:21 | 210.35.64.3     |   420 |
| 2017-01-10 05:21 | 115.63.103.29   |   129 |
| 2017-01-10 05:21 | 60.210.10.50    |   129 |
| 2017-01-10 05:21 | 61.130.28.141   |   129 |
| 2017-01-10 05:21 | 113.31.27.206   |   126 |
| 2017-01-10 05:21 | 122.228.199.114 |   124 |
| 2017-01-10 05:21 | 111.7.163.26    |   121 |
| 2017-01-10 05:21 | 202.121.64.136  |   117 |
| 2017-01-10 05:21 | 202.121.223.8   |   117 |

## 样例：离线分析数据库查询
select * from web.ofHRSips;

结果每小时刷新，并且覆盖之前的结果。每小时结果呈现30项。

| id               | IPSource        | count |
|------------------|-----------------|-------|
| 2017-01-10 05:00 | 202.121.223.13  | 11626 |
| 2017-01-10 05:00 | 210.35.64.3     |  8216 |
| 2017-01-10 05:00 | 210.35.115.110  |  6987 |
| 2017-01-10 05:00 | 60.210.10.50    |  6920 |
| 2017-01-10 05:00 | 115.63.103.29   |  6884 |
| 2017-01-10 05:00 | 61.130.28.141   |  6732 |
| 2017-01-10 05:00 | 122.228.199.114 |  6641 |
| 2017-01-10 05:00 | 113.31.27.206   |  6292 |
| 2017-01-10 05:00 | 111.7.163.26    |  6250 |
| 2017-01-10 05:00 | 202.121.66.144  |  4936 |
| 2017-01-10 05:00 | 112.90.37.251   |  4905 |
| 2017-01-10 05:00 | 119.97.146.107  |  4746 |
| 2017-01-10 05:00 | 202.121.64.131  |  4701 |
| 2017-01-10 05:00 | 54.94.216.67    |  4562 |
| 2017-01-10 05:00 | 202.121.223.8   |  3967 |
| 2017-01-10 05:00 | 202.121.64.136  |  3341 |
| 2017-01-10 05:00 | 202.121.64.132  |  3339 |
| 2017-01-10 05:00 | 202.121.64.129  |  3264 |
| 2017-01-10 05:00 | 210.35.99.247   |  3214 |
| 2017-01-10 05:00 | 202.121.64.130  |  3120 |
| 2017-01-10 05:00 | 202.121.223.16  |  2889 |
| 2017-01-10 05:00 | 202.121.223.6   |  2848 |
| 2017-01-10 05:00 | 222.186.129.155 |  2571 |
| 2017-01-10 05:00 | 202.121.223.10  |  2563 |
| 2017-01-10 05:00 | 202.121.64.134  |  2507 |
| 2017-01-10 05:00 | 202.121.223.28  |  2472 |
| 2017-01-10 05:00 | 202.121.64.137  |  2463 |
| 2017-01-10 05:00 | 202.121.223.14  |  2342 |
| 2017-01-10 05:00 | 202.121.64.138  |  2279 |
| 2017-01-10 05:00 | 54.153.30.23    |  2274 |




