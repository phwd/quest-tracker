package com.oculus.horizon.api.cloudstorage2;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class UserCloudFileWildcardsResponse {
    public UserCloudFileWildcards cloud_file_wildcards;

    public static class UserCloudFileWildcard {
        public String wildcard;
    }

    public static class UserCloudFileWildcards {
        public UserCloudFileWildcard[] nodes;
    }
}
