package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.professor.ProfessorDTO;
import br.com.hbsis.faculdade.professor.ProfessorService;
import br.com.hbsis.faculdade.sala.SalaDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/professor/save")
    public ProfessorDTO save(@Valid @RequestBody ProfessorDTO professorDTO) {
        return this.professorService.save(professorDTO);
    }

    @PutMapping("/professor/update/{id}")
    public ProfessorDTO update(@Valid @RequestBody ProfessorDTO professorDTO, @PathVariable("id") Long id) {
        return this.professorService.update(professorDTO, id);
    }

    @DeleteMapping("/professor/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.professorService.delete(id);
    }

    @GetMapping("/professor/get/{nome}")
    public List<ProfessorDTO> getAllByNomeAndSala(@PathVariable("nome") String nome, @Valid @RequestBody List<SalaDTO> salaDTOList, int page, int size) {
        return this.professorService.getProfessorBySerieAndSala(nome, salaDTOList, page, size);
    }
}
