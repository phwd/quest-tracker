package defpackage;

import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* renamed from: co0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2072co0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2584fo0 f9633a;
    public final List b;

    public C2072co0(C2584fo0 fo0, List list) {
        this.f9633a = fo0;
        this.b = list;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2584fo0 fo0 = this.f9633a;
        List list = this.b;
        Tutorial tutorial = (Tutorial) obj;
        Objects.requireNonNull(fo0);
        if (tutorial != null) {
            list.add(tutorial);
        }
        fo0.b.a(new C2413eo0(fo0, list));
    }
}
