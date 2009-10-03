/*
 * Copyright 2009 Hilbrand Bouwkamp, hs@bouwkamp.com
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
package org.cobogw.gwt.waveapi.gadget.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Participant class that describes participants on a wave.
 */
public class Participant extends JavaScriptObject {

  protected Participant() {
  }

  /**
   * Gets the unique identifier of this participant.
   *
   * @return The participant's id
   */
  public final native String getId() /*-{
    return this.getId();
  }-*/;

  /**
   * Gets the unique identifier of this participant.
   *
   * @return The participant's display name
   */
  public final native String getDisplayName() /*-{
    return this.getDisplayName();
  }-*/;

  /**
   * Gets the url of the thumbnail image for this participant.
   *
   * @return The participant's thumbnail image url.
   */
  public final native String getThumbnailUrl() /*-{
    return this.getThumbnailUrl();
  }-*/;
}
