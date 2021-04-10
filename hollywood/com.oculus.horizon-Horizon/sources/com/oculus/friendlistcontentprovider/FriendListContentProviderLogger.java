package com.oculus.friendlistcontentprovider;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import javax.annotation.concurrent.Immutable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
@Immutable
public final class FriendListContentProviderLogger {
    public static final String EVENT_LINKED_ACCOUNTS_QUERY = "oculus_mobile_friend_list_provider_linked_accounts_query";
    public static final String EXTRA_REQUESTING_PACKAGE = "requesting_package";
    public static final String EXTRA_RESULT = "result";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public FriendListContentProviderLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
