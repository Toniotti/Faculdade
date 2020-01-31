angular.module('faculdadeApp').controller('home', function ($scope, $location) {
    $scope.mudarRota = function (rota) {
        $location.path(rota)
    }
});