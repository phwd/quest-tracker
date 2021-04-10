package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.facebook.FacebookException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    public static final String ANDROID_ID_COLUMN_NAME = "androidid";
    public static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    public static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    public static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    public static final int CONNECTION_RESULT_SUCCESS = 0;
    public static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    public static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    public static final String TAG = "com.facebook.internal.AttributionIdentifiers";
    public static AttributionIdentifiers recentlyFetchedIdentifiers;
    public String androidAdvertiserId;
    public String androidInstallerPackage;
    public String attributionId;
    public long fetchTime;
    public boolean limitTracking;

    public static final class GoogleAdServiceConnection implements ServiceConnection {
        public AtomicBoolean consumed;
        public final BlockingQueue<IBinder> queue;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.queue.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }
    }

    public static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        Method methodQuietly;
        Method method;
        Method method2;
        Object invoke;
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
                if (methodQuietly2 != null) {
                    try {
                        invoke = methodQuietly2.invoke(null, context);
                    } catch (IllegalAccessException | InvocationTargetException unused) {
                    }
                    if ((invoke instanceof Integer) && ((Integer) invoke).intValue() == 0 && (methodQuietly = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class)) != null) {
                        try {
                            Object invoke2 = methodQuietly.invoke(null, context);
                            if (invoke2 != null) {
                                Class<?> cls = invoke2.getClass();
                                try {
                                    method = cls.getMethod("getId", new Class[0]);
                                } catch (NoSuchMethodException unused2) {
                                    method = null;
                                }
                                try {
                                    method2 = cls.getMethod("isLimitAdTrackingEnabled", new Class[0]);
                                } catch (NoSuchMethodException unused3) {
                                    method2 = null;
                                }
                                if (!(method == null || method2 == null)) {
                                    AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                                    Object obj = null;
                                    try {
                                        obj = method.invoke(invoke2, new Object[0]);
                                    } catch (IllegalAccessException | InvocationTargetException unused4) {
                                    }
                                    attributionIdentifiers.androidAdvertiserId = (String) obj;
                                    Object obj2 = null;
                                    try {
                                        obj2 = method2.invoke(invoke2, new Object[0]);
                                    } catch (IllegalAccessException | InvocationTargetException unused5) {
                                    }
                                    attributionIdentifiers.limitTracking = ((Boolean) obj2).booleanValue();
                                    return attributionIdentifiers;
                                }
                            }
                        } catch (IllegalAccessException | InvocationTargetException unused6) {
                        }
                    }
                }
                return null;
            }
            throw new FacebookException("getAndroidId cannot be called on the main thread.");
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    public static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd("android_id", e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    public static final class GoogleAdInfo implements IInterface {
        public static final int FIRST_TRANSACTION_CODE = 1;
        public static final int SECOND_TRANSACTION_CODE = 2;
        public IBinder binder;

        public GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b1, code lost:
        r3 = null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c1 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r12) {
        /*
        // Method dump skipped, instructions count: 197
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    public static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        if (androidIdViaService == null) {
            return new AttributionIdentifiers();
        }
        return androidIdViaService;
    }

    @Nullable
    public static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
}
