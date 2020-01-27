package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.sala.SalaDTO;
import br.com.hbsis.faculdade.sala.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SalaController {
    @Autowired
    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping("/sala/save")
    public SalaDTO save(@Valid @RequestBody SalaDTO salaDTO){
        return this.salaService.save(salaDTO);
    }

    @PostMapping("/sala/update/{serie}/{letra}")
    public SalaDTO update(@Valid @RequestBody SalaDTO salaDTO, @PathVariable("serie") Integer serie, @PathVariable("letra") String letra){
        return this.salaService.update(salaDTO, serie, letra);
    }

    @PostMapping("/sala/delete/{serie}/{letra}")
    public SalaDTO delete(@PathVariable("serie") Integer serie, @PathVariable("letra") String letra){
        return this.salaService.delete(serie, letra);
    }

    @GetMapping("/sala/{serie}/{letra}")
    public SalaDTO getSala(@PathVariable("serie") Integer serie, @PathVariable("letra") String letra){
        return SalaDTO.of(this.salaService.findEntityBySerieAndLetra(serie, letra));
    }
}
