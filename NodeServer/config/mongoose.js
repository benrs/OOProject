var config   = require('./config');
var mongoose = require('mongoose');

module.exports = function(){
	var db = mongoose.connect(config.db);
	require('../app/models/oop/user.server.model.js');
	require('../app/models/oop/token.server.model.js');
	require('../app/models/oop/pictures.server.model.js');
	return db;
}