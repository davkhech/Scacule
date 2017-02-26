package src

class Atom(val x: Int, val y: Int) {
  override def toString = "(" + x + ", " + y + ")"
}

object Atom {
  def apply(x: Int, y: Int): Atom = new Atom(x, y)
}