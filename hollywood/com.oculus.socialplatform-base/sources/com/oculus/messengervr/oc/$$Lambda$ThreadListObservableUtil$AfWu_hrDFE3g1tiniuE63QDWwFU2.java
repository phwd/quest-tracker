package com.oculus.messengervr.oc;

import X.AbstractC13191z2;

/* renamed from: com.oculus.messengervr.oc.-$$Lambda$ThreadListObservableUtil$AfWu_hrDFE3g1tiniuE63QDWwFU2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ThreadListObservableUtil$AfWu_hrDFE3g1tiniuE63QDWwFU2 implements AbstractC13191z2 {
    public static final /* synthetic */ $$Lambda$ThreadListObservableUtil$AfWu_hrDFE3g1tiniuE63QDWwFU2 INSTANCE = new $$Lambda$ThreadListObservableUtil$AfWu_hrDFE3g1tiniuE63QDWwFU2();

    @Override // X.AbstractC13191z2
    public final boolean test(Object obj) {
        return Constants.THREAD_LIST_UPDATE_RESULT_TYPES.contains(((OcMessengerManagerUpdateResult) obj).mUpdateResultType);
    }
}
