package com.facebook.secure.content;

import X.AnonymousClass08;
import X.JH;
import X.JI;
import com.facebook.systrace.Systrace;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DeferredInitAbstractContentProviderNoDIDelegate extends JI {
    public final AtomicBoolean A00 = new AtomicBoolean();

    public DeferredInitAbstractContentProviderNoDIDelegate(JH jh) {
        super(jh);
        if (Systrace.A03(512)) {
            Systrace.A01(512, AnonymousClass08.A05(getClass().getSimpleName(), ".", "onCreate"));
        }
        Systrace.A00(512);
    }
}
