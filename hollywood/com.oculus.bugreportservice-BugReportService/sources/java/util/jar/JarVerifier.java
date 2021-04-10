package java.util.jar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSigner;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import sun.security.util.Debug;
import sun.security.util.ManifestDigester;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;

/* access modifiers changed from: package-private */
public class JarVerifier {
    static final Debug debug = Debug.getInstance("jar");
    private boolean anyToVerify = true;
    private ByteArrayOutputStream baos;
    private Object csdomain = new Object();
    private Enumeration emptyEnumeration = new Enumeration() {
        /* class java.util.jar.JarVerifier.AnonymousClass3 */

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public String nextElement() {
            throw new NoSuchElementException();
        }
    };
    private CodeSigner[] emptySigner = new CodeSigner[0];
    private volatile ManifestDigester manDig;
    private List manifestDigests;
    byte[] manifestRawBytes = null;
    private boolean parsingBlockOrSF = false;
    private boolean parsingMeta = true;
    private ArrayList pendingBlocks;
    private Hashtable sigFileData;
    private Hashtable sigFileSigners;
    private ArrayList signerCache;
    private Map signerToCodeSource = new HashMap();
    private Map urlToCodeSourceMap = new HashMap();
    private Hashtable verifiedSigners;

    public JarVerifier(byte[] bArr) {
        this.manifestRawBytes = bArr;
        this.sigFileSigners = new Hashtable();
        this.verifiedSigners = new Hashtable();
        this.sigFileData = new Hashtable(11);
        this.pendingBlocks = new ArrayList();
        this.baos = new ByteArrayOutputStream();
        this.manifestDigests = new ArrayList();
    }

    public void beginEntry(JarEntry jarEntry, ManifestEntryVerifier manifestEntryVerifier) {
        if (jarEntry != null) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("beginEntry " + jarEntry.getName());
            }
            String name = jarEntry.getName();
            if (this.parsingMeta) {
                String upperCase = name.toUpperCase(Locale.ENGLISH);
                if (upperCase.startsWith("META-INF/") || upperCase.startsWith("/META-INF/")) {
                    if (jarEntry.isDirectory()) {
                        manifestEntryVerifier.setEntry(null, jarEntry);
                        return;
                    } else if (!upperCase.equals("META-INF/MANIFEST.MF") && !upperCase.equals("META-INF/INDEX.LIST")) {
                        if (SignatureFileVerifier.isBlockOrSF(upperCase)) {
                            this.parsingBlockOrSF = true;
                            this.baos.reset();
                            manifestEntryVerifier.setEntry(null, jarEntry);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            if (this.parsingMeta) {
                doneWithMeta();
            }
            if (jarEntry.isDirectory()) {
                manifestEntryVerifier.setEntry(null, jarEntry);
                return;
            }
            if (name.startsWith("./")) {
                name = name.substring(2);
            }
            if (name.startsWith("/")) {
                name = name.substring(1);
            }
            if (this.sigFileSigners.get(name) == null && this.verifiedSigners.get(name) == null) {
                manifestEntryVerifier.setEntry(null, jarEntry);
            } else {
                manifestEntryVerifier.setEntry(name, jarEntry);
            }
        }
    }

    public void update(int i, ManifestEntryVerifier manifestEntryVerifier) {
        if (i == -1) {
            processEntry(manifestEntryVerifier);
        } else if (this.parsingBlockOrSF) {
            this.baos.write(i);
        } else {
            manifestEntryVerifier.update((byte) i);
        }
    }

    public void update(int i, byte[] bArr, int i2, int i3, ManifestEntryVerifier manifestEntryVerifier) {
        if (i == -1) {
            processEntry(manifestEntryVerifier);
        } else if (this.parsingBlockOrSF) {
            this.baos.write(bArr, i2, i);
        } else {
            manifestEntryVerifier.update(bArr, i2, i);
        }
    }

    private void processEntry(ManifestEntryVerifier manifestEntryVerifier) {
        if (!this.parsingBlockOrSF) {
            JarEntry entry = manifestEntryVerifier.getEntry();
            if (entry != null && entry.signers == null) {
                entry.signers = manifestEntryVerifier.verify(this.verifiedSigners, this.sigFileSigners);
                entry.certs = mapSignersToCertArray(entry.signers);
                return;
            }
            return;
        }
        try {
            this.parsingBlockOrSF = false;
            if (debug != null) {
                debug.println("processEntry: processing block");
            }
            String upperCase = manifestEntryVerifier.getEntry().getName().toUpperCase(Locale.ENGLISH);
            if (upperCase.endsWith(".SF")) {
                String substring = upperCase.substring(0, upperCase.length() - 3);
                byte[] byteArray = this.baos.toByteArray();
                this.sigFileData.put(substring, byteArray);
                Iterator it = this.pendingBlocks.iterator();
                while (it.hasNext()) {
                    SignatureFileVerifier signatureFileVerifier = (SignatureFileVerifier) it.next();
                    if (signatureFileVerifier.needSignatureFile(substring)) {
                        if (debug != null) {
                            debug.println("processEntry: processing pending block");
                        }
                        signatureFileVerifier.setSignatureFile(byteArray);
                        signatureFileVerifier.process(this.sigFileSigners, this.manifestDigests);
                    }
                }
                return;
            }
            String substring2 = upperCase.substring(0, upperCase.lastIndexOf("."));
            if (this.signerCache == null) {
                this.signerCache = new ArrayList();
            }
            if (this.manDig == null) {
                synchronized (this.manifestRawBytes) {
                    if (this.manDig == null) {
                        this.manDig = new ManifestDigester(this.manifestRawBytes);
                        this.manifestRawBytes = null;
                    }
                }
            }
            SignatureFileVerifier signatureFileVerifier2 = new SignatureFileVerifier(this.signerCache, this.manDig, upperCase, this.baos.toByteArray());
            if (signatureFileVerifier2.needSignatureFileBytes()) {
                byte[] bArr = (byte[]) this.sigFileData.get(substring2);
                if (bArr == null) {
                    if (debug != null) {
                        debug.println("adding pending block");
                    }
                    this.pendingBlocks.add(signatureFileVerifier2);
                    return;
                }
                signatureFileVerifier2.setSignatureFile(bArr);
            }
            signatureFileVerifier2.process(this.sigFileSigners, this.manifestDigests);
        } catch (IOException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("processEntry caught: " + e);
            }
        } catch (SignatureException e2) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("processEntry caught: " + e2);
            }
        } catch (NoSuchAlgorithmException e3) {
            Debug debug4 = debug;
            if (debug4 != null) {
                debug4.println("processEntry caught: " + e3);
            }
        } catch (CertificateException e4) {
            Debug debug5 = debug;
            if (debug5 != null) {
                debug5.println("processEntry caught: " + e4);
            }
        }
    }

    private static Certificate[] mapSignersToCertArray(CodeSigner[] codeSignerArr) {
        if (codeSignerArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (CodeSigner codeSigner : codeSignerArr) {
            arrayList.addAll(codeSigner.getSignerCertPath().getCertificates());
        }
        return (Certificate[]) arrayList.toArray(new Certificate[arrayList.size()]);
    }

    /* access modifiers changed from: package-private */
    public boolean nothingToVerify() {
        return !this.anyToVerify;
    }

    /* access modifiers changed from: package-private */
    public void doneWithMeta() {
        this.parsingMeta = false;
        this.anyToVerify = !this.sigFileSigners.isEmpty();
        this.baos = null;
        this.sigFileData = null;
        this.pendingBlocks = null;
        this.signerCache = null;
        this.manDig = null;
        if (this.sigFileSigners.containsKey("META-INF/MANIFEST.MF")) {
            this.verifiedSigners.put("META-INF/MANIFEST.MF", (CodeSigner[]) this.sigFileSigners.remove("META-INF/MANIFEST.MF"));
        }
    }

    /* access modifiers changed from: package-private */
    public static class VerifierStream extends InputStream {
        private InputStream is;
        private JarVerifier jv;
        private ManifestEntryVerifier mev;
        private long numLeft;

        VerifierStream(Manifest manifest, JarEntry jarEntry, InputStream inputStream, JarVerifier jarVerifier) {
            if (inputStream != null) {
                this.is = inputStream;
                this.jv = jarVerifier;
                this.mev = new ManifestEntryVerifier(manifest);
                this.jv.beginEntry(jarEntry, this.mev);
                this.numLeft = jarEntry.getSize();
                if (this.numLeft == 0) {
                    this.jv.update(-1, this.mev);
                    return;
                }
                return;
            }
            throw new NullPointerException("is == null");
        }

        @Override // java.io.InputStream
        public int read() {
            InputStream inputStream = this.is;
            if (inputStream == null) {
                throw new IOException("stream closed");
            } else if (this.numLeft <= 0) {
                return -1;
            } else {
                int read = inputStream.read();
                this.jv.update(read, this.mev);
                this.numLeft--;
                if (this.numLeft == 0) {
                    this.jv.update(-1, this.mev);
                }
                return read;
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            if (this.is != null) {
                long j = this.numLeft;
                if (j > 0 && j < ((long) i2)) {
                    i2 = (int) j;
                }
                if (this.numLeft <= 0) {
                    return -1;
                }
                int read = this.is.read(bArr, i, i2);
                this.jv.update(read, bArr, i, i2, this.mev);
                this.numLeft -= (long) read;
                if (this.numLeft == 0) {
                    this.jv.update(-1, bArr, i, i2, this.mev);
                }
                return read;
            }
            throw new IOException("stream closed");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            InputStream inputStream = this.is;
            if (inputStream != null) {
                inputStream.close();
            }
            this.is = null;
            this.mev = null;
            this.jv = null;
        }

        @Override // java.io.InputStream
        public int available() {
            InputStream inputStream = this.is;
            if (inputStream != null) {
                return inputStream.available();
            }
            throw new IOException("stream closed");
        }
    }
}
