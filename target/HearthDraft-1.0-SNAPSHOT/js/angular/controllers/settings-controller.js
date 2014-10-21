function settingsController($scope, $http, $rootScope) {
$scope.refresh = function(){
    $http.get("rest/draft/settings")
            .success(function(data, status, headers, config) {
                $scope.settings = data;
                $scope.players = data.players;
                $scope.boosters = data.boosters;
                $scope.neutrals = data.neutrals;
                $scope.classcards = data.classcards;
            }).error(function(data, status, headers, config) {
            });
};

$scope.removeClass = function(asdf){
    var index = $scope.settings['classes'].indexOf(asdf);
    $scope.settings['classes'].splice(index, 1);    
};

$scope.startDraft = function(){
    $http.get("rest/draft/start")
            .success(function(data, status, headers, config) {
                console.debug("started");
            }).error(function(data, status, headers, config) {
                console.debug("error during start");
            });
};

$scope.resetDraft = function(){
    $http.get("rest/draft/clear")
            .success(function(data, status, headers, config) {
                console.debug("cleared");
            }).error(function(data, status, headers, config) {
                console.debug("error during clear");
            });
};

$scope.refresh();

$scope.updateSettings = function(){
    $http.post("rest/draft/updatesettings", $scope.settings)
            .success(function(data, status, headers, config) {
                console.debug("updated!");
            }).error(function(data, status, headers, config) {
                console.debug("error during update");
            });
};
};