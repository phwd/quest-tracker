package com.oculus.browser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PwaAppListFetcher extends RN implements AbstractC0530Iq0 {
    public static PwaAppListFetcher F = new PwaAppListFetcher();
    public final QN G;
    public String H;
    public KI0 I = new KI0(this, Long.MAX_VALUE);

    public PwaAppListFetcher() {
        new LinkedList();
        QN qn = new QN();
        this.G = qn;
        qn.b = this;
        C0591Jq0.a(this);
    }

    public static native void nativeAddToStore(String str, String str2, String str3, String str4, String str5);

    public static native void nativeClearStore();

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
    }

    @Override // defpackage.RN
    public void b(String str) {
        try {
            AbstractC1220Ua0.d("PwaAppListFetcher", "jsonResponse: " + str, new Object[0]);
            JSONArray jSONArray = new JSONObject(str).getJSONObject("data").getJSONObject("app_store_item_list").getJSONObject("all_items").getJSONArray("nodes");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                nativeAddToStore(jSONObject.getString("package_name"), jSONObject.getString("pwa_scope"), jSONObject.getString("id"), jSONObject.getJSONObject("icon_image").getString("uri"), jSONObject.getString("display_name"));
            }
        } catch (JSONException e) {
            StringBuilder i2 = AbstractC2531fV.i("JSON failed to parse: ");
            i2.append(e.toString());
            AbstractC1220Ua0.a("PwaAppListFetcher", i2.toString(), new Object[0]);
        }
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.H = str;
        if (this.I.a()) {
            e();
        } else {
            this.I.f8358a = 0;
        }
    }

    public final void e() {
        if (Gatekeeper.g().h(TU.PWA)) {
            nativeClearStore();
            try {
                this.G.d("https://graph.oculus.com/graphql?access_token=" + this.H + "&doc=" + URLEncoder.encode("{  app_store_item_list(id:\"3841740682542701\") {    all_items {      nodes {        ... on Application {          package_name,          id,          pwa_scope,          display_name,          icon_image {            uri          }        }      }    }  }}", "UTF-8"), null, 2, 2);
                KI0 ki0 = this.I;
                Objects.requireNonNull(ki0);
                ki0.f8358a = System.currentTimeMillis();
            } catch (UnsupportedEncodingException e) {
                StringBuilder i = AbstractC2531fV.i("Failed to encode url: ");
                i.append(e.toString());
                AbstractC1220Ua0.a("PwaAppListFetcher", i.toString(), new Object[0]);
            }
        }
    }

    public void f() {
        if (this.H == null) {
            this.I.f8358a = 0;
        } else if (this.I.a()) {
            e();
        }
    }
}
