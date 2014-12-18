package recfun

object test {
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
      1
    else if (coins.size == 0 || money < 0)
      0
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }                                               //> countChange: (money: Int, coins: List[Int])Int
  
  println(countChange(4, List(1, 2)))             //> 3
  
  
  def countChange2(money: Int, coins: List[Int]): List[List[Int]] = {
  	def countChange0(money: Int, coins: List[Int], acc: List[Int]): List[List[Int]] = {
  		if(money == 0) {
  			List(acc)
  		} else if(money < 0 || coins.isEmpty) {
  			println("acc:\t" + acc)
  			Nil
  		} else {
  			countChange0(money - coins.head, coins, acc :+ coins.head) ++ countChange0(money, coins.tail, acc)
  		}
  	}
    countChange0(money, coins, List())
  }                                               //> countChange2: (money: Int, coins: List[Int])List[List[Int]]
  
  println(countChange2(4, List(1, 2)))            //> acc:	List(1, 1, 1, 2)
                                                  //| acc:	List(1, 1, 1)
                                                  //| acc:	List(1, 1)
                                                  //| acc:	List(1, 2, 2)
                                                  //| acc:	List(1, 2)
                                                  //| acc:	List(1)
                                                  //| acc:	List(2)
                                                  //| acc:	List()
                                                  //| List(List(1, 1, 1, 1), List(1, 1, 2), List(2, 2))
}