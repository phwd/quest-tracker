package defpackage;

import android.util.JsonWriter;

/* renamed from: Gx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Gx1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8125a;
    public final String b;

    public Gx1(String str, String str2) {
        this.f8125a = str;
        this.b = str2;
    }

    public void a(JsonWriter jsonWriter) {
        jsonWriter.beginObject();
        jsonWriter.name("currency").value(this.f8125a);
        jsonWriter.name("value").value(this.b);
        jsonWriter.endObject();
    }
}
