var index = require("../../controllers/oop/index.server.controller.js");

module.exports = function(app){
	app.get('/', index.render);
}