/*
 * Copyright 2009 Google Inc.
 * Copyright 2010 Hilbrand Bouwkamp, hs@bouwkamp.com
 *
 * Adapted from the GWT DialogBox class.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cobogw.gwt.waveapi.gadget.client.ui;

import java.util.Iterator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.DialogBox.Caption;

/**
 * A Wave style decorated DialogBox.
 *
 * A form of popup that has a caption area at the top and can be dragged by the
 * user. Unlike a PopupPanel, calls to {@link #setWidth(String)} and
 * {@link #setHeight(String)} will set the width and height of the dialog box
 * itself, even if a widget has not been added as yet.
 *
 * <p>
 * <img class='gallery' src='doc-files/DialogBox.png'/>
 * </p>
 * <h3>CSS Style Rules</h3>
 *
 * <ul>
 * <li>.wavedialog { the outside of the dialog }</li>
 * <li>.wavedialog .wavedialoghead { the caption }</li>
 * <li>.wavedialog .wavedialogbody { the wrapper around the content }</li>
 * </ul>
 *
 * <h3>Generated wrapped HTML</h3>
 *
 * The Wave provided make dialog box wraps around the given div element,
 * which results in the following HTML code:
 *
 * <pre>
 *   &lt;div class="wavedialog">
 *     &lt;div class="wavedialoghead">
 *       &lt;span> header &lt;/span>
 *     &lt;/div>
 *     &lt;div class="wavedialogbody"> body &lt;/div>
 *   &lt;/div>
 * </pre>
 */
public class DialogBox extends PopupPanel implements HasHTML {

  private static class CaptionImpl extends HTML implements Caption {
    public CaptionImpl(Element e) {
      super(e);
    }

    @Override
    protected void onAttach() {
      super.onAttach();
    }

    @Override
    protected void onDetach() {
      super.onDetach();
    }
  }

  private static class BodyImpl extends SimplePanel {
    public BodyImpl(Element e) {
      super(e);
    }

    @Override
    protected void onAttach() {
      super.onAttach();
    }

    @Override
    protected void onDetach() {
      super.onDetach();
    }
  }

  private class MouseHandler implements MouseDownHandler, MouseUpHandler,
      MouseOutHandler, MouseOverHandler, MouseMoveHandler {

    public void onMouseDown(MouseDownEvent event) {
      beginDragging(event);
    }

    public void onMouseMove(MouseMoveEvent event) {
      continueDragging(event);
    }

    public void onMouseOut(MouseOutEvent event) {
    }

    public void onMouseOver(MouseOverEvent event) {
    }

    public void onMouseUp(MouseUpEvent event) {
      endDragging(event);
    }
  }

  private final CaptionImpl caption;
  private final BodyImpl body;
  private boolean dragging;
  private int dragStartX, dragStartY;
  private int windowWidth;
  private int clientLeft;
  private int clientTop;

  private HandlerRegistration resizeHandlerRegistration;

  /**
   * Creates an empty dialog box. It should not be shown until its child widget
   * has been added using {@link #add(Widget)}.
   */
  public DialogBox() {
    this(false);
  }

  /**
   * Creates an empty dialog box specifying its "auto-hide" property. It should
   * not be shown until its child widget has been added using
   * {@link #add(Widget)}.
   *
   * @param autoHide
   *          <code>true</code> if the dialog should be automatically hidden
   *          when the user clicks outside of it
   */
  public DialogBox(boolean autoHide) {
    this(autoHide, true);
  }

  /**
   * Creates an empty dialog box specifying its "auto-hide" property. It should
   * not be shown until its child widget has been added using
   * {@link #add(Widget)}.
   *
   * @param autoHide
   *          <code>true</code> if the dialog should be automatically hidden
   *          when the user clicks outside of it
   * @param modal
   *          <code>true</code> if keyboard and mouse events for widgets not
   *          contained by the dialog should be ignored
   */
  public DialogBox(boolean autoHide, boolean modal) {
    super(autoHide, modal);
    makeDialog(getElement(), "");
    caption = new CaptionImpl(getElement().getFirstChildElement()
        .getFirstChildElement());
    body = new BodyImpl(getElement().getFirstChildElement()
        .getNextSiblingElement());

    windowWidth = Window.getClientWidth();
    clientLeft = Document.get().getBodyOffsetLeft();
    clientTop = Document.get().getBodyOffsetTop();

    MouseHandler mouseHandler = new MouseHandler();
    addDomHandler(mouseHandler, MouseDownEvent.getType());
    addDomHandler(mouseHandler, MouseUpEvent.getType());
    addDomHandler(mouseHandler, MouseMoveEvent.getType());
    addDomHandler(mouseHandler, MouseOverEvent.getType());
    addDomHandler(mouseHandler, MouseOutEvent.getType());
  }

  @Override
  public void clear() {
    body.clear();
  }

  @Override
  public Widget getWidget() {
    return body.getWidget();
  }

  @Override
  public Iterator<Widget> iterator() {
    return body.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return body.remove(w);
  }

  /**
   * Provides access to the dialog's caption.
   *
   * This method is final because the Caption interface will expand. Therefore
   * it is highly likely that subclasses which implemented this method would end
   * up breaking.
   *
   * @return the logical caption for this dialog box
   */
  public final Caption getCaption() {
    return caption;
  }

  public String getHTML() {
    return caption.getHTML();
  }

  public String getText() {
    return caption.getText();
  }

  @Override
  public void hide() {
    if (resizeHandlerRegistration != null) {
      resizeHandlerRegistration.removeHandler();
      resizeHandlerRegistration = null;
    }
    super.hide();
  }

  @Override
  public void onBrowserEvent(Event event) {
    // If we're not yet dragging, only trigger mouse events if the event occurs
    // in the caption wrapper
    switch (event.getTypeInt()) {
    case Event.ONMOUSEDOWN:
    case Event.ONMOUSEUP:
    case Event.ONMOUSEMOVE:
    case Event.ONMOUSEOVER:
    case Event.ONMOUSEOUT:
      if (!dragging && !isCaptionEvent(event)) {
        return;
      }
    }

    if (event.getTypeInt() == Event.ONMOUSEUP && isCaptionEvent(event) &&
        insideHideButton(event)) {
      hide();
    }
    super.onBrowserEvent(event);
  }

  /**
   * Sets the html string inside the caption.
   *
   * Use {@link #setWidget(Widget)} to set the contents inside the
   * {@link DialogBox}.
   *
   * @param html
   *          the object's new HTML
   */
  public void setHTML(String html) {
    caption.setHTML(html);
  }

  /**
   * Sets the text inside the caption.
   *
   * Use {@link #setWidget(Widget)} to set the contents inside the
   * {@link DialogBox}.
   *
   * @param text
   *          the object's new text
   */
  public void setText(String text) {
    caption.setText(text);
  }

  @Override
  public void show() {
    if (resizeHandlerRegistration == null) {
      resizeHandlerRegistration = Window.addResizeHandler(new ResizeHandler() {
        public void onResize(ResizeEvent event) {
          windowWidth = event.getWidth();
        }
      });
    }
    super.show();
  }

  /**
   * Called on mouse down in the caption area, begins the dragging loop by
   * turning on event capture.
   *
   * @see DOM#setCapture
   * @see #continueDragging
   * @param event
   *          the mouse down event that triggered dragging
   */
  protected void beginDragging(MouseDownEvent event) {
    dragging = true;
    DOM.setCapture(getElement());
    dragStartX = event.getX();
    dragStartY = event.getY();
  }

  /**
   * Called on mouse move in the caption area, continues dragging if it was
   * started by {@link #beginDragging}.
   *
   * @see #beginDragging
   * @see #endDragging
   * @param event
   *          the mouse move event that continues dragging
   */
  protected void continueDragging(MouseMoveEvent event) {
    if (dragging) {
      int absX = event.getX() + getAbsoluteLeft();
      int absY = event.getY() + getAbsoluteTop();

      // if the mouse is off the screen to the left, right, or top, don't
      // move the dialog box. This would let users lose dialog boxes, which
      // would be bad for modal popups.
      if (absX < clientLeft || absX >= windowWidth || absY < clientTop) {
        return;
      }

      setPopupPosition(absX - dragStartX, absY - dragStartY);
    }
  }

  @Override
  protected void doAttachChildren() {
    try {
      body.onAttach();
    } finally {
      // See comment in doDetachChildren for an explanation of this call
      caption.onAttach();
    }
  }

  @Override
  protected void doDetachChildren() {
    try {
      body.onDetach();
    } finally {
      // We need to detach the caption specifically because it is not part of
      // the
      // iterator of Widgets that the {@link SimplePanel} super class returns.
      // This is similar to a {@link ComplexPanel}, but we do not want to expose
      // the caption widget, as its just an internal implementation.
      caption.onDetach();
    }
  }

  /**
   * Called on mouse up in the caption area, ends dragging by ending event
   * capture.
   *
   * @param event
   *          the mouse up event that ended dragging
   *
   * @see DOM#releaseCapture
   * @see #beginDragging
   * @see #endDragging
   */
  protected void endDragging(MouseUpEvent event) {
    dragging = false;
    DOM.releaseCapture(getElement());
  }

  @Override
  protected com.google.gwt.user.client.Element getContainerElement() {
    // body is null in super constructor, when makeDialog net yet called.
    return body == null ? getElement() : body.getElement();
  }

  /**
   * <b>Affected Elements:</b>
   * <ul>
   * <li>-caption = text at the top of the {@link DialogBox}.</li>
   * <li>-content = the container around the content.</li>
   * </ul>
   *
   * @see UIObject#onEnsureDebugId(String)
   */
  @Override
  protected void onEnsureDebugId(String baseID) {
    super.onEnsureDebugId(baseID);
    caption.ensureDebugId(baseID + "-caption");
    ensureDebugId(body.getElement(), baseID, "content");
  }

  @Override
  protected void onPreviewNativeEvent(NativePreviewEvent event) {
    // We need to preventDefault() on mouseDown events (outside of the
    // DialogBox content) to keep text from being selected when it
    // is dragged.
    NativeEvent nativeEvent = event.getNativeEvent();

    if (!event.isCanceled() && (event.getTypeInt() == Event.ONMOUSEDOWN)
        && isCaptionEvent(nativeEvent)) {
      nativeEvent.preventDefault();
    }

    super.onPreviewNativeEvent(event);
  }

  private boolean insideHideButton(Event event) {
    final int right = getElement().getAbsoluteLeft() + getElement().getOffsetWidth();
    final int top = getElement().getAbsoluteTop();
    final int x = event.getClientX();
    final int y = event.getClientY();

    return x >= right - 27 && x <= right - 12 && y >= top + 4 && y <= top + 19;
  }

  private boolean isCaptionEvent(NativeEvent event) {
    EventTarget target = event.getEventTarget();
    if (Element.is(target)) {
      return caption.getElement().getParentElement().isOrHasChild(Element.as(target));
    }
    return false;
  }

  private native void makeDialog(Element e, String header) /*-{
     $wnd.wave.ui.makeDialog(e, header);
  }-*/;
}
