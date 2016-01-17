hms.controller('TreatmentPlanController', function ($scope, $http, $window, $location) {

    $scope.isDutyNurse=$window.isDutyNurse;

    var patientID =  $location.search().accountID;
    $scope.prescription = {chiefComplain:[],onObservation:[],investigation:[], patientID:patientID};


    $scope.getPrescription = function (accountID) {
        $http({
            method: "get",
            url: '/rest/prescription/get?accountID=' + accountID
        }).success(function (result) {
            if(result.prescription && result.prescription.id) {
                $scope.prescription = result.prescription;
                $scope.prescription.chiefComplain = JSON.parse($scope.prescription.chiefComplain);
                $scope.prescription.onObservation = JSON.parse($scope.prescription.onObservation);
                $scope.prescription.investigation = JSON.parse($scope.prescription.investigation);
                $scope.prescription.diagonosis = JSON.parse($scope.prescription.diagonosis);
                $scope.prescription.advice = JSON.parse($scope.prescription.advice);
            }
            if($location.search().printNow){
                setTimeout(function(){window.print();},1000);

            }
        });
    };

    $scope.getTreatmentPlans = function (accountID) {
        $http({
            method: "get",
            url: '/rest/treatmentPlan/all?accountID=' + patientID
        }).success(function (result) {
            if(result.plans) {
                 for(var i in result.plans){
                     var plan = result.plans[i];
                     for(var j in $scope.extractionItems){
                         var ei = $scope.extractionItems[j];
                          //delete ei.$$hashKey;
                          if(plan.extractionId==ei.extractionId){
                              ei.num1 = plan.inputValue1!='null'?JSON.parse(plan.inputValue1):[];
                              ei.num2 = plan.inputValue2!='null'?JSON.parse(plan.inputValue2):[];
                              ei.num3 = plan.inputValue3!='null'?JSON.parse(plan.inputValue3):[];
                              ei.num4 = plan.inputValue4!='null'?JSON.parse(plan.inputValue4):[];
                              ei.paymentRegNo=plan.paymentRegNo=='null'?"":plan.paymentRegNo;
                              ei.done= plan.done;
                              ei.instrumentProvided= plan.instrumentProvided;
                              var temp = ei;
                              temp.planId = plan.id;
                              $scope.treatmentPlan.extraction.push(temp);
                              break;
                          }
                     }

                 }
            }
        });
    };


    $scope.treatmentPlan = {extraction:[]};


    $scope.getExtractionItems = function () {
        $http({
            method: "get",
            url: '/rest/extraction/get/all'
        }).success(function (result) {
            $scope.extractionItems=[];
            for(var i in result.extractionItems){
               var temp = result.extractionItems[i];
                temp.extractionId = temp.id;
                if(i<4){
                    temp.num1 =[];
                    temp.num2 =[];
                    temp.num3 =[];
                    temp.num4 =[];
                }
                //delete temp.id;
                //alert(JSON.stringify(temp));
                $scope.extractionItems.push(temp);
            }
        });
    };

    $scope.calculatePaymentAmount = function (index, item) {
        var amnt=0;
        if(item.id<4){
            amnt = item.taka*(item.num1?item.num1.length:0)+ item.taka*(item.num2?item.num2.length:0)+ item.taka*(item.num3?item.num3.length:0)+item.taka*(item.num4?item.num4.length:0);
        } else {
            amnt = item.taka;
        }
        $scope.treatmentPlan.extraction[index].paymentAmount = amnt;
        return amnt;

    };
    $scope.saveTreatmentPlan = function () {

        $scope.busy = true;
        var temp =[];
        for(var i in $scope.treatmentPlan.extraction) {
           var e = $scope.treatmentPlan.extraction[i];
           var ex = {patientId:patientID,
                     extractionId: e.id,
                     inputValue1: JSON.stringify(e.num1),
                     inputValue2: JSON.stringify(e.num2),
                     inputValue3: JSON.stringify(e.num3),
                     inputValue4: JSON.stringify(e.num4),
                     paymentAmount : e.paymentAmount,
                     paymentRegNo : e.paymentRegNo,
                     done : e.done,
                     instrumentProvided : e.instrumentProvided
                    };
            if(e.planId){ex.id= e.planId}
           console.log(JSON.stringify(ex));
            temp.push(ex);
        }

        $http({
            method: "post",
            url: '/rest/treatmentPlan/save',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param({jsonString:JSON.stringify(temp)})
        }).success(function (result) {
            $scope.busy = false;
            toastr.success('Treatment plan saved successfully.');
        });


    };

    $scope.isObject = function(input) {
        return angular.isObject(input);
    };

    $scope.isString = function(input) {
        return angular.isString(input);
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

    $scope.getChecklistValue = function(oe, source){
        if(!source){
            source = $scope.treatmentPlan.extraction;
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
        console.log(JSON.stringify($scope.treatmentPlan.extraction));
    };

    $scope.checklistShow = function(oe, source){
        if(!source){
            source = $scope.treatmentPlan.extraction;
        }
        for(var i in source){
            var item = source[i];
            if(item.name==oe){
                return true;
            }
        }

        return false;
    };

    $scope.getChecklistModel = function(oe, source){
        if(!source){
            source = $scope.treatmentPlan.extraction;
        }
        for(var i in source){
            var item = source[i];
            if(item.name==oe){
                return item;
            }
        }

        return {};
    };


    $scope.compareFn = function(obj1, obj2){
        return Object.keys(obj1)[0] ==  Object.keys(obj2)[0];
    }

});
