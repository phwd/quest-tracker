package com.oculus.externalstorage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class StorageWizardFormatConfirm extends StorageWizardBase implements DialogInterface.OnClickListener {
    /* access modifiers changed from: protected */
    @Override // com.oculus.externalstorage.StorageWizardBase
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlertParams.mTitle = getString(R.string.storage_format_confirm_title);
        this.mAlertParams.mMessage = getString(R.string.storage_format_confirm_message);
        this.mAlertParams.mPositiveButtonText = getString(R.string.reformat_usb);
        this.mAlertParams.mNegativeButtonText = getString(R.string.storage_launcher_refuse);
        this.mAlertParams.mPositiveButtonListener = this;
        this.mAlertParams.mNegativeButtonListener = this;
        setupAlert();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.oculus.externalstorage.StorageWizardFormatConfirm */
    /* JADX WARN: Multi-variable type inference failed */
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            Intent intent = new Intent((Context) this, (Class<?>) StorageWizardFormatProgress.class);
            intent.putExtra("android.os.storage.extra.DISK_ID", getIntent().getStringExtra("android.os.storage.extra.DISK_ID"));
            startActivity(intent);
        }
        finish();
    }
}
