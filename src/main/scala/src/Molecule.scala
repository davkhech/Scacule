package src

class Molecule(atoms: List[Atom]){
  def getEnergy(eps0: Double, eps1: Double) = {
    def iter(atoms: List[(Atom, Atom)], d: Set[Atom], s: Set[Atom]): (Set[Atom], Set[Atom]) = atoms match {
      case Nil ⇒ (d, s)
      case h::t ⇒
        if (h._1.y == 0 && h._2.y == 0) iter(t, d + h._1 + h._2, s + h._1 + h._2)
        else if (h._1.y != 0 && h._2.y == 0) iter(t, d, s + h._2)
        else if (h._1.y == 0 && h._2.y != 0) iter(t, d, s + h._1)
        else iter(t, d, s)
    }

    val (d, s) = iter(atoms zip atoms.tail, Set(), Set())
    eps0 * d.size / 2 + eps1 * (s.size - d.size)
  }

  def getRadialDistance = Math.sqrt(Math.pow(atoms.head.x, 2) + Math.pow(atoms.head.y, 2))

  def getX = atoms.head.x

  def getY = atoms.head.y

  override def toString: String = {
    atoms.map(atom ⇒ "(" + atom.x + ", " + atom.y + ") ").reduce(_ + _)
  }
}

object Molecule{
  def apply(atoms: List[Atom]): Molecule = new Molecule(atoms)
}
