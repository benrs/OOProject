var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var TokenSchema = new Schema({
	UID: {
		type: Number,
		unique: true,
		required: true
	},
	token: {
		type: String,
		unique: true,
		required: true
	},
	expiry: {
		type: Date,
		required: true,
		expires: 10*60
	}
});

mongoose.model('Token', TokenSchema);