package com.oculus.horizon.platformplugin;

import X.AbstractC06600ny;
import X.AnonymousClass0J3;
import X.AnonymousClass117;
import X.C003108z;
import com.facebook.annotations.Generated;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.horizon.media_session.MediaSessionLifecycleManager;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.vr.VRStateManager;
import com.oculus.managed.ManagedMode;
import com.oculus.partystatemanager.logging.PartyEventFactory;
import com.oculus.util.device.DeviceUtils;

@Generated({"By: InjectorProcessor"})
public class PlatformPluginManagerMethodAutoProvider extends AnonymousClass0J3<PlatformPluginManager> {
    public final Object get() {
        return new PlatformPluginManager(C003108z.A00(this), (VRStateManager) AnonymousClass117.A00(325, this), (UserProfileHelper) AnonymousClass117.A00(68, this), (MediaSessionLifecycleManager) AnonymousClass117.A00(99, this), (OculusLogger) AnonymousClass117.A00(574, this), DeviceUtils.A00(this), (SocialMethods) AnonymousClass117.A00(500, this), (NotificationBuilder) AnonymousClass117.A00(14, this), (AbstractC06600ny) AnonymousClass117.A00(399, this), (ManagedMode) AnonymousClass117.A00(380, this), (DeeplinkIntentUtils) AnonymousClass117.A00(66, this), (PartyEventFactory) AnonymousClass117.A00(434, this));
    }
}
