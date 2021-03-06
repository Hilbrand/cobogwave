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

import com.google.gwt.gadgets.client.GadgetFeature.FeatureName;

/**
 * Indicates that a Gadget requires the Google Wave feature.
 * http://wave-api.appspot.com/public/wave.js
 */
@FeatureName("wave")
public interface NeedsWave {
  /**
   * Entry point that gets called back to handle wave feature initialization.
   * 
   * Don't access the participant or state objects this method. the method is
   * called when the gadget loads. You use this function to confirm that the
   * wave is live and to register your callbacks. You should not attempt to
   * access the participant or state objects.
   * 
   * @param feature
   *          an instance of the feature to use to invoke feature specific
   *          methods.
   */
  void initializeFeature(WaveFeature feature);
}