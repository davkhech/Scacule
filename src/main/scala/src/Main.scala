package src

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App{
  println("Starting the computation ...")
  val startTime = System.currentTimeMillis()
  val molecules = Solver.createMoleculeList(12)
  val moleculeComputeEndTime = System.currentTimeMillis()
  printf("All molecule combinations are ready in %dms. Computing the statistics ...\n", moleculeComputeEndTime - startTime)
  (270 to 300).map {t ⇒
    Statistics.forTemperature(t, molecules)
  }foreach println

//  (270 to 320).map {t ⇒
//    Future{
//      Statistics.forTemperature(t, molecules)
//    }
//  }.foreach{future ⇒
//    future.foreach(println)
//  }
//
//  val temps = (270 to 320).partition(_ % 2 == 0)


  val endTime = System.currentTimeMillis()
  printf("Computation has been finished. It took %dms\n", endTime - startTime)
}
