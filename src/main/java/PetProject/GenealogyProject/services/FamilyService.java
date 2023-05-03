package PetProject.GenealogyProject.services;

import PetProject.GenealogyProject.models.Family;
import PetProject.GenealogyProject.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FamilyService {
    private final FamilyRepository familyRepository;

    @Autowired
    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public List<Family> findAll() {
        return familyRepository.findAll();
    }

    public Family findOne(int id) {
        Optional<Family> foundFamily = familyRepository.findById(id);
        return foundFamily.orElse(null);
    }

    @Transactional
    public void save(Family family) {
        familyRepository.save(family);
    }

    @Transactional
    public void update(int id, Family updatedFamily) {
        updatedFamily.setId(id);
        familyRepository.save(updatedFamily);
    }

    @Transactional
    public void delete(int id) {
        familyRepository.deleteById(id);
    }
}

