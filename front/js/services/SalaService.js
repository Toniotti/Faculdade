angular.module('faculdadeApp').service('salaVars', function () {
    var letraSelecionada;
    var serieSelecionada;

    this.setSerie = function (serie) {
        this.serieSelecionada = serie;
    }

    this.setLetra = function (letra) {
        this.letraSelecionada = letra;
    }

})