import breeze.linalg._
import breeze.plot._
import breeze.stats.mean
import com.github.tototoshi.csv._
import java.io.File

object CombinedPlotDemo {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("height_weight.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val height = DenseVector(data.map(_("height").toDouble).toArray)
    val weight = DenseVector(data.map(_("weight").toDouble).toArray)

    val meanX = mean(height)
    val meanY = mean(weight)
    val a = sum((height - meanX) *:* (weight - meanY)) / sum((height - meanX) *:* (height - meanX))
    val b = meanY - a * meanX

    val sortedHeight = DenseVector(height.toArray.sorted)
    val trendLine = sortedHeight.map(h => a * h + b)

    val fig = Figure("Height vs Weight")
    val plt = fig.subplot(0)
    plt += plot(height, weight, '+', colorcode = "red", name = "Data points")
    plt += plot(sortedHeight, trendLine, '-', colorcode = "blue", name = "Trend line")
    plt.xlabel = "Height (cm)"
    plt.ylabel = "Weight (kg)"
    plt.title = "Height vs Weight with Trend Line"
    plt.legend = true

    fig.saveas("combined_plot.png")
    println(f"Fitted line: Weight = $a%.3f * Height + $b%.3f")
    println("Combined plot saved as combined_plot.png")
  }
}
