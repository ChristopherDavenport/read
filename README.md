# read [![Build Status](https://travis-ci.com/ChristopherDavenport/read.svg?branch=master)](https://travis-ci.com/ChristopherDavenport/read) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/read_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/read_2.12)

Read Typeclass for Scala. Controversial? Yes. Problematic? Yes. Better than watching
your friends write `string.toInt` and calling it good enough? Absolutely!

## Quickstart

To use read in an existing SBT project with Scala 2.11 or a later version, add the following dependency to your
`build.sbt`:

```scala
libraryDependencies += "io.chrisdavenport" %% "read" % "<version>"
```

## Examples

```scala
import io.chrisdavenport.read.Read
import io.chrisdavenport.read.implicits._

"1".read[Int] // Right(1)

"Foo".read[Int] // Left

// For Those Who Want Haskell's Read
Read[Int].unsafeRead("1") // 1: Int
```
