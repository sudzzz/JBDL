package com.example.L2324MajorProjectEWallet.model;

import com.example.L2324MajorProjectEWallet.enums.UserIdentifierEnum;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable, UserDetails {

    private static final String AUTHORITY_DELIMITER=":";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true,nullable = false)
    private String phoneNumber;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String dob;
    @Enumerated(value = EnumType.STRING)
    private UserIdentifierEnum userIdentifier;
    @Column(unique = true)
    private String userIdentifierValue;

    private String country;
    private String authorities;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] allAuthorities = this.authorities.split(AUTHORITY_DELIMITER);
        return Arrays.stream(allAuthorities)
                .map(x-> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
