package com.oculus.library.net;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GenerateUpdateInfoResponse {
    public static final long INVALID_SIZE = -1;
    public static final long INVALID_VERSION_CODE = -1;
    public AppBinaryUpdates app_binary_updates;

    public static class AndroidBinary extends UpdateBinary {
        @Nullable
        public String apk_cert_signature;
        @Nullable
        public AssetFiles asset_files;
        @Nullable
        public String[] external_signatures;
        @Nullable
        public UpdateBinary obb_binary;
        public long version_code;
    }

    public static class AppBinaryUpdates {
        public ApplicationUpdateInfo[] updates;
    }

    public static class ApplicationUpdateInfo {
        public String app_id;
        @Nullable
        public AndroidBinary new_binary;
        @Nullable
        public Patch patch;
    }

    public static class AssetFileEdge {
        public AssetFileNode node;
    }

    public static class AssetFileNode {
        @SerializedName("checksum_hash")
        public String checksumHash;
        public String file_name;
        public String id;
        public boolean isPersistent;
        public boolean is_required;
        public long size;
        public String uri;
    }

    public static class AssetFiles {
        @Nullable
        public AssetFileEdge[] edges;
    }

    public static class AssetPatch extends UpdateBinary {
        public boolean is_persistent;
        public BinarySource source_binary;
    }

    public static class BinarySource {
        public String file_name;
        public boolean is_required;
    }

    public static class Patch extends UpdateBinary {
        @Nullable
        public AssetPatch[] asset_file_patches;
        @Nullable
        public UpdateBinary obb_patch;
    }

    public static class UpdateBinary {
        public String checksum_hash;
        public String id;
        public long size;
        @Nullable
        public String uri;
    }
}
