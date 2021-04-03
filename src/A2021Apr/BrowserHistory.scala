package A2021Apr

import scala.collection.mutable._

/**
  * Created by twr143 on 30.03.2021 at 11:30.
  */
class BrowserHistory(_homepage: String) {
  var history = ArrayBuffer(_homepage)
  var current = 0
  var size = 1

  def visit(url: String) {
    if (current < history.size - 1) {
      history(current + 1) = url
    } else
      history += url
    current += 1
    size = current + 1
  }

  def back(steps: Int): String = {
    if (current > steps) {
      current -= steps
      history(current)
    } else {
      current = 0
      history(0)
    }
  }

  def forward(steps: Int): String = {
    if (current + steps < size - 1) {
      current += steps
      history(current)
    } else {
      current = size - 1
      history(current)
    }
  }
}
