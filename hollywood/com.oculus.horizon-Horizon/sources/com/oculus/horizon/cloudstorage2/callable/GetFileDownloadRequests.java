package com.oculus.horizon.cloudstorage2.callable;

import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.api.cloudstorage2.UserCloudFile;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2ActionMap;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2FileAction;
import com.oculus.horizon.cloudstorage2.task.FileDownloadTask;
import java.util.HashMap;
import java.util.Map;

@Dependencies({})
public class GetFileDownloadRequests extends SyncWork<FileDownloadTask.FileRequest[]> {
    public final CloudStorage2ActionMap mActionMap;
    public final Map<String, FileDownloadTask.FileRequest> mFileRequests = new HashMap();

    /* renamed from: A00 */
    public final FileDownloadTask.FileRequest[] call() {
        int i;
        FileDownloadTask.FileRequest[] fileRequestArr = new FileDownloadTask.FileRequest[this.mActionMap.A00()];
        String[] strArr = (String[]) this.mActionMap.get(CloudStorage2FileAction.ADD);
        if (strArr != null) {
            i = 0;
            for (String str : strArr) {
                fileRequestArr[i] = this.mFileRequests.get(str);
                i++;
            }
        } else {
            i = 0;
        }
        String[] strArr2 = (String[]) this.mActionMap.get(CloudStorage2FileAction.UPDATE);
        if (strArr2 != null) {
            for (String str2 : strArr2) {
                fileRequestArr[i] = this.mFileRequests.get(str2);
                i++;
            }
        }
        return fileRequestArr;
    }

    @Inject
    public GetFileDownloadRequests(@Assisted UserCloudFilesResponse userCloudFilesResponse, @Assisted CloudStorage2ActionMap cloudStorage2ActionMap, @Assisted Reporter reporter) {
        super(reporter);
        UserCloudFile[] userCloudFiles = userCloudFilesResponse.getUserCloudFiles();
        if (userCloudFiles != null) {
            for (UserCloudFile userCloudFile : userCloudFiles) {
                Map<String, FileDownloadTask.FileRequest> map = this.mFileRequests;
                String str = userCloudFile.relative_path;
                map.put(str, new FileDownloadTask.FileRequest(userCloudFile.id, userCloudFile.cloud_file_uri, str));
                new Object[1][0] = userCloudFile.relative_path;
            }
        }
        this.mActionMap = cloudStorage2ActionMap;
    }
}
