package X;

import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.xanalytics.XAnalyticsAdapterHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class hD implements AbstractC0241Mg {
    public yZ A00;
    public final XAnalyticsAdapterHolder A01 = new XAnalyticsAdapterHolder(new hC(this));

    public static /* synthetic */ void A00(String str, String str2, C00446t r9) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str2.getBytes(Charset.forName("UTF8")));
            try {
                JsonReader jsonReader = new JsonReader(new InputStreamReader(byteArrayInputStream));
                try {
                    C0847jr A04 = r9.A04();
                    JsonToken peek = jsonReader.peek();
                    if (peek != JsonToken.BEGIN_OBJECT) {
                        String name = peek.name();
                        if (C0139Dd.A01.A3Y(3)) {
                            C0139Dd.A01(C00789g.class, StringFormatUtil.formatStrLocaleSafe("Invalid JSON token %s for event extra %s. Expecting BEGIN_OBJECT.", name, str2));
                        }
                    } else {
                        jsonReader.beginObject();
                        C00789g.A01(str, A04, jsonReader);
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
            C0139Dd.A0V("AssistantXAnalyticsProvider", e, "AssertionError from JsonReader.peek() for : %s ", str2);
            throw e;
        }
    }

    @Override // X.AbstractC0241Mg
    public final XAnalyticsHolder A38() {
        return this.A01;
    }
}
