package defpackage;

import android.util.JsonWriter;

/* renamed from: Hx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Hx1 {

    /* renamed from: a  reason: collision with root package name */
    public final Ix1 f8190a;
    public final Jx1 b;

    public Hx1(Ix1 ix1, Jx1 jx1) {
        this.f8190a = ix1;
        this.b = jx1;
    }

    public final void a(JsonWriter jsonWriter) {
        jsonWriter.beginObject();
        if (this.f8190a != null) {
            jsonWriter.name("total");
            this.f8190a.a(jsonWriter);
        } else {
            jsonWriter.name("total").nullValue();
        }
        jsonWriter.name("supportedMethods").beginArray();
        jsonWriter.value(this.b.f8330a);
        jsonWriter.endArray();
        jsonWriter.name("data").value(this.b.b);
        jsonWriter.endObject();
    }
}
