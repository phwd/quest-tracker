package com.oculus.horizon.api.cloudstorage2;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class UploadAndRemoveUserCloudFilesRequest extends ApiRequest<UploadAndRemoveUserCloudFilesResponse> {
    public String appGroupingID;
    public RemoveUserCloudFile[] removeFiles;
    public UploadUserCloudFile[] uploadHandles;

    public UploadAndRemoveUserCloudFilesRequest(String str, UploadUserCloudFile[] uploadUserCloudFileArr, RemoveUserCloudFile[] removeUserCloudFileArr) {
        this.appGroupingID = str;
        this.uploadHandles = uploadUserCloudFileArr;
        this.removeFiles = removeUserCloudFileArr;
    }

    public ImmutableMap<String, Object> getParams() {
        return ImmutableMap.builder().put("application_grouping_id", this.appGroupingID).put("upload_file_handles", this.uploadHandles).put("remove_files", this.removeFiles).build();
    }
}
