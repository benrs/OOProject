var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var autoIncrement = require('mongoose-auto-increment');

autoIncrement.initialize(mongoose);

var PictureSchema = new Schema({
	UID: {
		type: Number,
		required: true
	},
	encoded_pic: {
		type: String,
		required: true
	}
});

PictureSchema.plugin(autoIncrement.plugin, 'Picture');
mongoose.model('Picture', PictureSchema);