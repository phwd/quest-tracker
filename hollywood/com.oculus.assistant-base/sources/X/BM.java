package X;

import com.facebook.assistant.oacr.config.OacrTrimSpec;

public final class BM {
    public static final OacrTrimSpec A00;
    public static final OacrTrimSpec A01;
    public static final OacrTrimSpec A02;

    static {
        C0812iD iDVar = new C0812iD();
        iDVar.A02(0, "passthrough");
        iDVar.A02(1, false);
        iDVar.A02(2, false);
        iDVar.A02(3, false);
        iDVar.A02(4, "passthrough");
        iDVar.A02(5, false);
        iDVar.A02(6, false);
        Object[] A03 = iDVar.A03();
        OacrTrimSpec oacrTrimSpec = new OacrTrimSpec();
        oacrTrimSpec.A02("com.facebook.assistant.oacr.config.OacrTrimSpec", A03);
        A00 = oacrTrimSpec;
        C0812iD iDVar2 = new C0812iD();
        iDVar2.A02(0, "stella");
        iDVar2.A02(1, true);
        iDVar2.A02(2, true);
        iDVar2.A02(3, true);
        iDVar2.A02(4, "ondevice");
        iDVar2.A02(5, true);
        iDVar2.A02(6, true);
        Object[] A032 = iDVar2.A03();
        OacrTrimSpec oacrTrimSpec2 = new OacrTrimSpec();
        oacrTrimSpec2.A02("com.facebook.assistant.oacr.config.OacrTrimSpec", A032);
        A02 = oacrTrimSpec2;
        C0812iD iDVar3 = new C0812iD();
        iDVar3.A02(0, "portal");
        iDVar3.A02(1, true);
        iDVar3.A02(2, true);
        iDVar3.A02(3, true);
        iDVar3.A02(4, "ondevice");
        iDVar3.A02(5, true);
        iDVar3.A02(6, true);
        Object[] A033 = iDVar3.A03();
        OacrTrimSpec oacrTrimSpec3 = new OacrTrimSpec();
        oacrTrimSpec3.A02("com.facebook.assistant.oacr.config.OacrTrimSpec", A033);
        A01 = oacrTrimSpec3;
    }
}
