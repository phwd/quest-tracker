package com.oculus.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.os.IQuirksDb;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import oculus.internal.Constants;

public final class AppQuirks {
    private static final boolean DEBUG = true;
    private static final int MY_PID = Process.myPid();
    private static final AtomicReference<Bundle> MY_QUIRKS = new AtomicReference<>(null);
    private static final AtomicReference<IQuirksDb> QUIRK_SERVICE = new AtomicReference<>(null);
    private static final String TAG = "AppQuirks";

    public static <T> Optional<T> getQuirkForProcess(int pid, Quirk<T> quirk) {
        if (pid == MY_PID) {
            Bundle quirks = MY_QUIRKS.get();
            return quirk.getFromBundle(quirks == null ? Bundle.EMPTY : quirks);
        }
        try {
            return quirk.getFromBundle(getQuirkService().getQuirkForProcess(pid, quirk.getId()));
        } catch (RemoteException ex) {
            Log.e(TAG, "getQuirksForProcess exception", ex);
            return Optional.empty();
        }
    }

    public static void setProcessQuirks(Bundle quirks) {
        if (MY_QUIRKS.compareAndSet(null, quirks)) {
            try {
                getQuirkService().setQuirksForCaller(quirks);
            } catch (RemoteException ex) {
                Log.e(TAG, "setQuirksForCaller exception", ex);
            }
        }
    }

    public static void setQuirkService(IQuirksDb service) {
        QUIRK_SERVICE.set(service);
    }

    private static IQuirksDb getQuirkService() {
        IQuirksDb service = QUIRK_SERVICE.get();
        if (service == null) {
            try {
                service = IQuirksDb.Stub.asInterface((IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class).invoke(null, Constants.APP_QUIRKS_SERVICE));
            } catch (Exception ex) {
                Log.wtf(TAG, "Exception querying quirk service", ex);
            }
            if (service != null) {
                QUIRK_SERVICE.set(service);
            } else {
                QUIRK_SERVICE.compareAndSet(null, new NullQuirkService());
                service = QUIRK_SERVICE.get();
            }
            if (service instanceof NullQuirkService) {
                Log.e(TAG, "Failed to bind to AppQuirksService");
            }
        }
        return service;
    }

    /* access modifiers changed from: private */
    public static class NullQuirkService extends IQuirksDb.Stub {
        private NullQuirkService() {
        }

        @Override // com.oculus.os.IQuirksDb
        public void setQuirksForCaller(Bundle quirkBundle) {
        }

        @Override // com.oculus.os.IQuirksDb
        public Bundle getQuirkForProcess(int pid, int quirkId) {
            return Bundle.EMPTY;
        }
    }
}
