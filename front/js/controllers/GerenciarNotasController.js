angular.module('faculdadeApp').controller('gerenciarNotas', function($scope, $http, $routeParams, $window){
    $scope.notaUpdate = {}
    $scope.descUpdate = {}

    $scope.getAlunoByMatricula = function(){
        $http.get('http://localhost:8080/api/aluno/'+$routeParams.matricula).then(function(response){
            $scope.aluno = response.data
        })
    }

    $scope.getByNotaMatricula = function(){
        $http.get('http://localhost:8080/api/nota/get/'+$routeParams.matricula).then(function(response){
            $scope.notas = response.data
            var total = 0
            response.data.forEach(nota => {
                total = total + nota.nota
            })
            $scope.media = parseFloat((total/response.data.length).toFixed(2))
        })
    }

    $scope.delete = function(nota){
        $http.delete('http://localhost:8080/api/nota/delete/'+nota.idNota).then(function(response){
            $scope.getByNotaMatricula()        
        })
    }

    function validarDTO(){
        if($scope.notaAdicionar == null || $scope.notaAdicionar == "" || $scope.descAdicionar == null || $scope.descAdicionar == ""){
            throw "os campos da nota não podem estar vazios";
        }else{
            var notaDTO = {
                "nota" : $scope.notaAdicionar,
                "idAluno" : $routeParams.matricula,
                "desc" : $scope.descAdicionar
            }

            return notaDTO
        }
    }

    $scope.save = function(){
        var notaDTO = validarDTO()
        $http.post('http://localhost:8080/api/nota/save', notaDTO).then(function(response){
            $scope.getAlunoByMatricula()
            $scope.getByNotaMatricula()

            $scope.notaAdicionar = null
            $scope.descAdicionar = null
        }).catch(function(error){
        })
    }



    $scope.update = function(idNota){
        
        var notaDTO = {
            
        }

        $http.put('http://localhost:8080/api/nota/update/'+idNota, notaDTO).then(function(response){
            console.log(response.data)
        }).catch(function(error){
            console.log(error)
        })
        
    }

    $scope.gerarBoletim = function(){
        $window.open('http://localhost:8080/api/boletim/gerar/'+$routeParams.matricula, '_blank')
    }

    $scope.getAlunoByMatricula()
    $scope.getByNotaMatricula()
})