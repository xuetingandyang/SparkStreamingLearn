package xueting.sparkstreaming

import org.apache.spark._

/** Create a RDD of lines from a text file, and keep count of
 *  how often each word appears.
 */
object wordcount {
  
  def main(args: Array[String]) {
      // Set up a SparkContext named WordCount that runs locally using
      // all available cores.
    val conf = new SparkConf().setAppName("WordCount")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)
    
    val input = sc.textFile("data/book.txt")
    val words = input.flatMap( _.split("\\W+") ) // characters that are not a word character
    val lowercaseWords = words.map(_.toLowerCase())
    val wordCounts = lowercaseWords.countByValue().take(20)
    for ((word, count) <- wordCounts) {
      println(word + " " + count)
    }
    sc.stop()
//      val conf = new SparkConf().setAppName("WordCount")
//      conf.setMaster("local[*]")
//      val sc = new SparkContext(conf)
//
//      // Create a RDD of lines of text in our book
//      val input = sc.textFile("data/book.txt")
//      // Use flatMap to convert this into an rdd of each word in each line
//      val words = input.flatMap(line => line.split(' '))
//      // Convert these words to lowercase
//      val lowerCaseWords = words.map(word => word.toLowerCase())
//      // Count up the occurence of each unique word
//      val wordCounts = lowerCaseWords.countByValue()
//      
//      // Print the first 20 results
//      val sample = wordCounts.take(20)
//      
//      for ((word, count) <- sample) {
//        println(word + " " + count)
//      }
//      
//      sc.stop()
      
      
      
      
    }  
}