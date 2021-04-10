package com.oculus.horizon.api.cloudstorage2;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class UserCloudFileWildcardsResponse {
    public final UserCloudFileWildcards cloud_file_wildcards;

    public static class UserCloudFileWildcard {
        public final String wildcard;
    }

    public static class UserCloudFileWildcards {
        public final UserCloudFileWildcard[] nodes;
    }
}
