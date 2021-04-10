package com.oculus.ocms.app;

import android.app.Application;
import android.content.Context;
import com.facebook.common.process.ProcessName;
import com.facebook.inject.FbInjector;
import com.facebook.inject.FbInjectorProvider;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.rootmodule.RootModuleProvider;
import com.facebook.soloader.SoLoader;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.app.standalone.StandaloneApplicationLikeModule;
import com.oculus.base.app.ApplicationLike;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class OCMSApplication extends Application implements FbInjectorProvider {
    @Inject
    @Eager
    private ApplicationLike mApplicationDelegate;
    @Nullable
    private FbInjector mInjector;

    private static final void _UL_injectMe(Context context, OCMSApplication oCMSApplication) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oCMSApplication);
        } else {
            FbInjector.injectMe(OCMSApplication.class, oCMSApplication, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OCMSApplication oCMSApplication) {
        oCMSApplication.mApplicationDelegate = StandaloneApplicationLikeModule._UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(RootModuleProvider.get(ProcessName.current().getPrivateProcessName()));
            this.mInjector = FbInjector.createForApp(this, arrayList);
            notifyAll();
        }
        SoLoader.init((Context) this, false);
        _UL_injectMe(this, this);
        this.mApplicationDelegate.onCreate(this);
    }

    @Override // com.facebook.inject.FbInjectorProvider
    public synchronized FbInjector getInjector() {
        while (this.mInjector == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this.mInjector;
    }
}
