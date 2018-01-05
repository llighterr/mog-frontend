package com.mogproject.mogami.frontend.view.menu

import com.mogproject.mogami.util.Implicits._
import com.mogproject.mogami.frontend.model.board.{DoubleBoard, FlipDisabled}
import com.mogproject.mogami.frontend._
import com.mogproject.mogami.frontend.view.board.{SVGAreaLayout, SVGCompactLayout, SVGStandardLayout, SVGWideLayout}
import com.mogproject.mogami.frontend.view.i18n.DynamicLabel
import com.mogproject.mogami.frontend.view.setting.{BooleanSelector, DropdownSelector, LanguageSelector}
import org.scalajs.dom.html.Div

import scalatags.JsDom
import scalatags.JsDom.all._

/**
  *
  */
class SettingMenu extends AccordionMenu with SAMObserver[BasePlaygroundModel] {
  override lazy val ident: String = "Settings"
  override lazy val titleLabel = DynamicLabel(_.SETTINGS)
  override lazy val icon: String = "wrench"
  override lazy val visibleMode = Set(PlayModeType, ViewModeType, EditModeType)

  //
  // Elements
  //
  private[this] lazy val boardSizeSelector = DropdownSelector[Option[Int]]("Board Size", Vector(
    None -> "Automatic",
    Some(15) -> "15 - Extra Small",
    Some(20) -> "20",
    Some(25) -> "25",
    Some(30) -> "30 - Small",
    Some(40) -> "40 - Medium",
    Some(50) -> "50 - Large",
    Some(60) -> "60 - Extra Large"
  ), v => _.copy(pieceWidth = v), Seq(1))

  private[this] lazy val layoutSelector = DropdownSelector[SVGAreaLayout]("Layout", Vector(
    SVGStandardLayout -> "Standard",
    SVGCompactLayout -> "Compact",
    SVGWideLayout -> "Wide"
  ), v => _.copy(layout = v))

  private[this] lazy val pieceFaceSelector = DropdownSelector[PieceFace](
    "Piece Graphic", PieceFace.all.map(p => p -> p.displayName).toVector, v => _.copy(pieceFace = v)
  )

  private[this] lazy val doubleBoardSelector = BooleanSelector("Double Board Mode", v => _.copy(flipType = v.fold(DoubleBoard, FlipDisabled)))
  private[this] lazy val visualEffectSelector = BooleanSelector("Visual Effects", v => _.copy(visualEffectEnabled = v))
  private[this] lazy val soundEffectSelector = BooleanSelector("Sound Effects", v => _.copy(soundEffectEnabled = v))

  private[this] lazy val messageLanguageSelector = LanguageSelector("Messages", v => _.copy(messageLang = v))
  private[this] lazy val recordLanguageSelector = LanguageSelector("Record", v => _.copy(recordLang = v))

  private[this] lazy val selectors = Seq(
    boardSizeSelector,
    layoutSelector,
    pieceFaceSelector,
    doubleBoardSelector,
    visualEffectSelector,
    soundEffectSelector,
    messageLanguageSelector,
    recordLanguageSelector
  )

  override lazy val content: JsDom.TypedTag[Div] = div(
    selectors.map(_.element),
    div(
      cls := "alert alert-success setting-alert",
      DynamicLabel(_.SETTINGS_INFO).element
    )
  )

  //
  // Observer
  //
  override val samObserveMask: Int = super.samObserveMask | ObserveFlag.CONF

  override def refresh(model: BasePlaygroundModel, flag: Int): Unit = {
    super.refresh(model, flag)

    val config = model.config

    boardSizeSelector.select(config.pieceWidth)
    layoutSelector.select(config.layout)
    pieceFaceSelector.select(config.pieceFace)

    doubleBoardSelector.updateValue(config.flipType == DoubleBoard)
    visualEffectSelector.updateValue(config.visualEffectEnabled)
    soundEffectSelector.updateValue(config.soundEffectEnabled)

    messageLanguageSelector.updateValue(config.messageLang)
    recordLanguageSelector.updateValue(config.recordLang)
  }

}