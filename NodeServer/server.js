process.env.NODE_ENV = process.env.NODE_ENV || 'development';

var config   = require('./config/config');
var mongoose = require('./config/mongoose'); 
var express  = require('./config/express');

var db  = mongoose();
var app = express();

app.listen(config.port);

process.on('exit', function () {
	process.emit('cleanup');
});

process.on('SIGINT', function () {
	process.exit(2);
});

process.on('uncaughtException', function(e) {
	process.exit(99);
});

console.log(process.env.NODE_ENV  + ' server running at http://'+config.serverIP+':' + config.port);