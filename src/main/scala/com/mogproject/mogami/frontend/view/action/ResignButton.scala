package com.mogproject.mogami.frontend.view.action

import com.mogproject.mogami.frontend._
import com.mogproject.mogami.frontend.action.game.ResignAction
import com.mogproject.mogami.frontend.view.button.SingleButton
import com.mogproject.mogami.frontend.view.modal.YesNoDialog
import org.scalajs.dom.raw.HTMLElement

import scalatags.JsDom.all._

/**
  * Resign button
  */
case class ResignButton(isSmall: Boolean, confirm: Boolean) extends WebComponent with SAMObserver[BasePlaygroundModel] {
  private[this] def createLabel(messageLang: Language) = {
    val s = Map[Language, String](English -> "Resign", Japanese -> "投了")(messageLang)
    if (isSmall) {
      Seq(StringFrag(s + " "), span(cls := s"glyphicon glyphicon-flag", aria.hidden := true)).render
    } else {
      s.render
    }
  }

  private[this] val button = SingleButton(
    Map(English -> createLabel(English), Japanese -> createLabel(Japanese)),
    "btn-default" +: Seq("thin-btn", "btn-resign").filter(_ => isSmall),
    clickAction = Some(() => clickAction()),
    isBlockButton = !isSmall,
    dismissModal = !isSmall
  )

  def clickAction(): Unit = if (confirm) {
    YesNoDialog(English, div("Do you really want to resign?"), () => clickActionImpl()).show()
  } else {
    clickActionImpl()
  }

  def clickActionImpl(): Unit = {
    doAction(ResignAction)
  }

  override val element: HTMLElement = button.element

  //
  // Observer
  //
  override val samObserveMask: Int = {
    import ObserveFlag._
    MODE_TYPE | GAME_BRANCH | GAME_POSITION
  }

  override def refresh(model: BasePlaygroundModel, flag: Int): Unit = setDisabled(!model.mode.canMakeMove)

}
