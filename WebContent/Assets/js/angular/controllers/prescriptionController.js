hms.controller('PrescriptionController', function ($scope, $http, $location) {

    $scope.visitChanged = function (visitId) {
        location.href='/Patient/Prescription.jsp?accountID='+$scope.accountId+'&visitID='+visitId
        //$scope.getPrescription($scope.accountId, visitId);
    };

    $scope.prescription = {ho:[],chiefComplain:[],onObservation:[],investigation:[],diagonosis:[{disease:''}],medicines:[],advice:[],patientID: $location.search().accountID};

    $scope.searchMedicine = function(val, index){
        if(val.length>=3) {
            return $http.get('/rest/medicine/search?keyword=' + val).then(function (response) {
                //alert(JSON.stringify(response));
                return response.data.medicineList.map(function (item) {
                    return item;
                });
            });
        }
    };

    $scope.addMedicine = function($item, $model, $label, $index) {
        $scope.medicines[$index].medicineName = $item.medicineName;
    };

    $scope.getAllDisease = function(){
        $http({
            method: "get",
            url: '/rest/disease/get/all'
        }).success(function (result) {
            $scope.items = result.diseaseList;
        });
    };

    $scope.medicines = [];
    $scope.medicines.push({});
    $scope.addMedicineRow = function(){
        $scope.medicines.push({});
    };
    $scope.removeMedicineRow = function(index){
        $scope.medicines.splice(index, 1);
    };


    $scope.getVisits = function (accountID) {
        $http({
            method: "get",
            url: '/rest/visit/all?accountID=' + accountID
        }).success(function (result) {
            $scope.visits = result.visits;
            $scope.accountId = accountID;
        });
    }

    $scope.getPrescription = function (accountID, visitID) {
        $http({
            method: "get",
            url: '/rest/prescription/get?accountID=' + accountID + '&visitID='+visitID
        }).success(function (result) {
            if(result.prescription && result.prescription.id) {
                $scope.prescription = result.prescription;
                $scope.prescription.ho = JSON.parse($scope.prescription.ho);
                $scope.prescription.chiefComplain = JSON.parse($scope.prescription.chiefComplain);
                $scope.prescription.onObservation = JSON.parse($scope.prescription.onObservation);
                $scope.prescription.investigation = JSON.parse($scope.prescription.investigation);
                $scope.prescription.diagonosis = JSON.parse($scope.prescription.diagonosis);
                $scope.prescription.advice = JSON.parse($scope.prescription.advice);
                if($scope.prescription.medicines.length>0) {
                    $scope.medicines = $scope.prescription.medicines;
                }
            }else{
                $scope.medicines = [];
                $scope.medicines.push({});
                $scope.prescription = {ho:[],chiefComplain:[],onObservation:[],investigation:[],diagonosis:[{disease:''}],medicines:[],advice:[],patientID: $location.search().accountID};

            }

            var index = $scope.visits.map(function(x) {return x.id; }).indexOf(visitID);
            $scope.currentVisit = $scope.visits[index==-1?$scope.visits.length-1:index];
        });
    }

    $scope.savePrescription = function () {

        console.log(JSON.stringify($scope.prescription));
        $scope.busy = true;
        var temp = {medicines:[]};
        temp.id =  $scope.prescription.id;
        temp.patientID = $scope.prescription.patientID;
        temp.ho = JSON.stringify($scope.prescription.ho);
        temp.chiefComplain = JSON.stringify($scope.prescription.chiefComplain);
        temp.onObservation = JSON.stringify($scope.prescription.onObservation);
        temp.investigation = JSON.stringify($scope.prescription.investigation);
        temp.diagonosis = JSON.stringify($scope.prescription.diagonosis);
        temp.advice = JSON.stringify($scope.prescription.advice);
        temp.referTo =  $scope.prescription.referTo;
        temp.nextVisitDate =  $scope.prescription.nextVisitDate;
        for(var index in $scope.medicines){
            var med =  $scope.medicines[index];
            delete med.$$hashKey;
            temp.medicines.push(med);
        }

        $http({
            method: "post",
            url: '/rest/prescription/save',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param({jsonString:JSON.stringify(temp)})
        }).success(function (result) {
            $scope.busy = false;
            $scope.prescription.id = result.prescription.id;
            toastr.success('Prescription saved successfully.');
        });
    };

    $scope.toggleCheckBox=function($event){
        $event.stopPropagation();
        if($event.target.type!='checkbox') {
            $($event.currentTarget).siblings().children("input").first().click();
        }
        console.log($event.target.type)
        $($event.currentTarget).hide();
        $($event.currentTarget).siblings().show();
    };

    $scope.getValue = function(oe, source){
        if(!source){
            source = $scope.prescription.onObservation;
        }
        for(var i=0;i<source.length;i++){
            if(source[i][oe]){
                return source[i];
            }
        }
        var jsonVariable = {};
        jsonVariable[oe]={lt:[],rt:[],lb:[],rb:[]};
        return jsonVariable;
    };

    $scope.imChanged = function(oe){
        console.log(JSON.stringify($scope.prescription.onObservation));
    };

    $scope.show = function(oe, source){
        if(!source){
            source = $scope.prescription.onObservation;
        }
       for(var i in source){
           var item = source[i];
           if(item[oe]){
               return true;
           }
       }

        return false;
    };

    $scope.getModel = function(oe, source){
        if(!source){
            source = $scope.prescription.onObservation;
        }
        for(var i in source){
            var item = source[i];
            if(item[oe]){
                return item[oe];
            }
        }

        return {};
    };


    $scope.compareFn = function(obj1, obj2){
        return Object.keys(obj1)[0] ==  Object.keys(obj2)[0];
    }


    $scope.getOtherCheckboxValue = function(source){
        for(var i in source){
            var item = source[i];
            if(item.other){
                alert(item);
                return item;
            }
        }

        return {other:{text:''}};
    };

    $scope.getOtherTextModel = function(source){
        for(var i in source){
            var item = source[i];
            if(item.other){
                return item;
            }
        }

        return {other:{text:''}};
    };

    $scope.isObject = function(input) {
        return angular.isObject(input);
    };

    $scope.isString = function(input) {
        return angular.isString(input);
    };

});
