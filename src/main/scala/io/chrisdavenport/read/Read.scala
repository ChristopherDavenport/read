package io.chrisdavenport.read

trait Read[A]{
  def read(s: String): Either[ReadException.type, A]
  final def forceRead(s: String): A = 
    read(s).fold(e => throw e, identity)
}

object Read {
  def apply[A](implicit ev: Read[A]): Read[A] = ev
}