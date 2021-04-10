package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: S21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S21 implements AbstractC6017zw {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ X21 f8871a;

    public S21(X21 x21) {
        this.f8871a = x21;
    }

    @Override // defpackage.AbstractC6017zw
    public void a(long j) {
        X21 x21 = this.f8871a;
        TabModel tabModel = x21.d;
        if (tabModel != null) {
            if (!tabModel.a()) {
                x21.d.d();
            }
            x21.e.e();
        }
    }
}
