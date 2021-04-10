package defpackage;

import android.view.View;
import org.chromium.chrome.browser.datareduction.settings.DataReductionStatsPreference;

/* renamed from: eD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2317eD implements AbstractC4219pK0 {
    public final /* synthetic */ View F;
    public final /* synthetic */ DataReductionStatsPreference G;

    public C2317eD(DataReductionStatsPreference dataReductionStatsPreference, View view) {
        this.G = dataReductionStatsPreference;
        this.F = view;
    }

    @Override // defpackage.AbstractC4219pK0
    public void a() {
        this.F.setMinimumHeight(this.G.F.getResources().getDisplayMetrics().heightPixels - this.G.w0.F.top);
    }

    @Override // defpackage.AbstractC4219pK0
    public void b() {
    }
}
