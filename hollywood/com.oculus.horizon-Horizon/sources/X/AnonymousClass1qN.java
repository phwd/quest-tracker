package X;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1qN  reason: invalid class name */
public abstract class AnonymousClass1qN extends AnonymousClass1rX<AnonymousClass1qQ, AnonymousClass1qa<AnonymousClass1q1>> {
    @GuardedBy("this")
    public boolean A00 = false;
    public final AnonymousClass1r8 A01;
    public final AnonymousClass1qU A02;
    public final C09971qh A03;
    public final AnonymousClass1qE A04;
    public final /* synthetic */ AnonymousClass1q3 A05;

    public static void A01(AnonymousClass1qN r2, boolean z) {
        synchronized (r2) {
            if (z) {
                if (!r2.A00) {
                    ((AnonymousClass1rX) r2).A00.A05(1.0f);
                    r2.A00 = true;
                    r2.A03.A02();
                }
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (Lcom/facebook/imagepipeline/producers/Consumer<LX/1qa<LX/1q1;>;>;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1qN(AnonymousClass1q3 r5, AnonymousClass1qD r6, AnonymousClass1qU r7, int i) {
        super(r6);
        this.A05 = r5;
        this.A02 = r7;
        this.A04 = r7.A05;
        AnonymousClass1r8 r1 = r7.A07.A05;
        this.A01 = r1;
        this.A03 = new C09971qh(r5.A03, new AnonymousClass1qM(this, r5, r7, i), r1.A01);
        r7.A04(new AnonymousClass1s8(this, r5));
    }

    /* JADX WARN: Incorrect types in method signature: (LX/1q1;JLcom/facebook/imagepipeline/image/QualityInfo;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>; */
    @Nullable
    public static Map A00(@Nullable AnonymousClass1qN r14, AnonymousClass1q1 r15, long j, AnonymousClass1tA r18, boolean z, String str, String str2, String str3) {
        HashMap hashMap;
        if (!r14.A04.A8K(r14.A02, "DecodeProducer")) {
            return null;
        }
        String valueOf = String.valueOf(j);
        String valueOf2 = String.valueOf(r18.A02);
        String valueOf3 = String.valueOf(z);
        if (r15 instanceof AnonymousClass1qH) {
            Bitmap bitmap = ((AnonymousClass1qH) ((AnonymousClass1t1) r15)).A04;
            StringBuilder sb = new StringBuilder();
            sb.append(bitmap.getWidth());
            sb.append("x");
            sb.append(bitmap.getHeight());
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
            sb2.append(bitmap.getByteCount());
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
        return new AnonymousClass0KP(hashMap);
    }

    public boolean A08(AnonymousClass1qQ r2, int i) {
        return this.A03.A04(r2, i);
    }

    @Override // X.AnonymousClass1qD, X.AnonymousClass1rX
    public final void A04(float f) {
        super.A04(f * 0.99f);
    }
}
