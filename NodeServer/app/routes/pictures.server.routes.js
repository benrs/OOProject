var pictures = require('../controllers/pictures.server.controller.js');

module.exports = function(app){
	// Make sure everyone is using a valid token
	app.use(pictures.validateToken);
	app.post("/api/pictures/getPictures", pictures.getPictures);
	app.post("/api/pictures/getOnePicture", pictures.getOnePicture);
	app.post("/api/pictures/createPic", pictures.createNewPic);
	app.post("/api/pictures/deletePic", pictures.deletePic);
}