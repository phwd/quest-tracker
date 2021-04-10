package org.chromium.services.data_decoder;

import J.N;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.MalformedJsonException;
import com.oculus.os.Version;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JsonSanitizer {
    public static String a(String str) {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        StringWriter stringWriter = new StringWriter(str.length());
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                switch (N40.f8526a[jsonReader.peek().ordinal()]) {
                    case 1:
                        i++;
                        if (i < 200) {
                            jsonReader.beginArray();
                            jsonWriter.beginArray();
                            break;
                        } else {
                            throw new IllegalStateException("Too much nesting");
                        }
                    case 2:
                        i--;
                        jsonReader.endArray();
                        jsonWriter.endArray();
                        break;
                    case 3:
                        i++;
                        if (i < 200) {
                            jsonReader.beginObject();
                            jsonWriter.beginObject();
                            break;
                        } else {
                            throw new IllegalStateException("Too much nesting");
                        }
                    case 4:
                        i--;
                        jsonReader.endObject();
                        jsonWriter.endObject();
                        break;
                    case 5:
                        String nextName = jsonReader.nextName();
                        b(nextName);
                        jsonWriter.name(nextName);
                        break;
                    case 6:
                        String nextString = jsonReader.nextString();
                        b(nextString);
                        jsonWriter.value(nextString);
                        break;
                    case Version.VERSION_7:
                        String nextString2 = jsonReader.nextString();
                        try {
                            jsonWriter.value(Long.parseLong(nextString2));
                            break;
                        } catch (NumberFormatException unused) {
                            jsonWriter.value(Double.parseDouble(nextString2));
                            break;
                        }
                    case Version.VERSION_8:
                        jsonWriter.value(jsonReader.nextBoolean());
                        break;
                    case Version.VERSION_9:
                        jsonReader.nextNull();
                        jsonWriter.nullValue();
                        break;
                    case Version.VERSION_10:
                        z = true;
                        break;
                }
            } catch (Throwable th) {
                O21.a(jsonReader);
                O21.a(jsonWriter);
                throw th;
            }
        }
        String stringWriter2 = stringWriter.toString();
        O21.a(jsonReader);
        O21.a(jsonWriter);
        return stringWriter2;
    }

    public static String b(String str) {
        int length = str.length();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            char charAt = str.charAt(i);
            if (Character.isLowSurrogate(charAt)) {
                break;
            }
            if (Character.isHighSurrogate(charAt)) {
                i++;
                if (i >= length) {
                    break;
                }
                char charAt2 = str.charAt(i);
                if (!Character.isLowSurrogate(charAt2)) {
                    break;
                }
                Character.toCodePoint(charAt, charAt2);
            }
            i++;
        }
        if (z) {
            return str;
        }
        throw new MalformedJsonException("Invalid escape sequence");
    }

    public static void sanitize(long j, String str) {
        try {
            N.MkUGEqr$(j, a(str));
        } catch (IOException | IllegalStateException e) {
            N.MOkhqs8N(j, e.getMessage());
        }
    }
}
