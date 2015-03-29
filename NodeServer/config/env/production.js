var port = 8080;
var serverIP   = '54.148.43.145';
var dbIP       = '54.186.35.160';
var dbPort     = '27017';
var dbUser     = 'ubuntu';
var dbPassword = 'barault6404'; 

module.exports = {
	port: port,
	serverIP: serverIP,
	db: "mongod://"+dbUser+":"+dbPassword+"@"+dbIP+":"+dbPort+"/picApp"
}