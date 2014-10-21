function rootController($scope, $http, $timeout, $rootScope) {
    $scope.cardUrl = [];
    $scope.pickedcards = [];
    $scope.lastbooster = null;
    $scope.lastpick = null;
    var i = 0;
    
    (function tick() {
        $scope.data = $http.get("rest/draft/getbooster/" + $scope.name + "/"+i++)
            .success(function(data, status, headers, config) {
                $scope.booster = data;
                $scope.cardUrl = data['cardUrl'];
                $scope.lastbooster = data['uuid'];
                    $timeout(tick, 5000);
            }).error(function(data, status, headers, config) {
                    $timeout(tick, 5000);
            });
            
    })();
    
    $scope.getpicks = function(){
        $http.get("rest/draft/mycards/" + $scope.name)
            .success(function(data, status, headers, config) {
                //$scope.pickedcards = data['cardPicked'];
                $scope.pickedcards = [];
                for(var i=0;i<data['cardPicked'].length;++i){
                    $scope.pickedcards.push({id : i, url : data['cardPicked'][i]});
                }
            }).error(function(data, status, headers, config) {
            });
    };
    
    $scope.getBooster = function(){
            $http.get("rest/draft/getbooster/" + $scope.name)
            .success(function(data, status, headers, config) {
                $scope.booster = data;
                $scope.cardUrl = data['cardUrl'];
            }).error(function(data, status, headers, config) {

            });
    };

$scope.hideName = false;

$scope.joinDraft = function(){
    if($scope.name === null || $scope.name === ""){
        return;
    }
    $scope.hideName = true;
    console.debug($scope.name);
    $http.post("rest/draft/join/", {msg:$scope.name})
            .success(function(data, status, headers, config) {
            }).error(function(data, status, headers, config) {
            });
};

$scope.takeCard= function(takeCard){
    if($scope.lastbooster === $scope.lastpick){
        return;
    }
    else{
        $scope.lastpick = $scope.lastbooster;
    }
    $http.post("rest/draft/pickcard/", {name:$scope.name, url:takeCard})
            .success(function(data, status, headers, config) {
               $scope.getpicks();
            }).error(function(data, status, headers, config) {
            });
};

};