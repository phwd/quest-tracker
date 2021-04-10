package com.oculus.auth.subscriber;

import X.AbstractC06640p5;
import X.AnonymousClass0Pi;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C01010Iv;
import X.C02600ao;
import android.content.Context;
import android.content.Intent;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.oculus.auth.device.DeviceAuthTokenSubscriber;
import com.oculus.auth.service.DeviceTokenRefreshService;
import javax.inject.Provider;

@Dependencies({})
@ApplicationScoped
public class LoginDeviceAuthTokenSubscriber implements DeviceAuthTokenSubscriber {
    public static volatile LoginDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_INSTANCE;
    public static final C02600ao sSecureContextHelper = C02600ao.A00();

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(439, r2);
    }

    @AutoGeneratedAccessMethod
    public static final LoginDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (LoginDeviceAuthTokenSubscriber) AnonymousClass117.A00(439, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final LoginDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(AbstractC06640p5 r3) {
        if (_UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_INSTANCE == null) {
            synchronized (LoginDeviceAuthTokenSubscriber.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        r3.getApplicationInjector();
                        _UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_INSTANCE = new LoginDeviceAuthTokenSubscriber();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_subscriber_LoginDeviceAuthTokenSubscriber_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(439, r2);
    }

    @Override // com.oculus.auth.device.DeviceAuthTokenSubscriber
    public void onTokenRefresh(Context context, String str) {
        Intent intent = new Intent(context, DeviceTokenRefreshService.class);
        intent.setAction(DeviceTokenRefreshService.ACTION_DEVICE_SCOPED_TOKEN_REFRESH);
        intent.putExtra(DeviceTokenRefreshService.EXTRA_DEVICE_TOKEN, str);
        sSecureContextHelper.A06().A00(intent, context);
    }
}