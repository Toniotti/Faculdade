angular.module('faculdadeApp').controller('gerenciarSalas', function ($scope, $http, salaVars) {


    $scope.getAll = function () {
        $http.get('http://localhost:8080/api/sala/all').then(function (response) {
            series = []
            for(sala of response.data) {
                if(!series.includes(sala.serie)){
                    series.push(sala.serie)   
                }
            }
            $scope.series = series
        }).catch(function (error) {
        })
    }

    $scope.getBySerie = function () {
        var url = 'http://localhost:8080/api/sala/all/' + $scope.serieSelecionada
        $http.get(url).then(function (response) {
            $scope.salasBySerie = response.data
        }).catch(function (error) {
        })
    }

    $scope.changeVars = function () {
        salaVars.setSerie($scope.serieSelecionada)
        salaVars.setLetra($scope.letraSelecionada)
    }

    $scope.getAll()
});