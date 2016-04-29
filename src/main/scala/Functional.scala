import scala.io.StdIn

object Functional {
  def main(args: Array[String]): Unit = loop(1000).run

  def loop(balance: Int): IO[Unit] = for {
    line <- getLine
    newBalance <- line match {
      case "withdraw" => updateBalance(balance, _ - 100)
      case "deposit" => updateBalance(balance, _ + 100)
      case _ => IO[Int](balance)
    }
    _ <- putStrLn("Your balance is now " + newBalance)
    _ <- line match {
      case "quit" => IO[Unit]()
      case _ => loop(newBalance)
    }
  } yield Unit

  def getLine: IO[String] = IO(StdIn.readLine())

  def putStrLn(v: String): IO[Unit] = IO(println(v))

  def updateBalance(balance: Int, f: Int => Int): IO[Int] = IO(f(balance))
}

class IO[A] private(run0: => A) {
  def run = run0

  def flatMap[B](f: A => IO[B]): IO[B] = IO(f(run).run)

  def map[B](f: A => B): IO[B] = flatMap(a => IO(f(a)))
}

object IO {
  def apply[A](v: => A): IO[A] = new IO(v)
}
