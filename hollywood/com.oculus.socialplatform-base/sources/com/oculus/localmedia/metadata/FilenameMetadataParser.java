package com.oculus.localmedia.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilenameMetadataParser {
    public static final ArrayList<String> TOKENS_180 = new ArrayList<>(Arrays.asList("/180VIDEOS/", "_180.", "_180_", "_180-", "-180.", "-180_", "-180-", "_180X180.", "_180X180_", "_180X180-", "-180X180.", "-180X180_", "-180X180-"));
    public static final ArrayList<String> TOKENS_360 = new ArrayList<>(Arrays.asList("/360VIDEOS/", "/GEAR 360/", "_360.", "_360_", "_360-", "--360.", "-360_", "-360-"));
    public static final ArrayList<String> TOKENS_3D = new ArrayList<>(Arrays.asList("/3D/", "_3D.", "_3D_", "_3D-", "-3D.", "-3D_", "-3D-"));
    public static final ArrayList<String> TOKENS_RIFT = new ArrayList<>(Arrays.asList("-OCULUSRIFT.", "-OCULUSRIFT-", "_SMARTPHONEVR.", "_SMARTPHONEVR_", "_SMARTPHONEVR30.", "_SMARTPHONEVR60.", "_SMARTPHONEVR60_"));
    public static final ArrayList<String> TOKENS_SPHERE = new ArrayList<>(Arrays.asList("_SPHERE_", "_SPHERE.", "_SPHERICAL_", "_SPHERICAL."));
    public static final ArrayList<String> TOKENS_VRDESKTOP = new ArrayList<>(Arrays.asList("_VRDESKTOP.", "_VRDESKTOP_", "_VRDESKTOPHD.", "_VRDESKTOPHD_", "_VRDESKTOPSD.", "_VRDESKTOPSD_"));

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0149, code lost:
        if (r4.indexOf("_3DH.") <= 0) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.localmedia.metadata.FilenameMetadata parse(java.lang.String r7, com.oculus.localmedia.MediaType r8) {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.metadata.FilenameMetadataParser.parse(java.lang.String, com.oculus.localmedia.MediaType):com.oculus.localmedia.metadata.FilenameMetadata");
    }

    public static boolean pathContainsToken(String str, List<String> list) {
        for (String str2 : list) {
            if (str.indexOf(str2) > 0) {
                return true;
            }
        }
        return false;
    }
}
