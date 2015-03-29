var db = require("./mongoose.js")();
var User = require("mongoose").model("User");

console.log(User);

exports.isUnDef = function checkUndefined(variable){
	return typeof variable == "undefined";
}

exports.createNewUser = function createUser(email, username, password, res){
	
}