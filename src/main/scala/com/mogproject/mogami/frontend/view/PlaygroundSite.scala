package com.mogproject.mogami.frontend.view

import com.mogproject.mogami.frontend.view.footer.FooterLike
import com.mogproject.mogami.util.Implicits._
import com.mogproject.mogami.frontend.view.nav.NavBar
import org.scalajs.dom.Element

import scalatags.JsDom.all._

/**
  *
  */
trait PlaygroundSite extends WebComponent {
  def isMobile: Boolean

  def navBar: NavBar

  def mainPane: MainPaneLike

  def footer: FooterLike

  override def element: Element = div(
    div(cls := "navbar", tag("nav")(cls := navBar.classNames, navBar.element)),
    div(cls := "container-fluid",
      isMobile.fold(Seq(position := position.fixed.v, width := "100%", padding := 0), ""),
      mainPane.element,
      footer.element
    )
  ).render

}
