package defpackage;

import J.N;
import android.content.Intent;
import android.net.Uri;
import com.oculus.browser.PanelApp;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: rw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4658rw0 extends C2003cN {
    public Tab e;
    public final /* synthetic */ PanelApp f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4658rw0(PanelApp panelApp, Tab tab) {
        super(tab);
        this.f = panelApp;
        this.e = tab;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f0 A[Catch:{ Exception -> 0x014a }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0108 A[SYNTHETIC] */
    @Override // defpackage.C2003cN, defpackage.AbstractC1652aN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c(android.content.Intent r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 334
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4658rw0.c(android.content.Intent, boolean):int");
    }

    @Override // defpackage.C2003cN, defpackage.AbstractC1652aN
    public boolean e() {
        return !((TabImpl) this.e).isHidden();
    }

    public boolean r(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            if (!this.f.f0.Q || ((TabImpl) this.e).isHidden()) {
                AbstractC1220Ua0.d("PanelApp", "Ignoring intent launch as not in webtask mode or tab is hidden", new Object[0]);
                N.MnIWU0oo(this.f.f9704J, data.getScheme(), 3);
            } else {
                String scheme = data.getScheme();
                N.MnIWU0oo(this.f.f9704J, scheme, 1);
                AbstractC1220Ua0.d("PanelApp", "intent launch for schema " + scheme, new Object[0]);
                intent.getDataString();
                this.f9603a.startActivity(intent);
                return true;
            }
        }
        return false;
    }
}
