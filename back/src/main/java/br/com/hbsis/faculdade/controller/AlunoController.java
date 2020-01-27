package br.com.hbsis.faculdade.controller;

import br.com.hbsis.faculdade.aluno.AlunoDTO;
import br.com.hbsis.faculdade.aluno.AlunoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.InvalidPropertiesFormatException;

@RestController
@RequestMapping("/api")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/aluno/save")
    public AlunoDTO save(@Valid @RequestBody AlunoDTO alunoDTO) throws InvalidPropertiesFormatException {
        return this.alunoService.save(alunoDTO);
    }

    @PostMapping("/aluno/update")
    public AlunoDTO update(@Valid @RequestBody AlunoDTO novoAluno, @Valid @RequestBody AlunoDTO antigoAluno) throws InvalidPropertiesFormatException {
        return this.alunoService.update(novoAluno, antigoAluno);
    }

    @PostMapping("/aluno/delete")
    public void delete(@Valid @RequestBody AlunoDTO alunoDTO){
        this.alunoService.delete(alunoDTO);
    }

    @GetMapping("/aluno/{nome}/{letraSala}")
    public AlunoDTO getAluno(@Valid @RequestBody AlunoDTO alunoDTO){
        return AlunoDTO.of(this.alunoService.findEntityByDTO(alunoDTO));
    }
}
