package vendor.oculus.hardware.devicecert.V1_0;

import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public interface IDeviceCert extends IBase {

    @FunctionalInterface
    public interface loadCertificateCallback {
        void onValues(int i, ArrayList<Byte> arrayList);
    }

    @FunctionalInterface
    public interface signCallback {
        void onValues(int i, ArrayList<Byte> arrayList);
    }

    @FunctionalInterface
    public interface verifyKeyForCurrentSecureStateCallback {
        void onValues(int i, boolean z);
    }

    ArrayList<String> interfaceChain() throws RemoteException;

    void loadCertificate(String str, String str2, loadCertificateCallback loadcertificatecallback) throws RemoteException;

    void sign(String str, String str2, ArrayList<Byte> arrayList, signCallback signcallback) throws RemoteException;

    void verifyKeyForCurrentSecureState(String str, verifyKeyForCurrentSecureStateCallback verifykeyforcurrentsecurestatecallback) throws RemoteException;

    static default IDeviceCert asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IDeviceCert queryLocalInterface = iHwBinder.queryLocalInterface("vendor.oculus.hardware.devicecert@1.0::IDeviceCert");
        if (queryLocalInterface != null && (queryLocalInterface instanceof IDeviceCert)) {
            return queryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.oculus.hardware.devicecert@1.0::IDeviceCert")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static default IDeviceCert getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.oculus.hardware.devicecert@1.0::IDeviceCert", str));
    }

    static default IDeviceCert getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IDeviceCert {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.oculus.hardware.devicecert@1.0::IDeviceCert]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert
        public void verifyKeyForCurrentSecureState(String str, verifyKeyForCurrentSecureStateCallback verifykeyforcurrentsecurestatecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.oculus.hardware.devicecert@1.0::IDeviceCert");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                verifykeyforcurrentsecurestatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readBool());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert
        public void sign(String str, String str2, ArrayList<Byte> arrayList, signCallback signcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.oculus.hardware.devicecert@1.0::IDeviceCert");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                signcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert
        public void loadCertificate(String str, String str2, loadCertificateCallback loadcertificatecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.oculus.hardware.devicecert@1.0::IDeviceCert");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                loadcertificatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.oculus.hardware.devicecert.V1_0.IDeviceCert
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }
    }
}
