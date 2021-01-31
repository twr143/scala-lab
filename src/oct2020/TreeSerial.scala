package oct2020

import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 06.10.2020 at 9:53.
  */
object TreeSerial {

  case class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }


  class Codec {
    // Encodes a list of strings to a single string.
    def serialize(root: TreeNode): String = {
      if (root == null) return ""
      val lb = ListBuffer.empty[String]
      s(root, lb)
      lb.mkString(",")
    }

    def s(root: TreeNode, lb: ListBuffer[String]): Unit = {
      lb += root.value.toString
      if (root.left == null) lb += "x" else s(root.left, lb)
      if (root.right == null) lb += "x" else s(root.right, lb)
    }

    // Decodes a single string to a list of strings.
    def deserialize(s: String): TreeNode = {
      if (s.isEmpty) null
      else {
        val nodes = s.split(",")
        val r = TreeNode(nodes(0).toInt)
        d(nodes, 1, r)
        r
      }
    }

    def d(nodes: Array[String], i: Int, root: TreeNode): Int = {
      var shift = 0
      if (nodes(i) != "x") {
        root.left = new TreeNode(nodes(i).toInt)
        shift += d(nodes, i + 1, root.left)
      }
      if (nodes(i + 1 + shift) != "x") {
        root.right = new TreeNode(nodes(i + 1 + shift).toInt)
        shift += d(nodes, i + shift + 2, root.right)
      }
     /*if (shift > 0) shift else*/shift+ 2
    }

  }
  def main(args: Array[String]): Unit = {
    val r = TreeNode(2)
    val rl = TreeNode(3)
    val rr = TreeNode(4)
    val rll = TreeNode(5)
    rl.left = rll
    r.left = rl
    r.right = rr
    val root = TreeNode(0)
    root.right = r
    val c = new Codec
    val ser = c.serialize(root)
    println(ser)
    println(c.serialize(c.deserialize(ser)))
  }
}
