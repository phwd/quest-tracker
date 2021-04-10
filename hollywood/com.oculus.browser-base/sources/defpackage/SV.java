package defpackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import com.oculus.browser.R;

/* renamed from: SV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SV extends UV {
    public static final Object c = new Object();
    public static final SV d = new SV();
    public static final int e = UV.f9031a;

    public static Dialog h(Context context, int i, XE xe, DialogInterface.OnCancelListener onCancelListener) {
        String str;
        AlertDialog.Builder builder = null;
        if (i == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843529, typedValue, true);
        if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId))) {
            builder = new AlertDialog.Builder(context, 5);
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(AbstractC0604Jx.b(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Resources resources = context.getResources();
        if (i == 1) {
            str = resources.getString(R.string.f49380_resource_name_obfuscated_RES_2131952255);
        } else if (i == 2) {
            str = resources.getString(R.string.f49450_resource_name_obfuscated_RES_2131952262);
        } else if (i != 3) {
            str = resources.getString(17039370);
        } else {
            str = resources.getString(R.string.f49350_resource_name_obfuscated_RES_2131952252);
        }
        if (str != null) {
            builder.setPositiveButton(str, xe);
        }
        String c2 = AbstractC0604Jx.c(context, i);
        if (c2 != null) {
            builder.setTitle(c2);
        }
        return builder.create();
    }

    public static void j(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        if (activity instanceof AbstractActivityC3892nS) {
            KS Y = ((AbstractActivityC3892nS) activity).Y();
            T31 t31 = new T31();
            SE0.i(dialog, "Cannot display null dialog");
            dialog.setOnCancelListener(null);
            dialog.setOnDismissListener(null);
            t31.M0 = dialog;
            if (onCancelListener != null) {
                t31.N0 = onCancelListener;
            }
            t31.k1(Y, str);
            return;
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        PL pl = new PL();
        SE0.i(dialog, "Cannot display null dialog");
        dialog.setOnCancelListener(null);
        dialog.setOnDismissListener(null);
        pl.F = dialog;
        if (onCancelListener != null) {
            pl.G = onCancelListener;
        }
        pl.show(fragmentManager, str);
    }

    @Override // defpackage.UV
    public Intent a(Context context, int i, String str) {
        return super.a(context, i, str);
    }

    @Override // defpackage.UV
    public int b(Context context, int i) {
        return super.b(context, i);
    }

    @Override // defpackage.UV
    public final boolean c(int i) {
        return super.c(i);
    }

    public Dialog d(Activity activity, int i, int i2) {
        return h(activity, i, new GB1(super.a(activity, i, "d"), activity, i2), null);
    }

    public int e(Context context) {
        return b(context, UV.f9031a);
    }

    public boolean f(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog h = h(activity, i, new GB1(super.a(activity, i, "d"), activity, i2), onCancelListener);
        if (h == null) {
            return false;
        }
        j(activity, h, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public void g(Context context, int i) {
        PendingIntent pendingIntent;
        Intent a2 = super.a(context, i, "n");
        if (a2 == null) {
            pendingIntent = null;
        } else {
            pendingIntent = PendingIntent.getActivity(context, 0, a2, 134217728);
        }
        k(context, i, pendingIntent);
    }

    public final C4364qB1 i(Context context, AbstractC4193pB1 pb1) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        C4364qB1 qb1 = new C4364qB1(pb1);
        context.registerReceiver(qb1, intentFilter);
        qb1.f11122a = context;
        if (AbstractC3729mW.d(context, "com.google.android.gms")) {
            return qb1;
        }
        pb1.a();
        qb1.a();
        return null;
    }

    public final void k(Context context, int i, PendingIntent pendingIntent) {
        String str;
        String str2;
        int i2;
        if (i == 18) {
            new RV(this, context).sendEmptyMessageDelayed(1, 120000);
        } else if (pendingIntent != null) {
            if (i == 6) {
                str = AbstractC0604Jx.d(context, "common_google_play_services_resolution_required_title");
            } else {
                str = AbstractC0604Jx.c(context, i);
            }
            if (str == null) {
                str = context.getResources().getString(R.string.f49420_resource_name_obfuscated_RES_2131952259);
            }
            if (i == 6 || i == 19) {
                str2 = AbstractC0604Jx.e(context, "common_google_play_services_resolution_required_text", AbstractC0604Jx.a(context));
            } else {
                str2 = AbstractC0604Jx.b(context, i);
            }
            Resources resources = context.getResources();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            C0223Dp0 dp0 = new C0223Dp0(context, null);
            dp0.s = true;
            dp0.f(16, true);
            dp0.e(str);
            C0162Cp0 cp0 = new C0162Cp0();
            cp0.g(str2);
            if (dp0.l != cp0) {
                dp0.l = cp0;
                if (cp0.f8041a != dp0) {
                    cp0.f8041a = dp0;
                    if (cp0 != cp0) {
                        dp0.l = cp0;
                        cp0.f(dp0);
                    }
                }
            }
            if (FE.a(context)) {
                SE0.j(true);
                dp0.C.icon = context.getApplicationInfo().icon;
                dp0.j = 2;
                if (FE.b(context)) {
                    dp0.a(com.google.android.gms.base.R.drawable.common_full_open_on_phone, resources.getString(R.string.f49500_resource_name_obfuscated_RES_2131952267), pendingIntent);
                } else {
                    dp0.g = pendingIntent;
                }
            } else {
                dp0.C.icon = 17301642;
                String string = resources.getString(R.string.f49420_resource_name_obfuscated_RES_2131952259);
                dp0.C.tickerText = C0223Dp0.c(string);
                dp0.C.when = System.currentTimeMillis();
                dp0.g = pendingIntent;
                dp0.d(str2);
            }
            if (AbstractC4539rD0.a()) {
                SE0.j(AbstractC4539rD0.a());
                synchronized (c) {
                }
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                BW0 bw0 = AbstractC0604Jx.f8329a;
                String string2 = context.getResources().getString(R.string.f49410_resource_name_obfuscated_RES_2131952258);
                if (notificationChannel == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", string2, 4));
                } else if (!string2.contentEquals(notificationChannel.getName())) {
                    notificationChannel.setName(string2);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                dp0.A = "com.google.android.gms.availability";
            }
            Notification b = dp0.b();
            if (i == 1 || i == 2 || i == 3) {
                i2 = 10436;
                AbstractC3729mW.f10427a.set(false);
            } else {
                i2 = 39789;
            }
            notificationManager.notify(i2, b);
        } else if (i == 6) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }
}
