package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.aluno.boletim.BoletimDTO;
import br.com.hbsis.faculdade.aluno.boletim.BoletimService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/boletim")
public class BoletimController {
    private final BoletimService boletimService;

    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @GetMapping("/gerar/{id}")
    public void generateJasper(@PathVariable("id") Long matricula, HttpServletResponse response) {
        this.boletimService.generateJasper(matricula, response);
    }
}
