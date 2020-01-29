var faculdadeApp = angular.module('faculdadeApp', ['ngRoute']);

faculdadeApp.controller('home', function($scope, $location){
    $scope.mudarRota = function(rota){
        $location.path(rota)
    }
})

faculdadeApp.controller('gerenciarAlunos', function($scope, $http){
    $scope.pesquisar = function(){
        var url = 'http://localhost:8080/api/aluno/search/'+$scope.pesquisa+'/7/A/0/5'
        $http.get(url).then(function(response){
            $scope.resultadoPesquisa = response.data
            console.log(response.data)
        }).catch(function(error){
            console.log('Error: ', error)
            console.log('Error status: ', error.status)
        })
    }
})




// configuração de rota
faculdadeApp.config(function($routeProvider){
    $routeProvider
      .when("/gerenciar/alunos", {
        templateUrl:"../alunos.html",
        controller: 'gerenciarAlunos'
      })
      .otherwise({redirectTo: '/'});
  })