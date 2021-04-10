package com.oculus.headlesshorizon.notifications.receivers;

import X.AnonymousClass0NO;
import X.AnonymousClass0bP;
import X.C02780bN;
import X.C02790bO;
import X.C02870bf;
import X.C02880bg;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.oculus.messenger.service.MessengerServiceProviderContract;

public final class VrMessengerServiceStatusUtils {
    public static final String TAG = "VrMessengerServiceStatusUtils";

    public static void A00(@Nullable ContentProviderClient contentProviderClient) {
        if (contentProviderClient == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            contentProviderClient.close();
        } else {
            contentProviderClient.release();
        }
    }

    public static boolean A01(Context context, Uri uri) {
        ContentProviderClient contentProviderClient;
        Throwable th;
        PackageManager packageManager;
        ProviderInfo resolveContentProvider;
        try {
            C02870bf A02 = C02880bg.A02(new SingletonImmutableSet(C02780bN.A0L), ImmutableSet.A06(2, "com.oculus.messenger", "com.oculus.socialplatform"));
            String authority = uri.getAuthority();
            if (authority == null || (packageManager = context.getPackageManager()) == null || (resolveContentProvider = packageManager.resolveContentProvider(authority, 0)) == null) {
                contentProviderClient = null;
            } else {
                C02790bO A00 = AnonymousClass0bP.A00(context, resolveContentProvider.packageName);
                if (A02.A07(A00, context)) {
                    contentProviderClient = context.getContentResolver().acquireContentProviderClient(authority);
                    if (contentProviderClient != null) {
                        try {
                            Cursor query = contentProviderClient.query(uri, new String[]{MessengerServiceProviderContract.Status.COLUMN_ACTIVE}, null, null, null);
                            if (query != null) {
                                try {
                                    if (query.moveToNext()) {
                                        boolean z = false;
                                        if (query.getInt(query.getColumnIndexOrThrow(MessengerServiceProviderContract.Status.COLUMN_ACTIVE)) == 1) {
                                            z = true;
                                        }
                                        query.close();
                                        A00(contentProviderClient);
                                        return z;
                                    }
                                    query.close();
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (RemoteException e) {
                            AnonymousClass0NO.A0E(TAG, "Error running isMessengerRunning() for URI %s", e, uri.toString());
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                } else {
                    throw new SecurityException(String.format("The provider for uri '%s' is not trusted: %s", authority, A00));
                }
            }
            A00(contentProviderClient);
            return false;
            throw th;
        } catch (Throwable th3) {
            th = th3;
            contentProviderClient = null;
            A00(contentProviderClient);
            throw th;
        }
    }
}
