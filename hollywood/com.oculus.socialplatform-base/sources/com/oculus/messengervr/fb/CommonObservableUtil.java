package com.oculus.messengervr.fb;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC13031yl;
import X.AbstractC136820a;
import X.AbstractC140021g;
import X.AnonymousClass0MD;
import X.AnonymousClass215;
import X.AnonymousClass219;
import X.AnonymousClass21H;
import X.C137220e;
import X.C138320p;
import X.EnumC139220y;
import android.annotation.TargetApi;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class CommonObservableUtil {
    public static <T> AbstractC136820a<T[]> decorateArrayObservable(String str, AbstractC136820a<T[]> r3) {
        return (AbstractC136820a) ((AbstractC136820a) ((AbstractC136820a) r3.A0J($$Lambda$1nAnCmKYRzugDmDnf2XPfgr86k2.INSTANCE)).A0J(new AbstractC140021g(str) {
            /* class com.oculus.messengervr.fb.$$Lambda$CommonObservableUtil$wBVvie1ExCmHrmSis5peHaramLM2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC140021g
            public final Object apply(AbstractC136820a r2) {
                return CommonObservableUtil.decorateArrayObservableWithLogging(this.f$0, r2);
            }
        })).A0J($$Lambda$cbyaTxMwzdgOkV9NrGlCVbjYc2.INSTANCE);
    }

    public static <T> AbstractC136820a<T[]> decorateArrayObservableWithLogging(String str, AbstractC136820a<T[]> r3) {
        return r3.A0B(new AbstractC12851yS(str) {
            /* class com.oculus.messengervr.fb.$$Lambda$CommonObservableUtil$y0kbZq9PK3z5Yd5juE47xxGjDgw2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
            }
        }, new AbstractC12881yV(str) {
            /* class com.oculus.messengervr.fb.$$Lambda$CommonObservableUtil$kqBkkyQYGZZqGrIj6oCLUl6B1bk2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12881yV
            public final void run() {
            }
        }).A09(new AbstractC12851yS(str) {
            /* class com.oculus.messengervr.fb.$$Lambda$CommonObservableUtil$7rUiFwU6MhkOGDEMqJ9Oy3KAZ1U2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                CommonObservableUtil.lambda$decorateArrayObservableWithLogging$7(this.f$0, (AnonymousClass215) obj);
            }
        });
    }

    public static AbstractC136820a<Integer> decorateCountObservable(String str, AbstractC136820a<Integer> r3) {
        AbstractC136820a<Integer> A0A = r3.A0A(new AbstractC12851yS(str) {
            /* class com.oculus.messengervr.fb.$$Lambda$CommonObservableUtil$nOjo4ywHIyNpa5uPpOr86YkQ1x42 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
            }
        });
        AbstractC13031yl<Object, Object> r2 = C137220e.A08;
        AnonymousClass219.A01(r2, "keySelector is null");
        return new C138320p(A0A, r2, AnonymousClass219.A00);
    }

    public static AbstractC136820a<Optional<Pair<MessengerThread, MessengerParticipant[]>>> decorateThreadViewDataObservable(AbstractC136820a<Optional<Pair<MessengerThread, MessengerParticipant[]>>> r2) {
        return (AbstractC136820a) r2.A0B($$Lambda$CommonObservableUtil$7jQYnP5XDgnOhButH5VFd5_OU9I2.INSTANCE, $$Lambda$CommonObservableUtil$W7bKfiL2dSnIKwCFmSJwngMgGa82.INSTANCE).A09($$Lambda$CommonObservableUtil$hTyDW99NfgEGwMm7Lz4OSlhgaQ2.INSTANCE).A0J($$Lambda$cbyaTxMwzdgOkV9NrGlCVbjYc2.INSTANCE);
    }

    public static /* synthetic */ void lambda$decorateArrayObservableWithLogging$5(String str, AbstractC12271xB r1) throws Exception {
    }

    public static /* synthetic */ void lambda$decorateCountObservable$0(String str, Integer num) throws Exception {
    }

    public static /* synthetic */ void lambda$decorateThreadViewDataObservable$3() throws Exception {
    }

    public static <T> AbstractC136820a<T> toThrottled(AbstractC136820a<T> r1) {
        return r1.A0G(TimeUnit.MILLISECONDS);
    }

    public static /* synthetic */ void lambda$decorateArrayObservableWithLogging$7(String str, AnonymousClass215 r4) throws Exception {
        if (!r4.A01() && EnumC139220y.isError(r4.A00)) {
            AnonymousClass0MD.A09("VR_MESSENGER_API", "%s throw:", str, r4.A00());
        }
    }

    public static /* synthetic */ void lambda$decorateThreadViewDataObservable$4(AnonymousClass215 r3) throws Exception {
        boolean A01 = r3.A01();
        Object obj = r3.A00;
        if (A01) {
            if (obj == null || EnumC139220y.isError(obj)) {
                obj = null;
            }
            Optional optional = (Optional) obj;
            if (optional.isPresent()) {
                optional.get();
            }
        } else if (EnumC139220y.isError(obj)) {
            AnonymousClass0MD.A07("VR_MESSENGER_API", "ThreadViewDataObservable throw:", r3.A00());
        }
    }

    public static <T> AbstractC136820a<T> toReplayAutoConnect(AbstractC136820a<T> r1) {
        return new AnonymousClass21H(r1.A0I());
    }

    public static /* synthetic */ void lambda$decorateArrayObservableWithLogging$6(String str) throws Exception {
    }

    public static /* synthetic */ void lambda$decorateThreadViewDataObservable$2(AbstractC12271xB r0) throws Exception {
    }
}
