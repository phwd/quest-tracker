package com.oculus.horizon.api.item;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GenerateDownloadUriResponse {
    @Nullable
    public AndroidBinary new_binary;
    @Nullable
    public Patch patch;

    public static class AndroidBinary {
        @Nullable
        public ObbBinary obb_binary;
        public long size;
        @Nullable
        public String uri;
    }

    public static class ObbBinary {
        public long size;
        @Nullable
        public String uri;
    }

    public static class ObbPatch {
        public long size;
        @Nullable
        public String uri;
    }

    public static class Patch {
        @Nullable
        public ObbPatch obb_patch;
        public long size;
        @Nullable
        public String uri;
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
    public String getObbDownloadUri() {
        AndroidBinary androidBinary = this.new_binary;
        if (androidBinary == null || androidBinary.obb_binary == null) {
            return null;
        }
        return this.new_binary.obb_binary.uri;
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
    public String getObbPatchDownloadUri() {
        Patch patch2 = this.patch;
        if (patch2 == null || patch2.obb_patch == null) {
            return null;
        }
        return this.patch.obb_patch.uri;
    }
}
