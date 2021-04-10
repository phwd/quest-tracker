package defpackage;

import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: Ky  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0667Ky implements Runnable {
    public void run() {
        ComponentCallbacks2C3933ni0 ni0 = ComponentCallbacks2C3933ni0.F;
        Object obj = ThreadUtils.f10596a;
        ComponentCallbacks2C3933ni0.F = new ComponentCallbacks2C3933ni0("ChildService");
        ContextUtils.getApplicationContext().registerComponentCallbacks(ComponentCallbacks2C3933ni0.F);
    }
}
