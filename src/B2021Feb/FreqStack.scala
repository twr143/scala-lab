package B2021Feb

import scala.collection.mutable._
import scala.collection.mutable

/**
  * Created by twr143 on 21.02.2021 at 18:41.
  */
object FreqStack {

  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  val b = mutable.ListBuffer.empty[Int]

  val m = mutable.Map.empty[Int, Int]
  var max = 0
  var maxSet = mutable.Set.empty[Int]

  def push(x: Int) {
    b.+=:(x)
    adjust(m, x, 0)(_ + 1)
    if (m(x) > max) {
      max = m(x)
      maxSet = mutable.Set(x)
    } else if (m(x) == max) {
      maxSet += x
    }
  }

  def pop(): Int = {
    val it = b.iterator
    while (it.hasNext) {
      val v = it.next()
      if (maxSet.contains(v)) {
        b -= v
        adjust(m, v, 0)(_ - 1)
        if (maxSet.size == 1) {
          max = m.values.max
          maxSet = mutable.Set.empty[Int]
          m.keySet.foreach(k => if (m(k) == max) maxSet += k)
        } else {
          maxSet -= v
        }
        return v
      }
    }
    -1
  }
}
