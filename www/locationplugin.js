var exec = require('cordova/exec');
function locationplugin() { 
	console.log("locationplugin.js: is created");
}
locationplugin.prototype.startPositioning = function(){ 
	console.log("locationplugin.js: startPositioning"); 
	exec(null, null ,"LocationPlugin","startPositioning",[]);
}

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

var locationplugin = new locationplugin(); 
module.exports = locationplugin;