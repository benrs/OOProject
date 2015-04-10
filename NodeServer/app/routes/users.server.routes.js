var users = require('../controllers/users.server.controller.js');

module.exports = function(app){
	app.post('/api/users/createUser', users.create);

	app.post('/api/users/login', users.login);
}