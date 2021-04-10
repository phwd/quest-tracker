package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.SignatureException;
import java.util.List;
import javax.net.ssl.SSLException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Y6  reason: invalid class name */
public final class AnonymousClass0Y6 {
    public final AnonymousClass0Y8 A00;

    public final byte[] A00(Socket socket) {
        List<byte[]> list;
        try {
            byte[] A08 = ((AnonymousClass1vC) socket).A08(new byte[0]);
            AnonymousClass0Y8 r7 = this.A00;
            byte[] A02 = r7.A02();
            int length = A02.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(3 + length);
            byteArrayOutputStream.write(r7.A01.getEncoded());
            byteArrayOutputStream.write(new byte[]{(byte) (length >> 8), (byte) length});
            byteArrayOutputStream.write(A02);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byte[] A03 = r7.A03(A08);
            int i = 2;
            int length2 = A03.length;
            int i2 = 0;
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(byteArray.length + 1 + 2 + length2 + 2 + 0);
            byteArrayOutputStream2.write(r7.A00.getEncoded());
            byteArrayOutputStream2.write(byteArray);
            byteArrayOutputStream2.write(new byte[]{(byte) (length2 >> 8), (byte) length2});
            byteArrayOutputStream2.write(A03);
            byteArrayOutputStream2.write(0);
            byteArrayOutputStream2.write(0);
            AnonymousClass0YA r4 = new AnonymousClass0YA(byteArrayOutputStream2.toByteArray());
            int i3 = 0;
            while (true) {
                list = r4.A00;
                if (i3 >= list.size()) {
                    break;
                }
                i += list.get(i3).length;
                i2 += list.get(i3).length;
                i3++;
            }
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream(i);
            byteArrayOutputStream3.write(new byte[]{(byte) (i2 >> 8), (byte) (i2 >> 0)});
            for (int i4 = 0; i4 < list.size(); i4++) {
                byteArrayOutputStream3.write(list.get(i4));
            }
            return byteArrayOutputStream3.toByteArray();
        } catch (SSLException e) {
            AnonymousClass0NO.A0H("TokenBinderAuthenticator", e, "exception/exportingKeyingMaterial");
            return new byte[0];
        } catch (SignatureException e2) {
            AnonymousClass0NO.A0H("TokenBinderAuthenticator", e2, "exception/tokenBindingSignature");
            return new byte[0];
        } catch (AnonymousClass0Y1 e3) {
            AnonymousClass0NO.A0H("TokenBinderAuthenticator", e3, "exception/noKeyPairGenerated");
            return new byte[0];
        } catch (IOException e4) {
            AnonymousClass0NO.A0H("TokenBinderAuthenticator", e4, "exception/tokenBindingSerialization");
            return new byte[0];
        }
    }

    public AnonymousClass0Y6(AnonymousClass0Y9 r3) {
        String str;
        switch (r3.ordinal()) {
            case 2:
                this.A00 = new C06170mc();
                return;
            case 3:
                str = "Not yet implemented";
                break;
            default:
                str = "Unable to initialize";
                break;
        }
        throw new RuntimeException(str);
    }

    public AnonymousClass0Y6(String str, String str2, AnonymousClass0Y9 r6) {
        String str3;
        switch (r6.ordinal()) {
            case 2:
                this.A00 = new C06170mc(C01910Xz.A02(str), C01910Xz.A02(str2));
                return;
            case 3:
                str3 = "Not yet implemented";
                break;
            default:
                str3 = "Unable to initialize";
                break;
        }
        throw new RuntimeException(str3);
    }
}
