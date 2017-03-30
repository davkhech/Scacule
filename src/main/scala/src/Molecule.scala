package src

class Molecule(atoms: List[Atom]){
  def getEnergy(eps0: Int, eps1: Int) = {
    def iter(atoms: List[Atom]): Int = atoms.tail match {
      case atom ⇒ 1
      case atom::tail ⇒ 1
    }

    iter(atoms)
  }

  def getRadialDistance = ???

  override def toString: String = {
    atoms.foreach(atom ⇒ print(atom, " "))
    " "
  }
//    atoms.map(atom ⇒ "(" + atom.x + ", " + atom.y + ") ").reduce(_ + _)
}

object Molecule{
  def apply(atoms: List[Atom]): Molecule = new Molecule(atoms)
}
