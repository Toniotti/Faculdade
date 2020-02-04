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
        .when("/gerenciar/notas/pesquisarAlunoNota", {
            templateUrl: "/view/pesquisarAlunoNota.html",
            controller: "gerenciarAlunos"
        })
        .when("/gerenciar/notas/cadastrarNota", {
            templateUrl: "/view/cadastrarNota.html",
            controller: "gerenciarAlunos"
        })
        .when("/gerenciar/notas/adicionarNota/:matricula", {
            templateUrl: "/view/adicionarNota.html",
            controller: "gerenciarNotas"
        })
        .otherwise({ redirectTo: '/' })
})