package defpackage;

import java.util.Collection;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: IX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IX0 implements Cy1 {

    /* renamed from: a  reason: collision with root package name */
    public final C3640ly1 f8231a;
    public final /* synthetic */ SingleWebsiteSettings b;

    public IX0(SingleWebsiteSettings singleWebsiteSettings, C3640ly1 ly1) {
        this.b = singleWebsiteSettings;
        this.f8231a = ly1;
    }

    @Override // defpackage.Cy1
    public void a(Collection collection) {
        if (this.b.u() != null) {
            this.b.M0 = SingleWebsiteSettings.A1(this.f8231a, collection);
            this.b.l1();
        }
    }
}
