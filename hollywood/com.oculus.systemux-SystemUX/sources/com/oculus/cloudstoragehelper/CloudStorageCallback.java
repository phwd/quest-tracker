package com.oculus.cloudstoragehelper;

import com.oculus.ipc.common.ParcelableCallbackReceiver;

public interface CloudStorageCallback extends ParcelableCallbackReceiver.Callback<CloudStorageResult> {
    void onResult(CloudStorageResult cloudStorageResult);
}
