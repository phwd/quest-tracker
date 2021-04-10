package com.facebook.oxygen.common.verification;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.io.Closeables;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSigner;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.annotation.Nullable;

public class FbSignatureVerifier {
    private final FbSigVerifierKillSwitchProvider mKillSwitchProvider;
    private final PackageManager mPackageManager;
    @Nullable
    private PublicKey mPublicKey;
    private final byte[] mPublicKeyData;
    private final String mSignatureAlgorithm;

    public interface FbSigVerifierKillSwitchProvider {
        boolean isKillSwitchEnabled();
    }

    public FbSignatureVerifier(PackageManager packageManager, FbSigVerifierKillSwitchProvider fbSigVerifierKillSwitchProvider, byte[] bArr, String str) {
        this.mPackageManager = packageManager;
        this.mPublicKeyData = bArr;
        this.mSignatureAlgorithm = str;
        this.mKillSwitchProvider = fbSigVerifierKillSwitchProvider;
    }

    @SuppressLint({"CatchGeneralException"})
    public boolean isKillSwitchEnabled() {
        try {
            return this.mKillSwitchProvider.isKillSwitchEnabled();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void enforceSignature(File file, @Nullable byte[] bArr) throws IOException, GeneralSecurityException, SecurityException {
        ExtSigVerificationResult verifySignature = verifySignature(file, bArr);
        if (!verifySignature.verified) {
            throw new SecurityException("Failed to verify apk " + file + ": " + verifySignature.reason);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.oxygen.common.verification.ExtSigVerificationResult verifySignature(java.io.File r5, @javax.annotation.Nullable byte[] r6) throws java.io.IOException, java.security.GeneralSecurityException {
        /*
            r4 = this;
            boolean r0 = r4.isKillSwitchEnabled()
            if (r0 == 0) goto L_0x000d
            java.lang.String r5 = "FB signatures verification disabled"
            com.facebook.oxygen.common.verification.ExtSigVerificationResult r5 = com.facebook.oxygen.common.verification.ExtSigVerificationResult.unverified(r5)
            return r5
        L_0x000d:
            r0 = 0
            java.util.jar.JarFile r1 = new java.util.jar.JarFile     // Catch:{ all -> 0x0023 }
            java.lang.String r2 = r5.getAbsolutePath()     // Catch:{ all -> 0x0023 }
            r3 = 1
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0023 }
            com.facebook.oxygen.common.verification.ExtSigVerificationResult r5 = r4.verifySignature(r5, r1, r6)     // Catch:{ all -> 0x0020 }
            r1.close()
            return r5
        L_0x0020:
            r5 = move-exception
            r0 = r1
            goto L_0x0024
        L_0x0023:
            r5 = move-exception
        L_0x0024:
            if (r0 == 0) goto L_0x0029
            r0.close()
        L_0x0029:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.oxygen.common.verification.FbSignatureVerifier.verifySignature(java.io.File, byte[]):com.facebook.oxygen.common.verification.ExtSigVerificationResult");
    }

    private ExtSigVerificationResult verifySignature(File file, JarFile jarFile, @Nullable byte[] bArr) throws IOException, GeneralSecurityException {
        InputStream inputStream;
        Throwable th;
        try {
            Set<String> listJarEntries = listJarEntries(jarFile, "META-INF/");
            try {
                Optional<String> findMetaInfFileWithExtension = findMetaInfFileWithExtension(listJarEntries, ".SF");
                if (!findMetaInfFileWithExtension.isPresent()) {
                    return ExtSigVerificationResult.unverified("No .SF files found.");
                }
                if (bArr != null) {
                    try {
                        inputStream = new ByteArrayInputStream(bArr);
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = null;
                        Closeables.closeQuietly(inputStream);
                        Closeables.closeQuietly((InputStream) null);
                        Closeables.close(jarFile, true);
                        throw th;
                    }
                } else {
                    try {
                        Optional<String> findMetaInfFileWithExtension2 = findMetaInfFileWithExtension(listJarEntries, ".OSIG");
                        if (!findMetaInfFileWithExtension2.isPresent()) {
                            ExtSigVerificationResult unverified = ExtSigVerificationResult.unverified("No signature found or provided.");
                            Closeables.closeQuietly((InputStream) null);
                            Closeables.closeQuietly((InputStream) null);
                            Closeables.close(jarFile, true);
                            return unverified;
                        }
                        ZipEntry entry = jarFile.getEntry(findMetaInfFileWithExtension2.get());
                        if (entry == null) {
                            ExtSigVerificationResult unverifiedFormat = ExtSigVerificationResult.unverifiedFormat("Failed to open %s.", findMetaInfFileWithExtension2);
                            Closeables.closeQuietly((InputStream) null);
                            Closeables.closeQuietly((InputStream) null);
                            Closeables.close(jarFile, true);
                            return unverifiedFormat;
                        }
                        inputStream = jarFile.getInputStream(entry);
                    } catch (SecurityException e) {
                        ExtSigVerificationResult unverified2 = ExtSigVerificationResult.unverified(e.getMessage());
                        Closeables.closeQuietly((InputStream) null);
                        Closeables.closeQuietly((InputStream) null);
                        Closeables.close(jarFile, true);
                        return unverified2;
                    }
                }
                try {
                    ZipEntry entry2 = jarFile.getEntry(findMetaInfFileWithExtension.get());
                    if (entry2 == null) {
                        ExtSigVerificationResult unverifiedFormat2 = ExtSigVerificationResult.unverifiedFormat("Failed to open %s.", findMetaInfFileWithExtension);
                        Closeables.closeQuietly(inputStream);
                        Closeables.closeQuietly((InputStream) null);
                        Closeables.close(jarFile, true);
                        return unverifiedFormat2;
                    }
                    InputStream inputStream2 = jarFile.getInputStream(entry2);
                    if (!verifySignature(getPublicKey(), inputStream2, inputStream)) {
                        ExtSigVerificationResult unverified3 = ExtSigVerificationResult.unverified("Signature check failed.");
                        Closeables.closeQuietly(inputStream);
                        Closeables.closeQuietly(inputStream2);
                        Closeables.close(jarFile, true);
                        return unverified3;
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
                        PackageInfo packageArchiveInfo = this.mPackageManager.getPackageArchiveInfo(file.getPath(), 64);
                        if (packageArchiveInfo == null) {
                            ExtSigVerificationResult unverified4 = ExtSigVerificationResult.unverified("Failed to parse apk.");
                            Closeables.closeQuietly(inputStream);
                            Closeables.closeQuietly(inputStream2);
                            Closeables.close(jarFile, true);
                            return unverified4;
                        } else if (packageArchiveInfo.signatures == null || packageArchiveInfo.signatures.length == 0) {
                            ExtSigVerificationResult unverified5 = ExtSigVerificationResult.unverified("Apk has no signatures.");
                            Closeables.closeQuietly(inputStream);
                            Closeables.closeQuietly(inputStream2);
                            Closeables.close(jarFile, true);
                            return unverified5;
                        } else if (packageArchiveInfo.signatures.length > 1) {
                            ExtSigVerificationResult unverified6 = ExtSigVerificationResult.unverified("Apk has multiple signatures.");
                            Closeables.closeQuietly(inputStream);
                            Closeables.closeQuietly(inputStream2);
                            Closeables.close(jarFile, true);
                            return unverified6;
                        } else {
                            try {
                                enforceV1Signature(jarFile, packageArchiveInfo.signatures[0]);
                            } catch (SecurityException e2) {
                                ExtSigVerificationResult unverified7 = ExtSigVerificationResult.unverified(e2.getMessage());
                                Closeables.closeQuietly(inputStream);
                                Closeables.closeQuietly(inputStream2);
                                Closeables.close(jarFile, true);
                                return unverified7;
                            }
                        }
                    }
                    ExtSigVerificationResult verified = ExtSigVerificationResult.verified();
                    Closeables.closeQuietly(inputStream);
                    Closeables.closeQuietly(inputStream2);
                    Closeables.close(jarFile, true);
                    return verified;
                } catch (Throwable th3) {
                    th = th3;
                    Closeables.closeQuietly(inputStream);
                    Closeables.closeQuietly((InputStream) null);
                    Closeables.close(jarFile, true);
                    throw th;
                }
            } catch (SecurityException e3) {
                return ExtSigVerificationResult.unverified(e3.getMessage());
            }
        } catch (SecurityException e4) {
            return ExtSigVerificationResult.unverified(e4.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean verifySignature(PublicKey publicKey, InputStream inputStream, InputStream inputStream2) throws IOException, GeneralSecurityException {
        Signature instance = Signature.getInstance(this.mSignatureAlgorithm);
        instance.initVerify(publicKey);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            instance.update(bArr, 0, read);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read2 = inputStream2.read(bArr);
            if (read2 == -1) {
                return instance.verify(byteArrayOutputStream.toByteArray());
            }
            byteArrayOutputStream.write(bArr, 0, read2);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized PublicKey getPublicKey() throws CertificateException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (this.mPublicKey == null) {
            this.mPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(this.mPublicKeyData));
        }
        return this.mPublicKey;
    }

    /* access modifiers changed from: package-private */
    public Set<String> listJarEntries(JarFile jarFile, String str) throws IOException {
        HashSet hashSet = new HashSet();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            String name = entries.nextElement().getName();
            if (name.startsWith(str)) {
                if (!hashSet.contains(name)) {
                    hashSet.add(name);
                } else {
                    throw new SecurityException("Multiple entries found: " + name);
                }
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: package-private */
    public Optional<String> findMetaInfFileWithExtension(Set<String> set, String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : set) {
            if (str2.endsWith(str)) {
                arrayList.add(str2);
            }
        }
        if (arrayList.isEmpty()) {
            return Optional.absent();
        }
        if (arrayList.size() <= 1) {
            String str3 = (String) arrayList.get(0);
            if (str3.lastIndexOf("/") == str3.indexOf("/")) {
                return Optional.of(str3);
            }
            throw new SecurityException(str3 + " includes sub directories in its path.");
        }
        throw new SecurityException("Expected one " + str + " file, found " + arrayList);
    }

    /* access modifiers changed from: package-private */
    public void enforceV1Signature(JarFile jarFile, android.content.pm.Signature signature) throws IOException {
        ArrayList<JarEntry> arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry nextElement = entries.nextElement();
            if (!nextElement.isDirectory()) {
                String name = nextElement.getName();
                if (name.startsWith("META-INF/")) {
                    continue;
                } else if (!hashSet.contains(name)) {
                    hashSet.add(name);
                    arrayList.add(nextElement);
                } else {
                    throw new SecurityException("Multiple entries with the same name: " + name);
                }
            }
        }
        byte[] bArr = new byte[4096];
        for (JarEntry jarEntry : arrayList) {
            Object[] loadAndVerifyJarEntrySignatures = loadAndVerifyJarEntrySignatures(jarFile, jarEntry, bArr);
            if (loadAndVerifyJarEntrySignatures == null || loadAndVerifyJarEntrySignatures.length == 0) {
                throw new SecurityException("No signatures for: " + jarEntry.getName());
            } else if (loadAndVerifyJarEntrySignatures.length > 1) {
                throw new SecurityException("Multiple signatures for: " + jarEntry.getName());
            } else if (!signature.equals(loadAndVerifyJarEntrySignatures[0])) {
                throw new SecurityException(String.format("Entry signature mismatch: %s.\nExpected:\n%s\nActual:\n%s", jarEntry.getName(), Base64.encodeToString(signature.toByteArray(), 0), Base64.encodeToString(loadAndVerifyJarEntrySignatures[0].toByteArray(), 0)));
            }
        }
    }

    @Nullable
    private static android.content.pm.Signature[] loadAndVerifyJarEntrySignatures(JarFile jarFile, JarEntry jarEntry, byte[] bArr) throws IOException {
        Throwable th;
        InputStream inputStream;
        try {
            inputStream = jarFile.getInputStream(jarEntry);
            try {
                consumeStream(inputStream, bArr);
                jarEntry.getCertificates();
                android.content.pm.Signature[] jarEntrySignatures = getJarEntrySignatures(jarEntry);
                Closeables.closeQuietly(inputStream);
                return jarEntrySignatures;
            } catch (Throwable th2) {
                th = th2;
                Closeables.closeQuietly(inputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            Closeables.closeQuietly(inputStream);
            throw th;
        }
    }

    @Nullable
    private static android.content.pm.Signature[] getJarEntrySignatures(JarEntry jarEntry) throws IOException {
        CodeSigner[] codeSigners = jarEntry.getCodeSigners();
        if (codeSigners == null || codeSigners.length == 0) {
            return null;
        }
        android.content.pm.Signature[] signatureArr = new android.content.pm.Signature[codeSigners.length];
        for (int i = 0; i < codeSigners.length; i++) {
            List<? extends Certificate> certificates = codeSigners[i].getSignerCertPath().getCertificates();
            if (certificates.isEmpty()) {
                return null;
            }
            try {
                signatureArr[i] = new android.content.pm.Signature(((Certificate) certificates.get(0)).getEncoded());
            } catch (CertificateEncodingException e) {
                throw new IOException(e);
            }
        }
        return signatureArr;
    }

    private static void consumeStream(InputStream inputStream, byte[] bArr) throws IOException {
        do {
        } while (inputStream.read(bArr, 0, bArr.length) > 0);
    }
}
