var users = require('../controllers/users.server.controller.js');

module.exports = function(app){
	app.post('/api/users/createUser', users.create);

	app.get('/api/users/:userId', users.read);
	app.put('/api/users/:userId', users.update);
	app.delete('/api/users/:userId', users.delete);
	app.param('userId', users.userByID);
}