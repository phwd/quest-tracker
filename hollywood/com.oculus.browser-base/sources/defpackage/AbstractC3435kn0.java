package defpackage;

import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: kn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3435kn0 {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f10304a = {"", "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"};

    public static NdefRecord a(byte[] bArr, String str, boolean z) {
        byte[] bArr2;
        byte b;
        String uri = Uri.parse(new String(bArr, "UTF-8")).normalizeScheme().toString();
        if (uri.length() != 0) {
            if (str == null) {
                bArr2 = null;
            } else {
                bArr2 = AbstractC3153j7.b(str);
            }
            if (z) {
                return new NdefRecord(3, AbstractC3153j7.b(uri), bArr2, null);
            }
            int i = 1;
            while (true) {
                String[] strArr = f10304a;
                if (i >= strArr.length) {
                    b = 0;
                    break;
                } else if (uri.startsWith(strArr[i])) {
                    b = (byte) i;
                    uri = uri.substring(strArr[i].length());
                    break;
                } else {
                    i++;
                }
            }
            byte[] b2 = AbstractC3153j7.b(uri);
            byte[] bArr3 = new byte[(b2.length + 1)];
            bArr3[0] = b;
            System.arraycopy(b2, 0, bArr3, 1, b2.length);
            return new NdefRecord(1, NdefRecord.RTD_URI, bArr2, bArr3);
        }
        throw new IllegalArgumentException("uri is empty");
    }

    public static C3606ln0 b(Uri uri, boolean z) {
        if (uri == null) {
            return null;
        }
        C3606ln0 ln0 = new C3606ln0();
        ln0.d = 0;
        if (z) {
            ln0.e = "absolute-url";
        } else {
            ln0.e = "url";
        }
        ln0.j = AbstractC3153j7.b(uri.toString());
        return ln0;
    }

    public static C3264jn0 c(byte[] bArr) {
        try {
            return e(new NdefMessage(bArr));
        } catch (FormatException | UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static boolean d(String str) {
        if (!Charset.forName("US-ASCII").newEncoder().canEncode(str) || str.length() < 2 || str.length() > 256 || str.charAt(0) != ':') {
            return false;
        }
        if (Character.isLowerCase(str.charAt(1)) || Character.isDigit(str.charAt(1))) {
            return true;
        }
        return false;
    }

    public static C3264jn0 e(NdefMessage ndefMessage) {
        C3606ln0 ln0;
        NdefRecord[] records = ndefMessage.getRecords();
        C3264jn0 jn0 = new C3264jn0();
        ArrayList arrayList = new ArrayList();
        for (NdefRecord ndefRecord : records) {
            short tnf = ndefRecord.getTnf();
            C3606ln0 ln02 = null;
            if (tnf != 0) {
                if (tnf != 1) {
                    if (tnf == 2) {
                        String str = new String(ndefRecord.getType(), "UTF-8");
                        byte[] payload = ndefRecord.getPayload();
                        ln0 = new C3606ln0();
                        ln0.d = 0;
                        ln0.e = "mime";
                        ln0.f = str;
                        ln0.j = payload;
                    } else if (tnf == 3) {
                        ln02 = b(ndefRecord.toUri(), true);
                    } else if (tnf == 4) {
                        String str2 = new String(ndefRecord.getType(), "UTF-8");
                        byte[] payload2 = ndefRecord.getPayload();
                        ln0 = new C3606ln0();
                        ln0.d = 1;
                        ln0.e = str2;
                        ln0.j = payload2;
                        ln0.k = c(payload2);
                    } else if (tnf == 5) {
                        byte[] payload3 = ndefRecord.getPayload();
                        ln02 = new C3606ln0();
                        ln02.d = 0;
                        ln02.e = "unknown";
                        ln02.j = payload3;
                    }
                } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_URI)) {
                    ln02 = b(ndefRecord.toUri(), false);
                } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    byte[] payload4 = ndefRecord.getPayload();
                    if (payload4.length != 0) {
                        ln0 = new C3606ln0();
                        ln0.d = 0;
                        ln0.e = "text";
                        ln0.h = (payload4[0] & 128) == 0 ? "utf-8" : "utf-16";
                        int i = payload4[0] & 63;
                        ln0.i = new String(payload4, 1, i, "US-ASCII");
                        int i2 = i + 1;
                        if (i2 <= payload4.length) {
                            ln0.j = Arrays.copyOfRange(payload4, i2, payload4.length);
                        }
                    }
                } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_SMART_POSTER)) {
                    byte[] payload5 = ndefRecord.getPayload();
                    ln02 = new C3606ln0();
                    ln02.d = 0;
                    ln02.e = "smart-poster";
                    ln02.j = payload5;
                    ln02.k = c(payload5);
                } else {
                    String str3 = ':' + new String(ndefRecord.getType(), "UTF-8");
                    if (d(str3)) {
                        byte[] payload6 = ndefRecord.getPayload();
                        C3606ln0 ln03 = new C3606ln0();
                        ln03.d = 2;
                        ln03.e = str3;
                        ln03.j = payload6;
                        ln03.k = c(payload6);
                        ln02 = ln03;
                    }
                }
                ln02 = ln0;
            } else {
                ln02 = new C3606ln0();
                ln02.d = 0;
                ln02.e = "empty";
                ln02.j = new byte[0];
            }
            if (!(ln02 == null || ndefRecord.getTnf() == 0)) {
                ln02.g = new String(ndefRecord.getId(), "UTF-8");
            }
            if (ln02 != null) {
                arrayList.add(ln02);
            }
        }
        C3606ln0[] ln0Arr = new C3606ln0[arrayList.size()];
        jn0.d = ln0Arr;
        arrayList.toArray(ln0Arr);
        return jn0;
    }

    public static NdefMessage f(C3264jn0 jn0) {
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                C3606ln0[] ln0Arr = jn0.d;
                if (i < ln0Arr.length) {
                    arrayList.add(g(ln0Arr[i]));
                    i++;
                } else {
                    NdefRecord[] ndefRecordArr = new NdefRecord[arrayList.size()];
                    arrayList.toArray(ndefRecordArr);
                    return new NdefMessage(ndefRecordArr);
                }
            }
        } catch (I30 | UnsupportedEncodingException | IllegalArgumentException unused) {
            throw new I30();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01b9, code lost:
        throw new defpackage.I30();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.nfc.NdefRecord g(defpackage.C3606ln0 r14) {
        /*
        // Method dump skipped, instructions count: 774
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC3435kn0.g(ln0):android.nfc.NdefRecord");
    }
}
