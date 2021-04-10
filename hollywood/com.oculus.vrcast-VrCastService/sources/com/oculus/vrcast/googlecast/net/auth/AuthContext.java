package com.oculus.vrcast.googlecast.net.auth;

import android.util.Log;
import android.util.Pair;
import com.google.protobuf.ByteString;
import com.oculus.vrcast.googlecast.net.CastProtocol;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;

public class AuthContext {
    private static final String TAG = "AuthContext";
    private static final boolean mEnableHashAlgorithmCheck = false;
    private final GoogleCastCA mGoogleCastCA;
    private final CastProtocol.HashAlgorithm mHashAlgorithm;
    private final byte[] mNonce = generateNonce();
    private final X509Certificate mSocketCertificate;

    public AuthContext(Certificate certificate, GoogleCastCA googleCastCA, CastProtocol.HashAlgorithm hashAlgorithm) {
        this.mSocketCertificate = (X509Certificate) certificate;
        this.mGoogleCastCA = googleCastCA;
        this.mHashAlgorithm = hashAlgorithm;
    }

    private byte[] generateNonce() {
        return SecureRandom.getSeed(16);
    }

    public ByteString getNonce() {
        return ByteString.copyFrom(this.mNonce);
    }

    public CastProtocol.HashAlgorithm getHashAlgorithm() {
        return this.mHashAlgorithm;
    }

    public AuthResult verifyResponse(CastProtocol.AuthResponse authResponse) {
        Pair<AuthResult, byte[]> checkCertificateAndExportAsDer = checkCertificateAndExportAsDer(this.mSocketCertificate);
        AuthResult authResult = (AuthResult) checkCertificateAndExportAsDer.first;
        if (AuthResult.isError(authResult)) {
            return authResult;
        }
        byte[] bArr = (byte[]) checkCertificateAndExportAsDer.second;
        ByteString senderNonce = authResponse.getSenderNonce();
        AuthResult verifyResponseNonce = verifyResponseNonce(senderNonce.toByteArray());
        if (AuthResult.isError(verifyResponseNonce)) {
            return verifyResponseNonce;
        }
        ByteBuffer allocate = ByteBuffer.allocate(senderNonce.size() + bArr.length);
        senderNonce.copyTo(allocate);
        allocate.put(bArr);
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(authResponse.getClientAuthCertificate().newInput());
            AuthResult verifyCertificateChain = verifyCertificateChain(authResponse, x509Certificate);
            return AuthResult.isError(verifyCertificateChain) ? verifyCertificateChain : verifyCredentials(authResponse, allocate, x509Certificate);
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException e) {
            Log.e(TAG, "Received exception, returning error", e);
            return AuthResult.AuthErrorGeneric;
        }
    }

    private Pair<AuthResult, byte[]> checkCertificateAndExportAsDer(X509Certificate x509Certificate) {
        try {
            x509Certificate.checkValidity();
            try {
                return new Pair<>(AuthResult.AuthSuccess, x509Certificate.getEncoded());
            } catch (CertificateEncodingException e) {
                Log.e(TAG, "Could not encode peer certificate", e);
                return new Pair<>(AuthResult.AuthErrorCertVerify, null);
            }
        } catch (CertificateExpiredException | CertificateNotYetValidException unused) {
            return new Pair<>(AuthResult.AuthErrorCertExpired, null);
        }
    }

    private AuthResult verifyResponseNonce(byte[] bArr) {
        return AuthResult.AuthSuccess;
    }

    private AuthResult verifyCertificateChain(CastProtocol.AuthResponse authResponse, X509Certificate x509Certificate) throws CertificateException, NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IOException {
        CertificateFactory instance = CertificateFactory.getInstance("X.509");
        ArrayList arrayList = new ArrayList();
        arrayList.add(x509Certificate);
        for (ByteString byteString : authResponse.getIntermediateCertificateList()) {
            try {
                arrayList.add((X509Certificate) instance.generateCertificate(byteString.newInput()));
            } catch (CertificateException unused) {
                return AuthResult.AuthErrorGeneric;
            }
        }
        CertPathValidator instance2 = CertPathValidator.getInstance("PKIX");
        CertPath generateCertPath = instance.generateCertPath(arrayList);
        HashSet hashSet = new HashSet();
        for (X509Certificate x509Certificate2 : this.mGoogleCastCA.getRootCerts()) {
            hashSet.add(new TrustAnchor(x509Certificate2, null));
        }
        PKIXParameters pKIXParameters = new PKIXParameters(hashSet);
        pKIXParameters.setRevocationEnabled(mEnableHashAlgorithmCheck);
        try {
            instance2.validate(generateCertPath, pKIXParameters);
            return AuthResult.AuthSuccess;
        } catch (CertPathValidatorException e) {
            Log.e(TAG, "Certificate does not chain to trusted root", e);
            return AuthResult.AuthErrorCertVerify;
        } catch (InvalidAlgorithmParameterException e2) {
            Log.e(TAG, "Could not find requested certificate algorithm", e2);
            return AuthResult.AuthErrorGeneric;
        }
    }

    private AuthResult verifyCredentials(CastProtocol.AuthResponse authResponse, ByteBuffer byteBuffer, X509Certificate x509Certificate) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException {
        return verifySignature(x509Certificate, authResponse.getSignature().toByteArray(), byteBuffer.array(), authResponse.getHashAlgorithm());
    }

    private AuthResult verifySignature(X509Certificate x509Certificate, byte[] bArr, byte[] bArr2, CastProtocol.HashAlgorithm hashAlgorithm) throws InvalidKeyException, NoSuchProviderException, NoSuchAlgorithmException {
        boolean z;
        try {
            Signature signature = getSignature(hashAlgorithm);
            signature.initVerify(x509Certificate.getPublicKey());
            signature.update(bArr2);
            z = signature.verify(bArr);
        } catch (SignatureException unused) {
            z = mEnableHashAlgorithmCheck;
        }
        if (z) {
            return AuthResult.AuthSuccess;
        }
        return AuthResult.AuthErrorSigNotVerified;
    }

    private Signature getSignature(CastProtocol.HashAlgorithm hashAlgorithm) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (hashAlgorithm.equals(CastProtocol.HashAlgorithm.SHA1)) {
            return Signature.getInstance("SHA1withRSA");
        }
        if (hashAlgorithm.equals(CastProtocol.HashAlgorithm.SHA256)) {
            return Signature.getInstance("SHA256WithRSA");
        }
        return null;
    }

    public enum AuthResult {
        AuthSuccess,
        AuthErrorGeneric,
        AuthErrorProtocol,
        AuthErrorNonceMismatch,
        AuthErrorCertVerify,
        AuthErrorCertRevoked,
        AuthErrorAudioOnly,
        AuthErrorSigNotVerified,
        AuthErrorCertExpired;

        public static boolean isSuccess(AuthResult authResult) {
            return !isError(authResult);
        }

        public static boolean isError(AuthResult authResult) {
            if (!AuthSuccess.equals(authResult)) {
                return true;
            }
            return AuthContext.mEnableHashAlgorithmCheck;
        }
    }
}
