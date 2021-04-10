package oculus.internal;

import android.util.ArrayMap;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractApkSignatureSchemeV2 {
    public abstract ByteBuffer getLengthPrefixedSlice(ByteBuffer byteBuffer) throws IOException;

    public abstract ByteBuffer getSignatureBlock(RandomAccessFile randomAccessFile) throws IOException, NoSignatureFoundException;

    public abstract byte[] readLengthPrefixedByteArray(ByteBuffer byteBuffer) throws IOException;

    public X509Certificate[][] findDigestsAndCertificates(RandomAccessFile apk, List<Map<Integer, byte[]>> signerDigests) throws IOException, NoSignatureFoundException {
        ByteBuffer signatureBlock = getSignatureBlock(apk);
        int signerCount = 0;
        List<X509Certificate[]> signerCerts = new ArrayList<>();
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            ByteBuffer signers = getLengthPrefixedSlice(signatureBlock);
            while (signers.hasRemaining()) {
                signerCount++;
                try {
                    ByteBuffer signer = getLengthPrefixedSlice(signers);
                    Map<Integer, byte[]> digests = new ArrayMap<>();
                    signerCerts.add(parseDigestsAndCertificates(signer, digests, certFactory));
                    signerDigests.add(digests);
                } catch (IOException | SecurityException | BufferUnderflowException e) {
                    throw new SecurityException("Failed to parse/verify signer #" + signerCount + " block", e);
                }
            }
            if (signerCount < 1) {
                throw new SecurityException("No signers found");
            } else if (!signerDigests.isEmpty()) {
                return (X509Certificate[][]) signerCerts.toArray(new X509Certificate[signerCerts.size()][]);
            } else {
                throw new SecurityException("No content digests found");
            }
        } catch (CertificateException e2) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e2);
        }
    }

    public X509Certificate[] parseDigestsAndCertificates(ByteBuffer signerBlock, Map<Integer, byte[]> contentDigests, CertificateFactory certFactory) throws SecurityException, IOException {
        ByteBuffer signedData = getLengthPrefixedSlice(signerBlock);
        ByteBuffer digests = getLengthPrefixedSlice(signedData);
        int digestCount = 0;
        while (digests.hasRemaining()) {
            digestCount++;
            try {
                ByteBuffer digest = getLengthPrefixedSlice(digests);
                if (digest.remaining() >= 8) {
                    int sigAlgorithm = digest.getInt();
                    contentDigests.put(Integer.valueOf(sigAlgorithm), readLengthPrefixedByteArray(digest));
                } else {
                    throw new IOException("Record too short");
                }
            } catch (IOException | BufferUnderflowException e) {
                throw new IOException("Failed to parse digest record #" + digestCount, e);
            }
        }
        ByteBuffer certificates = getLengthPrefixedSlice(signedData);
        List<X509Certificate> certs = new ArrayList<>();
        int certificateCount = 0;
        while (certificates.hasRemaining()) {
            certificateCount++;
            try {
                certs.add((X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(readLengthPrefixedByteArray(certificates))));
            } catch (CertificateException e2) {
                throw new SecurityException("Failed to decode certificate #" + certificateCount, e2);
            }
        }
        if (!certs.isEmpty()) {
            return (X509Certificate[]) certs.toArray(new X509Certificate[certs.size()]);
        }
        throw new SecurityException("No certificates listed");
    }

    public class NoSignatureFoundException extends Exception {
        public NoSignatureFoundException(String message) {
            super(message);
        }

        public NoSignatureFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
