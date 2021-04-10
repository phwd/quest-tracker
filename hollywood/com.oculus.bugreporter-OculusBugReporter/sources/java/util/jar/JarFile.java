package java.util.jar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Spliterators;
import java.util.jar.JarVerifier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;

public class JarFile extends ZipFile {
    private static final char[] CLASSPATH_CHARS = {'c', 'l', 'a', 's', 's', '-', 'p', 'a', 't', 'h'};
    private static final int[] CLASSPATH_LASTOCC = new int[128];
    private static final int[] CLASSPATH_OPTOSFT = new int[10];
    public static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
    private volatile boolean hasCheckedSpecialAttributes;
    private boolean hasClassPathAttribute;
    private JarVerifier jv;
    private boolean jvInitialized;
    private JarEntry manEntry;
    private Manifest manifest;
    private boolean verify;

    private native String[] getMetaInfEntryNames();

    public JarFile(String name) throws IOException {
        this(new File(name), true, 1);
    }

    public JarFile(String name, boolean verify2) throws IOException {
        this(new File(name), verify2, 1);
    }

    public JarFile(File file) throws IOException {
        this(file, true, 1);
    }

    public JarFile(File file, boolean verify2) throws IOException {
        this(file, verify2, 1);
    }

    public JarFile(File file, boolean verify2, int mode) throws IOException {
        super(file, mode);
        this.verify = verify2;
    }

    public Manifest getManifest() throws IOException {
        return getManifestFromReference();
    }

    private synchronized Manifest getManifestFromReference() throws IOException {
        Manifest man;
        JarEntry manEntry2;
        man = this.manifest;
        if (man == null && (manEntry2 = getManEntry()) != null) {
            if (this.verify) {
                byte[] b = getBytes(manEntry2);
                man = new Manifest(new ByteArrayInputStream(b));
                if (!this.jvInitialized) {
                    this.jv = new JarVerifier(b);
                }
            } else {
                man = new Manifest(super.getInputStream(manEntry2));
            }
            this.manifest = man;
        }
        return man;
    }

    public JarEntry getJarEntry(String name) {
        return (JarEntry) getEntry(name);
    }

    @Override // java.util.zip.ZipFile
    public ZipEntry getEntry(String name) {
        ZipEntry ze = super.getEntry(name);
        if (ze != null) {
            return new JarFileEntry(ze);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public class JarEntryIterator implements Enumeration<JarEntry>, Iterator<JarEntry> {
        final Enumeration<? extends ZipEntry> e;

        private JarEntryIterator() {
            this.e = JarFile.super.entries();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.e.hasMoreElements();
        }

        @Override // java.util.Iterator
        public JarEntry next() {
            return new JarFileEntry((ZipEntry) this.e.nextElement());
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return hasNext();
        }

        @Override // java.util.Enumeration
        public JarEntry nextElement() {
            return next();
        }
    }

    @Override // java.util.zip.ZipFile
    public Enumeration<JarEntry> entries() {
        return new JarEntryIterator();
    }

    @Override // java.util.zip.ZipFile
    public Stream<JarEntry> stream() {
        return StreamSupport.stream(Spliterators.spliterator(new JarEntryIterator(), (long) size(), 1297), false);
    }

    /* access modifiers changed from: private */
    public class JarFileEntry extends JarEntry {
        JarFileEntry(ZipEntry ze) {
            super(ze);
        }

        @Override // java.util.jar.JarEntry
        public Attributes getAttributes() throws IOException {
            Manifest man = JarFile.this.getManifest();
            if (man != null) {
                return man.getAttributes(getName());
            }
            return null;
        }

        @Override // java.util.jar.JarEntry
        public Certificate[] getCertificates() {
            try {
                JarFile.this.maybeInstantiateVerifier();
                if (this.certs == null && JarFile.this.jv != null) {
                    this.certs = JarFile.this.jv.getCerts(JarFile.this, this);
                }
                if (this.certs == null) {
                    return null;
                }
                return (Certificate[]) this.certs.clone();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // java.util.jar.JarEntry
        public CodeSigner[] getCodeSigners() {
            try {
                JarFile.this.maybeInstantiateVerifier();
                if (this.signers == null && JarFile.this.jv != null) {
                    this.signers = JarFile.this.jv.getCodeSigners(JarFile.this, this);
                }
                if (this.signers == null) {
                    return null;
                }
                return (CodeSigner[]) this.signers.clone();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeInstantiateVerifier() throws IOException {
        if (this.jv == null && this.verify) {
            String[] names = getMetaInfEntryNames();
            if (names != null) {
                for (String str : names) {
                    String name = str.toUpperCase(Locale.ENGLISH);
                    if (name.endsWith(".DSA") || name.endsWith(".RSA") || name.endsWith(".EC") || name.endsWith(".SF")) {
                        getManifest();
                        return;
                    }
                }
            }
            this.verify = false;
        }
    }

    private void initializeVerifier() {
        ManifestEntryVerifier mev = null;
        try {
            String[] names = getMetaInfEntryNames();
            if (names != null) {
                for (int i = 0; i < names.length; i++) {
                    String uname = names[i].toUpperCase(Locale.ENGLISH);
                    if (MANIFEST_NAME.equals(uname) || SignatureFileVerifier.isBlockOrSF(uname)) {
                        JarEntry e = getJarEntry(names[i]);
                        if (e != null) {
                            if (mev == null) {
                                mev = new ManifestEntryVerifier(getManifestFromReference());
                            }
                            byte[] b = getBytes(e);
                            if (b != null && b.length > 0) {
                                this.jv.beginEntry(e, mev);
                                this.jv.update(b.length, b, 0, b.length, mev);
                                this.jv.update(-1, null, 0, 0, mev);
                            }
                        } else {
                            throw new JarException("corrupted jar file");
                        }
                    }
                }
            }
        } catch (IOException ex) {
            this.jv = null;
            this.verify = false;
            if (JarVerifier.debug != null) {
                JarVerifier.debug.println("jarfile parsing error!");
                ex.printStackTrace();
            }
        }
        JarVerifier jarVerifier = this.jv;
        if (jarVerifier != null) {
            jarVerifier.doneWithMeta();
            if (JarVerifier.debug != null) {
                JarVerifier.debug.println("done with meta!");
            }
            if (this.jv.nothingToVerify()) {
                if (JarVerifier.debug != null) {
                    JarVerifier.debug.println("nothing to verify!");
                }
                this.jv = null;
                this.verify = false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r0 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] getBytes(java.util.zip.ZipEntry r5) throws java.io.IOException {
        /*
            r4 = this;
            java.io.InputStream r0 = super.getInputStream(r5)
            long r1 = r5.getSize()     // Catch:{ all -> 0x0014 }
            int r1 = (int) r1     // Catch:{ all -> 0x0014 }
            r2 = 1
            byte[] r1 = sun.misc.IOUtils.readFully(r0, r1, r2)     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x0013
            r0.close()
        L_0x0013:
            return r1
        L_0x0014:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r2 = move-exception
            if (r0 == 0) goto L_0x0021
            r0.close()     // Catch:{ all -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0021:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.jar.JarFile.getBytes(java.util.zip.ZipEntry):byte[]");
    }

    @Override // java.util.zip.ZipFile
    public synchronized InputStream getInputStream(ZipEntry ze) throws IOException {
        maybeInstantiateVerifier();
        if (this.jv == null) {
            return super.getInputStream(ze);
        }
        if (!this.jvInitialized) {
            initializeVerifier();
            this.jvInitialized = true;
            if (this.jv == null) {
                return super.getInputStream(ze);
            }
        }
        return new JarVerifier.VerifierStream(getManifestFromReference(), ze instanceof JarFileEntry ? (JarEntry) ze : getJarEntry(ze.getName()), super.getInputStream(ze), this.jv);
    }

    static {
        int[] iArr = CLASSPATH_LASTOCC;
        iArr[99] = 1;
        iArr[108] = 2;
        iArr[115] = 5;
        iArr[45] = 6;
        iArr[112] = 7;
        iArr[97] = 8;
        iArr[116] = 9;
        iArr[104] = 10;
        for (int i = 0; i < 9; i++) {
            CLASSPATH_OPTOSFT[i] = 10;
        }
        CLASSPATH_OPTOSFT[9] = 1;
    }

    private synchronized JarEntry getManEntry() {
        String[] names;
        if (this.manEntry == null) {
            this.manEntry = getJarEntry(MANIFEST_NAME);
            if (this.manEntry == null && (names = getMetaInfEntryNames()) != null) {
                int i = 0;
                while (true) {
                    if (i >= names.length) {
                        break;
                    } else if (MANIFEST_NAME.equals(names[i].toUpperCase(Locale.ENGLISH))) {
                        this.manEntry = getJarEntry(names[i]);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        return this.manEntry;
    }

    public boolean hasClassPathAttribute() throws IOException {
        checkForSpecialAttributes();
        return this.hasClassPathAttribute;
    }

    private boolean match(char[] src, byte[] b, int[] lastOcc, int[] optoSft) {
        int len = src.length;
        int last = b.length - len;
        int i = 0;
        while (i <= last) {
            for (int j = len - 1; j >= 0; j--) {
                char c = (char) b[i + j];
                char c2 = ((c + 65471) | ('Z' - c)) >= 0 ? (char) (c + ' ') : c;
                if (c2 != src[j]) {
                    i += Math.max((j + 1) - lastOcc[c2 & 127], optoSft[j]);
                }
            }
            return true;
        }
        return false;
    }

    private void checkForSpecialAttributes() throws IOException {
        if (!this.hasCheckedSpecialAttributes) {
            JarEntry manEntry2 = getManEntry();
            if (manEntry2 != null) {
                if (match(CLASSPATH_CHARS, getBytes(manEntry2), CLASSPATH_LASTOCC, CLASSPATH_OPTOSFT)) {
                    this.hasClassPathAttribute = true;
                }
            }
            this.hasCheckedSpecialAttributes = true;
        }
    }

    /* access modifiers changed from: package-private */
    public JarEntry newEntry(ZipEntry ze) {
        return new JarFileEntry(ze);
    }
}
