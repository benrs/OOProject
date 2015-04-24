var User    = require("mongoose").model("User");
var Token   = require("mongoose").model("Token");
var Picture = require("mongoose").model("Picture");
var db      = require("mongoose"); 

// Code courtesy of stack overflow
// It mimics php's range() function
Array.range= function(a, b, step){
    var A= [];
    if(typeof a== 'number'){
        A[0]= a;
        step= step || 1;
        while(a+step<= b){
            A[A.length]= a+= step;
        }
    }
    else{
        var s= 'abcdefghijklmnopqrstuvwxyz';
        if(a=== a.toUpperCase()){
            b=b.toUpperCase();
            s= s.toUpperCase();
        }
        s= s.substring(s.indexOf(a), s.indexOf(b)+ 1);
        A= s.split('');        
    }
    return A;
}

function shuffle(o){
    for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
    return o;
}
// End of Stack Overflow custom functions

// Simply checks if a variable is undefined
exports.isUnDef = function checkUndefined(variable){
	return typeof variable == "undefined";
}

// Creates a new user and handles appropriate error cases
exports.createNewUser = function createUser(user, res, next){
	var response = {};

	console.log("B");

	user.save(function(err) {
		console.log("C");

		if(err){
			switch(err.code){
				case 11000:
					if(err.message.indexOf("username") != -1){
						response.error = "Username is already taken";
						response.protocolCode = 200;
					}else if(err.message.indexOf("email") != -1){
						response.error = "Email is already taken";
						response.protocolCode = 200;
					}
				break;
				default:
					response.error = "Something went wrong with your request";
					response.protocolCode = 500;
				break
			}
		}else{
			response.success = "Successfully created user";
			response.protocolCode = 100;
		}
		res.json(response);
		res.end();
	});
}

// Generates a random string based off of length given
exports.generateRandomString = function randomString(length){
	var string = "";
	var array = Array.range(0, 9).concat(Array.range('a', 'z')).concat(Array.range('A', 'Z'));
	for(var i = 0; i < length; i++){
		array = shuffle(array);
		string += array[i%array.length];
	}

	return string;
}

// Generates a token, it will also regenerate a token
// if the current user already has a token or if
// a token with the same value exists
exports.generateToken = function newToken(token, res){
	var response = {};
	token.save(function(err){
		if(err){
			switch(err.code){
				case 11000:
					exports.regenerateToken(token, res);
				break;
				default:
					response.error = "Something went wrong with your request";
					response.protocolCode = 500;
					res.json(response);
					res.end();
				break
			}
		}else{
			response.token   = token.token;
			response.success = "Successfully logged in";
			response.protocolCode = 100;
			res.json(response);
			res.end();
		}
	});
}

// Deletes the users current token and generates a new one
exports.regenerateToken = function regenerateToken(token, res){
	var response = {};

	Token.remove({ UID: token.UID }).exec(function(err, result){
		if(err){
			response.error = "Something went wrong with your request";
			response.protocolCode = 500;
			res.json(response);
			res.end();
		}else{
			token.token = exports.generateRandomString(40);
			token.date  = new Date(new Date().getTime()+1000*60*10);
			exports.generateToken(token, res);
		}
	});
}

exports.validateToken = function validateToken(token, req, res, next){
	Token.find({ token: token }).exec(function(err, result){
		if(err){
			response.error = "Token is invalid or expired";
			response.protocolCode = 600;
			res.json(response);
			res.end();
		}else{
			var token = result[0];
			var expiry   = new Date(token.expiry).getTime()/1000;
			var rightNow = new Date().getTime()/1000;

			if(expiry > rightNow){
				req.token = token;
				exports.prolongToken(req, res, next);
			}else{
				response.error     = "Token is invalid or expired";
				response.protocolCode = 600;
				res.json(response);
				res.end();
			}
		}
	});
}

exports.prolongToken = function prolongToken(req, res, next){
	var newExpiry  = new Date(new Date().getTime()+1000*60*10);
	Token.update({ token: req.token, expiry: newExpiry }).exec(function(err, result){
		if(err){
			response.error = "Token is invalid or expired";
			response.protocolCode = 600;
			res.json(response);
			res.end();
		}else{
			next();
		}
	});
}

// Finds a picture by it's ID
// Possible errors:
// 			0 - Picture does not belong to the user
exports.findPicByID = function findPic(picID, currentUser, mustOwn, callback, res){
	var response = {};

	Picture.find({ _id: picID }).exec(function(err, result){
		if(err){
			response.error = "Something went wrong";
			response.protocolCode = 500;
			res.json(response);
			res.end();
		}else{
			if(result.length <= 0){
				callback(result, res);
			}else if(mustOwn && result[0].UID != currentUser){
				callback(0, res);
			}else{
				callback(result[0], res)
			}
		}
	});
}

exports.getOnePictureCB = function getOnePictureCB(pic, res){
	var response = {};

	if(pic == 0){
		response.error = "Picture does not belong to user";
		response.protocolCode = 400;
	}else if(pic.length <= 0){
		response.error = "Picture does not exist";
		response.protocolCode = 300;
	}else{
		response.encoded_pic = pic.encoded_pic;
		response.success = "Successfully retrieved picture";
		response.protocolCode = 100;
	}

	res.json(response);
	res.end();
}

exports.deletePicCB = function deletePicCB(pic, res){
	var response = {};

	if(typeof pic == "number" && pic == 0){
		response.error = "Picture does not belong to user";
		response.protocolCode = 400;
		res.json(response);
		res.end();
	}else if(pic.length <= 0){
		response.error = "Picture does not exist";
		response.protocolCode = 300;
		res.json(response);
		res.end();
	}else{
		// Delete the picture
		Picture.remove({ _id: pic._id }).exec(function(err, result){
			if(err){
				response.error = "Something went wrong";
				response.protocolCode = 500;
			}else{
				response.success = "Successfully deleted the picture";
				response.protocolCode = 100;
			}
			res.json(response);
			res.end();
		});
	}
}