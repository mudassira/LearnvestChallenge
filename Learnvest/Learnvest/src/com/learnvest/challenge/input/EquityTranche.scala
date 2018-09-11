package com.learnvest.challenge.input

case class EquityTranche(faceValue: Double) extends Tranche
{
  override def equals(x:Any):Boolean = super.equals(x)
  override def hashCode() = super.hashCode()
}