package com.oculus.horizon.linkedaccounts.dumper;

import X.AbstractC06640p5;
import X.AnonymousClass0dM;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.linkedaccounts.LinkedAccountsManager;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_linkedaccounts_LinkedAccountsManager_ULSEP_BINDING_ID"})
public class LinkedAccountsDumperPlugin implements AnonymousClass0dM {
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_INSERT = "insert";
    public static final String COMMAND_QUERY = "query";
    public static final String NAME = "linkedaccounts";
    @Inject
    @Eager
    public final LinkedAccountsManager mLinkedAccountsManager;

    @Inject
    public LinkedAccountsDumperPlugin(AbstractC06640p5 r2) {
        this.mLinkedAccountsManager = (LinkedAccountsManager) AnonymousClass117.A00(19, r2);
    }
}
