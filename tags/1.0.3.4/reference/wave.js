var a, wave = wave || {};
wave.Callback = function(b, c) {
  this.callback_ = b;
  this.context_ = c || null
};
wave.Callback.prototype.invoke = function() {
  this.callback_ && this.callback_.apply(this.context_, arguments)
};
wave.Mode = {UNKNOWN:0, VIEW:1, EDIT:2, DIFF_ON_OPEN:3, PLAYBACK:4};
wave.API_PARAM_ = "wave";
wave.ID_PARAM_ = "waveId";
wave.id_ = null;
wave.viewer_ = null;
wave.host_ = null;
wave.participants_ = [];
wave.participantMap_ = {};
wave.participantCallback_ = new wave.Callback(null);
wave.state_ = null;
wave.stateCallback_ = new wave.Callback(null);
wave.mode_ = null;
wave.modeCallback_ = new wave.Callback(null);
wave.inWaveContainer_ = false;wave.util = wave.util || {};
wave.util.SPACES_ = "                                                 ";
wave.util.toSpaces_ = function(b) {
  return wave.util.SPACES_.substring(0, b * 2)
};
wave.util.isArray_ = function(b) {
  try {
    return b && typeof b.length == "number"
  }catch(c) {
    return false
  }
};
wave.util.printJson = function(b, c, e) {
  if(!b || typeof b.valueOf() != "object") {
    if(typeof b == "string")return"'" + b + "'";
    else if(b instanceof Function)return"[function]";
    return"" + b
  }var d = [], f = wave.util.isArray_(b), h = f ? "[]" : "{}", g = c ? "\n" : "", j = c ? " " : "", k = 0;
  e = e || 1;
  c || (e = 0);
  d.push(h.charAt(0));
  for(var i in b) {
    var l = b[i];
    k++ > 0 && d.push(", ");
    if(!f) {
      d.push(g);
      d.push(wave.util.toSpaces_(e));
      d.push(i + ": ");
      d.push(j)
    }d.push(wave.util.printJson(l, c, e + 1))
  }if(!f) {
    d.push(g);
    d.push(wave.util.toSpaces_(e - 1))
  }d.push(h.charAt(1));
  return d.join("")
};wave.Participant = function(b, c, e) {
  this.id_ = b || "";
  this.displayName_ = c || "";
  this.thumbnailUrl_ = e || ""
};
wave.Participant.prototype.getId = function() {
  return this.id_
};
wave.Participant.prototype.getDisplayName = function() {
  return this.displayName_
};
wave.Participant.prototype.getThumbnailUrl = function() {
  return this.thumbnailUrl_
};
wave.Participant.fromJson_ = function(b) {
  var c = new wave.Participant;
  c.id_ = b.id;
  c.displayName_ = b.displayName;
  c.thumbnailUrl_ = b.thumbnailUrl;
  return c
};wave.State = function() {
  this.setState_(null)
};
a = wave.State.prototype;
a.get = function(b, c) {
  if(b in this.state_)return this.state_[b];
  return c === undefined ? null : c
};
a.getKeys = function() {
  var b = [];
  for(var c in this.state_)b.push(c);
  return b
};
a.submitDelta = function(b) {
  gadgets.rpc.call(null, "wave_gadget_state", null, b)
};
a.submitValue = function(b, c) {
  var e = {};
  e[b] = c;
  this.submitDelta(e)
};
a.reset = function() {
  var b = {};
  for(var c in this.state_)b[c] = null;
  this.submitDelta(b)
};
a.setState_ = function(b) {
  this.state_ = b || {}
};
a.toString = function() {
  return wave.util.printJson(this.state_, true)
};wave.checkWaveContainer_ = function() {
  var b = gadgets.util.getUrlParameters();
  wave.inWaveContainer_ = b.hasOwnProperty(wave.API_PARAM_) && b[wave.API_PARAM_];
  wave.id_ = b.hasOwnProperty(wave.ID_PARAM_) && b[wave.ID_PARAM_]
};
wave.isInWaveContainer = function() {
  return wave.inWaveContainer_
};
wave.receiveWaveParticipants_ = function(b) {
  wave.viewer_ = null;
  wave.host_ = null;
  wave.participants_ = [];
  wave.participantMap_ = {};
  var c = b.myId, e = b.authorId;
  b = b.participants;
  for(var d in b) {
    var f = wave.Participant.fromJson_(b[d]);
    if(d == c)wave.viewer_ = f;
    if(d == e)wave.host_ = f;
    wave.participants_.push(f);
    wave.participantMap_[d] = f
  }if(!wave.viewer_ && c) {
    f = new wave.Participant(c, c);
    wave.viewer_ = f;
    wave.participants_.push(f);
    wave.participantMap_[c] = f
  }wave.participantCallback_.invoke(wave.participants_)
};
wave.receiveState_ = function(b) {
  wave.state_ = wave.state_ || new wave.State;
  wave.state_.setState_(b);
  wave.stateCallback_.invoke(wave.state_)
};
wave.receiveMode_ = function(b) {
  wave.mode_ = b || {};
  wave.modeCallback_.invoke(wave.getMode())
};
wave.getViewer = function() {
  return wave.viewer_
};
wave.getHost = function() {
  return wave.host_
};
wave.getParticipants = function() {
  return wave.participants_
};
wave.getParticipantById = function(b) {
  return wave.participantMap_[b]
};
wave.getState = function() {
  return wave.state_
};
wave.getMode = function() {
  if(wave.mode_) {
    var b = wave.mode_["${playback}"], c = wave.mode_["${edit}"];
    if(b != null && c != null)return b == "1" ? wave.Mode.PLAYBACK : c == "1" ? wave.Mode.EDIT : wave.Mode.VIEW
  }return wave.Mode.UNKNOWN
};
wave.isPlayback = function() {
  var b = wave.getMode();
  return b == wave.Mode.PLAYBACK || b == wave.Mode.UNKNOWN
};
wave.setStateCallback = function(b, c) {
  wave.stateCallback_ = new wave.Callback(b, c);
  wave.state_ && wave.stateCallback_.invoke(wave.state_)
};
wave.setParticipantCallback = function(b, c) {
  wave.participantCallback_ = new wave.Callback(b, c);
  wave.participants_ && wave.participantCallback_.invoke(wave.participants_)
};
wave.setModeCallback = function(b, c) {
  wave.modeCallback_ = new wave.Callback(b, c);
  wave.mode_ && wave.modeCallback_.invoke(wave.getMode())
};
wave.getTime = function() {
  return(new Date).getTime()
};
wave.log = function(b) {
  gadgets.rpc.call(null, "wave_log", null, b || "")
};
wave.getWaveId = function() {
  return wave.id_
};
wave.internalInit_ = function() {
  wave.checkWaveContainer_();
  if(wave.isInWaveContainer()) {
    gadgets.rpc.register("wave_participants", wave.receiveWaveParticipants_);
    gadgets.rpc.register("wave_gadget_state", wave.receiveState_);
    gadgets.rpc.register("wave_gadget_mode", wave.receiveMode_);
    gadgets.rpc.call(null, "wave_enable", null, "0.1")
  }
};
(wave.init_ = function() {
  window.gadgets && gadgets.util.registerOnLoadHandler(function() {
    wave.internalInit_()
  })
})();var tamings___ = tamings___ || [], caja___, ___;
tamings___.push(function(b) {
  function c(e, d) {
    var f = {apply:___.markFuncFreeze(function(h, g) {
      return ___.callPub(e, "apply", [d, g])
    })};
    return new wave.Callback(f, ___.USELESS)
  }
  ___.grantRead(wave, "Mode");
  c.prototype = wave.Callback.prototype;
  wave.Callback.prototype.constructor = c;
  ___.markCtor(c, Object, "Callback");
  ___.primFreeze(c);
  ___.tamesTo(wave.Callback, c);
  ___.hanleGenericMethod(c.prototype, "invoke", function() {
    return ___.callPub(this.callback_, "apply", [___.tame(this.context_), Array.slice(arguments, 0)])
  });
  caja___.whitelistCtors([[wave, "Participant", Object], [wave, "State", Object]]);
  caja___.whitelistMeths([[wave.Participant, "getDisplayName"], [wave.Participant, "getId"], [wave.Participant, "getThumbnailUrl"], [wave.State, "get"], [wave.State, "getKeys"], [wave.State, "reset"], [wave.State, "submitDelta"], [wave.State, "submitValue"], [wave.State, "toString"]]);
  caja___.whitelistFuncs([[wave, "getHost"], [wave, "getMode"], [wave, "getParticipantById"], [wave, "getParticipants"], [wave, "getState"], [wave, "getTime"], [wave, "getViewer"], [wave, "isInWaveContainer"], [wave, "log"], [wave, "setModeCallback"], [wave, "setParticipantCallback"], [wave, "setStateCallback"], [wave.util, "printJson"]]);
  b.outers.wave = ___.tame(wave)
});
