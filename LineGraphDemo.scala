import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object LineGraphDemo {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("monthly_revenue.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val months = DenseVector(data.map(_("month").toDouble).toArray)
    val revenue = DenseVector(data.map(_("revenue").toDouble).toArray)

    val fig = Figure("Revenue Trend")
    val plt = fig.subplot(0)
    plt += plot(months, revenue, '-', colorcode = "blue")
    plt.xlabel = "Month"
    plt.ylabel = "Revenue"
    plt.title = "Monthly Revenue Trend"

    fig.saveas("line_graph.png")
    println("Line graph saved as line_graph.png")
  }
}
