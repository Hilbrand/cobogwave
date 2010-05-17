/*
 * Copyright 2010 Hilbrand Bouwkamp, hs@bouwkamp.com
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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * A Wave styled button, build around an anchor element.
 *
 * <h3>CSS Style Rules</h3>
 *
 * <ul>
 * <li>.wavebutton { the anchor }</li>
 * </ul>
 *
 * <h3>Generated wrapped HTML</h3>
 *
 * The Wave provided make button method wraps around the given anchor element,
 * which results in the following HTML code:
 *
 * <pre>
 *   &lt;a class="wavebutton">
 *     &lt;span> b.innerHTML &lt;/span>
 *   &lt;/a>
 * </pre>
 */
public class Button extends com.google.gwt.user.client.ui.Anchor {

  public Button() {
    super();
    makeButton(getElement());
    getElement().getFirstChildElement().getStyle().setCursor(Cursor.POINTER);
  }

  /**
   * Creates a button with the given HTML caption.
   *
   * @param html the HTML caption
   */
  public Button(String html) {
    this();
    setHTML(html);
  }

  /**
   * Creates a button with the given HTML caption and click listener.
   *
   * @param html the HTML caption
   * @param handler the click handler
   */
  public Button(String html, ClickHandler handler) {
    this(html);
    addClickHandler(handler);
  }

  @Override
  public String getHTML() {
    return getElement().getFirstChildElement().getInnerHTML();
  }

  @Override
  public String getText() {
    return getElement().getFirstChildElement().getInnerText();
  }

  @Override
  public void setHTML(String html) {
    getElement().getFirstChildElement().setInnerHTML(html);
  }

  @Override
  public void setText(String text) {
    getElement().getFirstChildElement().setInnerText(text);
  }

  private native void makeButton(Element e) /*-{
    $wnd.wave.ui.makeButton(e);
  }-*/;
}
