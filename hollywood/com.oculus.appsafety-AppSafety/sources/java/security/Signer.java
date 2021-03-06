package java.security;

@Deprecated
public abstract class Signer extends Identity {
    private static final long serialVersionUID = -1763464102261361480L;
    private PrivateKey privateKey;

    protected Signer() {
    }

    public Signer(String name) {
        super(name);
    }

    public Signer(String name, IdentityScope scope) throws KeyManagementException {
        super(name, scope);
    }

    public PrivateKey getPrivateKey() {
        check("getSignerPrivateKey");
        return this.privateKey;
    }

    public final void setKeyPair(KeyPair pair) throws InvalidParameterException, KeyException {
        check("setSignerKeyPair");
        final PublicKey pub = pair.getPublic();
        PrivateKey priv = pair.getPrivate();
        if (pub == null || priv == null) {
            throw new InvalidParameterException();
        }
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                /* class java.security.Signer.AnonymousClass1 */

                @Override // java.security.PrivilegedExceptionAction
                public Void run() throws KeyManagementException {
                    Signer.this.setPublicKey(pub);
                    return null;
                }
            });
            this.privateKey = priv;
        } catch (PrivilegedActionException pae) {
            throw ((KeyManagementException) pae.getException());
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.security.Identity
    public String printKeys() {
        if (getPublicKey() == null || this.privateKey == null) {
            return "\tno keys";
        }
        return "\tpublic and private keys initialized";
    }

    @Override // java.security.Identity, java.security.Principal
    public String toString() {
        return "[Signer]" + super.toString();
    }

    private static void check(String directive) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkSecurityAccess(directive);
        }
    }
}
