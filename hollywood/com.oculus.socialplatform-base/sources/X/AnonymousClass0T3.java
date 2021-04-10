package X;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0T3  reason: invalid class name */
public abstract class AnonymousClass0T3 extends AnonymousClass0jM {
    @Nullable
    public String A00;
    @Nullable
    public String[] A01;
    public final Map<String, Object> A02 = new HashMap();
    public final Context A03;

    public abstract AnonymousClass0lB A08() throws IOException;

    public static void A00(File file, byte b) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0090, code lost:
        if ((r29 & 2) == 0) goto L_0x02bb;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00f2 */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f2 A[SYNTHETIC, Splitter:B:51:0x00f2] */
    @Override // X.AnonymousClass0l1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(int r29) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 801
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0T3.A04(int):void");
    }

    @Override // X.AnonymousClass0jM, X.AnonymousClass0l1
    public final int A05(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        Object obj;
        int A07;
        Map<String, Object> map = this.A02;
        synchronized (map) {
            obj = map.get(str);
            if (obj == null) {
                obj = new Object();
                map.put(str, obj);
            }
        }
        synchronized (obj) {
            A07 = A07(str, i, super.A00, threadPolicy);
        }
        return A07;
    }

    public AnonymousClass0T3(Context context, String str) {
        super(new File(AnonymousClass006.A09(context.getApplicationInfo().dataDir, "/", str)), 1);
        this.A03 = context;
    }

    public byte[] A09() throws IOException {
        Parcel obtain = Parcel.obtain();
        AnonymousClass0lB A08 = A08();
        try {
            AnonymousClass0l7[] r3 = A08.A00().A00;
            obtain.writeByte((byte) 1);
            int length = r3.length;
            obtain.writeInt(length);
            for (int i = 0; i < length; i++) {
                obtain.writeString(r3[i].A01);
                obtain.writeString(r3[i].A00);
            }
            A08.close();
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable unused) {
        }
        throw th;
    }
}
