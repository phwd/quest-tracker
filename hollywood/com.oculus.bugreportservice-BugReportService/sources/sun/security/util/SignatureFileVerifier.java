package sun.security.util;

import java.io.ByteArrayInputStream;
import java.security.CodeSigner;
import java.security.CryptoPrimitive;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import sun.security.jca.Providers;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

public class SignatureFileVerifier {
    private static final String ATTR_DIGEST = "-DIGEST-Manifest-Main-Attributes".toUpperCase(Locale.ENGLISH);
    private static final Set DIGEST_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.MESSAGE_DIGEST));
    private static final DisabledAlgorithmConstraints JAR_DISABLED_CHECK = new DisabledAlgorithmConstraints("jdk.jar.disabledAlgorithms");
    private static final Debug debug = Debug.getInstance("jar");
    private static final char[] hexc = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private PKCS7 block;
    private CertificateFactory certificateFactory;
    private HashMap createdDigests;
    private ManifestDigester md;
    private String name;
    private byte[] sfBytes;
    private ArrayList signerCache;
    private boolean workaround = false;

    /* JADX INFO: finally extract failed */
    public SignatureFileVerifier(ArrayList arrayList, ManifestDigester manifestDigester, String str, byte[] bArr) {
        Object obj = null;
        this.certificateFactory = null;
        try {
            obj = Providers.startJarVerification();
            this.block = new PKCS7(bArr);
            this.sfBytes = this.block.getContentInfo().getData();
            this.certificateFactory = CertificateFactory.getInstance("X509");
            Providers.stopJarVerification(obj);
            this.name = str.substring(0, str.lastIndexOf(".")).toUpperCase(Locale.ENGLISH);
            this.md = manifestDigester;
            this.signerCache = arrayList;
        } catch (Throwable th) {
            Providers.stopJarVerification(obj);
            throw th;
        }
    }

    public boolean needSignatureFileBytes() {
        return this.sfBytes == null;
    }

    public boolean needSignatureFile(String str) {
        return this.name.equalsIgnoreCase(str);
    }

    public void setSignatureFile(byte[] bArr) {
        this.sfBytes = bArr;
    }

    public static boolean isBlockOrSF(String str) {
        return str.endsWith(".SF") || str.endsWith(".DSA") || str.endsWith(".RSA") || str.endsWith(".EC");
    }

    private MessageDigest getDigest(String str) {
        if (JAR_DISABLED_CHECK.permits(DIGEST_PRIMITIVE_SET, str, null)) {
            if (this.createdDigests == null) {
                this.createdDigests = new HashMap();
            }
            MessageDigest messageDigest = (MessageDigest) this.createdDigests.get(str);
            if (messageDigest != null) {
                return messageDigest;
            }
            try {
                messageDigest = MessageDigest.getInstance(str);
                this.createdDigests.put(str, messageDigest);
                return messageDigest;
            } catch (NoSuchAlgorithmException unused) {
                return messageDigest;
            }
        } else {
            throw new SignatureException("SignatureFile check failed. Disabled algorithm used: " + str);
        }
    }

    public void process(Hashtable hashtable, List list) {
        Throwable th;
        Object obj;
        try {
            obj = Providers.startJarVerification();
            try {
                processImpl(hashtable, list);
                Providers.stopJarVerification(obj);
            } catch (Throwable th2) {
                th = th2;
                Providers.stopJarVerification(obj);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            obj = null;
            Providers.stopJarVerification(obj);
            throw th;
        }
    }

    private void processImpl(Hashtable hashtable, List list) {
        Manifest manifest = new Manifest();
        manifest.read(new ByteArrayInputStream(this.sfBytes));
        String value = manifest.getMainAttributes().getValue(Attributes.Name.SIGNATURE_VERSION);
        if (value != null && value.equalsIgnoreCase("1.0")) {
            SignerInfo[] verify = this.block.verify(this.sfBytes);
            if (verify != null) {
                CodeSigner[] signers = getSigners(verify, this.block);
                if (signers != null) {
                    boolean verifyManifestHash = verifyManifestHash(manifest, this.md, list);
                    if (verifyManifestHash || verifyManifestMainAttrs(manifest, this.md)) {
                        for (Map.Entry entry : manifest.getEntries().entrySet()) {
                            String str = (String) entry.getKey();
                            if (verifyManifestHash || verifySection((Attributes) entry.getValue(), str, this.md)) {
                                if (str.startsWith("./")) {
                                    str = str.substring(2);
                                }
                                if (str.startsWith("/")) {
                                    str = str.substring(1);
                                }
                                updateSigners(signers, hashtable, str);
                                Debug debug2 = debug;
                                if (debug2 != null) {
                                    debug2.println("processSignature signed name = " + str);
                                }
                            } else {
                                Debug debug3 = debug;
                                if (debug3 != null) {
                                    debug3.println("processSignature unsigned name = " + str);
                                }
                            }
                        }
                        updateSigners(signers, hashtable, "META-INF/MANIFEST.MF");
                        return;
                    }
                    throw new SecurityException("Invalid signature file digest for Manifest main attributes");
                }
                return;
            }
            throw new SecurityException("cannot verify signature block file " + this.name);
        }
    }

    private boolean verifyManifestHash(Manifest manifest, ManifestDigester manifestDigester, List list) {
        boolean z = false;
        for (Map.Entry entry : manifest.getMainAttributes().entrySet()) {
            String obj = entry.getKey().toString();
            if (obj.toUpperCase(Locale.ENGLISH).endsWith("-DIGEST-MANIFEST")) {
                String substring = obj.substring(0, obj.length() - 16);
                list.add(obj);
                list.add(entry.getValue());
                MessageDigest digest = getDigest(substring);
                if (digest != null) {
                    byte[] manifestDigest = manifestDigester.manifestDigest(digest);
                    byte[] decode = Base64.getMimeDecoder().decode((String) entry.getValue());
                    Debug debug2 = debug;
                    if (debug2 != null) {
                        debug2.println("Signature File: Manifest digest " + digest.getAlgorithm());
                        debug.println("  sigfile  " + toHex(decode));
                        debug.println("  computed " + toHex(manifestDigest));
                        debug.println();
                    }
                    if (MessageDigest.isEqual(manifestDigest, decode)) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private boolean verifyManifestMainAttrs(Manifest manifest, ManifestDigester manifestDigester) {
        MessageDigest digest;
        for (Map.Entry entry : manifest.getMainAttributes().entrySet()) {
            String obj = entry.getKey().toString();
            if (obj.toUpperCase(Locale.ENGLISH).endsWith(ATTR_DIGEST) && (digest = getDigest(obj.substring(0, obj.length() - ATTR_DIGEST.length()))) != null) {
                byte[] digest2 = manifestDigester.get("Manifest-Main-Attributes", false).digest(digest);
                byte[] decode = Base64.getMimeDecoder().decode((String) entry.getValue());
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("Signature File: Manifest Main Attributes digest " + digest.getAlgorithm());
                    Debug debug3 = debug;
                    debug3.println("  sigfile  " + toHex(decode));
                    Debug debug4 = debug;
                    debug4.println("  computed " + toHex(digest2));
                    debug.println();
                }
                if (!MessageDigest.isEqual(digest2, decode)) {
                    Debug debug5 = debug;
                    if (debug5 == null) {
                        return false;
                    }
                    debug5.println("Verification of Manifest main attributes failed");
                    debug.println();
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0102 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0018 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean verifySection(java.util.jar.Attributes r9, java.lang.String r10, sun.security.util.ManifestDigester r11) {
        /*
        // Method dump skipped, instructions count: 318
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.util.SignatureFileVerifier.verifySection(java.util.jar.Attributes, java.lang.String, sun.security.util.ManifestDigester):boolean");
    }

    private CodeSigner[] getSigners(SignerInfo[] signerInfoArr, PKCS7 pkcs7) {
        ArrayList arrayList = null;
        for (SignerInfo signerInfo : signerInfoArr) {
            ArrayList certificateChain = signerInfo.getCertificateChain(pkcs7);
            CertPath generateCertPath = this.certificateFactory.generateCertPath(certificateChain);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(new CodeSigner(generateCertPath, signerInfo.getTimestamp()));
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("Signature Block Certificate: " + certificateChain.get(0));
            }
        }
        if (arrayList != null) {
            return (CodeSigner[]) arrayList.toArray(new CodeSigner[arrayList.size()]);
        }
        return null;
    }

    static String toHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(hexc[(bArr[i] >> 4) & 15]);
            stringBuffer.append(hexc[bArr[i] & 15]);
        }
        return stringBuffer.toString();
    }

    static boolean contains(CodeSigner[] codeSignerArr, CodeSigner codeSigner) {
        for (CodeSigner codeSigner2 : codeSignerArr) {
            if (codeSigner2.equals(codeSigner)) {
                return true;
            }
        }
        return false;
    }

    static boolean isSubSet(CodeSigner[] codeSignerArr, CodeSigner[] codeSignerArr2) {
        if (codeSignerArr2 == codeSignerArr) {
            return true;
        }
        for (CodeSigner codeSigner : codeSignerArr) {
            if (!contains(codeSignerArr2, codeSigner)) {
                return false;
            }
        }
        return true;
    }

    static boolean matches(CodeSigner[] codeSignerArr, CodeSigner[] codeSignerArr2, CodeSigner[] codeSignerArr3) {
        if (codeSignerArr2 == null && codeSignerArr == codeSignerArr3) {
            return true;
        }
        if (!((codeSignerArr2 == null || isSubSet(codeSignerArr2, codeSignerArr)) && isSubSet(codeSignerArr3, codeSignerArr))) {
            return false;
        }
        for (int i = 0; i < codeSignerArr.length; i++) {
            if (!((codeSignerArr2 != null && contains(codeSignerArr2, codeSignerArr[i])) || contains(codeSignerArr3, codeSignerArr[i]))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void updateSigners(CodeSigner[] codeSignerArr, Hashtable hashtable, String str) {
        CodeSigner[] codeSignerArr2 = (CodeSigner[]) hashtable.get(str);
        int size = this.signerCache.size();
        while (true) {
            size--;
            if (size != -1) {
                CodeSigner[] codeSignerArr3 = (CodeSigner[]) this.signerCache.get(size);
                if (matches(codeSignerArr3, codeSignerArr2, codeSignerArr)) {
                    hashtable.put(str, codeSignerArr3);
                    return;
                }
            } else {
                if (codeSignerArr2 != null) {
                    CodeSigner[] codeSignerArr4 = new CodeSigner[(codeSignerArr2.length + codeSignerArr.length)];
                    System.arraycopy(codeSignerArr2, 0, codeSignerArr4, 0, codeSignerArr2.length);
                    System.arraycopy(codeSignerArr, 0, codeSignerArr4, codeSignerArr2.length, codeSignerArr.length);
                    codeSignerArr = codeSignerArr4;
                }
                this.signerCache.add(codeSignerArr);
                hashtable.put(str, codeSignerArr);
                return;
            }
        }
    }
}
