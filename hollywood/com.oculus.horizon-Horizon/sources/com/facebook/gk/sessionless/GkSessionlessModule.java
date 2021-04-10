package com.facebook.gk.sessionless;

import X.AnonymousClass0J2;
import X.AnonymousClass0J5;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.AnonymousClass11r;
import X.AnonymousClass12I;
import X.AnonymousClass12L;
import android.content.Context;
import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.InjectorModule;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GkSessionlessModule extends AnonymousClass0J5 {
    public static final Object A00 = new Object();
    public static final Object A01 = new Object();
    public static final Object A02 = new Object();
    public static volatile AnonymousClass12I A03;
    public static volatile AnonymousClass12L A04;
    public static volatile AnonymousClass11r A05;

    @DoNotStrip
    public static class GkSessionlessModuleSelendroidInjector implements AnonymousClass0QB {
        public AnonymousClass0QC A00;

        @DoNotStrip
        public GatekeeperWriter getGatekeeperWriter() {
            return (GatekeeperWriter) AnonymousClass0J2.A04(497, this.A00);
        }

        @DoNotStrip
        public GkSessionlessModuleSelendroidInjector(Context context) {
            this.A00 = new AnonymousClass0QC(0, AnonymousClass0J2.get(context));
        }
    }
}
