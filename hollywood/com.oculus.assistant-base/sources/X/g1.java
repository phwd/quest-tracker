package X;

import com.google.common.base.Absent;
import java.util.concurrent.TimeUnit;

public final class g1 {
    public final /* synthetic */ C0740gP A00;

    public g1(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    public final void A00(FU fu) {
        C0740gP gPVar = this.A00;
        C0112Aq aq = gPVar.A0h;
        aq.A01(new i2());
        for (C0741gQ gQVar : gPVar.A10) {
            gQVar.A00.logLocalEvent("TTS", "Ready");
        }
        fu.A57();
        aq.A01(new i0(AnonymousClass09.A01));
        Absent absent = Absent.INSTANCE;
        hG hGVar = C00879r.A00;
        EnumC00909u r0 = EnumC00909u.RESPONSE_PREPARED;
        hGVar.A00.markerPoint(hGVar.A02, r0.getPointName(), C0785hJ.A00(absent), TimeUnit.MILLISECONDS);
    }
}
