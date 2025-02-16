package fr.pierre.medheadUser.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InformationConnexion {

    private String token;
    private long expiresDans;
    private String mail;
}
