package src

object Main extends App{
  println("Starting the computation ...")
  val startTime = System.currentTimeMillis()
  val molecules = Solver.createMoleculeList(10)
  println("All molecule combinations are ready. Computing the statistics ...")
  (270 to 320).map {t ⇒
    Statistics.forTemperature(t, molecules)
  }.foreach(println)
  val endTime = System.currentTimeMillis()
  printf("Computation has been finished. It took %dms\n", endTime - startTime)
}
