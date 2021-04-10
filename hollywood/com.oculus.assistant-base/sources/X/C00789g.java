package X;

import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.common.stringformat.StringFormatUtil;

/* renamed from: X.9g  reason: invalid class name and case insensitive filesystem */
public final class C00789g {
    public static void A00(String str, C0846jq jqVar, JsonReader jsonReader) {
        while (jsonReader.hasNext()) {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NUMBER) {
                C0846jq.A01(jqVar, Double.valueOf(jsonReader.nextDouble()));
            } else if (peek == JsonToken.STRING) {
                C0846jq.A01(jqVar, jsonReader.nextString());
            } else if (peek == JsonToken.BOOLEAN) {
                C0846jq.A01(jqVar, Boolean.valueOf(jsonReader.nextBoolean()));
            } else if (peek == JsonToken.BEGIN_OBJECT) {
                jsonReader.beginObject();
                C0847jr A00 = jqVar.A01.A00();
                C0846jq.A00(jqVar, A00);
                A01(str, A00, jsonReader);
            } else if (peek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                DR dr = jqVar.A01;
                C0846jq jqVar2 = (C0846jq) dr.A01.A18();
                if (jqVar2 == null) {
                    jqVar2 = new C0846jq(dr.A00);
                }
                jqVar2.A03(dr);
                C0846jq.A00(jqVar, jqVar2);
                A00(str, jqVar2, jsonReader);
            } else {
                jsonReader.skipValue();
                Object[] objArr = {str, peek.name()};
                if (C0139Dd.A01.A3Y(6)) {
                    C0139Dd.A0E(C00789g.class.getSimpleName(), StringFormatUtil.formatStrLocaleSafe("Ignoring extra array field in %s, unsupported type %s", objArr));
                }
            }
        }
        jsonReader.endArray();
    }

    public static void A01(String str, C0847jr jrVar, JsonReader jsonReader) {
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NUMBER) {
                C0847jr.A01(jrVar, nextName, Double.valueOf(jsonReader.nextDouble()));
            } else if (peek == JsonToken.STRING) {
                C0847jr.A01(jrVar, nextName, jsonReader.nextString());
            } else if (peek == JsonToken.BOOLEAN) {
                C0847jr.A01(jrVar, nextName, Boolean.valueOf(jsonReader.nextBoolean()));
            } else if (peek == JsonToken.BEGIN_OBJECT) {
                jsonReader.beginObject();
                A01(nextName, jrVar.A05(nextName), jsonReader);
            } else if (peek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                A00(nextName, jrVar.A04(nextName), jsonReader);
            } else {
                jsonReader.skipValue();
                String name = peek.name();
                if (C0139Dd.A01.A3Y(3)) {
                    C0139Dd.A01(C00789g.class, StringFormatUtil.formatStrLocaleSafe("Ignoring extra field %s on %s, unsupported type %s", nextName, str, name));
                }
            }
        }
        jsonReader.endObject();
    }
}
