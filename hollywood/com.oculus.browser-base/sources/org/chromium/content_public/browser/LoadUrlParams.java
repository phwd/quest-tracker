package org.chromium.content_public.browser;

import java.util.Locale;
import java.util.Map;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LoadUrlParams {

    /* renamed from: a  reason: collision with root package name */
    public String f10938a;
    public Origin b;
    public int c;
    public C2512fL0 d;
    public Map e;
    public String f;
    public int g = 0;
    public ResourceRequestBody h = null;
    public boolean i;
    public boolean j;
    public long k;
    public long l;
    public boolean m;

    public LoadUrlParams(String str, int i2) {
        this.f10938a = str;
        this.c = i2;
    }

    public final String a(String str, boolean z) {
        if (this.e == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : this.e.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(((String) entry.getKey()).toLowerCase(Locale.US));
            sb.append(":");
            sb.append((String) entry.getValue());
        }
        if (z) {
            sb.append(str);
        }
        return sb.toString();
    }
}
