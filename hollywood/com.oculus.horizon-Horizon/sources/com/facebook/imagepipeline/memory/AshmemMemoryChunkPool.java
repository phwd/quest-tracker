package com.facebook.imagepipeline.memory;

import X.AbstractC10131rK;
import X.AbstractC10321rv;
import X.AbstractC10671uj;
import X.AbstractC10691uo;
import X.C10001qn;
import X.C10471su;
import android.annotation.TargetApi;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@TargetApi(27)
@Nullsafe(Nullsafe.Mode.STRICT)
public class AshmemMemoryChunkPool extends AbstractC10131rK {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1rI
    public final /* bridge */ /* synthetic */ AbstractC10321rv A04(int i) {
        return new C10001qn(i);
    }

    @Override // X.AbstractC10131rK
    public final /* bridge */ /* synthetic */ AbstractC10321rv A07(int i) {
        return new C10001qn(i);
    }

    @DoNotStrip
    public AshmemMemoryChunkPool(AbstractC10671uj r1, C10471su r2, AbstractC10691uo r3) {
        super(r1, r2, r3);
    }
}
