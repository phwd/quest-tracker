package com.oculus.alpenglow.locale;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.C04910hv;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.constants.Constants;
import java.util.Locale;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_secure_context_SecureContextHelper_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LocaleConfigChangeListener implements ConfigChangeListener {
    public static final String TAG = "EnterpriseServer.LocaleConfigChangeListener";
    public static volatile LocaleConfigChangeListener _UL__ULSEP_com_oculus_alpenglow_locale_LocaleConfigChangeListener_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        Device.ManagementInfo A3y;
        Device.ManagementInfo.DeviceConfig A3Q;
        Device.ManagementInfo.DeviceConfig.OsConfig A4D;
        String str = null;
        if (!(device2 == null || (A3y = device2.A3y()) == null || (A3Q = A3y.A3Q()) == null || (A4D = A3Q.A4D()) == null)) {
            str = A4D.A3v();
        }
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("_");
            Locale locale = new Locale(split[0], split[1]);
            if (!Resources.getSystem().getConfiguration().locale.equals(locale)) {
                AnonymousClass0NK.A06(TAG, "setting locale to %s", locale.toLanguageTag());
                Intent intent = new Intent();
                intent.setComponent(ComponentName.createRelative("com.oculus.companion.server", Constants.COMPANION_SERVER_SERVICE));
                intent.setAction(Constants.COMPANION_ACTION_LOCALE);
                intent.putExtra(Constants.COMPANION_EXTRA_COUNTRY, locale.getCountry());
                intent.putExtra(Constants.COMPANION_EXTRA_LANGUAGE, locale.getLanguage());
                intent.putExtra(Constants.COMPANION_EXTRA_SHOULD_REBOOT, false);
                ((C04910hv) AnonymousClass0Lh.A03(1, 15, this._UL_mInjectionContext)).A03().A00(intent, (Context) AnonymousClass0Lh.A03(0, 76, this._UL_mInjectionContext));
            }
        }
    }

    @Inject
    public LocaleConfigChangeListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
    }
}
