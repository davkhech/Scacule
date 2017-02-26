package src

object Main extends App{
  println("Hello, Vardges")
  val time = System.currentTimeMillis()
//  World(3).atoms.foreach(println)
  Solver.createMoleculeList(12)
//  println (Solver.createMoleculeList(11))
  println("I'm ready!")
  println(System.currentTimeMillis() - time)
}
