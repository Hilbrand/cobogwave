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
 * Event fired when wave state changes.  
 */
public class StateUpdateEvent extends GwtEvent<StateUpdateEventHandler> {

  /**
   * Handler type.
   */
  private static Type<StateUpdateEventHandler> TYPE;

  /**
   * Gets the type associated with this event.
   *
   * @return returns the handler type
   */
  public static Type<StateUpdateEventHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<StateUpdateEventHandler>();
    }
    return TYPE;
  }

  static StateUpdateEvent fire(HasHandlers source, WaveFeature wave,
      State delta) {
    // If no handlers exist, then type can be null.
    if (TYPE != null) {
      final StateUpdateEvent event = new StateUpdateEvent(wave, delta);

      source.fireEvent(event);
      return event;
    }
    return null;
  }

  private final WaveFeature wave;
  private final State delta;

  private StateUpdateEvent(WaveFeature wave, State delta) {
    this.wave = wave;
    this.delta = delta;
  }

  protected void dispatch(StateUpdateEventHandler handler) {
    handler.onUpdate(this);
  }

  @SuppressWarnings("unchecked")
  public Type<StateUpdateEventHandler> getAssociatedType() {
    return (Type) TYPE;
  }

  public State getDelta() {
    return delta;
  }

  public State getState() {
    return wave.getState();
  }

  public WaveFeature getWave() {
    return wave;
  }
}
