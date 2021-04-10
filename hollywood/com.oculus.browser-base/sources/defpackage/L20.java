package defpackage;

import android.content.Context;
import android.content.Intent;
import java.io.File;

@Deprecated
/* renamed from: L20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class L20 extends AbstractServiceC2158dG1 {
    public static void b(Context context, C4881tD1 td1) {
        synchronized (td1) {
            td1.d.clear();
            File[] listFiles = WH1.j(td1.b).listFiles();
            for (File file : listFiles) {
                if (file.getName().startsWith("com.google.InstanceId")) {
                    file.delete();
                }
            }
            td1.f11331a.edit().clear().commit();
        }
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra("CMD", "RST");
        intent.setClassName(context, "com.google.android.gms.gcm.GcmReceiver");
        context.sendBroadcast(intent);
    }
}
