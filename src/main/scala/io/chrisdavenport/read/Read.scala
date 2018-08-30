package io.chrisdavenport.read


/**
  * Read is a typeclass for working with the general inputs
  * to a programming language.
  *
  * Law:
  * a: A => (Show[A].show andThen Read[A].read)(a) === Right(a)
  **/
trait Read[A]{
  def read(s: String): Either[ReadException, A]
  final def unsafeRead(s: String): A = 
    read(s).fold(e => throw e, identity)
}

object Read {
  def apply[A](implicit ev: Read[A]): Read[A] = ev
}