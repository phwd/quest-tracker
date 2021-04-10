package com.oculus.common.fbaccountsmanager;

import java.util.Optional;
import java.util.function.Function;

/* renamed from: com.oculus.common.fbaccountsmanager.-$$Lambda$IGMAWJjBMMudfnOmHfrmk49OuSo2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$IGMAWJjBMMudfnOmHfrmk49OuSo2 implements Function {
    public static final /* synthetic */ $$Lambda$IGMAWJjBMMudfnOmHfrmk49OuSo2 INSTANCE = new $$Lambda$IGMAWJjBMMudfnOmHfrmk49OuSo2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Optional.ofNullable(((MessengerVrAccountResult) obj).mHasCheckedHasAcknowledgedMessenger);
    }
}
