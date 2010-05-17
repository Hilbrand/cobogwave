/*
 * Copyright 2009-2010 Hilbrand Bouwkamp, hs@bouwkamp.com
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

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event fired when the wave mode changes. 
 */
public class ModeChangeEvent extends GwtEvent<ModeChangeEventHandler> {

  /**
   * Handler type.
   */
  private static Type<ModeChangeEventHandler> TYPE;

  /**
   * Gets the type associated with this event.
   *
   * @return returns the handler type
   */
  public static Type<ModeChangeEventHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<ModeChangeEventHandler>();
    }
    return TYPE;
  }

  static ModeChangeEvent fire(HasHandlers source, int mode) {
    // If no handlers exist, then type can be null.
    if (TYPE != null) {
      final ModeChangeEvent event = new ModeChangeEvent(mode);
      source.fireEvent(event);
      return event;
    }
    return null;
  }

  private int mode;

  private ModeChangeEvent(int mode) {
    this.mode = mode;
  }

  protected void dispatch(ModeChangeEventHandler handler) {
    handler.onUpdate(this);
  }

  @SuppressWarnings("unchecked")
  public Type<ModeChangeEventHandler> getAssociatedType() {
    return (Type) TYPE;
  }

  public int getMode() {
    return mode;
  }
}
