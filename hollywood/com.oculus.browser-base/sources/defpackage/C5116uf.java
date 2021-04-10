package defpackage;

import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.ThreadUtils;

/* renamed from: uf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5116uf extends AbstractC2556ff {

    /* renamed from: a  reason: collision with root package name */
    public static C5116uf f11427a;

    public static Set e(SharedPreferences sharedPreferences) {
        Set<String> stringSet = sharedPreferences.getStringSet("bts_cached_uma", new HashSet());
        if (stringSet != null && stringSet.contains(null)) {
            stringSet.remove(null);
        }
        return stringSet;
    }

    public static C5116uf f() {
        if (f11427a == null) {
            f11427a = new C5116uf();
        }
        return f11427a;
    }

    @Override // defpackage.AbstractC2556ff
    public void a(int i, boolean z) {
        int b = AbstractC2556ff.b(i);
        c("Android.NativeBackgroundTask.TaskStarted", b);
        if (z) {
            c("Android.NativeBackgroundTask.TaskStarted.ReducedMode", b);
        } else {
            c("Android.NativeBackgroundTask.TaskStarted.FullBrowser", b);
        }
    }

    public void c(String str, int i) {
        String str2;
        C4946tf tfVar;
        SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
        Set e = e(sharedPreferences);
        String str3 = str + ":" + i + ":";
        Iterator it = e.iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = null;
                break;
            }
            str2 = (String) it.next();
            if (str2.startsWith(str3)) {
                break;
            }
        }
        HashSet hashSet = new HashSet(e);
        if (str2 != null) {
            tfVar = C4946tf.a(str2);
            if (tfVar == null) {
                tfVar = new C4946tf(str, i, 1);
            }
            hashSet.remove(str2);
            tfVar.c++;
        } else {
            tfVar = new C4946tf(str, i, 1);
        }
        hashSet.add(tfVar.toString());
        Object obj = ThreadUtils.f10596a;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (hashSet.contains(null)) {
            hashSet.remove(null);
        }
        edit.putStringSet("bts_cached_uma", hashSet);
        edit.apply();
    }

    public void d() {
        Object obj = ThreadUtils.f10596a;
        for (String str : e(AbstractC3983nz.f10523a)) {
            C4946tf a2 = C4946tf.a(str);
            if (a2 != null) {
                for (int i = 0; i < a2.c; i++) {
                    AbstractC3364kK0.g(a2.f11357a, a2.b, 27);
                }
            }
        }
        Object obj2 = ThreadUtils.f10596a;
        AbstractC3983nz.f10523a.edit().remove("bts_cached_uma").apply();
    }

    public void g(int i, boolean z) {
        if (z) {
            c("Android.BackgroundTaskScheduler.TaskCreated.WithExpiration", AbstractC2556ff.b(i));
        } else {
            c("Android.BackgroundTaskScheduler.TaskCreated.WithoutExpiration", AbstractC2556ff.b(i));
        }
    }

    public void h() {
        c("Android.BackgroundTaskScheduler.TaskRescheduled", 0);
    }

    public void i(int i) {
        c("Android.BackgroundTaskScheduler.TaskStarted", AbstractC2556ff.b(i));
    }

    public void j(int i) {
        c("Android.BackgroundTaskScheduler.TaskStopped", AbstractC2556ff.b(i));
    }
}
