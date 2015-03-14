This project is not maintained anymore.

--- 

The [Google Wave Gadget API](http://code.google.com/apis/wave/extensions/gadgets/reference.html) is an extension to the Gadget API. This project is created to wrap the Wave Gadget API in Java classes to be able to program Gadgets with GWT. The following document provided by Google can help with [Debugging Wave Gadgets](http://docs.google.com/View?id=dggjrx3s_166cfhms9gd).

### How to use ###

Add the `WaveGadget.gwt.xml` to your project `gwt.xml`:
```
   <inherits name='org.cobogw.gwt.waveapi.gadget.WaveGadget' />
```

Create a class that extends `WaveGadget`.

```
@Gadget.ModulePrefs(title = "Hello World")
public class HelloWorld extends WaveGadget<UserPreferences> {

  public HelloWorld() {
  }

  protected void init(UserPreferences preferences) {
    getWave().addParticipantUpdateEventHandler(new ParticipantUpdateEventHandler() {
      public void onUpdate(ParticipantUpdateEvent event) {
        //handle participants added, also called when wave with gadget is opened.
      }
    });
    getWave().addStateUpdateEventHandler(new StateUpdateEventHandler() {
      public void onUpdate(StateUpdateEvent event) {
        //handle state changes.
      }
    });
    ...init your widgets...
    RootPanel.get().add(<you widget>);
  }
}
```

There are 4 events `StatusUpdateEvent`, `PrivateStatusUpdateEvent`, `ParticipantUpdateEvent` and `ModeChangeEvent`. To act on changes register a handler for one of those events with the methods on the `WaveFeature` class and the events will be fired when state or participants change.

**If you are not using maven you need to add the GWT gadgets api library manually to your project. Download the library from the http://code.google.com/p/gwt-google-apis/ page and add the `gwt-gadgets-1.2.jar` , `gwt-gadgets-1.1.jar` or `gwt-gadgets-1.0.3.jar` file to your project.**

### GWT-RPC in Wave Gadget ###
With the release version 1.2 of the iGoogle GWT Gadget api library it's possible to use GWT-RPC within a Wave Gadget. To make GWT-RPC work within a Wave Gadget see the Gadgets FAQ: http://code.google.com/p/gwt-google-apis/wiki/GadgetsFAQ#How_can_I_get_GWT_RPC_to_work_in_a_Gadget?

### Wave styled widgets ###

Since version 1.1.2 the wave styled widgets are added. These are provided via the wave api: Button, DialogBox and DecoratedSimplePanel. **However, the current implementation in wave of those widgets and how the wrapper are broken, and it's not recommended to use the widgets at this moment (July 23, 2010)**

![button.png](https://raw.githubusercontent.com/Hilbrand/cobogwave/master/src/main/resources/org/cobogw/gwt/waveapi/gadget/client/ui/doc-files/button.png)

![dialogbox.png](https://raw.githubusercontent.com/Hilbrand/cobogwave/master/src/main/resources/org/cobogw/gwt/waveapi/gadget/client/ui/doc-files/dialogbox.png)

![decoratedsimplepanel.png](https://raw.githubusercontent.com/Hilbrand/cobogwave/master/src/main/resources/org/cobogw/gwt/waveapi/gadget/client/ui/doc-files/decoratedsimplepanel.png)

### To wave or to wave-preview? ###
The first version 1.0.3 made use of the FeatureName "wave-preview". By using that name the wave-preview script is loaded by the gadget container. However, there are several problems reported with this version, e.g. not loading in some browsers and returning false in wave.isInWaveContainer(). Therefor in version 1.0.3.1 this was changed to FeatureName "wave". Using this name it is required to load the wave.js script by the gadget itself, but that is taken care of by the library via the WaveGadget.gwt.xml file. Thanks goes to Vicente J. Ruiz Jurado for providing the solution.

### Maven2 Support ###

The libraries are also supported for maven2. The repository is located at http://cobogwave.googlecode.com/svn/maven2/. Add the repository to your pom.xml file:
```
  <repository>
    <id>cobogwave</id>
    <name>Cobogwave repository at googlecode</name>
    <url>http://cobogwave.googlecode.com/svn/maven2/</url>
  </repository>
```
And add the following dependency:
```
  <dependency>
    <groupId>org.cobogw.gwt.wave-api</groupId>
    <artifactId>cobogwave-gadget</artifactId>
    <version>1.2</version>
  </dependency>
```
Using the library via maven will use version 1.0.3 of the google-api library.
