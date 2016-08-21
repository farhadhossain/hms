hms.controller('LogBookController', function ($scope, $http, $location) {

    $scope.users = [];

    $scope.programSelected = "Residency";

    $scope.programChanged = function(item) {
        $scope.getUsers();
    };


    $scope.getUsers = function () {
        $http({
            method: "get",
            url: '/rest/users/get?program=' + $scope.programSelected
        }).success(function (result) {
            $scope.users = result.users;
            //alert(JSON.stringify($scope.users));
        });
    };


    $scope.getUsers();

});
