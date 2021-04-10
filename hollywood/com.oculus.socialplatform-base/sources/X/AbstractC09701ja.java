package X;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1ja  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09701ja extends AbstractC09791jm<AnonymousClass0PZ, AbstractC00820Ju<AnonymousClass0VM>> {
    @GuardedBy("this")
    public boolean A00 = false;
    public final AnonymousClass0PI A01;
    public final C10161kv A02;
    public final C10081km A03;
    public final AnonymousClass1l6 A04;
    public final /* synthetic */ AnonymousClass1je A05;

    public static void A01(AbstractC09701ja r2, boolean z) {
        synchronized (r2) {
            if (z) {
                if (!r2.A00) {
                    ((AbstractC09791jm) r2).A00.A06(1.0f);
                    r2.A00 = true;
                    r2.A03.A02();
                }
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Lcom/facebook/imagepipeline/producers/Consumer<LX/0Ju<LX/0VM;>;>;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractC09701ja(AnonymousClass1je r5, AbstractC10011kf r6, C10161kv r7, int i) {
        super(r6);
        this.A05 = r5;
        this.A02 = r7;
        this.A04 = r7.A05;
        AnonymousClass0PI r1 = r7.A07.A05;
        this.A01 = r1;
        this.A03 = new C10081km(r5.A03, new AnonymousClass1jZ(this, r5, r7, i), r1.A01);
        r7.A04(new C09801jn(this, r5));
    }

    /* JADX WARN: Incorrect types in method signature: (LX/0VM;JLcom/facebook/imagepipeline/image/QualityInfo;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>; */
    @Nullable
    public static Map A00(@Nullable AbstractC09701ja r14, AnonymousClass0VM r15, long j, C03410mW r18, boolean z, String str, String str2, String str3) {
        HashMap hashMap;
        if (!r14.A04.A9I(r14.A02, "DecodeProducer")) {
            return null;
        }
        String valueOf = String.valueOf(j);
        String valueOf2 = String.valueOf(r18.A02);
        String valueOf3 = String.valueOf(z);
        if (r15 instanceof C002305g) {
            Bitmap A042 = ((AnonymousClass0I0) r15).A04();
            StringBuilder sb = new StringBuilder();
            sb.append(A042.getWidth());
            sb.append("x");
            sb.append(A042.getHeight());
            String obj = sb.toString();
            hashMap = new HashMap(8);
            hashMap.put("bitmapSize", obj);
            hashMap.put("queueTime", valueOf);
            hashMap.put("hasGoodQuality", valueOf2);
            hashMap.put("isFinal", valueOf3);
            hashMap.put("encodedImageSize", str2);
            hashMap.put("imageFormat", str);
            hashMap.put("requestedImageSize", "unknown");
            hashMap.put("sampleSize", str3);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(A042.getByteCount());
            hashMap.put("byteCount", sb2.toString());
        } else {
            hashMap = new HashMap(7);
            hashMap.put("queueTime", valueOf);
            hashMap.put("hasGoodQuality", valueOf2);
            hashMap.put("isFinal", valueOf3);
            hashMap.put("encodedImageSize", str2);
            hashMap.put("imageFormat", str);
            hashMap.put("requestedImageSize", "unknown");
            hashMap.put("sampleSize", str3);
        }
        return new C00690Id(hashMap);
    }

    public boolean A09(AnonymousClass0PZ r2, int i) {
        return this.A03.A04(r2, i);
    }

    @Override // X.AbstractC09791jm, X.AbstractC10011kf
    public final void A05(float f) {
        super.A05(f * 0.99f);
    }
}
