package com.facebook.assistant.oacr;

public class InteractionUtils {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0049, code lost:
        if (r9 != false) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.assistant.oacr.OacrAssistantResponse constructAssistantResponse(com.facebook.messenger.assistant.thrift.AssistantServerMessage r6, java.lang.String r7, java.lang.String r8, boolean r9, X.FU r10) {
        /*
            r5 = r10
            r0 = 1
            java.lang.Object r2 = r6.A00(r0)
            com.facebook.messenger.assistant.thrift.CuResponseHeader r2 = (com.facebook.messenger.assistant.thrift.CuResponseHeader) r2
            if (r2 != 0) goto L_0x002f
            X.lr r3 = new X.lr
            r3.<init>()
            java.lang.String r2 = ""
            r0 = 0
            r3.A02(r0, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            r0 = 1
            r3.A02(r0, r1)
            r0 = 2
            r3.A02(r0, r2)
            java.lang.Object[] r1 = r3.A03()
            com.facebook.messenger.assistant.thrift.CuResponseHeader r2 = new com.facebook.messenger.assistant.thrift.CuResponseHeader
            r2.<init>()
            java.lang.String r0 = "com.facebook.messenger.assistant.thrift.CuResponseHeader"
            r2.A02(r0, r1)
        L_0x002f:
            r0 = 2
            java.lang.Object r0 = r6.A00(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x0055
            X.FN r3 = new X.FN
            r3.<init>(r0)
            if (r10 != 0) goto L_0x0049
            if (r9 == 0) goto L_0x0053
            java.lang.String r1 = "Null AssistantTTSPlayer when constructing AssistantResponse that streams back TTS"
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        L_0x0049:
            if (r9 == 0) goto L_0x0053
        L_0x004b:
            r4 = r8
            r6 = r7
            com.facebook.assistant.oacr.OacrAssistantResponse r1 = new com.facebook.assistant.oacr.OacrAssistantResponse
            r1.<init>(r2, r3, r4, r5, r6)
            return r1
        L_0x0053:
            r5 = 0
            goto L_0x004b
        L_0x0055:
            java.lang.String r1 = "Null actions in AssistantServerMessage when constructing AssistantResponse"
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.assistant.oacr.InteractionUtils.constructAssistantResponse(com.facebook.messenger.assistant.thrift.AssistantServerMessage, java.lang.String, java.lang.String, boolean, X.FU):com.facebook.assistant.oacr.OacrAssistantResponse");
    }
}
