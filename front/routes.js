angular.module('faculdadeApp').config(function ($routeProvider) {
    $routeProvider
        .when("/gerenciar/alunos/pesquisa", {
            templateUrl: "/view/pesquisaAlunos.html",
            controller: 'gerenciarAlunos'
        })
        .when("/gerenciar/alunos/cadastro", {
            templateUrl: "/view/cadastrarAluno.html",
            controller: 'gerenciarAlunos'
        })
        .when("/gerenciar/alunos/editar/:id", {
            templateUrl: "/view/editarAlunos.html",
            controller: "gerenciarAlunos"
        })
        .otherwise({ redirectTo: '/' })
})