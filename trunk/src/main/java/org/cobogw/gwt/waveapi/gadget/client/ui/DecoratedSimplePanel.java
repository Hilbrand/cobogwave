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
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * A Wave style decorated simple panel.
 *
 * Note: while the wave api method generates a header into this panel no access
 * is provided to set content into this header. Mainly because the api method
 * itself doesn't provide access and thus it's not clear if the header is
 * supported in the future.
 *
 * <h3>CSS Style Rules</h3>
 *
 * <ul>
 * <li>.wavebox { the outside of the panel }</li>
 * <li>.wavebox .waveboxhead { the head of the panel }</li>
 * <li>.wavebox .waveboxbody { the body of the panel }</li>
 * </ul>
 *
 * <h3>Generated wrapped HTML</h3>
 *
 * The Wave provided make frame wraps around the given div element, which
 * results in the following HTML code:
 *
 * <pre>
 *   &lt;div class="wavebox">
 *     &lt;div class="waveboxhead">
 *       &lt;span>&nbsp;&lt;/span>
 *     &lt;/div>
 *     &lt;div class="waveboxbody"> body &lt;/div>
 *   &lt;/div>
 * </pre>
 */
public class DecoratedSimplePanel extends SimplePanel {

  public DecoratedSimplePanel() {
    super();
    makeFrame(getElement());
  }

  @Override
  protected com.google.gwt.user.client.Element getContainerElement() {
    return getElement().getFirstChildElement().getNextSiblingElement().cast();
  }

  private native void makeFrame(Element e) /*-{
    $wnd.wave.ui.makeFrame(e);
  }-*/;
}
