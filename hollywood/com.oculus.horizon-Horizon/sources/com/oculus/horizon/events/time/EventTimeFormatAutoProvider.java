package com.oculus.horizon.events.time;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import X.C003108z;
import X.C01020Iw;
import com.facebook.annotations.Generated;
import com.oculus.locale.LocaleModule;
import com.oculus.time.Clock;

@Generated({"By: InjectorProcessor"})
public class EventTimeFormatAutoProvider extends AnonymousClass0J3<EventTimeFormat> {
    public final Object get() {
        return new EventTimeFormat(C003108z.A02(this), (Clock) AnonymousClass117.A00(354, this), LocaleModule.A01(this), new C01020Iw(106, this));
    }
}
