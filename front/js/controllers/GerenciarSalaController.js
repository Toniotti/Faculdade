angular.module('faculdadeApp').controller('gerenciarSalas', function ($scope, $http, salaVars) {
    $scope.salas = []
    $scope.salaSelecionada = {}
    $scope.delete = {}

    $scope.getAll = function () {
        $http.get('http://localhost:8080/api/sala/all').then(function (response) {
            series = []
            for(sala of response.data) {
                if(!series.includes(sala.serie)){
                    series.push(sala.serie)   
                }
            }
            $scope.salas = response.data
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

    $scope.cadastrar = function(){
        var salaDTO = {
            "serie" : $scope.serieCadastro,
            "letra" : $scope.letraCadastro.toUpperCase()
        }

        $http.post('http://localhost:8080/api/sala/save', salaDTO).then(function(response){
            console.log(response.data)
        }).catch(function(error){
            console.log(error)
        })
        $scope.getAll()
    }

    $scope.delete = function(){
        $http.delete('http://localhost:8080/api/sala/delete/'+salaVars.serieSelecionada+'/'+salaVars.letraSelecionada).then(function(response){
            console.log(response.data)
        }).catch(function(error){
            console.log(error)
        })
        $scope.getAll()
    }

    $scope.update = function(){
        var salaDTO = {
            "serie" : $scope.serieCadastro,
            "letra" : $scope.letraCadastro.toUpperCase()
        }

        $http.put('http://localhost:8080/api/sala/update/'+salaVars.serieSelecionada+'/'+salaVars.letraSelecionada, salaDTO).then(function(response){
            console.log(response.data)
        }).catch(function(error){
            console.log(error)
        })
        $scope.getAll()
    }

    $scope.excluirSalaSelecionada = function(sala){
        console.log("sala: ",sala)
        console.log($scope.salaSelecionada.indexOf(sala))
        $scope.salaSelecionada = $scope.salaSelecionada.splice($scope.salaSelecionada.indexOf(sala))
    }

    $scope.getAll()
});