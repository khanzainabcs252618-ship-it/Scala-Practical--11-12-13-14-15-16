import com.github.tototoshi.csv._
import java.io.File

object OneHotEncoding {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("students.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val columnToEncode = "department"
    val categories = data.map(_(columnToEncode)).distinct.sorted

    val newData = data.map { row =>
      val value = row(columnToEncode)
      val oneHotCols = categories.map(cat => cat -> (if (cat == value) "1" else "0")).toMap
      (row - columnToEncode) ++ oneHotCols
    }

    val headers = newData.head.keys.toList
    println("Header: " + headers.mkString(", "))
    newData.foreach(row => println(headers.map(row).mkString(", ")))

    val writer = CSVWriter.open(new File("students_encoded.csv"))
    writer.writeRow(headers)
    newData.foreach(row => writer.writeRow(headers.map(row)))
    writer.close()

    println(s"\nEncoded '$columnToEncode'. Categories: ${categories.mkString(", ")}")
  }
}
