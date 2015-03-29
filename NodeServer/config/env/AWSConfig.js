var AWS = require('aws-sdk');

module.exports = function(){
	AWS.config.update({accessKeyId: 'AKIAI3K6YFSDCKDJVDYQ', secretAccessKey: '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'});
	AWS.config.region = 'us-west-2';
	return AWS;
}