package defpackage;

import java.util.List;
import org.chromium.chrome.browser.datareduction.settings.DataReductionStatsPreference;

/* renamed from: dD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2147dD extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DataReductionStatsPreference f9758a;

    public C2147dD(DataReductionStatsPreference dataReductionStatsPreference) {
        this.f9758a = dataReductionStatsPreference;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        List list = (List) obj;
        DataReductionStatsPreference dataReductionStatsPreference = this.f9758a;
        dataReductionStatsPreference.v0 = list;
        dataReductionStatsPreference.F0.a(list);
    }
}
