package com.mogproject.mogami.frontend.view.modal


import com.mogproject.mogami.util.Implicits._
import com.mogproject.mogami._
import com.mogproject.mogami.frontend.action.board.MakeMoveAction
import com.mogproject.mogami.frontend.{Coord}
import com.mogproject.mogami.frontend.sam.PlaygroundSAM
import com.mogproject.mogami.frontend.view.button.PieceFaceButton
import com.mogproject.mogami.frontend.view.{English, Japanese, Language}
import com.mogproject.mogami.frontend.view.event.EventManageable
import com.mogproject.mogami.frontend.view.piece.PieceFace
import org.scalajs.jquery.JQuery

import scalatags.JsDom.all._

/**
  * Promotion dialog
  */
case class PromotionDialog(messageLang: Language,
                           pieceFace: PieceFace,
                           pieceSize: Coord,
                           rawMove: Move,
                           rotate: Boolean
                          ) extends EventManageable with ModalLike {
  //
  // promotion specific
  //
  private[this] val buttonUnpromote: PieceFaceButton = PieceFaceButton(pieceFace, pieceSize, rawMove.oldPtype, rotate)
  private[this] val buttonPromote = PieceFaceButton(pieceFace, pieceSize, rawMove.oldPtype.promoted, rotate)

//    button(tpe := "button", cls := "btn btn-default btn-block",
//    style := s"height: ${pieceRenderer.layout.PIECE_HEIGHT * textScale}px !important",
//    data("dismiss") := "modal",
//    canvasUnpromote
//  ).render

//  private[this] val buttonPromote = button(tpe := "button", cls := "btn btn-default btn-block",
//    style := s"height: ${pieceRenderer.layout.PIECE_HEIGHT * textScale}px !important",
//    data("dismiss") := "modal",
//    canvasPromote
//  ).render

  //
  // modal traits
  //
  override def displayCloseButton: Boolean = false

  override def isStatic: Boolean = true

  override val title: String = messageLang match {
    case Japanese => "成りますか?"
    case English => "Do you want to promote?"
  }

  override val modalBody: ElemType = div()

  override val modalFooter: ElemType = div(footerDefinition,
    div(cls := "row",
      div(cls := "col-xs-5 col-xs-offset-1 col-md-3 col-md-offset-3", buttonUnpromote.element),
      div(cls := "col-xs-5 col-md-3", buttonPromote.element)
    )
  )

  override def initialize(dialog: JQuery): Unit = {
    setModalClickEvent(buttonUnpromote.element, dialog, () => PlaygroundSAM.doAction(MakeMoveAction(rawMove)))
    setModalClickEvent(buttonPromote.element, dialog, () => PlaygroundSAM.doAction(MakeMoveAction(rawMove.copy(promote = true))))
  }

}
