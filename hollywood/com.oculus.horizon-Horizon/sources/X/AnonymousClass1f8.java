package X;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.WebDialog;

/* renamed from: X.1f8  reason: invalid class name */
public class AnonymousClass1f8 extends WebDialog.Builder {
    public String A00;
    public boolean A01;

    public AnonymousClass1f8(Context context, String str, Bundle bundle) {
        super(context, str, "oauth", bundle);
    }

    @Override // com.facebook.internal.WebDialog.Builder
    public final WebDialog build() {
        Bundle bundle = this.parameters;
        bundle.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
        bundle.putString("client_id", this.applicationId);
        bundle.putString("e2e", this.A00);
        bundle.putString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN_AND_SIGNED_REQUEST);
        bundle.putString(ServerProtocol.DIALOG_PARAM_RETURN_SCOPES, "true");
        if (this.A01) {
            bundle.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, ServerProtocol.DIALOG_REREQUEST_AUTH_TYPE);
        }
        return new WebDialog(this.context, "oauth", bundle, this.theme, this.listener);
    }
}
