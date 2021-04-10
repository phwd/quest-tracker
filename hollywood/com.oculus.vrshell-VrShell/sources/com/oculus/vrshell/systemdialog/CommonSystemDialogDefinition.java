package com.oculus.vrshell.systemdialog;

import android.net.UrlQuerySanitizer;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogDefinitionCommon;

public final class CommonSystemDialogDefinition {
    private final String mDialogDefinition;

    public CommonSystemDialogDefinition(CommonSystemDialog commonSystemDialog, String str) {
        DialogDefinitionCommon dialogDefinitionCommon = new DialogDefinitionCommon(commonSystemDialog);
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
        urlQuerySanitizer.setAllowUnregisteredParamaters(true);
        urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getSpaceLegal());
        urlQuerySanitizer.parseUrl(str);
        for (UrlQuerySanitizer.ParameterValuePair parameterValuePair : urlQuerySanitizer.getParameterList()) {
            dialogDefinitionCommon.setParameter(parameterValuePair.mParameter, parameterValuePair.mValue);
        }
        this.mDialogDefinition = dialogDefinitionCommon.getDialogConfigurationIPCParcel();
    }

    public final String getPendingDialogConfigurationIPCParcel() {
        return this.mDialogDefinition;
    }
}
