process.env.NODE_ENV = process.env.NODE_ENV || 'development';

var config   = require('./config/config');
var mongoose = require('./config/mongoose'); 
var express  = require('./config/express');

var db  = mongoose();
var app = express();

app.listen(config.variables.port);

console.log(process.env.NODE_ENV  + ' server running at http://'+config.variables.serverIP+':' + config.variables.port);