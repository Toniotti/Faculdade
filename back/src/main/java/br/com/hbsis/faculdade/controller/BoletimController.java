package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.aluno.boletim.BoletimDTO;
import br.com.hbsis.faculdade.aluno.boletim.BoletimService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/boletim")
public class BoletimController {
    private final BoletimService boletimService;

    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @PostMapping("/save")
    public BoletimDTO save(@RequestBody BoletimDTO boletimDTO){
        return this.boletimService.save(boletimDTO);
    }

    @GetMapping("/gerar/{id}")
    public void generateJasper(@PathVariable("id") Long matricula, HttpServletResponse response) {
        this.boletimService.generateJasper(matricula, response);
    }
}
