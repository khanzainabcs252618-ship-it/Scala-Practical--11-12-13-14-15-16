import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object HistogramDemo {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("exam_scores.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val scores = DenseVector(data.map(_("score").toDouble).toArray)

    val fig = Figure("Exam Score Histogram")
    fig.width = 800
    fig.height = 800

    val plt1 = fig.subplot(2, 1, 0)
    plt1 += hist(scores, 5)
    plt1.title = "Exam Scores - 5 bins"

    val plt2 = fig.subplot(2, 1, 1)
    plt2 += hist(scores, 15)
    plt2.title = "Exam Scores - 15 bins"

    fig.saveas("histogram.png")
    println("Histogram saved as histogram.png")
  }
}
