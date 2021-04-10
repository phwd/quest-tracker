package com.oculus.common.socialtablet.config;

import X.AbstractC07951dj;
import X.AnonymousClass1hE;
import X.C07651cn;
import X.C07821dS;
import X.C08031dr;
import android.content.Context;
import com.bumptech.glide.annotation.GlideModule;

@GlideModule
public class SocialTabletGlideModule extends AbstractC07951dj {
    public static final int BITMAP_POOL_SIZE_BYTES = 0;
    public static final int DISK_CACHE_SIZE_BYTES = 209715200;
    public static final int MEMORY_CACHE_SIZE_BYTES = 0;

    @Override // X.AbstractC07951dj
    public void applyOptions(Context context, C07651cn r5) {
        r5.A05 = new C07821dS(0);
        r5.A02 = new AnonymousClass1hE(0);
        r5.A04 = new C08031dr(context, 209715200);
    }
}
