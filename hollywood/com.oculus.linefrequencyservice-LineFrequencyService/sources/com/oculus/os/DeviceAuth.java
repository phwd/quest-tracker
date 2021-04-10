package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.internal.annotations.VisibleForTesting;
import com.oculus.aidl.IDeviceAuthService;
import java.io.IOException;
import oculus.internal.binder.BinderServiceClient;

public final class DeviceAuth {
    @VisibleForTesting
    static final String DEVICE_AUTH_SERVER_CLASS = "com.oculus.deviceauthserver.DeviceAuthService";
    @VisibleForTesting
    static final String DEVICE_AUTH_SERVER_PACKAGE = "com.oculus.deviceauthserver";
    private final Context mContext;

    public DeviceAuth(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new NullPointerException("context is null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.os.DeviceAuthToken fetchToken(java.lang.String r5) throws com.oculus.os.DeviceAuth.DeviceIdentityException, com.oculus.os.DeviceAuth.NetworkException, com.oculus.os.DeviceAuth.BackendException, java.lang.InterruptedException {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x005a
            com.oculus.os.DeviceAuth$DeviceAuthServiceClient r0 = new com.oculus.os.DeviceAuth$DeviceAuthServiceClient     // Catch:{ ServiceSpecificException -> 0x0036, RemoteException -> 0x002f }
            android.content.Context r1 = r4.mContext     // Catch:{ ServiceSpecificException -> 0x0036, RemoteException -> 0x002f }
            r0.<init>(r1)     // Catch:{ ServiceSpecificException -> 0x0036, RemoteException -> 0x002f }
            java.lang.Object r1 = r0.awaitService()     // Catch:{ all -> 0x0023 }
            com.oculus.aidl.IDeviceAuthService r1 = (com.oculus.aidl.IDeviceAuthService) r1     // Catch:{ all -> 0x0023 }
            com.oculus.os.DeviceAuthToken r1 = r1.fetchDeviceAuthToken(r5)     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x001b
            r0.close()
            return r1
        L_0x001b:
            com.oculus.os.DeviceAuth$DeviceIdentityException r2 = new com.oculus.os.DeviceAuth$DeviceIdentityException
            java.lang.String r3 = "DeviceAuthService.fetchDeviceAuthToken() returned null"
            r2.<init>(r3)
            throw r2
        L_0x0023:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x002e:
            throw r2
        L_0x002f:
            r0 = move-exception
            com.oculus.os.DeviceAuth$DeviceIdentityException r1 = new com.oculus.os.DeviceAuth$DeviceIdentityException
            r1.<init>(r0)
            throw r1
        L_0x0036:
            r0 = move-exception
            int r1 = r0.errorCode
            r2 = 1
            if (r1 == r2) goto L_0x0054
            r2 = 2
            if (r1 == r2) goto L_0x004e
            r2 = 3
            if (r1 == r2) goto L_0x0048
            com.oculus.os.DeviceAuth$DeviceIdentityException r1 = new com.oculus.os.DeviceAuth$DeviceIdentityException
            r1.<init>(r0)
            throw r1
        L_0x0048:
            com.oculus.os.DeviceAuth$BackendException r1 = new com.oculus.os.DeviceAuth$BackendException
            r1.<init>(r0)
            throw r1
        L_0x004e:
            com.oculus.os.DeviceAuth$NetworkException r1 = new com.oculus.os.DeviceAuth$NetworkException
            r1.<init>(r0)
            throw r1
        L_0x0054:
            com.oculus.os.DeviceAuth$DeviceIdentityException r1 = new com.oculus.os.DeviceAuth$DeviceIdentityException
            r1.<init>(r0)
            throw r1
        L_0x005a:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "applicationClientToken is null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.DeviceAuth.fetchToken(java.lang.String):com.oculus.os.DeviceAuthToken");
    }

    private static final class DeviceAuthServiceClient extends BinderServiceClient<IDeviceAuthService> {
        DeviceAuthServiceClient(Context context) throws RemoteException {
            super(context, newIntent());
        }

        private static Intent newIntent() {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(DeviceAuth.DEVICE_AUTH_SERVER_PACKAGE, DeviceAuth.DEVICE_AUTH_SERVER_CLASS));
            return intent;
        }

        /* access modifiers changed from: protected */
        @Override // oculus.internal.binder.BinderServiceClient
        public IDeviceAuthService asInterface(IBinder binder) {
            return IDeviceAuthService.Stub.asInterface(binder);
        }
    }

    public static final class DeviceIdentityException extends IOException {
        DeviceIdentityException(String message) {
            super(message);
        }

        DeviceIdentityException(Exception cause) {
            super(cause.getMessage(), cause);
        }
    }

    public static final class NetworkException extends IOException {
        NetworkException(Exception cause) {
            super(cause.getMessage(), cause);
        }
    }

    public static final class BackendException extends IOException {
        BackendException(Exception cause) {
            super(cause.getMessage(), cause);
        }
    }
}
