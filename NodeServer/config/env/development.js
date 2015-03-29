var port = 8080;
var serverIP = 'localhost';
var dbIP     = 'localhost';

module.exports = {
	port: port,
	serverIP: serverIP,
	db: "mongod://"+dbIP+"/picApp"
}