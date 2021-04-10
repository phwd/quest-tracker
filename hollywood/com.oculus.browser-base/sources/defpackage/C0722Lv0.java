package defpackage;

import android.app.Activity;
import android.app.usage.UsageStatsManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* renamed from: Lv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0722Lv0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0905Ov0 f8447a;
    public final String b;

    public C0722Lv0(C0905Ov0 ov0, String str) {
        this.f8447a = ov0;
        this.b = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0905Ov0 ov0 = this.f8447a;
        String str = this.b;
        String str2 = (String) obj;
        Objects.requireNonNull(ov0);
        if (str2 != null) {
            try {
                UsageStatsManager.class.getDeclaredMethod(str, Activity.class, String.class).invoke((UsageStatsManager) ov0.f8655a.getSystemService("usagestats"), ov0.f8655a, str2);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                AbstractC1220Ua0.a("PageViewObserver", "Failed to report to platform API", e);
            }
        }
    }
}
