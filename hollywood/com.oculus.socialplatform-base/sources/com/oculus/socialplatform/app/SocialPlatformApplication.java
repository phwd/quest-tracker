package com.oculus.socialplatform.app;

import X.AbstractC03380mI;
import X.AnonymousClass006;
import X.AnonymousClass05c;
import X.AnonymousClass0HY;
import X.AnonymousClass0Jl;
import X.AnonymousClass0MD;
import X.AnonymousClass0R6;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0l0;
import X.AnonymousClass0l1;
import X.AnonymousClass0l4;
import X.AnonymousClass0lI;
import X.AnonymousClass0lg;
import X.C002105e;
import X.EnumC03110lH;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import com.facebook.inject.rootmodule.defaultmodule.___DEFAULT___ProcessRootModule;
import com.oculus.processhelper.ProcessHelper;
import com.oculus.provider.OculusContent;
import com.oculus.socialplatform.PanelApplication;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class SocialPlatformApplication extends PanelApplication implements AnonymousClass0R6 {
    public static final String TAG = "SocialPlatformApplication";
    public AnonymousClass0RE _UL_mInjectionContext;
    @Nullable
    public AnonymousClass0VF mInjector;

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, SocialPlatformApplication socialPlatformApplication) {
        socialPlatformApplication._UL_mInjectionContext = new AnonymousClass0RE(1, r2);
    }

    @Override // X.AnonymousClass0R6
    @Nullable
    public synchronized AnonymousClass0VF getInjector() {
        AnonymousClass0VF r0;
        if (!ProcessHelper.isInDefaultProcess(this)) {
            r0 = null;
        } else {
            while (true) {
                try {
                    r0 = this.mInjector;
                    if (r0 != null) {
                        break;
                    }
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return r0;
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void onCreate() {
        AnonymousClass0MD.A00(5);
        if (!ProcessHelper.isInDefaultProcess(this)) {
            super.onCreate();
            return;
        }
        OculusContent.FriendList.rebuild("com.oculus.socialplatform");
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            AnonymousClass0Jl.A00();
            arrayList.add(new ___DEFAULT___ProcessRootModule());
            AbstractC03380mI.A00();
            AnonymousClass0VF.A00 = getApplicationContext();
            this.mInjector = new C002105e(this, arrayList);
            notifyAll();
        }
        _UL_injectMe(this, this);
        ((SocialPlatformInitializer) AnonymousClass0VF.A03(0, 19, this._UL_mInjectionContext)).run();
    }

    public static final void _UL_injectMe(Context context, SocialPlatformApplication socialPlatformApplication) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), socialPlatformApplication);
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        synchronized (AnonymousClass0lI.class) {
            AnonymousClass0l1[] r4 = {null};
            int i = 2;
            if (!AnonymousClass0lI.A02) {
                StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                try {
                    if (new File("/data/local/tmp/ctscan_test_running").exists()) {
                        i = 0;
                    }
                    AnonymousClass0l4.A00(new File(AnonymousClass006.A07(context.getApplicationInfo().dataDir, "/app_libs")));
                    AnonymousClass0l0.A04(context, i);
                    AnonymousClass0lI.A01 = new ArrayList<>();
                    AnonymousClass0l1 r0 = r4[0];
                    if (r0 != null) {
                        AnonymousClass0l0.A05(r0);
                        AnonymousClass0lI.A01.add(r0.toString());
                    }
                    AnonymousClass0l0.A05(new AnonymousClass05c(context));
                    AnonymousClass0lI.A01.add("lib-assets");
                    EnumC03110lH r2 = EnumC03110lH.XZ;
                    AnonymousClass0l0.A05(new AnonymousClass0HY(context, r2));
                    AnonymousClass0lI.A01.add(r2.getOutputDirectoryName());
                    EnumC03110lH r22 = EnumC03110lH.ZSTD;
                    AnonymousClass0l0.A05(new AnonymousClass0HY(context, r22));
                    AnonymousClass0lI.A01.add(r22.getOutputDirectoryName());
                    EnumC03110lH r23 = EnumC03110lH.SUPERPACK_XZ;
                    AnonymousClass0l0.A05(new AnonymousClass0HY(context, r23));
                    AnonymousClass0lI.A01.add(r23.getOutputDirectoryName());
                    EnumC03110lH r24 = EnumC03110lH.SUPERPACK_ZSTD;
                    AnonymousClass0l0.A05(new AnonymousClass0HY(context, r24));
                    AnonymousClass0lI.A01.add(r24.getOutputDirectoryName());
                    EnumC03110lH r25 = EnumC03110lH.SUPERPACK_BR;
                    AnonymousClass0l0.A05(new AnonymousClass0HY(context, r25));
                    AnonymousClass0lI.A01.add(r25.getOutputDirectoryName());
                    AnonymousClass0lI.A00 = context;
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    AnonymousClass0lI.A02 = true;
                } catch (IOException e) {
                    Log.e("FbSoLoader", "IOException during init", e);
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    AnonymousClass0lI.A02 = true;
                    throw th;
                }
            }
        }
    }
}
