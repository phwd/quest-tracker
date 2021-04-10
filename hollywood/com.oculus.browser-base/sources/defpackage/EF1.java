package defpackage;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.TokenData;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/* renamed from: EF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class EF1 {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f7948a = {"com.google", "com.google.work", "cn.google"};
    public static final ComponentName b = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    public static final C1464Ya0 c = new C1464Ya0("Auth", "GoogleAuthUtil");

    public static void a(Context context, int i) {
        try {
            AbstractC3729mW.b(context.getApplicationContext(), i);
        } catch (C3387kW e) {
            throw new C3046iW(e.G, e.getMessage(), new Intent(e.F));
        } catch (C3216jW e2) {
            throw new C2192dW(e2.getMessage());
        }
    }

    public static Object b(Context context, ComponentName componentName, AbstractC4040oH1 oh1) {
        ServiceConnectionC4615ri riVar = new ServiceConnectionC4615ri();
        ZF1 a2 = ZF1.a(context);
        Objects.requireNonNull(a2);
        if (a2.b(new NV(componentName), riVar, "GoogleAuthUtil")) {
            try {
                Object a3 = oh1.a(riVar.a());
                a2.c(new NV(componentName), riVar, "GoogleAuthUtil");
                return a3;
            } catch (RemoteException | InterruptedException e) {
                C1464Ya0 ya0 = c;
                Log.i(ya0.f9281a, ya0.b.concat(String.format(Locale.US, "GoogleAuthUtil", "Error on service connection.", e)));
                throw new IOException("Error on service connection.", e);
            } catch (Throwable th) {
                a2.c(new NV(componentName), riVar, "GoogleAuthUtil");
                throw th;
            }
        } else {
            throw new IOException("Could not bind to service.");
        }
    }

    public static TokenData c(Context context, Account account, String str, Bundle bundle) {
        SE0.h("Calling this from your main thread can lead to deadlock");
        SE0.g(str, "Scope cannot be empty or null.");
        e(account);
        a(context, 8400000);
        Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        if (TextUtils.isEmpty(bundle2.getString("androidPackageName"))) {
            bundle2.putString("androidPackageName", str2);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return (TokenData) b(context, b, new C2670gG1(account, str, bundle2));
    }

    public static Object d(Object obj) {
        if (obj != null) {
            return obj;
        }
        c.a("GoogleAuthUtil", "Binder call returned null.");
        throw new IOException("Service unavailable.");
    }

    public static void e(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        } else if (!TextUtils.isEmpty(account.name)) {
            for (String str : f7948a) {
                if (str.equals(account.type)) {
                    return;
                }
            }
            throw new IllegalArgumentException("Account type not supported");
        } else {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
    }
}
