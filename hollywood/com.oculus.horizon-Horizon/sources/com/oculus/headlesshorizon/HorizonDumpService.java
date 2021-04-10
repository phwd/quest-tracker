package com.oculus.headlesshorizon;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.os.DumpsysProxyClientService;
import com.oculus.util.constants.OculusConstants;
import java.util.Locale;
import javax.inject.Provider;

public class HorizonDumpService extends DumpsysProxyClientService {
    @Inject
    public Provider<Credentials> mCredentialsProvider;

    public String getAppName() {
        return OculusConstants.HORIZON_APP_NAME;
    }

    public String dumpState() {
        Credentials credentials = this.mCredentialsProvider.get();
        Locale locale = Locale.ENGLISH;
        boolean z = true;
        Object[] objArr = new Object[1];
        if (credentials == null || TextUtils.isEmpty(credentials.mAccessToken)) {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        return String.format(locale, "Is credentials valid: %b\n", objArr);
    }

    public static final void _UL_injectMe(Context context, HorizonDumpService horizonDumpService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), horizonDumpService);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r0, HorizonDumpService horizonDumpService) {
        horizonDumpService.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.oculus.headlesshorizon.HorizonDumpService */
    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        HorizonDumpService.super.onCreate();
        _UL_injectMe(this, this);
    }
}
