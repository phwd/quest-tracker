package defpackage;

import android.content.ComponentName;

/* renamed from: JT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JT0 implements AbstractC2018cU0 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC2018cU0 f8289a;

    public JT0(AbstractC2018cU0 cu0) {
        this.f8289a = cu0;
    }

    @Override // defpackage.AbstractC2018cU0
    public void a() {
        AbstractC2018cU0 cu0 = this.f8289a;
        if (cu0 != null) {
            cu0.a();
        }
    }

    @Override // defpackage.AbstractC2018cU0
    public void b(ComponentName componentName) {
        PU0 pu0 = NU0.f8549a;
        pu0.p("last_shared_package_name", componentName.getPackageName());
        pu0.p("last_shared_class_name", componentName.getClassName());
        AbstractC2018cU0 cu0 = this.f8289a;
        if (cu0 != null) {
            cu0.b(componentName);
        }
    }
}
