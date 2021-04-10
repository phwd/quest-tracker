package androidx.core.graphics;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.MotionEventCompat;
import java.io.File;

@RequiresApi(MotionEventCompat.AXIS_WHEEL)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return new File(path);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004e, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0051, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0052, code lost:
        if (r3 != null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0059, code lost:
        throw r4;
     */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r9, android.os.CancellationSignal r10, @androidx.annotation.NonNull androidx.core.provider.FontsContractCompat.FontInfo[] r11, int r12) {
        /*
            r8 = this;
            int r0 = r11.length
            r1 = 0
            r2 = 1
            if (r0 >= r2) goto L_0x0006
            return r1
        L_0x0006:
            androidx.core.provider.FontsContractCompat$FontInfo r0 = r8.findBestInfo(r11, r12)
            android.content.ContentResolver r2 = r9.getContentResolver()
            android.net.Uri r3 = r0.getUri()     // Catch:{ IOException -> 0x005a }
            java.lang.String r4 = "r"
            android.os.ParcelFileDescriptor r3 = r2.openFileDescriptor(r3, r4, r10)     // Catch:{ IOException -> 0x005a }
            java.io.File r4 = r8.getFile(r3)     // Catch:{ all -> 0x004f }
            if (r4 == 0) goto L_0x0032
            boolean r5 = r4.canRead()     // Catch:{ all -> 0x004f }
            if (r5 != 0) goto L_0x0028
            goto L_0x0032
        L_0x0028:
            android.graphics.Typeface r5 = android.graphics.Typeface.createFromFile(r4)     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x0031
            r3.close()
        L_0x0031:
            return r5
        L_0x0032:
            java.io.FileInputStream r5 = new java.io.FileInputStream
            java.io.FileDescriptor r6 = r3.getFileDescriptor()
            r5.<init>(r6)
            android.graphics.Typeface r6 = super.createFromInputStream(r9, r5)     // Catch:{ all -> 0x0046 }
            r5.close()
            r3.close()
            return r6
        L_0x0046:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r6 = move-exception
            r5.close()     // Catch:{ all -> 0x004d }
            goto L_0x004e
        L_0x004d:
            r7 = move-exception
        L_0x004e:
            throw r6
        L_0x004f:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r4 = move-exception
            if (r3 == 0) goto L_0x0059
            r3.close()     // Catch:{ all -> 0x0058 }
            goto L_0x0059
        L_0x0058:
            r5 = move-exception
        L_0x0059:
            throw r4
        L_0x005a:
            r3 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
