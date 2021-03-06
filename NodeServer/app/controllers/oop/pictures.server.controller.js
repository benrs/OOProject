var Token   = require("mongoose").model("Token");
var Picture = require("mongoose").model("Picture");
var funcs   = require("../../../config/globalFunctions.js");

// This is a middle ware function which fetches the appropriate
// token. It will either pass the token forwards or let the user
// know that they passed an invalid token.
exports.validateToken = function(req, res, next){
	var body = req.body;
	var response = {};

	if(!funcs.isUnDef(body.token)){
		funcs.validateToken(body.token, req, res, next);
	}else{
		response.error = "Invalid use of api";
		response.protocolCode = 700;
		res.json(response);
		res.end();
	}
}

// This function fetchs all the pictures of a user.
exports.getPictures = function(req, res, next){
	var body = req.body;
	var response = {};

	var usersID  = req.token.UID;

	Picture.find({ UID: usersID }).exec(function(err, result){
		// TO DO: Build the json of array of [{name, id},...]
		response.pictures = result; 
		response.success  = "Successfully got pictures";
		response.protocolCode = 100;
		res.json(response);
		res.end();
	});
}

// Gets one picture from a specific user
exports.getOnePicture = function(req, res, next){
	var body = req.body;
	var response = {};

	if(!funcs.isUnDef(body.picture_ID)){
		var token    = req.token;
		var usersID  = token.UID;
		var picID    = body.picture_ID;

		funcs.findPicByID(picID, usersID, true, funcs.getOnePictureCB, res);
	}else{
		response.error = "Invalid use of api";
		response.protocolCode = 700;
		res.json(response);
		res.end();
	}
}

// Creates a new picture, user simply has to pass in an
// encoded picture
exports.createNewPic = function(req, res, next){
	var body = req.body;
	var response = {};

	if(!funcs.isUnDef(body.encoded_pic)){
		var token    = req.token;
		var usersID  = token.UID;

		var pic = new Picture();
		pic.UID = usersID;
		pic.encoded_pic = body.encoded_pic;

		pic.save(function(err, result) {
			if(err){
				response.error = "Something went wrong";
				response.protocolCode = 500;
			}else{
				response.picture_ID = result._id;
				response.success = "Successfully added the picture";
				response.protocolCode = 100;
			}
			res.json(response);
			res.end();
		});
	}else{
		response.error = "Invalid use of api";
		response.protocolCode = 700;
		res.json(response);
		res.end();
	}
}

exports.deletePic = function(req, res, next){
	var body = req.body;
	var response = {};

	if(!funcs.isUnDef(body.picture_ID)){
		var token    = req.token;
		var usersID  = token.UID;
		var picID    = body.picture_ID;

		funcs.findPicByID(picID, usersID, true, funcs.deletePicCB, res);
	}else{
		response.error = "Invalid use of api";
		response.protocolCode = 700;
		res.json(response);
		res.end();
	}
}