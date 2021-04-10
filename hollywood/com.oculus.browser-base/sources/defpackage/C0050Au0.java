package defpackage;

import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.endpoint_fetcher.EndpointResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: Au0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0050Au0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0111Bu0 f7704a;
    public final Callback b;

    public C0050Au0(C0111Bu0 bu0, Callback callback) {
        this.f7704a = bu0;
        this.b = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0111Bu0 bu0 = this.f7704a;
        Callback callback = this.b;
        Objects.requireNonNull(bu0);
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(((EndpointResponse) obj).f10666a).getJSONArray("annotations");
            for (int i = 0; i < jSONArray.length(); i++) {
                AbstractC4822su0 a2 = AbstractC5162uu0.a(jSONArray.getJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        } catch (JSONException e) {
            StringBuilder i2 = AbstractC2531fV.i("Failed to parse SingleUrlPageAnnotations response. Details: ");
            i2.append(e.toString());
            AbstractC1220Ua0.a("PASP", i2.toString(), new Object[0]);
        }
        callback.onResult(new C2536fX0(arrayList));
    }
}
