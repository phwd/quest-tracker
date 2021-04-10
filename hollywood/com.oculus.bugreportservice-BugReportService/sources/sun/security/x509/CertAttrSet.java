package sun.security.x509;

import java.io.OutputStream;

public interface CertAttrSet {
    void encode(OutputStream outputStream);

    String getName();
}
