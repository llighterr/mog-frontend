package com.mogproject.mogami.frontend.view.modal

import com.mogproject.mogami.frontend._
import com.mogproject.mogami.frontend.view.event.EventManageable
import org.scalajs.dom.html.Element
import org.scalajs.jquery.JQuery

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

/**
  * Yes-no dialog
  */
case class YesNoDialog(lang: Language, message: TypedTag[Element], callback: () => Unit) extends EventManageable with ModalLike {

  //
  // yes no specific
  //
  private[this] val yes = lang match {
    case Japanese => "はい"
    case English => "Yes"
  }

  private[this] val no = lang match {
    case Japanese => "いいえ"
    case English => "No"
  }

  private[this] val yesButton = button(
    tpe := "button", cls := "btn btn-primary btn-block", dismiss, yes
  ).render

  //
  // modal traits
  //
  override def isStatic: Boolean = true

  override val title: String = lang match {
    case Japanese => "確認"
    case English => "Confirmation"
  }

  override val modalBody: ElemType = div(bodyDefinition, message)

  override val modalFooter: ElemType = div(footerDefinition,
    div(cls := "row",
      div(cls := "col-xs-4 col-xs-offset-4 col-md-3 col-md-offset-6",
        button(tpe := "button", cls := "btn btn-default btn-block", dismiss, no)
      ),
      div(cls := "col-xs-4 col-md-3",
        yesButton
      )
    )
  )

  override def initialize(dialog: JQuery): Unit = {
    setModalClickEvent(yesButton, dialog, callback)
  }

}
