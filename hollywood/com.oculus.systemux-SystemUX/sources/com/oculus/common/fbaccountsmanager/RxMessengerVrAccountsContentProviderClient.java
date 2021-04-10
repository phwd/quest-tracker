package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import android.content.Context;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.util.Optional;

@TargetApi(25)
public class RxMessengerVrAccountsContentProviderClient {
    public static Single<Optional<String>> getUserId(Context context) {
        return Single.create(new SingleOnSubscribe(context) {
            /* class com.oculus.common.fbaccountsmanager.$$Lambda$RxMessengerVrAccountsContentProviderClient$BHNSy99cRzT3ykeFweB_VTJa9o */
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void subscribe(SingleEmitter singleEmitter) {
                RxMessengerVrAccountsContentProviderClient.lambda$getUserId$0(this.f$0, singleEmitter);
            }
        });
    }

    public static Single<Optional<Boolean>> getIsMessengerAuthenticated(Context context) {
        return Single.create(new SingleOnSubscribe(context) {
            /* class com.oculus.common.fbaccountsmanager.$$Lambda$RxMessengerVrAccountsContentProviderClient$SVngfdrzYapJo1n4NUgzUf18d9w */
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void subscribe(SingleEmitter singleEmitter) {
                RxMessengerVrAccountsContentProviderClient.lambda$getIsMessengerAuthenticated$1(this.f$0, singleEmitter);
            }
        });
    }

    public static Single<Optional<MessengerVrAccountResult>> getAccountData(Context context) {
        return Single.create(new SingleOnSubscribe(context) {
            /* class com.oculus.common.fbaccountsmanager.$$Lambda$RxMessengerVrAccountsContentProviderClient$kxm4USY4re3GhMfNLcnSkvgZJ0k */
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void subscribe(SingleEmitter singleEmitter) {
                RxMessengerVrAccountsContentProviderClient.lambda$getAccountData$2(this.f$0, singleEmitter);
            }
        });
    }
}
