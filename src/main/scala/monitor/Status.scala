package monitor

abstract class Status {
  def toCode: String
  def +(other: Status): Status = if (severity >= other.severity) this else other
  val severity: Int
}

object Red extends Status {
  def toCode = "1000"
  val severity = 2
  override def toString = "Red"
}

object Yellow extends Status {
  def toCode = "0100"
  val severity = 1
  override def toString = "Yellow"
}

object Green extends Status {
  def toCode = "0010"
  val severity = 0
  override def toString = "Green"
}

object NoLights extends Status {
  def toCode = "0000"
  val severity = 0
  override def toString = "No lights"
}
