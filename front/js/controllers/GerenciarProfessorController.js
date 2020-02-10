angular.module('faculdadeApp').controller('gerenciarProfessor', function($scope, $http, salaVars){
    $scope.salaSelecionada = []

    $scope.deletarSalaSelecionada = function(sala){
        $scope.salaSelecionada.splice($scope.salaSelecionada.indexOf(sala), 1)
    }

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
            "salas" : $scope.salaSelecionada,
            "cpf" : $scope.cpfProfessor
        }
        $http.post('http://localhost:8080/api/professor/save', professorDTO).then(function(response){
            $scope.nomeProfessor = null
            $scope.salaSelecionada = null
            $scope.cpfProfessor = null
        }).catch(function(error){
            return "erro ao salvar professor"
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