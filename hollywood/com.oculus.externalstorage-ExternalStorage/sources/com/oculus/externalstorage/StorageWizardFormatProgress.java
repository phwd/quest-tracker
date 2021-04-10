package com.oculus.externalstorage;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StorageWizardFormatProgress extends StorageWizardBase {
    private static final String TAG = StorageWizardFormatProgress.class.getSimpleName();
    private PartitionTask mTask;
    private View mView;

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.oculus.externalstorage.StorageWizardFormatProgress */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.externalstorage.StorageWizardBase
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mView = View.inflate(this, R.layout.storage_wizard_progress, null);
        this.mAlertParams.mView = this.mView;
        this.mAlertParams.mTitle = TextUtils.expandTemplate(getText(R.string.storage_wizard_format_progress_title), this.mDisk.getDescription());
        ((TextView) this.mView.findViewById(R.id.storage_wizard_body)).setText(TextUtils.expandTemplate(getText(R.string.storage_wizard_format_progress_body), this.mDisk.getDescription()));
        setupAlert();
        this.mView.setKeepScreenOn(true);
        this.mTask = (PartitionTask) getLastNonConfigurationInstance();
        PartitionTask partitionTask = this.mTask;
        if (partitionTask == null) {
            this.mTask = new PartitionTask();
            this.mTask.setActivity(this);
            this.mTask.execute(new Void[0]);
            return;
        }
        partitionTask.setActivity(this);
    }

    public Object onRetainNonConfigurationInstance() {
        return this.mTask;
    }

    /* access modifiers changed from: protected */
    public ProgressBar getProgressBar() {
        return (ProgressBar) this.mView.findViewById(R.id.storage_wizard_progress);
    }

    public static class PartitionTask extends AsyncTask<Void, Integer, Exception> {
        public StorageWizardFormatProgress mActivity;

        /* access modifiers changed from: protected */
        public Exception doInBackground(Void... params) {
            StorageWizardFormatProgress activity = this.mActivity;
            try {
                this.mActivity.mStorage.partitionPublic(activity.mDisk.getId());
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        public void setActivity(StorageWizardFormatProgress activity) {
            this.mActivity = activity;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [android.content.Context, com.oculus.externalstorage.StorageWizardFormatProgress] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onPostExecute(java.lang.Exception r5) {
            /*
                r4 = this;
                com.oculus.externalstorage.StorageWizardFormatProgress r0 = r4.mActivity
                boolean r1 = r0.isDestroyed()
                if (r1 == 0) goto L_0x0009
                return
            L_0x0009:
                if (r5 == 0) goto L_0x0036
                java.lang.String r1 = com.oculus.externalstorage.StorageWizardFormatProgress.access$000()
                java.lang.String r2 = "Failed to partition"
                android.util.Log.e(r1, r2, r5)
                android.content.Intent r1 = new android.content.Intent
                java.lang.Class<com.oculus.externalstorage.StorageWizardError> r2 = com.oculus.externalstorage.StorageWizardError.class
                r1.<init>(r0, r2)
                android.os.storage.DiskInfo r2 = r0.mDisk
                java.lang.String r2 = r2.getId()
                java.lang.String r3 = "android.os.storage.extra.DISK_ID"
                r1.putExtra(r3, r2)
                java.lang.String r2 = r5.getMessage()
                java.lang.String r3 = "oculus.storage.ERROR_MESSAGE"
                r1.putExtra(r3, r2)
                r0.startActivity(r1)
                r0.finish()
                return
            L_0x0036:
                com.oculus.externalstorage.StorageWizardFormatProgress.access$100(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.externalstorage.StorageWizardFormatProgress.PartitionTask.onPostExecute(java.lang.Exception):void");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.oculus.externalstorage.StorageWizardFormatProgress */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onFormatFinished() {
        Intent intent = new Intent((Context) this, (Class<?>) StorageWizardReady.class);
        intent.putExtra("android.os.storage.extra.DISK_ID", this.mDisk.getId());
        startActivity(intent);
        finish();
    }
}
