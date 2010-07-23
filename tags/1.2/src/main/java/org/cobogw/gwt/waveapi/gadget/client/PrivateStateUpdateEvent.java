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

public class PrivateStateUpdateEvent
    extends GwtEvent<PrivateStateUpdateEventHandler> {

  /**
   * Handler type.
   */
  private static Type<PrivateStateUpdateEventHandler> TYPE;

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

  static PrivateStateUpdateEvent fire(HasHandlers source, WaveFeature wave,
      PrivateState delta) {
    // If no handlers exist, then type can be null.
    if (TYPE != null) {
      final PrivateStateUpdateEvent event =
          new PrivateStateUpdateEvent(wave, delta);

      source.fireEvent(event);
      return event;
    }
    return null;
  }

  private final WaveFeature wave;
  private final PrivateState delta;

  private PrivateStateUpdateEvent(WaveFeature wave, PrivateState delta) {
    this.wave = wave;
    this.delta = delta;
  }

  protected void dispatch(PrivateStateUpdateEventHandler handler) {
    handler.onUpdate(this);
  }

  @SuppressWarnings("unchecked")
  public Type<PrivateStateUpdateEventHandler> getAssociatedType() {
    return (Type) TYPE;
  }

  public PrivateState getDelta() {
    return delta;
  }

  public PrivateState getPrivateState() {
    return wave.getPrivateState();
  }
  
  public WaveFeature getWave() {
    return wave;
  }
}
