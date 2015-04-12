module.exports = {
  initialize:function(hello) {
    console.log("Wherami.js: initialize()");
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
                 "initialize",
                 []);
  },

  shortestPath: function(start_area, start_x, start_y, end_area, end_x, end_y, callback) {
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
    
    cordova.exec(successCallback, 
                 errorCallback, 
                 "WheramiPlugin", 
                 "shortestPath", 
                 [locationValues]);
  }
};