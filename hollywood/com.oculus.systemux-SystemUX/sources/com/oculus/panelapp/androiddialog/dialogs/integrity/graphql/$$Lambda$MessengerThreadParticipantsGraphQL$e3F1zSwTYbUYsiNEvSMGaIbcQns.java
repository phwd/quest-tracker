package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.graphql.fb.FacebookGraphQLUtil;

/* renamed from: com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.-$$Lambda$MessengerThreadParticipantsGraphQL$e3F1zSwTYbUYsiNEvSMGaIbcQns  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MessengerThreadParticipantsGraphQL$e3F1zSwTYbUYsiNEvSMGaIbcQns implements FacebookGraphQLUtil.FailureCallback {
    public static final /* synthetic */ $$Lambda$MessengerThreadParticipantsGraphQL$e3F1zSwTYbUYsiNEvSMGaIbcQns INSTANCE = new $$Lambda$MessengerThreadParticipantsGraphQL$e3F1zSwTYbUYsiNEvSMGaIbcQns();

    private /* synthetic */ $$Lambda$MessengerThreadParticipantsGraphQL$e3F1zSwTYbUYsiNEvSMGaIbcQns() {
    }

    @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
    public final void callback(Throwable th) {
        Log.e(MessengerThreadParticipantsGraphQL.TAG, "Failed to parse response when fetching messenger thread participants: ", th);
    }
}
