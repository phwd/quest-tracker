package com.oculus.externalstorage;

import android.os.Bundle;
import android.os.storage.DiskInfo;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import java.util.Objects;
import oculus.internal.ui.VrAlertActivity;

public abstract class StorageWizardBase extends VrAlertActivity {
    protected DiskInfo mDisk;
    protected StorageManager mStorage;
    private final StorageEventListener mStorageListener = new StorageEventListener() {
        /* class com.oculus.externalstorage.StorageWizardBase.AnonymousClass1 */

        public void onDiskDestroyed(DiskInfo disk) {
            if (StorageWizardBase.this.mDisk.id.equals(disk.id)) {
                StorageWizardBase.this.finish();
            }
        }
    };
    protected VolumeInfo mVolume;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        StorageWizardBase.super.onCreate(savedInstanceState);
        this.mStorage = (StorageManager) getSystemService(StorageManager.class);
        String volumeId = getIntent().getStringExtra("android.os.storage.extra.VOLUME_ID");
        if (!TextUtils.isEmpty(volumeId)) {
            this.mVolume = this.mStorage.findVolumeById(volumeId);
        }
        String diskId = getIntent().getStringExtra("android.os.storage.extra.DISK_ID");
        if (!TextUtils.isEmpty(diskId)) {
            this.mDisk = this.mStorage.findDiskById(diskId);
        } else {
            VolumeInfo volumeInfo = this.mVolume;
            if (volumeInfo != null) {
                this.mDisk = volumeInfo.getDisk();
            }
        }
        if (this.mDisk != null) {
            this.mStorage.registerListener(this.mStorageListener);
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mStorage.unregisterListener(this.mStorageListener);
        StorageWizardBase.super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public VolumeInfo findFirstVolume(int type) {
        for (VolumeInfo vol : this.mStorage.getVolumes()) {
            if (Objects.equals(this.mDisk.getId(), vol.getDiskId()) && vol.getType() == type) {
                return vol;
            }
        }
        return null;
    }
}
