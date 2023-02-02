# Load or describe config  
```scala
object App extends ZIOAppDefault {

  case class A(a: Int, b: Option[Boolean])
  override def run: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] = {
    for {
      a <- loadConfig[A]()
      _ <- Console.printLine(a)
      desc <- describeConfig[A]()
      _ <- Console.printLine(desc)
    } yield ()
  }
}
```