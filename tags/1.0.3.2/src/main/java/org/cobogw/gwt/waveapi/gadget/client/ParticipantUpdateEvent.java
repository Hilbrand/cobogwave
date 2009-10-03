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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ParticipantUpdateEvent extends GwtEvent<ParticipantUpdateEventHandler> {

  /**
   * Handler type.
   */
  private static Type<ParticipantUpdateEventHandler> TYPE;
  private final WaveFeature wave;

  /**
   * Gets the type associated with this event.
   *
   * @return returns the handler type
   */
  public static Type<ParticipantUpdateEventHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<ParticipantUpdateEventHandler>();
    }
    return TYPE;
  }

  static ParticipantUpdateEvent fire(HasHandlers source, WaveFeature wave) {
    // If no handlers exist, then type can be null.
    if (TYPE != null) {
      final ParticipantUpdateEvent event = new ParticipantUpdateEvent(wave);
      source.fireEvent(event);
      return event;
    }
    return null;
  }

  private ParticipantUpdateEvent(WaveFeature wave) {
    this.wave = wave;
  }

  protected void dispatch(ParticipantUpdateEventHandler handler) {
    handler.onUpdate(this);
  }

  @SuppressWarnings("unchecked")
  public Type<ParticipantUpdateEventHandler> getAssociatedType() {
    return (Type) TYPE;
  }

  public JsArray<Participant> getParticipants() {
    return wave.getParticipants();
  }
}
