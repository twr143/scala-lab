package types

sealed class StringOrInt[T]
    object StringOrInt {
      implicit object IntWitness extends StringOrInt[Int]
      implicit object StringWitness extends StringOrInt[String]
    }