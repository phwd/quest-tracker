package android.os;

import android.os.IMaintenanceActivityListener;

public interface IDeviceIdleController extends IInterface {

    public static class Default implements IDeviceIdleController {
        @Override // android.os.IDeviceIdleController
        public void addPowerSaveTempWhitelistApp(String str, long j, int i, String str2) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public long addPowerSaveTempWhitelistAppForMms(String str, int i, String str2) throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public long addPowerSaveTempWhitelistAppForSms(String str, int i, String str2) throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public void addPowerSaveWhitelistApp(String str) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public void delayDoze(int i) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void exitIdle(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdTempWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdUserWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getFullPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getFullPowerWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int getIdleStateDetailed() throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public int getLightIdleStateDetailed() throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getSystemPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getSystemPowerWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getUserPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public boolean isPowerSaveWhitelistApp(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public boolean registerMaintenanceActivityListener(IMaintenanceActivityListener iMaintenanceActivityListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public void removePowerSaveWhitelistApp(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void unregisterMaintenanceActivityListener(IMaintenanceActivityListener iMaintenanceActivityListener) throws RemoteException {
        }
    }

    void addPowerSaveTempWhitelistApp(String str, long j, int i, String str2) throws RemoteException;

    long addPowerSaveTempWhitelistAppForMms(String str, int i, String str2) throws RemoteException;

    long addPowerSaveTempWhitelistAppForSms(String str, int i, String str2) throws RemoteException;

    void addPowerSaveWhitelistApp(String str) throws RemoteException;

    void delayDoze(int i) throws RemoteException;

    void exitIdle(String str) throws RemoteException;

    int[] getAppIdTempWhitelist() throws RemoteException;

    int[] getAppIdUserWhitelist() throws RemoteException;

    int[] getAppIdWhitelist() throws RemoteException;

    int[] getAppIdWhitelistExceptIdle() throws RemoteException;

    String[] getFullPowerWhitelist() throws RemoteException;

    String[] getFullPowerWhitelistExceptIdle() throws RemoteException;

    int getIdleStateDetailed() throws RemoteException;

    int getLightIdleStateDetailed() throws RemoteException;

    String[] getSystemPowerWhitelist() throws RemoteException;

    String[] getSystemPowerWhitelistExceptIdle() throws RemoteException;

    String[] getUserPowerWhitelist() throws RemoteException;

    boolean isPowerSaveWhitelistApp(String str) throws RemoteException;

    boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException;

    boolean registerMaintenanceActivityListener(IMaintenanceActivityListener iMaintenanceActivityListener) throws RemoteException;

    void removePowerSaveWhitelistApp(String str) throws RemoteException;

    void unregisterMaintenanceActivityListener(IMaintenanceActivityListener iMaintenanceActivityListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceIdleController {
        private static final String DESCRIPTOR = "android.os.IDeviceIdleController";
        static final int TRANSACTION_addPowerSaveTempWhitelistApp = 14;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForMms = 15;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForSms = 16;
        static final int TRANSACTION_addPowerSaveWhitelistApp = 1;
        static final int TRANSACTION_delayDoze = 22;
        static final int TRANSACTION_exitIdle = 17;
        static final int TRANSACTION_getAppIdTempWhitelist = 11;
        static final int TRANSACTION_getAppIdUserWhitelist = 10;
        static final int TRANSACTION_getAppIdWhitelist = 9;
        static final int TRANSACTION_getAppIdWhitelistExceptIdle = 8;
        static final int TRANSACTION_getFullPowerWhitelist = 7;
        static final int TRANSACTION_getFullPowerWhitelistExceptIdle = 6;
        static final int TRANSACTION_getIdleStateDetailed = 20;
        static final int TRANSACTION_getLightIdleStateDetailed = 21;
        static final int TRANSACTION_getSystemPowerWhitelist = 4;
        static final int TRANSACTION_getSystemPowerWhitelistExceptIdle = 3;
        static final int TRANSACTION_getUserPowerWhitelist = 5;
        static final int TRANSACTION_isPowerSaveWhitelistApp = 13;
        static final int TRANSACTION_isPowerSaveWhitelistExceptIdleApp = 12;
        static final int TRANSACTION_registerMaintenanceActivityListener = 18;
        static final int TRANSACTION_removePowerSaveWhitelistApp = 2;
        static final int TRANSACTION_unregisterMaintenanceActivityListener = 19;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceIdleController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceIdleController)) {
                return new Proxy(iBinder);
            }
            return (IDeviceIdleController) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        addPowerSaveWhitelistApp(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        removePowerSaveWhitelistApp(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] systemPowerWhitelistExceptIdle = getSystemPowerWhitelistExceptIdle();
                        parcel2.writeNoException();
                        parcel2.writeStringArray(systemPowerWhitelistExceptIdle);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] systemPowerWhitelist = getSystemPowerWhitelist();
                        parcel2.writeNoException();
                        parcel2.writeStringArray(systemPowerWhitelist);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] userPowerWhitelist = getUserPowerWhitelist();
                        parcel2.writeNoException();
                        parcel2.writeStringArray(userPowerWhitelist);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] fullPowerWhitelistExceptIdle = getFullPowerWhitelistExceptIdle();
                        parcel2.writeNoException();
                        parcel2.writeStringArray(fullPowerWhitelistExceptIdle);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] fullPowerWhitelist = getFullPowerWhitelist();
                        parcel2.writeNoException();
                        parcel2.writeStringArray(fullPowerWhitelist);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        int[] appIdWhitelistExceptIdle = getAppIdWhitelistExceptIdle();
                        parcel2.writeNoException();
                        parcel2.writeIntArray(appIdWhitelistExceptIdle);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        int[] appIdWhitelist = getAppIdWhitelist();
                        parcel2.writeNoException();
                        parcel2.writeIntArray(appIdWhitelist);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        int[] appIdUserWhitelist = getAppIdUserWhitelist();
                        parcel2.writeNoException();
                        parcel2.writeIntArray(appIdUserWhitelist);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        int[] appIdTempWhitelist = getAppIdTempWhitelist();
                        parcel2.writeNoException();
                        parcel2.writeIntArray(appIdTempWhitelist);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isPowerSaveWhitelistExceptIdleApp = isPowerSaveWhitelistExceptIdleApp(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isPowerSaveWhitelistExceptIdleApp ? 1 : 0);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isPowerSaveWhitelistApp = isPowerSaveWhitelistApp(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isPowerSaveWhitelistApp ? 1 : 0);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        addPowerSaveTempWhitelistApp(parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        long addPowerSaveTempWhitelistAppForMms = addPowerSaveTempWhitelistAppForMms(parcel.readString(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeLong(addPowerSaveTempWhitelistAppForMms);
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        long addPowerSaveTempWhitelistAppForSms = addPowerSaveTempWhitelistAppForSms(parcel.readString(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeLong(addPowerSaveTempWhitelistAppForSms);
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        exitIdle(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean registerMaintenanceActivityListener = registerMaintenanceActivityListener(IMaintenanceActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(registerMaintenanceActivityListener ? 1 : 0);
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterMaintenanceActivityListener(IMaintenanceActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        int idleStateDetailed = getIdleStateDetailed();
                        parcel2.writeNoException();
                        parcel2.writeInt(idleStateDetailed);
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        int lightIdleStateDetailed = getLightIdleStateDetailed();
                        parcel2.writeNoException();
                        parcel2.writeInt(lightIdleStateDetailed);
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        delayDoze(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IDeviceIdleController {
            public static IDeviceIdleController sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.os.IDeviceIdleController
            public void addPowerSaveWhitelistApp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().addPowerSaveWhitelistApp(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void removePowerSaveWhitelistApp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removePowerSaveWhitelistApp(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getSystemPowerWhitelistExceptIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPowerWhitelistExceptIdle();
                    }
                    obtain2.readException();
                    String[] createStringArray = obtain2.createStringArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createStringArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getSystemPowerWhitelist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPowerWhitelist();
                    }
                    obtain2.readException();
                    String[] createStringArray = obtain2.createStringArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createStringArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getUserPowerWhitelist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserPowerWhitelist();
                    }
                    obtain2.readException();
                    String[] createStringArray = obtain2.createStringArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createStringArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getFullPowerWhitelistExceptIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFullPowerWhitelistExceptIdle();
                    }
                    obtain2.readException();
                    String[] createStringArray = obtain2.createStringArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createStringArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getFullPowerWhitelist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFullPowerWhitelist();
                    }
                    obtain2.readException();
                    String[] createStringArray = obtain2.createStringArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createStringArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdWhitelistExceptIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdWhitelistExceptIdle();
                    }
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdWhitelist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdWhitelist();
                    }
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdUserWhitelist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdUserWhitelist();
                    }
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdTempWhitelist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdTempWhitelist();
                    }
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPowerSaveWhitelistExceptIdleApp(str);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public boolean isPowerSaveWhitelistApp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPowerSaveWhitelistApp(str);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void addPowerSaveTempWhitelistApp(String str, long j, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(14, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().addPowerSaveTempWhitelistApp(str, j, i, str2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long addPowerSaveTempWhitelistAppForMms(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveTempWhitelistAppForMms(str, i, str2);
                    }
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    obtain2.recycle();
                    obtain.recycle();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long addPowerSaveTempWhitelistAppForSms(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveTempWhitelistAppForSms(str, i, str2);
                    }
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    obtain2.recycle();
                    obtain.recycle();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void exitIdle(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(17, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().exitIdle(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public boolean registerMaintenanceActivityListener(IMaintenanceActivityListener iMaintenanceActivityListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMaintenanceActivityListener != null ? iMaintenanceActivityListener.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMaintenanceActivityListener(iMaintenanceActivityListener);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void unregisterMaintenanceActivityListener(IMaintenanceActivityListener iMaintenanceActivityListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMaintenanceActivityListener != null ? iMaintenanceActivityListener.asBinder() : null);
                    if (this.mRemote.transact(19, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterMaintenanceActivityListener(iMaintenanceActivityListener);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int getIdleStateDetailed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIdleStateDetailed();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int getLightIdleStateDetailed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightIdleStateDetailed();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void delayDoze(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(22, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().delayDoze(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceIdleController iDeviceIdleController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iDeviceIdleController == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iDeviceIdleController;
                return true;
            }
        }

        public static IDeviceIdleController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
