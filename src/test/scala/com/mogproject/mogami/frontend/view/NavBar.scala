package com.mogproject.mogami.frontend.view

import com.mogproject.mogami.util.Implicits._
import com.mogproject.mogami.frontend.action.ChangeModeAction
import com.mogproject.mogami.frontend._
import com.mogproject.mogami.frontend.model.TestModel
import com.mogproject.mogami.frontend.sam.SAMObserver
import com.mogproject.mogami.frontend.state.ObserveFlag
import com.mogproject.mogami.frontend.view.button.RadioButton
import com.mogproject.mogami.frontend.view.nav.NavBarLike

/**
  *
  */
case class NavBar(isMobile: Boolean) extends NavBarLike with SAMObserver[TestModel] {

  lazy val modeButton: RadioButton[ModeType] = RadioButton(
    Seq(PlayModeType, ViewModeType, EditModeType),
    Map(English -> Seq("Play", "View", "Edit")),
    (mt: ModeType) => doAction(ChangeModeAction(mt, confirmed = false)),
    Seq("thin-btn", "mode-select"),
    Seq.empty
  )

  override lazy val buttons: Seq[WebComponent] = modeButton +: super.buttons

  //
  // Observer
  //
  override val samObserveMask: Int = ObserveFlag.MODE_TYPE

  override def refresh(model: TestModel, flag: Int): Unit = {
    val modeType = model.mode.modeType
    modeButton.updateValue(modeType)
    replaceClass(navElem, "nav-bg-", s"nav-bg-${modeType.toString.take(4).toLowerCase()}")
  }
}