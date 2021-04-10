package com.facebook.imagepipeline.memory;

import X.AbstractC09461i1;
import X.AnonymousClass0JS;
import X.AnonymousClass1i0;
import X.AnonymousClass1i3;
import X.AnonymousClass1iY;
import X.C09501ia;
import android.annotation.TargetApi;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@TargetApi(27)
@Nullsafe(Nullsafe.Mode.STRICT)
public class AshmemMemoryChunkPool extends AbstractC09461i1 {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC09401hs
    public final /* bridge */ /* synthetic */ AnonymousClass1iY A04(int i) {
        return new C09501ia(i);
    }

    @Override // X.AbstractC09461i1
    public final /* bridge */ /* synthetic */ AnonymousClass1iY A07(int i) {
        return new C09501ia(i);
    }

    @DoNotStrip
    public AshmemMemoryChunkPool(AnonymousClass0JS r1, AnonymousClass1i0 r2, AnonymousClass1i3 r3) {
        super(r1, r2, r3);
    }
}
