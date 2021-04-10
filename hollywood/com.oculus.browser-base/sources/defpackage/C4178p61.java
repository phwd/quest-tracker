package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: p61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4178p61 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4349q61 f11049a;

    public C4178p61(C4349q61 q61) {
        this.f11049a = q61;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        Objects.requireNonNull(this.f11049a);
    }
}
