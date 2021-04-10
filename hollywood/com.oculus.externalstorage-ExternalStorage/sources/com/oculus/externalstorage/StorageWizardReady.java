package com.oculus.externalstorage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

public class StorageWizardReady extends StorageWizardBase implements DialogInterface.OnClickListener {
    /* access modifiers changed from: protected */
    @Override // com.oculus.externalstorage.StorageWizardBase
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlertParams.mTitle = TextUtils.expandTemplate(getText(R.string.storage_wizard_ready_title), this.mDisk.getDescription());
        this.mAlertParams.mMessage = TextUtils.expandTemplate(getText(R.string.storage_wizard_ready_external_body), this.mDisk.getDescription());
        this.mAlertParams.mPositiveButtonText = getString(R.string.done);
        this.mAlertParams.mPositiveButtonListener = this;
        setupAlert();
    }

    public void onClick(DialogInterface dialog, int which) {
        finish();
    }
}
