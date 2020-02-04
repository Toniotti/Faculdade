angular.module('faculdadeApp').service('gerenciarAlunoService', function () {
    var alunoSelecionado = {}

    this.setAlunoSelected = function (aluno) {
        this.alunoSelecionado = aluno;
    }

})