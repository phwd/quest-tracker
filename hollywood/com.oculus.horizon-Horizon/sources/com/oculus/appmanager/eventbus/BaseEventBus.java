package com.oculus.appmanager.eventbus;

import X.AbstractC09211aF;
import X.AnonymousClass1aA;

public abstract class BaseEventBus {
    public AnonymousClass1aA mNonUIBus = new AnonymousClass1aA(AbstractC09211aF.A00);
    public AnonymousClass1aA mUIBus = new AnonymousClass1aA();

    /* renamed from: com.oculus.appmanager.eventbus.BaseEventBus$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ BaseEventBus this$0;
        public final /* synthetic */ Object val$event;
    }
}
