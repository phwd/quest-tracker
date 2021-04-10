package com.oculus.localmedia.metadata;

import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.localmedia.metadata.SidecarMetadata;
import java.io.File;
import org.json.JSONObject;

public class SidecarMetadataParser {
    public static SidecarMetadata parse(String filePath) {
        try {
            String json = MediaProviderUtils.readAll(new File(filePath.substring(0, filePath.lastIndexOf(".")) + ".txt"));
            if (json == null || json.isEmpty()) {
                return null;
            }
            JSONObject sidecarJson = new JSONObject(json);
            SidecarMetadata.Builder builder = SidecarMetadata.builder();
            String title = sidecarJson.optString("title", null);
            if (title != null) {
                builder.setTitle(title);
            }
            String format = sidecarJson.optString("format", null);
            if (format != null) {
                if (format.equalsIgnoreCase("2d")) {
                    builder.setFormat("2D");
                } else if (format.equalsIgnoreCase("3d")) {
                    builder.setFormat("3D");
                } else if (format.equalsIgnoreCase("3dlr")) {
                    builder.setFormat("3DLR");
                } else if (format.equalsIgnoreCase("3dlrf")) {
                    builder.setFormat("3DLRF");
                } else if (format.equalsIgnoreCase("3dtb")) {
                    builder.setFormat("3DTB");
                } else if (format.equalsIgnoreCase("3dtbf")) {
                    builder.setFormat("3DTBF");
                }
            }
            return builder.build();
        } catch (Exception e) {
            return null;
        }
    }
}
