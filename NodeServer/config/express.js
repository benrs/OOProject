var express    = require('express');
var subdomain  = require('express-subdomain'); 
var bodyParser = require('body-parser');

var serverLogging = function(req, res, next){
	console.log(req.method, req.url);
	next();
}

module.exports = function(){
	var app = express();
	var mainRouter = express.Router(); 
	var oopRouter  = express.Router();

	// Setting up the default html engine
	app.engine('html', require('ejs').renderFile);
	app.set('views', './app/views');

	// Initializing some middleware
	app.use(bodyParser.urlencoded({ extended: true }));
	app.use(bodyParser.json());
	app.use(serverLogging);

	// Letting express know where our static file are located
	app.use(express.static('./public'));
	app.use(express.static('./bower_components/angular'));
	app.use(express.static('./bower_components/angular-animate'));
	app.use(express.static('./bower_components/angular-aria'));
	app.use(express.static('./bower_components/angular-material'));

	// Including all of the routes that we need
	require('../app/routes/index.server.routes.js')(oopRouter);
	require('../app/routes/users.server.routes.js')(oopRouter);
	require('../app/routes/pictures.server.routes.js')(oopRouter);

	app.use(subdomain('oop', oopRouter));
	return app;
}