package oculus.internal.license.parsing;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PSSParameterSpec;
import java.util.Collection;
import oculus.internal.license.LicenseFormatException;
import oculus.internal.license.SignerNotFoundException;

public class LicenseBlobParser {
    public static final short CURRENT_VERSION = 1;
    public static final int MAGIC = 1329810515;
    public static final short SIGNER_FINGERPRINT_SIZE = 16;
    CertStore certStore;

    public enum SignatureScheme {
        SHA256_RSASSA_PSS_WITH_MAXIMUM_SALT_LENGTH(1, "SHA256withRSA/PSS"),
        SHA256_RSASSA_PSS(2, "SHA256withRSA/PSS");
        
        public final String algorithmName;
        public final short value;

        private SignatureScheme(short value2, String algorithmName2) {
            this.value = value2;
            this.algorithmName = algorithmName2;
        }

        static SignatureScheme valueOf(int identifier) {
            SignatureScheme signatureScheme = SHA256_RSASSA_PSS_WITH_MAXIMUM_SALT_LENGTH;
            if (identifier == signatureScheme.value) {
                return signatureScheme;
            }
            SignatureScheme signatureScheme2 = SHA256_RSASSA_PSS;
            if (identifier == signatureScheme2.value) {
                return signatureScheme2;
            }
            throw new IllegalArgumentException("unrecognized signature scheme");
        }
    }

    public LicenseBlobParser(CertStore certStore2) {
        this.certStore = certStore2;
    }

    public ByteBuffer parseLicense(byte[] blob) throws LicenseFormatException, SignatureException, SignerNotFoundException {
        return parseLicense(ByteBuffer.wrap(blob).asReadOnlyBuffer());
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer parseLicense(byte[] blob, PSSParameterSpec pssParameterSpec) throws LicenseFormatException, SignatureException, SignerNotFoundException {
        return parseLicense(ByteBuffer.wrap(blob).asReadOnlyBuffer(), pssParameterSpec);
    }

    public ByteBuffer parseLicense(ByteBuffer blob) throws LicenseFormatException, SignatureException, SignerNotFoundException {
        return parseLicense(blob, (PSSParameterSpec) null);
    }

    private ByteBuffer parseLicense(ByteBuffer blob, PSSParameterSpec pssParameterSpec) throws LicenseFormatException, SignatureException, SignerNotFoundException {
        RuntimeException e;
        ByteBuffer wrapped = blob.isReadOnly() ? blob : blob.asReadOnlyBuffer();
        try {
            if (wrapped.getInt() != 1329810515) {
                throw new LicenseFormatException("Invalid header");
            } else if (wrapped.getShort() == 1) {
                SignatureScheme scheme = SignatureScheme.valueOf(wrapped.getShort());
                byte[] fingerprint = new byte[16];
                wrapped.get(fingerprint);
                try {
                    Certificate signer = lookupSigner(fingerprint);
                    PublicKey pk = signer.getPublicKey();
                    if (pk.getAlgorithm() == "RSA") {
                        byte[] signature = new byte[(((RSAPublicKey) pk).getModulus().bitLength() / 8)];
                        wrapped.get(signature);
                        int signedDataLength = wrapped.getInt();
                        if (signedDataLength != wrapped.remaining()) {
                            throw new LicenseFormatException(String.format("Invalid header: signedDataLength = %d; remaining = %d", Integer.valueOf(signedDataLength), Integer.valueOf(wrapped.remaining())));
                        } else if (signedDataLength == 0) {
                            try {
                                throw new LicenseFormatException("No signed data in license blob");
                            } catch (IllegalArgumentException | BufferUnderflowException e2) {
                                e = e2;
                                throw new LicenseFormatException(e);
                            }
                        } else if (verifySignature(scheme, pssParameterSpec, signer, signature, wrapped.slice())) {
                            return wrapped;
                        } else {
                            throw new SignatureException("Invalid signature");
                        }
                    } else {
                        throw new SignerNotFoundException("signer not supported");
                    }
                } catch (IllegalArgumentException | BufferUnderflowException e3) {
                    e = e3;
                    throw new LicenseFormatException(e);
                }
            } else {
                throw new LicenseFormatException("Unrecognized version");
            }
        } catch (IllegalArgumentException | BufferUnderflowException e4) {
            e = e4;
            throw new LicenseFormatException(e);
        }
    }

    private static boolean verifySignature(SignatureScheme scheme, PSSParameterSpec pssParameterSpec, Certificate signer, byte[] signature, ByteBuffer signedData) throws SignatureException {
        Signature verifier = buildSignatureSchemeVerifier(scheme, pssParameterSpec, signer);
        verifier.update(signedData);
        return verifier.verify(signature);
    }

    private static Signature buildSignatureSchemeVerifier(SignatureScheme scheme, PSSParameterSpec parameterSpecOverride, Certificate signer) throws SignatureException {
        try {
            Signature verifier = Signature.getInstance(scheme.algorithmName);
            if (parameterSpecOverride == null && scheme == SignatureScheme.SHA256_RSASSA_PSS_WITH_MAXIMUM_SALT_LENGTH) {
                parameterSpecOverride = buildMaxSaltLengthPssParameterSpec(verifier, (RSAPublicKey) signer.getPublicKey());
            }
            verifier.initVerify(signer.getPublicKey());
            if (parameterSpecOverride != null) {
                verifier.setParameter(parameterSpecOverride);
            }
            return verifier;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidParameterSpecException e) {
            throw new SignatureException(e);
        }
    }

    private Certificate lookupSigner(byte[] fingerprint) throws SignerNotFoundException {
        try {
            Collection<? extends Certificate> certificates = this.certStore.getCertificates(new PublicKeyFingerprintSelector(fingerprint, MessageDigest.getInstance("MD5")));
            if (!certificates.isEmpty()) {
                return (Certificate) certificates.toArray()[0];
            }
            throw new SignerNotFoundException("No matching certificates");
        } catch (NoSuchAlgorithmException | CertStoreException e) {
            throw new SignerNotFoundException(e);
        }
    }

    public static PSSParameterSpec buildMaxSaltLengthPssParameterSpec(Signature signer, RSAPublicKey rsaPublicKey) throws InvalidParameterSpecException, NoSuchAlgorithmException {
        PSSParameterSpec pssParameterSpec = (PSSParameterSpec) signer.getParameters().getParameterSpec(PSSParameterSpec.class);
        return new PSSParameterSpec(pssParameterSpec.getDigestAlgorithm(), pssParameterSpec.getMGFAlgorithm(), pssParameterSpec.getMGFParameters(), ((rsaPublicKey.getModulus().bitLength() / 8) - MessageDigest.getInstance(pssParameterSpec.getDigestAlgorithm()).getDigestLength()) - 2, pssParameterSpec.getTrailerField());
    }
}
