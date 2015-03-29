var User = require("mongoose").model("User");

exports.isUnDef = function checkUndefined(variable){
	return typeof variable == "undefined";
}

exports.createNewUser = function createUser(user, res, next){
	var response = {};
	user.save(function(err) {
		console.log(err);
		if(err){
			switch(err.code){
				case 11000:
					if(err.message.indexOf("username") != -1){
						response.error = "Username is already taken";
					}else if(err.message.indexOf("email") != -1){
						response.error = "Email is already taken";
					}
				break;
				default:
					response.error = "Something went wrong with your request";
				break
			}
		}
		else{
			response.success = "Successfully created user";
		}
		res.json(response);
	});
}