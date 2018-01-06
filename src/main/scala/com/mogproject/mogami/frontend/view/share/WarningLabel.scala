package com.mogproject.mogami.frontend.view.share

import com.mogproject.mogami.frontend.view.WebComponent
import com.mogproject.mogami.frontend.view.i18n.{DynamicElement, Messages}
import org.scalajs.dom.raw.HTMLElement

import scalatags.JsDom.all._

/**
  * Warning Label
  */
class WarningLabel extends WebComponent with DynamicElement {

  override lazy val element: HTMLElement = div(
    cls := "alert alert-warning",
    display := display.none.v
  ).render

  override def getDynamicElements(messages: Messages): Seq[Frag] = Seq(
    strong(messages.WARNING + "!"),
    " " + messages.SHARE_WARNING
  )

}
