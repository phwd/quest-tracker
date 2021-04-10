package com.oculus.horizon.service_media.screenshot;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.C02800bY;
import X.C02930bl;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.view.Surface;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.R;
import com.oculus.horizon.service_media.MC;
import com.oculus.horizon.service_media.OVRMediaServiceNotification;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import com.oculus.mediaupload.io.FileUtils;
import com.oculus.util.device.DeviceUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_OVRMediaServiceNotification_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
public class ScreenshotManager {
    public static final String COMPANION_PACKAGE_NAME = "com.oculus.companion.server";
    public static final int COMPRESSION_QUALITY = 85;
    public static final String SCREENSHOT_FILE_PATH;
    public static final String TAG = "ScreenshotManager";
    public AnonymousClass0QC _UL_mInjectionContext;
    public double mDelay;
    @Nullable
    public File mFile;
    public int mHeight;
    public ImageReader mImageReader;
    public volatile Image mLatestImage;
    public Surface mSurface;
    public String mTargetPackage;
    public int mWidth;

    public interface ScreenshotListener {
        void A5s(File file, boolean z);
    }

    public final void A00(String str, int i, int i2, double d, final boolean z, @Nullable final ScreenshotListener screenshotListener) {
        this.mTargetPackage = str;
        this.mWidth = i;
        this.mHeight = i2;
        this.mDelay = d;
        String format = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File file = new File(SCREENSHOT_FILE_PATH);
        file.mkdirs();
        File file2 = new File(file, AnonymousClass006.A08(str, "-", format, FileUtils.JPG_FILE_EXTENSION));
        file2.getAbsolutePath();
        this.mFile = file2;
        ImageReader newInstance = ImageReader.newInstance(this.mWidth, this.mHeight, 1, 1);
        this.mImageReader = newInstance;
        this.mSurface = newInstance.getSurface();
        this.mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            /* class com.oculus.horizon.service_media.screenshot.ScreenshotManager.AnonymousClass1 */

            public final void onImageAvailable(ImageReader imageReader) {
                ScreenshotManager.this.mLatestImage = imageReader.acquireLatestImage();
                if (ScreenshotManager.this.mLatestImage != null) {
                    Image.Plane[] planes = ScreenshotManager.this.mLatestImage.getPlanes();
                    try {
                        int width = ScreenshotManager.this.mLatestImage.getWidth();
                        int height = ScreenshotManager.this.mLatestImage.getHeight();
                        FileOutputStream fileOutputStream = new FileOutputStream(ScreenshotManager.this.mFile);
                        if (planes[0].getBuffer() == null) {
                            AnonymousClass0NO.A08(ScreenshotManager.TAG, "No image data in buffer.");
                        } else {
                            ByteBuffer buffer = planes[0].getBuffer();
                            int[] iArr = new int[buffer.order(ByteOrder.BIG_ENDIAN).asIntBuffer().remaining()];
                            int pixelStride = planes[0].getPixelStride();
                            int rowStride = planes[0].getRowStride() - (pixelStride * width);
                            int i = 0;
                            for (int i2 = 0; i2 < height * width; i2++) {
                                iArr[i2] = ((buffer.get(i) & 255) << 16) | 0 | ((buffer.get(i + 1) & 255) << 8) | (buffer.get(i + 2) & 255) | ((buffer.get(i + 3) & 255) << 24);
                                i += pixelStride;
                                if (i2 % width == 0 && i2 != 0) {
                                    i += rowStride;
                                }
                            }
                            Bitmap createBitmap = Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
                            createBitmap.setHasAlpha(false);
                            createBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
                        }
                        File file = ScreenshotManager.this.mFile;
                        if (file != null) {
                            ScreenshotListener screenshotListener = screenshotListener;
                            if (screenshotListener != null) {
                                screenshotListener.A5s(file, z);
                            }
                            ScreenshotManager screenshotManager = ScreenshotManager.this;
                            File file2 = screenshotManager.mFile;
                            MediaScannerConnection.scanFile((Context) AnonymousClass0J2.A03(0, 80, screenshotManager._UL_mInjectionContext), new String[]{file2.getPath()}, null, new MediaScannerConnection.OnScanCompletedListener(file2) {
                                /* class com.oculus.horizon.service_media.screenshot.ScreenshotManager.AnonymousClass2 */
                                public final /* synthetic */ File val$file;

                                {
                                    this.val$file = r2;
                                }

                                public final void onScanCompleted(String str, Uri uri) {
                                    String str2;
                                    String str3 = null;
                                    if (((AnonymousClass0Rg) AnonymousClass0J2.A03(2, 399, ScreenshotManager.this._UL_mInjectionContext)).A36(36310293470511114L)) {
                                        str2 = uri.getLastPathSegment();
                                        str3 = this.val$file.getPath();
                                    } else {
                                        str2 = null;
                                    }
                                    OVRMediaServiceNotification oVRMediaServiceNotification = (OVRMediaServiceNotification) AnonymousClass0J2.A03(1, 149, ScreenshotManager.this._UL_mInjectionContext);
                                    if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, oVRMediaServiceNotification._UL_mInjectionContext)).A36(MC.oculus_share_interactive_capture_notifications.enabled)) {
                                        OVRMediaServiceNotification.A04(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.screenshot_aui_title), oVRMediaServiceNotification.mContext.getString(R.string.screenshot_aui_success_message), OVRMediaServiceNotification.A00(oVRMediaServiceNotification, str2, false), str3);
                                        return;
                                    }
                                    OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.screenshot_title), oVRMediaServiceNotification.mContext.getString(R.string.screenshot_success_message));
                                }
                            });
                        }
                    } catch (IOException unused) {
                        OVRMediaServiceNotification oVRMediaServiceNotification = (OVRMediaServiceNotification) AnonymousClass0J2.A03(1, 149, ScreenshotManager.this._UL_mInjectionContext);
                        OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.screenshot_title), oVRMediaServiceNotification.mContext.getString(R.string.screenshot_error_message));
                    }
                    ScreenshotManager screenshotManager2 = ScreenshotManager.this;
                    ImageReader imageReader2 = screenshotManager2.mImageReader;
                    if (imageReader2 != null) {
                        imageReader2.close();
                    }
                    if (screenshotManager2.mLatestImage != null) {
                        screenshotManager2.mLatestImage.close();
                    }
                }
            }
        }, new Handler());
        Intent intent = new Intent(OVRMediaServiceContract.SCREENSHOT_ACTION);
        intent.putExtra(OVRMediaServiceContract.INTENT_KEY_SCREENSHOT_DELAY, this.mDelay);
        intent.putExtra("intent_pkg", ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).getPackageName());
        intent.putExtra("surface", this.mSurface);
        try {
            C02800bY.A02(intent, (Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext), AnonymousClass006.A05(getClass().getSimpleName(), ":onReceive()"));
        } catch (C02930bl e) {
            AnonymousClass0NO.A0B(TAG, "Cannot attach callerInfo", e);
            e.printStackTrace();
        }
        intent.setPackage(this.mTargetPackage);
        ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).sendBroadcast(intent);
        intent.setPackage("com.oculus.companion.server");
        ((Context) AnonymousClass0J2.A03(0, 80, this._UL_mInjectionContext)).sendBroadcast(intent);
    }

    static {
        String path = Environment.getExternalStorageDirectory().getPath();
        String str = File.separator;
        SCREENSHOT_FILE_PATH = AnonymousClass006.A09(path, str, DeviceUtils.OCULUS_DIR, str, "Screenshots");
    }

    @Inject
    public ScreenshotManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
