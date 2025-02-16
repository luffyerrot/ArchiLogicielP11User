package fr.pierre.medheadUser.repository;

import fr.pierre.medheadUser.model.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {

    Optional<UtilisateurEntity> findByMail(String mail);
}
