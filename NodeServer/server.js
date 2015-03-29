process.env.NODE_ENV = process.env.NODE_ENV || 'development';

var config   = require('./config/config');
var dynamoDB = require('./config/dynamoDB'); 
var express  = require('./config/express');

var db  = dynamoDB();
var app = express();

app.listen(config.variables.port);

app.get('/test/', function(req, res){
	db.listTables(function(error, response){
		console.log(response);
	});
	res.end();
});

console.log(process.env.NODE_ENV  + ' server running at http://'+config.variables.serverIP+':' + config.variables.port);