package bhci.dmg.bhLogistique;

import bhci.dmg.bhLogistique.dao.Famille;
import bhci.dmg.bhLogistique.repository.FamilleRepository;
import bhci.dmg.bhLogistique.services.FamilleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    FamilleRepository repository;

    @Autowired
    FamilleService service;

    @BeforeEach
    public void delete_all_Familles() {
        repository.deleteAll();

    }

    @Test
    public void should_find_no_Familles_if_repository_is_empty() {
        Iterable<Famille> Familles = repository.findAll();

        assertThat(Familles).isEmpty();
    }

    @Test
    public void should_store_a_Famille() {
        Famille famille = repository.save(new Famille("Famille 1", "FAM1"));

        assertThat(famille).hasFieldOrPropertyWithValue("libelleFamille", "Famille 1")
                .hasFieldOrPropertyWithValue("codeFamille", "FAM1");
    }

    @Test
//    @Order(3)
    public void should_find_all_Familles() {
        Famille tut1 = new Famille("Famille 1", "FAM1");
        entityManager.persist(tut1);

        Famille tut2 = new Famille("Famille 2", "FAM2");
        entityManager.persist(tut2);

        Famille tut3 = new Famille("Famille 3", "FAM3");
        entityManager.persist(tut3);

        Iterable<Famille> Familles = repository.findAll();

        assertThat(Familles).hasSize(3).contains(tut1, tut2, tut3);
    }

    @Test
//    @Order(4)
    public void should_find_Famille_by_id() {
        Famille tut1 = new Famille("Famille 1", "FAM1");
        entityManager.persist(tut1);

        Famille tut2 = new Famille("Famille 2", "FAM2");
        entityManager.persist(tut2);

        Famille foundFamille = repository.findById(tut2.getIdFamille()).get();

        assertThat(foundFamille).isEqualTo(tut2);
    }



    @Test
    public void should_find_Familles_by_code() {
        Famille tut1 = new Famille("test Familles 1 de", "FAM1");
        entityManager.persist(tut1);

        Famille tut2 = new Famille("test Familles 2 de", "FAM2");
        entityManager.persist(tut2);

        Famille tut3 = new Famille("test Famille 3 de", "FAM3");
        entityManager.persist(tut3);

        Famille foundFamille = service.getFamilleByCode(tut1.getCodeFamille());

        assertThat(foundFamille).isEqualTo(tut1);
    }

    @Test
//    @Order(5)
    public void should_update_Famille_by_id() {
        Famille tut1 = new Famille("Famille 1", "FAM1");
        entityManager.persist(tut1);

        Famille tut2 = new Famille("Famille 2", "FAM2");
        entityManager.persist(tut2);

        Famille updatedFam = new Famille("Famille 1", "FAM1");

        Famille tut = repository.findById(tut2.getIdFamille()).get();
        tut.setLibelleFamille(updatedFam.getLibelleFamille());
        tut.setCodeFamille(updatedFam.getCodeFamille());
        repository.save(tut);

        Famille checkFam = repository.findById(tut2.getIdFamille()).get();

        assertThat(checkFam.getIdFamille()).isEqualTo(tut2.getIdFamille());
        assertThat(checkFam.getLibelleFamille()).isEqualTo(updatedFam.getLibelleFamille());
        assertThat(checkFam.getCodeFamille()).isEqualTo(updatedFam.getCodeFamille());
    }

    @Test
//    @Order(6)
    public void should_delete_Famille_by_id() {
        Famille tut1 = new Famille("Famille 1", "FAM1");
        entityManager.persist(tut1);

        Famille tut2 = new Famille("Famille 2", "FAM2");
        entityManager.persist(tut2);

        Famille tut3 = new Famille("Famille 3", "FAM3");
        entityManager.persist(tut3);

        repository.deleteById(tut2.getIdFamille());

        Iterable<Famille> Familles = repository.findAll();

        assertThat(Familles).hasSize(2).contains(tut1, tut3);
    }

    @Test
//    @Order(7)
    public void should_delete_all_Familles() {
        entityManager.persist(new Famille("Famille 1", "FAM1"));
        entityManager.persist(new Famille("Famille 2", "FAM2"));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}
