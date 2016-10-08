var ngApp = angular.module('ngApp', ['ngRoute', 'ngResource']);

ngApp.config(function ($controllerProvider, $compileProvider, $filterProvider, $provide, $routeProvider, $httpProvider) {
	
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

	ngApp.lazy = {
        controller: $controllerProvider.register,
       	directive: $compileProvider.directive,
        filter: $filterProvider.register,
        factory: $provide.factory,
        service: $provide.service
    };

	$routeProvider.when('/home', {
		templateUrl: 'app/home/home.html'
	});

	$routeProvider.when('/signup', {
		templateUrl: 'app/account/signup.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/account/account.service.js',
					'app/account/account.controller.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	});

	$routeProvider.when('/signin', {
		templateUrl: 'app/account/signin.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/account/account.service.js',
					'app/account/account.controller.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	});
	$routeProvider.when('/test', {
		templateUrl: 'app/test/test.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/test/test.controller.js',
					'app/test/test.service.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	});
	$routeProvider.otherwise({
		redirectTo: '/home'
	});
});