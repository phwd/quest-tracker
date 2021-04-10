package com.oculus.horizon.api.cloudstorage2;

import androidx.annotation.Nullable;

public class UploadUserCloudFile {
    @Nullable
    public final String expected_sha1_hash;
    public final String file_handle;
    public final String relative_path;

    public UploadUserCloudFile(String str, String str2, @Nullable String str3) {
        this.relative_path = str;
        this.file_handle = str2;
        this.expected_sha1_hash = str3;
    }
}
