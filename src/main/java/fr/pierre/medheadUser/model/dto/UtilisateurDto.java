package fr.pierre.medheadUser.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class UtilisateurDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7660400037406233187L;

    private String mail;
    private String motdepasse;
}
