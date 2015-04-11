(function(){
	var app = angular.module("myApp", ['ngMaterial']);

	app.config(function($mdThemingProvider){
		$mdThemingProvider.theme('default')
		    .primaryPalette('red')
		    .accentPalette('pink')
		    .warnPalette('red')
		    .backgroundPalette('deep-orange');
	});

	app.controller('SideNavController', function($scope, $timeout, $mdSidenav, $log) {
		$scope.toggleLeft = function() {
			$mdSidenav('left').toggle().then(function(){
				$log.debug("toggle left is done");
			});
		};
	});
	
	app.controller('LeftCtrl', function($scope, $timeout, $mdSidenav, $log) {
		$scope.close = function() {
			$mdSidenav('left').close().then(function(){
				$log.debug("close LEFT is done");
			});
		};
	});
})();