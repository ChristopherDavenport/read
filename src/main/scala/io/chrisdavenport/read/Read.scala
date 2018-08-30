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

  def readNonFatal[A](f: String => A): Read[A] = new Read[A]{
    override def read(s: String): Either[ReadException, A] = 
      util.parseNonFatal(f(s))
  } 

  def functor[A, B](r: Read[A])(f: A => B): Read[B] = new Read[B]{
    def read(s: String): Either[ReadException, B] = 
      r.read(s).map(f)
  }
}