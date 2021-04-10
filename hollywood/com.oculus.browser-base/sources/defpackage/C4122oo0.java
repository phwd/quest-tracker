package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;

/* renamed from: oo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4122oo0 {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[][] f10578a = {new byte[]{Byte.MIN_VALUE, 115, -64, 33, -64, 87, 89, 117, 98, 105, 75, 101, 121}, new byte[]{89, 117, 98, 105, 107, 101, 121, 78, 69, 79, 114, 51}};
    public static C4122oo0 b;
    public final List c = new ArrayList();

    public C4122oo0() {
        String MOVY9QtZ = N.MOVY9QtZ("WebNFCBlockList", "historical_bytes_additions");
        if (!(MOVY9QtZ == null || MOVY9QtZ.isEmpty())) {
            String[] split = MOVY9QtZ.split(",");
            for (String str : split) {
                int length = str.length();
                byte[] bArr = null;
                if (length % 2 == 1) {
                    AbstractC1220Ua0.f("NfcBlocklist", "Length of %s is odd", str);
                } else {
                    byte[] bArr2 = new byte[(length / 2)];
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            bArr = bArr2;
                            break;
                        }
                        char charAt = str.charAt(i);
                        char charAt2 = str.charAt(i + 1);
                        if (Character.digit(charAt, 16) < 0 || Character.digit(charAt2, 16) < 0) {
                            AbstractC1220Ua0.f("NfcBlocklist", "Invalid hex character found in %s", str);
                        } else {
                            bArr2[i / 2] = (byte) (Character.digit(charAt2, 16) + (Character.digit(charAt, 16) << 4));
                            i += 2;
                        }
                    }
                    AbstractC1220Ua0.f("NfcBlocklist", "Invalid hex character found in %s", str);
                }
                if (bArr != null) {
                    this.c.add(bArr);
                }
            }
        }
    }
}
