var port = 8080;
var serverIP   = 'localhost';
var dbIP       = '54.200.0.50';
var dbPort     = '27017';

module.exports = {
	port: port,
	serverIP: serverIP,
	db: "mongodb://"+dbIP+":"+dbPort+"/picApp"
}