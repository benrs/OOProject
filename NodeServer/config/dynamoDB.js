var config = require('./config');
var AWS    = config.AWS(); 

module.exports = function(){
	var dynamodb = new AWS.DynamoDB();
	return dynamodb;
}