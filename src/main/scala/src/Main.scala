package src

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import breeze.linalg._
import breeze.plot._

object Main extends App{

//  val f = Figure()
//  val p = f.subplot(0)

  println("Starting the computation ...")
  val startTime = System.currentTimeMillis()
  val polymers = Solver.createPolymerList(8)
  val polymerComputeEndTime = System.currentTimeMillis()
  printf("All molecule combinations are ready in %dms. Computing the statistics ...\n", polymerComputeEndTime - startTime)

  val stats = (300 to 600).map(t ⇒ Statistics.forTemperature(t, polymers)).toList
  val statSet = StatisticsSet(stats)

  statSet.saveCSV("data.csv")

//  polymers.take(10).foreach(polymer ⇒
//    p += plot(polymer.getCoordinatesX, polymer.getCoordinatesY)
//  )
//  f.saveas("lines.png")

//  val x_ = (300 to 600) map {t ⇒
//    Statistics.forTemperature(t, polymers)
//  } map {stat ⇒
//    stat.meanXSqr
//  }

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
