package src

class Molecule(atoms: List[Atom]){
  def getEnergy = ???

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
