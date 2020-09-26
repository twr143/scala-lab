package sep2020

/**
  * Created by Ilya Volynin on 26.09.2020 at 12:31.
  */
object IncrTriplet {
  def increasingTriplet(nums: Array[Int]): Boolean = {
    if (nums.length < 3) false
    else {
      var i, j = -1
      for (ind <- 1 to nums.length - 1)
        if (j > -1 && nums(ind - 1) < nums(ind) && nums(j) < nums(ind)) return true
        else if (nums(ind - 1) < nums(ind) && (i == -1 || nums(ind) <= nums(i))) {
          i = ind - 1
          j = ind
        }
        else if (nums(ind - 1) < nums(ind))
          if (nums(ind - 1) < nums(j) && nums(i) < nums(ind - 1)) return true
          else if (nums(ind) < nums(j) && nums(ind) > nums(i)) j = ind

      false
    }
  }
  def isMonotonic(a: Array[Int]): Boolean = {
      if (a.length<3)  true
      else {
        for (i<-1 to a.length-2)
        if((a(i)>a(i-1)&&a(i)>a(i+1))||(a(i)<a(i-1)&&a(i)<a(i+1))) return false
        true
      }
  }
  def main(args: Array[String]): Unit = {
    //        println(increasingTriplet(Array(5, 1, 5, 5, 2, 5, 4)))
    println(increasingTriplet(Array(1, 2, 1, 2, 1, 2, 1, 2, 1, 2)))
  }
}
