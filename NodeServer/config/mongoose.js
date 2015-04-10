var config   = require('./config');
var mongoose = require('mongoose');

module.exports = function(){
	var db = mongoose.connect(config.db);
	require('../app/models/user.server.model.js');
	require('../app/models/token.server.model.js');
	require('../app/models/pictures.server.model.js');
	return db;
}