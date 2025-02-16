package fr.pierre.medheadUser.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class UtilisateurEntity implements UserDetails {

    @Serial
    private static final long serialVersionUID = -824248866373589293L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_generator")
    @SequenceGenerator(name = "utilisateur_generator", sequenceName = "utilisateur_seq", allocationSize = 1)
    private int id;

    private String mail;
    private String motDePasse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "utilisateur_id")
    private InfoUtilisateurEntity infoUtilisateurEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
