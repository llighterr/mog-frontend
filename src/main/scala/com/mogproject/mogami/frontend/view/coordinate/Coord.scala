package com.mogproject.mogami.frontend.view.coordinate

import org.scalajs.dom.raw.{SVGCircleElement, SVGPolygonElement}

import scalatags.JsDom.{Modifier, TypedTag, svgAttrs}
import scalatags.JsDom.svgTags.{circle, polygon}
import scalatags.JsDom.all._

/**
  * 2-D coordinates
  */
case class Coord(x: Int, y: Int) {
  def unary_- : Coord = Coord(-x, -y)

  def +(coord: Coord): Coord = Coord(this.x + coord.x, this.y + coord.y)

  override def toString: String = s"${x},${y}"

  def toSVGCircle(radius: Int, modifier: Modifier*): TypedTag[SVGCircleElement] =
    circle(Seq(svgAttrs.cx := x, svgAttrs.cy := y, svgAttrs.r := radius) ++ modifier: _*)

  def toSVGPolygon(nodes: Seq[Coord], modifier: Modifier*): TypedTag[SVGPolygonElement] =
    polygon(Seq(svgAttrs.points := (this +: nodes).mkString(" ")) ++ modifier: _*)
}
