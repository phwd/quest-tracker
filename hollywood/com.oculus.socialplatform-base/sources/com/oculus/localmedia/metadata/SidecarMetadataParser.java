package com.oculus.localmedia.metadata;

import X.AnonymousClass006;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.localmedia.metadata.SidecarMetadata;
import java.io.File;
import org.json.JSONObject;

public class SidecarMetadataParser {
    public static SidecarMetadata parse(String str) {
        try {
            String readAll = MediaProviderUtils.readAll(new File(AnonymousClass006.A07(str.substring(0, str.lastIndexOf(".")), ".txt")));
            if (readAll != null && !readAll.isEmpty()) {
                JSONObject jSONObject = new JSONObject(readAll);
                SidecarMetadata.Builder builder = new SidecarMetadata.Builder();
                String optString = jSONObject.optString("title", null);
                if (optString != null) {
                    builder.mTitle = optString;
                }
                String optString2 = jSONObject.optString("format", null);
                if (optString2 != null) {
                    if (optString2.equalsIgnoreCase("2d")) {
                        builder.mFormat = "2D";
                    } else if (optString2.equalsIgnoreCase("3d")) {
                        builder.mFormat = "3D";
                    } else if (optString2.equalsIgnoreCase("3dlr")) {
                        builder.mFormat = "3DLR";
                    } else if (optString2.equalsIgnoreCase("3dlrf")) {
                        builder.mFormat = "3DLRF";
                    } else if (optString2.equalsIgnoreCase("3dtb")) {
                        builder.mFormat = "3DTB";
                    } else if (optString2.equalsIgnoreCase("3dtbf")) {
                        builder.mFormat = "3DTBF";
                    }
                }
                return builder.build();
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
