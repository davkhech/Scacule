package src

object Solver {
  val dR = List((0, 1), (-1, 0), (0, -1), (1, 0))

  def createMoleculeList(len: Int): List[Molecule] = {
    val atomAt = atomGetter(len)
    def _iter(l: Int, trace: List[Atom]): List[Molecule] = {
      val last = trace.head
      if (l == 0) List(Molecule(trace))
      else dR.map(dr ⇒ _iter(l - 1, atomAt(last.x + dr._1, last.y + dr._2) :: trace)).reduce(_ ++ _)
    }

    _iter(len, List(Atom(0, 0)))
  }

  def atomGetter(len: Int): (Int, Int) ⇒ Atom = {
    lazy val atoms = (-len to len).map(x ⇒ x → (-len to len).map(y ⇒ y → Atom(x, y)).toMap).toMap

    (x: Int, y: Int) ⇒ atoms(x)(y)
  }
}
