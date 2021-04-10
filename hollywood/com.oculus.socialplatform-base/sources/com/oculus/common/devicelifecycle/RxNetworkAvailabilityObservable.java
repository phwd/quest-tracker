package com.oculus.common.devicelifecycle;

import X.AbstractC06511aN;
import X.AbstractC136820a;
import X.AnonymousClass1vb;
import X.AnonymousClass1vc;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import java.util.Objects;

public class RxNetworkAvailabilityObservable {
    public static final String TAG = "RxNetworkAvailabilityObservable";

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static AbstractC136820a<Boolean> create(Context context) {
        return AbstractC136820a.A01(new AnonymousClass1vc(context) {
            /* class com.oculus.common.devicelifecycle.$$Lambda$RxNetworkAvailabilityObservable$bBjdwP7XNwhmjXTd9AQM_EjXYZA2 */
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AnonymousClass1vc
            public final void subscribe(AnonymousClass1vb r2) {
                RxNetworkAvailabilityObservable.lambda$create$1(this.f$0, r2);
            }
        });
    }

    public static /* synthetic */ void lambda$create$1(Context context, final AnonymousClass1vb r3) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(context.getSystemService(ConnectivityManager.class), "ConnectivityManager should not be null.");
        NetworkRequest build = new NetworkRequest.Builder().addCapability(12).build();
        AnonymousClass1 r1 = new ConnectivityManager.NetworkCallback() {
            /* class com.oculus.common.devicelifecycle.RxNetworkAvailabilityObservable.AnonymousClass1 */

            public void onAvailable(Network network) {
                AnonymousClass1vb.this.onNext(true);
            }

            public void onLost(Network network) {
                AnonymousClass1vb.this.onNext(false);
            }
        };
        connectivityManager.registerNetworkCallback(build, r1);
        r3.A9i(new AbstractC06511aN(connectivityManager, r1) {
            /* class com.oculus.common.devicelifecycle.$$Lambda$RxNetworkAvailabilityObservable$3lgcZ9i5XebdooA5YiqvA02S6j82 */
            public final /* synthetic */ ConnectivityManager f$0;
            public final /* synthetic */ ConnectivityManager.NetworkCallback f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // X.AbstractC06511aN
            public final void cancel() {
                this.f$0.unregisterNetworkCallback(this.f$1);
            }
        });
    }
}
