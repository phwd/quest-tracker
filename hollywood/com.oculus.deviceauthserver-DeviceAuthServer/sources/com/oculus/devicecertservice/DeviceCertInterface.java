package com.oculus.devicecertservice;

import android.os.RemoteException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

public interface DeviceCertInterface {

    public enum ProvisionType {
        FACTORY,
        PROTOTYPE,
        UNPROVISIONED
    }

    public enum SecureState {
        CURRENT,
        SECURE,
        INSECURE
    }

    boolean containsAlias(String str) throws RemoteException;

    boolean generateRsaKeyPair(String str) throws RemoteException;

    ProvisionState getDeviceProvisionState() throws RemoteException;

    SecureState getDeviceSecureState() throws RemoteException;

    RSAPublicKey getPublicKey(String str, SecureState secureState) throws RemoteException;

    X509Certificate loadCertificate(String str, SecureState secureState) throws RemoteException;

    void prototypeProvision(String str) throws RemoteException;

    byte[] sign(String str, String str2, byte[] bArr) throws RemoteException;

    void storeCertificate(String str, SecureState secureState, X509Certificate x509Certificate) throws RemoteException;

    public static class ProvisionState {
        public final String provisionSerial;
        public final ProvisionType provisionType;

        public ProvisionState(ProvisionType provisionType2, String provisionSerial2) {
            this.provisionType = provisionType2;
            this.provisionSerial = provisionSerial2;
        }
    }
}
