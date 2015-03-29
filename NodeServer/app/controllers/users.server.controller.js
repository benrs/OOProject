var db    = require("../../config/mongoose.js")();
var funcs = require("../../config/globalFunctions.js"); 

exports.create = function(req, res, next){
	var body = req.body;
	var response = {};

	if(!funcs.isUnDef(body.email) && !funcs.isUnDef(body.username) && !funcs.isUnDef(body.password)){
		funcs.createNewUser(body.email, body.username, body.password, res);
	}else{
		response.error = "Invalid use of api";
		res.json(response);
	}
}

exports.list = function(req, res, next){
}

exports.userByID = function(req, res, next, id){
	// This is a middleware function that will
	// pass the user onto the next function
};

exports.read = function(req, res){
};

exports.update = function(req, res, next){
};

exports.delete = function(req, res, next){
};