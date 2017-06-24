package src

object Solver {
  val dR = List((0, 1), (-1, 0), (0, -1), (1, 0))

  def createPolymerListSelfCross(len: Int): List[Polymer] = {
    val moleculeAt = moleculeGetter(len)
    def _iter(l: Int, trace: List[Molecule]): List[Polymer] = {
      val last = trace.head
      if (l == 0) List(Polymer(trace))
      else dR.map(dr ⇒ _iter(l - 1, moleculeAt(last.x + dr._1, last.y + dr._2) :: trace)).reduce(_ ++ _)
    }

    _iter(len, List(moleculeAt(0, 0)))
  }

  def createPolymerList(len: Int): List[Polymer] = {
    val moleculeAt = moleculeGetter(len)
    def _iter(l: Int, trace: List[Molecule], set: Set[Molecule]): List[Polymer] = {
      val last = trace.head

      if (l == 0) List(Polymer(trace))
      else dR.map(dr ⇒ {
        val next = moleculeAt(last.x + dr._1, last.y + dr._2)
        if (!set.contains(next)) _iter(l - 1, next :: trace, set + last) else List()
      }).reduce(_ ++ _)
    }

    _iter(len, List(moleculeAt(0, 0)), Set(moleculeAt(0, 0)))
  }

  def moleculeGetter(len: Int): (Int, Int) ⇒ Molecule = {
    lazy val atoms = (-len to len).map(x ⇒ x → (-len to len).map(y ⇒ y → Molecule(x, y)).toMap).toMap

    (x: Int, y: Int) ⇒ atoms(x)(y)
  }
}