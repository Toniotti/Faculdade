var faculdadeApp = angular.module('faculdadeApp', ['ngRoute']);

faculdadeApp.service('salaVars', function(){
    var letraSelecionada;
    var serieSelecionada;

    this.setSerie = function(serie){
        this.serieSelecionada = serie;
    }

    this.setLetra = function(letra){
        this.letraSelecionada = letra;
    }

})


faculdadeApp.controller('home', ['$scope', '$location', function($scope, $location){
    $scope.mudarRota = function(rota){
        $location.path(rota)
    }
}])

faculdadeApp.controller('gerenciarAlunos', ['$scope', '$http', 'salaVars', function($scope, $http, salaVars){

    $scope.pesquisar = function(){
        var url = 'http://localhost:8080/api/aluno/search/'+$scope.pesquisa+'/'+salaVars.serieSelecionada+'/'+salaVars.letraSelecionada+'/0/5'
        $http.get(url).then(function(response){
            $scope.resultadoPesquisa = response.data
        }).catch(function(error){
        })
        $scope.resultadoPesquisa = ''
    }
}])

faculdadeApp.controller('gerenciarSalas', ['$scope', '$http', 'salaVars', function($scope, $http, salaVars){

    $scope.getAll = function(){
        $http.get('http://localhost:8080/api/sala/all/').then(function(response){
            $scope.salas = response.data
        }).catch(function(error){
        })
    }

    $scope.getBySerie = function(){
        var url = 'http://localhost:8080/api/sala/all/'+$scope.serieSelecionada
        $http.get(url).then(function(response){
            $scope.salasBySerie = response.data
        }).catch(function(error){
        })
    }

    $scope.changeVars = function(){
        salaVars.setSerie($scope.serieSelecionada)
        salaVars.setLetra($scope.letraSelecionada)
    }
}])


// configuração de rota
faculdadeApp.config(function($routeProvider){
    $routeProvider
      .when("/gerenciar/alunos/pesquisa", {
        templateUrl:"../pesquisaAluno.html",
        controller: 'gerenciarAlunos'
      })
      .otherwise({redirectTo: '/'});
  })
