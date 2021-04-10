package X;

import android.preference.PreferenceManager;
import com.facebook.auth.viewercontext.ViewerContext;
import com.facebook.debug.tracer.Tracer;
import com.facebook.tigon.oktigon.OkTigonService;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import java.util.concurrent.TimeUnit;

public final class W4 {
    public static ViewerContext A00;
    public static C0515bC A01;
    public static C0892oE A02;
    public static GA A03;
    public static C0894oG A04;
    public static GE A05;
    public static OkTigonService A06;
    public static C0548bl A07;
    public static OkTigonServiceHolder A08;
    public static final long A09 = TimeUnit.MINUTES.toMillis(10);

    public static synchronized C0515bC A00() {
        C0515bC bCVar;
        synchronized (W4.class) {
            Tracer.A01("getMobileConfig");
            try {
                bCVar = A01;
                if (bCVar == null) {
                    C0892oE oEVar = A02;
                    if (oEVar == null) {
                        oEVar = new C0892oE();
                        A02 = oEVar;
                    }
                    bCVar = new C0515bC(oEVar);
                    bCVar.A02 = new C1238vs();
                    bCVar.A03 = BX.A00().getFilesDir();
                    boolean z = false;
                    if (bCVar.A02 == null) {
                        z = true;
                    }
                    bCVar.A04 = z;
                    C0139Dd.A0G("MobileConfigFactoryImpl", "Created MobileConfigFactoryImpl, isSessionless:%s", Boolean.valueOf(z));
                    if (bCVar.A01 == null) {
                        bCVar.A01 = new C0893oF();
                    }
                    A01 = bCVar;
                }
            } finally {
                Tracer.A00();
            }
        }
        return bCVar;
    }

    public static synchronized OkTigonServiceHolder A01(String str) {
        OkTigonServiceHolder okTigonServiceHolder;
        synchronized (W4.class) {
            okTigonServiceHolder = A08;
            if (okTigonServiceHolder == null) {
                OkTigonService okTigonService = A06;
                if (okTigonService == null) {
                    C0548bl blVar = A07;
                    if (blVar == null) {
                        blVar = A2.A00();
                        A07 = blVar;
                    }
                    okTigonService = new OkTigonService(blVar, str, YS.A00());
                    A06 = okTigonService;
                }
                okTigonServiceHolder = new OkTigonServiceHolder(okTigonService);
                A08 = okTigonServiceHolder;
            }
        }
        return okTigonServiceHolder;
    }

    public static void A02(String str, long j) {
        String string = PreferenceManager.getDefaultSharedPreferences(BX.A00()).getString(str, "default");
        if (!string.equals("default")) {
            GA ga = A03;
            boolean booleanValue = Boolean.valueOf(string).booleanValue();
            AbstractC0168Ft ft = ga.A00;
            if (ft != null) {
                ft.updateOverrideForParam(j, booleanValue);
                ga.A00 = ga.A02.getNewOverridesTable();
                C0515bC bCVar = ga.A01;
                if (bCVar != null) {
                    synchronized (bCVar) {
                        bCVar.A00 = bCVar.A05.getNewOverridesTableIfExists();
                        for (AbstractC0166Fr fr : bCVar.A07) {
                            if (fr != null && (fr instanceof AbstractC0890oC)) {
                                AbstractC0890oC oCVar = (AbstractC0890oC) fr;
                                AbstractC0168Ft ft2 = bCVar.A00;
                                synchronized (oCVar) {
                                    oCVar.A00 = ft2;
                                }
                            }
                        }
                        if (bCVar.A0E != null && (bCVar.A0E instanceof AbstractC0890oC)) {
                            AbstractC0890oC oCVar2 = (AbstractC0890oC) bCVar.A0E;
                            AbstractC0168Ft ft3 = bCVar.A00;
                            synchronized (oCVar2) {
                                oCVar2.A00 = ft3;
                            }
                        }
                    }
                }
            }
        }
    }
}
