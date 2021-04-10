package X;

import android.util.Base64;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.messenger.assistant.thrift.AssistantClientOpaqueRequest;
import com.facebook.messenger.assistant.thrift.AssistantClientRequest;
import com.facebook.messenger.assistant.thrift.OpaqueRequest;
import com.facebook.messenger.assistant.thrift.PredefinedIntentRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.Xd  reason: case insensitive filesystem */
public final /* synthetic */ class RunnableC0426Xd implements Runnable {
    public static final /* synthetic */ RunnableC0426Xd A00 = new RunnableC0426Xd();
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$pCUVLdZcPE0YF45n2zMd4wu5-QE";

    public final void run() {
        AssistantLogger assistantLogger = C00799i.A00;
        assistantLogger.startEventInteraction("HelpTask");
        String str = assistantLogger.mInteractionId;
        Ep.A00();
        HashMap hashMap = new HashMap();
        if ("help".isEmpty() || "IN:GENERAL_HELP".isEmpty()) {
            throw new IllegalArgumentException("Building AssistantTaskPrototyper with no domain or intent");
        }
        BS bs = new BS(hashMap, str);
        C0878mx mxVar = new C0878mx();
        mxVar.A02(1, bs.A01);
        mxVar.A02(3, bs.A00);
        Map map = bs.A03;
        if (map != null) {
            mxVar.A02(2, map);
        }
        Object[] A03 = mxVar.A03();
        HyperThriftBase.Builder.A01(A03, 2);
        PredefinedIntentRequest predefinedIntentRequest = new PredefinedIntentRequest();
        predefinedIntentRequest.A02("com.facebook.messenger.assistant.thrift.PredefinedIntentRequest", A03);
        C0868lR lRVar = new C0868lR();
        lRVar.A02(1, predefinedIntentRequest);
        lRVar.A00 = 2;
        Object[] A032 = lRVar.A03();
        AssistantClientOpaqueRequest assistantClientOpaqueRequest = new AssistantClientOpaqueRequest();
        assistantClientOpaqueRequest.A02("com.facebook.messenger.assistant.thrift.AssistantClientOpaqueRequest", A032);
        assistantClientOpaqueRequest.A01(lRVar.A00);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Ei.A00(new Ei(Ep.A00().A00, new C0984q2().A2l(new C0992qC(byteArrayOutputStream))), "com.facebook.messenger.assistant.thrift.AssistantClientOpaqueRequest", assistantClientOpaqueRequest);
            byteArrayOutputStream.close();
            String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            C0877mq mqVar = new C0877mq();
            mqVar.A02(0, encodeToString);
            Object[] A033 = mqVar.A03();
            OpaqueRequest opaqueRequest = new OpaqueRequest();
            opaqueRequest.A02("com.facebook.messenger.assistant.thrift.OpaqueRequest", A033);
            C0869lT lTVar = new C0869lT();
            lTVar.A02(3, opaqueRequest);
            lTVar.A00 = 4;
            Object[] A034 = lTVar.A03();
            AssistantClientRequest assistantClientRequest = new AssistantClientRequest();
            assistantClientRequest.A02("com.facebook.messenger.assistant.thrift.AssistantClientRequest", A034);
            assistantClientRequest.A01(lTVar.A00);
            C0112Aq.A00().A01(new C0816iL(assistantClientRequest, bs.A02));
        } catch (L9 e) {
            throw new RuntimeException(e);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }
}
