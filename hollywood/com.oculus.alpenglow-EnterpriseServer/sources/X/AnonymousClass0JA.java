package X;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* renamed from: X.0JA  reason: invalid class name */
public final class AnonymousClass0JA {
    public static final AnonymousClass0Je A06 = new AnonymousClass0Je();
    public static final Executor A07 = new AnonymousClass0J5();
    @Nullable
    public AnonymousClass0KQ A00 = null;
    public final Context A01;
    public final File A02;
    public final String A03;
    public final Executor A04;
    public final AnonymousClass0J9[] A05;

    public static void A00(AnonymousClass0JA r5) {
        try {
            for (AnonymousClass0J9 r1 : r5.A05) {
                r1.A01(false);
            }
            if (!new File(r5.A02, ".unpacked").createNewFile()) {
                throw new IOException("Could not create .unpacked file");
            }
        } catch (IOException unused) {
        }
    }

    public static void A01(AnonymousClass0JA r2) throws IOException {
        AnonymousClass0KQ r0 = r2.A00;
        AnonymousClass0Q1.A00(r0);
        r0.close();
        r2.A00 = null;
        A06.A01(r2.A03);
    }

    public static byte[] A03(InputStream inputStream, byte[] bArr, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, 0, Math.min(i - i2, bArr.length));
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            i2 += read;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public AnonymousClass0JA(AnonymousClass0J7 r3) {
        Context context = r3.A00;
        AnonymousClass0Q1.A00(context);
        this.A01 = context;
        File file = r3.A01;
        AnonymousClass0Q1.A00(file);
        this.A02 = file;
        ArrayList<AnonymousClass0J9> arrayList = r3.A04;
        this.A05 = (AnonymousClass0J9[]) arrayList.toArray(new AnonymousClass0J9[arrayList.size()]);
        this.A04 = r3.A03;
        String str = r3.A02;
        AnonymousClass0Q1.A00(str);
        this.A03 = str;
    }

    public static void A02(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    A02(file2);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }
}
