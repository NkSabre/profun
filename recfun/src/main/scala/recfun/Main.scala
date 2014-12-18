package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == 0 || c == r){
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance0(chars: List[Char], leftCount: Int): Boolean = {
      if(chars.isEmpty) {
        leftCount == 0
      } else {
        chars.head match {
          case '(' => balance0(chars.tail, leftCount + 1)
          case ')' if leftCount > 0 => balance0(chars.tail, leftCount - 1)
          case ')' => false
          case _ => balance0(chars.tail, leftCount)
        }
      }
    }
    balance0(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 
      1 
    else if (coins.size == 0 || money < 0) 
      0 
    else 
      countChange(money, coins.tail) + countChange(money - coins.head, coins) 
  }
}
