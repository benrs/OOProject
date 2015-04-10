var express = require('express');
var exphbs  = require('express-handlebars');
var bodyParser = require('body-parser');

var serverLogging = function(req, res, next){
	console.log(req.method, req.url);
	next();
}

module.exports = function(){
	var app = express();
	var hbs = exphbs.create({});

	// Setting up the handlebars engine
	app.engine('handlebars', hbs.engine);
	app.set('views', './app/views')
	app.set('view engine', 'handlebars');

	// Initializing some middleware
	app.use(bodyParser.urlencoded({ extended: true }));
	app.use(bodyParser.json());
	app.use(serverLogging);

	// Letting express know where our static file are located
	app.use(express.static('./public'));

	// Including all of the routes that we need
	require('../app/routes/index.server.routes.js')(app);
	require('../app/routes/users.server.routes.js')(app);
	require('../app/routes/pictures.server.routes.js')(app);
	return app;
}