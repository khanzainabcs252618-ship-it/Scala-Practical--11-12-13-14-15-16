import scala.io.Source

object WordFrequency {
  def main(args: Array[String]): Unit = {
    val filename = "sample.txt"
    val source = Source.fromFile(filename)
    val text = try source.mkString finally source.close()

    val words = text.toLowerCase
      .replaceAll("[^a-z0-9\\s]", "")
      .split("\\s+")
      .filter(_.nonEmpty)

    val freq = words.groupBy(identity).view.mapValues(_.size).toSeq.sortBy(-_._2)

    println(f"${"Word"}%-15s${"Count"}%s")
    println("-" * 25)
    freq.foreach { case (w, c) => println(f"$w%-15s$c%d") }

    println(s"\nTotal words: ${words.length}")
    println(s"Unique words: ${freq.length}")
  }
}
