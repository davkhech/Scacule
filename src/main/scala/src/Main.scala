package src

object Main extends App{
  println("Starting the computation ...")
  val startTime = System.currentTimeMillis()
  val molecules = Solver.createMoleculeList(10)
  val endTime = System.currentTimeMillis()
  printf("Computation has been finished. It took %dms\n", endTime - startTime)
}
