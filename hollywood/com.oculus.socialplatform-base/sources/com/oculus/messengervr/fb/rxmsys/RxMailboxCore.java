package com.oculus.messengervr.fb.rxmsys;

import X.AbstractC06371Zh;
import X.AbstractC06511aN;
import X.AbstractC10551og;
import X.AbstractC13251zE;
import X.AnonymousClass1YZ;
import X.AnonymousClass1Z6;
import X.AnonymousClass1ZX;
import X.AnonymousClass1Zb;
import X.C06431Zu;
import X.C06461Zy;
import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class RxMailboxCore {
    public final C06461Zy mMailboxCore;

    public AbstractC13251zE<Boolean> fetchMessagesPage(Number number, @Nullable Number number2, Number number3) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(number, number2, number3) {
            /* class com.oculus.messengervr.fb.rxmsys.$$Lambda$RxMailboxCore$U0vGxjrInSmZ2L4DCbASlfCJSCM2 */
            public final /* synthetic */ Number f$1;
            public final /* synthetic */ Number f$2;
            public final /* synthetic */ Number f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r5) {
                RxMailboxCore.this.lambda$fetchMessagesPage$0$RxMailboxCore(this.f$1, this.f$2, this.f$3, r5);
            }
        });
    }

    public /* synthetic */ void lambda$fetchMessagesPage$0$RxMailboxCore(Number number, Number number2, Number number3, AbstractC10551og r11) throws Exception {
        C06461Zy r2 = this.mMailboxCore;
        $$Lambda$18t7XSNjqgVPxPL7788ncUqLAX42 r1 = new AnonymousClass1YZ() {
            /* class com.oculus.messengervr.fb.rxmsys.$$Lambda$18t7XSNjqgVPxPL7788ncUqLAX42 */

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                AbstractC10551og.this.onSuccess(obj);
            }
        };
        AnonymousClass1Z6 r0 = r2.A00;
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        r3.A02(r1);
        r0.A9T(new C06431Zu(r2, r3, number, number2, number3));
        r11.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.rxmsys.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public RxMailboxCore(C06461Zy r1) {
        this.mMailboxCore = r1;
    }
}
