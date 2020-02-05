package algo.design.cache

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 05.02.2020 at 21:11.
  */
object LRUCache {
  val _capacity = 2
  val cache = new mutable.HashMap[Int, (Int, Int)]() {
    override def default(key: Int): (Int, Int) = null
  }
  val frequencies = mutable.TreeMap[Int, Int]()

  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  var index = 0;

  def get(key: Int): Int = {
    val result = cache(key)
    if (result != null) {
      adjust(cache, key, (0, 0)) { case (value, _) => (value, index) }
      frequencies -= result._2
      frequencies += (index -> key)
      index += 1
      result._1
    } else -1

  }

  def put(key: Int, value: Int) {
    val result = cache(key)
    if (result != null) frequencies -= result._2
    adjust(cache, key, (value, index)) { case (_, _) => (value, index) }
    frequencies += (index -> key)
    index += 1
    if (frequencies.size > _capacity) {
      val keyToRemove = frequencies(frequencies.firstKey)
      frequencies -= frequencies.firstKey
      cache -= keyToRemove
    }

  }

  def main(args: Array[String]): Unit = {
    put(1, 1)
    put(2, 2)
    println(get(1)) // returns 1

    put(3, 3) // evicts key 2

    println(get(2)) // returns -1 (not found)

    put(4, 4) // evicts key 1

    println(get(1)) //-1
    println(get(3)) // returns 3
    println(get(4)) // returns 4

  }
}
