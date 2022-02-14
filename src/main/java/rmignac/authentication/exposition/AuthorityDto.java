package rmignac.authentication.exposition;

import java.util.UUID;

public class AuthorityDto {

    private UUID uuid;
    private String authority;

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
}
