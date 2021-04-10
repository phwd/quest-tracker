package com.oculus.localmedia.metadata;

import com.oculus.localmedia.LocalMediaManager;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.localmedia.metadata.ImageHeaderMetadata;

public class ImageHeaderMetadataParser {
    public static ImageHeaderMetadata parse(String filePath, String mimeType, int width, int height) {
        boolean z = true;
        if (!"image/jpeg".equalsIgnoreCase(mimeType)) {
            return null;
        }
        boolean is3D = false;
        boolean notEquirect = false;
        if (!(width == 0 && height == 0)) {
            is3D = width == height;
            if (!is3D && width != height * 2) {
                notEquirect = true;
            }
            if (height <= 1024) {
                notEquirect = true;
            }
        }
        if (notEquirect && !LocalMediaManager.isStandaloneDevice()) {
            return null;
        }
        String audioMimeType = null;
        String projection = MediaProviderUtils.extractProjectionFromXMPHeader(filePath);
        if (projection == null) {
            projection = MediaProviderUtils.extractProjectionFromExifHeader(filePath);
        } else {
            audioMimeType = MediaProviderUtils.extractAudioMimeTypeFromXMPHeader(filePath);
        }
        if (projection != null) {
            int degrees = MediaProviderUtils.getImageOrientationDegrees(filePath, mimeType);
            if ("EQUIRECTANGULAR".equalsIgnoreCase(projection) || "VR180".equalsIgnoreCase(projection)) {
                ImageHeaderMetadata.Builder orientation = ImageHeaderMetadata.builder().setProjectionType(projection).setFormat("EQUIRECTANGULAR".equalsIgnoreCase(projection) ? is3D ? "3DTB" : "2D" : "3DLR").setOrientation(degrees);
                if (audioMimeType == null || audioMimeType.isEmpty()) {
                    z = false;
                }
                return orientation.setHasAudio(z).build();
            }
        }
        return null;
    }
}
