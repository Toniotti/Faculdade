angular.module('faculdadeApp').controller('gerenciarAlunos', function ($scope, $routeParams, $http, salaVars, $location) {
    $scope.alunoSelecionado = {};

    $scope.pesquisar = function () {
        var url = 'http://localhost:8080/api/aluno/search/' + $scope.pesquisa + '/' + salaVars.serieSelecionada + '/' + salaVars.letraSelecionada + '/0/5'
        $http.get(url).then(function (response) {
            $scope.resultadoPesquisa = response.data
        }).catch(function (error) {
        })
        $scope.resultadoPesquisa = ''
    }

    $scope.cadastrarAluno = function () {
        var alunoDTO = {
            'nome': $scope.nomeAluno,
            'sala': salaVars.letraSelecionada,
            'serie': salaVars.serieSelecionada
        }
        $http.post('http://localhost:8080/api/aluno/save', alunoDTO).then(function () {
            return alert('Usuario cadastrado com sucesso.')
        }).catch(function (error) {
        })
    }

    $scope.editarAluno = function(){
        var newAluno = {
            'nome' : $scope.nomeAlunoEditar,
            'sala' : salaVars.letraSelecionada,
            'serie' : salaVars.serieSelecionada
        }
        $http.put('http://localhost:8080/api/aluno/update/'+$scope.alunoSelecionado.matricula, newAluno)
        .then(function(response){  
            $scope.retornoEditarAluno = "Aluno editado com sucesso."
        })
        .catch(function(error){

        })
    }

    $scope.getByIdInUrl = function () {
        var url = 'http://localhost:8080/api/aluno/' + $routeParams.id
        $http.get(url).then(function (response) {
            $scope.alunoSelecionado = response.data;
            $scope.nomeAlunoEditar = response.data.nome
        }).catch(function (error) {
        })
    }


    $scope.changePath = function(path){
        $location.path(path)
    }

    $scope.getByIdInUrl()
});