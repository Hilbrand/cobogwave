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

/**
 * Identifiers for wave modes.
 */
public class Mode {
  /**
   * The blip containing the gadget is in an unknown mode. In this case, you
   * should not attempt to edit the blip.
   */
  public static final int UNKNOWN = nativeUNKNOWN();

  /**
   * The blip containing the gadget is in view, but not edit mode.
   */
  public static final int VIEW = nativeVIEW();

  /**
   * The blip containing the gadget is in edit mode.
   */
  public static final int EDIT = nativeEDIT();

  /**
   * The blip containing the gadget has changed since the last time it was
   * opened and the gadget should notify this change to the user.
   */
  public static final int DIFF_ON_OPEN = nativeDIFF_ON_OPEN();

  /**
   * The blip containing the gadget is in playback mode.
   */
  public static final int PLAYBACK = nativePLAYBACK();

  private native static int nativeUNKNOWN() /*-{
    return $win.wave.Mode.UNKNOWN;
  }-*/;

  private native static int nativeVIEW() /*-{
    return $win.wave.Mode.VIEW;
  }-*/;

  private native static int nativeEDIT() /*-{
    return $win.wave.Mode.EDIT;
  }-*/;

  private native static int nativeDIFF_ON_OPEN() /*-{
    return $win.wave.Mode.DIFF_ON_OPEN;
  }-*/;

  private native static int nativePLAYBACK() /*-{
    return $win.wave.Mode.PLAYBACK;
  }-*/;
}
