angular.module('faculdadeApp').controller('gerenciarProfessor', function($scope, $http, salaVars){
    $scope.serieSelecionada = {}
    $scope.letraSelecionada = {}

    $scope.adicionarSala = function(){
        $scope.serieSelecionada = salaVars.serieSelecionada
        $scope.letraSelecionada = salaVars.letraSelecionada

        var sala = {
            "serie" : $scope.serieSelecionada,
            "letra" : $scope.letraSelecionada
        }

        if(existeArray($scope.salaSelecionada, sala) == false){
            $scope.salaSelecionada.push(sala)
        }
    }

    $scope.cadastrarProfessor = function(){
        var professorDTO = {
            "nome" : $scope.nomeProfessor,
            "salas" : $scope.salaSelecionada
        }
        $http.post('http://localhost:8080/api/professor/save', professorDTO).then(function(response){
            console.log(response.data)
        }).catch(function(error){
            console.log(error)
        })
    }
    function existeArray(array, obj){
        for(var i = 0; i < array.length; i++){
            if(array[i].serie == obj.serie && array[i].letra == obj.letra){
                return true
            }
        }
        return false
    }
})