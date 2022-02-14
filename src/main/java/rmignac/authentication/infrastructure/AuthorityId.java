package rmignac.authentication.infrastructure;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AuthorityId implements Serializable {

    public static final String AUTHORITY_USER = "USER";

    @Column(name = "id")
    private UUID uuid;
    @Column(name = "authority")
    private String authority;

    public AuthorityId() {
    }

    public AuthorityId(UUID uuid, String authority) {
        this.uuid = uuid;
        this.authority = authority;
    }

    public static AuthorityId getDefaultAuthority(UUID id) {
        return new AuthorityId(id, AUTHORITY_USER);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityId that = (AuthorityId) o;
        return uuid.equals(that.uuid) && authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, authority);
    }
}