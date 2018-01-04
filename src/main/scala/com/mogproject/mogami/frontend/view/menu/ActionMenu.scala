package com.mogproject.mogami.frontend.view.menu

import com.mogproject.mogami.frontend.Language
import com.mogproject.mogami.frontend.model.{English, Japanese, PlayModeType}
import com.mogproject.mogami.frontend.view.action.ResignButton
import org.scalajs.dom.html.Div

import scalatags.JsDom
import scalatags.JsDom.all._

/**
  *
  */
class ActionMenu extends AccordionMenu {

  override lazy val ident: String = "Action"
  override lazy val titleLabel: Map[Language, String] = Map(English -> ident, Japanese -> "特殊な指し手")
  override lazy val icon: String = "tower"
  override lazy val visibleMode = Set(PlayModeType)

  private[this] lazy val resignButton = ResignButton(isSmall = false, confirm = false)

  override lazy val content: JsDom.TypedTag[Div] = div(cls := "row",
    div(cls := "col-xs-6 col-sm-4",
      resignButton.element
    )
  )

}
