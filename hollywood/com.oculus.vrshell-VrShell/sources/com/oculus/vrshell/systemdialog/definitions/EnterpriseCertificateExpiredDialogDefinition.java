package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public class EnterpriseCertificateExpiredDialogDefinition extends CustomSystemDialogDefinition {
    public EnterpriseCertificateExpiredDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.ENTERPRISE_CERTIFICATE_EXPIRED.path(), context.getResources().getString(R.string.certificate_expired_title), context.getResources().getString(R.string.certificate_expired_body)));
    }
}
