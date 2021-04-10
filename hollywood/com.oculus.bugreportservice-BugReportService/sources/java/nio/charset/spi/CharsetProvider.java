package java.nio.charset.spi;

import java.nio.charset.Charset;

public abstract class CharsetProvider {
    public abstract Charset charsetForName(String str);

    protected CharsetProvider() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("charsetProvider"));
            throw null;
        }
    }
}
