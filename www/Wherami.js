module.exports = {
  initialize: function(successCallback) {
  	console.log("Wherami.js: is created");
    cordova.exec(successCallback,
                 null, // No failure callback
                 "WheramiPlugin",
                 "initialize",
                 []);
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

