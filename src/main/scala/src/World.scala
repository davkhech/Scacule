package src

import scala.collection.immutable.HashMap

class World(len: Int){
  lazy val atoms = (-len to len).map(x ⇒ x → (-len to len).map(y ⇒ y → Atom(x, y)).toMap).toMap
//    (-len to len).flatMap(x ⇒ (-len to len).map(y ⇒ (x, y) → Atom(x, y)))

  def getAtom(x: Int, y: Int) = atoms(x)(y)
}

object World {
  def apply(len: Int): World = new World(len)
}
