package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: KB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KB extends C0980Qb1 {
    public final /* synthetic */ OB M;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KB(OB ob, Tab tab) {
        super(tab);
        this.M = ob;
    }

    @Override // defpackage.C0980Qb1
    public int p() {
        int p = super.p();
        if (p != 3) {
            return p;
        }
        Objects.requireNonNull(this.M);
        return 1;
    }
}
