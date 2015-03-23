var config   = require('./config');
var mongoose = require('mongoose');

module.exports = function(){
	var db = mongoose.connect(config.db);
	require('../app/models/users.server.model');
	return db;
}