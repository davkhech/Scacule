import src.Atom

import scala.collection.immutable.HashMap

val m = (-3 to 3).flatMap(x ⇒ (-3 to 3).map(y ⇒ (x, y) → Atom(x, y))).toMap
HashMap(3→4)



val nm = (-3 to 3).map(x ⇒ x → (-3 to 3).map(y ⇒ y → Atom(x, y)).toMap).toMap
nm(1)(2)