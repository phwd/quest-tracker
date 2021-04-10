package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public final class YD {
    public static YD A00;

    public static synchronized YD A00() {
        YD yd;
        synchronized (YD.class) {
            yd = A00;
            if (yd == null) {
                yd = new YD();
                A00 = yd;
            }
        }
        return yd;
    }

    public static void A01(Writer writer, char c) throws IOException {
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
                case '\t':
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

    private void A02(Writer writer, YF yf) throws IOException {
        ArrayList<Object> arrayList = yf.A00;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                writer.write(44);
            }
            A04(writer, null, arrayList.get(i));
        }
    }

    private void A03(Writer writer, YE ye) throws IOException {
        int i = ye.A00;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                writer.write(44);
            }
            String A0B = ye.A0B(i2);
            writer.write(34);
            int length = A0B.length();
            for (int i3 = 0; i3 < length; i3++) {
                A01(writer, A0B.charAt(i3));
            }
            writer.write(34);
            writer.write(58);
            A04(writer, A0B, ye.A0A(i2));
        }
    }

    private void A04(Writer writer, @Nullable String str, @Nullable Object obj) throws IOException {
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
                A01(writer, str4.charAt(i));
            }
            writer.write(34);
            return;
        } else if (obj instanceof Number) {
            Number number = (Number) obj;
            StringBuilder sb = ME.A01.get().A00;
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
        } else if (obj instanceof MF) {
            MF mf = (MF) obj;
            YD yd = this;
            YD yd2 = mf.A02;
            if (yd2 != null) {
                yd = yd2;
            }
            Class<?> cls = yd.getClass();
            if (cls.equals(YD.class)) {
                YD yd3 = this;
                if (writer != null) {
                    YD yd4 = mf.A02;
                    if (yd4 != null) {
                        yd3 = yd4;
                    }
                    yd3.A05(writer, mf);
                    return;
                }
                throw new AssertionError("Writer is null!");
            }
            StringBuilder sb3 = new StringBuilder("Unsupported encoder=");
            sb3.append(cls);
            sb3.append(", flags=");
            sb3.append(0);
            sb3.append(" combination");
            throw new IllegalStateException(sb3.toString());
        } else {
            if (str != null) {
                str2 = AnonymousClass06.A05(" (found in key '", str, "')");
            } else {
                str2 = "";
            }
            throw new IllegalArgumentException(AnonymousClass06.A06("The type ", obj.getClass().toString(), " is not supported", str2));
        }
        writer.write(str3);
    }

    public final void A05(Writer writer, MF mf) throws IOException {
        int i;
        if (mf instanceof YE) {
            writer.write(123);
            A03(writer, (YE) mf);
            i = 125;
        } else {
            writer.write(91);
            A02(writer, (YF) mf);
            i = 93;
        }
        writer.write(i);
    }

    public final void A06(Writer writer, MF mf) throws IOException {
        if (mf instanceof YE) {
            A03(writer, (YE) mf);
        } else {
            A02(writer, (YF) mf);
        }
    }
}
