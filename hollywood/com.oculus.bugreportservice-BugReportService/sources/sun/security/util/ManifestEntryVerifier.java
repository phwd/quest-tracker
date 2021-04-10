package sun.security.util;

import java.security.CodeSigner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.Manifest;
import sun.security.jca.Providers;

public class ManifestEntryVerifier {
    private static final Debug debug = Debug.getInstance("jar");
    private static final char[] hexc = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    HashMap createdDigests = new HashMap(11);
    ArrayList digests = new ArrayList();
    private JarEntry entry;
    private Manifest man;
    ArrayList manifestHashes = new ArrayList();
    private String name = null;
    private CodeSigner[] signers = null;
    private boolean skip = true;

    /* access modifiers changed from: private */
    public static class SunProviderHolder {
        private static final Provider instance = Providers.getSunProvider();
    }

    public ManifestEntryVerifier(Manifest manifest) {
        this.man = manifest;
    }

    public void setEntry(String str, JarEntry jarEntry) {
        this.digests.clear();
        this.manifestHashes.clear();
        this.name = str;
        this.entry = jarEntry;
        this.skip = true;
        this.signers = null;
        Manifest manifest = this.man;
        if (!(manifest == null || str == null)) {
            Attributes attributes = manifest.getAttributes(str);
            if (attributes == null) {
                Manifest manifest2 = this.man;
                attributes = manifest2.getAttributes("./" + str);
                if (attributes == null) {
                    Manifest manifest3 = this.man;
                    attributes = manifest3.getAttributes("/" + str);
                    if (attributes == null) {
                        return;
                    }
                }
            }
            for (Map.Entry entry2 : attributes.entrySet()) {
                String obj = entry2.getKey().toString();
                if (obj.toUpperCase(Locale.ENGLISH).endsWith("-DIGEST")) {
                    String substring = obj.substring(0, obj.length() - 7);
                    MessageDigest messageDigest = (MessageDigest) this.createdDigests.get(substring);
                    if (messageDigest == null) {
                        try {
                            messageDigest = MessageDigest.getInstance(substring, SunProviderHolder.instance);
                            this.createdDigests.put(substring, messageDigest);
                        } catch (NoSuchAlgorithmException unused) {
                        }
                    }
                    if (messageDigest != null) {
                        this.skip = false;
                        messageDigest.reset();
                        this.digests.add(messageDigest);
                        this.manifestHashes.add(Base64.getMimeDecoder().decode((String) entry2.getValue()));
                    }
                }
            }
        }
    }

    public void update(byte b) {
        if (!this.skip) {
            for (int i = 0; i < this.digests.size(); i++) {
                ((MessageDigest) this.digests.get(i)).update(b);
            }
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        if (!this.skip) {
            for (int i3 = 0; i3 < this.digests.size(); i3++) {
                ((MessageDigest) this.digests.get(i3)).update(bArr, i, i2);
            }
        }
    }

    public JarEntry getEntry() {
        return this.entry;
    }

    public CodeSigner[] verify(Hashtable hashtable, Hashtable hashtable2) {
        if (this.skip) {
            return null;
        }
        CodeSigner[] codeSignerArr = this.signers;
        if (codeSignerArr != null) {
            return codeSignerArr;
        }
        for (int i = 0; i < this.digests.size(); i++) {
            MessageDigest messageDigest = (MessageDigest) this.digests.get(i);
            byte[] bArr = (byte[]) this.manifestHashes.get(i);
            byte[] digest = messageDigest.digest();
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("Manifest Entry: " + this.name + " digest=" + messageDigest.getAlgorithm());
                Debug debug3 = debug;
                StringBuilder sb = new StringBuilder();
                sb.append("  manifest ");
                sb.append(toHex(bArr));
                debug3.println(sb.toString());
                Debug debug4 = debug;
                debug4.println("  computed " + toHex(digest));
                debug.println();
            }
            if (!MessageDigest.isEqual(digest, bArr)) {
                throw new SecurityException(messageDigest.getAlgorithm() + " digest error for " + this.name);
            }
        }
        this.signers = (CodeSigner[]) hashtable2.remove(this.name);
        CodeSigner[] codeSignerArr2 = this.signers;
        if (codeSignerArr2 != null) {
            hashtable.put(this.name, codeSignerArr2);
        }
        return this.signers;
    }

    static String toHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(hexc[(bArr[i] >> 4) & 15]);
            stringBuffer.append(hexc[bArr[i] & 15]);
        }
        return stringBuffer.toString();
    }
}
