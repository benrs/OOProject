var User   = require("mongoose").model("User");
var Token  = require("mongoose").model("Token");
var funcs  = require("../../../config/globalFunctions.js");
var bcrypt = require("bcrypt"); 

exports.create = function(req, res, next){
	var body = req.body;
	var response = {};

	console.log(req);
	console.log(body);

	if(!funcs.isUnDef(body.email) && !funcs.isUnDef(body.username) && !funcs.isUnDef(body.password)){
		var salt = bcrypt.genSaltSync(10);
		body.password = bcrypt.hashSync(body.password, salt);

		var user = new User(body);
		funcs.createNewUser(user, res, next);
	}else{
		response.error = "Invalid use of api";
		response.protocolCode = 700;
		res.json(response);
	}
}

exports.login = function(req, res){
	var body = req.body;
	var response = {};

	if(!funcs.isUnDef(body.username) && !funcs.isUnDef(body.password)){
		User.find({ username: body.username }).exec(function(err, result){
			result = result[0];
			var match = bcrypt.compareSync(body.password, result.password);
			if(match){
				var date = new Date(new Date().getTime()+1000*60*10);
				var tmp  = funcs.generateRandomString(40);

				var token = new Token({
					UID: result._id,
					token: tmp,
					expiry: date
				});

				funcs.generateToken(token, res);
			}else{
				response.error = "Invalid username or password";
				response.protocolCode = 800;
				res.json(response);
			}
		});
	}else{
		response.error = "Invalid use of api";
		response.protocolCode = 700;
		res.json(response);
	}
}
