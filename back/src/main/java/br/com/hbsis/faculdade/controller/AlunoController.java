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
    public AlunoDTO update(@Valid @RequestBody AlunoDTO alunoDTO, @RequestParam("nome") String nome, @RequestParam("letraSala") String letraSala) throws InvalidPropertiesFormatException {
        return this.alunoService.update(alunoDTO, nome, letraSala);
    }

    @PostMapping("/aluno/delete")
    public void delete(@RequestParam("nome") String nome, @RequestParam("letraSala") String letraSala){
        this.alunoService.delete(nome, letraSala);
    }

    @GetMapping("/aluno/{nome}/{letraSala}")
    public AlunoDTO getAluno(@Valid @RequestParam("nome") String nome, @RequestParam("letraSala") String letraSala){
        return AlunoDTO.of(this.alunoService.findEntityByNomeAndSala(nome, letraSala));
    }
}
