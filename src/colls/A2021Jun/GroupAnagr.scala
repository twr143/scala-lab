package colls.A2021Jun

/**
  * Created by twr143 on 04.06.2021 at 12:50.
  */
object GroupAnagr {
  def groupAnagrams(strs: Array[String]): List[List[String]] = {
     strs.groupBy(_.sorted).values.map(_.toList).toList 
  }
  
  def main(args: Array[String]): Unit = {
    
  }
}
