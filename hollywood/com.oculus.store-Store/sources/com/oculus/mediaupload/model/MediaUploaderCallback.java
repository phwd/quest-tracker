package com.oculus.mediaupload.model;

import com.oculus.ipc.common.ParcelableCallbackReceiver;

public interface MediaUploaderCallback extends ParcelableCallbackReceiver.Callback<MediaUploaderResult> {
    void onResult(MediaUploaderResult mediaUploaderResult);
}
