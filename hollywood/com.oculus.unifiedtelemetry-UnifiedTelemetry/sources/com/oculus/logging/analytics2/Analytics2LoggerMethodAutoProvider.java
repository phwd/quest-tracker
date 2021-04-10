package com.oculus.logging.analytics2;

import X.AbstractC0097Hv;
import X.C0515sp;
import X.Fe;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class Analytics2LoggerMethodAutoProvider extends AbstractC0097Hv<Fe> {
    public final Object get() {
        Fe fe;
        Analytics2LoggerProvider analytics2LoggerProvider = (Analytics2LoggerProvider) C0515sp.A00(77, this);
        synchronized (analytics2LoggerProvider) {
            fe = analytics2LoggerProvider.mAnalytics2Logger;
        }
        return fe;
    }
}
