package PetProject.GenealogyProject.services;

import PetProject.GenealogyProject.models.Village;
import PetProject.GenealogyProject.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VillageService {
    private final VillageRepository villageRepository;

    @Autowired
    public VillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public List<Village> findAll() {
        return villageRepository.findAll();
    }

    public Village findOne(int id) {
        Optional<Village> foundVillage = villageRepository.findById(id);
        return foundVillage.orElse(null);
    }

    @Transactional
    public void save(Village village) {
        villageRepository.save(village);
    }

    @Transactional
    public void update(int id, Village updatedVillage) {
        updatedVillage.setId(id);
        villageRepository.save(updatedVillage);
    }

    @Transactional
    public void delete(int id) {
        villageRepository.deleteById(id);
    }
}




