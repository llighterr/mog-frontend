package com.mogproject.mogami.frontend.view.board.box

import com.mogproject.mogami.Ptype
import com.mogproject.mogami.frontend.{Coord, Rect}
import org.scalajs.dom.raw.SVGTextElement
import org.scalajs.dom.svg.RectElement

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

/**
  *
  */
case class SVGBoxLayout(centerX: Int,
                        pieceSize: Coord,
                        bottomMargin: Int = 30,
                        labelHeight: Int = 87,
                        strokeWidth: Int = 5,
                        labelFontSize: Int = 80,
                        shadowWidth: Int = 30
                       ) {

  final val numberSize: Coord = Coord(120, 120)

  final val PIECE_FACE_SIZE: Int = pieceSize.x * 20 / 21

  final val numberAdjustment: Coord = Coord(pieceSize.x * 7 / 8, pieceSize.x * 8 / 7 - numberSize.y)

  val boxWidth: Int = 8 * pieceSize.x

  val labelRect: Rect = Rect(Coord(centerX - boxWidth / 2 - strokeWidth, 0), boxWidth + 2 * strokeWidth + 1, labelHeight)

  val boxRect: Rect = Rect(labelRect.leftBottom + Coord(strokeWidth, strokeWidth), boxWidth, pieceSize.y)

  val shadowRect: Rect = Rect(labelRect.rightTop - Coord(1, 0), shadowWidth, labelHeight + pieceSize.y + 2 * strokeWidth)

  val boxBorder: TypedTag[RectElement] = boxRect.toSVGRect(cls := "board-border")

  val boxShadow: Seq[TypedTag[RectElement]] = Seq(labelRect, shadowRect).map(_.toSVGRect(cls := "box-label"))

  val boxLabelText: TypedTag[SVGTextElement] = {
    val r = labelRect.copy(leftTop = Coord(labelRect.center.x, labelRect.top), width = 0)
    val fs = labelFontSize
    val fc = fs * 60 / 100 // center of the font
    r.toSVGText("UNUSED PIECES", false, Some((fs, fc, false)), cls := "indicator-text")
  }

  val extendedHeight: Int = labelHeight + boxRect.height + 2 * strokeWidth + bottomMargin

  def getRect(ptype: Ptype): Rect = {
    Rect(boxRect.leftTop + Coord(ptype.sortId * pieceSize.x, 0), pieceSize.x, pieceSize.y)
  }
}
