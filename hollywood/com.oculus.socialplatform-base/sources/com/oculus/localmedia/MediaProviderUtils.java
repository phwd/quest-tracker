package com.oculus.localmedia;

import X.AnonymousClass006;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.impl.Base64;
import com.adobe.xmp.impl.XMPMetaParser;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MediaProviderUtils {
    public static final String GAUDIO_NAMESPACE = "http://ns.google.com/photos/1.0/audio/";
    public static final byte[] GPANO_AUDIO_DATA_END_BYTES = "\"".getBytes();
    public static final byte[] GPANO_AUDIO_DATA_START_BYTES = "GAudio:Data=\"".getBytes();
    public static final String GPANO_AUDIO_MIMETYPE_PROPERTY = "GAudio:Mime";
    public static final String GPANO_CROPPED_AREA_WIDTH_PROPERTY = "GPano:CroppedAreaImageWidthPixels";
    public static final String GPANO_FULL_PANO_WIDTH_PROPERTY = "GPano:FullPanoWidthPixels";
    public static final String GPANO_NAMESPACE = "http://ns.google.com/photos/1.0/panorama/";
    public static final String GPANO_PROJECTION_TYPE_PROPERTY = "GPano:ProjectionType";
    public static final int JPEG_APP = 225;
    public static final int JPEG_HEADER = 255;
    public static final String JPEG_MIME_TYPE = "image/jpeg";
    public static final int MIN_IMAGE_SIZE = 1024;
    public static final int MIN_SQUARE_IMAGE_SIZE = 2048;
    public static final String NOMEDIA_FILENAME = ".nomedia";
    public static final Pattern SDCARD_FOLDER_NAME_PATTERN = Pattern.compile("[0-9,a-f,A-F]{4}-[0-9,a-f,A-F]{4}");
    public static final String SUPPORTED_PROJECTION_TYPE = "equirectangular";
    public static final int VR180_FOV_PERCENT_THRESHOLD = 10;
    public static final byte[] XMP_EXTENSION_NAMESPACE_BYTES;
    public static final int XMP_EXTENSION_OFFSET;
    public static final String XMP_NAMESPACE = "http://ns.adobe.com/xap/1.0/";
    public static final ArrayList<ExifChecks> exifList = new ArrayList<>(Arrays.asList(new ExifChecks("Model", "RICOH THETA"), new ExifChecks("Orientation", SUPPORTED_PROJECTION_TYPE), new ExifChecks("Software", "photosynth")));

    public static class XMPExtendedMetadata {
        public List<byte[]> chunks;
        public int totalLength;

        public void addSegment(byte[] bArr) {
            this.chunks.add(bArr);
            this.totalLength += bArr.length;
        }

        public byte[] decode() {
            byte[] bArr = new byte[this.totalLength];
            int i = 0;
            for (byte[] bArr2 : this.chunks) {
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, i, length);
                i += length;
            }
            return Base64.decode(bArr);
        }

        public XMPExtendedMetadata() {
            this.chunks = new ArrayList();
            this.totalLength = 0;
        }
    }

    public static String convertJPEGSegmentToString(InputStream inputStream) {
        byte[] readJPEGSegment = readJPEGSegment(inputStream, null);
        if (readJPEGSegment != null) {
            return new String(readJPEGSegment);
        }
        return null;
    }

    public static byte[] extractAudioFromXMPHeader(String str) {
        byte[] readJPEGSegment;
        XMPExtendedMetadata xMPExtendedMetadata = new XMPExtendedMetadata();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            boolean z = false;
            while (true) {
                int read = bufferedInputStream.read();
                if (read == -1) {
                    break;
                } else if (read == 255 && bufferedInputStream.read() == 225 && (readJPEGSegment = readJPEGSegment(bufferedInputStream, XMP_EXTENSION_NAMESPACE_BYTES)) != null) {
                    if (!z) {
                        byte[] bArr = GPANO_AUDIO_DATA_START_BYTES;
                        int indexOf = indexOf(readJPEGSegment, bArr);
                        if (indexOf > 0) {
                            xMPExtendedMetadata.addSegment(Arrays.copyOfRange(readJPEGSegment, indexOf + bArr.length, readJPEGSegment.length));
                            z = true;
                        }
                    } else {
                        int indexOf2 = indexOf(readJPEGSegment, GPANO_AUDIO_DATA_END_BYTES);
                        if (indexOf2 > 0) {
                            xMPExtendedMetadata.addSegment(Arrays.copyOfRange(readJPEGSegment, XMP_EXTENSION_OFFSET, indexOf2));
                            break;
                        }
                        xMPExtendedMetadata.addSegment(Arrays.copyOfRange(readJPEGSegment, XMP_EXTENSION_OFFSET, readJPEGSegment.length));
                    }
                }
            }
            return xMPExtendedMetadata.decode();
        } catch (Exception e) {
            Log.e(LocalMediaManager.TAG, "getJPEGXMPAudio error ", e);
            return null;
        }
    }

    public static String extractAudioMimeTypeFromXMPHeader(String str) {
        try {
            String jpegxmp = getJPEGXMP(new File(str));
            if (jpegxmp == null) {
                return null;
            }
            return getXMPProperty(XMPMetaParser.parse(jpegxmp, null), GAUDIO_NAMESPACE, GPANO_AUDIO_MIMETYPE_PROPERTY);
        } catch (XMPException unused) {
            Log.e(LocalMediaManager.TAG, AnonymousClass006.A07("XMP parse failure for ", str));
            return null;
        }
    }

    public static int getImageOrientationDegrees(String str, String str2) {
        if (str2 == null || str2.compareTo(JPEG_MIME_TYPE) == 0) {
            try {
                int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
                if (attributeInt == 6) {
                    return 90;
                }
                if (attributeInt == 3) {
                    return 180;
                }
                if (attributeInt == 8) {
                    return 270;
                }
            } catch (Throwable th) {
                Log.e(LocalMediaManager.TAG, "Exif failure when getting orientation:", th);
                return 0;
            }
        }
        return 0;
    }

    public static String getJPEGXMP(File file) {
        String str = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            while (true) {
                try {
                    int read = bufferedInputStream.read();
                    if (read == -1) {
                        break;
                    } else if (read == 255 && bufferedInputStream.read() == 225 && (str = checkJPEGNamespace(bufferedInputStream, "http://ns.adobe.com/xap/1.0/")) != null) {
                        return str;
                    }
                } catch (IOException e) {
                    Log.e(LocalMediaManager.TAG, "getJPEGXMP IOException ", e);
                }
            }
            return str;
        } catch (Exception e2) {
            Log.e(LocalMediaManager.TAG, "getJPEGXMP BufferedInputStream Exception ", e2);
            return null;
        }
    }

    public static int indexOf(byte[] bArr, byte[] bArr2) {
        int i = 0;
        while (true) {
            int length = bArr.length;
            int length2 = bArr2.length;
            if (i >= (length - length2) + 1) {
                return -1;
            }
            for (int i2 = 0; i2 < length2; i2++) {
                if (bArr[i + i2] != bArr2[i2]) {
                    i++;
                }
            }
            return i;
        }
    }

    public static boolean isFile(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        return file.exists() && !file.isDirectory();
    }

    public static boolean isLikelyPano(String str, int i, int i2) {
        if (!(i == 0 || i2 == 0 || isBlacklistedPano(str))) {
            if (i == i2 && i >= 2048) {
                return true;
            }
            if ((i == i2 * 12 || i == i2 * 6) && i2 >= 1024) {
                return true;
            }
            if ((i2 == i * 12 || i2 == i * 6) && i >= 1024) {
                return true;
            }
            return i == (i2 << 2) && i >= 1024;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0030 A[SYNTHETIC, Splitter:B:19:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0035 A[Catch:{ IOException -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x003e A[SYNTHETIC, Splitter:B:31:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0043 A[Catch:{ IOException -> 0x004a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readAll(java.io.File r5) {
        /*
            r4 = 0
            if (r5 == 0) goto L_0x004f
            boolean r0 = r5.exists()
            if (r0 == 0) goto L_0x004f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0039, all -> 0x0029 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0039, all -> 0x0029 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x003c, all -> 0x0027 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x003c, all -> 0x0027 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x003c, all -> 0x0027 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x003c, all -> 0x0027 }
        L_0x001d:
            java.lang.String r0 = r1.readLine()     // Catch:{ Exception -> 0x003b, all -> 0x002c }
            if (r0 == 0) goto L_0x0044
            r3.append(r0)     // Catch:{ Exception -> 0x003b, all -> 0x002c }
            goto L_0x001d
        L_0x0027:
            r0 = move-exception
            goto L_0x002e
        L_0x0029:
            r0 = move-exception
            r2 = r4
            goto L_0x002e
        L_0x002c:
            r0 = move-exception
            r4 = r1
        L_0x002e:
            if (r4 == 0) goto L_0x0033
            r4.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0033:
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            throw r0
        L_0x0039:
            r2 = r4
            goto L_0x003c
        L_0x003b:
            r4 = r1
        L_0x003c:
            if (r4 == 0) goto L_0x0041
            r4.close()     // Catch:{ IOException -> 0x004a }
        L_0x0041:
            if (r2 == 0) goto L_0x004a
            goto L_0x0047
        L_0x0044:
            r1.close()     // Catch:{ IOException -> 0x004a }
        L_0x0047:
            r2.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            java.lang.String r0 = r3.toString()
            return r0
        L_0x004f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaProviderUtils.readAll(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004b A[SYNTHETIC, Splitter:B:25:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0055 A[SYNTHETIC, Splitter:B:33:0x0055] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String saveToCache(android.content.Context r7, byte[] r8, java.lang.String r9) {
        /*
            r6 = 0
            if (r7 == 0) goto L_0x0059
            if (r8 == 0) goto L_0x0059
            if (r9 == 0) goto L_0x0059
            java.io.File r0 = r7.getCacheDir()     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            r5.<init>(r0, r9)     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            boolean r0 = r5.exists()     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            if (r0 != 0) goto L_0x002f
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r0]     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            r2.<init>(r8)     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x003c, all -> 0x0051 }
        L_0x0024:
            int r1 = r2.read(r4)     // Catch:{ Exception -> 0x003a }
            if (r1 <= 0) goto L_0x0030
            r0 = 0
            r3.write(r4, r0, r1)     // Catch:{ Exception -> 0x003a }
            goto L_0x0024
        L_0x002f:
            r3 = r6
        L_0x0030:
            java.lang.String r0 = r5.toString()     // Catch:{ Exception -> 0x003a }
            if (r3 == 0) goto L_0x0039
            r3.close()     // Catch:{ IOException -> 0x0039 }
        L_0x0039:
            return r0
        L_0x003a:
            r2 = move-exception
            goto L_0x003e
        L_0x003c:
            r2 = move-exception
            r3 = r6
        L_0x003e:
            java.lang.String r1 = com.oculus.localmedia.LocalMediaManager.TAG     // Catch:{ all -> 0x004f }
            java.lang.String r0 = "Error saving content:"
            java.lang.String r0 = X.AnonymousClass006.A07(r0, r9)     // Catch:{ all -> 0x004f }
            android.util.Log.e(r1, r0, r2)     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x004e
            r3.close()     // Catch:{ IOException -> 0x004e }
        L_0x004e:
            return r6
        L_0x004f:
            r0 = move-exception
            goto L_0x0053
        L_0x0051:
            r0 = move-exception
            r3 = r6
        L_0x0053:
            if (r3 == 0) goto L_0x0058
            r3.close()     // Catch:{ IOException -> 0x0058 }
        L_0x0058:
            throw r0
        L_0x0059:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaProviderUtils.saveToCache(android.content.Context, byte[], java.lang.String):java.lang.String");
    }

    public static class ExifChecks {
        public String condition;
        public String tag;

        public ExifChecks(String str, String str2) {
            this.tag = str;
            this.condition = str2;
        }
    }

    static {
        byte[] bytes = "http://ns.adobe.com/xmp/extension/".getBytes();
        XMP_EXTENSION_NAMESPACE_BYTES = bytes;
        XMP_EXTENSION_OFFSET = bytes.length + 41;
    }

    public static String extractProjectionFromExifHeader(String str) {
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            int i = 0;
            while (true) {
                ArrayList<ExifChecks> arrayList = exifList;
                if (i >= arrayList.size()) {
                    return null;
                }
                String attribute = exifInterface.getAttribute(arrayList.get(i).tag);
                if (attribute != null && attribute.equalsIgnoreCase(arrayList.get(i).condition)) {
                    return "EQUIRECTANGULAR";
                }
                i++;
            }
        } catch (Exception e) {
            Log.e(LocalMediaManager.TAG, AnonymousClass006.A0B("ExtractExif failure for ", str, " because ", e.getMessage()));
            return null;
        }
    }

    public static String extractProjectionFromXMPHeader(String str) {
        try {
            String jpegxmp = getJPEGXMP(new File(str));
            if (jpegxmp != null) {
                XMPMeta parse = XMPMetaParser.parse(jpegxmp, null);
                if (!SUPPORTED_PROJECTION_TYPE.equalsIgnoreCase(getXMPProperty(parse, GPANO_NAMESPACE, GPANO_PROJECTION_TYPE_PROPERTY))) {
                    String xMPProperty = getXMPProperty(parse, GPANO_NAMESPACE, GPANO_CROPPED_AREA_WIDTH_PROPERTY);
                    String xMPProperty2 = getXMPProperty(parse, GPANO_NAMESPACE, GPANO_FULL_PANO_WIDTH_PROPERTY);
                    if (!(xMPProperty == null || xMPProperty2 == null)) {
                        int intValue = Integer.valueOf(xMPProperty, 10).intValue();
                        int intValue2 = Integer.valueOf(xMPProperty2, 10).intValue();
                        if (intValue > 0 && intValue2 > 0) {
                            if (intValue != intValue2) {
                                if (Math.abs(((intValue2 - (intValue << 1)) * 100) / intValue2) < 10) {
                                    return "VR180";
                                }
                            }
                        }
                    }
                }
                return "EQUIRECTANGULAR";
            }
        } catch (XMPException unused) {
            Log.e(LocalMediaManager.TAG, AnonymousClass006.A07("XMP parse failure for ", str));
        }
        return null;
    }

    public static String getExternalStorageDirectory() {
        File[] listFiles = new File(TabletDeepLinkingUriUtil.SETTINGS_STORAGE_URI).listFiles();
        if (listFiles == null) {
            return null;
        }
        for (File file : listFiles) {
            if (file.isDirectory()) {
                String name = file.getName();
                if (name.length() == 9 && SDCARD_FOLDER_NAME_PATTERN.matcher(name).matches()) {
                    return file.getAbsolutePath();
                }
            }
        }
        return null;
    }

    public static String getFileExtension(String str, boolean z) {
        if (str == null || str.indexOf(46) <= 0) {
            return "";
        }
        return str.substring(str.lastIndexOf(".") + (!z ? 1 : 0), str.length()).toLowerCase();
    }

    public static String getFileFullExtension(String str, boolean z) {
        if (str == null || str.indexOf(46) <= 0) {
            return "";
        }
        return str.substring(str.indexOf(".") + (!z ? 1 : 0), str.length()).toLowerCase();
    }

    public static String getFilename(String str) {
        int length;
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf <= 0 || lastIndexOf >= (length = str.length())) {
            return str;
        }
        return str.substring(lastIndexOf + 1, length);
    }

    public static String getFolderPath(String str) {
        File parentFile = new File(str).getParentFile();
        if (parentFile != null) {
            return parentFile.getAbsolutePath();
        }
        return null;
    }

    public static ArrayList<String> getHiddenFolders(Context context) {
        String folderPath;
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_data"}, AnonymousClass006.A0B("media_type=0", " AND ", "title", " LIKE ?"), new String[]{"%.nomedia%"}, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("_data"));
            if (!(string == null || (folderPath = getFolderPath(string)) == null)) {
                arrayList.add(folderPath);
            }
        }
        query.close();
        return arrayList;
    }

    public static String getXMPProperty(XMPMeta xMPMeta, String str, String str2) throws XMPException {
        if (xMPMeta == null || !xMPMeta.doesPropertyExist(str, str2)) {
            return null;
        }
        return xMPMeta.getProperty(str, str2).toString();
    }

    public static boolean isPrivateFolder(String str) {
        if (str == null || !new File(AnonymousClass006.A09(str, File.separator, NOMEDIA_FILENAME)).exists()) {
            return false;
        }
        return true;
    }

    public static byte[] readJPEGSegment(InputStream inputStream, byte[] bArr) {
        byte[] bArr2;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            if (readUnsignedShort > 2) {
                bArr2 = new byte[(readUnsignedShort - 2)];
                try {
                    dataInputStream.read(bArr2);
                } catch (IOException e) {
                    Log.e(LocalMediaManager.TAG, "convertJPEGSegmentToString error : ", e);
                }
                if (bArr != null) {
                    for (int i = 0; i < bArr.length; i++) {
                        if (bArr[i] != bArr2[i]) {
                            Log.e(LocalMediaManager.TAG, AnonymousClass006.A03("Namespace not matching at index ", i));
                        }
                    }
                }
                return bArr2;
            }
        } catch (IOException e2) {
            Log.e(LocalMediaManager.TAG, "convertJPEGSegmentToString error : ", e2);
            bArr2 = null;
        }
        return null;
    }

    public static String removeFileExtension(String str) {
        if (str == null || str.indexOf(46) <= 0) {
            return str;
        }
        return str.substring(0, str.lastIndexOf("."));
    }

    public static String checkJPEGNamespace(InputStream inputStream, String str) {
        String convertJPEGSegmentToString = convertJPEGSegmentToString(inputStream);
        if (convertJPEGSegmentToString == null || !convertJPEGSegmentToString.startsWith(str)) {
            return null;
        }
        return convertJPEGSegmentToString.substring(str.length() + 1);
    }

    public static boolean hasExternalStorage() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isBlacklistedPano(String str) {
        return str.toLowerCase().contains("oculus/screenshots");
    }

    public static boolean isPrivateFile(String str) {
        return isPrivateFolder(getFolderPath(str));
    }

    public static boolean isWhitelistedPano(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.contains("oculus/360photos/") || lowerCase.endsWith(".vr.jpg")) {
            return true;
        }
        return false;
    }

    public static boolean shouldTreatImageAsPano(String str, int i, int i2) {
        if (isWhitelistedPano(str) || isLikelyPano(str, i, i2)) {
            return true;
        }
        return false;
    }
}
