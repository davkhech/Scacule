package src

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App{
  println("Starting the computation ...")
  val startTime = System.currentTimeMillis()
  val polymers = Solver.createPolymerListSelfCross(9)
  val polymerComputeEndTime = System.currentTimeMillis()
  printf("All molecule combinations are ready in %dms. Computing the statistics ...\n", polymerComputeEndTime - startTime)

  (300 to 310).map {t ⇒
    Statistics.forTemperature(t, polymers)
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
