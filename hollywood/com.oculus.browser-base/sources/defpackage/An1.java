package defpackage;

import android.content.SharedPreferences;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.endpoint_fetcher.EndpointResponse;

/* renamed from: An1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class An1 extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        EndpointResponse endpointResponse = (EndpointResponse) obj;
        if (endpointResponse != null) {
            try {
                List d = Dn1.d(new StringReader(endpointResponse.f10666a));
                if (d != null) {
                    Dn1.e(d);
                    SharedPreferences.Editor edit = Dn1.b().edit();
                    Objects.requireNonNull(Dn1.a());
                    edit.putLong("suppressed-until", System.currentTimeMillis() + ((long) AbstractC2793h01.j.c())).apply();
                }
            } catch (IOException e) {
                AbstractC1220Ua0.f("TrendyTerms", "Failed parsing trendy terms.", e);
            }
        }
    }
}
