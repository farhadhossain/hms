hms.controller('ViewFindingsController', function ($scope, $http, $location) {

    $scope.visitChanged = function (visitId) {
       location.href= '/Report/PatientInfoAll.jsp?accountID='+ $scope.accountId + '&visitId='+visitId;
    };

    $scope.getVisits = function (accountID) {
        $http({
            method: "get",
            url: '/rest/visit/all?accountID=' + accountID
        }).success(function (result) {
            $scope.visits = result.visits;
            $scope.accountId = accountID;
        });
    };

});
