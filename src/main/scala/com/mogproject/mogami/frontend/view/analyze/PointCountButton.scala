package com.mogproject.mogami.frontend.view.analyze

import com.mogproject.mogami.frontend._
import com.mogproject.mogami.frontend.action.analyze.CountPointAction
import com.mogproject.mogami.frontend.model.English
import com.mogproject.mogami.frontend.sam.PlaygroundSAM
import com.mogproject.mogami.frontend.view.WebComponent
import com.mogproject.mogami.frontend.view.button.SingleButton
import com.mogproject.mogami.util.Implicits._
import org.scalajs.dom.html.Div

import scalatags.JsDom.all._

/**
  *
  */
class PointCountButton extends WebComponent with SAMObserver[BasePlaygroundModel] {

  private[this] val countButton: SingleButton = SingleButton(
    Map(English -> "Count".render),
    clickAction = Some(() => PlaygroundSAM.doAction(CountPointAction)),
    tooltip = Map(English -> "Count points for this position"),
    isBlockButton = true
  )

  private[this] lazy val countMessage: Div = div(
    cls := "col-xs-8 col-sm-9 text-muted",
    marginTop := 6
  ).render

  override lazy val element: Div = div(
    div(cls := "row",
      div(cls := "col-xs-4 col-sm-3",
        countButton.element
      ),
      countMessage
    )
  ).render

  //
  // messaging
  //
  def displayResult(point: Int, isKingInPromotionZone: Boolean, numPiecesInPromotionZone: Int): Unit = {
    val plural = (1 < numPiecesInPromotionZone).fold("s", "")

    val msg = Seq(
      s"Points: ${point}",
      "In the promotion zone: " + isKingInPromotionZone.fold("King + ", "") + s"${numPiecesInPromotionZone} piece${plural}"
    ).mkString("\n")
    displayMessage(msg)
  }

  private[this] def displayMessage(message: String): Unit = {
    countMessage.innerHTML = message.replace("\n", br().toString())
  }

  def clearMessage(): Unit = {
    countMessage.innerHTML = ""
  }

  //
  // Observer
  //
  override val samObserveMask: Int = ObserveFlag.GAME_BRANCH | ObserveFlag.GAME_POSITION

  override def refresh(model: BasePlaygroundModel, flag: Int): Unit = {
    clearMessage()
  }
}
