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
package org.cobogw.gwt.waveapi.gadget.client;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PrivateStateUpdateEvent extends GwtEvent<PrivateStateUpdateEventHandler> {

  /**
   * Handler type.
   */
  private static Type<PrivateStateUpdateEventHandler> TYPE;
  private final WaveFeature wave;

  /**
   * Gets the type associated with this event.
   *
   * @return returns the handler type
   */
  public static Type<PrivateStateUpdateEventHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<PrivateStateUpdateEventHandler>();
    }
    return TYPE;
  }

  static PrivateStateUpdateEvent fire(HasHandlers source, WaveFeature wave) {
    // If no handlers exist, then type can be null.
    if (TYPE != null) {
      final PrivateStateUpdateEvent event = new PrivateStateUpdateEvent(wave);
      source.fireEvent(event);
      return event;
    }
    return null;
  }

  public PrivateStateUpdateEvent(WaveFeature wave) {
    this.wave = wave;
  }

  protected void dispatch(PrivateStateUpdateEventHandler handler) {
    handler.onUpdate(this);
  }

  @SuppressWarnings("unchecked")
  public Type<PrivateStateUpdateEventHandler> getAssociatedType() {
    return (Type) TYPE;
  }

  public PrivateState PrivateState() {
    return wave.getPrivateState();
  }
}
