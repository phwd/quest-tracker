package defpackage;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: YC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YC1 {

    /* renamed from: a  reason: collision with root package name */
    public static final RC1 f9261a = FC1.b().a();
    public static String b = null;
    public static boolean c = false;
    public static int d = 0;
    public static BroadcastReceiver e = null;
    public Context f;
    public Map g = new C4931ta();
    public Messenger h;
    public Messenger i;
    public MessengerCompat j;
    public PendingIntent k;

    public YC1(Context context) {
        this.f = context;
    }

    public static void b(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e2) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(valueOf.length() + 24);
                sb.append("Failed to send response ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
            }
        }
    }

    public static boolean d(PackageManager packageManager, String str) {
        try {
            b = packageManager.getApplicationInfo(str, 0).packageName;
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean e(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return d(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(str2.length() + String.valueOf(str).length() + 56);
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID", sb.toString());
        return false;
    }

    public static String g(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("registration_id");
            if (string == null) {
                string = bundle.getString("unregistered");
            }
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString("error");
            if (string2 != null) {
                throw new IOException(string2);
            }
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(valueOf.length() + 29);
            sb.append("Unexpected response from GCM ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString(), new Throwable());
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    public static String j(Context context) {
        boolean z;
        String str = b;
        if (str != null) {
            return str;
        }
        Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        boolean z2 = true;
        if (!AbstractC4539rD0.a()) {
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (it.hasNext()) {
                    if (e(packageManager, it.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                        c = false;
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return b;
            }
        }
        Iterator<ResolveInfo> it2 = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (true) {
            if (it2.hasNext()) {
                if (e(packageManager, it2.next().activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                    c = true;
                    break;
                }
            } else {
                z2 = false;
                break;
            }
        }
        if (z2) {
            return b;
        }
        Log.w("InstanceID", "Failed to resolve IID implementation package, falling back");
        if (d(packageManager, "com.google.android.gms")) {
            c = AbstractC4539rD0.a();
            return b;
        }
        Log.w("InstanceID", "Google Play services is missing, unable to get tokens");
        return null;
    }

    public final Bundle a(Bundle bundle, KeyPair keyPair) {
        int i2;
        String str;
        int i3;
        OI1 oi1;
        Context context = this.f;
        try {
            i2 = context.getPackageManager().getPackageInfo(j(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i2 = -1;
        }
        bundle.putString("gmsv", Integer.toString(i2));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(D20.f(this.f)));
        Context context2 = this.f;
        try {
            str = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            str = null;
        }
        bundle.putString("app_ver_name", str);
        bundle.putString("cliv", "iid-12451000");
        bundle.putString("appid", D20.d(keyPair));
        if (i2 < 12000000) {
            return h(bundle);
        }
        Objects.requireNonNull(f9261a);
        C4896tI1 ti1 = new C4896tI1(this.f);
        synchronized (ti1) {
            i3 = ti1.d;
            ti1.d = i3 + 1;
        }
        C5898zC1 zc1 = new C5898zC1(i3, bundle);
        synchronized (ti1) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String.valueOf(zc1).length();
            }
            if (!ti1.c.b(zc1)) {
                FI1 fi1 = new FI1(ti1, null);
                ti1.c = fi1;
                fi1.b(zc1);
            }
            oi1 = zc1.b.f8303a;
        }
        try {
            return (Bundle) AbstractC3925nf1.a(oi1);
        } catch (InterruptedException | ExecutionException e3) {
            if (Log.isLoggable("InstanceID", 3)) {
                String.valueOf(e3).length();
            }
            if (!(e3.getCause() instanceof C5218vC1) || ((C5218vC1) e3.getCause()).F != 4) {
                return null;
            }
            return h(bundle);
        }
    }

    public final void c(String str, Object obj) {
        synchronized (YC1.class) {
            Object obj2 = this.g.get(str);
            this.g.put(str, obj);
            b(obj2, obj);
        }
    }

    public final void f(Intent intent) {
        String str;
        if (intent != null) {
            String action = intent.getAction();
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(action) || "com.google.android.gms.iid.InstanceID".equals(action)) {
                String stringExtra = intent.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    String stringExtra2 = intent.getStringExtra("error");
                    if (stringExtra2 == null) {
                        String valueOf = String.valueOf(intent.getExtras());
                        StringBuilder sb = new StringBuilder(valueOf.length() + 49);
                        sb.append("Unexpected response, no error or registration id ");
                        sb.append(valueOf);
                        Log.w("InstanceID", sb.toString());
                        return;
                    }
                    if (Log.isLoggable("InstanceID", 3)) {
                        if (stringExtra2.length() != 0) {
                            "Received InstanceID error ".concat(stringExtra2);
                        } else {
                            new String("Received InstanceID error ");
                        }
                    }
                    String str2 = null;
                    if (stringExtra2.startsWith("|")) {
                        String[] split = stringExtra2.split("\\|");
                        if (!"ID".equals(split[1])) {
                            Log.w("InstanceID", stringExtra2.length() != 0 ? "Unexpected structured response ".concat(stringExtra2) : new String("Unexpected structured response "));
                        }
                        if (split.length > 2) {
                            String str3 = split[2];
                            str = split[3];
                            if (str.startsWith(":")) {
                                str = str.substring(1);
                            }
                            str2 = str3;
                        } else {
                            str = "UNKNOWN";
                        }
                        stringExtra2 = str;
                        intent.putExtra("error", stringExtra2);
                    }
                    if (str2 == null) {
                        synchronized (YC1.class) {
                            for (String str4 : this.g.keySet()) {
                                Object obj = this.g.get(str4);
                                this.g.put(str4, stringExtra2);
                                b(obj, stringExtra2);
                            }
                        }
                        return;
                    }
                    c(str2, stringExtra2);
                    return;
                }
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    Bundle extras = intent.getExtras();
                    extras.putString("registration_id", group2);
                    c(group, extras);
                } else if (Log.isLoggable("InstanceID", 3)) {
                    if (stringExtra.length() != 0) {
                        "Unexpected response string: ".concat(stringExtra);
                    } else {
                        new String("Unexpected response string: ");
                    }
                }
            } else if (Log.isLoggable("InstanceID", 3)) {
                String valueOf2 = String.valueOf(intent.getAction());
                if (valueOf2.length() != 0) {
                    "Unexpected response ".concat(valueOf2);
                } else {
                    new String("Unexpected response ");
                }
            }
        }
    }

    public final Bundle h(Bundle bundle) {
        Bundle i2 = i(bundle);
        if (i2 == null || !i2.containsKey("google.messenger")) {
            return i2;
        }
        Bundle i3 = i(bundle);
        if (i3 == null || !i3.containsKey("google.messenger")) {
            return i3;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0162 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle i(android.os.Bundle r9) {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.YC1.i(android.os.Bundle):android.os.Bundle");
    }
}
