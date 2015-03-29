var mongoose = require('../../config/mongoose.js');
var Schema = mongoose.Schema;
var autoIncrement = require('mongoose-auto-increment');

var connection = monogoose();

autoIncrement.initialize(connection);

var UserSchema = new Schema({
	email: {
		type: String,
		unique: true
	},
	username: {
		type: String,
		unique: true
	},
	password: String
});

UserSchema.plugin(autoIncrement.plugin, 'User');
mongoose.model('User', UserSchema);