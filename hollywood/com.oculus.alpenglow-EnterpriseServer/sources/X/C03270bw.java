package X;

import android.content.Context;
import com.facebook.systrace.Systrace;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: X.0bw  reason: invalid class name and case insensitive filesystem */
public class C03270bw extends AnonymousClass0J9 {
    @Override // X.AnonymousClass0J9
    public final void A01(boolean z) throws IOException {
        if (!z) {
            File file = this.A00;
            AnonymousClass0Q1.A00(file);
            Systrace.A01(2147483648L, "AppUnpacker.fsync");
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    randomAccessFile.getFD().sync();
                    randomAccessFile.close();
                    return;
                } catch (Throwable unused) {
                }
            } finally {
                Systrace.A00(2147483648L);
            }
        } else {
            return;
        }
        throw th;
    }

    @Override // X.AnonymousClass0J9
    public final boolean A02(Context context, byte[] bArr) {
        File file = this.A00;
        AnonymousClass0Q1.A00(file);
        return !file.exists();
    }

    public C03270bw(String str, String str2) {
        super(str, str2);
    }
}
