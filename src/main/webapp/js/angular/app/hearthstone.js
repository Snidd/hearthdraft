var HearthStone = angular.module("HearthStone", ["ui.directives", "ngRoute"]);

HearthStone.config(function($routeProvider) {
    $routeProvider
            .when("/", {templateUrl: "./templates/welcome.html"})
            .when("/home", {templateUrl: './templates/home.html'})
            .when("/createfaktura", {templateUrl: './templates/createfaktura.html'})
            .when("/profile", {templateUrl: './templates/profile.html'})
            .when("/myinvoices", {templateUrl: './templates/showfakturalist.html'})
            .when("/information", {templateUrl: './templates/information.html'})
            .when("/signup", {templateUrl: './templates/signup.html'});
});