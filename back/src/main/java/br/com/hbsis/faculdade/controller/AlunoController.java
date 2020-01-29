package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.aluno.AlunoDTO;
import br.com.hbsis.faculdade.aluno.AlunoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/aluno/save")
    public AlunoDTO save(@Valid @RequestBody AlunoDTO alunoDTO) {
        return this.alunoService.save(alunoDTO);
    }

    @PutMapping("/aluno/update/{id}")
    public AlunoDTO update(@Valid @RequestBody AlunoDTO alunoDTO, @PathVariable("id") Long id) {
        return this.alunoService.update(alunoDTO, id);
    }

    @DeleteMapping("/aluno/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        this.alunoService.delete(id);
    }

    @GetMapping("/aluno/{matricula}")
    public AlunoDTO getAluno(@PathVariable("matricula") Long matricula){
        return this.alunoService.get(matricula);
    }

    @GetMapping("/aluno/search/{nome}/{serie}/{letra}/{page}/{size}")
    public List<AlunoDTO> searchBySala(@PathVariable("nome") String nome, @PathVariable("serie") Integer serie, @PathVariable("letra") String letra, @PathVariable("page") int page, @PathVariable("size") int size){
        return this.alunoService.getAllPaginated(nome, letra, serie, page, size);
    }
}
