package defpackage;

import android.util.Log;
import com.oculus.browser.NewTabMessageHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: cr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2081cr0 extends RN implements AbstractC0530Iq0 {
    public static final Object F = new Object();
    public static C2081cr0 G;
    public String H;
    public long I;

    /* renamed from: J  reason: collision with root package name */
    public HashSet f9725J = new HashSet();
    public String K;
    public QN L;

    public C2081cr0(QN qn) {
        this.L = qn;
        qn.b = this;
        C0591Jq0.a(this);
        this.I = 0;
    }

    public static C2081cr0 f() {
        C2081cr0 cr0;
        synchronized (F) {
            if (G == null) {
                G = new C2081cr0(new QN());
            }
            cr0 = G;
        }
        return cr0;
    }

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
    }

    @Override // defpackage.RN
    public void b(String str) {
        Log.i("OculusExplore", str);
        g(str);
        this.H = str;
        this.I = System.currentTimeMillis();
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.K = str;
        e();
    }

    public void e() {
        if (this.K == null) {
            g("{\"error\": \"No access token\"}");
        } else if (this.H == null || System.currentTimeMillis() - this.I >= 5000) {
            try {
                this.L.d("https://graph.oculus.com/graphql?access_token=" + this.K + "&q=" + URLEncoder.encode("viewer() {  oculus_browser_new_tab {    edges {      node {id, trace_id,prime_render_info {  title,  image_click_info {    navigate_info {      location    }  },  image.size_tag(h192) {    uri  }}      }    }  }}", "UTF-8"), null, 2, 2);
            } catch (UnsupportedEncodingException e) {
                StringBuilder i = AbstractC2531fV.i("Failed to encode url: ");
                i.append(e.toString());
                Log.e("OculusExplore", i.toString());
            }
        } else {
            String str = this.H;
            if (str != null) {
                Log.i("OculusExplore", str);
                g(str);
                this.H = str;
                this.I = System.currentTimeMillis();
            }
        }
    }

    public void g(String str) {
        HashSet hashSet;
        synchronized (this.f9725J) {
            hashSet = (HashSet) this.f9725J.clone();
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            NewTabMessageHandler newTabMessageHandler = (NewTabMessageHandler) ((AbstractC1910br0) it.next());
            long j = newTabMessageHandler.F;
            if (j != 0) {
                newTabMessageHandler.nativePublishStringMessage(j, "update_new_tab_stories", str);
            }
        }
    }
}
