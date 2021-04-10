package com.facebook.gk.sessionless;

import X.AbstractC01750Lk;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import X.AnonymousClass13v;
import X.AnonymousClass15z;
import X.AnonymousClass160;
import X.C05670kV;
import android.content.Context;
import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.InjectorModule;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GkSessionlessModule extends AbstractC01750Lk {
    public static final Object A00 = new Object();
    public static final Object A01 = new Object();
    public static final Object A02 = new Object();
    public static volatile AnonymousClass160 A03;
    public static volatile AnonymousClass13v A04;
    public static volatile AnonymousClass15z A05;

    @DoNotStrip
    public static class GkSessionlessModuleSelendroidInjector implements AnonymousClass0R6 {
        public AnonymousClass0R7 A00;

        @DoNotStrip
        public GatekeeperWriter getGatekeeperWriter() {
            AnonymousClass0R7 r0 = this.A00;
            if (r0 != null) {
                return (GatekeeperWriter) AnonymousClass0Lh.A04(r0.A01);
            }
            throw new C05670kV("A local injection was attempted before the constructor completed or before injectMe was called.");
        }

        @DoNotStrip
        public GkSessionlessModuleSelendroidInjector(Context context) {
            this.A00 = new AnonymousClass0R7(0, AnonymousClass0Lh.get(context));
        }
    }
}
