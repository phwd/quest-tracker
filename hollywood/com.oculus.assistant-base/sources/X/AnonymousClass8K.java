package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.assistant.oacr.OacrMCName;
import com.facebook.messenger.assistant.thrift.MCValueUnion;
import com.facebook.messenger.assistant.thrift.TMCValue;
import java.util.HashMap;

/* renamed from: X.8K  reason: invalid class name */
public final class AnonymousClass8K extends HashMap<Integer, TMCValue> {
    public final /* synthetic */ C0740gP this$0;

    public AnonymousClass8K(C0740gP gPVar) {
        this.this$0 = gPVar;
        C0875md mdVar = new C0875md();
        mdVar.A02(3, OacrConstants.AUTO_SPEECH_DOMAIN);
        mdVar.A00 = 4;
        MCValueUnion A04 = mdVar.A04();
        Integer valueOf = Integer.valueOf(OacrMCName.RBC_RULES.ordinal());
        C0885ne neVar = new C0885ne();
        neVar.A02(0, A04);
        put(valueOf, neVar.A04());
        C0875md mdVar2 = new C0875md();
        mdVar2.A02(0, false);
        mdVar2.A00 = 1;
        MCValueUnion A042 = mdVar2.A04();
        Integer valueOf2 = Integer.valueOf(OacrMCName.CONTACT_RANKING_ENABLED.ordinal());
        C0885ne neVar2 = new C0885ne();
        neVar2.A02(0, A042);
        put(valueOf2, neVar2.A04());
        C0875md mdVar3 = new C0875md();
        mdVar3.A02(0, false);
        mdVar3.A00 = 1;
        MCValueUnion A043 = mdVar3.A04();
        Integer valueOf3 = Integer.valueOf(OacrMCName.PRON_LEARNING_ENABLED.ordinal());
        C0885ne neVar3 = new C0885ne();
        neVar3.A02(0, A043);
        put(valueOf3, neVar3.A04());
        C0875md mdVar4 = new C0875md();
        mdVar4.A02(2, Double.valueOf(0.0d));
        mdVar4.A00 = 3;
        MCValueUnion A044 = mdVar4.A04();
        Integer valueOf4 = Integer.valueOf(OacrMCName.CONTACT_RANKING_SCORE_FLOOR.ordinal());
        C0885ne neVar4 = new C0885ne();
        neVar4.A02(0, A044);
        put(valueOf4, neVar4.A04());
        C0875md mdVar5 = new C0875md();
        mdVar5.A02(2, Double.valueOf(0.04d));
        mdVar5.A00 = 3;
        MCValueUnion A045 = mdVar5.A04();
        Integer valueOf5 = Integer.valueOf(OacrMCName.CONTACT_RANKING_ALPHA.ordinal());
        C0885ne neVar5 = new C0885ne();
        neVar5.A02(0, A045);
        put(valueOf5, neVar5.A04());
        C0875md mdVar6 = new C0875md();
        mdVar6.A02(0, false);
        mdVar6.A00 = 1;
        MCValueUnion A046 = mdVar6.A04();
        Integer valueOf6 = Integer.valueOf(OacrMCName.CONTACT_PHONEME_MATCHING_ENABLED.ordinal());
        C0885ne neVar6 = new C0885ne();
        neVar6.A02(0, A046);
        put(valueOf6, neVar6.A04());
    }
}
