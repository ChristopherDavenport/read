package io.chrisdavenport.read

import util.readNonFatal

trait Read[A]{
  def read(s: String): Either[ReadException, A]
  final def unsafeRead(s: String): A = 
    read(s).fold(e => throw e, identity)
}

object Read {
  def apply[A](implicit ev: Read[A]): Read[A] = ev

  implicit val readString : Read[String] = readNonFatal(identity)
  implicit val readInt: Read[Int] = readNonFatal(_.toInt)
  implicit val readDouble: Read[Double] = readNonFatal(_.toDouble)
  implicit val readLong : Read[Long] = readNonFatal(_.toLong)
  implicit val readFloat: Read[Float] = readNonFatal(_.toFloat)

}