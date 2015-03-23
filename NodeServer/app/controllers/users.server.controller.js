var User = require('mongoose').model('User');

exports.create = function(req, res, next){
	var user = new User(req.body);

	var response = {};

	user.save(function(err){
		if(err){
			switch(err.code){
				case 11000:
				case 11001:
					response.error = 'Email is already taken';
					break;
				default:
					response.error = 'Something went wrong';
			}
		}else{
			response.error = "";
			response.success = "Successfully created user";
		}

		res.json(response);
	});
}

exports.list = function(req, res, next){
	var response = {};

	User.find({}, function(err, users){
		if(err){
			response.error = "Something went wrong";
			req.json(response);
			return next(err);
		}else{
			res.json(users);
		}
	});
}

exports.userByID = function(req, res, next, id){
	var response = {};

	User.findOne({
			_id: id
		}, 
		function(err, user){
			if(err){
				response.error = "Something went wrong";
				res.json(response);
				return next(err);
			}
			else{
				if(user == null){
					response.error = "User does not exist";
					res.json(response);
					return;
				}
				req.user = user;
				next();
			}
		}
	);
};

exports.read = function(req, res){
	res.json(req.user);
};

exports.update = function(req, res, next){
	var response = {};

	User.findByIdAndUpdate(req.user.id, req.body, function(err, user){
		if(err){
			switch(err.code){
				case 11000:
				case 11001:
					response.error = 'Email is already taken';
					break;
				default:
					response.error = 'Something went wrong';
			}
			res.json(response);
			return next(err);
		}
		else{
			response.error = "";
			response.success = "User information successfully updated";
			res.json(response);
		}
	});
};

exports.delete = function(req, res, next){
	var response = {};

	req.user.remove(function(err){
		if(err){
			response.error = "Something went wrong";
			res.json(response);
			return next(err);
		}
		else{
			response.error = "";
			response.success = "User successfully deleted";
			res.json(response);
		}
	})
};