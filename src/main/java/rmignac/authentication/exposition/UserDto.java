package rmignac.authentication.exposition;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class UserDto {

    private UUID id;

    private String username;

    Collection<AuthorityDto> authorities;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<AuthorityDto> authorities) {
        this.authorities = authorities;
    }
}
