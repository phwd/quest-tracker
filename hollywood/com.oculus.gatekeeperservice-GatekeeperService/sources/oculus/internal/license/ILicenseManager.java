package oculus.internal.license;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.license.Category;
import com.oculus.license.EvaluationResult;
import com.oculus.license.License;
import com.oculus.license.UserAction;
import com.oculus.license.VerificationResult;
import com.oculus.os.PackageMetadata;
import java.util.List;

public interface ILicenseManager extends IInterface {
    EvaluationResult evaluateActionAgainstPackageId(UserAction userAction, List<String> list, String str) throws RemoteException;

    EvaluationResult evaluateActionAgainstPackageMetadata(UserAction userAction, List<String> list, PackageMetadata packageMetadata) throws RemoteException;

    License installLicense(byte[] bArr) throws RemoteException;

    License[] installLicenses(LicenseCollection licenseCollection, int i) throws RemoteException;

    License[] queryByCategoryAndSecurityPrincipals(Category category, List<String> list) throws RemoteException;

    License[] queryByPackage(PackageMetadata packageMetadata) throws RemoteException;

    License[] queryLicenses(String str, String[] strArr, String str2) throws RemoteException;

    boolean registerLicenseSigner(byte[] bArr) throws RemoteException;

    boolean reset() throws RemoteException;

    void restart() throws RemoteException;

    boolean revokeLicenses(long[] jArr) throws RemoteException;

    boolean unrevokeLicenses(long[] jArr) throws RemoteException;

    VerificationResult verifyActionAgainstPackageId(UserAction userAction, List<String> list, String str) throws RemoteException;

    VerificationResult verifyActionAgainstPackageMetadata(UserAction userAction, List<String> list, PackageMetadata packageMetadata) throws RemoteException;

    public static class Default implements ILicenseManager {
        @Override // oculus.internal.license.ILicenseManager
        public License installLicense(byte[] licenseBlob) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public License[] installLicenses(LicenseCollection licenseBlobs, int flags) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public VerificationResult verifyActionAgainstPackageMetadata(UserAction userAction, List<String> list, PackageMetadata packageMetadata) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public VerificationResult verifyActionAgainstPackageId(UserAction userAction, List<String> list, String packageName) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public EvaluationResult evaluateActionAgainstPackageMetadata(UserAction userAction, List<String> list, PackageMetadata packageMetadata) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public EvaluationResult evaluateActionAgainstPackageId(UserAction userAction, List<String> list, String packageName) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public boolean revokeLicenses(long[] fbids) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.license.ILicenseManager
        public boolean unrevokeLicenses(long[] fbids) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.license.ILicenseManager
        public License[] queryByCategoryAndSecurityPrincipals(Category category, List<String> list) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public License[] queryLicenses(String selection, String[] selectionArgs, String orderBy) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public License[] queryByPackage(PackageMetadata packageMetadata) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.license.ILicenseManager
        public boolean registerLicenseSigner(byte[] encodedCertificate) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.license.ILicenseManager
        public boolean reset() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.license.ILicenseManager
        public void restart() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILicenseManager {
        private static final String DESCRIPTOR = "oculus.internal.license.ILicenseManager";
        static final int TRANSACTION_evaluateActionAgainstPackageId = 6;
        static final int TRANSACTION_evaluateActionAgainstPackageMetadata = 5;
        static final int TRANSACTION_installLicense = 1;
        static final int TRANSACTION_installLicenses = 2;
        static final int TRANSACTION_queryByCategoryAndSecurityPrincipals = 9;
        static final int TRANSACTION_queryByPackage = 11;
        static final int TRANSACTION_queryLicenses = 10;
        static final int TRANSACTION_registerLicenseSigner = 12;
        static final int TRANSACTION_reset = 13;
        static final int TRANSACTION_restart = 14;
        static final int TRANSACTION_revokeLicenses = 7;
        static final int TRANSACTION_unrevokeLicenses = 8;
        static final int TRANSACTION_verifyActionAgainstPackageId = 4;
        static final int TRANSACTION_verifyActionAgainstPackageMetadata = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILicenseManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILicenseManager)) {
                return new Proxy(obj);
            }
            return (ILicenseManager) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            LicenseCollection _arg0;
            UserAction _arg02;
            PackageMetadata _arg2;
            UserAction _arg03;
            UserAction _arg04;
            PackageMetadata _arg22;
            UserAction _arg05;
            Category _arg06;
            PackageMetadata _arg07;
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        License _result = installLicense(data.createByteArray());
                        reply.writeNoException();
                        if (_result != null) {
                            reply.writeInt(1);
                            _result.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg0 = LicenseCollection.CREATOR.createFromParcel(data);
                        } else {
                            _arg0 = null;
                        }
                        License[] _result2 = installLicenses(_arg0, data.readInt());
                        reply.writeNoException();
                        reply.writeTypedArray(_result2, 1);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg02 = UserAction.CREATOR.createFromParcel(data);
                        } else {
                            _arg02 = null;
                        }
                        List<String> _arg1 = data.createStringArrayList();
                        if (data.readInt() != 0) {
                            _arg2 = PackageMetadata.CREATOR.createFromParcel(data);
                        } else {
                            _arg2 = null;
                        }
                        VerificationResult _result3 = verifyActionAgainstPackageMetadata(_arg02, _arg1, _arg2);
                        reply.writeNoException();
                        if (_result3 != null) {
                            reply.writeInt(1);
                            _result3.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg03 = UserAction.CREATOR.createFromParcel(data);
                        } else {
                            _arg03 = null;
                        }
                        VerificationResult _result4 = verifyActionAgainstPackageId(_arg03, data.createStringArrayList(), data.readString());
                        reply.writeNoException();
                        if (_result4 != null) {
                            reply.writeInt(1);
                            _result4.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg04 = UserAction.CREATOR.createFromParcel(data);
                        } else {
                            _arg04 = null;
                        }
                        List<String> _arg12 = data.createStringArrayList();
                        if (data.readInt() != 0) {
                            _arg22 = PackageMetadata.CREATOR.createFromParcel(data);
                        } else {
                            _arg22 = null;
                        }
                        EvaluationResult _result5 = evaluateActionAgainstPackageMetadata(_arg04, _arg12, _arg22);
                        reply.writeNoException();
                        if (_result5 != null) {
                            reply.writeInt(1);
                            _result5.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg05 = UserAction.CREATOR.createFromParcel(data);
                        } else {
                            _arg05 = null;
                        }
                        EvaluationResult _result6 = evaluateActionAgainstPackageId(_arg05, data.createStringArrayList(), data.readString());
                        reply.writeNoException();
                        if (_result6 != null) {
                            reply.writeInt(1);
                            _result6.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        boolean revokeLicenses = revokeLicenses(data.createLongArray());
                        reply.writeNoException();
                        reply.writeInt(revokeLicenses ? 1 : 0);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        boolean unrevokeLicenses = unrevokeLicenses(data.createLongArray());
                        reply.writeNoException();
                        reply.writeInt(unrevokeLicenses ? 1 : 0);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg06 = Category.CREATOR.createFromParcel(data);
                        } else {
                            _arg06 = null;
                        }
                        License[] _result7 = queryByCategoryAndSecurityPrincipals(_arg06, data.createStringArrayList());
                        reply.writeNoException();
                        reply.writeTypedArray(_result7, 1);
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        License[] _result8 = queryLicenses(data.readString(), data.createStringArray(), data.readString());
                        reply.writeNoException();
                        reply.writeTypedArray(_result8, 1);
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg07 = PackageMetadata.CREATOR.createFromParcel(data);
                        } else {
                            _arg07 = null;
                        }
                        License[] _result9 = queryByPackage(_arg07);
                        reply.writeNoException();
                        reply.writeTypedArray(_result9, 1);
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        boolean registerLicenseSigner = registerLicenseSigner(data.createByteArray());
                        reply.writeNoException();
                        reply.writeInt(registerLicenseSigner ? 1 : 0);
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        boolean reset = reset();
                        reply.writeNoException();
                        reply.writeInt(reset ? 1 : 0);
                        return true;
                    case 14:
                        data.enforceInterface(DESCRIPTOR);
                        restart();
                        reply.writeNoException();
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ILicenseManager {
            public static ILicenseManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // oculus.internal.license.ILicenseManager
            public License installLicense(byte[] licenseBlob) throws RemoteException {
                License _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(licenseBlob);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().installLicense(licenseBlob);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = License.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public License[] installLicenses(LicenseCollection licenseBlobs, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (licenseBlobs != null) {
                        _data.writeInt(1);
                        licenseBlobs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flags);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().installLicenses(licenseBlobs, flags);
                    }
                    _reply.readException();
                    License[] _result = (License[]) _reply.createTypedArray(License.CREATOR);
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public VerificationResult verifyActionAgainstPackageMetadata(UserAction userAction, List<String> activeSecurityPrincipals, PackageMetadata packageMetadata) throws RemoteException {
                VerificationResult _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userAction != null) {
                        _data.writeInt(1);
                        userAction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(activeSecurityPrincipals);
                    if (packageMetadata != null) {
                        _data.writeInt(1);
                        packageMetadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyActionAgainstPackageMetadata(userAction, activeSecurityPrincipals, packageMetadata);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VerificationResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public VerificationResult verifyActionAgainstPackageId(UserAction userAction, List<String> activeSecurityPrincipals, String packageName) throws RemoteException {
                VerificationResult _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userAction != null) {
                        _data.writeInt(1);
                        userAction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(activeSecurityPrincipals);
                    _data.writeString(packageName);
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyActionAgainstPackageId(userAction, activeSecurityPrincipals, packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VerificationResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public EvaluationResult evaluateActionAgainstPackageMetadata(UserAction userAction, List<String> activeSecurityPrincipals, PackageMetadata packageMetadata) throws RemoteException {
                EvaluationResult _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userAction != null) {
                        _data.writeInt(1);
                        userAction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(activeSecurityPrincipals);
                    if (packageMetadata != null) {
                        _data.writeInt(1);
                        packageMetadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().evaluateActionAgainstPackageMetadata(userAction, activeSecurityPrincipals, packageMetadata);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = EvaluationResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public EvaluationResult evaluateActionAgainstPackageId(UserAction userAction, List<String> activeSecurityPrincipals, String packageName) throws RemoteException {
                EvaluationResult _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userAction != null) {
                        _data.writeInt(1);
                        userAction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(activeSecurityPrincipals);
                    _data.writeString(packageName);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().evaluateActionAgainstPackageId(userAction, activeSecurityPrincipals, packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = EvaluationResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public boolean revokeLicenses(long[] fbids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLongArray(fbids);
                    boolean _result = false;
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().revokeLicenses(fbids);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public boolean unrevokeLicenses(long[] fbids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLongArray(fbids);
                    boolean _result = false;
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unrevokeLicenses(fbids);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public License[] queryByCategoryAndSecurityPrincipals(Category category, List<String> activeSecurityPrincipals) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (category != null) {
                        _data.writeInt(1);
                        category.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(activeSecurityPrincipals);
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryByCategoryAndSecurityPrincipals(category, activeSecurityPrincipals);
                    }
                    _reply.readException();
                    License[] _result = (License[]) _reply.createTypedArray(License.CREATOR);
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public License[] queryLicenses(String selection, String[] selectionArgs, String orderBy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(selection);
                    _data.writeStringArray(selectionArgs);
                    _data.writeString(orderBy);
                    if (!this.mRemote.transact(10, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryLicenses(selection, selectionArgs, orderBy);
                    }
                    _reply.readException();
                    License[] _result = (License[]) _reply.createTypedArray(License.CREATOR);
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public License[] queryByPackage(PackageMetadata packageMetadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (packageMetadata != null) {
                        _data.writeInt(1);
                        packageMetadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryByPackage(packageMetadata);
                    }
                    _reply.readException();
                    License[] _result = (License[]) _reply.createTypedArray(License.CREATOR);
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public boolean registerLicenseSigner(byte[] encodedCertificate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(encodedCertificate);
                    boolean _result = false;
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerLicenseSigner(encodedCertificate);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public boolean reset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(13, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reset();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.license.ILicenseManager
            public void restart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(14, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().restart();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILicenseManager impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILicenseManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
