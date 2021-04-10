package X;

import android.content.Context;
import android.os.Looper;
import android.os.StrictMode;
import com.facebook.crudolib.prefs.LightSharedPreferences;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;

/* renamed from: X.1RO  reason: invalid class name */
public final class AnonymousClass1RO {
    @Nullable
    public static C03850of A00;

    public static synchronized C03850of A00(Context context) {
        C03850of r1;
        StrictMode.ThreadPolicy threadPolicy;
        synchronized (AnonymousClass1RO.class) {
            r1 = A00;
            if (r1 == null) {
                AnonymousClass0Lk r5 = new AnonymousClass0Lk(Executors.newSingleThreadExecutor(), AnonymousClass0Lk.A00(context.getApplicationContext()));
                synchronized (r5) {
                    Map<String, LightSharedPreferences> map = r5.A01;
                    r1 = map.get("mailbox_shared_preferences_name");
                    if (r1 == null) {
                        File file = new File(r5.A00, "mailbox_shared_preferences_name");
                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            threadPolicy = StrictMode.allowThreadDiskReads();
                        } else {
                            threadPolicy = null;
                        }
                        try {
                            File parentFile = file.getParentFile();
                            if (parentFile != null) {
                                if (parentFile.exists() && !parentFile.isDirectory()) {
                                    AnonymousClass0MD.A0A("LightSharedPreferencesFactory", "cannot create directory %s, a file already exists with that name", parentFile.getAbsolutePath());
                                }
                                if (!parentFile.exists()) {
                                    parentFile.mkdirs();
                                }
                                r1 = new C03850of(file, r5.A02);
                                map.put("mailbox_shared_preferences_name", r1);
                            } else {
                                throw new AssertionError("expecting a file which is always under some dir");
                            }
                        } finally {
                            if (threadPolicy != null) {
                                StrictMode.setThreadPolicy(threadPolicy);
                            }
                        }
                    }
                }
                A00 = r1;
            }
        }
        return r1;
    }
}
