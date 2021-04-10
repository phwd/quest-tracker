package java.util.jar;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import sun.misc.JarIndex;
import sun.security.util.ManifestEntryVerifier;

public class JarInputStream extends ZipInputStream {
    private final boolean doVerify;
    private JarEntry first;
    private JarVerifier jv;
    private Manifest man;
    private ManifestEntryVerifier mev;
    private boolean tryManifest;

    public JarInputStream(InputStream in) throws IOException {
        this(in, true);
    }

    public JarInputStream(InputStream in, boolean verify) throws IOException {
        super(in);
        this.doVerify = verify;
        JarEntry e = (JarEntry) super.getNextEntry();
        if (e != null && e.getName().equalsIgnoreCase("META-INF/")) {
            e = (JarEntry) super.getNextEntry();
        }
        this.first = checkManifest(e);
    }

    private JarEntry checkManifest(JarEntry e) throws IOException {
        if (e == null || !JarFile.MANIFEST_NAME.equalsIgnoreCase(e.getName())) {
            return e;
        }
        this.man = new Manifest();
        byte[] bytes = getBytes(new BufferedInputStream(this));
        this.man.read(new ByteArrayInputStream(bytes));
        closeEntry();
        if (this.doVerify) {
            this.jv = new JarVerifier(bytes);
            this.mev = new ManifestEntryVerifier(this.man);
        }
        return (JarEntry) super.getNextEntry();
    }

    private byte[] getBytes(InputStream is) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
        while (true) {
            int n = is.read(buffer, 0, buffer.length);
            if (n == -1) {
                return baos.toByteArray();
            }
            baos.write(buffer, 0, n);
        }
    }

    public Manifest getManifest() {
        return this.man;
    }

    @Override // java.util.zip.ZipInputStream
    public ZipEntry getNextEntry() throws IOException {
        JarEntry e;
        JarEntry jarEntry = this.first;
        if (jarEntry == null) {
            e = (JarEntry) super.getNextEntry();
            if (this.tryManifest) {
                e = checkManifest(e);
                this.tryManifest = false;
            }
        } else {
            JarEntry e2 = this.first;
            if (jarEntry.getName().equalsIgnoreCase(JarIndex.INDEX_NAME)) {
                this.tryManifest = true;
            }
            this.first = null;
            e = e2;
        }
        JarVerifier jarVerifier = this.jv;
        if (!(jarVerifier == null || e == null)) {
            if (jarVerifier.nothingToVerify()) {
                this.jv = null;
                this.mev = null;
            } else {
                this.jv.beginEntry(e, this.mev);
            }
        }
        return e;
    }

    public JarEntry getNextJarEntry() throws IOException {
        return (JarEntry) getNextEntry();
    }

    @Override // java.io.FilterInputStream, java.util.zip.ZipInputStream, java.util.zip.InflaterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int n;
        if (this.first == null) {
            n = super.read(b, off, len);
        } else {
            n = -1;
        }
        JarVerifier jarVerifier = this.jv;
        if (jarVerifier != null) {
            jarVerifier.update(n, b, off, len, this.mev);
        }
        return n;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.zip.ZipInputStream
    public ZipEntry createZipEntry(String name) {
        JarEntry e = new JarEntry(name);
        Manifest manifest = this.man;
        if (manifest != null) {
            e.attr = manifest.getAttributes(name);
        }
        return e;
    }
}
