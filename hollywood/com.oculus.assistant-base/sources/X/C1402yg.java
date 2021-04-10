package X;

import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.xanalytics.XAnalyticsAdapterHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import com.oculus.os.AnalyticsEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/* renamed from: X.yg  reason: case insensitive filesystem */
public final class C1402yg implements AbstractC0241Mg {
    public static XAnalyticsAdapterHolder A00;

    public static void A00(String str, String str2, AnalyticsEvent analyticsEvent) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str2.getBytes(Charset.forName("UTF8")));
            try {
                JsonReader jsonReader = new JsonReader(new InputStreamReader(byteArrayInputStream));
                try {
                    JsonToken peek = jsonReader.peek();
                    if (peek != JsonToken.BEGIN_OBJECT) {
                        String name = peek.name();
                        if (C0139Dd.A01.A3Y(3)) {
                            C0139Dd.A01(C1402yg.class, StringFormatUtil.formatStrLocaleSafe("Invalid JSON token %s for event extra %s. Expecting BEGIN_OBJECT.", name, str2));
                        }
                        jsonReader.close();
                        byteArrayInputStream.close();
                        return;
                    }
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName = jsonReader.nextName();
                        JsonToken peek2 = jsonReader.peek();
                        if (peek2 == JsonToken.NUMBER) {
                            analyticsEvent.setExtra(nextName, Double.valueOf(jsonReader.nextDouble()));
                        } else if (peek2 == JsonToken.STRING) {
                            analyticsEvent.setExtra(nextName, jsonReader.nextString());
                        } else if (peek2 == JsonToken.BOOLEAN) {
                            analyticsEvent.setExtra(nextName, Boolean.valueOf(jsonReader.nextBoolean()));
                        } else {
                            jsonReader.skipValue();
                            String name2 = peek2.name();
                            if (C0139Dd.A01.A3Y(3)) {
                                C0139Dd.A01(C1402yg.class, StringFormatUtil.formatStrLocaleSafe("Ignoring extra field %s on %s, unsupported type %s", nextName, str, name2));
                            }
                        }
                    }
                    jsonReader.close();
                    byteArrayInputStream.close();
                    return;
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } catch (IOException unused3) {
        } catch (AssertionError e) {
            C0139Dd.A07(C1402yg.class, e, "AssertionError from JsonReader.peek() for : %s ", str2);
            throw e;
        }
    }

    public C1402yg() {
        if (A00 == null) {
            A00 = new XAnalyticsAdapterHolder(new C1401yf(this));
        }
    }

    @Override // X.AbstractC0241Mg
    public final XAnalyticsHolder A38() {
        return A00;
    }
}
