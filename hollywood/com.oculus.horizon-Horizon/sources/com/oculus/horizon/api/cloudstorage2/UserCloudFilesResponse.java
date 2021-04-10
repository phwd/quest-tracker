package com.oculus.horizon.api.cloudstorage2;

import com.oculus.http.core.base.ValidatableApiResponse;

public class UserCloudFilesResponse implements ValidatableApiResponse {
    public final Viewer viewer;

    public static class User {
        public final UserCloudFiles cloud_files;
    }

    public static class UserCloudFiles {
        public final UserCloudFile[] nodes;
    }

    public static class Viewer {
        public final User user;
    }

    public UserCloudFile[] getUserCloudFiles() {
        return this.viewer.user.cloud_files.nodes;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null) {
            str = "Received an invalid UserCloudFilesResponse. Response didn't have a viewer";
        } else if (viewer2.user == null) {
            str = "Received an invalid UserCloudFilesResponse. Viewer didn't have a user";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
