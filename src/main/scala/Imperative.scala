import scala.io.StdIn

object Imperative {
  def main(args: Array[String]): Unit = {
    var on = true
    var balance = 1000
    var text = ""

    while(on){
      text = StdIn.readLine()
      if(text == "withdraw") {
        println("Withdrawing £100")
        balance -= 100
        println("Balance is now £" +  balance)
      }
      else if(text == "deposit") {
        println("Depositing £100")
        balance += 100
        println("Balance is now £" +  balance)
      }
      else if (text == "quit") on = false
    }
  }
}
