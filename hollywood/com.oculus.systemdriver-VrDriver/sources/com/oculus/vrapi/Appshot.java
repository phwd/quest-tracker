package com.oculus.vrapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* compiled from: AppShot */
class Appshot {
    private static final String ACTION_UPDATE_APP_SCREENSHOT = "com.oculus.vrshell.intent.action.UPDATE_APP_SCREENSHOT";
    public static final String INTENT_KEY_FROM_PKG = "intent_pkg";
    public static final String INTENT_KEY_JPEG = "jpg_data";
    public static final String SHELL_PACKAGE_NAME = "com.oculus.vrshell";
    public static final String SYSTEMUX_PACKAGE_NAME = "com.oculus.systemux";
    private static final String TAG = "Appshot";
    private static Context mCachedContext = null;
    private static String mClientPackageName = null;
    private static Handler mHandler = null;
    private static volatile Looper mImageAcquireLooper;
    private static volatile boolean mImageAvailable = false;
    private static ImageReader mImageReader = null;
    private static final Object mInitLock = new Object();
    private static volatile boolean mInitialized = false;
    private static volatile Image mLatestImage;
    private static Surface mSurface = null;
    private static final Object mWriteLock = new Object();

    Appshot() {
    }

    public static Surface getSurface() {
        return mSurface;
    }

    public static boolean init(Context context, int width, int height) {
        Log.d(TAG, "init " + context.getPackageName());
        try {
            synchronized (mInitLock) {
                if (mInitialized) {
                    mInitLock.wait();
                } else {
                    mInitialized = true;
                }
                mCachedContext = context;
                mImageReader = ImageReader.newInstance(width, height, 1, 1);
                mSurface = mImageReader.getSurface();
                if (mHandler != null) {
                    setImageListener(mHandler);
                    return true;
                }
                new Thread(new Runnable() {
                    /* class com.oculus.vrapi.Appshot.AnonymousClass1 */

                    public void run() {
                        Looper.prepare();
                        Looper unused = Appshot.mImageAcquireLooper = Looper.myLooper();
                        Appshot.setImageListener(new Handler());
                        Looper.loop();
                    }
                }).start();
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "init() Failed " + e);
            synchronized (mInitLock) {
                mInitialized = false;
                mInitLock.notify();
                return false;
            }
        }
    }

    public static void release() {
        if (mCachedContext != null) {
            Log.d(TAG, "release " + mCachedContext.getPackageName());
        }
        try {
            synchronized (mInitLock) {
                synchronized (mWriteLock) {
                    if (mImageAvailable) {
                        mWriteLock.wait();
                    }
                    if (mImageReader != null) {
                        mImageReader.close();
                    }
                    if (mLatestImage != null) {
                        mLatestImage.close();
                    }
                    if (mImageAcquireLooper != null) {
                        mImageAcquireLooper.quit();
                    }
                    mSurface = null;
                }
                mInitialized = false;
                mInitLock.notify();
            }
        } catch (Exception e) {
            Log.e(TAG, "release failed " + e);
        }
    }

    public static void setClient(String clientPackageName) {
        mClientPackageName = clientPackageName;
        Log.v(TAG, "Appshot client set to " + mClientPackageName);
    }

    /* access modifiers changed from: private */
    public static void setImageListener(Handler handler) {
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            /* class com.oculus.vrapi.Appshot.AnonymousClass2 */

            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
                if (com.oculus.vrapi.Appshot.mLatestImage == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
                r0 = com.oculus.vrapi.Appshot.mWriteLock;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
                monitor-enter(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
                r0 = com.oculus.vrapi.Appshot.mImageAvailable = true;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
                monitor-exit(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
                com.oculus.vrapi.Appshot.sendAppshot(com.oculus.vrapi.Appshot.mLatestImage.getPlanes());
                com.oculus.vrapi.Appshot.mLatestImage.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
                r0 = com.oculus.vrapi.Appshot.mLatestImage = r4.acquireNextImage();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onImageAvailable(android.media.ImageReader r4) {
                /*
                    r3 = this;
                    java.lang.Object r0 = com.oculus.vrapi.Appshot.access$200()     // Catch:{ Exception -> 0x003f }
                    monitor-enter(r0)     // Catch:{ Exception -> 0x003f }
                    boolean r1 = com.oculus.vrapi.Appshot.access$300()     // Catch:{ all -> 0x003c }
                    if (r1 == 0) goto L_0x000d
                    monitor-exit(r0)     // Catch:{ all -> 0x003c }
                    return
                L_0x000d:
                    monitor-exit(r0)     // Catch:{ all -> 0x003c }
                    android.media.Image r0 = r4.acquireNextImage()
                    com.oculus.vrapi.Appshot.access$402(r0)
                    android.media.Image r0 = com.oculus.vrapi.Appshot.access$400()
                    if (r0 == 0) goto L_0x003b
                    java.lang.Object r0 = com.oculus.vrapi.Appshot.access$200()
                    monitor-enter(r0)
                    r1 = 1
                    com.oculus.vrapi.Appshot.access$302(r1)     // Catch:{ all -> 0x0038 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0038 }
                    android.media.Image r0 = com.oculus.vrapi.Appshot.access$400()
                    android.media.Image$Plane[] r0 = r0.getPlanes()
                    com.oculus.vrapi.Appshot.access$500(r0)
                    android.media.Image r1 = com.oculus.vrapi.Appshot.access$400()
                    r1.close()
                    goto L_0x003b
                L_0x0038:
                    r1 = move-exception
                    monitor-exit(r0)
                    throw r1
                L_0x003b:
                    goto L_0x0058
                L_0x003c:
                    r1 = move-exception
                    monitor-exit(r0)
                    throw r1
                L_0x003f:
                    r0 = move-exception
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "onImageAvailable failed "
                    r1.append(r2)
                    r1.append(r0)
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "Appshot"
                    android.util.Log.e(r2, r1)
                L_0x0058:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrapi.Appshot.AnonymousClass2.onImageAvailable(android.media.ImageReader):void");
            }
        }, handler);
    }

    private static void convertImageToJpg(int width, int height, int quality, OutputStream outStream, Image.Plane[] planes) {
        ByteBuffer buffer = planes[0].getBuffer();
        if (buffer == null) {
            Log.e(TAG, "No image data in buffer.");
            return;
        }
        Trace.beginSection("convertImageToJpg");
        byte[] rawBytes = new byte[buffer.capacity()];
        buffer.get(rawBytes);
        int pixelStride = planes[0].getPixelStride();
        int rowPadding = planes[0].getRowStride() - (pixelStride * width);
        int offset = 0;
        int pixelCount = width * height;
        int[] imageData = new int[pixelCount];
        for (int i = 0; i < pixelCount; i++) {
            imageData[i] = ((rawBytes[offset + 2] & 255) << 0) | ((rawBytes[offset + 1] & 255) << 8) | ((rawBytes[offset] & 255) << 16) | ((rawBytes[offset + 3] & 255) << 24);
            offset += pixelStride;
            if (i % width == 0 && i != 0) {
                offset += rowPadding;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(imageData, width, height, Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(false);
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outStream);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public static void sendAppshot(Image.Plane[] planes) {
        Log.v(TAG, "sendAppshot");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        convertImageToJpg(mLatestImage.getWidth(), mLatestImage.getHeight(), 85, byteStream, planes);
        try {
            synchronized (mWriteLock) {
                mImageAvailable = false;
                mWriteLock.notify();
            }
        } catch (Exception e) {
            Log.e(TAG, "sendAppshot failed " + e);
        }
        if (!SHELL_PACKAGE_NAME.equals(mClientPackageName)) {
            broadcastAppShot(mCachedContext, mClientPackageName, byteStream.toByteArray());
        }
    }

    static void broadcastAppShot(Context context, String appShotPackage, byte[] jpgBytes) {
        Log.d(TAG, "broadcastAppShot intent_pkg: " + appShotPackage + " jpg_size: " + jpgBytes.length);
        if (TextUtils.isEmpty(appShotPackage)) {
            Log.e(TAG, "Failed to pass packageName");
            return;
        }
        Intent intent = new Intent(ACTION_UPDATE_APP_SCREENSHOT);
        intent.setPackage(SYSTEMUX_PACKAGE_NAME);
        intent.putExtra("intent_pkg", appShotPackage);
        intent.putExtra("jpg_data", jpgBytes);
        context.sendBroadcast(intent);
    }
}
