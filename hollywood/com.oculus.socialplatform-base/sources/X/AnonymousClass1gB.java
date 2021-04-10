package X;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/* renamed from: X.1gB  reason: invalid class name */
public final class AnonymousClass1gB implements AnonymousClass1dN<ByteBuffer, AnonymousClass1gA> {
    public static final C09231hJ A05 = new C09231hJ();
    public static final AnonymousClass1h6 A06 = new AnonymousClass1h6();
    public final Context A00;
    public final C09231hJ A01;
    public final AnonymousClass1h6 A02;
    public final AnonymousClass1hA A03;
    public final List<AbstractC08251eH> A04;

    public AnonymousClass1gB(Context context, List<AbstractC08251eH> list, AbstractC07941di r6, ArrayPool arrayPool) {
        AnonymousClass1h6 r2 = A06;
        C09231hJ r1 = A05;
        this.A00 = context.getApplicationContext();
        this.A04 = list;
        this.A01 = r1;
        this.A03 = new AnonymousClass1hA(r6, arrayPool);
        this.A02 = r2;
    }

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0290, code lost:
        if (android.util.Log.isLoggable("BufferGifDecoder", 2) != false) goto L_0x02c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02c5, code lost:
        if (android.util.Log.isLoggable("BufferGifDecoder", 2) != false) goto L_0x02c7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02cc A[SYNTHETIC] */
    @Override // X.AnonymousClass1dN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass1fR<X.AnonymousClass1gA> A2V(@androidx.annotation.NonNull java.nio.ByteBuffer r24, int r25, int r26, @androidx.annotation.NonNull X.AnonymousClass1cO r27) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 765
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1gB.A2V(java.lang.Object, int, int, X.1cO):X.1fR");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull ByteBuffer byteBuffer, @NonNull AnonymousClass1cO r5) throws IOException {
        ImageHeaderParser$ImageType A012;
        ByteBuffer byteBuffer2 = byteBuffer;
        if (!((Boolean) r5.A00(C09141gl.A01)).booleanValue()) {
            List<AbstractC08251eH> list = this.A04;
            if (byteBuffer2 == null) {
                A012 = ImageHeaderParser$ImageType.UNKNOWN;
            } else {
                A012 = C08201eC.A01(list, new C08261eI(byteBuffer2));
            }
            if (A012 != ImageHeaderParser$ImageType.GIF) {
                return false;
            }
            return true;
        }
        return false;
    }
}
