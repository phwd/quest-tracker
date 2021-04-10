package com.oculus.common.fbaccountsmanager;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC13251zE;
import android.annotation.TargetApi;
import android.content.Context;
import java.util.Optional;

@TargetApi(25)
public class RxMessengerVrAccountsContentProviderClient {
    public static AbstractC13251zE<Optional<MessengerVrAccountResult>> getAccountData(Context context) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(context) {
            /* class com.oculus.common.fbaccountsmanager.$$Lambda$RxMessengerVrAccountsContentProviderClient$kxm4USY4re3GhMfNLcnSkvgZJ0k2 */
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r2) {
                RxMessengerVrAccountsContentProviderClient.lambda$getAccountData$2(this.f$0, r2);
            }
        });
    }

    public static AbstractC13251zE<Optional<Boolean>> getIsMessengerAuthenticated(Context context) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(context) {
            /* class com.oculus.common.fbaccountsmanager.$$Lambda$RxMessengerVrAccountsContentProviderClient$SVngfdrzYapJo1n4NUgzUf18d9w2 */
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r2) {
                RxMessengerVrAccountsContentProviderClient.lambda$getIsMessengerAuthenticated$1(this.f$0, r2);
            }
        });
    }

    public static AbstractC13251zE<Optional<String>> getUserId(Context context) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(context) {
            /* class com.oculus.common.fbaccountsmanager.$$Lambda$RxMessengerVrAccountsContentProviderClient$BHNSy99cRzT3ykeFweB_VTJa9o2 */
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r2) {
                RxMessengerVrAccountsContentProviderClient.lambda$getUserId$0(this.f$0, r2);
            }
        });
    }
}
