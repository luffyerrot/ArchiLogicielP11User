package fr.pierre.medheadUser.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "info")
public class InfoUtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_utilisateur_generator")
    @SequenceGenerator(name = "info_utilisateur_generator", sequenceName = "info_utilisateur_seq", allocationSize = 1)
    private int id;
    private int utilisateur_id;

    private String prenom;
    private String nom;
    private LocalDate dateDeNaissance;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "infoUtilisateurEntity", cascade = CascadeType.ALL)
    private UtilisateurEntity utilisateur;
}
