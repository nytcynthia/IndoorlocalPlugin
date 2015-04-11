module.exports = {
  initialize: function() {
  	console.log("Wherami.js: initialize()");
    var successCallback = function(success) {
      console.log("log: " + success);
    }
  	var errorCallback = function(error) {
  		console.log("log: " + error);
  	}
    cordova.exec(successCallback,
                 errorCallback,
                 "WheramiPlugin",
                 "initialize",
                 []);
  },

  startCalculation:function(hello) {
    console.log("Wherami.js: start()");
    var successCallback = function(success) {
      console.log("log: " + success);
      hello(success);
    }
    var errorCallback = function(error) {
      console.log("log: " + error);
    }
    cordova.exec(successCallback,
                 errorCallback, // indoorLocationManager not initialized
                 "WheramiPlugin",
                 "start",
                 []);
  },

  shortestPath: function(start_area, start_x, start_y, end_area, end_x, end_y, callback) {
    // locationValues: float start_area, float start_x, float start_y, float end_area, float end_x, float end_y       
    console.log("Wherami.js: shortestPath");
    var locationValues = [];
    var startLocation = {
      "area": start_area,
      "x": start_x,
      "y": start_y
    }
    var endLocation = {
      "area": end_area,
      "x": end_x,
      "y": end_y
    }
    locationValues.push(startLocation);
    locationValues.push(endLocation);

    console.log(JSON.stringify(locationValues));

    var successCallback = function(success) {
      console.log("log: " + success);
      callback(success);
    }
    var errorCallback = function(error) {
      console.log("log: " + error);
    }
    // locationVales: startLocation.x
    cordova.exec(successCallback, 
                 errorCallback, 
                 "WheramiPlugin", 
                 "shortestPath", 
                 [locationValues]);
  }
};




/*
var exec = require('cordova/exec');
function Wherami() { 
	console.log("Wherami.js: is created");
}

WheramiPlugin.prototype.initialize = function(successCallback){
	console.log("Wherami.js: initialize()");
	var errorCallback = function(error) {
		console.log(error);
	};

	exec(successCallback, errorCallback, "WheramiPlugin", "initialize", []);
};

var WheramiPlugin = new WheramiPlugin(); 
module.exports = WheramiPlugin;
*/

// PayPalMobile.prototype.version = function(completionCallback) {
//   var failureCallback = function() {
//     console.log("Could not retrieve PayPal library version");
//   };

//   cordova.exec(completionCallback, failureCallback, "PayPalMobile", "version", []);
// };

// locationplugin.prototype.startPositioning = function(){ 
// 	console.log("locationplugin.js: startPositioning"); 
// 	exec(null, null ,"LocationPlugin","startPositioning",[]);
// }

// locationplugin.prototype.onButtonClick = function(){
// 	console.log("onButtonClick();");
// 	exec(null, null, "LocationPlugin", "onButtonClick", []);
// } 

// locationplugin.prototype.appendMessage = function(){
// 	console.log("locationplugin.js: appendMessage");
// 	exec(function(){}, function(){}, "locationplugin", "appendMessage",[]);
// }

// locationplugin.prototype.stopPositioning = function(){
// 	console.log("locationplugin.js: stopPositioning");
// 	exec(function(){}, function(){}, "locationplugin", "stopPositioning", []);
// }

// locationplugin.prototype.onBackPressed = function(){
// 	console.log("locationplugin.js: onBackPressed");
// 	exec(function(){}, function(){}, "locationplugin", "onBackPressed", []);
// }

