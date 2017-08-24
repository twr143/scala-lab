object Composition {

  def main(args: Array[String]): Unit = {

  }
  def compose[A](f: A=>Option[A],g: A=>Option[A], x:A):Option[A]={
    if (f(x).isEmpty || g(f(x).get).isEmpty) None else g(f(x).get)
  }
  def f(x:Double):Option[Double]={
    x match{
      case 1 => None
      case x1 => Some(1/(x1-1))
    }
  }
}
