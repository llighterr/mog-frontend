package com.mogproject.mogami.frontend.state

import com.mogproject.mogami.frontend.model.{BasePlaygroundModel, EditMode}

/**
  *
  */
trait ObserveFlagLike {

  //
  // Configuration
  //
  final val CONF = 1 << 0

  final val CONF_MSG_LANG = 1 << 1
  final val CONF_RCD_LANG = 1 << 2

  final val CONF_DEVICE = 1 << 4
  final val CONF_LAYOUT = 1 << 5
  final val CONF_NUM_AREAS = 1 << 6
  final val CONF_FLIP_TYPE = 1 << 7
  final val CONF_PIECE_WIDTH = 1 << 8
  final val CONF_PIECE_FACE = 1 << 9
  final val CONF_NEW_BRANCH = 1 << 10

  final val CONF_DEV = 1 << 11
  final val CONF_DEBUG = 1 << 12

  //
  // Mode
  //
  final val MODE_TYPE = 1 << 16
  final val MODE_EDIT = 1 << 17

  final val GAME_BRANCH = 1 << 18 // Includes Trunk. Check {turn, board, hand} in Edit Mode
  final val GAME_INFO = 1 << 19
  final val GAME_COMMENT = 1 << 20
  final val GAME_POSITION = 1 << 21
  final val GAME_INDICATOR = 1 << 22
  final val GAME_HANDICAP = 1 << 23
  final val GAME_JUST_MOVED = 1 << 24
  final val GAME_NEXT_POS = 1 << 25
  final val GAME_PREV_POS = 1 << 26
  final val GAME_BRANCH_CHANGED = 1 << 27

  //
  // Menu Dialog (Open/Closed)
  //
  final val MENU_DIALOG = 1 << 28

  //
  // Cursor
  //
  final val CURSOR_ACTIVE = 1 << 29
  final val CURSOR_SELECT = 1 << 30
  final val CURSOR_FLASH = 1 << 31

}

object ObserveFlag extends ObserveFlagLike {
  def getObserveFlag(oldModel: BasePlaygroundModel, newModel: BasePlaygroundModel): Int = {
    var ret: Int = 0

    //
    // Config updates
    //
    if (oldModel.config != newModel.config) {
      ret |= CONF

      val a = oldModel.config
      val b = newModel.config

      if (a.messageLang != b.messageLang) ret |= CONF_MSG_LANG
      if (a.recordLang != b.recordLang) ret |= CONF_RCD_LANG
      if (a.deviceType != b.deviceType) ret |= CONF_DEVICE
      if (a.layout != b.layout) ret |= CONF_LAYOUT
      if (a.flipType != b.flipType) {
        ret |= CONF_FLIP_TYPE

        if (a.flipType.numAreas != b.flipType.numAreas) ret |= CONF_NUM_AREAS
      }
      if (a.pieceWidth != b.pieceWidth) ret |= CONF_PIECE_WIDTH
      if (a.pieceFace != b.pieceFace) ret |= CONF_PIECE_FACE
      if (a.newBranchMode != b.newBranchMode) ret |= CONF_NEW_BRANCH
    }

    //
    // Mode updates
    //
    if (oldModel.mode != newModel.mode) {
      val a = oldModel.mode
      val b = newModel.mode

      if (a.modeType != b.modeType) {
        ret |= MODE_TYPE

        if (a.isEditMode || b.isEditMode) ret |= MODE_EDIT
      }

      if (a.getGameInfo != b.getGameInfo) ret |= GAME_INFO

      (a.getGameControl, b.getGameControl) match {
        case (Some(g), Some(h)) =>
          if (g.game.trunk != h.game.trunk || g.game.branches != h.game.branches) ret |= GAME_BRANCH
          if (g.displayBranchNo != h.displayBranchNo) {
            ret |= GAME_POSITION
            ret |= GAME_BRANCH_CHANGED
          }
          if (g.displayPosition != h.displayPosition) ret |= GAME_POSITION
          if (g.game.comments != h.game.comments) ret |= GAME_COMMENT
        case _ =>
      }

      (a, b) match {
        case (EditMode(_, t1, b1, h1), EditMode(_, t2, b2, h2)) if t1 != t2 || b1 != b2 || h1 != h2 => ret |= GAME_BRANCH
        case _ =>
      }

      if (a.getIndicators != b.getIndicators) ret |= GAME_INDICATOR
      if (a.isHandicapped != b.isHandicapped) ret |= GAME_HANDICAP

      if (b.isJustMoved(a)) ret |= GAME_JUST_MOVED
      if (b.isNext(a)) ret |= GAME_NEXT_POS
      if (b.isPrevious(a)) ret |= GAME_PREV_POS
    }

    //
    // Cursor updates
    //
    if (oldModel.activeCursor != newModel.activeCursor) ret |= CURSOR_ACTIVE
    if (oldModel.selectedCursor != newModel.selectedCursor) ret |= CURSOR_SELECT
    if (newModel.flashedCursor.isDefined) ret |= CURSOR_FLASH

    //
    // Menu dialog updates
    //
    if (oldModel.menuDialogOpen != newModel.menuDialogOpen) ret |= MENU_DIALOG

    ret
  }
}