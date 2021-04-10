package defpackage;

import android.content.SharedPreferences;
import android.util.SparseArray;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: dM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2173dM0 {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBoolean f9775a = new AtomicBoolean();
    public static SparseArray b;
    public long c = -1;
    public long d = 100;
    public final SharedPreferences e;

    public C2173dM0(int i) {
        SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("customtabs_client_bans", 0);
        this.e = sharedPreferences;
        sharedPreferences.getFloat("score_" + i, 10.0f);
        sharedPreferences.getLong("last_request_" + i, 0);
        sharedPreferences.getLong("banned_until_" + i, 0);
    }

    public static C2173dM0 a(int i) {
        String key;
        if (b == null) {
            b = new SparseArray();
            SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("customtabs_client_bans", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            long currentTimeMillis = System.currentTimeMillis();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                if (!(entry == null || (key = entry.getKey()) == null || !key.startsWith("last_request_"))) {
                    try {
                        if (currentTimeMillis - ((Long) entry.getValue()).longValue() >= 1209600000) {
                            String substring = key.substring(13);
                            SharedPreferences.Editor remove = edit.remove("score_" + substring);
                            SharedPreferences.Editor remove2 = remove.remove("last_request_" + substring);
                            remove2.remove("banned_until_" + substring);
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            edit.apply();
        }
        C2173dM0 dm0 = (C2173dM0) b.get(i);
        if (dm0 != null) {
            return dm0;
        }
        C2173dM0 dm02 = new C2173dM0(i);
        b.put(i, dm02);
        return dm02;
    }

    public static void b() {
        if (!(!f9775a.compareAndSet(false, true))) {
            PostTask.b(C3070if1.b, new RunnableC2002cM0(), 0);
        }
    }
}
