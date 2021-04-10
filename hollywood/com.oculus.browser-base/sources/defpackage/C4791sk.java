package defpackage;

import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* renamed from: sk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4791sk implements AbstractC4451qk {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RunnableC4961tk f11295a;

    public C4791sk(RunnableC4961tk tkVar) {
        this.f11295a = tkVar;
    }

    @Override // defpackage.AbstractC4451qk
    public void a() {
    }

    @Override // defpackage.AbstractC4451qk
    public void onSuccess() {
        Context applicationContext = ContextUtils.getApplicationContext();
        this.f11295a.F.m = new TracingControllerAndroidImpl(applicationContext);
        TracingControllerAndroidImpl tracingControllerAndroidImpl = this.f11295a.F.m;
        applicationContext.registerReceiver(tracingControllerAndroidImpl.b, tracingControllerAndroidImpl.c);
    }
}
