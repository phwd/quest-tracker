package com.oculus.localmedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class MediaUtils {
    public static final String TAG = "MediaUtils";
    public static final int THUMBNAIL_WIDTH = 40;

    public static class MediaEditResult {
        public String dstFilePath;
        public String error;

        public MediaEditResult(String str, String str2) {
            this.error = str;
            this.dstFilePath = str2;
        }

        public String getDstFilePath() {
            return this.dstFilePath;
        }

        public String getError() {
            return this.error;
        }
    }

    public static MediaEditResult cropImage(String str, int i, int i2, int i3, int i4, int i5, Context context) throws Exception {
        String str2;
        Uri parse = Uri.parse(str);
        String nextAvailableFilename = getNextAvailableFilename(parse.getPath(), "jpg");
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), parse);
            Matrix matrix = new Matrix();
            matrix.postRotate((float) i5);
            Bitmap.createBitmap(bitmap, i, i2, i3, i4, matrix, true).compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(nextAvailableFilename));
            str2 = "";
        } catch (Exception e) {
            str2 = e.getMessage();
        }
        if (str2 == "") {
            MediaScannerConnection.scanFile(context, new String[]{nextAvailableFilename}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /* class com.oculus.localmedia.MediaUtils.AnonymousClass1 */

                public void onScanCompleted(String str, Uri uri) {
                }
            });
        }
        return new MediaEditResult(str2, nextAvailableFilename);
    }

    public static String getNextAvailableFilename(String str, String str2) {
        String substring = str.substring(0, str.lastIndexOf(46));
        int i = 1;
        while (true) {
            int i2 = i + 1;
            String format = String.format("%s(%d).%s", substring, Integer.valueOf(i), str2);
            if (!new File(format).exists()) {
                return format;
            }
            i = i2;
        }
    }

    public static MediaEditResult trimVideo(String str, int i, int i2, Context context) throws Exception {
        String str2;
        int integer;
        Uri parse = Uri.parse(str);
        String nextAvailableFilename = getNextAvailableFilename(parse.getPath(), "mp4");
        try {
            parse.getPath();
            MediaExtractor mediaExtractor = new MediaExtractor();
            MediaExtractor mediaExtractor2 = new MediaExtractor();
            mediaExtractor.setDataSource(parse.getPath());
            mediaExtractor2.setDataSource(parse.getPath());
            int trackCount = mediaExtractor.getTrackCount();
            MediaMuxer mediaMuxer = new MediaMuxer(nextAvailableFilename, 0);
            HashMap hashMap = new HashMap(trackCount);
            int i3 = -1;
            for (int i4 = 0; i4 < trackCount; i4++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i4);
                String string = trackFormat.getString("mime");
                mediaExtractor.selectTrack(i4);
                hashMap.put(Integer.valueOf(i4), Integer.valueOf(mediaMuxer.addTrack(trackFormat)));
                if (trackFormat.containsKey("max-input-size") && (integer = trackFormat.getInteger("max-input-size")) > i3) {
                    i3 = integer;
                }
                if (string.startsWith("video/")) {
                    mediaExtractor2.selectTrack(i4);
                }
            }
            if (i3 < 0) {
                i3 = 1048576;
            }
            if (i > 0) {
                mediaExtractor2.seekTo((long) (i * 1000), 2);
                mediaExtractor.seekTo(mediaExtractor2.getSampleTime(), 2);
            }
            ByteBuffer allocate = ByteBuffer.allocate(i3);
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            try {
                mediaMuxer.start();
                while (true) {
                    bufferInfo.offset = 0;
                    int readSampleData = mediaExtractor.readSampleData(allocate, 0);
                    bufferInfo.size = readSampleData;
                    if (readSampleData < 0) {
                        bufferInfo.size = 0;
                        break;
                    }
                    long sampleTime = mediaExtractor.getSampleTime();
                    bufferInfo.presentationTimeUs = sampleTime;
                    if (i2 > 0 && sampleTime > ((long) (i2 * 1000))) {
                        break;
                    }
                    bufferInfo.flags = mediaExtractor.getSampleFlags();
                    mediaMuxer.writeSampleData(((Integer) hashMap.get(Integer.valueOf(mediaExtractor.getSampleTrackIndex()))).intValue(), allocate, bufferInfo);
                    mediaExtractor.advance();
                }
                mediaMuxer.stop();
                mediaMuxer.release();
                str2 = "";
            } catch (IllegalStateException e) {
                str2 = e.getMessage();
                Log.e(TAG, "The source video file is malformed", e);
                mediaMuxer.release();
            } catch (Throwable th) {
                mediaMuxer.release();
                throw th;
            }
        } catch (Exception e2) {
            str2 = e2.getMessage();
            Log.e(TAG, "Error trimming video file", e2);
        }
        if (str2 == "") {
            MediaScannerConnection.scanFile(context, new String[]{nextAvailableFilename}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /* class com.oculus.localmedia.MediaUtils.AnonymousClass2 */

                public void onScanCompleted(String str, Uri uri) {
                }
            });
        }
        return new MediaEditResult(str2, nextAvailableFilename);
    }

    public static ArrayList getKeyFrameTimestamps(String str) throws Exception {
        Uri parse = Uri.parse(str);
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(parse.getPath());
        ArrayList arrayList = new ArrayList();
        int trackCount = mediaExtractor.getTrackCount();
        int i = 0;
        while (true) {
            if (i >= trackCount) {
                break;
            } else if (mediaExtractor.getTrackFormat(i).getString("mime").startsWith("video/")) {
                mediaExtractor.selectTrack(i);
                break;
            } else {
                i++;
            }
        }
        long j = -1;
        while (mediaExtractor.getSampleTime() != j && mediaExtractor.getSampleTime() != -1) {
            j = mediaExtractor.getSampleTime();
            if ((mediaExtractor.getSampleFlags() & 1) > 0) {
                arrayList.add(Long.valueOf(j / 1000));
            }
            mediaExtractor.seekTo(1000 + j, 1);
        }
        return arrayList;
    }

    public static String getThumbnail(String str, int i) throws Exception {
        Uri parse = Uri.parse(str);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(parse.getPath());
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(mediaMetadataRetriever.getFrameAtTime((long) (i * 1000), 2), 40, 40, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createScaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        mediaMetadataRetriever.release();
        return Base64.encodeToString(byteArray, 0);
    }
}
