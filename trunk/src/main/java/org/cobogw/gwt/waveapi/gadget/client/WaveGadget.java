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

import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;

/**
 * A base class for writing a Google Wave Gadget.
 */
public abstract class WaveGadget<T extends UserPreferences> extends Gadget<T> 
  implements NeedsWave {

  private WaveFeature wave;

  protected WaveGadget() {
  }

  /**
   * Called by the gadget container.
   */
  public final void initializeFeature(WaveFeature feature) {
    wave = feature;
  }
  
  /**
   * Returns the wave object.
   * 
   * @return Wave object
   */
  public final WaveFeature getWave() {
    return wave;
  }
}
