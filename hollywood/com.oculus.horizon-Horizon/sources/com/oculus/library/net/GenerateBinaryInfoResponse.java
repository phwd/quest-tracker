package com.oculus.library.net;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GenerateBinaryInfoResponse {
    public AppBinaryInfoList app_binary_info;

    public static class AndroidBinary {
        public AssetFiles asset_files;
        public ObbBinary obb_binary;
    }

    public static class AppBinaryInfo {
        @Nullable
        public AndroidBinary binary;
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
}
