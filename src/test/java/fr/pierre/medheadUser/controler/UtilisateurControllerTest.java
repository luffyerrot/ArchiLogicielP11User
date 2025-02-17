package fr.pierre.medheadUser.controler;

import fr.pierre.medheadUser.controller.UtilisateurController;
import fr.pierre.medheadUser.model.dto.InformationConnexion;
import fr.pierre.medheadUser.model.dto.UtilisateurDto;
import fr.pierre.medheadUser.model.entity.UtilisateurEntity;
import fr.pierre.medheadUser.service.connexion.api.ConnexionService;
import fr.pierre.medheadUser.service.jwtoken.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UtilisateurControllerTest {

    @Mock
    private JwtService jwtService;
    @Mock
    private ConnexionService connexionService;
    @InjectMocks
    private UtilisateurController utilisateurController;

    @Test
    public void inscription_expectToBeOk_ReturnUtilisateurEntity() {
        // Given
        final String MAIL = "mail@test.fr";
        final String MOT_DE_PASSE = "motdepasse";
        final UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .mail(MAIL)
                .motdepasse(MOT_DE_PASSE)
                .build();
        final UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(1);
        utilisateurEntity.setMail(MAIL);
        utilisateurEntity.setMotDePasse(MOT_DE_PASSE);
        Mockito
                .when(connexionService.register(utilisateurDto))
                .thenReturn(utilisateurEntity);

        // When
        ResponseEntity<UtilisateurEntity> response = utilisateurController.inscription(utilisateurDto);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().getId());
        Assertions.assertEquals(MAIL, response.getBody().getMail());
        Assertions.assertEquals(MOT_DE_PASSE, response.getBody().getMotDePasse());
    }

    @Test
    public void connexion_expectToBeOk_ReturnInformationConnexion() {
        // Given
        final String MAIL = "mail@test.fr";
        final String MOT_DE_PASSE = "motdepasse";
        final String TOKEN = "token";
        final UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .mail(MAIL)
                .motdepasse(MOT_DE_PASSE)
                .build();
        final UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(1);
        utilisateurEntity.setMail(MAIL);
        utilisateurEntity.setMotDePasse(MOT_DE_PASSE);
        Mockito
                .when(connexionService.authenticate(utilisateurDto))
                .thenReturn(utilisateurEntity);
        Mockito
                .when(jwtService.generateToken(utilisateurEntity))
                .thenReturn(TOKEN);

        // When
        ResponseEntity<InformationConnexion> response = utilisateurController.connexion(utilisateurDto);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(MAIL, response.getBody().getMail());
        Assertions.assertEquals(TOKEN, response.getBody().getToken());
    }
}
