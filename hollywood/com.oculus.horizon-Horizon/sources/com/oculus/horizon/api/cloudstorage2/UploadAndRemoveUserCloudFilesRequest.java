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
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("application_grouping_id", this.appGroupingID);
        A01.put("upload_file_handles", this.uploadHandles);
        A01.put("remove_files", this.removeFiles);
        return A01.build();
    }
}
