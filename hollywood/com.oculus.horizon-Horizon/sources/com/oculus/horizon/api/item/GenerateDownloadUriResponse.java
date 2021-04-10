package com.oculus.horizon.api.item;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GenerateDownloadUriResponse {
    @Nullable
    public final AndroidBinary new_binary;
    @Nullable
    public final Patch patch;

    public static class AndroidBinary {
        @Nullable
        public final ObbBinary obb_binary;
        public long size;
        @Nullable
        public final String uri;
    }

    public static class ObbBinary {
        public long size;
        @Nullable
        public final String uri;
    }

    public static class ObbPatch {
        public long size;
        @Nullable
        public final String uri;
    }

    public static class Patch {
        @Nullable
        public final ObbPatch obb_patch;
        public long size;
        @Nullable
        public final String uri;
    }

    @Nullable
    public String getApkDownloadUri() {
        AndroidBinary androidBinary = this.new_binary;
        if (androidBinary == null) {
            return null;
        }
        return androidBinary.uri;
    }

    @Nullable
    public String getApkPatchDownloadUri() {
        Patch patch2 = this.patch;
        if (patch2 == null) {
            return null;
        }
        return patch2.uri;
    }

    @Nullable
    public String getObbDownloadUri() {
        ObbBinary obbBinary;
        AndroidBinary androidBinary = this.new_binary;
        if (androidBinary == null || (obbBinary = androidBinary.obb_binary) == null) {
            return null;
        }
        return obbBinary.uri;
    }

    @Nullable
    public String getObbPatchDownloadUri() {
        ObbPatch obbPatch;
        Patch patch2 = this.patch;
        if (patch2 == null || (obbPatch = patch2.obb_patch) == null) {
            return null;
        }
        return obbPatch.uri;
    }
}
