package com.oculus.messengervr.fb.rxmsys;

import X.AbstractC06371Zh;
import X.AbstractC06511aN;
import X.AbstractC10551og;
import X.AbstractC13251zE;
import X.AnonymousClass1YZ;
import X.AnonymousClass1Z6;
import X.AnonymousClass1ZX;
import X.AnonymousClass1Zb;
import X.AnonymousClass269;
import X.AnonymousClass26E;
import X.AnonymousClass26Z;
import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.messengervr.mca.MailboxMessengerVr$LoadThreadViewDataOptions;
import com.facebook.msys.annotations.MailboxType;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class RxMailboxMessengerVr {
    public final AnonymousClass269 mMailboxMessengerVr;

    public /* synthetic */ void lambda$loadThreadViewDataWithOptions$0$RxMailboxMessengerVr(int i, long j, long j2, MailboxMessengerVr$LoadThreadViewDataOptions mailboxMessengerVr$LoadThreadViewDataOptions, AbstractC10551og r19) throws Exception {
        AnonymousClass269 r4 = this.mMailboxMessengerVr;
        $$Lambda$qtjRS4C5r1O4tLNZkZcyUbkngR42 r1 = new AnonymousClass1YZ() {
            /* class com.oculus.messengervr.fb.rxmsys.$$Lambda$qtjRS4C5r1O4tLNZkZcyUbkngR42 */

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                AbstractC10551og.this.onSuccess(obj);
            }
        };
        AnonymousClass1Z6 r0 = r4.A00;
        AnonymousClass1Zb r5 = new AnonymousClass1Zb(r0);
        r5.A02(r1);
        r0.A9T(new AnonymousClass26E(r4, r5, i, j, j2, mailboxMessengerVr$LoadThreadViewDataOptions));
        r19.A9i(new AbstractC06511aN() {
            /* class com.oculus.messengervr.fb.rxmsys.$$Lambda$vzeJjaK9GC0u5mb7upkZB1ZCbMk2 */

            @Override // X.AbstractC06511aN
            public final void cancel() {
                AnonymousClass1ZX.this.cancel();
            }
        });
    }

    public AbstractC13251zE<AnonymousClass26Z> loadThreadViewDataWithOptions(@MailboxType int i, long j, long j2, MailboxMessengerVr$LoadThreadViewDataOptions mailboxMessengerVr$LoadThreadViewDataOptions) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(i, j, j2, mailboxMessengerVr$LoadThreadViewDataOptions) {
            /* class com.oculus.messengervr.fb.rxmsys.$$Lambda$RxMailboxMessengerVr$jo5DRCIoBQPq8y98yy6NINiTPqs2 */
            public final /* synthetic */ int f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ long f$3;
            public final /* synthetic */ MailboxMessengerVr$LoadThreadViewDataOptions f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
                this.f$4 = r7;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r9) {
                RxMailboxMessengerVr.this.lambda$loadThreadViewDataWithOptions$0$RxMailboxMessengerVr(this.f$1, this.f$2, this.f$3, this.f$4, r9);
            }
        });
    }

    public RxMailboxMessengerVr(AnonymousClass269 r1) {
        this.mMailboxMessengerVr = r1;
    }
}
