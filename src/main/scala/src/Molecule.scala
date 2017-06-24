package src

class Molecule(val x: Int, val y: Int) {
  override def toString: String = "(" + x + ", " + y + ")"
}

object Molecule {
  def apply(x: Int, y: Int): Molecule = new Molecule(x, y)
}