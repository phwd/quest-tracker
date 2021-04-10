package defpackage;

import android.util.Pair;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

/* renamed from: Ub1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1224Ub1 {
    public static File a(File file, int i, boolean z) {
        return new File(file, b(i, z));
    }

    public static String b(int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "cryptonito" : "tab");
        sb.append(i);
        return sb.toString();
    }

    public static Pair c(String str) {
        try {
            if (str.startsWith("cryptonito")) {
                return Pair.create(Integer.valueOf(Integer.parseInt(str.substring(10))), Boolean.TRUE);
            }
            if (str.startsWith("tab")) {
                return Pair.create(Integer.valueOf(Integer.parseInt(str.substring(3))), Boolean.FALSE);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(21:0|(1:5)(1:4)|(1:7)|(4:9|10|11|(2:13|14))|15|(1:17)(2:18|(1:20))|21|(3:22|23|(1:25))|28|29|32|33|34|35|38|(2:39|40)|(3:43|44|(1:48))|51|52|55|56) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ef, code lost:
        defpackage.AbstractC1220Ua0.f("TabState", "Failed to read shouldPreserve flag from tab state. Assuming shouldPreserve is false", new java.lang.Object[0]);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00eb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.C0797Nb1 d(java.io.FileInputStream r17, boolean r18) {
        /*
        // Method dump skipped, instructions count: 328
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1224Ub1.d(java.io.FileInputStream, boolean):Nb1");
    }

    public static C0797Nb1 e(File file, int i) {
        boolean z;
        Throwable th;
        FileInputStream fileInputStream;
        IOException e;
        File a2 = a(file, i, false);
        if (!a2.exists()) {
            a2 = a(file, i, true);
            z = true;
        } else {
            z = false;
        }
        FileInputStream fileInputStream2 = null;
        r2 = null;
        C0797Nb1 nb1 = null;
        if (!a2.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(a2);
            try {
                nb1 = d(fileInputStream, z);
            } catch (FileNotFoundException unused) {
            } catch (IOException e2) {
                e = e2;
                AbstractC1220Ua0.a("TabState", "Failed to restore tab state.", e);
                O21.a(fileInputStream);
                return nb1;
            }
        } catch (FileNotFoundException unused2) {
            fileInputStream = null;
            AbstractC1220Ua0.a("TabState", "Failed to restore tab state for tab: " + a2, new Object[0]);
            O21.a(fileInputStream);
            return nb1;
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
            AbstractC1220Ua0.a("TabState", "Failed to restore tab state.", e);
            O21.a(fileInputStream);
            return nb1;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            O21.a(fileInputStream2);
            throw th;
        }
        O21.a(fileInputStream);
        return nb1;
    }

    public static void f(File file, C0797Nb1 nb1, boolean z) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Ax1 ax1 = nb1.f8557a;
        if (ax1 != null) {
            ByteBuffer byteBuffer = ax1.f7707a;
            int limit = byteBuffer.limit();
            byte[] bArr = new byte[limit];
            byteBuffer.rewind();
            byteBuffer.get(bArr);
            DataOutputStream dataOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                if (z) {
                    try {
                        Cipher a2 = AbstractC5161uu.f11443a.a(1);
                        if (a2 != null) {
                            dataOutputStream = new DataOutputStream(new BufferedOutputStream(new CipherOutputStream(fileOutputStream, a2)));
                        }
                    } catch (FileNotFoundException unused) {
                        AbstractC1220Ua0.f("TabState", "FileNotFoundException while attempting to save TabState.", new Object[0]);
                        O21.a(dataOutputStream);
                        O21.a(fileOutputStream);
                    } catch (IOException unused2) {
                        AbstractC1220Ua0.f("TabState", "IOException while attempting to save TabState.", new Object[0]);
                        O21.a(dataOutputStream);
                        O21.a(fileOutputStream);
                    }
                    O21.a(dataOutputStream);
                    O21.a(fileOutputStream);
                }
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                if (z) {
                    dataOutputStream.writeLong(0);
                }
                dataOutputStream.writeLong(nb1.d);
                dataOutputStream.writeInt(limit);
                dataOutputStream.write(bArr);
                dataOutputStream.writeInt(nb1.b);
                String str = nb1.e;
                if (str == null) {
                    str = "";
                }
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeInt(nb1.f8557a.b);
                dataOutputStream.writeLong(-1);
                dataOutputStream.writeBoolean(false);
                dataOutputStream.writeInt(nb1.f);
                Integer num = nb1.g;
                dataOutputStream.writeInt(num != null ? num.intValue() : -1);
                dataOutputStream.writeInt(nb1.c);
            } catch (FileNotFoundException unused3) {
                fileOutputStream = null;
                AbstractC1220Ua0.f("TabState", "FileNotFoundException while attempting to save TabState.", new Object[0]);
                O21.a(dataOutputStream);
                O21.a(fileOutputStream);
            } catch (IOException unused4) {
                fileOutputStream = null;
                AbstractC1220Ua0.f("TabState", "IOException while attempting to save TabState.", new Object[0]);
                O21.a(dataOutputStream);
                O21.a(fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                O21.a(null);
                O21.a(fileOutputStream);
                throw th;
            }
            O21.a(dataOutputStream);
            O21.a(fileOutputStream);
        }
    }
}
