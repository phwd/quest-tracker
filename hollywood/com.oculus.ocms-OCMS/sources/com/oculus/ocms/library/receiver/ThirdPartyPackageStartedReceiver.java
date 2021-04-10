package com.oculus.ocms.library.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.infer.annotation.Initializer;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.context.SecureContext;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.ultralight.UL;
import com.oculus.library.database.DatabaseModule;
import com.oculus.library.database.LibraryStorage;
import com.oculus.library.model.App;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.ocms.library.provider.contract.TrustedBinaryContract;
import com.oculus.ocms.library.service.ThirdPartyPackageWarnOrBlockIntentService;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

public class ThirdPartyPackageStartedReceiver extends OculusSystemSecureBroadcastReceiver implements InjectableComponentWithoutContext {
    private static final boolean DEBUG = false;
    private static final String EVENT_SUBTYPE = "event_subtype";
    private static final String PACKAGE_NAME = "package_name";
    private static final String TAG = "ThirdPartyPackageStartedReceiver";
    private static final String TELEMETRY_EVENT_NAME = "oculus_mobile_third_party_package_started";
    private InjectionContext _UL_mInjectionContext;

    public ThirdPartyPackageStartedReceiver() {
        super(new String[0]);
    }

    private static final void _UL_injectMe(Context context, ThirdPartyPackageStartedReceiver thirdPartyPackageStartedReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe((InjectorLike) FbInjector.get(context), thirdPartyPackageStartedReceiver);
        } else {
            FbInjector.injectMe(ThirdPartyPackageStartedReceiver.class, (InjectableComponentWithoutContext) thirdPartyPackageStartedReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, ThirdPartyPackageStartedReceiver thirdPartyPackageStartedReceiver) {
        thirdPartyPackageStartedReceiver._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    @Initializer
    public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
        Uri data;
        String schemeSpecificPart;
        _UL_injectMe(context, this);
        if (intent != null && TextUtils.equals(intent.getAction(), "com.oculus.action.THIRD_PARTY_PACKAGE_STARTED") && (data = intent.getData()) != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null && !schemeSpecificPart.isEmpty()) {
            ((EventManager) FbInjector.lazyInstance(1, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(TELEMETRY_EVENT_NAME).addExtra("package_name", schemeSpecificPart).addExtra(EVENT_SUBTYPE, "launch").logAndRelease();
            App app = ((LibraryStorage) FbInjector.lazyInstance(0, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(schemeSpecificPart);
            if (app != null && TextUtils.equals(app.trustedBinaryStatus, TrustedBinaryContract.STATUS_UNTRUSTED)) {
                Intent intent2 = new Intent(context, ThirdPartyPackageWarnOrBlockIntentService.class);
                intent2.putExtra("package_name", schemeSpecificPart);
                SecureContext.launchFamilyService(intent2, context);
            }
        }
    }
}
