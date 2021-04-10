package defpackage;

import J.N;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.LauncherShortcutActivity;

/* renamed from: Ht  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0474Ht implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC0474Ht(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        XO xo;
        AbstractActivityC2601fu fuVar = this.F;
        AbstractC3100ip1.f10165a.d("MemoryAndroid.DeviceMemoryClass", ((ActivityManager) fuVar.getSystemService("activity")).getMemoryClass());
        PU0 pu0 = NU0.f8549a;
        boolean M$3vpOHw = N.M$3vpOHw();
        boolean d = pu0.d("incognito-shortcut-added", false);
        if (M$3vpOHw) {
            Intent intent = new Intent("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB");
            intent.setPackage(fuVar.getPackageName());
            intent.setClass(fuVar, LauncherShortcutActivity.class);
            if (((ShortcutManager) fuVar.getSystemService(ShortcutManager.class)).addDynamicShortcuts(Arrays.asList(new ShortcutInfo.Builder(fuVar, "dynamic-new-incognito-tab-shortcut").setShortLabel(fuVar.getResources().getString(R.string.f46090_resource_name_obfuscated_RES_2131951926)).setLongLabel(fuVar.getResources().getString(R.string.f54680_resource_name_obfuscated_RES_2131952785)).setIcon(Icon.createWithResource(fuVar, (int) R.drawable.f34980_resource_name_obfuscated_RES_2131231538)).setIntent(intent).build()))) {
                pu0.m("incognito-shortcut-added", true);
            }
        } else if (!M$3vpOHw && d) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("dynamic-new-incognito-tab-shortcut");
            ShortcutManager shortcutManager = (ShortcutManager) fuVar.getSystemService(ShortcutManager.class);
            shortcutManager.disableShortcuts(arrayList);
            shortcutManager.removeDynamicShortcuts(arrayList);
            pu0.m("incognito-shortcut-added", false);
        }
        C2940ht htVar = new C2940ht(new C3110it(), fuVar.l1);
        Executor executor = AbstractC2032cb.f9616a;
        htVar.f();
        ((ExecutorC1463Ya) executor).execute(htVar.e);
        if (fuVar.I1.get() != null && fuVar.C1) {
            long j = fuVar.a0;
            C3818n01 n01 = (C3818n01) ((AbstractC2451f01) fuVar.I1.get());
            M01 m01 = n01.c;
            m01.G.b(j);
            UH0 uh0 = m01.I;
            if (!(uh0 == null || (xo = (XO) uh0.g(N01.i)) == null)) {
                C2861hP hPVar = xo.j;
                boolean z = xo.f;
                hPVar.S = j;
                hPVar.R = z;
                AbstractC2793h01.e("FeedStreamCreatedTime", xo.q - j, z);
            }
            Boolean bool = m01.f0;
            if (bool != null) {
                AbstractC3100ip1.f10165a.a("Startup.Android.CachedFeedVisibilityConsistency", bool.equals(m01.g0));
            }
            C5927zO zOVar = n01.p;
            if (zOVar != null) {
                AbstractC2793h01.e("FeedsLoadingPlaceholderShown", zOVar.c.H - j, true);
            }
        }
    }
}
