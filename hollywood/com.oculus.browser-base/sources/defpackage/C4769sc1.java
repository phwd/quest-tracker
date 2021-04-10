package defpackage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.endpoint_fetcher.EndpointResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: sc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4769sc1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4939tc1 f11286a;
    public final Callback b;
    public final C3836n61 c;

    public C4769sc1(C4939tc1 tc1, Callback callback, C3836n61 n61) {
        this.f11286a = tc1;
        this.b = callback;
        this.c = n61;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int i;
        C4939tc1 tc1 = this.f11286a;
        Callback callback = this.b;
        C3836n61 n61 = this.c;
        EndpointResponse endpointResponse = (EndpointResponse) obj;
        Objects.requireNonNull(tc1);
        LinkedList linkedList = new LinkedList();
        try {
            JSONObject jSONObject = new JSONObject(endpointResponse.f10666a);
            if (jSONObject.isNull("suggestions")) {
                callback.onResult(new C3574lc1(Collections.emptyList(), n61));
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("suggestions");
            for (int i2 = 0; i2 < jSONObject.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                List a2 = C3836n61.a(jSONObject2.getJSONArray("tabs").toString());
                String str = (String) jSONObject2.get("action");
                str.hashCode();
                if (str.equals("Close")) {
                    i = 1;
                } else if (!str.equals("Group")) {
                    AbstractC1220Ua0.a("TSSF", String.format("Unknown action: %s\n", str), new Object[0]);
                    i = -1;
                } else {
                    i = 0;
                }
                linkedList.add(new C1529Zb1(a2, i, (String) jSONObject2.get("providerName")));
            }
            callback.onResult(new C3574lc1(linkedList, n61));
        } catch (JSONException e) {
            AbstractC1220Ua0.a("TSSF", String.format("There was a problem parsing the JSON\n Details: %s", e.getMessage()), new Object[0]);
        }
    }
}
