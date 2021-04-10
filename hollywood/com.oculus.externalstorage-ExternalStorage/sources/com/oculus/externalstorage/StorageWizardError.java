package com.oculus.externalstorage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

public class StorageWizardError extends StorageWizardBase implements DialogInterface.OnClickListener {
    public static final String EXTRA_ERROR_MESSAGE = "oculus.storage.ERROR_MESSAGE";

    /* access modifiers changed from: protected */
    @Override // com.oculus.externalstorage.StorageWizardBase
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlertParams.mTitle = TextUtils.expandTemplate(getText(R.string.storage_wizard_error_title), this.mDisk.getDescription());
        this.mAlertParams.mMessage = TextUtils.expandTemplate(getText(R.string.storage_wizard_error_body), getIntent().getStringExtra(EXTRA_ERROR_MESSAGE));
        this.mAlertParams.mPositiveButtonText = getString(R.string.done);
        this.mAlertParams.mPositiveButtonListener = this;
        setupAlert();
    }

    public void onClick(DialogInterface dialog, int which) {
        finish();
    }
}
