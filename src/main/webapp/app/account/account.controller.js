'use strict';

/* Users Controller */

ngApp.lazy.controller('accountsCtrl', function($scope, $log, $location, $http, AccountFactory) {
	var vm = this;
	vm.isLoading = false;
	vm.showSignIn = 1;
	vm.signup = signup;
	vm.signin = signin;
	vm.authenticate = authenticate;

	function signup () {
		AccountFactory.save(vm.obj, function (data) {
			angular.extend(vm.obj, data);
			vm.obj.message = data.message;
			setUser(vm.obj);
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});

	}

	function signin () {
		$http.post('/accounts/signin', vm.obj).success(function(data) {
			angular.extend(vm.obj, data);
			vm.obj.message = data.message;
			setUser(vm.obj);
		}).error(function(error) {
			$log.log("ERROR signin: ", error);
		});
	}


	function authenticate () {
		$log.log ("authenticate...");
		$http.get('/accounts/').success(function(data) {
			$log.log ("authenticate success: ", data);
		}).error(function(error) {
			$log.log("ERROR signin: ", error);
		});
	}

	function setUser (data) {
		authenticate ();
		/*if (!data.isLoggedIn) {
			data = {};
		}
		localStorageService.set("user", data)
		MenuFactory.setUser(data);*/
		/*if (data.isLoggedIn) {
			$location.path('/groups');
		}*/
	}

});