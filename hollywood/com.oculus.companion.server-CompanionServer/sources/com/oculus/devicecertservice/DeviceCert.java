package com.oculus.devicecertservice;

import android.os.RemoteException;
import android.util.Log;
import com.oculus.devicecertservice.DeviceCert;
import com.oculus.devicecertservice.DeviceCertInterface;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import vendor.oculus.hardware.devicecert.V1_0.IDeviceCert;
import vendor.oculus.hardware.devicecert.V1_0.Result;

public class DeviceCert implements DeviceCertInterface {
    private IDeviceCert deviceCertService = null;

    public DeviceCert() {
        try {
            this.deviceCertService = IDeviceCert.getService();
        } catch (RemoteException e) {
            throw new RuntimeException("DeviceCert initialization failed", e);
        }
    }

    /* access modifiers changed from: package-private */
    public class Wrapper<T> {
        public T value;

        Wrapper() {
        }
    }

    public byte[] sign(String str, String str2, byte[] bArr) throws RemoteException {
        String str3;
        if (this.deviceCertService == null) {
            throw new RuntimeException("devicecert interface is null");
        } else if (str2.endsWith("withRSA")) {
            if (str2.startsWith("SHA256")) {
                str3 = "SHA-256";
            } else if (str2.startsWith("SHA384")) {
                str3 = "SHA-384";
            } else if (str2.startsWith("SHA512")) {
                str3 = "SHA-512";
            } else {
                throw new RuntimeException("unsupported signature algorithm");
            }
            try {
                byte[] digest = MessageDigest.getInstance(str3).digest(bArr);
                Wrapper wrapper = new Wrapper();
                Wrapper wrapper2 = new Wrapper();
                this.deviceCertService.sign(str, str2, toByteArrayList(digest), new IDeviceCert.signCallback(wrapper, wrapper2) {
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
                if (wrapper.value.intValue() == 0) {
                    return (byte[]) wrapper2.value;
                }
                throw new RuntimeException("devicecert returned error " + Result.toString(wrapper.value.intValue()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("unsupported signature algorithm");
        }
    }

    public /* synthetic */ void lambda$sign$1$DeviceCert(Wrapper wrapper, Wrapper wrapper2, int i, ArrayList arrayList) {
        wrapper.value = (T) Integer.valueOf(i);
        if (wrapper.value.intValue() == 0) {
            wrapper2.value = (T) toByteArray(arrayList);
        }
    }

    public X509Certificate loadCertificate(String str, DeviceCertInterface.SecureState secureState) throws RemoteException {
        if (this.deviceCertService != null) {
            Wrapper wrapper = new Wrapper();
            Wrapper wrapper2 = new Wrapper();
            this.deviceCertService.loadCertificate(str, secureState.name(), new IDeviceCert.loadCertificateCallback(wrapper, wrapper2) {
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
            if (wrapper.value.intValue() == 0) {
                try {
                    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream((byte[]) wrapper2.value));
                } catch (Exception e) {
                    Log.e("DeviceCertLibrary", "load certificate failed, Error " + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("devicecert returned error " + Result.toString(wrapper.value.intValue()));
            }
        } else {
            throw new RuntimeException("devicecert interface is null");
        }
    }

    public /* synthetic */ void lambda$loadCertificate$2$DeviceCert(Wrapper wrapper, Wrapper wrapper2, int i, ArrayList arrayList) {
        wrapper.value = (T) Integer.valueOf(i);
        if (wrapper.value.intValue() == 0) {
            wrapper2.value = (T) toByteArray(arrayList);
        }
    }

    public boolean containsAlias(String str) throws RemoteException {
        if (this.deviceCertService != null) {
            Wrapper wrapper = new Wrapper();
            Wrapper wrapper2 = new Wrapper();
            this.deviceCertService.verifyKeyForCurrentSecureState(str, new IDeviceCert.verifyKeyForCurrentSecureStateCallback(wrapper2) {
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
            Log.d("DeviceCertLibrary", String.format("containsAlias received status: %d", wrapper.value));
            if (wrapper.value.intValue() == 0) {
                return wrapper2.value.booleanValue();
            }
            throw new RuntimeException("devicecert returned error " + Result.toString(wrapper.value.intValue()));
        }
        throw new RuntimeException("devicecert interface is null");
    }

    static /* synthetic */ void lambda$containsAlias$3(Wrapper wrapper, Wrapper wrapper2, int i, boolean z) {
        Log.d("DeviceCertLibrary", String.format("verifyKeyForCurrentSecureState returned status: %d", Integer.valueOf(i)));
        wrapper.value = (T) Integer.valueOf(i);
        if (wrapper.value.intValue() == 0) {
            wrapper2.value = (T) Boolean.valueOf(z);
        }
    }

    private byte[] toByteArray(ArrayList<Byte> arrayList) {
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bArr;
    }

    private ArrayList<Byte> toByteArrayList(byte[] bArr) {
        ArrayList<Byte> arrayList = new ArrayList<>();
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    public DeviceCertInterface.ProvisionState getDeviceProvisionState() throws RemoteException {
        try {
            return new DeviceCertInterface.ProvisionState(DeviceCertInterface.ProvisionType.FACTORY, extractDeviceSerialFromCert(loadCertificate("device_identity", DeviceCertInterface.SecureState.SECURE)));
        } catch (Exception unused) {
            Log.i("DeviceCertLibrary", "Unable to load secure device identity certificate.");
            try {
                return new DeviceCertInterface.ProvisionState(DeviceCertInterface.ProvisionType.PROTOTYPE, extractDeviceSerialFromCert(loadCertificate("device_identity", DeviceCertInterface.SecureState.INSECURE)));
            } catch (Exception unused2) {
                Log.i("DeviceCertLibrary", "Unable to load insecure device identity certificate.");
                return new DeviceCertInterface.ProvisionState(DeviceCertInterface.ProvisionType.UNPROVISIONED, "N/A");
            }
        }
    }

    private String extractDeviceSerialFromCert(X509Certificate x509Certificate) {
        byte[] extensionValue = x509Certificate.getExtensionValue("1.3.6.1.4.1.40981.2.3.9");
        if (extensionValue == null || extensionValue.length < 2) {
            return "<missing>";
        }
        if (extensionValue[0] != 4) {
            return "<invalid>";
        }
        return new String(extensionValue, 2, extensionValue[1], StandardCharsets.UTF_8);
    }
}
