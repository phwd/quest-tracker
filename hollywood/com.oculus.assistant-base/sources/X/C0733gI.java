package X;

import com.facebook.assistant.oacr.AudioReader;
import com.facebook.assistant.oacr.OacrApi;
import com.facebook.assistant.oacr.utils.StreamHandler;
import com.facebook.debug.tracer.Tracer;

/* renamed from: X.gI  reason: case insensitive filesystem */
public final class C0733gI extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$45";
    public final /* synthetic */ C0740gP A00;
    public final /* synthetic */ iF A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0733gI(C0740gP gPVar, iF iFVar) {
        super("ACP: activateVoiceInteraction");
        this.A00 = gPVar;
        this.A01 = iFVar;
    }

    public final void run() {
        int i;
        StreamHandler streamHandler;
        Tracer.A01("onActivateVoiceInteraction");
        try {
            C0740gP gPVar = this.A00;
            if (gPVar.A05 != null) {
                iF iFVar = this.A01;
                String str = iFVar.A07;
                if (str == null) {
                    str = C7.A00().toString();
                }
                try {
                    String str2 = iFVar.A0A;
                    if (str2.equals("voice_command")) {
                        String str3 = iFVar.A09;
                        int i2 = iFVar.A01;
                        i = i2;
                        Integer valueOf = Integer.valueOf(i2);
                        String obj = iFVar.A04.toString();
                        C0139Dd.A0J("AssistantClientPlatform", "executeVoiceRequest, speech domain: %s, audio sampling rate: %d, encoding: %s", str3, valueOf, obj);
                        streamHandler = gPVar.A05.executeVoiceRequest(str3, i2, str, iFVar.A08.equals("required"), "enabled".equals(iFVar.A06), false, iFVar.A03, obj);
                    } else if (str2.equals("transcription")) {
                        String obj2 = iFVar.A04.toString();
                        C0139Dd.A0F("AssistantClientPlatform", "executeTranscriptionRequest, encoding: %s", obj2);
                        OacrApi oacrApi = gPVar.A05;
                        String str4 = iFVar.A09;
                        i = iFVar.A01;
                        streamHandler = oacrApi.executeTranscriptionRequest(str4, i, iFVar.A0B, false, "enabled".equals(iFVar.A06), str, false, obj2, iFVar.A02);
                    } else if (str2.equals("voice_search")) {
                        String obj3 = iFVar.A04.toString();
                        C0139Dd.A0F("AssistantClientPlatform", "executeVoiceSearchTranscriptionRequest, encoding: %s", obj3);
                        OacrApi oacrApi2 = gPVar.A05;
                        String str5 = iFVar.A09;
                        i = iFVar.A01;
                        streamHandler = oacrApi2.executeTranscriptionRequest(str5, i, iFVar.A0B, true, "enabled".equals(iFVar.A06), str, false, obj3, iFVar.A02);
                    } else {
                        C0740gP.A04(gPVar, new C0813iG(AnonymousClass09.A01, "Invalid Voice Request Type"));
                        return;
                    }
                    gPVar.A14.set(true);
                    AudioReader audioReader = new AudioReader(iFVar.A05, streamHandler, i, iFVar.A00, new C0732gH(this, str));
                    gPVar.A03 = audioReader;
                    audioReader.start();
                } catch (RuntimeException e) {
                    C0139Dd.A0O("AssistantClientPlatform", "Caught Runtime Exception: %s when exeucting request in OacrApi", e.getMessage());
                    C0740gP.A04(gPVar, new C0813iG(AnonymousClass09.A0L, e.getMessage()));
                }
                return;
            }
            throw null;
        } finally {
            Tracer.A00();
        }
    }
}
