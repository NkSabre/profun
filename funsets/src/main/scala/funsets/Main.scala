package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  val s = (x: Int) => x > 0 && x < 4
  assert(exists(s, x => x % 2 == 0), "exists 1")
  //assert(forall(s, x => x < 4), "forall 1")
  assert(!forall(s, x => x > 3), "forall 2")
  assert(forall(s, x => x < 4), "forall 1")
}
