package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.0pR  reason: invalid class name */
public final class AnonymousClass0pR {
    public static AnonymousClass0pR A00;

    public static synchronized AnonymousClass0pR A00() {
        AnonymousClass0pR r0;
        synchronized (AnonymousClass0pR.class) {
            r0 = A00;
            if (r0 == null) {
                r0 = new AnonymousClass0pR();
                A00 = r0;
            }
        }
        return r0;
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

    private void A02(Writer writer, @Nullable String str, @Nullable Object obj) throws IOException {
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
            StringBuilder sb = C01290Ms.A01.get().A00;
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
        } else if (obj instanceof AbstractC01300Mt) {
            AbstractC01300Mt r7 = (AbstractC01300Mt) obj;
            AnonymousClass0pR r2 = this;
            AnonymousClass0pR r0 = r7.A02;
            if (r0 != null) {
                r2 = r0;
            }
            Class<?> cls = r2.getClass();
            if (cls.equals(AnonymousClass0pR.class)) {
                AnonymousClass0pR r1 = this;
                AnonymousClass0pR r02 = r7.A02;
                if (r02 != null) {
                    r1 = r02;
                }
                r1.A03(writer, r7);
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
                str2 = AnonymousClass006.A07(" (found in key '", str, "')");
            } else {
                str2 = "";
            }
            throw new IllegalArgumentException(AnonymousClass006.A08("The type ", obj.getClass().toString(), " is not supported", str2));
        }
        writer.write(str3);
    }

    public final void A03(Writer writer, AbstractC01300Mt r10) throws IOException {
        int i;
        if (r10 instanceof AnonymousClass0pS) {
            AnonymousClass0pS r102 = (AnonymousClass0pS) r10;
            writer.write(123);
            int i2 = r102.A00;
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 > 0) {
                    writer.write(44);
                } else if (i3 < 0) {
                    throw new ArrayIndexOutOfBoundsException(i3);
                }
                if (i3 < r102.A00) {
                    ArrayList<Object> arrayList = r102.A01;
                    String str = (String) arrayList.get(i3 << 1);
                    writer.write(34);
                    int length = str.length();
                    for (int i4 = 0; i4 < length; i4++) {
                        A01(writer, str.charAt(i4));
                    }
                    writer.write(34);
                    writer.write(58);
                    if (i3 < r102.A00) {
                        A02(writer, str, arrayList.get((i3 << 1) + 1));
                    }
                }
                throw new ArrayIndexOutOfBoundsException(i3);
            }
            i = 125;
        } else {
            writer.write(91);
            ArrayList<Object> arrayList2 = ((AnonymousClass0pT) r10).A00;
            int size = arrayList2.size();
            for (int i5 = 0; i5 < size; i5++) {
                if (i5 > 0) {
                    writer.write(44);
                }
                A02(writer, null, arrayList2.get(i5));
            }
            i = 93;
        }
        writer.write(i);
    }
}
