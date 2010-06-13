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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.gadgets.client.GadgetFeature;

/**
 * Wave Feature class.
 */
public class WaveFeature implements GadgetFeature {

  public class Mode {
    public static final int UNKNOWN = 0;
    public static final int VIEW = 1;
    public static final int EDIT = 2;
    public static final int DIFF_ON_OPEN = 3;
    public static final int PLAYBACK = 4;
  }

  private static HandlerManager pHandler;
  private static HasHandlers pHasHandlers;
  private static HandlerManager sHandler;
  private static HasHandlers sHasHandlers;
  private static WaveFeature wave;

  private WaveFeature(){
  }

  /**
   * Adds a {@link ParticipantUpdateEvent} handler.
   *
   * @param handler
   *          the handler
   * @return the registration for the event
   */

  public HandlerRegistration addParticipantUpdateEventHandler(
      ParticipantUpdateEventHandler handler) {
    if (wave == null) {
      wave = this;
    }
    if (pHandler == null) {
      pHasHandlers = new HasHandlers() {
        public void fireEvent(GwtEvent<?> event) {
          pHandler.fireEvent(event);
        }
      };
      pHandler = new HandlerManager(pHasHandlers);
      registerParticipantUpdateCallback();
    }
    return pHandler.addHandler(ParticipantUpdateEvent.getType(), handler);
  }

  /**
   * Adds a {@link ParticipantUpdateEvent} handler.
   *
   * @param handler
   *          the handler
   * @return the registration for the event
   */

  public HandlerRegistration addStateUpdateEventHandler(
      StateUpdateEventHandler handler) {
    if (wave == null) {
      wave = this;
    }
    if (sHandler == null) {
      sHasHandlers = new HasHandlers() {
        public void fireEvent(GwtEvent<?> event) {
          sHandler.fireEvent(event);
        }
      };
      sHandler = new HandlerManager(sHasHandlers);
      registerStateUpdateCallback();
    }
    return sHandler.addHandler(StateUpdateEvent.getType(), handler);
  }

  /**
   * Get host, participant who added this gadget to the blip. Note that the host
   * may no longer be in the participant list.
   *
   * @return host (null if not known)
   */
  public native Participant getHost() /*-{
    return $wnd.wave.getViewer();
  }-*/;

  /**
   * Returns the mode the wave is in. Match with states in {@link Mode} class.
   *
   * @return
   */
  public native int getMode() /*-{
    return $wnd.wave.getMode();
  }-*/;

  /**
   * Returns a Participant with the given id.
   *
   * @param The
   *          id of the participant to retrieve
   * @return The participant with the given id
   */
  public native Participant getParticipantById(String id) /*-{
    return $wnd.wave.getParticipantById(id);
  }-*/;

  /**
   * Returns a list of participants on the Wave.
   *
   * @return Participant list.
   */
  public native JsArray<Participant> getParticipants() /*-{
    return $wnd.wave.getParticipants();
  }-*/;

  /**
   * @return Returns a list of participants on the Wave
   */
  public native State getState() /*-{
    return $wnd.wave.getState();
  }-*/;

  /**
   * Retrieves "gadget time" which is either the playback frame time in the
   * playback mode or the current time otherwise.
   *
   * @return The gadget time
   */
  public long getTime() {
    return (long) getTime0();
  }

  /**
   * Get the participant whose client renders this gadget.
   *
   * @return the viewer (null if not known)
   */
  public native Participant getViewer() /*-{
    return $wnd.wave.getViewer();
  }-*/;

  /**
   * Indicates whether the gadget runs inside a wave container.
   *
   * @return whether the gadget runs inside a wave container
   */
  public native boolean isInWaveContainer() /*-{
    return $wnd.wave && $wnd.wave.isInWaveContainer();
  }-*/;

  /**
   * Returns the playback state of the wave/wavelet/gadget.
   *
   * @return whether the gadget should be in the playback state
   */
  public native boolean isPlayback() /*-{
    return $wnd.wave.isPlayback();
  }-*/;

  public native void log(String log) /*-{
    return $wnd.wave.log(log);
  }-*/;

  /**
   * Outputs JSON objects in text format. Optionally pretty print.
   *
   * @param obj The object to print.
   * @param opt_tabs Number of tabs to start indent
   * @return
   */
  public native String printJson(JavaScriptObject obj, int opt_tabs)  /*-{
    return $wnd.util.printJson(obj, opt_pretty, opt_tabs);
  }-*/;

  /**
   * Outputs JSON objects in text format. Optionally pretty print.
   *
   * @param obj The object to print.
   * @param opt_pretty If true
   * @param opt_tabs Number of tabs to start indent
   * @return Outputs JSON objects in text format
   */
  public native String printJson(JavaScriptObject obj, boolean opt_pretty, int opt_tabs)  /*-{
    return $wnd.util.printJson(obj, opt_pretty, opt_tabs);
  }-*/;

  /**
   * Sets the participant update callback. If the participant information is
   * already received, the callback is invoked immediately to report the current
   * participant information. Only one callback can be defined. Consecutive
   * calls would remove old callback and set the new one.
   */
  @SuppressWarnings("unused")
  private native void setParticipantCallback(String callback,
      JavaScriptObject opt_context) /*-{
    $wnd.wave.setParticipantCallback(callback, opt_context);
  }-*/;

  /**
   * Sets the gadget state update callback. If the state is already received
   * from the container, the callback is invoked immediately to report the
   * current gadget state. Only one callback can be defined. Consecutive calls
   * would remove the old callback and set the new one.
   */
  @SuppressWarnings("unused")
  private native void setStateCallback(String callback,
      JavaScriptObject opt_context) /*-{
    $wnd.wave.setStateCallback(callback, opt_context);
  }-*/;

  /**
   * Register the stateUpdated method to be called when the state changes.
   */
  private native void registerParticipantUpdateCallback() /*-{
    $wnd.wave.setParticipantCallback(@org.cobogw.gwt.waveapi.gadget.client.WaveFeature::participantUpdateEvent());
  }-*/;

  /**
   * Register the stateUpdated method to be called when the state changes.
   */
  private native void registerStateUpdateCallback() /*-{
    $wnd.wave.setStateCallback(@org.cobogw.gwt.waveapi.gadget.client.WaveFeature::stateUpdateEvent());
  }-*/;

  /**
   * This method is called from the wave JavaScript library on Participant
   * changes.
   */
  @SuppressWarnings("unused")
  private static void participantUpdateEvent() {
    ParticipantUpdateEvent.fire(pHasHandlers, wave);
  }

  /**
   * This method is called from the wave JavaScript library on State changes.
   */
  @SuppressWarnings("unused")
  private static void stateUpdateEvent() {
    StateUpdateEvent.fire(sHasHandlers, wave);
  }

  private native double getTime0() /*-{
    return $wnd.wave.getTime();
  }-*/;
}