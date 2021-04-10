package javax.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;
import javax.crypto.CryptoPolicyParser;

/* access modifiers changed from: package-private */
public final class CryptoPermissions extends PermissionCollection implements Serializable {
    CryptoPermissions() {
    }

    /* access modifiers changed from: package-private */
    public void load(InputStream in) throws IOException, CryptoPolicyParser.ParsingException {
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return true;
    }

    @Override // java.security.PermissionCollection
    public void add(Permission permission) {
    }

    @Override // java.security.PermissionCollection
    public boolean implies(Permission permission) {
        return true;
    }

    @Override // java.security.PermissionCollection
    public Enumeration elements() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public CryptoPermissions getMinimum(CryptoPermissions other) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public PermissionCollection getPermissionCollection(String alg) {
        return null;
    }
}
