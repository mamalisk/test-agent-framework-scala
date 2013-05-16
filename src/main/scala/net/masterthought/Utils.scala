package net.masterthought


object Utils extends App{

  implicit def listToString(list:List[String]) : String = {
    list.foldRight("")(_ + " " + _)
  }

  def reduceRight(list:List[String]) : String = {
    list.reduceRight(_ + " " + _)
  }

  val result = listToString(List("item1","item2","item3"))
  val result1 = reduceRight(List("item1","item2","item3"))
  println(result)
  println(result1)

}
