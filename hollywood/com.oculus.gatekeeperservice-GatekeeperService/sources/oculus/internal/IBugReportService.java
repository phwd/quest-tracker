package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.os.BugReport;

public interface IBugReportService extends IInterface {
    boolean cancelBugReport(BugReport bugReport) throws RemoteException;

    String createBugReport(boolean z) throws RemoteException;

    boolean submitBugReport(BugReport bugReport) throws RemoteException;

    public static class Default implements IBugReportService {
        @Override // oculus.internal.IBugReportService
        public String createBugReport(boolean collectLogs) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IBugReportService
        public boolean submitBugReport(BugReport bugReport) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IBugReportService
        public boolean cancelBugReport(BugReport bugReport) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IBugReportService {
        private static final String DESCRIPTOR = "oculus.internal.IBugReportService";
        static final int TRANSACTION_cancelBugReport = 3;
        static final int TRANSACTION_createBugReport = 1;
        static final int TRANSACTION_submitBugReport = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBugReportService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IBugReportService)) {
                return new Proxy(obj);
            }
            return (IBugReportService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BugReport _arg0;
            BugReport _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String _result = createBugReport(data.readInt() != 0);
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = BugReport.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                boolean submitBugReport = submitBugReport(_arg0);
                reply.writeNoException();
                reply.writeInt(submitBugReport ? 1 : 0);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = BugReport.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                boolean cancelBugReport = cancelBugReport(_arg02);
                reply.writeNoException();
                reply.writeInt(cancelBugReport ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IBugReportService {
            public static IBugReportService sDefaultImpl;
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

            @Override // oculus.internal.IBugReportService
            public String createBugReport(boolean collectLogs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(collectLogs ? 1 : 0);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createBugReport(collectLogs);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IBugReportService
            public boolean submitBugReport(BugReport bugReport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (bugReport != null) {
                        _data.writeInt(1);
                        bugReport.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().submitBugReport(bugReport);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IBugReportService
            public boolean cancelBugReport(BugReport bugReport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (bugReport != null) {
                        _data.writeInt(1);
                        bugReport.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelBugReport(bugReport);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBugReportService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IBugReportService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
