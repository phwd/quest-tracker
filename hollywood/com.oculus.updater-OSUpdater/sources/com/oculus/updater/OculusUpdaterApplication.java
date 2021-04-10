package com.oculus.updater;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.common.process.PrivateProcessName;
import com.facebook.common.process.ProcessName;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.FbInjectorProvider;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.rootmodule.RootModuleProvider;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.common.build.BuildConstants;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.AppInitModule;
import com.oculus.errorreporting.ErrorReporter;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.os.Version;
import com.oculus.updater.init.AppInitializer;
import java.util.ArrayList;

public class OculusUpdaterApplication extends Application implements FbInjectorProvider {
    @Inject
    @Eager
    private AppInitLock mAppInitLock;
    @Inject
    @Eager
    private AppInitializer mAppInitializer;
    private FbInjector mInjector;

    private static final void _UL_injectMe(Context context, OculusUpdaterApplication oculusUpdaterApplication) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusUpdaterApplication);
        } else {
            FbInjector.injectMe(OculusUpdaterApplication.class, oculusUpdaterApplication, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusUpdaterApplication oculusUpdaterApplication) {
        oculusUpdaterApplication.mAppInitializer = AppInitializer._UL__ULSEP_com_oculus_updater_init_AppInitializer_ULSEP_ACCESS_METHOD(injectorLike);
        oculusUpdaterApplication.mAppInitLock = AppInitModule._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            PrivateProcessName privateProcessName = ProcessName.current().getPrivateProcessName();
            if (privateProcessName != null) {
                arrayList.add(RootModuleProvider.get(privateProcessName));
            }
            this.mInjector = FbInjector.createForApp(this, arrayList);
            notifyAll();
        }
        _UL_injectMe(this, this);
        NativeLoader.init(new SystemDelegate());
        if (BuildConstants.DEBUG) {
            BLog.setLogLevel(2);
        } else {
            BLog.setLogLevel(4);
        }
        ErrorReporter.initCrashReporting(createDeviceProtectedStorageContext(), "1961163130779579", "Oculus Updater");
        this.mAppInitializer.run();
        this.mAppInitLock.notifyAppInitializationComplete();
        UnifiedTelemetryLogger.getInstance().init(getApplicationContext());
        if (Version.CURRENT_SDK_VERSION >= 13) {
            startService(new Intent(this, OculusUpdaterDumpService.class));
        }
        Intent intent = new Intent("process_started");
        intent.setComponent(new ComponentName(this, "com.oculus.updater.core.os.OSUpdateService"));
        startService(intent);
    }

    @Override // com.facebook.inject.FbInjectorProvider
    public final synchronized FbInjector getInjector() {
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
