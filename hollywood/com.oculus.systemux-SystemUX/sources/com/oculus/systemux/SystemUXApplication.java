package com.oculus.systemux;

import android.app.Application;
import com.oculus.common.ocauth.OVRAuthHelper;
import com.oculus.panelapp.anytimeui.config.FrescoConfigModule;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.concurrent.Callable;

public class SystemUXApplication extends Application {
    private final OVRAuthHelper mAuthHelper = new OVRAuthHelper(this);

    public void onCreate() {
        super.onCreate();
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.systemux.$$Lambda$SystemUXApplication$qhaj68behxzQ0rPFyHJF5kpGCUE */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SystemUXApplication.this.lambda$onCreate$55$SystemUXApplication();
            }
        });
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.systemux.$$Lambda$SystemUXApplication$_adnzaQ_0JkJxjSTy6X3uTfuV14 */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SystemUXApplication.this.lambda$onCreate$56$SystemUXApplication();
            }
        });
        initFresco();
    }

    public /* synthetic */ Object lambda$onCreate$55$SystemUXApplication() throws Exception {
        initAccessToken();
        return null;
    }

    public /* synthetic */ Object lambda$onCreate$56$SystemUXApplication() throws Exception {
        initEntitlements();
        return null;
    }

    public OVRAuthHelper getAuthHelper() {
        return this.mAuthHelper;
    }

    private void initAccessToken() {
        this.mAuthHelper.fetchAccessToken();
    }

    private void initEntitlements() {
        HorizonUtil.refetch(getBaseContext(), null);
    }

    private void initFresco() {
        FrescoConfigModule.initialize(getBaseContext());
    }
}
