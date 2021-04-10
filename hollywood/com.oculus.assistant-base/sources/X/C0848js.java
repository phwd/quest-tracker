package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.oculus.aidl.OVRServiceInterface;
import java.io.Writer;
import java.util.ArrayList;

/* renamed from: X.js  reason: case insensitive filesystem */
public final class C0848js {
    public static C0848js A00;

    public static void A00(Writer writer, char c) {
        String str;
        if (c == '\f') {
            writer.write(92);
            c = 'f';
        } else if (c == '\r') {
            writer.write(92);
            c = 'r';
        } else if (c == '\"' || c == '\\') {
            writer.write(92);
        } else {
            switch (c) {
                case '\b':
                    writer.write(92);
                    c = 'b';
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    writer.write(92);
                    c = 't';
                    break;
                case '\n':
                    writer.write(92);
                    str = "n";
                    writer.write(str);
                    return;
                default:
                    if (c <= 31 || c == 8232 || c == 8233) {
                        str = String.format("\\u%04x", Integer.valueOf(c));
                        writer.write(str);
                        return;
                    }
            }
        }
        writer.write(c);
    }

    private void A01(Writer writer, String str, Object obj) {
        String str2;
        String str3;
        int byteValue;
        if (obj == null) {
            str3 = "null";
        } else if (obj instanceof String) {
            String str4 = (String) obj;
            writer.write(34);
            int length = str4.length();
            for (int i = 0; i < length; i++) {
                A00(writer, str4.charAt(i));
            }
            writer.write(34);
            return;
        } else if (obj instanceof Number) {
            Number number = (Number) obj;
            StringBuilder sb = ((DP) DP.A01.get()).A00;
            sb.delete(0, sb.length());
            if (number instanceof Float) {
                sb.append(number.floatValue());
            } else if (number instanceof Double) {
                sb.append(number.doubleValue());
            } else {
                if (number instanceof Integer) {
                    byteValue = number.intValue();
                } else if (number instanceof Long) {
                    sb.append(number.longValue());
                } else if (number instanceof Short) {
                    byteValue = number.shortValue();
                } else if (number instanceof Byte) {
                    byteValue = number.byteValue();
                } else {
                    StringBuilder sb2 = new StringBuilder("Type ");
                    sb2.append(number.getClass());
                    sb2.append(" not supported");
                    throw new UnsupportedOperationException(sb2.toString());
                }
                sb.append(byteValue);
            }
            int length2 = sb.length();
            for (int i2 = 0; i2 < length2; i2++) {
                writer.write(sb.charAt(i2));
            }
            return;
        } else if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                str3 = "true";
            } else {
                str3 = "false";
            }
        } else if (obj instanceof DQ) {
            DQ dq = (DQ) obj;
            C0848js jsVar = this;
            C0848js jsVar2 = dq.A02;
            if (jsVar2 != null) {
                jsVar = jsVar2;
            }
            Class<?> cls = jsVar.getClass();
            if (cls.equals(C0848js.class)) {
                C0848js jsVar3 = this;
                C0848js jsVar4 = dq.A02;
                if (jsVar4 != null) {
                    jsVar3 = jsVar4;
                }
                jsVar3.A02(writer, dq);
                return;
            }
            StringBuilder sb3 = new StringBuilder("Unsupported encoder=");
            sb3.append(cls);
            sb3.append(", flags=");
            sb3.append(0);
            sb3.append(" combination");
            throw new IllegalStateException(sb3.toString());
        } else {
            if (str != null) {
                str2 = AnonymousClass08.A05(" (found in key '", str, "')");
            } else {
                str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
            }
            throw new IllegalArgumentException(AnonymousClass08.A06("The type ", obj.getClass().toString(), " is not supported", str2));
        }
        writer.write(str3);
    }

    public final void A02(Writer writer, DQ dq) {
        int i;
        if (dq instanceof C0847jr) {
            C0847jr jrVar = (C0847jr) dq;
            writer.write(123);
            int i2 = jrVar.A00;
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 > 0) {
                    writer.write(44);
                } else if (i3 < 0) {
                    throw new ArrayIndexOutOfBoundsException(i3);
                }
                if (i3 < jrVar.A00) {
                    String str = (String) jrVar.A01.get(i3 << 1);
                    writer.write(34);
                    int length = str.length();
                    for (int i4 = 0; i4 < length; i4++) {
                        A00(writer, str.charAt(i4));
                    }
                    writer.write(34);
                    writer.write(58);
                    A01(writer, str, jrVar.A06(i3));
                } else {
                    throw new ArrayIndexOutOfBoundsException(i3);
                }
            }
            i = 125;
        } else {
            writer.write(91);
            ArrayList arrayList = ((C0846jq) dq).A00;
            int size = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                if (i5 > 0) {
                    writer.write(44);
                }
                A01(writer, null, arrayList.get(i5));
            }
            i = 93;
        }
        writer.write(i);
    }
}
