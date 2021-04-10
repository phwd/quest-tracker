package com.oculus.localmedia;

import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.Base64;
import com.oculus.provider.OculusContent;
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
    private static final String GAUDIO_NAMESPACE = "http://ns.google.com/photos/1.0/audio/";
    private static final byte[] GPANO_AUDIO_DATA_END_BYTES = "\"".getBytes();
    private static final byte[] GPANO_AUDIO_DATA_START_BYTES = "GAudio:Data=\"".getBytes();
    private static final String GPANO_AUDIO_MIMETYPE_PROPERTY = "GAudio:Mime";
    private static final String GPANO_CROPPED_AREA_WIDTH_PROPERTY = "GPano:CroppedAreaImageWidthPixels";
    private static final String GPANO_FULL_PANO_WIDTH_PROPERTY = "GPano:FullPanoWidthPixels";
    private static final String GPANO_NAMESPACE = "http://ns.google.com/photos/1.0/panorama/";
    private static final String GPANO_PROJECTION_TYPE_PROPERTY = "GPano:ProjectionType";
    private static final int JPEG_APP = 225;
    private static final int JPEG_HEADER = 255;
    public static final String JPEG_MIME_TYPE = "image/jpeg";
    public static final int MIN_IMAGE_SIZE = 1024;
    public static final int MIN_SQUARE_IMAGE_SIZE = 2048;
    public static final String NOMEDIA_FILENAME = ".nomedia";
    private static final Pattern SDCARD_FOLDER_NAME_PATTERN = Pattern.compile("[0-9,a-f,A-F]{4}-[0-9,a-f,A-F]{4}");
    private static final String SUPPORTED_PROJECTION_TYPE = "equirectangular";
    public static final int VR180_FOV_PERCENT_THRESHOLD = 10;
    private static final byte[] XMP_EXTENSION_NAMESPACE_BYTES = "http://ns.adobe.com/xmp/extension/".getBytes();
    private static final int XMP_EXTENSION_OFFSET = (XMP_EXTENSION_NAMESPACE_BYTES.length + 41);
    private static final String XMP_NAMESPACE = "http://ns.adobe.com/xap/1.0/";
    private static final ArrayList<ExifChecks> exifList = new ArrayList<>(Arrays.asList(new ExifChecks("Model", "RICOH THETA"), new ExifChecks("Orientation", SUPPORTED_PROJECTION_TYPE), new ExifChecks("Software", "photosynth")));

    private static class ExifChecks {
        private String condition;
        private String tag;

        public ExifChecks(String tag2, String condition2) {
            this.tag = tag2;
            this.condition = condition2;
        }
    }

    /* access modifiers changed from: private */
    public static class XMPExtendedMetadata {
        private List<byte[]> chunks;
        private int totalLength;

        private XMPExtendedMetadata() {
            this.chunks = new ArrayList();
            this.totalLength = 0;
        }

        public void addSegment(byte[] segment) {
            this.chunks.add(segment);
            this.totalLength += segment.length;
        }

        public byte[] decode() {
            int position = 0;
            byte[] full = new byte[this.totalLength];
            for (byte[] chunk : this.chunks) {
                System.arraycopy(chunk, 0, full, position, chunk.length);
                position += chunk.length;
            }
            return Base64.decode(full);
        }
    }

    public static boolean isFile(String filepath) {
        if (filepath == null) {
            return false;
        }
        File file = new File(filepath);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        return true;
    }

    public static boolean isPrivateFile(String path) {
        return isPrivateFolder(getFolderPath(path));
    }

    public static boolean isPrivateFolder(String path) {
        if (path == null || !new File(path + File.separator + NOMEDIA_FILENAME).exists()) {
            return false;
        }
        return true;
    }

    public static String getFileExtension(String filepath, boolean includeDot) {
        if (filepath == null || filepath.indexOf(46) <= 0) {
            return "";
        }
        return filepath.substring(filepath.lastIndexOf(".") + (includeDot ? 0 : 1), filepath.length()).toLowerCase();
    }

    public static String getFileFullExtension(String filepath, boolean includeDot) {
        if (filepath == null || filepath.indexOf(46) <= 0) {
            return "";
        }
        return filepath.substring(filepath.indexOf(".") + (includeDot ? 0 : 1), filepath.length()).toLowerCase();
    }

    public static String removeFileExtension(String filepath) {
        if (filepath == null || filepath.indexOf(46) <= 0) {
            return filepath;
        }
        return filepath.substring(0, filepath.lastIndexOf("."));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d A[SYNTHETIC, Splitter:B:19:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063 A[SYNTHETIC, Splitter:B:30:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String saveToCache(android.content.Context r11, byte[] r12, java.lang.String r13) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaProviderUtils.saveToCache(android.content.Context, byte[], java.lang.String):java.lang.String");
    }

    public static boolean shouldTreatImageAsPano(String filePath, int imageWidth, int imageHeight) {
        if (isWhitelistedPano(filePath) || isLikelyPano(filePath, imageWidth, imageHeight)) {
            return true;
        }
        return false;
    }

    private static boolean isWhitelistedPano(String filePath) {
        String path = filePath.toLowerCase();
        return path.contains("oculus/360photos/") || path.endsWith(".vr.jpg");
    }

    private static boolean isBlacklistedPano(String filePath) {
        return filePath.toLowerCase().contains("oculus/screenshots");
    }

    private static boolean isLikelyPano(String filePath, int width, int height) {
        if (width == 0 || height == 0 || isBlacklistedPano(filePath)) {
            return false;
        }
        if (width == height && width >= 2048) {
            return true;
        }
        if ((width == height * 12 || width == height * 6) && height >= 1024) {
            return true;
        }
        if ((height == width * 12 || height == width * 6) && width >= 1024) {
            return true;
        }
        return width == height * 4 && width >= 1024;
    }

    public static String getFolderPath(String filepath) {
        File parent = new File(filepath).getParentFile();
        if (parent != null) {
            return parent.getAbsolutePath();
        }
        return null;
    }

    public static String getFilename(String filepath) {
        int index = filepath.lastIndexOf(47);
        if (index <= 0 || index >= filepath.length()) {
            return filepath;
        }
        return filepath.substring(index + 1, filepath.length());
    }

    public static ArrayList<String> getHiddenFolders(Context context) {
        String folderPath;
        ArrayList<String> hiddenFolders = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_data"}, "media_type=0" + " AND " + OculusContent.FriendList.ACTIVITY_TITLE_COLUMN + " LIKE ?", new String[]{"%.nomedia%"}, null);
        while (cursor.moveToNext()) {
            String hiddenFilePath = cursor.getString(cursor.getColumnIndex("_data"));
            if (!(hiddenFilePath == null || (folderPath = getFolderPath(hiddenFilePath)) == null)) {
                hiddenFolders.add(folderPath);
            }
        }
        cursor.close();
        return hiddenFolders;
    }

    public static String extractProjectionFromXMPHeader(String filePath) {
        try {
            String xmpString = getJPEGXMP(new File(filePath));
            if (xmpString == null) {
                return null;
            }
            XMPMeta metaData = XMPMetaFactory.parseFromString(xmpString);
            if (SUPPORTED_PROJECTION_TYPE.equalsIgnoreCase(getXMPProperty(metaData, GPANO_NAMESPACE, GPANO_PROJECTION_TYPE_PROPERTY))) {
                return "EQUIRECTANGULAR";
            }
            String croppedWidthStr = getXMPProperty(metaData, GPANO_NAMESPACE, GPANO_CROPPED_AREA_WIDTH_PROPERTY);
            String panoWidthStr = getXMPProperty(metaData, GPANO_NAMESPACE, GPANO_FULL_PANO_WIDTH_PROPERTY);
            if (croppedWidthStr == null || panoWidthStr == null) {
                return null;
            }
            int croppedWidth = Integer.valueOf(croppedWidthStr, 10).intValue();
            int panoWidth = Integer.valueOf(panoWidthStr, 10).intValue();
            if (croppedWidth <= 0 || panoWidth <= 0) {
                return null;
            }
            if (croppedWidth == panoWidth) {
                return "EQUIRECTANGULAR";
            }
            if (Math.abs(((panoWidth - (croppedWidth * 2)) * 100) / panoWidth) < 10) {
                return "VR180";
            }
            return null;
        } catch (XMPException e) {
            Log.e(LocalMediaManager.TAG, "XMP parse failure for " + filePath);
            return null;
        }
    }

    public static String extractAudioMimeTypeFromXMPHeader(String filePath) {
        try {
            String xmpString = getJPEGXMP(new File(filePath));
            if (xmpString == null) {
                return null;
            }
            return getXMPProperty(XMPMetaFactory.parseFromString(xmpString), GAUDIO_NAMESPACE, GPANO_AUDIO_MIMETYPE_PROPERTY);
        } catch (XMPException e) {
            Log.e(LocalMediaManager.TAG, "XMP parse failure for " + filePath);
            return null;
        }
    }

    private static String getXMPProperty(XMPMeta metaData, String namespace, String propertyName) throws XMPException {
        if (metaData == null || !metaData.doesPropertyExist(namespace, propertyName)) {
            return null;
        }
        return metaData.getProperty(namespace, propertyName).toString();
    }

    public static byte[] extractAudioFromXMPHeader(String filePath) {
        byte[] segment;
        boolean started = false;
        XMPExtendedMetadata data = new XMPExtendedMetadata();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            while (true) {
                int offSet = inputStream.read();
                if (offSet == -1) {
                    break;
                } else if (offSet == JPEG_HEADER && inputStream.read() == JPEG_APP && (segment = readJPEGSegment(inputStream, XMP_EXTENSION_NAMESPACE_BYTES)) != null) {
                    if (!started) {
                        int start = indexOf(segment, GPANO_AUDIO_DATA_START_BYTES);
                        if (start > 0) {
                            data.addSegment(Arrays.copyOfRange(segment, GPANO_AUDIO_DATA_START_BYTES.length + start, segment.length));
                            started = true;
                        }
                    } else {
                        int endIndex = indexOf(segment, GPANO_AUDIO_DATA_END_BYTES);
                        if (endIndex > 0) {
                            data.addSegment(Arrays.copyOfRange(segment, XMP_EXTENSION_OFFSET, endIndex));
                            break;
                        }
                        data.addSegment(Arrays.copyOfRange(segment, XMP_EXTENSION_OFFSET, segment.length));
                    }
                }
            }
            return data.decode();
        } catch (Exception e) {
            Log.e(LocalMediaManager.TAG, "getJPEGXMPAudio error ", e);
            return null;
        }
    }

    public static String extractProjectionFromExifHeader(String file) {
        try {
            ExifInterface exif = new ExifInterface(file);
            for (int i = 0; i < exifList.size(); i++) {
                String attribute = exif.getAttribute(exifList.get(i).tag);
                if (attribute != null && attribute.equalsIgnoreCase(exifList.get(i).condition)) {
                    return "EQUIRECTANGULAR";
                }
            }
        } catch (Exception e) {
            Log.e(LocalMediaManager.TAG, "ExtractExif failure for " + file + " because " + e.getMessage());
        }
        return null;
    }

    public static int getImageOrientationDegrees(String filePath, String mimeType) {
        if (mimeType != null && mimeType.compareTo(JPEG_MIME_TYPE) != 0) {
            return 0;
        }
        try {
            int orientation = new ExifInterface(filePath).getAttributeInt("Orientation", 1);
            if (orientation == 6) {
                return 90;
            }
            if (orientation == 3) {
                return 180;
            }
            if (orientation == 8) {
                return 270;
            }
            return 0;
        } catch (Throwable throwable) {
            Log.e(LocalMediaManager.TAG, "Exif failure when getting orientation:", throwable);
            return 0;
        }
    }

    public static boolean hasExternalStorage() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getExternalStorageDirectory() {
        File[] fileList = new File("/storage").listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isDirectory()) {
                    String name = file.getName();
                    if (name.length() == 9 && SDCARD_FOLDER_NAME_PATTERN.matcher(name).matches()) {
                        return file.getAbsolutePath();
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f A[SYNTHETIC, Splitter:B:16:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034 A[Catch:{ IOException -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0050 A[SYNTHETIC, Splitter:B:31:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0055 A[Catch:{ IOException -> 0x0059 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readAll(java.io.File r8) {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaProviderUtils.readAll(java.io.File):java.lang.String");
    }

    private static String getJPEGXMP(File inputFile) {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
            String result = null;
            while (true) {
                try {
                    int offSet = inputStream.read();
                    if (offSet == -1 || (offSet == JPEG_HEADER && inputStream.read() == JPEG_APP && (result = checkJPEGNamespace(inputStream, "http://ns.adobe.com/xap/1.0/")) != null)) {
                        break;
                    }
                } catch (IOException e) {
                    Log.e(LocalMediaManager.TAG, "getJPEGXMP IOException ", e);
                }
            }
            return result;
        } catch (Exception e2) {
            Log.e(LocalMediaManager.TAG, "getJPEGXMP BufferedInputStream Exception ", e2);
            return null;
        }
    }

    public static int indexOf(byte[] outerArray, byte[] smallerArray) {
        for (int i = 0; i < (outerArray.length - smallerArray.length) + 1; i++) {
            boolean found = true;
            int j = 0;
            while (true) {
                if (j >= smallerArray.length) {
                    break;
                } else if (outerArray[i + j] != smallerArray[j]) {
                    found = false;
                    break;
                } else {
                    j++;
                }
            }
            if (found) {
                return i;
            }
        }
        return -1;
    }

    private static String checkJPEGNamespace(InputStream inputStream, String nameSpace) {
        String resultingString = convertJPEGSegmentToString(inputStream);
        if (resultingString == null || !resultingString.startsWith(nameSpace)) {
            return null;
        }
        return resultingString.substring(nameSpace.length() + 1);
    }

    private static String convertJPEGSegmentToString(InputStream inputStream) {
        byte[] buffer = readJPEGSegment(inputStream, null);
        if (buffer != null) {
            return new String(buffer);
        }
        return null;
    }

    private static byte[] readJPEGSegment(InputStream inputStream, byte[] namespace) {
        DataInputStream dataStream = new DataInputStream(inputStream);
        byte[] buffer = null;
        try {
            int sizeOfStream = dataStream.readUnsignedShort();
            if (sizeOfStream <= 2) {
                return null;
            }
            buffer = new byte[(sizeOfStream - 2)];
            try {
                dataStream.read(buffer);
            } catch (IOException e) {
                Log.e(LocalMediaManager.TAG, "convertJPEGSegmentToString error : ", e);
            }
            if (namespace != null) {
                for (int i = 0; i < namespace.length; i++) {
                    if (namespace[i] != buffer[i]) {
                        Log.e(LocalMediaManager.TAG, "Namespace not matching at index " + i);
                        return null;
                    }
                }
            }
            return buffer;
        } catch (IOException e2) {
            Log.e(LocalMediaManager.TAG, "convertJPEGSegmentToString error : ", e2);
        }
    }
}
