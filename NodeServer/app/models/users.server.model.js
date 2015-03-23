var mongoose = require('mongoose');
var Schema   = mongoose.Schema;
var auto_inc = require('mongoose-auto-increment');

auto_inc.initialize(mongoose);

var UserSchema = new Schema({
	name: String,
	email: {
		type: String,
		index: true,
		unique: true
	},
	password: String
});

UserSchema.plugin(auto_inc.plugin, 'User');
mongoose.model('User', UserSchema);