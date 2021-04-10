package defpackage;

import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;

/* renamed from: G4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class G4 implements AbstractC3298jy1 {

    /* renamed from: a  reason: collision with root package name */
    public final AllSiteSettings f8059a;
    public final int[] b;

    public G4(AllSiteSettings allSiteSettings, int[] iArr) {
        this.f8059a = allSiteSettings;
        this.b = iArr;
    }

    @Override // defpackage.AbstractC3298jy1
    public void a() {
        AllSiteSettings allSiteSettings = this.f8059a;
        int[] iArr = this.b;
        Objects.requireNonNull(allSiteSettings);
        int i = iArr[0] - 1;
        iArr[0] = i;
        if (i <= 0) {
            allSiteSettings.k1();
        }
    }
}
