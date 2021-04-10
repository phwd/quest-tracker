package X;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1pf  reason: invalid class name */
public final class AnonymousClass1pf {
    public ContentResolver A00;
    public AssetManager A01;
    public Resources A02;
    public final int A03 = 2048;
    public final AnonymousClass0Km A04;
    public final AbstractC10521tg A05;
    public final AnonymousClass1pw<AnonymousClass1kC> A06 = new AnonymousClass1pw<>();
    public final AnonymousClass1pw<AnonymousClass1kC> A07 = new AnonymousClass1pw<>();
    public final AnonymousClass1oQ A08;
    public final AnonymousClass1oQ A09;
    public final C10331rw A0A;
    public final AbstractC10301rt<AnonymousClass1kC, AnonymousClass1q1> A0B;
    public final AbstractC10301rt<AnonymousClass1kC, PooledByteBuffer> A0C;
    public final C09761ow A0D;
    public final AnonymousClass1o9 A0E;
    public final AnonymousClass1tN A0F;
    public final AnonymousClass1tj A0G;
    public final AnonymousClass1pV A0H;
    public final boolean A0I;

    public final C09901px A00(AnonymousClass1pP<AnonymousClass1qQ> r7, boolean z, AnonymousClass1lX r9) {
        return new C09901px(this.A0E.A00, this.A0H, r7, z, r9);
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/0Km;LX/1tN;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZZZLcom/facebook/imagepipeline/core/ExecutorSupplier;Lcom/facebook/common/memory/PooledByteBufferFactory;LX/1rt<LX/1kC;LX/1q1;>;LX/1rt<LX/1kC;Lcom/facebook/common/memory/PooledByteBuffer;>;LX/1oQ;LX/1oQ;Lcom/facebook/imagepipeline/cache/CacheKeyFactory;LX/1tg;IIZILX/1ow;ZI)V */
    public AnonymousClass1pf(Context context, AnonymousClass0Km r4, AnonymousClass1tN r5, AnonymousClass1tj r6, boolean z, AnonymousClass1o9 r8, AnonymousClass1pV r9, AbstractC10301rt r10, AbstractC10301rt r11, AnonymousClass1oQ r12, AnonymousClass1oQ r13, C10331rw r14, AbstractC10521tg r15, C09761ow r16) {
        this.A00 = context.getApplicationContext().getContentResolver();
        this.A02 = context.getApplicationContext().getResources();
        this.A01 = context.getApplicationContext().getAssets();
        this.A04 = r4;
        this.A0F = r5;
        this.A0G = r6;
        this.A0I = z;
        this.A0E = r8;
        this.A0H = r9;
        this.A0B = r10;
        this.A0C = r11;
        this.A08 = r12;
        this.A09 = r13;
        this.A0A = r14;
        this.A05 = r15;
        this.A0D = r16;
    }
}
