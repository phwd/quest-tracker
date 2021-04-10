package org.chromium.net;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.text.Normalizer;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NetStringUtil {
    public static String convertToUnicode(ByteBuffer byteBuffer, String str) {
        try {
            return Charset.forName(str).newDecoder().decode(byteBuffer).toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String convertToUnicodeAndNormalize(ByteBuffer byteBuffer, String str) {
        String convertToUnicode = convertToUnicode(byteBuffer, str);
        if (convertToUnicode == null) {
            return null;
        }
        return Normalizer.normalize(convertToUnicode, Normalizer.Form.NFC);
    }

    public static String convertToUnicodeWithSubstitutions(ByteBuffer byteBuffer, String str) {
        try {
            CharsetDecoder newDecoder = Charset.forName(str).newDecoder();
            newDecoder.onMalformedInput(CodingErrorAction.REPLACE);
            newDecoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            newDecoder.replaceWith("�");
            return newDecoder.decode(byteBuffer).toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String toUpperCase(String str) {
        try {
            return str.toUpperCase(Locale.getDefault());
        } catch (Exception unused) {
            return null;
        }
    }
}
