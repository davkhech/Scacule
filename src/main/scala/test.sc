import src.Atom

import scala.collection.immutable.HashMap

val m = (-3 to 3).flatMap(x ⇒ (-3 to 3).map(y ⇒ (x, y) → Atom(x, y))).toMap
HashMap(3→4)



val nm = (-3 to 3).map(x ⇒ x → (-3 to 3).map(y ⇒ y → Atom(x, y)).toMap).toMap
nm(1)(2)

val e:Array[Int] = Array(3, 4, 5)
(1 to 5).filter(x ⇒ !e.contains(x)).toArray

val w = "Hello"
val q = w.toLowerCase.toList.groupBy(x ⇒ x).map(x ⇒ (x._1, x._2.length)).toList


val r = List(q, q)
r.flatten.groupBy(w ⇒ w._1).map(w ⇒ (w._1, w._2.map(_._2).sum))
r.flatten.groupBy(c ⇒ c._1).map(w ⇒ (w._1, w._2.map(_._2).sum))