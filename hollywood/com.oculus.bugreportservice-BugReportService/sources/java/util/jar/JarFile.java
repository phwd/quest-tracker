package java.util.jar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.jar.JarVerifier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import sun.security.util.Debug;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;

public class JarFile extends ZipFile {
    private static final char[] CLASSPATH_CHARS = {'c', 'l', 'a', 's', 's', '-', 'p', 'a', 't', 'h'};
    private static final int[] CLASSPATH_LASTOCC = new int[128];
    private static final int[] CLASSPATH_OPTOSFT = new int[10];
    private JarVerifier jv;
    private boolean jvInitialized;
    private JarEntry manEntry;
    private Manifest manifest;
    private boolean verify;

    private native String[] getMetaInfEntryNames();

    public JarFile(String str) {
        this(new File(str), true, 1);
    }

    public JarFile(File file, boolean z, int i) {
        super(file, i);
        this.verify = z;
    }

    public Manifest getManifest() {
        return getManifestFromReference();
    }

    private synchronized Manifest getManifestFromReference() {
        Manifest manifest2;
        JarEntry manEntry2;
        manifest2 = this.manifest;
        if (manifest2 == null && (manEntry2 = getManEntry()) != null) {
            if (this.verify) {
                byte[] bytes = getBytes(manEntry2);
                Manifest manifest3 = new Manifest(new ByteArrayInputStream(bytes));
                if (!this.jvInitialized) {
                    this.jv = new JarVerifier(bytes);
                }
                manifest2 = manifest3;
            } else {
                manifest2 = new Manifest(super.getInputStream(manEntry2));
            }
            this.manifest = manifest2;
        }
        return manifest2;
    }

    public JarEntry getJarEntry(String str) {
        return (JarEntry) getEntry(str);
    }

    @Override // java.util.zip.ZipFile
    public ZipEntry getEntry(String str) {
        ZipEntry entry = super.getEntry(str);
        if (entry != null) {
            return new JarFileEntry(entry);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public class JarEntryIterator implements Enumeration, Iterator {
        final Enumeration e;

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
    public Enumeration entries() {
        return new JarEntryIterator();
    }

    /* access modifiers changed from: private */
    public class JarFileEntry extends JarEntry {
        JarFileEntry(ZipEntry zipEntry) {
            super(zipEntry);
        }
    }

    private void maybeInstantiateVerifier() {
        if (this.jv == null && this.verify) {
            String[] metaInfEntryNames = getMetaInfEntryNames();
            if (metaInfEntryNames != null) {
                for (String str : metaInfEntryNames) {
                    String upperCase = str.toUpperCase(Locale.ENGLISH);
                    if (upperCase.endsWith(".DSA") || upperCase.endsWith(".RSA") || upperCase.endsWith(".EC") || upperCase.endsWith(".SF")) {
                        getManifest();
                        return;
                    }
                }
            }
            this.verify = false;
        }
    }

    private void initializeVerifier() {
        try {
            String[] metaInfEntryNames = getMetaInfEntryNames();
            if (metaInfEntryNames != null) {
                ManifestEntryVerifier manifestEntryVerifier = null;
                for (int i = 0; i < metaInfEntryNames.length; i++) {
                    String upperCase = metaInfEntryNames[i].toUpperCase(Locale.ENGLISH);
                    if ("META-INF/MANIFEST.MF".equals(upperCase) || SignatureFileVerifier.isBlockOrSF(upperCase)) {
                        JarEntry jarEntry = getJarEntry(metaInfEntryNames[i]);
                        if (jarEntry != null) {
                            if (manifestEntryVerifier == null) {
                                manifestEntryVerifier = new ManifestEntryVerifier(getManifestFromReference());
                            }
                            byte[] bytes = getBytes(jarEntry);
                            if (bytes != null && bytes.length > 0) {
                                this.jv.beginEntry(jarEntry, manifestEntryVerifier);
                                this.jv.update(bytes.length, bytes, 0, bytes.length, manifestEntryVerifier);
                                this.jv.update(-1, null, 0, 0, manifestEntryVerifier);
                            }
                            manifestEntryVerifier = manifestEntryVerifier;
                        } else {
                            throw new JarException("corrupted jar file");
                        }
                    }
                }
            }
        } catch (IOException e) {
            this.jv = null;
            this.verify = false;
            Debug debug = JarVerifier.debug;
            if (debug != null) {
                debug.println("jarfile parsing error!");
                e.printStackTrace();
            }
        }
        JarVerifier jarVerifier = this.jv;
        if (jarVerifier != null) {
            jarVerifier.doneWithMeta();
            Debug debug2 = JarVerifier.debug;
            if (debug2 != null) {
                debug2.println("done with meta!");
            }
            if (this.jv.nothingToVerify()) {
                Debug debug3 = JarVerifier.debug;
                if (debug3 != null) {
                    debug3.println("nothing to verify!");
                }
                this.jv = null;
                this.verify = false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r2 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] getBytes(java.util.zip.ZipEntry r3) {
        /*
            r2 = this;
            java.io.InputStream r2 = super.getInputStream(r3)
            long r0 = r3.getSize()     // Catch:{ all -> 0x0014 }
            int r3 = (int) r0     // Catch:{ all -> 0x0014 }
            r0 = 1
            byte[] r3 = sun.misc.IOUtils.readFully(r2, r3, r0)     // Catch:{ all -> 0x0014 }
            if (r2 == 0) goto L_0x0013
            r2.close()
        L_0x0013:
            return r3
        L_0x0014:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r0 = move-exception
            if (r2 == 0) goto L_0x0021
            r2.close()     // Catch:{ all -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0021:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.jar.JarFile.getBytes(java.util.zip.ZipEntry):byte[]");
    }

    @Override // java.util.zip.ZipFile
    public synchronized InputStream getInputStream(ZipEntry zipEntry) {
        maybeInstantiateVerifier();
        if (this.jv == null) {
            return super.getInputStream(zipEntry);
        }
        if (!this.jvInitialized) {
            initializeVerifier();
            this.jvInitialized = true;
            if (this.jv == null) {
                return super.getInputStream(zipEntry);
            }
        }
        return new JarVerifier.VerifierStream(getManifestFromReference(), zipEntry instanceof JarFileEntry ? (JarEntry) zipEntry : getJarEntry(zipEntry.getName()), super.getInputStream(zipEntry), this.jv);
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
        String[] metaInfEntryNames;
        if (this.manEntry == null) {
            this.manEntry = getJarEntry("META-INF/MANIFEST.MF");
            if (this.manEntry == null && (metaInfEntryNames = getMetaInfEntryNames()) != null) {
                int i = 0;
                while (true) {
                    if (i >= metaInfEntryNames.length) {
                        break;
                    } else if ("META-INF/MANIFEST.MF".equals(metaInfEntryNames[i].toUpperCase(Locale.ENGLISH))) {
                        this.manEntry = getJarEntry(metaInfEntryNames[i]);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        return this.manEntry;
    }
}
