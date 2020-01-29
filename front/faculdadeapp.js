var faculdadeApp = angular.module('faculdadeApp', ['ngRoute']);

faculdadeApp.controller('home', function($scope, $http, $location){
    $scope.pesquisar = function(){
        var url = 'http://localhost:8080/api/aluno/search/'+$scope.pesquisa+'/7/A/0/5'
        $http.get(url).then(function(response){
            $scope.resultadoPesquisa = response.data
        }).catch(function(error){
            console.log('Error: ', error)
            console.log('Error status: ', error.status)
        })
        console.log($scope.resultadoPesquisa)
    }


    $scope.mudarRota = function(rota){
        $location.path(rota)
    }
})




// configuração de rota
faculdadeApp.config(function($routeProvider){
    $routeProvider
      .when("/gerenciar/alunos", {
        templateUrl:"../alunos.html"
      })
      .otherwise({redirectTo: '/'});
  })