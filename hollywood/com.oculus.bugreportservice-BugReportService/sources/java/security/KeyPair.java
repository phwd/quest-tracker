package java.security;

import java.io.Serializable;

public final class KeyPair implements Serializable {
    private static final long serialVersionUID = -7565189502268009837L;
    private PrivateKey privateKey;
    private PublicKey publicKey;
}
