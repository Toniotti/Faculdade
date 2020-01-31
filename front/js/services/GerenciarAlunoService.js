angular.module('faculdadeApp').service('gerenciarAlunoService', function () {
    var alunoSelecionado = {}

    this.getAlunoSelected = function () {
        return this.alunoSelecionado;
    }

    this.setAlunoSelected = function (aluno) {
        this.alunoSelecionado = aluno;
    }

})