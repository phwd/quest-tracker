package com.oculus.horizon.cloudstorage2.model;

import com.oculus.horizon.api.cloudstorage2.UserCloudFile;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import java.util.HashMap;
import java.util.Map;

public class CloudStorage2Metadata {
    public final CloudStorage2FileMetadata[] files;

    public final Map<String, CloudStorage2FileMetadata> A01() {
        HashMap hashMap = new HashMap();
        CloudStorage2FileMetadata[] cloudStorage2FileMetadataArr = this.files;
        for (CloudStorage2FileMetadata cloudStorage2FileMetadata : cloudStorage2FileMetadataArr) {
            hashMap.put(cloudStorage2FileMetadata.relativePath, cloudStorage2FileMetadata);
        }
        return hashMap;
    }

    public CloudStorage2Metadata(CloudStorage2FileMetadata[] cloudStorage2FileMetadataArr) {
        this.files = cloudStorage2FileMetadataArr;
    }

    public static CloudStorage2Metadata A00(UserCloudFilesResponse userCloudFilesResponse) {
        UserCloudFile[] userCloudFiles = userCloudFilesResponse.getUserCloudFiles();
        int length = userCloudFiles.length;
        CloudStorage2FileMetadata[] cloudStorage2FileMetadataArr = new CloudStorage2FileMetadata[length];
        for (int i = 0; i < length; i++) {
            UserCloudFile userCloudFile = userCloudFiles[i];
            CloudStorage2FileMetadata cloudStorage2FileMetadata = new CloudStorage2FileMetadata();
            cloudStorage2FileMetadata.modifiedTime = (long) userCloudFile.uploaded_time;
            cloudStorage2FileMetadata.relativePath = userCloudFile.relative_path;
            cloudStorage2FileMetadata.sha1 = userCloudFile.sha1_hash;
            cloudStorage2FileMetadataArr[i] = cloudStorage2FileMetadata;
        }
        return new CloudStorage2Metadata(cloudStorage2FileMetadataArr);
    }
}
