package br.com.hbsis.faculdade.professor;

import br.com.hbsis.faculdade.sala.Sala;
import br.com.hbsis.faculdade.sala.SalaDTO;
import br.com.hbsis.faculdade.sala.SalaService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final IProfessorRepository professorRepository;
    private final SalaService salaService;

    public ProfessorService(IProfessorRepository professorRepository, SalaService salaService) {
        this.professorRepository = professorRepository;
        this.salaService = salaService;
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());

        professor.setSala(this.salaService.getSalasByDtoList(professorDTO.getSalas()));

        return ProfessorDTO.of(this.professorRepository.save(professor));
    }

    public ProfessorDTO update(ProfessorDTO professorDTO, Long idProfessor) {
        Optional<Professor> professorOptional = this.professorRepository.findById(idProfessor);
        if(professorOptional.isPresent()){
            Professor professor = professorOptional.get();
            professor.setNome(professorDTO.getNome());
            professor.setCpf(professorDTO.getCpf());
            professor.setSala(this.salaService.getSalasByDtoList(professorDTO.getSalas()));

            return ProfessorDTO.of(this.professorRepository.save(professor));
        }
        throw  new NoResultException("Professor não encontrado.s");
    }

    public void delete(Long idProfessor){
        this.professorRepository.deleteById(idProfessor);
    }

    public List<ProfessorDTO> getProfessorBySerieAndSala(String nomeProfessor, List<SalaDTO> salaDTOList, int page, int size){
        List<Sala> salaList = this.salaService.getSalasByDtoList(salaDTOList);
        List<Professor> professores = this.professorRepository.findByNomeContainingIgnoreCaseAndSalaIn(nomeProfessor, salaList, PageRequest.of(page, size));
        if(professores.isEmpty()){throw new NoResultException("Nenhum professor encontrado.");}
        return this.getDtoOfList(professores);
    }

    public List<ProfessorDTO> getDtoOfList(List<Professor> professorList){
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        professorList.forEach(professor -> professorDTOList.add(ProfessorDTO.of(professor)));

        return professorDTOList;
    }
}
