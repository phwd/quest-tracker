package java.util.jar;

import java.io.IOException;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.zip.ZipEntry;

public class JarEntry extends ZipEntry {
    Attributes attr;
    Certificate[] certs;
    CodeSigner[] signers;

    public JarEntry(String name) {
        super(name);
    }

    public JarEntry(ZipEntry ze) {
        super(ze);
    }

    public JarEntry(JarEntry je) {
        this((ZipEntry) je);
        this.attr = je.attr;
        this.certs = je.certs;
        this.signers = je.signers;
    }

    public Attributes getAttributes() throws IOException {
        return this.attr;
    }

    public Certificate[] getCertificates() {
        Certificate[] certificateArr = this.certs;
        if (certificateArr == null) {
            return null;
        }
        return (Certificate[]) certificateArr.clone();
    }

    public CodeSigner[] getCodeSigners() {
        CodeSigner[] codeSignerArr = this.signers;
        if (codeSignerArr == null) {
            return null;
        }
        return (CodeSigner[]) codeSignerArr.clone();
    }
}
