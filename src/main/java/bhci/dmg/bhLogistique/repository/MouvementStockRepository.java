package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock,Long> {
}
