package io.chrisdavenport.read

object syntax extends AllSyntax

trait AllSyntax {
  implicit class ReadStringOps(s: String){
    def read[A: Read] : Either[ReadException, A] = 
      Read[A].read(s)
  }
}