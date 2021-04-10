package com.oculus.horizon.linkedaccounts.provider;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.ContentProvider;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import javax.annotation.concurrent.Immutable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
@Immutable
public final class LinkedAccountsContentProviderLogger {
    public static final String EVENT_DELETE = "oculus_mobile_linked_accounts_provider_delete";
    public static final String EVENT_INSERT = "oculus_mobile_linked_accounts_provider_insert";
    public static final String EVENT_PERM_CHECK = "oculus_mobile_linked_accounts_provider_perm_check";
    public static final String EVENT_QUERY = "oculus_mobile_linked_accounts_provider_query";
    public static final String EXTRA_REQUESTING_PACKAGE = "requesting_package";
    public static final String EXTRA_RESULT = "result";
    public static final String EXTRA_SERVICE_PROVIDER = "service_provider";
    public static final String EXTRA_SERVICE_PROVIDERS = "service_providers";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static Event A00(LinkedAccountsContentProviderLogger linkedAccountsContentProviderLogger, String str, ContentProvider contentProvider) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, linkedAccountsContentProviderLogger._UL_mInjectionContext)).A22(str);
        A22.A15("requesting_package", contentProvider.getCallingPackage());
        return A22;
    }

    @Inject
    public LinkedAccountsContentProviderLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
