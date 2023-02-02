package io.github.liewhite.config

import zio.*
import zio.config._
import zio.config.magnolia.*
import zio.config.yaml.YamlConfigSource

import java.nio.file.Path

def loadConfig[A: Descriptor](
    path: String = "./conf/config.yaml"
): ZIO[Any, ReadError[K], A] = {
  for {
    _ <- ZIO.unit
    source = YamlConfigSource.fromYamlPath(Path.of(path))
    desc = descriptor[A] from source
    conf <- read(desc)
  } yield conf
}
def describeConfig[A: Descriptor](
    path: String = "./conf/config.yaml"
): zio.config.Table = {
  val desc = descriptor[A]
  val doc = generateDocs(desc).toTable
  doc
}

// object App extends ZIOAppDefault {

//   case class A(a: Int, b: Option[Boolean])
//   override def run: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] = {
//     for {
//       a <- loadConfig[A]()
//       _ <- Console.printLine(a)
//       desc <- describeConfig[A]()
//       _ <- Console.printLine(desc)
//     } yield ()
//   }

// }
