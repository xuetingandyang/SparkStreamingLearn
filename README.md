# Apache Spark Streaming with Scala

17 hands-on examples for studying Apache Spark Streaming with Scala.


## LogParser
Maintains top URL's visited over a 5 minute window, 
from a stream of Apache access logs on port 9999.
 
 - Listening to streamed data on a TCP socket
 - Using regular expression to parse data

Basic Approach:
 - Listen to a stream of raw Apache web server log lines over network
 - Use a regex to break up each line into its fields as it comes in
 - The "request" field will contain a HTTP command, URL, and protocol
 	- like Get /apache_pd.gif HTTP/1.0
 - Split the request into fields and extract just the URL
 - Map this into (URL, 1) tuples
 - Reduce the tuples to count up each URL, using a window
 - Sort & print the results as batches come in

Simulating a data source:
 - In Windows, use "ncat" utility to feed a sample apache log 
   to our script over the network.
 	- run through cmd: ncat -kl 9999 < data/access_log.txt
 	- That transmits the contents of access_log **one line at a time**
      over port 9999 to whoever connects to that port
 - In Linux, this is the same thing as "nc".
  
## LogAlarmer
Monitors a stream of Apache access logs on port 9999, 
and prints an alarm if an excessive ratio of errors is encountered.

 - learn util.Try to handle exceptions in Scala
 - using countByKeyAndWindow()

Basic Approach:
 - Use a regex to parse a stream of Apache logs as LogParse.scala
 - Extract the status code from each line
 - Map those codes into "Success" or "Failure"
 - Use countByValueAndWindow() to tally up the total success and
   failure statuses over some time period.
 - Add up the total success and failure codes from each RDD in the DStream as we go
 - Raise an alarm if error/success ratio is more than 50\%.
 
 
 
## LogSQL
Illustrates using SparkSQL with Spark Streaming, 
to issue queries on Apache log data extracted from a stream on port 9999.

Basic Approach:
 - Construct a regular expression to extract fields from raw Apache log lines
 - Create a socket stream to read log data published via netcat on port 9999 locally
 - Extract the (URL, status, user agent) from each log line
 - Process each RDD from each batch as it comes in
 - Count up occurrences of each user agent in this RDD from this batch.

 
## StructuredStreaming
Streams data, query data, and write out the results using SparkSession.

Basic Approach:
 - Create a SparkSession
 - Convert raw text into a Dataset of LogEntry rows,
   then just select the "status" and "dataTime" columns.
 - Group by status code with a one-hour window
 - Start the streaming query, dumping results to the console.

## KafkaExample
Working example of listening for log data from Kafka's testLogs topic on port 9092.

## FlumePushExample
Example of connecting to Flume in a "push" configuration.

## FlumePullExample
Example of connecting to Flume log data, in a "pull" configuration. 

## CustomerReceiverExample
Example from the Spark documentation.
This implements a socket receiver from scratch using a custom Receiver.

## CassandraExample
Listens to Apache log data on port 9999 and saves URL, status, and user agent
by IP address in a Cassandra database.


 
 
 
