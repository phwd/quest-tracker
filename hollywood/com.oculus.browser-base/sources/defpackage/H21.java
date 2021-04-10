package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* renamed from: H21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H21 {

    /* renamed from: a  reason: collision with root package name */
    public static final Lock f8131a = new ReentrantLock();
    public static H21 b;
    public final Lock c = new ReentrantLock();
    public final SharedPreferences d;

    public H21(Context context) {
        this.d = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static H21 a(Context context) {
        Objects.requireNonNull(context, "null reference");
        Lock lock = f8131a;
        lock.lock();
        try {
            if (b == null) {
                b = new H21(context.getApplicationContext());
            }
            H21 h21 = b;
            lock.unlock();
            return h21;
        } catch (Throwable th) {
            f8131a.unlock();
            throw th;
        }
    }

    public GoogleSignInAccount b() {
        String c2 = c("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(c2).length() + 20);
        sb.append("googleSignInAccount");
        sb.append(":");
        sb.append(c2);
        String c3 = c(sb.toString());
        if (c3 == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.A(c3);
        } catch (JSONException unused) {
            return null;
        }
    }

    public final String c(String str) {
        this.c.lock();
        try {
            return this.d.getString(str, null);
        } finally {
            this.c.unlock();
        }
    }
}
