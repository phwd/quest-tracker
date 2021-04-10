package com.oculus.horizon.api.cloudstorage2;

public class RemoveUserCloudFile {
    public String expected_sha1_hash;
    public String relative_path;

    public RemoveUserCloudFile(String str, String str2) {
        this.relative_path = str;
        this.expected_sha1_hash = str2;
    }
}
