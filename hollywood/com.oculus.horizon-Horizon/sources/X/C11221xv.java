package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import com.facebook.cameracore.muxing.MuxerWrapper;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xv  reason: invalid class name and case insensitive filesystem */
public final class C11221xv {
    public double A00 = 1.0d;
    public long A01;
    public long A02;
    public long A03;
    public long A04;
    @Nullable
    public ByteBuffer A05;
    public final List<MuxerWrapper> A06;

    public static boolean A00(C11221xv r6, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException, InterruptedException {
        boolean z;
        boolean z2 = true;
        for (AnonymousClass1y3 r4 : r6.A06) {
            if (!r4.A06) {
                CountDownLatch countDownLatch = r4.A00;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    r4.A00.await(2, TimeUnit.SECONDS);
                }
                AnonymousClass1y3.A00(r4);
                if (!r4.A06) {
                    z = false;
                    z2 &= z;
                }
            }
            r4.A01.AAJ(byteBuffer, bufferInfo);
            z = true;
            z2 &= z;
        }
        return z2;
    }

    public C11221xv(List<MuxerWrapper> list) {
        ArrayList arrayList = new ArrayList();
        this.A06 = arrayList;
        arrayList.addAll(list);
        this.A01 = -1;
        this.A03 = -1;
        this.A02 = -1;
        this.A04 = -1;
    }
}
