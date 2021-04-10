package com.oculus.localmedia.metadata;

public class ImageHeaderMetadataParser {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r11 <= 1024) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r1 == false) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (com.oculus.localmedia.LocalMediaManager.isStandaloneDevice() != false) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r4 = com.oculus.localmedia.MediaProviderUtils.extractProjectionFromXMPHeader(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r4 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        r4 = com.oculus.localmedia.MediaProviderUtils.extractProjectionFromExifHeader(r8);
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        if (r4 == null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0030, code lost:
        r2 = com.oculus.localmedia.MediaProviderUtils.getImageOrientationDegrees(r8, r9);
        r1 = "EQUIRECTANGULAR".equalsIgnoreCase(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        if (r1 != false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if ("VR180".equalsIgnoreCase(r4) == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        if (r1 == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
        if (r6 == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        r0 = "3DTB";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004a, code lost:
        r1 = new com.oculus.localmedia.metadata.ImageHeaderMetadata.Builder();
        r1.mProjectionType = r4;
        r1.mFormat = r0;
        r1.mOrientationDegrees = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r3 == null) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005b, code lost:
        if (r3.isEmpty() != false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005d, code lost:
        r1.mHasAudio = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        return r1.build();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0066, code lost:
        r0 = "2D";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0069, code lost:
        r0 = "3DLR";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006c, code lost:
        r3 = com.oculus.localmedia.MediaProviderUtils.extractAudioMimeTypeFromXMPHeader(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0072, code lost:
        if (r10 != r11) goto L_0x000f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r11 != 0) goto L_0x000f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        if (r10 == (r11 << 1)) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        r1 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.localmedia.metadata.ImageHeaderMetadata parse(java.lang.String r8, java.lang.String r9, int r10, int r11) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.metadata.ImageHeaderMetadataParser.parse(java.lang.String, java.lang.String, int, int):com.oculus.localmedia.metadata.ImageHeaderMetadata");
    }
}
