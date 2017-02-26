package src

object Solver {
  val dx = List(0, -1, 0, 1)
  val dy = List(1, 0, -1, 0)
  val dR = List((0, 1), (-1, 0), (0, -1), (1, 0))

  def createMoleculeList(len: Int): List[Molecule] = {
    val world = World(len)
    def _iter(l: Int, trace: List[Atom]): List[Molecule] = {
      val last = trace.head
      if (l == 0) List(Molecule(trace))
      else dR.map(dr ⇒ _iter(l - 1, world.getAtom(last.x + dr._1, last.y + dr._2) :: trace)).reduce(_ ++ _)
    }

    _iter(len, List(Atom(0, 0)))
  }
}
