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
    public static final String TAG = MediaUtils.class.getSimpleName();

    public static class MediaEditResult {
        String dstFilePath;
        String error;

        public MediaEditResult(String error2, String dstFilePath2) {
            this.error = error2;
            this.dstFilePath = dstFilePath2;
        }

        public String getError() {
            return this.error;
        }

        public String getDstFilePath() {
            return this.dstFilePath;
        }
    }

    private static String getNextAvailableFilename(String filepath, String extension) {
        int count = 1;
        String filepathWithoutExt = filepath.substring(0, filepath.lastIndexOf(46));
        while (true) {
            int count2 = count + 1;
            String dstFilepath = String.format("%s(%d).%s", filepathWithoutExt, Integer.valueOf(count), extension);
            if (!new File(dstFilepath).exists()) {
                return dstFilepath;
            }
            count = count2;
        }
    }

    public static ArrayList getKeyFrameTimestamps(String source) throws Exception {
        Uri sourceUri = Uri.parse(source);
        MediaExtractor extractor = new MediaExtractor();
        extractor.setDataSource(sourceUri.getPath());
        ArrayList keyframeTimestampsMS = new ArrayList();
        int trackCount = extractor.getTrackCount();
        int i = 0;
        while (true) {
            if (i >= trackCount) {
                break;
            } else if (extractor.getTrackFormat(i).getString("mime").startsWith("video/")) {
                extractor.selectTrack(i);
                break;
            } else {
                i++;
            }
        }
        long sampleTime = -1;
        while (extractor.getSampleTime() != sampleTime && extractor.getSampleTime() != -1) {
            sampleTime = extractor.getSampleTime();
            if ((extractor.getSampleFlags() & 1) > 0) {
                keyframeTimestampsMS.add(Long.valueOf(sampleTime / 1000));
                Log.d(TAG, "Keyframe obtained at time: " + Long.toString(sampleTime));
            }
            extractor.seekTo(1000 + sampleTime, 1);
        }
        return keyframeTimestampsMS;
    }

    public static String getThumbnail(String source, int timeMs) throws Exception {
        Uri sourceUri = Uri.parse(source);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(sourceUri.getPath());
        Bitmap bitmap = Bitmap.createScaledBitmap(retriever.getFrameAtTime((long) (timeMs * 1000), 2), 40, 40, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        retriever.release();
        return Base64.encodeToString(bytes, 0);
    }

    public static MediaEditResult cropImage(String source, int startX, int startY, int width, int height, int angle, Context ctx) throws Exception {
        Uri sourceUri = Uri.parse(source);
        String dstFilepath = getNextAvailableFilename(sourceUri.getPath(), "jpg");
        String error = "";
        try {
            Bitmap originalImage = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), sourceUri);
            Matrix matrix = new Matrix();
            matrix.postRotate((float) angle);
            Bitmap.createBitmap(originalImage, startX, startY, width, height, matrix, true).compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(dstFilepath));
        } catch (Exception e) {
            error = e.getMessage();
        }
        if (error == "") {
            MediaScannerConnection.scanFile(ctx, new String[]{dstFilepath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /* class com.oculus.localmedia.MediaUtils.AnonymousClass1 */

                public void onScanCompleted(String path, Uri uri) {
                }
            });
        }
        return new MediaEditResult(error, dstFilepath);
    }

    public static MediaEditResult trimVideo(String source, int startMs, int endMs, Context ctx) throws Exception {
        Uri sourceUri = Uri.parse(source);
        String dstFilepath = getNextAvailableFilename(sourceUri.getPath(), "mp4");
        String error = "";
        try {
            Log.d(TAG, "Trimming: " + sourceUri.getPath());
            Log.d(TAG, "From " + Integer.toString(startMs) + "to" + Integer.toString(endMs));
            MediaExtractor extractor = new MediaExtractor();
            MediaExtractor videoExtractor = new MediaExtractor();
            extractor.setDataSource(sourceUri.getPath());
            videoExtractor.setDataSource(sourceUri.getPath());
            int trackCount = extractor.getTrackCount();
            MediaMuxer muxer = new MediaMuxer(dstFilepath, 0);
            int bufferSize = -1;
            HashMap<Integer, Integer> indexMap = new HashMap<>(trackCount);
            for (int i = 0; i < trackCount; i++) {
                MediaFormat format = extractor.getTrackFormat(i);
                String mime = format.getString("mime");
                Log.d(TAG, "Processed track with format " + mime);
                extractor.selectTrack(i);
                indexMap.put(Integer.valueOf(i), Integer.valueOf(muxer.addTrack(format)));
                if (format.containsKey("max-input-size")) {
                    int newSize = format.getInteger("max-input-size");
                    if (newSize > bufferSize) {
                        bufferSize = newSize;
                    }
                }
                if (mime.startsWith("video/")) {
                    videoExtractor.selectTrack(i);
                }
            }
            if (bufferSize < 0) {
                bufferSize = 1048576;
            }
            if (startMs > 0) {
                videoExtractor.seekTo((long) (startMs * 1000), 2);
                extractor.seekTo(videoExtractor.getSampleTime(), 2);
            }
            ByteBuffer dstBuf = ByteBuffer.allocate(bufferSize);
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            try {
                muxer.start();
                while (true) {
                    bufferInfo.offset = 0;
                    bufferInfo.size = extractor.readSampleData(dstBuf, 0);
                    if (bufferInfo.size < 0) {
                        Log.d(TAG, "Saw input EOS.");
                        bufferInfo.size = 0;
                        break;
                    }
                    bufferInfo.presentationTimeUs = extractor.getSampleTime();
                    if (endMs > 0 && bufferInfo.presentationTimeUs > ((long) (endMs * 1000))) {
                        Log.d(TAG, "The current sample is over the trim end time.");
                        break;
                    }
                    bufferInfo.flags = extractor.getSampleFlags();
                    muxer.writeSampleData(indexMap.get(Integer.valueOf(extractor.getSampleTrackIndex())).intValue(), dstBuf, bufferInfo);
                    extractor.advance();
                }
                muxer.stop();
            } catch (IllegalStateException e) {
                error = e.getMessage();
                Log.e(TAG, "The source video file is malformed", e);
            } finally {
                muxer.release();
            }
        } catch (Exception e2) {
            error = e2.getMessage();
            Log.e(TAG, "Error trimming video file", e2);
        }
        Log.d(TAG, "Trimming finished");
        if (error == "") {
            MediaScannerConnection.scanFile(ctx, new String[]{dstFilepath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /* class com.oculus.localmedia.MediaUtils.AnonymousClass2 */

                public void onScanCompleted(String path, Uri uri) {
                }
            });
        }
        return new MediaEditResult(error, dstFilepath);
    }
}
