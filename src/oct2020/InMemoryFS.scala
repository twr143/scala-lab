package oct2020

/**
  * Created by Ilya Volynin on 05.10.2020 at 16:17.
  */
object InMemoryFS {
  private def getSecondSlash(s: String): Int = {
    var cnt = 0
    var i = 0
    while (cnt < 1 && i < s.length - 1) {
      i += 1
      if (s(i) == '/') cnt += 1
    }
    if (cnt == 1) i else s.length
  }

  case class File(name: String, var contents: String = "")
  case class Dir(name: String, var files: Set[File] = Set.empty, var dirs: Set[Dir] = Set.empty) {
    def mkDir(rest: String): Unit =
      if (rest.nonEmpty) {
        val secondSlash = getSecondSlash(rest)
        val n = rest.substring(0, secondSlash)
        val r = rest.substring(secondSlash)
        var exists = false
        for (d <- dirs)
          if (d.name == n) {
            exists = true
            d.mkDir(r)
          }
        if (!exists) {
          val newDir = Dir(n)
          dirs += newDir
          newDir.mkDir(r)
        }
      }

    def ls(path: String): List[String] = {
      if (path.isEmpty || path == "/")
        (files.map(_.name.substring(1)) ++ dirs.map(_.name.substring(1))).toList.sorted
      else {
        val secondSlash = getSecondSlash(path)
        val n = path.substring(0, secondSlash)
        val r = path.substring(secondSlash)
        for (d <- dirs)
          if (d.name == n)
            return d.ls(r)
        for (f <- files)
          if (f.name == n)
            return List(f.name.substring(1))
        return List.empty
      }
    }

    def addContent(filePath: String, content: String): Unit = {
      val secondSlash = getSecondSlash(filePath)
      if (secondSlash == filePath.length) {
        var exists = false
        for (f <- files)
          if (f.name == filePath) {
            exists = true
            f.contents += content
          }
        if (!exists) {
          val newFile = File(filePath, content)
          files += newFile
        }
      } else {
        val n = filePath.substring(0, secondSlash)
        val r = filePath.substring(secondSlash)
        var exists = false
        for (d <- dirs)
          if (d.name == n) {
            exists = true
            d.addContent(r, content)
          }
        if (!exists) {
          val newDir = Dir(n)
          dirs += newDir
          newDir.addContent(r, content)
        }
      }
    }

    def readContent(filePath: String): String = {
      val secondSlash = getSecondSlash(filePath)
      if (secondSlash == filePath.length) {
        var exists = false
        for (f <- files)
          if (f.name == filePath)
            return f.contents
      } else {
        val n = filePath.substring(0, secondSlash)
        val r = filePath.substring(secondSlash)
        var exists = false
        for (d <- dirs)
          if (d.name == n) {
            return d.readContent(r)
          }
      }
      ""
    }


  }
  lazy val root = Dir("/")

  def mkdir(path: String): Unit = {
    root.mkDir(path)

  }

  def ls(path: String): List[String] = {
    root.ls(path)
  }


  def addContentToFile(filePath: String, content: String) {
    root.addContent(filePath, content)
  }

  def readContentFromFile(filePath: String): String = {
    root.readContent(filePath)
  }

  def main(args: Array[String]): Unit = {
    mkdir("/a/b/c")
    addContentToFile("/a/b/c/d", "hello")
    println(ls("/a/b/c/d"))
//    println(readContentFromFile("/a/b/c/d"))
  }
}
