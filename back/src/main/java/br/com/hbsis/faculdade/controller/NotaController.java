package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.aluno.nota.NotaDTO;
import br.com.hbsis.faculdade.aluno.nota.NotaService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotaController {
    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping("/nota/save")
    public NotaDTO save(@Valid @RequestBody NotaDTO notaDTO) {
        return this.notaService.save(notaDTO);
    }

    @DeleteMapping("/nota/delete/{idNota}")
    public void delete(@PathVariable("idNota") Long idNota) {
        this.notaService.delete(idNota);
    }

    @PutMapping("/nota/update/{idNota}")
    public NotaDTO update(@Valid @RequestBody NotaDTO notaDTO, @PathVariable("idNota") Long idNota) {
        return this.notaService.update(notaDTO, idNota);
    }

    @GetMapping("/nota/get/{idAluno}")
    public List<NotaDTO> getNotasByAluno(@PathVariable("idAluno") Long idAluno) {
        return this.notaService.getNotaByAluno(idAluno);
    }
}
