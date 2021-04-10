package com.oculus.library.net;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GenerateBinaryInfoResponse {
    public AppBinaryInfoList app_binary_info;

    public static class AndroidBinary {
        public AssetFiles asset_files;
        public ObbBinary obb_binary;
    }

    public static class AppBinaryInfoList {
        public AppBinaryInfo[] info;
    }

    public static class AssetFileEdge {
        public AssetFileNode node;
    }

    public static class AssetFileNode extends ObbBinary {
        public boolean is_required;
    }

    public static class AssetFiles {
        @Nullable
        public AssetFileEdge[] edges;
    }

    public static class ObbBinary {
        public String checksum_hash;
        public String file_name;
    }

    public static class AppBinaryInfo {
        @Nullable
        public AndroidBinary binary;

        /* access modifiers changed from: package-private */
        public boolean hasObb() {
            AndroidBinary androidBinary = this.binary;
            return (androidBinary == null || androidBinary.obb_binary == null) ? false : true;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAssets() {
            AndroidBinary androidBinary = this.binary;
            return (androidBinary == null || androidBinary.asset_files == null || this.binary.asset_files.edges == null || this.binary.asset_files.edges.length <= 0) ? false : true;
        }
    }

    public boolean hasAssets() {
        AppBinaryInfoList appBinaryInfoList = this.app_binary_info;
        if (appBinaryInfoList == null || appBinaryInfoList.info == null || this.app_binary_info.info[0] == null || !this.app_binary_info.info[0].hasAssets()) {
            return false;
        }
        return true;
    }

    public boolean hasObb() {
        AppBinaryInfoList appBinaryInfoList = this.app_binary_info;
        if (appBinaryInfoList == null || appBinaryInfoList.info == null || this.app_binary_info.info[0] == null || !this.app_binary_info.info[0].hasObb()) {
            return false;
        }
        return true;
    }

    public List<AssetFileNode> getAssets() {
        ArrayList arrayList = new ArrayList();
        if (!hasAssets()) {
            return arrayList;
        }
        for (AssetFileEdge assetFileEdge : this.app_binary_info.info[0].binary.asset_files.edges) {
            arrayList.add(assetFileEdge.node);
        }
        return arrayList;
    }

    @Nullable
    public ObbBinary getObb() {
        if (!hasObb()) {
            return null;
        }
        return this.app_binary_info.info[0].binary.obb_binary;
    }
}
