package algo.design.cache

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 14.02.2020 at 20:18.
  */
object LFUCache {
  val _capacity = 0
  val cache = new mutable.HashMap[Int, (Int, Int, Int)]() {
    override def default(key: Int): (Int, Int, Int) = null
  }

  val frequencies = mutable.TreeMap[(Int, Int), Int]()(Ordering.fromLessThan({ case ((i1, f1), (i2, f2)) => if (f1 != f2) f1 < f2 else i1 < i2 }))

  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  var index = 0;

  def get(key: Int): Int = {
    val result = cache(key)
    if (result != null) {
      adjust(cache, key, (0, 0, 0)) { case (value, _, freq) => (value, index, freq + 1) }
      frequencies -= ((result._2, result._3))
      frequencies += ((index, result._3 + 1) -> key)
      index += 1
      result._1
    } else -1

  }

  def put(key: Int, value: Int) {
    val result = cache(key)
    if (result != null) frequencies -= ((result._2, result._3))
    adjust(cache, key, (value, index, 0)) { case (_, _, f) => (value, index, f + 1) }
    frequencies += (if (result != null) (index, result._3 + 1) -> key else (index, 1) -> key)
    index += 1
    if (frequencies.size > _capacity) {
      val keyToRemove = frequencies(frequencies.firstKey)
      if (keyToRemove != key || _capacity == 0) {
        frequencies -= frequencies.firstKey
        cache -= keyToRemove
      } else if (frequencies.size > 1) {
        val secondKeyToRemove = frequencies(frequencies.tail.firstKey)
        frequencies -= frequencies.tail.firstKey
        cache -= secondKeyToRemove
      }
    }

  }

  def main(args: Array[String]): Unit = {
    put(0, 0) // evicts key 2
    println(get(0)) // returns 1

  }

}
