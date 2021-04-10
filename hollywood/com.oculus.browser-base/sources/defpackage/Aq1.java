package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.component_updater.UpdateScheduler;

/* renamed from: Aq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Aq1 extends AbstractC4798sm0 {
    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        UpdateScheduler instance = UpdateScheduler.getInstance();
        instance.a(instance.d);
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        UpdateScheduler.getInstance().b = ne;
        return 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        UpdateScheduler instance = UpdateScheduler.getInstance();
        long j = instance.c;
        if (j != 0) {
            N.MlOPWyho(j, instance);
        }
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        UpdateScheduler instance = UpdateScheduler.getInstance();
        long j = instance.c;
        if (j != 0) {
            N.MGPsKKYQ(j, instance);
        }
        instance.b = null;
        instance.a(instance.d);
        return false;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        UpdateScheduler instance = UpdateScheduler.getInstance();
        long j = instance.c;
        if (j != 0) {
            N.MGPsKKYQ(j, instance);
        }
        instance.b = null;
        instance.a(instance.d);
        return false;
    }
}
