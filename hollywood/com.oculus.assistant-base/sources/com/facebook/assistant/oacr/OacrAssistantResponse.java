package com.facebook.assistant.oacr;

import X.AbstractC0370Ug;
import X.C0871lr;
import X.FN;
import X.FO;
import X.FP;
import X.FU;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.messenger.assistant.thrift.CuResponseHeader;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OacrAssistantResponse {
    public FP mApplicationVoiceCommandListener = null;
    public FN mAssistantActionMap;
    public final FU mAssistantTTSPlayer;
    public CuResponseHeader mCuResponseHeader;
    public String mDeviceContext;
    public final String mInteractionId;
    public List mPreActions;

    public Integer getClientRequestId() {
        throw new RuntimeException("not supported function: getClientRequestId");
    }

    public OacrAssistantResponse(CuResponseHeader cuResponseHeader, FN fn, String str, FU fu, String str2) {
        HyperThriftBase hyperThriftBase;
        this.mCuResponseHeader = cuResponseHeader;
        this.mAssistantActionMap = fn;
        this.mDeviceContext = str;
        this.mAssistantTTSPlayer = fu;
        this.mInteractionId = str2;
        Number number = (Number) cuResponseHeader.A00(1);
        if (number != null && number.intValue() == 43 && (hyperThriftBase = (HyperThriftBase) fn.A00(FO.MULTI_RESPONSE_ACTION)) != null) {
            ArrayList arrayList = new ArrayList();
            AbstractC0370Ug A0E = ImmutableMap.A05(fn.A00).A06().iterator();
            while (A0E.hasNext()) {
                HyperThriftBase hyperThriftBase2 = (HyperThriftBase) A0E.next();
                if (hyperThriftBase2.A00(19) == null) {
                    arrayList.add(hyperThriftBase2);
                }
            }
            arrayList.addAll((Collection) hyperThriftBase.A00(2));
            Object A00 = hyperThriftBase.A00(0);
            if (A00 != null) {
                this.mPreActions = (List) hyperThriftBase.A00(1);
                this.mAssistantActionMap = new FN(arrayList);
                C0871lr lrVar = new C0871lr(cuResponseHeader);
                lrVar.A02(1, A00);
                Object[] A03 = lrVar.A03();
                CuResponseHeader cuResponseHeader2 = new CuResponseHeader();
                cuResponseHeader2.A02("com.facebook.messenger.assistant.thrift.CuResponseHeader", A03);
                this.mCuResponseHeader = cuResponseHeader2;
            }
        }
    }

    public FN getActionMap() {
        return this.mAssistantActionMap;
    }

    public FP getApplicationVoiceCommandListener() {
        return null;
    }

    public String getDeviceContext() {
        return this.mDeviceContext;
    }

    public String getInteractionId() {
        return this.mInteractionId;
    }

    public List getPreActions() {
        return this.mPreActions;
    }

    public CuResponseHeader getResponseHeader() {
        return this.mCuResponseHeader;
    }

    public FU getTTSPlayer() {
        return this.mAssistantTTSPlayer;
    }

    public void setApplicationVoiceCommandListener(FP fp) {
        this.mApplicationVoiceCommandListener = fp;
    }
}
