package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.graphql.fb.FacebookGraphQLUtil;

/* renamed from: com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.-$$Lambda$BlockListsGraphQL$kBcpasHlz5rIruB39nuETkfBo54  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$BlockListsGraphQL$kBcpasHlz5rIruB39nuETkfBo54 implements FacebookGraphQLUtil.FailureCallback {
    public static final /* synthetic */ $$Lambda$BlockListsGraphQL$kBcpasHlz5rIruB39nuETkfBo54 INSTANCE = new $$Lambda$BlockListsGraphQL$kBcpasHlz5rIruB39nuETkfBo54();

    private /* synthetic */ $$Lambda$BlockListsGraphQL$kBcpasHlz5rIruB39nuETkfBo54() {
    }

    @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
    public final void callback(Throwable th) {
        Log.e(BlockListsGraphQL.TAG, "Failed to parse response when fetching block lists: ", th);
    }
}
