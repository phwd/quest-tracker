package com.oculus.horizon.api.cloudstorage2;

import com.oculus.http.core.base.ValidatableApiResponse;

public class UserCloudFilesResponse implements ValidatableApiResponse {
    public Viewer viewer;

    public static class User {
        public UserCloudFiles cloud_files;
    }

    public static class UserCloudFiles {
        public UserCloudFile[] nodes;
    }

    public static class Viewer {
        public User user;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null) {
            throw new NullPointerException("Received an invalid UserCloudFilesResponse. Response didn't have a viewer");
        } else if (viewer2.user == null) {
            throw new NullPointerException("Received an invalid UserCloudFilesResponse. Viewer didn't have a user");
        }
    }

    public UserCloudFile[] getUserCloudFiles() {
        return this.viewer.user.cloud_files.nodes;
    }
}
