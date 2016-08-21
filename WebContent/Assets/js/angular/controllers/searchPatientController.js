hms.controller('SearchPatientController', function ($scope, $modal) {

    $scope.open = function (patientId) {

        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            resolve: {
                item : function () {
                    return {patientId:patientId};
                }
            }
        });


    };
});

hms.controller('ModalInstanceCtrl', function ($scope, $modalInstance, item, $http) {

    $scope.item = item;

    $scope.ok = function () {
        $scope.busy = true;
        $http({
            method: "post",
            url: '/rest/visit/save',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param({jsonString:JSON.stringify($scope.item)})
        }).success(function (result) {
            $scope.busy = false;
            if (result.result == 'success') {
                $modalInstance.close('');
                window.location.reload();
            } else {
                $scope.alertService.addAlert({type: 'warning', msg: result.message});
            }
        });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
