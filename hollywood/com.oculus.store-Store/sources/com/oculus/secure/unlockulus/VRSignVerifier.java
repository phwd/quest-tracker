package com.oculus.secure.unlockulus;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class VRSignVerifier {
    private static final byte[] CERT = {48, -126, 3, -119, 48, -126, 2, 113, -96, 3, 2, 1, 2, 2, 4, 90, -42, -9, -24, 48, Ascii.CR, 6, 9, 42, -122, 72, -122, -9, Ascii.CR, 1, 1, Ascii.VT, 5, 0, 48, 117, 49, Ascii.VT, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, Ascii.VT, 48, 9, 6, 3, 85, 4, 8, 19, 2, 67, 65, 49, 19, 48, 17, 6, 3, 85, 4, 7, 19, 10, 77, 101, 110, 108, 111, 32, 80, 97, 114, 107, 49, Ascii.DC4, 48, Ascii.DC2, 6, 3, 85, 4, 10, 19, Ascii.VT, 79, 99, 117, 108, 117, 115, 32, 72, 111, 109, 101, 49, Ascii.CAN, 48, Ascii.SYN, 6, 3, 85, 4, Ascii.VT, 19, Ascii.SI, 79, 99, 117, 108, 117, 115, 32, 80, 108, 97, 116, 102, 111, 114, 109, 49, Ascii.DC4, 48, Ascii.DC2, 6, 3, 85, 4, 3, 19, Ascii.VT, 79, 99, 117, 108, 117, 115, 44, 32, 73, 110, 99, 48, Ascii.RS, Ascii.ETB, Ascii.CR, 49, 52, 48, 57, 48, 53, 49, 55, 50, 56, 49, 48, 90, Ascii.ETB, Ascii.CR, 52, 50, 48, 49, 50, 49, 49, 55, 50, 56, 49, 48, 90, 48, 117, 49, Ascii.VT, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, Ascii.VT, 48, 9, 6, 3, 85, 4, 8, 19, 2, 67, 65, 49, 19, 48, 17, 6, 3, 85, 4, 7, 19, 10, 77, 101, 110, 108, 111, 32, 80, 97, 114, 107, 49, Ascii.DC4, 48, Ascii.DC2, 6, 3, 85, 4, 10, 19, Ascii.VT, 79, 99, 117, 108, 117, 115, 32, 72, 111, 109, 101, 49, Ascii.CAN, 48, Ascii.SYN, 6, 3, 85, 4, Ascii.VT, 19, Ascii.SI, 79, 99, 117, 108, 117, 115, 32, 80, 108, 97, 116, 102, 111, 114, 109, 49, Ascii.DC4, 48, Ascii.DC2, 6, 3, 85, 4, 3, 19, Ascii.VT, 79, 99, 117, 108, 117, 115, 44, 32, 73, 110, 99, 48, -126, 1, 34, 48, Ascii.CR, 6, 9, 42, -122, 72, -122, -9, Ascii.CR, 1, 1, 1, 5, 0, 3, -126, 1, Ascii.SI, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -97, 111, -47, -6, 0, 102, -81, 93, 121, 98, 49, -32, 82, -100, -25, -116, -106, 71, 65, 116, -56, -115, -42, -127, 58, -105, 49, 0, -53, -62, 73, 103, -42, 95, -119, 101, 95, -40, Ascii.FS, 89, -39, 92, -11, -59, -23, -102, -28, 81, 93, -60, 69, -114, -57, 78, -29, 34, 59, Ascii.VT, Ascii.ETB, 35, -85, SignedBytes.MAX_POWER_OF_TWO, -81, -98, -75, 77, Ascii.RS, 121, -8, 0, 33, UnsignedBytes.MAX_POWER_OF_TWO, 38, 4, -99, 113, -106, 96, -106, -96, 87, Ascii.DEL, 76, -15, -31, -121, -34, 81, -86, 72, Ascii.FF, -127, -58, 47, -92, -121, 122, 126, -44, -42, Ascii.NAK, 1, 0, 124, -81, -56, 98, 115, 124, 113, 99, -11, 72, -104, -94, -50, 1, -51, Ascii.SO, -112, -65, -16, Ascii.RS, 80, -4, 115, 94, -73, SignedBytes.MAX_POWER_OF_TWO, 53, 46, 61, 5, -23, -76, Ascii.DEL, 47, -114, Ascii.RS, -6, 1, -28, 36, 74, Ascii.DEL, -108, -77, -30, 124, -22, 54, Ascii.FS, -38, 5, 48, -27, 45, -98, 125, -64, -50, 88, -118, 71, 88, -7, Ascii.SI, -13, -122, -28, 61, 65, -40, -42, -11, -93, 89, Ascii.DC4, 19, -38, -60, -78, -115, 94, 7, UnsignedBytes.MAX_POWER_OF_TWO, 88, -16, Ascii.FS, -82, -87, 41, -99, -66, 96, UnsignedBytes.MAX_POWER_OF_TWO, 39, 3, -106, -110, Ascii.VT, 58, 89, -62, Ascii.SYN, -24, -58, 118, 55, -21, -27, -42, 104, 92, -87, -102, 96, 116, 92, -16, -114, Ascii.SI, 122, -30, -7, 76, -89, -20, 4, Ascii.SO, -55, 110, -40, 55, 78, 123, -7, SignedBytes.MAX_POWER_OF_TWO, -20, -69, Ascii.DEL, 102, -65, -100, -63, -103, -78, 77, -31, -77, 53, -26, -87, Ascii.DC4, 58, -61, 2, 3, 1, 0, 1, -93, 33, 48, Ascii.US, 48, Ascii.GS, 6, 3, 85, Ascii.GS, Ascii.SO, 4, Ascii.SYN, 4, Ascii.DC4, -58, 97, -40, 109, 43, 68, 123, 68, -97, 37, -7, 41, 32, 38, -28, -26, 61, 35, 111, -103, 48, Ascii.CR, 6, 9, 42, -122, 72, -122, -9, Ascii.CR, 1, 1, Ascii.VT, 5, 0, 3, -126, 1, 1, 0, 69, -87, -88, -68, -4, -123, 79, -25, -19, -108, -53, 93, 114, 51, -87, 120, 101, 89, Ascii.ESC, Ascii.NAK, 102, -63, -16, 37, -62, 84, -20, -93, -79, -36, -17, -7, 106, -15, 126, SignedBytes.MAX_POWER_OF_TWO, 62, -90, 111, 70, 7, -22, Ascii.FF, -44, -6, -87, Ascii.DEL, -79, 58, -100, -44, -104, Ascii.DEL, 122, Ascii.RS, -8, -120, Ascii.RS, 84, -113, -3, 67, 112, -81, -118, Ascii.SYN, Ascii.DLE, Ascii.CR, 93, 49, -45, 111, Ascii.US, -77, -11, 93, -63, 2, -88, -20, -88, 10, 97, -35, -82, 103, -7, -12, -15, -73, -33, -61, -94, 125, -62, -38, 118, -118, 120, -68, 118, -32, -117, -81, -52, -110, -126, 87, 101, -57, -103, -122, -92, 107, 37, 109, -117, 62, -99, -61, 55, -115, 73, -65, -97, 98, 66, -49, 101, 105, Ascii.EM, 1, 119, -79, -90, 121, -98, -103, 38, 59, -1, 94, 111, 53, Ascii.SO, -127, -37, 89, -119, 33, 86, 111, -21, 87, -22, 118, -18, 82, -35, -65, -32, 92, -56, Ascii.FF, -4, -89, -14, -67, -46, 102, -56, 40, -13, 58, 71, 117, Ascii.FF, 44, 40, -72, Ascii.SYN, 74, -18, -96, -83, 91, -125, 5, 115, 38, -58, 99, 47, -14, -81, 116, 79, 0, -83, -19, -60, Ascii.DEL, 113, 78, -55, -4, 86, UnsignedBytes.MAX_POWER_OF_TWO, -86, 57, 90, 86, -39, -61, 114, -122, -60, -102, 80, -94, -41, 85, 114, -61, -124, 67, -53, -35, 121, -16, 87, 17, 54, -122, -60, 75, -10, Ascii.CAN, 39, -14, 9, -102, 90, -51, -78, -5, -81, 87, -49, -17, -79, -102, 19, 68, -43, -63};
    private final FBSignVerify mVerifier;

    public VRSignVerifier() {
        try {
            this.mVerifier = new FBSignVerify((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(CERT)));
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyVRSig(String apkFile) throws IOException {
        JarFile jar = new JarFile(apkFile);
        List<JarEntry> sfEntries = getJarEntries(jar, ".SF");
        if (sfEntries.size() != 1) {
            throw new IOException("expected one .SF file, got " + sfEntries.size());
        }
        InputStream sfInput = jar.getInputStream(sfEntries.get(0));
        for (JarEntry entry : getJarEntries(jar, ".OSIG")) {
            if (this.mVerifier.verify(sfInput, jar.getInputStream(entry))) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyOculusSig(String serial, InputStream signatureStream) throws IOException {
        return this.mVerifier.verify(new ByteArrayInputStream(("﻿" + serial).getBytes("UTF-16LE")), signatureStream);
    }

    private static List<JarEntry> getJarEntries(JarFile inputJar, String suffix) {
        Enumeration<JarEntry> entries = inputJar.entries();
        List<JarEntry> matching = new ArrayList<>();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().endsWith(suffix)) {
                matching.add(entry);
            }
        }
        return matching;
    }

    public static void main(String[] argv) throws Exception {
        VRSignVerifier verifier = new VRSignVerifier();
        String apk = argv[0];
        System.out.println(apk + " " + verifier.verifyVRSig(apk));
    }
}
