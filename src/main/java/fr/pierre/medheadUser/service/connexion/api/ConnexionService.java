package fr.pierre.medheadUser.service.connexion.api;

import fr.pierre.medheadUser.model.dto.UtilisateurDto;
import fr.pierre.medheadUser.model.entity.UtilisateurEntity;

public interface ConnexionService {

    UtilisateurEntity register(UtilisateurDto input);

    UtilisateurEntity authenticate(UtilisateurDto input);
}
