package com.oculus.socialplatform;

import X.AnonymousClass006;
import X.AnonymousClass0l0;
import android.os.Build;
import com.oculus.device.APIInterceptor;
import com.oculus.localmedia.LocalMediaManager;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.SocialModule;
import com.oculus.modules.SocialPlatformServiceModuleImpl;
import com.oculus.modules.codegen.SocialPlatformServiceModule;
import com.oculus.panellib.ReactVRPanelService;
import com.oculus.util.StringUtil;
import org.apache.commons.cli.HelpFormatter;

public class BasePanelService extends ReactVRPanelService {
    public static final String FB_APP_ID = "403280113536791";
    public static final String FB_APP_NAME = "oculus-social-platform";
    public static final String TAG = "SocialPlatformPanelService";
    public SocialPlatformServiceModule.ServiceType mServiceType;

    /* renamed from: com.oculus.socialplatform.BasePanelService$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$modules$codegen$SocialPlatformServiceModule$ServiceType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.modules.codegen.SocialPlatformServiceModule$ServiceType[] r0 = com.oculus.modules.codegen.SocialPlatformServiceModule.ServiceType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.socialplatform.BasePanelService.AnonymousClass1.$SwitchMap$com$oculus$modules$codegen$SocialPlatformServiceModule$ServiceType = r2
                com.oculus.modules.codegen.SocialPlatformServiceModule$ServiceType r0 = com.oculus.modules.codegen.SocialPlatformServiceModule.ServiceType.LIVESTREAM_PANEL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.modules.codegen.SocialPlatformServiceModule$ServiceType r0 = com.oculus.modules.codegen.SocialPlatformServiceModule.ServiceType.SHARE_SHEET     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.modules.codegen.SocialPlatformServiceModule$ServiceType r0 = com.oculus.modules.codegen.SocialPlatformServiceModule.ServiceType.SOCIAL     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.modules.codegen.SocialPlatformServiceModule$ServiceType r0 = com.oculus.modules.codegen.SocialPlatformServiceModule.ServiceType.SOCIAL_DIALOGS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.modules.codegen.SocialPlatformServiceModule$ServiceType r0 = com.oculus.modules.codegen.SocialPlatformServiceModule.ServiceType.SOCIAL_BACK_PANEL     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.socialplatform.BasePanelService.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        try {
            AnonymousClass0l0.A06("socialplatform");
        } catch (UnsatisfiedLinkError unused) {
            System.loadLibrary("socialplatform");
        }
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return StringUtil.capitalize(str2);
        }
        return AnonymousClass006.A09(StringUtil.capitalize(str), HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, str2);
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        SocialPlatformServiceModule.ServiceType serviceType = this.mServiceType;
        SocialPlatformServiceModuleImpl.setServiceType(serviceType);
        switch (serviceType.ordinal()) {
            case 0:
            case 1:
                return;
            default:
                SocialModule.registerPartyChangeListener(this);
                SocialModule.registerFriendsChangeListener(this);
                LibraryModuleImpl.registerLibraryChangeListener(this);
                LibraryModuleImpl.fetchInitialLibraryAsync(this);
                QuickPromotionModuleImpl.setHttpClientInterceptor(new APIInterceptor(getApplicationContext()));
                LocalMediaManager.init(this);
                return;
        }
    }

    public BasePanelService(SocialPlatformServiceModule.ServiceType serviceType) {
        super("403280113536791", FB_APP_NAME, serviceType.toString());
        this.mServiceType = serviceType;
    }
}
