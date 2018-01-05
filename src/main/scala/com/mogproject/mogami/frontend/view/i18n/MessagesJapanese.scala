package com.mogproject.mogami.frontend.view.i18n

import com.mogproject.mogami.State
import com.mogproject.mogami.util.Implicits._

/**
  * Japanese message definitions
  */
case object MessagesJapanese extends Messages {

  override val FLIP: String = "反転"

  override val COMMENT_CLEAR: String = "削除"
  override val COMMENT_CLEAR_TOOLTIP: String = "このコメントを削除"
  override val COMMENT_UPDATE: String = "更新"
  override val COMMENT_UPDATE_TOOLTIP: String = "このコメントを更新"

  override val MENU: String = "メニュー"

  override val SHARE: String = "シェア"
  override val RECORD_URL: String = "棋譜 URL"
  override val SNAPSHOT_URL: String = "局面 URL"
  override val SHORTEN_URL: String = "短縮 URL"
  override val SNAPSHOT_IMAGE: String = "局面画像生成"
  override val SNAPSHOT_SFEN_STRING: String = "局面 SFEN 文字列"
  override val NOTES_VIEW: String = "Notes ビュー"

  override val MANAGE: String = "ファイル管理"
  override val LOAD_FROM_FILE: String = "棋譜ファイルの読み込み"
  override val LOAD_FROM_TEXT: String = "テキストの読み込み"
  override val SAVE_TO_FILE_CLIPBOARD: String = "棋譜ファイル / クリップボードへ書き出し"

  override val ACTION: String = "特殊な指し手"
  override val RESIGN: String = "投了"

  override val ANALYZE: String = "解析"
  override val ANALYZE_FOR_CHECKMATE: String = "詰み解析"
  override val ANALYZE_CHECKMATE_TOOLTIP: String = "この局面の詰み手順を解析"

  override val ADD_CHECKMATE_MOVES: String = "手順を棋譜に追記"
  override val ADD_CHECKMATE_MOVES_TOOLTIP: String = "この手順を現在の棋譜に反映"

  override val CHECKMATE_MOVES_ADDED: String = "詰み手順が反映されました。"
  override val TIMEOUT: String = "時間制限"
  override val SEC: String = "秒"
  override val ANALYZING: String = "解析中"
  override val CHECKMATE_ANALYZE_TIMEOUT: String = "制限時間内に解析できませんでした。"
  override val NO_CHECKMATES: String = "詰みはありません。"
  override val CHECKMATE_FOUND: String = "詰み手順を発見しました"
  override val BRANCH: String = "分岐"

  override val COUNT_POINT: String = "点数計算"
  override val COUNT_POINT_LABEL: String = "点数計算 (持将棋判定用)"
  override val COUNT_POINT_TOOLTIP: String = "局面の点数を計算 (持将棋判定用)"

  override def COUNT_POINT_RESULT(point: Int, isKingInPromotionZone: Boolean, numPiecesInPromotionZone: Int): String = {
    Seq(
      s"点数: ${point}点",
      "敵陣3段目以内: " + isKingInPromotionZone.fold("玉 + ", "") + s"${numPiecesInPromotionZone}枚"
    ).mkString("\n")
  }

  override val RESET: String = "初期局面"
  override val INITIAL_STATE: Map[State, String] = Map(
    State.HIRATE -> "平手",
    State.MATING_BLACK -> "詰将棋 (先手)",
    State.MATING_WHITE -> "詰将棋 (後手)",
    State.HANDICAP_LANCE -> "香落ち",
    State.HANDICAP_BISHOP -> "角落ち",
    State.HANDICAP_ROOK -> "飛車落ち",
    State.HANDICAP_ROOK_LANCE -> "飛香落ち",
    State.HANDICAP_2_PIECE -> "二枚落ち",
    State.HANDICAP_3_PIECE -> "三枚落ち",
    State.HANDICAP_4_PIECE -> "四枚落ち",
    State.HANDICAP_5_PIECE -> "五枚落ち",
    State.HANDICAP_6_PIECE -> "六枚落ち",
    State.HANDICAP_8_PIECE -> "八枚落ち",
    State.HANDICAP_10_PIECE -> "十枚落ち",
    State.HANDICAP_THREE_PAWNS -> "歩三兵",
    State.HANDICAP_NAKED_KING -> "裸玉"
  )

  override val SETTINGS: String = "設定"
  override val BOARD_SIZE: String = "盤面サイズ"
  override val LAYOUT: String = "レイアウト"
  override val PIECE_GRAPHIC: String = "駒画像"
  override val DOUBLE_BOARD_MODE: String = "ダブル将棋盤モード"
  override val VISUAL_EFFECTS: String = "エフェクト"
  override val SOUND_EFFECTS: String = "サウンド"
  override val MESSAGE_LANG: String = "表示言語"
  override val RECORD_LANG: String = "棋譜言語"
  override val SETTINGS_INFO: String = "設定はお使いのブラウザに保存されます"

  override val HELP: String = "ヘルプ"
  override val ABOUT_THIS_SITE: String = "このサイトについて"


  override val MOVES: String = "棋譜"
  override val TRUNK: String = "本譜"
}
