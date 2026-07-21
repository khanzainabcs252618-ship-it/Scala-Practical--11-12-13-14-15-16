import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object ScatterPlotDemo {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("employees.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val male = data.filter(_("gender") == "M")
    val female = data.filter(_("gender") == "F")

    def toVec(rows: List[Map[String, String]], col: String): DenseVector[Double] =
      DenseVector(rows.map(_(col).toDouble).toArray)

    val fig = Figure("Experience vs Salary")
    val plt = fig.subplot(0)
    plt += plot(toVec(male, "experience"), toVec(male, "salary"), '+', colorcode = "blue", name = "Male")
    plt += plot(toVec(female, "experience"), toVec(female, "salary"), '+', colorcode = "red", name = "Female")
    plt.xlabel = "Experience (years)"
    plt.ylabel = "Salary"
    plt.title = "Experience vs Salary"
    plt.legend = true

    fig.saveas("scatter_plot.png")
    println("Scatter plot saved as scatter_plot.png")
  }
}
