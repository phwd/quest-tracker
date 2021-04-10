package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.graphql.fb.FacebookGraphQLUtil;

/* renamed from: com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.-$$Lambda$FbBlockListGraphQL$ljj3ldogWgIXA_8U_katDv_0TkA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$FbBlockListGraphQL$ljj3ldogWgIXA_8U_katDv_0TkA implements FacebookGraphQLUtil.FailureCallback {
    public static final /* synthetic */ $$Lambda$FbBlockListGraphQL$ljj3ldogWgIXA_8U_katDv_0TkA INSTANCE = new $$Lambda$FbBlockListGraphQL$ljj3ldogWgIXA_8U_katDv_0TkA();

    private /* synthetic */ $$Lambda$FbBlockListGraphQL$ljj3ldogWgIXA_8U_katDv_0TkA() {
    }

    @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
    public final void callback(Throwable th) {
        Log.e(FbBlockListGraphQL.TAG, "Failed to parse response when fetching FB block list");
    }
}
