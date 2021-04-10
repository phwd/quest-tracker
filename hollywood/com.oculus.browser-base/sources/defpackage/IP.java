package defpackage;

import android.os.StrictMode;
import java.io.File;
import org.chromium.base.ContextUtils;

/* renamed from: IP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IP {

    /* renamed from: a  reason: collision with root package name */
    public static File f8224a;

    static {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            f8224a = ContextUtils.getApplicationContext().getDir("persisted_tab_data_storage", 0);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }
}
