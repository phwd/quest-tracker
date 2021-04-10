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

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0052, code lost:
        r7 = r6;
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0061, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0062, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x006a, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x006b, code lost:
        if (r3 != null) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x006d, code lost:
        if (r7 != null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0072, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0073, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0074, code lost:
        r7.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0078, code lost:
        r3.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0061 A[ExcHandler: all (th java.lang.Throwable)] */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r12, android.os.CancellationSignal r13, @androidx.annotation.NonNull androidx.core.provider.FontsContractCompat.FontInfo[] r14, int r15) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
