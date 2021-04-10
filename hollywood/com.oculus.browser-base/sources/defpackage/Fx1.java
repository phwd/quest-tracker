package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* renamed from: Fx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Fx1 {
    public static Bundle a(String str, String str2, String str3, String str4, byte[][] bArr, Map map, Ix1 ix1, List list, Map map2, Kx1 kx1, List list2) {
        Parcelable[] parcelableArr;
        String str5;
        String str6;
        String str7;
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("paymentRequestId", str);
        }
        if (str2 != null) {
            bundle.putString("merchantName", str2);
        }
        bundle.putString("topLevelOrigin", str3);
        bundle.putString("paymentRequestOrigin", str4);
        String str8 = null;
        if (bArr == null || bArr.length <= 0) {
            parcelableArr = null;
        } else {
            parcelableArr = new Parcelable[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                Bundle bundle2 = new Bundle();
                c(bArr[i], "certificateChain[" + i + "]");
                bundle2.putByteArray("certificate", bArr[i]);
                parcelableArr[i] = bundle2;
            }
            bundle.putParcelableArray("topLevelCertificateChain", parcelableArr);
        }
        bundle.putStringArrayList("methodNames", new ArrayList<>(map.keySet()));
        Bundle bundle3 = new Bundle();
        for (Map.Entry entry : map.entrySet()) {
            c(entry.getValue(), "methodDataMap's entry value");
            bundle3.putString((String) entry.getKey(), ((Jx1) entry.getValue()).b);
        }
        bundle.putParcelable("methodData", bundle3);
        String str9 = "{}";
        if (map2 != null) {
            Collection<Hx1> values = map2.values();
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            try {
                jsonWriter.beginArray();
                for (Hx1 hx1 : values) {
                    if (hx1 != null) {
                        hx1.a(jsonWriter);
                    } else {
                        throw new IllegalArgumentException("PaymentDetailsModifier should not be null.");
                    }
                }
                jsonWriter.endArray();
                str7 = stringWriter.toString();
            } catch (IOException unused) {
                str7 = str9;
            }
            bundle.putString("modifiers", str7);
        }
        if (ix1 != null) {
            Gx1 gx1 = ix1.f8260a;
            Objects.requireNonNull(gx1);
            StringWriter stringWriter2 = new StringWriter();
            try {
                gx1.a(new JsonWriter(stringWriter2));
                str6 = stringWriter2.toString();
            } catch (IOException unused2) {
                str6 = null;
            }
            if (str6 == null) {
                str6 = str9;
            }
            bundle.putString("total", str6);
        }
        if (kx1 != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putBoolean("requestPayerName", kx1.f8396a);
            bundle4.putBoolean("requestPayerEmail", kx1.b);
            bundle4.putBoolean("requestPayerPhone", kx1.c);
            bundle4.putBoolean("requestShipping", kx1.d);
            String str10 = kx1.e;
            if (str10 != null) {
                bundle4.putString("shippingType", str10);
            }
            bundle.putBundle("paymentOptions", bundle4);
        }
        if (kx1 != null && kx1.d) {
            bundle.putParcelableArray("shippingOptions", Lx1.a(list2));
        }
        if (str != null) {
            bundle.putString("id", str);
        }
        bundle.putString("origin", str3);
        bundle.putString("iframeOrigin", str4);
        if (parcelableArr != null) {
            bundle.putParcelableArray("certificateChain", parcelableArr);
        }
        String str11 = (String) ((Map.Entry) map.entrySet().iterator().next()).getKey();
        bundle.putString("methodName", str11);
        Jx1 jx1 = (Jx1) map.get(str11);
        if (jx1 == null) {
            str5 = str9;
        } else {
            str5 = jx1.b;
        }
        bundle.putString("data", str5);
        bundle.putParcelable("dataMap", bundle3);
        StringWriter stringWriter3 = new StringWriter();
        JsonWriter jsonWriter2 = new JsonWriter(stringWriter3);
        try {
            jsonWriter2.beginObject();
            if (ix1 != null) {
                jsonWriter2.name("total");
                ix1.a(jsonWriter2);
            }
            if (list != null) {
                jsonWriter2.name("displayItems").beginArray();
                jsonWriter2.endArray();
            }
            jsonWriter2.endObject();
            str8 = stringWriter3.toString();
        } catch (IOException unused3) {
        }
        if (str8 != null) {
            str9 = str8;
        }
        bundle.putString("details", str9);
        return bundle;
    }

    public static void b(Map map, String str) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(AbstractC2531fV.f(str, " should not be null or empty."));
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(AbstractC2531fV.f(str, " should not be null."));
        }
    }

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(AbstractC2531fV.f(str2, " should not be null or empty."));
        }
    }
}
