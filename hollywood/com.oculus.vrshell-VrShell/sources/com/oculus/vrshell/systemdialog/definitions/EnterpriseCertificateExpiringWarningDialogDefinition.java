package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import java.util.Date;

public class EnterpriseCertificateExpiringWarningDialogDefinition extends CustomSystemDialogDefinition {
    private static final int DAY_IN_MILLIS = 86400000;
    private static final int DIALOG_AUTO_DISMISS_DELAY_MILLIS = 4000;

    public EnterpriseCertificateExpiringWarningDialogDefinition(Context context) {
        super(createEnterpriseCertificateExpiringWarningDialogDefinitionCustom(context));
    }

    private static DialogDefinitionCustom createEnterpriseCertificateExpiringWarningDialogDefinitionCustom(Context context) {
        String str;
        if (EnterpriseServer.isInEnterpriseMode(context)) {
            int max = (int) (Math.max(EnterpriseServer.getConfiguration(context).getLicenseExpirationDate().getTime() - new Date().getTime(), 0L) / 86400000);
            if (max == 0) {
                str = context.getResources().getString(R.string.certificate_expiring_warning_text_today);
            } else if (max != 1) {
                str = context.getResources().getString(R.string.certificate_expiring_warning_text_days, Integer.valueOf(max));
            } else {
                str = context.getResources().getString(R.string.certificate_expiring_warning_text_one_day);
            }
        } else {
            str = context.getResources().getString(R.string.certificate_expiring_warning_text_uncertain);
        }
        return new DialogDefinitionCustom(SystemUXRoute.ENTERPRISE_CERTIFICATE_EXPIRING_WARNING.path(), context.getResources().getString(R.string.certificate_expiring_warning_title), str).setAutoAction("close", DIALOG_AUTO_DISMISS_DELAY_MILLIS);
    }
}
