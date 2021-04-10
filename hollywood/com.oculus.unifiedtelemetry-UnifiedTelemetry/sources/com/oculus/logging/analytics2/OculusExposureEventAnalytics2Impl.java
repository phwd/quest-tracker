package com.oculus.logging.analytics2;

import X.C0219We;
import X.Fb;
import X.Fe;
import X.GO;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.annotation.Nullable;

public class OculusExposureEventAnalytics2Impl extends OculusEventAnalytics2Impl {
    public OculusExposureEventAnalytics2Impl(Fe fe, IErrorReporter iErrorReporter, C0219We we, @Nullable String str, String str2, boolean z) {
        super(str, str2, iErrorReporter, we, fe.A00(new Fb(str, str2, GO.EXPERIMENT, z)));
    }
}
