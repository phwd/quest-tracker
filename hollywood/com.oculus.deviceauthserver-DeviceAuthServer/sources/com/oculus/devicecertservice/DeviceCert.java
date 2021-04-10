package com.oculus.devicecertservice;

import android.os.RemoteException;
import android.util.Log;
import com.android.org.bouncycastle.util.io.pem.PemReader;
import com.oculus.devicecertservice.DeviceCert;
import com.oculus.devicecertservice.DeviceCertInterface;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import vendor.oculus.hardware.devicecert.V1_0.IDeviceCert;
import vendor.oculus.hardware.devicecert.V1_0.Result;

public class DeviceCert implements DeviceCertInterface {
    private static final String CERTFILE_EXTENSION = ".crt";
    private static final String KEYFILE_EXTENSION = ".key";
    private static final String PROVISIONING_BASE_DIR = "/persist/provisioning/";
    private static final String TAG = "DeviceCertLibrary";
    private IDeviceCert deviceCertService = null;

    public DeviceCert() {
        try {
            this.deviceCertService = IDeviceCert.getService();
        } catch (RemoteException ex) {
            throw new RuntimeException("DeviceCert initialization failed", ex);
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public boolean generateRsaKeyPair(String alias) throws RemoteException {
        IDeviceCert iDeviceCert = this.deviceCertService;
        if (iDeviceCert != null) {
            int result = iDeviceCert.generateRsaKeyPairForCurrentSecureState(alias);
            if (result == 0) {
                return true;
            }
            throw new RuntimeException("devicecert returned error " + Result.toString(result));
        }
        throw new RuntimeException("devicecert interface is null");
    }

    /* access modifiers changed from: package-private */
    public class Wrapper<T> {
        public T value;

        Wrapper() {
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public RSAPublicKey getPublicKey(String alias, DeviceCertInterface.SecureState state) throws RemoteException {
        if (this.deviceCertService != null) {
            Wrapper<Integer> result = new Wrapper<>();
            Wrapper<byte[]> encodedKey = new Wrapper<>();
            this.deviceCertService.getPublicKey(alias, state.name(), new IDeviceCert.getPublicKeyCallback(result, encodedKey) {
                /* class com.oculus.devicecertservice.$$Lambda$DeviceCert$cw_Bd9I2t4vTzIEyybUiGvynslw */
                private final /* synthetic */ DeviceCert.Wrapper f$1;
                private final /* synthetic */ DeviceCert.Wrapper f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert.getPublicKeyCallback
                public final void onValues(int i, ArrayList arrayList) {
                    DeviceCert.this.lambda$getPublicKey$0$DeviceCert(this.f$1, this.f$2, i, arrayList);
                }
            });
            if (result.value.intValue() == 0) {
                byte pubExpLen = ((byte[]) encodedKey.value)[0];
                byte[] publicExponent = Arrays.copyOfRange((byte[]) encodedKey.value, 1, pubExpLen + 1);
                try {
                    try {
                        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, Arrays.copyOfRange((byte[]) encodedKey.value, pubExpLen + 1, ((byte[]) encodedKey.value).length)), new BigInteger(1, publicExponent)));
                    } catch (Exception e) {
                        Log.e(TAG, "getPublicKey failed, Error " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            } else {
                throw new RuntimeException("devicecert returned error " + Result.toString(result.value.intValue()));
            }
        } else {
            throw new RuntimeException("devicecert interface is null");
        }
    }

    public /* synthetic */ void lambda$getPublicKey$0$DeviceCert(Wrapper result, Wrapper encodedKey, int r, ArrayList d) {
        result.value = (T) Integer.valueOf(r);
        if (result.value.intValue() == 0) {
            encodedKey.value = (T) toByteArray(d);
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public byte[] sign(String alias, String algorithm, byte[] msg) throws RemoteException {
        String digestAlgo;
        if (this.deviceCertService == null) {
            throw new RuntimeException("devicecert interface is null");
        } else if (algorithm.endsWith("withRSA")) {
            if (algorithm.startsWith("SHA256")) {
                digestAlgo = "SHA-256";
            } else if (algorithm.startsWith("SHA384")) {
                digestAlgo = "SHA-384";
            } else if (algorithm.startsWith("SHA512")) {
                digestAlgo = "SHA-512";
            } else {
                throw new RuntimeException("unsupported signature algorithm");
            }
            try {
                byte[] digestMsg = MessageDigest.getInstance(digestAlgo).digest(msg);
                Wrapper<Integer> result = new Wrapper<>();
                Wrapper<byte[]> signedData = new Wrapper<>();
                this.deviceCertService.sign(alias, algorithm, toByteArrayList(digestMsg), new IDeviceCert.signCallback(result, signedData) {
                    /* class com.oculus.devicecertservice.$$Lambda$DeviceCert$HSDbEYpSAWSSLblwxpQQ27dukzM */
                    private final /* synthetic */ DeviceCert.Wrapper f$1;
                    private final /* synthetic */ DeviceCert.Wrapper f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert.signCallback
                    public final void onValues(int i, ArrayList arrayList) {
                        DeviceCert.this.lambda$sign$1$DeviceCert(this.f$1, this.f$2, i, arrayList);
                    }
                });
                if (result.value.intValue() == 0) {
                    return (byte[]) signedData.value;
                }
                throw new RuntimeException("devicecert returned error " + Result.toString(result.value.intValue()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("unsupported signature algorithm");
        }
    }

    public /* synthetic */ void lambda$sign$1$DeviceCert(Wrapper result, Wrapper signedData, int r, ArrayList d) {
        result.value = (T) Integer.valueOf(r);
        if (result.value.intValue() == 0) {
            signedData.value = (T) toByteArray(d);
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public void storeCertificate(String alias, DeviceCertInterface.SecureState state, X509Certificate certificate) throws RemoteException {
        IDeviceCert iDeviceCert = this.deviceCertService;
        if (iDeviceCert != null) {
            try {
                int result = iDeviceCert.storeCertificate(alias, state.name(), toByteArrayList(certificate.getEncoded()));
                if (result != 0) {
                    throw new RuntimeException("devicecert returned error " + Result.toString(result));
                }
            } catch (CertificateEncodingException e) {
                Log.e(TAG, "storeCertificate failed, Error: " + e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("devicecert interface is null");
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public X509Certificate loadCertificate(String alias, DeviceCertInterface.SecureState state) throws RemoteException {
        if (this.deviceCertService != null) {
            Wrapper<Integer> result = new Wrapper<>();
            Wrapper<byte[]> certEncoded = new Wrapper<>();
            this.deviceCertService.loadCertificate(alias, state.name(), new IDeviceCert.loadCertificateCallback(result, certEncoded) {
                /* class com.oculus.devicecertservice.$$Lambda$DeviceCert$qAexM1rMz8P4pHric6GMrGeT9OM */
                private final /* synthetic */ DeviceCert.Wrapper f$1;
                private final /* synthetic */ DeviceCert.Wrapper f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert.loadCertificateCallback
                public final void onValues(int i, ArrayList arrayList) {
                    DeviceCert.this.lambda$loadCertificate$2$DeviceCert(this.f$1, this.f$2, i, arrayList);
                }
            });
            if (result.value.intValue() == 0) {
                try {
                    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream((byte[]) certEncoded.value));
                } catch (Exception e) {
                    Log.e(TAG, "load certificate failed, Error " + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("devicecert returned error " + Result.toString(result.value.intValue()));
            }
        } else {
            throw new RuntimeException("devicecert interface is null");
        }
    }

    public /* synthetic */ void lambda$loadCertificate$2$DeviceCert(Wrapper result, Wrapper certEncoded, int r, ArrayList d) {
        result.value = (T) Integer.valueOf(r);
        if (result.value.intValue() == 0) {
            certEncoded.value = (T) toByteArray(d);
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public boolean containsAlias(String alias) throws RemoteException {
        if (this.deviceCertService != null) {
            Wrapper<Integer> result = new Wrapper<>();
            Wrapper<Boolean> valid = new Wrapper<>();
            this.deviceCertService.verifyKeyForCurrentSecureState(alias, new IDeviceCert.verifyKeyForCurrentSecureStateCallback(valid) {
                /* class com.oculus.devicecertservice.$$Lambda$DeviceCert$u1k8TUQERI4_ETXcx7rqGQ_eryY */
                private final /* synthetic */ DeviceCert.Wrapper f$1;

                {
                    this.f$1 = r2;
                }

                @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert.verifyKeyForCurrentSecureStateCallback
                public final void onValues(int i, boolean z) {
                    DeviceCert.lambda$containsAlias$3(DeviceCert.Wrapper.this, this.f$1, i, z);
                }
            });
            Log.d(TAG, String.format("containsAlias received status: %d", result.value));
            if (result.value.intValue() == 0) {
                return valid.value.booleanValue();
            }
            throw new RuntimeException("devicecert returned error " + Result.toString(result.value.intValue()));
        }
        throw new RuntimeException("devicecert interface is null");
    }

    static /* synthetic */ void lambda$containsAlias$3(Wrapper result, Wrapper valid, int r, boolean v) {
        Log.d(TAG, String.format("verifyKeyForCurrentSecureState returned status: %d", Integer.valueOf(r)));
        result.value = (T) Integer.valueOf(r);
        if (result.value.intValue() == 0) {
            valid.value = (T) Boolean.valueOf(v);
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public DeviceCertInterface.SecureState getDeviceSecureState() throws RemoteException {
        if (this.deviceCertService != null) {
            Wrapper<Integer> result = new Wrapper<>();
            Wrapper<Boolean> isLocked = new Wrapper<>();
            this.deviceCertService.getDeviceLockState(new IDeviceCert.getDeviceLockStateCallback(isLocked) {
                /* class com.oculus.devicecertservice.$$Lambda$DeviceCert$LfQczgNDt2NHkA3EWSQQiPlngNQ */
                private final /* synthetic */ DeviceCert.Wrapper f$1;

                {
                    this.f$1 = r2;
                }

                @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert.getDeviceLockStateCallback
                public final void onValues(int i, boolean z) {
                    DeviceCert.lambda$getDeviceSecureState$4(DeviceCert.Wrapper.this, this.f$1, i, z);
                }
            });
            if (isLocked.value.booleanValue()) {
                return DeviceCertInterface.SecureState.SECURE;
            }
            return DeviceCertInterface.SecureState.INSECURE;
        }
        throw new RuntimeException("devicecert interface is null");
    }

    static /* synthetic */ void lambda$getDeviceSecureState$4(Wrapper result, Wrapper isLocked, int r, boolean ls) {
        result.value = (T) Integer.valueOf(r);
        if (result.value.intValue() == 0) {
            isLocked.value = (T) Boolean.valueOf(ls);
        }
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public void prototypeProvision(String alias) throws RemoteException {
        if (this.deviceCertService != null) {
            try {
                File keyFile = new File(PROVISIONING_BASE_DIR + alias + KEYFILE_EXTENSION);
                if (keyFile.getCanonicalPath().startsWith(PROVISIONING_BASE_DIR)) {
                    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(new PemReader(new FileReader(keyFile)).readPemObject().getContent()));
                    byte[] privateExponentBytes = rsaPrivateKey.getPrivateExponent().toByteArray();
                    if (privateExponentBytes[0] == 0) {
                        privateExponentBytes = Arrays.copyOfRange(privateExponentBytes, 1, privateExponentBytes.length);
                    }
                    byte[] modulusBytes = rsaPrivateKey.getModulus().toByteArray();
                    if (modulusBytes[0] == 0) {
                        modulusBytes = Arrays.copyOfRange(modulusBytes, 1, modulusBytes.length);
                    }
                    this.deviceCertService.prototypeProvision(alias, toByteArrayList(privateExponentBytes), toByteArrayList(modulusBytes), toByteArrayList(((RSAPublicKey) ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(new File(PROVISIONING_BASE_DIR + alias + CERTFILE_EXTENSION)))).getPublicKey()).getPublicExponent().toByteArray()));
                    return;
                }
                throw new IllegalArgumentException("bad alias - directory traversal not allowed");
            } catch (Exception e) {
                Log.e(TAG, "prototypeProvision failed, Error " + e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("devicecert interface is null");
        }
    }

    private byte[] toByteArray(ArrayList<Byte> list) {
        byte[] result = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).byteValue();
        }
        return result;
    }

    private ArrayList<Byte> toByteArrayList(byte[] array) {
        ArrayList<Byte> result = new ArrayList<>();
        for (byte b : array) {
            result.add(Byte.valueOf(b));
        }
        return result;
    }

    @Override // com.oculus.devicecertservice.DeviceCertInterface
    public DeviceCertInterface.ProvisionState getDeviceProvisionState() throws RemoteException {
        try {
            return new DeviceCertInterface.ProvisionState(DeviceCertInterface.ProvisionType.FACTORY, extractDeviceSerialFromCert(loadCertificate("device_identity", DeviceCertInterface.SecureState.SECURE)));
        } catch (Exception e) {
            Log.i(TAG, "Unable to load secure device identity certificate.");
            try {
                return new DeviceCertInterface.ProvisionState(DeviceCertInterface.ProvisionType.PROTOTYPE, extractDeviceSerialFromCert(loadCertificate("device_identity", DeviceCertInterface.SecureState.INSECURE)));
            } catch (Exception e2) {
                Log.i(TAG, "Unable to load insecure device identity certificate.");
                return new DeviceCertInterface.ProvisionState(DeviceCertInterface.ProvisionType.UNPROVISIONED, "N/A");
            }
        }
    }

    private String extractDeviceSerialFromCert(X509Certificate certificate) {
        byte[] certSerialRaw = certificate.getExtensionValue("1.3.6.1.4.1.40981.2.3.9");
        if (certSerialRaw == null || certSerialRaw.length < 2) {
            return "<missing>";
        }
        if (certSerialRaw[0] != 4) {
            return "<invalid>";
        }
        return new String(certSerialRaw, 2, certSerialRaw[1], StandardCharsets.UTF_8);
    }
}
