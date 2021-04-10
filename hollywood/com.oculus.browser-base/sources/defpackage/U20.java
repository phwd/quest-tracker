package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.TransactionTooLargeException;
import java.util.ArrayList;

/* renamed from: U20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class U20 {
    public static Intent a(Intent intent, Exception exc) {
        AbstractC1220Ua0.a("IntentUtils", "Invalid incoming intent.", exc);
        return intent.replaceExtras((Bundle) null);
    }

    public static void b(RuntimeException runtimeException, Intent intent) {
        if (runtimeException.getCause() instanceof TransactionTooLargeException) {
            StringBuilder i = AbstractC2531fV.i("Could not resolve Activity for intent ");
            i.append(intent.toString());
            AbstractC1220Ua0.a("IntentUtils", i.toString(), runtimeException);
            return;
        }
        throw runtimeException;
    }

    public static boolean c(Bundle bundle, String str, boolean z) {
        try {
            return bundle.getBoolean(str, z);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", "getBoolean failed on bundle " + bundle, new Object[0]);
            return z;
        }
    }

    public static boolean d(Intent intent, String str, boolean z) {
        try {
            return intent.getBooleanExtra(str, z);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getBooleanExtra failed on intent ", intent), new Object[0]);
            return z;
        }
    }

    public static Bundle e(Intent intent, String str) {
        try {
            return intent.getBundleExtra(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getBundleExtra failed on intent ", intent), new Object[0]);
            return null;
        }
    }

    public static byte[] f(Intent intent, String str) {
        try {
            return intent.getByteArrayExtra(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getByteArrayExtra failed on intent ", intent), new Object[0]);
            return null;
        }
    }

    public static int g(Bundle bundle, String str, int i) {
        try {
            return bundle.getInt(str, i);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", "getInt failed on bundle " + bundle, new Object[0]);
            return i;
        }
    }

    public static int h(Intent intent, String str, int i) {
        try {
            return intent.getIntExtra(str, i);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getIntExtra failed on intent ", intent), new Object[0]);
            return i;
        }
    }

    public static long i(Intent intent, String str, long j) {
        try {
            return intent.getLongExtra(str, j);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getLongExtra failed on intent ", intent), new Object[0]);
            return j;
        }
    }

    public static Parcelable j(Bundle bundle, String str) {
        try {
            return bundle.getParcelable(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", "getParcelable failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static Parcelable k(Intent intent, String str) {
        try {
            return intent.getParcelableExtra(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getParcelableExtra failed on intent ", intent), new Object[0]);
            return null;
        }
    }

    public static String l(Bundle bundle, String str) {
        try {
            return bundle.getString(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", "getString failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static ArrayList m(Intent intent, String str) {
        try {
            return intent.getStringArrayListExtra(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getStringArrayListExtra failed on intent ", intent), new Object[0]);
            return null;
        }
    }

    public static String n(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getStringExtra failed on intent ", intent), new Object[0]);
            return null;
        }
    }

    public static boolean o(Intent intent, String str) {
        try {
            return intent.hasExtra(str);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("hasExtra failed on intent ", intent), new Object[0]);
            return false;
        }
    }

    public static void p(Intent intent, String str, IBinder iBinder) {
        Bundle bundle = new Bundle();
        try {
            bundle.putBinder(str, null);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", "putBinder failed on bundle " + bundle, new Object[0]);
        }
        intent.putExtras(bundle);
    }

    public static boolean q(Context context, Intent intent) {
        try {
            context.startActivity(intent, null);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    public static Intent r(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            intent.getBooleanExtra("TriggerUnparcel", false);
            return intent;
        } catch (BadParcelableException e) {
            return a(intent, e);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof ClassNotFoundException) {
                return a(intent, e2);
            }
            throw e2;
        }
    }
}
