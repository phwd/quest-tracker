package defpackage;

import android.util.JsonWriter;

/* renamed from: Ix1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Ix1 {

    /* renamed from: a  reason: collision with root package name */
    public final Gx1 f8260a;

    public Ix1(Gx1 gx1) {
        this.f8260a = gx1;
    }

    public void a(JsonWriter jsonWriter) {
        jsonWriter.beginObject();
        jsonWriter.name("label").value("");
        jsonWriter.name("amount");
        this.f8260a.a(jsonWriter);
        jsonWriter.endObject();
    }
}
