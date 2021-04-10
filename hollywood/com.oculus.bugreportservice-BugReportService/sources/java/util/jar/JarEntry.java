package java.util.jar;

import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.zip.ZipEntry;

public class JarEntry extends ZipEntry {
    Attributes attr;
    Certificate[] certs;
    CodeSigner[] signers;

    public JarEntry(ZipEntry zipEntry) {
        super(zipEntry);
    }

    public JarEntry(JarEntry jarEntry) {
        this((ZipEntry) jarEntry);
        this.attr = jarEntry.attr;
        this.certs = jarEntry.certs;
        this.signers = jarEntry.signers;
    }
}
