var port = 8080;
var serverIP   = 'localhost';
var dbIP       = 'ec2-54-200-0-50.us-west-2.compute.amazonaws.com';
var dbPort     = '27017';

module.exports = {
	port: port,
	serverIP: serverIP,
	db: "mongodb://"+dbIP+":"+dbPort+"/picApp"
}