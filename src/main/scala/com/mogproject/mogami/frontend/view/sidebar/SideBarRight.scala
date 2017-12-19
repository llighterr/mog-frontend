package com.mogproject.mogami.frontend.view.sidebar

import com.mogproject.mogami.frontend.view.menu.{AccordionMenu, MenuPane}
import com.mogproject.mogami.frontend.view.{Observer, WebComponent}
import org.scalajs.dom.html.{Div, Heading}

import scalatags.JsDom.all._

/**
  *
  */
class SideBarRight extends SideBarLike with Observer[AccordionMenu] {

  override val EXPANDED_WIDTH: Int = SideBarRight.EXPANDED_WIDTH

  lazy val menuPane = MenuPane(false)

//  override def childComponents: Seq[WebComponent] = Seq(menuPane)

  override protected val outputClass: String = "sidebar-right"

  override lazy val titleExpanded: Heading = h4(
    cls := "sidebar-heading",
    onclick := { () => collapseSideBar() },
    span(cls := "glyphicon glyphicon-minus"),
    span(paddingLeft := 14.px, "Menu")
  ).render

  override lazy val titleCollapsed: Heading = h4(
    cls := "sidebar-heading",
    display := display.none.v,
    onclick := { () => expandSideBar() },
    span(cls := "glyphicon glyphicon-plus")
  ).render

  override def content: Div = div(
    titleExpanded,
    titleCollapsed,
    menuPane.element
  ).render

  override def collapseSideBar(): Unit = if (!isCollapsed) {
    super.collapseSideBar()
    menuPane.collapseMenu()
  }

  override def expandSideBar(): Unit = if (isCollapsed) {
    super.expandSideBar()
    menuPane.expandMenu()
  }

//  override def initialize(): Unit = {
//    super.initialize()
//    menuPane.sections.foreach(_.accordions.foreach(_.addObserver(this)))
//  }

  override def handleUpdate(subject: AccordionMenu): Unit = {
    expandSideBar()
  }
}

object SideBarRight {
  val EXPANDED_WIDTH: Int = 460
}
