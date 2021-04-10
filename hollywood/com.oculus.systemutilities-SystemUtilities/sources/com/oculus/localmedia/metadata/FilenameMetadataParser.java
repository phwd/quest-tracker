package com.oculus.localmedia.metadata;

import com.oculus.localmedia.MediaType;
import com.oculus.localmedia.metadata.FilenameMetadata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FilenameMetadataParser {
    private static final ArrayList<String> TOKENS_180 = new ArrayList<>(Arrays.asList("/180VIDEOS/", "_180.", "_180_", "_180-", "-180.", "-180_", "-180-", "_180X180.", "_180X180_", "_180X180-", "-180X180.", "-180X180_", "-180X180-"));
    private static final ArrayList<String> TOKENS_360 = new ArrayList<>(Arrays.asList("/360VIDEOS/", "/GEAR 360/", "_360.", "_360_", "_360-", "--360.", "-360_", "-360-"));
    private static final ArrayList<String> TOKENS_3D = new ArrayList<>(Arrays.asList("/3D/", "_3D.", "_3D_", "_3D-", "-3D.", "-3D_", "-3D-"));
    private static final ArrayList<String> TOKENS_RIFT = new ArrayList<>(Arrays.asList("-OCULUSRIFT.", "-OCULUSRIFT-", "_SMARTPHONEVR.", "_SMARTPHONEVR_", "_SMARTPHONEVR30.", "_SMARTPHONEVR60.", "_SMARTPHONEVR60_"));
    private static final ArrayList<String> TOKENS_SPHERE = new ArrayList<>(Arrays.asList("_SPHERE_", "_SPHERE.", "_SPHERICAL_", "_SPHERICAL."));
    private static final ArrayList<String> TOKENS_VRDESKTOP = new ArrayList<>(Arrays.asList("_VRDESKTOP.", "_VRDESKTOP_", "_VRDESKTOPHD.", "_VRDESKTOPHD_", "_VRDESKTOPSD.", "_VRDESKTOPSD_"));

    public static FilenameMetadata parse(String path, MediaType type) {
        FilenameMetadata.Builder builder = FilenameMetadata.builder();
        String title = new StringTokenizer(path.substring(path.lastIndexOf(47) + 1), "?#").nextToken();
        int index = title.lastIndexOf(".");
        boolean isCardboardMedia = false;
        if (index > 0) {
            title = title.substring(0, index);
            if (title.endsWith(".vr")) {
                title = title.substring(0, title.length() - 3);
                isCardboardMedia = true;
            }
        }
        builder.setTitle(title);
        builder.setIsCardboard(isCardboardMedia);
        if (type == MediaType.VIDEO) {
            String filePath = path.toUpperCase();
            if (!filePath.contains(".")) {
                filePath = filePath + ".ext";
            }
            if (pathContainsToken(filePath, TOKENS_3D)) {
                builder.setFormat("3D");
            }
            if (pathContainsToken(filePath, TOKENS_360)) {
                builder.setPresentationFormat("SPHERICAL");
            } else if (pathContainsToken(filePath, TOKENS_180)) {
                builder.setPresentationFormat("SPHERICAL");
                builder.setFovX(180.0f);
            }
            if (filePath.indexOf("/VR/") > 0) {
                builder.setFormat("3D");
                builder.setPresentationFormat("SPHERICAL");
            } else if (filePath.indexOf("/180VR/") > 0) {
                builder.setFormat("3D");
                builder.setPresentationFormat("SPHERICAL");
                builder.setFovX(180.0f);
            }
            if (pathContainsToken(filePath, TOKENS_SPHERE)) {
                builder.setPresentationFormat("SPHERICAL");
            } else if (filePath.indexOf("_CUBEMAP_") > 0 || filePath.indexOf("_CUBEMAP.") > 0) {
                builder.setPresentationFormat("UNPADDED_CUBEMAP_32");
            } else if (filePath.indexOf("_BARREL_") > 0 || filePath.indexOf("_BARREL.") > 0) {
                builder.setPresentationFormat("BARREL");
            }
            if (isCardboardMedia) {
                builder.setPresentationFormat("VR180");
                builder.setFovX(180.0f);
            }
            if (pathContainsToken(filePath, TOKENS_RIFT)) {
                builder.setFovX(180.0f);
                builder.setFormat("3DLR");
            } else if (pathContainsToken(filePath, TOKENS_VRDESKTOP)) {
                builder.setFovX(180.0f);
                builder.setFormat("3DLR");
            } else if (filePath.indexOf("_TB_") > 0 || filePath.indexOf("_TB.") > 0) {
                builder.setFormat("3DTB");
            } else if (filePath.indexOf("_BT_") > 0 || filePath.indexOf("_BT.") > 0) {
                builder.setFormat("3DBT");
            } else if (filePath.indexOf("_TBF_") > 0 || filePath.indexOf("_TBF.") > 0) {
                builder.setFormat("3DTBF");
            } else if (filePath.indexOf("_BTF_") > 0 || filePath.indexOf("_BTF.") > 0) {
                builder.setFormat("3DBTF");
            } else if (filePath.indexOf("_LR_") > 0 || filePath.indexOf("_LR.") > 0) {
                builder.setFormat("3DLR");
            } else if (filePath.indexOf("_RL_") > 0 || filePath.indexOf("_RL.") > 0) {
                builder.setFormat("3DRL");
            } else if (filePath.indexOf("_LRF_") > 0 || filePath.indexOf("_LRF.") > 0) {
                builder.setFormat("3DLRF");
            } else if (filePath.indexOf("_RLF_") > 0 || filePath.indexOf("_RLF.") > 0) {
                builder.setFormat("3DRLF");
            } else if (filePath.indexOf("_3DV_") > 0 || filePath.indexOf("_3DV.") > 0) {
                builder.setFormat("3DTB");
            } else if (filePath.indexOf("_3DH_") > 0 || filePath.indexOf("_3DH.") > 0) {
                builder.setFormat("3DLR");
            }
        } else if (isCardboardMedia) {
            builder.setFormat("3DTB");
            builder.setPresentationFormat("SPHERICAL");
        }
        return builder.build();
    }

    private static boolean pathContainsToken(String path, List<String> tokens) {
        for (String token : tokens) {
            if (path.indexOf(token) > 0) {
                return true;
            }
        }
        return false;
    }
}
