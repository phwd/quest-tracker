package sun.security.x509;

import sun.security.util.DerOutputStream;

public interface GeneralNameInterface {
    int constrains(GeneralNameInterface generalNameInterface);

    void encode(DerOutputStream derOutputStream);

    int getType();
}
