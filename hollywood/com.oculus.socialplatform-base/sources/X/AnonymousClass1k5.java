package X;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1k5  reason: invalid class name */
public final class AnonymousClass1k5 {
    public ContentResolver A00;
    public AssetManager A01;
    public Resources A02;
    public final int A03 = 2048;
    public final AnonymousClass0VT A04;
    public final AnonymousClass0JW A05;
    public final AnonymousClass0P2 A06;
    public final AnonymousClass0P7<AnonymousClass0H3> A07 = new AnonymousClass0P7<>();
    public final AnonymousClass0P7<AnonymousClass0H3> A08 = new AnonymousClass0P7<>();
    public final C09901kJ A09;
    public final C09901kJ A0A;
    public final AnonymousClass1l8 A0B;
    public final AbstractC03450mg<AnonymousClass0H3, AnonymousClass0VM> A0C;
    public final AbstractC03450mg<AnonymousClass0H3, AnonymousClass0JV> A0D;
    public final AnonymousClass1Wf A0E;
    public final C03440md A0F;
    public final AnonymousClass0PW A0G;
    public final AnonymousClass1k2 A0H;
    public final boolean A0I;

    public final C09751jg A00(AnonymousClass1j8<AnonymousClass0PZ> r7, boolean z, AbstractC01080Pu r9) {
        return new C09751jg(this.A0F.A00, this.A05, r7, z, r9);
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/0VT;LX/0PW;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZZZLcom/facebook/imagepipeline/core/ExecutorSupplier;LX/0JW;LX/0mg<LX/0H3;LX/0VM;>;LX/0mg<LX/0H3;LX/0JV;>;LX/1kJ;LX/1kJ;Lcom/facebook/imagepipeline/cache/CacheKeyFactory;LX/0P2;IIZILX/1Wf;ZI)V */
    public AnonymousClass1k5(Context context, AnonymousClass0VT r4, AnonymousClass0PW r5, AnonymousClass1k2 r6, boolean z, C03440md r8, AnonymousClass0JW r9, AbstractC03450mg r10, AbstractC03450mg r11, C09901kJ r12, C09901kJ r13, AnonymousClass1l8 r14, AnonymousClass0P2 r15, AnonymousClass1Wf r16) {
        this.A00 = context.getApplicationContext().getContentResolver();
        this.A02 = context.getApplicationContext().getResources();
        this.A01 = context.getApplicationContext().getAssets();
        this.A04 = r4;
        this.A0G = r5;
        this.A0H = r6;
        this.A0I = z;
        this.A0F = r8;
        this.A05 = r9;
        this.A0C = r10;
        this.A0D = r11;
        this.A09 = r12;
        this.A0A = r13;
        this.A0B = r14;
        this.A06 = r15;
        this.A0E = r16;
    }
}
