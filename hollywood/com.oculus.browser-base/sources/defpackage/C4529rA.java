package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: rA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4529rA extends AbstractC5431wV {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4700sA f11187a;

    public C4529rA(C4700sA sAVar, AbstractC4359qA qAVar) {
        this.f11187a = sAVar;
    }

    @Override // defpackage.AbstractC5601xV
    public void a(int i, int i2) {
        ContextualSearchManager contextualSearchManager = (ContextualSearchManager) this.f11187a.b;
        if (!contextualSearchManager.n()) {
            contextualSearchManager.i(8);
        }
    }

    @Override // defpackage.AbstractC5601xV, defpackage.AbstractC5431wV
    public void c() {
        this.f11187a.p = System.nanoTime();
        C4700sA sAVar = this.f11187a;
        sAVar.q = TextUtils.isEmpty(sAVar.f);
    }

    @Override // defpackage.AbstractC5601xV, defpackage.AbstractC5431wV
    public void d() {
        this.f11187a.o = System.nanoTime();
    }

    @Override // defpackage.AbstractC5601xV
    public void e(int i, int i2) {
        this.f11187a.o = System.nanoTime();
        ContextualSearchManager contextualSearchManager = (ContextualSearchManager) this.f11187a.b;
        if (contextualSearchManager.P.g == 3) {
            Objects.requireNonNull(contextualSearchManager.T);
        }
    }
}
