package X;

import android.content.Context;
import android.os.Looper;
import android.os.StrictMode;
import com.facebook.crudolib.prefs.LightSharedPreferences;
import com.facebook.infer.annotation.NullsafeStrict;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.ThreadSafe;

@NullsafeStrict
@ThreadSafe
/* renamed from: X.0Lk  reason: invalid class name */
public final class AnonymousClass0Lk {
    public final File A00;
    public final Map<String, LightSharedPreferences> A01 = new HashMap();
    public final Executor A02;

    public AnonymousClass0Lk(Executor executor, File file) {
        this.A02 = executor;
        this.A00 = file;
    }

    public static File A00(Context context) {
        StrictMode.ThreadPolicy threadPolicy;
        String str;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            threadPolicy = StrictMode.allowThreadDiskReads();
        } else {
            threadPolicy = null;
        }
        try {
            if (!AnonymousClass0Ls.A01) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File("/proc/self/cmdline"));
                    byte[] bArr = new byte[512];
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            str = new String(bArr, 0, read).trim();
                            AnonymousClass0Ls.A00 = str;
                            AnonymousClass0Ls.A01 = true;
                        } else {
                            throw new EOFException();
                        }
                    } finally {
                        fileInputStream.close();
                    }
                } catch (IOException unused) {
                    str = null;
                }
            }
            String str2 = AnonymousClass0Ls.A00;
            if (str2 == null) {
                str2 = "default";
            }
            File file = new File(context.getDir("light_prefs", 0), str2);
            file.mkdirs();
            return file;
        } finally {
            if (threadPolicy != null) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
        }
    }
}
