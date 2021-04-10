package sun.security.x509;

import sun.security.util.DerOutputStream;

public class ReasonFlags {
    private static final String[] NAMES = {"unused", "key_compromise", "ca_compromise", "affiliation_changed", "superseded", "cessation_of_operation", "certificate_hold", "privilege_withdrawn", "aa_compromise"};

    public void encode(DerOutputStream derOutputStream) {
        throw null;
    }
}
