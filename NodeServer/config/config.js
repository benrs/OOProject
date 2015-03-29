module.exports = {
	variables: require('./env/' + process.env.NODE_ENV + '.js'),
	AWS: require('./env/AWSConfig.js')
}