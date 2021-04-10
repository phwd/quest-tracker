package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: KT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KT0 extends BroadcastReceiver implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8367a = new Object();
    public static String b;
    public static KT0 c;
    public AbstractC2018cU0 d;

    public KT0(AbstractC2018cU0 cu0) {
        this.d = cu0;
    }

    public static void b(WindowAndroid windowAndroid, Intent intent, AbstractC2018cU0 cu0) {
        Context applicationContext = ContextUtils.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        synchronized (f8367a) {
            if (b == null) {
                b = packageName + "/" + KT0.class.getName() + "_ACTION";
            }
            KT0 kt0 = c;
            if (kt0 != null) {
                applicationContext.unregisterReceiver(kt0);
                KT0 kt02 = c;
                AbstractC2018cU0 cu02 = kt02.d;
                if (cu02 != null) {
                    cu02.a();
                    kt02.d = null;
                }
            }
            KT0 kt03 = new KT0(cu0);
            c = kt03;
            applicationContext.registerReceiver(kt03, new IntentFilter(b));
        }
        Intent intent2 = new Intent(b);
        intent2.setPackage(packageName);
        intent2.putExtra("receiver_token", c.hashCode());
        IT0.a(windowAndroid, Intent.createChooser(intent, applicationContext.getString(R.string.f61530_resource_name_obfuscated_RES_2131953470), PendingIntent.getBroadcast((Activity) windowAndroid.s0().get(), 0, intent2, 1342177280).getIntentSender()), c);
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        AbstractC2018cU0 cu0;
        if (i == 0 && (cu0 = this.d) != null) {
            cu0.a();
            this.d = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r4.hasExtra("receiver_token") == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r4.getIntExtra("receiver_token", 0) == hashCode()) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r3 = (android.content.ComponentName) r4.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
        r4 = r2.d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        r4.b(r3);
        r2.d = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r3, android.content.Intent r4) {
        /*
            r2 = this;
            java.lang.Object r3 = defpackage.KT0.f8367a
            monitor-enter(r3)
            KT0 r0 = defpackage.KT0.c     // Catch:{ all -> 0x003e }
            if (r0 == r2) goto L_0x0009
            monitor-exit(r3)     // Catch:{ all -> 0x003e }
            return
        L_0x0009:
            android.content.Context r0 = org.chromium.base.ContextUtils.getApplicationContext()     // Catch:{ all -> 0x003e }
            KT0 r1 = defpackage.KT0.c     // Catch:{ all -> 0x003e }
            r0.unregisterReceiver(r1)     // Catch:{ all -> 0x003e }
            r0 = 0
            defpackage.KT0.c = r0     // Catch:{ all -> 0x003e }
            monitor-exit(r3)     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "receiver_token"
            boolean r3 = r4.hasExtra(r3)
            if (r3 == 0) goto L_0x003d
            java.lang.String r3 = "receiver_token"
            r1 = 0
            int r3 = r4.getIntExtra(r3, r1)
            int r1 = r2.hashCode()
            if (r3 == r1) goto L_0x002c
            goto L_0x003d
        L_0x002c:
            java.lang.String r3 = "android.intent.extra.CHOSEN_COMPONENT"
            android.os.Parcelable r3 = r4.getParcelableExtra(r3)
            android.content.ComponentName r3 = (android.content.ComponentName) r3
            cU0 r4 = r2.d
            if (r4 == 0) goto L_0x003d
            r4.b(r3)
            r2.d = r0
        L_0x003d:
            return
        L_0x003e:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.KT0.onReceive(android.content.Context, android.content.Intent):void");
    }
}
