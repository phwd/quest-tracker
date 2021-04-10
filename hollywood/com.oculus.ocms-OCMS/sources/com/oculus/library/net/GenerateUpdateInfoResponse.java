package com.oculus.library.net;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONArray;

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

    public static class AssetFileEdge {
        public AssetFileNode node;
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

    public static class AssetFileNode {
        @SerializedName("checksum_hash")
        public String checksumHash;
        public String file_name;
        public String id;
        public boolean isPersistent;
        public boolean is_required;
        public long size;
        public String uri;

        public AssetFileNode(String str, String str2, String str3, String str4, long j, boolean z, boolean z2) {
            this.id = str;
            this.file_name = str2;
            this.uri = str3;
            this.checksumHash = str4;
            this.size = j;
            this.is_required = z;
            this.isPersistent = z2;
        }
    }

    public static class ApplicationUpdateInfo {
        public String app_id;
        @Nullable
        public AndroidBinary new_binary;
        @Nullable
        public Patch patch;

        public boolean hasApkUpdate() {
            return this.new_binary != null;
        }

        public boolean hasObbUpdate() {
            AndroidBinary androidBinary = this.new_binary;
            return (androidBinary == null || androidBinary.obb_binary == null) ? false : true;
        }

        @Nullable
        public String getObbBinaryId() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null || androidBinary.obb_binary == null) {
                return null;
            }
            return this.new_binary.obb_binary.id;
        }

        @Nullable
        public String getApkBinaryId() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary != null) {
                return androidBinary.id;
            }
            return null;
        }

        public boolean hasPatchApkUpdate() {
            return this.patch != null;
        }

        public boolean hasPatchObbUpdate() {
            Patch patch2 = this.patch;
            return (patch2 == null || patch2.obb_patch == null) ? false : true;
        }

        public long getVersionCode() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null) {
                return -1;
            }
            return androidBinary.version_code;
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

        @Nullable
        public String getApkSignature() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null || androidBinary.apk_cert_signature == null) {
                return null;
            }
            return this.new_binary.apk_cert_signature;
        }

        @Nullable
        public String getExternalSignatures() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null || androidBinary.external_signatures == null || this.new_binary.external_signatures.length <= 0) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (String str : this.new_binary.external_signatures) {
                jSONArray.put(str);
            }
            return jSONArray.toString();
        }

        public long getApkSize() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null) {
                return -1;
            }
            return androidBinary.size;
        }

        public long getObbSize() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null || androidBinary.obb_binary == null) {
                return -1;
            }
            return this.new_binary.obb_binary.size;
        }

        public long getApkPatchSize() {
            Patch patch2 = this.patch;
            if (patch2 == null) {
                return -1;
            }
            return patch2.size;
        }

        public long getObbPatchSize() {
            Patch patch2 = this.patch;
            if (patch2 == null || patch2.obb_patch == null) {
                return -1;
            }
            return this.patch.obb_patch.size;
        }

        @Nullable
        public String getApkChecksum() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null) {
                return null;
            }
            return androidBinary.checksum_hash;
        }

        @Nullable
        public String getObbChecksum() {
            AndroidBinary androidBinary = this.new_binary;
            if (androidBinary == null || androidBinary.obb_binary == null) {
                return null;
            }
            return this.new_binary.obb_binary.checksum_hash;
        }

        @Nullable
        public String getApkPatchChecksum() {
            Patch patch2 = this.patch;
            if (patch2 == null) {
                return null;
            }
            return patch2.checksum_hash;
        }

        @Nullable
        public String getObbPatchChecksum() {
            Patch patch2 = this.patch;
            if (patch2 == null || patch2.obb_patch == null) {
                return null;
            }
            return this.patch.obb_patch.checksum_hash;
        }

        public boolean hasAssets() {
            AndroidBinary androidBinary = this.new_binary;
            return (androidBinary == null || androidBinary.asset_files == null || this.new_binary.asset_files.edges == null || this.new_binary.asset_files.edges.length <= 0) ? false : true;
        }

        public List<AssetFileNode> getAssets() {
            ArrayList arrayList = new ArrayList();
            if (!hasAssets()) {
                return arrayList;
            }
            for (AssetFileEdge assetFileEdge : this.new_binary.asset_files.edges) {
                arrayList.add(assetFileEdge.node);
            }
            return arrayList;
        }

        public List<AssetFileNode> getRequiredAssets() {
            ArrayList arrayList = new ArrayList();
            if (!hasAssets()) {
                return arrayList;
            }
            AssetFileEdge[] assetFileEdgeArr = this.new_binary.asset_files.edges;
            for (AssetFileEdge assetFileEdge : assetFileEdgeArr) {
                if (!(assetFileEdge == null || assetFileEdge.node == null || !assetFileEdge.node.is_required)) {
                    arrayList.add(assetFileEdge.node);
                }
            }
            return arrayList;
        }

        public boolean hasAssetPatches() {
            Patch patch2 = this.patch;
            return (patch2 == null || patch2.asset_file_patches == null || this.patch.asset_file_patches.length <= 0) ? false : true;
        }

        public List<AssetFileNode> getAssetPatches() {
            ArrayList arrayList = new ArrayList();
            if (!hasAssetPatches()) {
                return arrayList;
            }
            AssetPatch[] assetPatchArr = this.patch.asset_file_patches;
            for (AssetPatch assetPatch : assetPatchArr) {
                arrayList.add(new AssetFileNode(assetPatch.id, assetPatch.source_binary.file_name, assetPatch.uri, assetPatch.checksum_hash, assetPatch.size, assetPatch.source_binary.is_required, assetPatch.is_persistent));
            }
            return arrayList;
        }
    }
}
