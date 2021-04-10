package com.oculus.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.oculus.aidl.IAuthenticationCallback;

public interface IAuthService2 extends IInterface {

    public class Default implements IAuthService2 {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IAuthService2
        public void fetchCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback) {
        }

        @Override // com.oculus.aidl.IAuthService2
        public void fetchDeviceScopedCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback) {
        }

        @Override // com.oculus.aidl.IAuthService2
        public Bundle getCredentials() {
            return null;
        }

        @Override // com.oculus.aidl.IAuthService2
        public Bundle getDeviceScopedCredentials() {
            return null;
        }
    }

    public abstract class Stub extends Binder implements IAuthService2 {
        public static final String DESCRIPTOR = "com.oculus.aidl.IAuthService2";
        public static final int TRANSACTION_fetchCredentials = 3;
        public static final int TRANSACTION_fetchDeviceScopedCredentials = 4;
        public static final int TRANSACTION_getCredentials = 1;
        public static final int TRANSACTION_getDeviceScopedCredentials = 2;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            Bundle bundle;
            boolean z = false;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                bundle = getCredentials();
            } else if (i != 2) {
                if (i == 3) {
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    fetchCredentials(readString, readString2, z, IAuthenticationCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i == 4) {
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString3 = parcel.readString();
                    String readString4 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    fetchDeviceScopedCredentials(readString3, readString4, z, IAuthenticationCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                parcel2.writeNoException();
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                bundle = getDeviceScopedCredentials();
            }
            parcel2.writeNoException();
            if (bundle != null) {
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
                return true;
            }
            parcel2.writeInt(0);
            return true;
        }

        public class Proxy implements IAuthService2 {
            public static IAuthService2 sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.aidl.IAuthService2
            public void fetchCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback) {
                IBinder iBinder;
                IAuthService2 iAuthService2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (iAuthenticationCallback != null) {
                        iBinder = iAuthenticationCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iAuthService2 = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iAuthService2.fetchCredentials(str, str2, z, iAuthenticationCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthService2
            public void fetchDeviceScopedCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback) {
                IBinder iBinder;
                IAuthService2 iAuthService2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (iAuthenticationCallback != null) {
                        iBinder = iAuthenticationCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iAuthService2 = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iAuthService2.fetchDeviceScopedCredentials(str, str2, z, iAuthenticationCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getCredentials() {
                Bundle bundle;
                IAuthService2 iAuthService2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iAuthService2 = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = iAuthService2.getCredentials();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getDeviceScopedCredentials() {
                Bundle bundle;
                IAuthService2 iAuthService2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iAuthService2 = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = iAuthService2.getDeviceScopedCredentials();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IAuthService2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAuthService2)) {
                return new Proxy(iBinder);
            }
            return (IAuthService2) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IAuthService2 iAuthService2) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iAuthService2 == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iAuthService2;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthService2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void fetchCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback);

    void fetchDeviceScopedCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback);

    Bundle getCredentials();

    Bundle getDeviceScopedCredentials();
}
