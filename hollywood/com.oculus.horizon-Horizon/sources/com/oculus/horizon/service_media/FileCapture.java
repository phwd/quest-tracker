package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0I1;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.AnonymousClass117;
import X.AnonymousClass1X0;
import X.AnonymousClass1X9;
import X.AnonymousClass1XI;
import X.AnonymousClass1Xx;
import X.C003108z;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.view.Surface;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.R;
import com.oculus.horizon.abuse_prevention.interfaces.AbuseCaptureProvider;
import com.oculus.horizon.capture.CaptureDelegate;
import com.oculus.horizon.capture.ScreenCapture;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service_media.MC;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.FunnelData;
import com.oculus.mediaupload.io.FileUtils;
import com.oculus.util.device.DeviceUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_SurfaceCapture_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformplugin_PlatformPluginManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_CaptureAnalytics_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_ForegroundAppChecker_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_OVRMediaServiceNotification_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_capture_ScreenCapture_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
@ApplicationScoped
public class FileCapture implements AbuseCaptureProvider {
    public static final int AUDIO_RECORDER_INTERVAL_MS = 500;
    public static final String CAPTURE_STATE_UPDATE_ACTION = "com.oculus.systemactivities.CAPTURE_STATE_UPDATE";
    public static final int DEFAULT_ABUSE_CAPTURE_BITRATE = 1000000;
    public static final int DEFAULT_ABUSE_CAPTURE_FPS = 30;
    public static final int DEFAULT_ABUSE_CAPTURE_RES = 512;
    public static final long DEFAULT_CAPTURE_TO_DISK_BITRATE = 5000000;
    public static final long DEFAULT_CAPTURE_TO_DISK_FPS = 60;
    public static final int DEFAULT_CAPTURE_TO_DISK_IFRAME_INTERVAL_S = 5;
    public static final long DEFAULT_CAPTURE_TO_DISK_RES = 1024;
    public static final long DEFAULT_INSTANT_REPLAY_BITRATE = 2500000;
    public static final long DEFAULT_INSTANT_REPLAY_RES = 832;
    public static final int INSTANT_REPLAY_DURATION_US = 15000000;
    public static final String INSTANT_REPLAY_EXCEPTION_FIELD = "exception";
    public static final int INSTANT_REPLAY_IFRAME_INTERVAL_S = 1;
    public static final long INSTANT_REPLAY_MIN_RECORDING_LENGTH_IN_BYTES = 625000;
    public static final int INSTANT_REPLAY_OFFSET_FROM_END_US = 2000000;
    public static final String INSTANT_REPLAY_SAVE_ERROR_EVENT = "oculus_instant_replay_save_error";
    public static final String INSTANT_REPLAY_STATE_UPDATE_ACTION = "com.oculus.systemactivities.INSTANT_REPLAY_STATE_UPDATE";
    public static final String INSTANT_REPLAY_TEMP_DIR;
    public static final String INTENT_PARAM_ABUSE_RECORDING_ELAPSED_TIME = "abuse_recording_elapsed_time";
    public static final String INTENT_PARAM_ABUSE_RECORDING_UUID = "abuse_recording_uuid";
    public static final String INTENT_PARAM_CAPTURE_MODE = "capture_mode";
    public static final String INTENT_PARAM_IS_CAPTURE_RUNNING = "is_capture_running";
    public static final String INTENT_PARAM_IS_INSTANT_REPLAY_AVAILABLE = "is_available";
    public static final String INTENT_PARAM_IS_INSTANT_REPLAY_CAPTURE = "is_instant_replay";
    public static final String SYSTEMUX_PACKAGE_NAME = "com.oculus.systemux";
    public static final String TAG = "FileCapture";
    public static final String VIDEO_OUTPUT_DIR;
    public static final String VRSHELL_PACKAGE_NAME = "com.oculus.vrshell";
    public static volatile FileCapture _UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_FileCapture_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public long mAbuseRecordingStartTime;
    @Nullable
    public String mAbuseRecordingUUID;
    @Nullable
    public AudioCapture mAudioCapture;
    public long mAudioTimeElapsed;
    @Nullable
    public String mAutoCaptureForReportUUID;
    @Inject
    @Eager
    public final CaptureAnalytics mCaptureAnalytics;
    public Mode mCaptureMode = Mode.UNKNOWN;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final ForegroundAppChecker mForegroundAppChecker;
    @Nullable
    public File mInstantReplayRecordingFile;
    public boolean mIsInstantReplay = false;
    public final Object mLock = new Object();
    public long mNativeAudioRenderer;
    public boolean mNotifyMediaReady = true;
    public final HashMap<String, AnonymousClass1X9> mPendingInstantReplaysToSave = new HashMap<>();
    @Inject
    @Eager
    public final PlatformPluginManager mPlatformPluginManager;
    @Nullable
    public File mRecordingFile;
    @Nullable
    public Surface mSurface;
    @Inject
    @Eager
    public final SurfaceCapture mSurfaceCapture;
    @Nullable
    public AnonymousClass1X9 mUnmuxedRollingBufferMuxerConfig;

    public abstract class VideoStateListener implements ScreenCapture.VideoStateListener {
        public VideoStateListener() {
        }

        @Override // com.oculus.horizon.capture.ScreenCapture.VideoStateListener
        public final void A60(Exception exc) {
            AnonymousClass0NO.A0B(FileCapture.TAG, "onError", exc);
            FileCapture fileCapture = FileCapture.this;
            fileCapture.A9R(fileCapture.mSurfaceCapture.mPackageName);
            FileCapture fileCapture2 = FileCapture.this;
            fileCapture2.mCaptureAnalytics.A00(exc, fileCapture2.mCaptureMode);
        }

        @Override // com.oculus.horizon.capture.ScreenCapture.VideoStateListener
        public final void A6z(Surface surface) {
            synchronized (FileCapture.this.mLock) {
                FileCapture fileCapture = FileCapture.this;
                fileCapture.mSurface = surface;
                fileCapture.mAudioTimeElapsed = 0;
                SurfaceCapture surfaceCapture = fileCapture.mSurfaceCapture;
                surfaceCapture.A02(surfaceCapture.mPackageName, surface);
                FileCapture fileCapture2 = FileCapture.this;
                CaptureAnalytics captureAnalytics = fileCapture2.mCaptureAnalytics;
                String str = fileCapture2.mSurfaceCapture.mPackageName;
                Mode mode = fileCapture2.mCaptureMode;
                String str2 = str;
                ((EventManager) AnonymousClass0J2.A03(0, 242, captureAnalytics._UL_mInjectionContext)).A9H(FunnelContract.CAPTURE_FUNNEL_NAME);
                FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, captureAnalytics._UL_mInjectionContext)).A24();
                String str3 = "";
                if (str == null) {
                    str2 = str3;
                }
                A24.A18("package", str2);
                if (mode != null) {
                    str3 = mode.toString();
                }
                A24.A18(CaptureAnalytics.KEY_CAPTURE_TYPE, str3);
                ((EventManager) AnonymousClass0J2.A03(0, 242, captureAnalytics._UL_mInjectionContext)).A8F(FunnelContract.CAPTURE_FUNNEL_NAME, CaptureAnalytics.ACTION_START_CAPTURE, null, A24);
            }
        }

        @Override // com.oculus.horizon.capture.ScreenCapture.VideoStateListener
        public final void A72(File file) {
            synchronized (FileCapture.this.mLock) {
                if (!(this instanceof AnonymousClass2)) {
                    FileCapture fileCapture = FileCapture.this;
                    HashMap<String, AnonymousClass1X9> hashMap = fileCapture.mPendingInstantReplaysToSave;
                    AnonymousClass1X9 r1 = fileCapture.mUnmuxedRollingBufferMuxerConfig;
                    synchronized (fileCapture.mLock) {
                        try {
                            String canonicalPath = file.getCanonicalPath();
                            if (hashMap.containsKey(canonicalPath)) {
                                hashMap.remove(canonicalPath);
                                if (FileCapture.A00(fileCapture, file, hashMap.get(canonicalPath), fileCapture.mPendingInstantReplaysToSave)) {
                                    fileCapture.A03(Mode.CAPTURE_TO_DISK, file, true, true);
                                }
                            } else if (r1 == null) {
                                AnonymousClass0NO.A08(FileCapture.TAG, "no muxing config available");
                            } else {
                                File file2 = r1.A03;
                                if (file2 == null) {
                                    AnonymousClass0NO.A08(FileCapture.TAG, "no temp file available");
                                } else if (!file2.exists()) {
                                    AnonymousClass0NO.A08(FileCapture.TAG, "temp file was not created");
                                } else if (file2.length() < FileCapture.INSTANT_REPLAY_MIN_RECORDING_LENGTH_IN_BYTES) {
                                    AnonymousClass0NO.A09(FileCapture.TAG, "recording too short to be saved");
                                    file2.delete();
                                } else {
                                    Intent intent = new Intent(FileCapture.INSTANT_REPLAY_STATE_UPDATE_ACTION);
                                    intent.putExtra(FileCapture.INTENT_PARAM_IS_INSTANT_REPLAY_AVAILABLE, true);
                                    intent.setPackage("com.oculus.systemux");
                                    fileCapture.mContext.sendBroadcast(intent);
                                }
                            }
                        } catch (IOException e) {
                            AnonymousClass0NO.A0B(FileCapture.TAG, "couldn't get file canonical path", e);
                        }
                    }
                } else {
                    AnonymousClass2 r12 = (AnonymousClass2) this;
                    FileCapture fileCapture2 = FileCapture.this;
                    fileCapture2.A03(r16, file, false, fileCapture2.mNotifyMediaReady);
                }
                CaptureAnalytics captureAnalytics = FileCapture.this.mCaptureAnalytics;
                ((EventManager) AnonymousClass0J2.A03(0, 242, captureAnalytics._UL_mInjectionContext)).A8F(FunnelContract.CAPTURE_FUNNEL_NAME, CaptureAnalytics.ACTION_STOP_CAPTURE, null, null);
                ((EventManager) AnonymousClass0J2.A03(0, 242, captureAnalytics._UL_mInjectionContext)).A2Q(FunnelContract.CAPTURE_FUNNEL_NAME);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
            r13 = java.nio.ByteBuffer.wrap(new byte[0]);
            r6 = r9.mLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
            monitor-enter(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
            if (r9.mNativeAudioRenderer == 0) goto L_0x00b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
            java.util.Arrays.fill(r9.mAudioShortsBuffer, (short) 0);
            java.util.Arrays.fill(r9.mAudioBytesBuffer, (byte) 0);
            r14 = r9.mNativeAudioRenderer;
            r1 = r9.mAudioShortsBuffer;
            r10 = com.oculus.horizon.platformplugin.PlatformPluginManager.nativeCapturedAudioRenderer_RetrievePCM(r14, r1, r1.length, 48000, 1);
            r4 = java.lang.System.currentTimeMillis();
            r2 = r9.mCurrentTimestampMs;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
            if (r2 != 0) goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
            r9.mCurrentTimestampMs = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
            r0 = java.lang.Math.max(0L, r4 - r2);
            r9.mCurrentTimestampMs = r4;
            r2 = (int) (((r0 * 48000) / 1000) * ((long) 2));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0082, code lost:
            r2 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0083, code lost:
            if (r10 != 0) goto L_0x008a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0086, code lost:
            if (r2 == -1) goto L_0x008a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0088, code lost:
            r10 = r2 / 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x008d, code lost:
            if (r10 > r9.mAudioShortsBuffer.length) goto L_0x00b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x008f, code lost:
            if (r10 < 0) goto L_0x00b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0091, code lost:
            java.nio.ByteBuffer.wrap(r9.mAudioBytesBuffer).order(java.nio.ByteOrder.nativeOrder()).asShortBuffer().put(r9.mAudioShortsBuffer, 0, r10);
            r13 = java.nio.ByteBuffer.wrap(r9.mAudioBytesBuffer, 0, r10 << 1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b0, code lost:
            monitor-exit(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b1, code lost:
            r2 = r13.array();
            r1 = r13.limit();
            r0 = r8.mCaptureDelegate;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bb, code lost:
            if (r0 != null) goto L_0x00c5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bd, code lost:
            X.AnonymousClass0NO.A08(com.oculus.horizon.capture.ScreenCapture.TAG, "CaptureDelegate is null, setup first!");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c4, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c5, code lost:
            r0.A5l(r2, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c8, code lost:
            return;
         */
        @Override // com.oculus.horizon.capture.ScreenCapture.VideoStateListener
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void A2z() {
            /*
            // Method dump skipped, instructions count: 209
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.FileCapture.VideoStateListener.A2z():void");
        }
    }

    public static boolean A01(@Nullable FileCapture fileCapture, String str, File file, final Mode mode, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        File file2;
        CaptureDelegate captureDelegate;
        ScreenCapture screenCapture;
        long j;
        long j2;
        File file3 = file;
        synchronized (fileCapture.mLock) {
            if (fileCapture.A05() || str == null) {
                AnonymousClass0NO.A0E(TAG, "Failed to start capture for packageName = %s", str);
            } else {
                fileCapture.mIsInstantReplay = z;
                fileCapture.mNotifyMediaReady = true;
                fileCapture.mCaptureMode = mode;
                SurfaceCapture surfaceCapture = fileCapture.mSurfaceCapture;
                boolean z2 = false;
                if (mode == Mode.ABUSE_CAPTURE) {
                    z2 = true;
                }
                surfaceCapture.mLiftInhibit = z2;
                boolean z3 = false;
                if (!z) {
                    z3 = true;
                }
                surfaceCapture.mShowCaptureIndicator = z3;
                surfaceCapture.mPackageName = str;
                fileCapture.mRecordingFile = file;
                if (file == null) {
                    String format = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
                    File file4 = new File(VIDEO_OUTPUT_DIR);
                    file4.mkdirs();
                    file3 = new File(new File(file4, AnonymousClass006.A08(str, "-", format, FileUtils.MP4_FILE_EXTENSION)).getAbsolutePath());
                    fileCapture.mRecordingFile = file3;
                }
                fileCapture.mInstantReplayRecordingFile = file3;
                try {
                    long nativeCapturedAudioRenderer_New = PlatformPluginManager.nativeCapturedAudioRenderer_New();
                    fileCapture.mNativeAudioRenderer = nativeCapturedAudioRenderer_New;
                    fileCapture.mAudioCapture = new AudioCapture(fileCapture.mPlatformPluginManager, nativeCapturedAudioRenderer_New);
                    if (z) {
                        fileCapture.mAutoCaptureForReportUUID = UUID.randomUUID().toString();
                        File file5 = new File(fileCapture.mContext.getFilesDir(), INSTANT_REPLAY_TEMP_DIR);
                        File file6 = new File(file5, fileCapture.mRecordingFile.getName());
                        file5.mkdirs();
                        AnonymousClass1X9 r5 = new AnonymousClass1X9(file6);
                        fileCapture.mUnmuxedRollingBufferMuxerConfig = r5;
                        ((ScreenCapture) AnonymousClass0J2.A03(1, 331, fileCapture._UL_mInjectionContext)).A00(new VideoStateListener() {
                            /* class com.oculus.horizon.service_media.FileCapture.AnonymousClass1 */
                        }, r5);
                    } else {
                        fileCapture.mUnmuxedRollingBufferMuxerConfig = null;
                        ((ScreenCapture) AnonymousClass0J2.A03(1, 331, fileCapture._UL_mInjectionContext)).A00(new VideoStateListener() {
                            /* class com.oculus.horizon.service_media.FileCapture.AnonymousClass2 */
                        }, null);
                    }
                    switch (mode.ordinal()) {
                        case 0:
                            long j3 = 832;
                            if (z) {
                                j = 832;
                            } else {
                                j = 1024;
                            }
                            i5 = (int) AnonymousClass0I1.A00("debug.oculus.capture.width", j);
                            if (!z) {
                                j3 = 1024;
                            }
                            i4 = (int) AnonymousClass0I1.A00("debug.oculus.capture.height", j3);
                            i3 = (int) AnonymousClass0I1.A00("debug.oculus.capture.fps", 60);
                            if (z) {
                                j2 = DEFAULT_INSTANT_REPLAY_BITRATE;
                            } else {
                                j2 = DEFAULT_CAPTURE_TO_DISK_BITRATE;
                            }
                            int A00 = (int) AnonymousClass0I1.A00("debug.oculus.capture.bitrate", j2);
                            screenCapture = (ScreenCapture) AnonymousClass0J2.A03(1, 331, fileCapture._UL_mInjectionContext);
                            file2 = fileCapture.mRecordingFile;
                            i = 5;
                            if (z) {
                                i = 1;
                            }
                            captureDelegate = screenCapture.mCaptureDelegate;
                            if (captureDelegate != null) {
                                i2 = A00;
                                captureDelegate.A9K(file2, i5, i4, i3, i2, i);
                                screenCapture.mIsCapturing = true;
                                break;
                            } else {
                                AnonymousClass0NO.A08(ScreenCapture.TAG, "CaptureDelegate is null, setup first!");
                                break;
                            }
                        case 1:
                            screenCapture = (ScreenCapture) AnonymousClass0J2.A03(1, 331, fileCapture._UL_mInjectionContext);
                            file2 = fileCapture.mRecordingFile;
                            i5 = 512;
                            i3 = 30;
                            i2 = DEFAULT_ABUSE_CAPTURE_BITRATE;
                            i = 5;
                            captureDelegate = screenCapture.mCaptureDelegate;
                            if (captureDelegate != null) {
                                i4 = 512;
                                captureDelegate.A9K(file2, i5, i4, i3, i2, i);
                                screenCapture.mIsCapturing = true;
                                break;
                            } else {
                                AnonymousClass0NO.A08(ScreenCapture.TAG, "CaptureDelegate is null, setup first!");
                                break;
                            }
                        default:
                            AnonymousClass0NO.A0F(TAG, "start recording failed: unsupported mode (%d)", Integer.valueOf(mode.mVal));
                            fileCapture.A9R(str);
                            fileCapture.mCaptureAnalytics.A00(null, fileCapture.mCaptureMode);
                            break;
                    }
                    fileCapture.A02();
                    return true;
                } catch (IOException e) {
                    AnonymousClass0NO.A0B(TAG, "start recording failed", e);
                    fileCapture.A9R(str);
                    fileCapture.mCaptureAnalytics.A00(e, fileCapture.mCaptureMode);
                }
            }
            return false;
        }
    }

    /* renamed from: com.oculus.horizon.service_media.FileCapture$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$service_media$FileCapture$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.horizon.service_media.FileCapture$Mode[] r0 = com.oculus.horizon.service_media.FileCapture.Mode.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.service_media.FileCapture.AnonymousClass5.$SwitchMap$com$oculus$horizon$service_media$FileCapture$Mode = r2
                com.oculus.horizon.service_media.FileCapture$Mode r0 = com.oculus.horizon.service_media.FileCapture.Mode.CAPTURE_TO_DISK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.service_media.FileCapture$Mode r0 = com.oculus.horizon.service_media.FileCapture.Mode.ABUSE_CAPTURE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.FileCapture.AnonymousClass5.<clinit>():void");
        }
    }

    public enum Mode {
        CAPTURE_TO_DISK(0),
        ABUSE_CAPTURE(1),
        UNKNOWN(-1);
        
        public final int mVal;

        /* access modifiers changed from: public */
        Mode(int i) {
            this.mVal = i;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/io/File;LX/1X9;Ljava/util/HashMap<Ljava/lang/String;LX/1X9;>;)Z */
    @VisibleForTesting
    public static final boolean A00(@Nullable FileCapture fileCapture, @Nullable File file, AnonymousClass1X9 r27, HashMap hashMap) {
        File file2;
        File[] fileArr;
        AnonymousClass1XI r2;
        AnonymousClass1XI r22;
        AnonymousClass1XI r14;
        synchronized (fileCapture.mLock) {
            if (!(file2 == null || r27 == null)) {
                MediaFormat mediaFormat = r27.A02;
                MediaFormat mediaFormat2 = r27.A01;
                if (!(mediaFormat == null || mediaFormat2 == null || (file2 = r27.A03) == null)) {
                    if (!file2.exists()) {
                        try {
                            hashMap.put(file2.getCanonicalPath(), r27);
                        } catch (IOException e) {
                            AnonymousClass0NO.A0B(TAG, "unable to get output file path", e);
                        }
                    } else {
                        int i = r27.A00;
                        if (file2.isDirectory()) {
                            fileArr = file2.listFiles();
                            if (fileArr == null || fileArr.length == 0) {
                                try {
                                    throw new IllegalStateException("no buffers available");
                                } catch (IOException | IllegalStateException e2) {
                                    AnonymousClass0NO.A0B(TAG, "unable to mux instant replay temp files", e2);
                                    Event A22 = ((EventManager) AnonymousClass0J2.A03(2, 242, fileCapture._UL_mInjectionContext)).A22(INSTANT_REPLAY_SAVE_ERROR_EVENT);
                                    String message = e2.getMessage();
                                    if (message != null) {
                                        A22.A15("exception", message);
                                    }
                                    A22.A5L();
                                    if (file2.exists()) {
                                    }
                                } finally {
                                    file2.delete();
                                }
                            } else {
                                Arrays.sort(fileArr, AnonymousClass1Xx.A00);
                            }
                        } else {
                            fileArr = new File[]{file2};
                        }
                        long j = 0;
                        for (File file3 : fileArr) {
                            if (file3 instanceof AnonymousClass1XI) {
                                r14 = (AnonymousClass1XI) file3;
                            } else {
                                r14 = new AnonymousClass1XI(file3);
                            }
                            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new AnonymousClass1X0(r14)));
                            while (dataInputStream.available() > 0) {
                                MediaCodec.BufferInfo A00 = AnonymousClass1Xx.A00(dataInputStream);
                                boolean z = true;
                                if (dataInputStream.readInt() != 1) {
                                    z = false;
                                }
                                byte[] bArr = new byte[A00.size];
                                dataInputStream.read(bArr);
                                ByteBuffer.wrap(bArr);
                                if (z) {
                                    j = A00.presentationTimeUs;
                                }
                            }
                            dataInputStream.close();
                        }
                        long j2 = j - 2000000;
                        if (j2 > 0) {
                            MediaMuxer mediaMuxer = new MediaMuxer(file2.getCanonicalPath(), 0);
                            int addTrack = mediaMuxer.addTrack(mediaFormat2);
                            int addTrack2 = mediaMuxer.addTrack(mediaFormat);
                            mediaMuxer.setOrientationHint(i);
                            mediaMuxer.start();
                            long j3 = j2 - 15000000;
                            long j4 = 0;
                            boolean z2 = false;
                            for (File file4 : fileArr) {
                                if (file4 instanceof AnonymousClass1XI) {
                                    r22 = (AnonymousClass1XI) file4;
                                } else {
                                    r22 = new AnonymousClass1XI(file4);
                                }
                                DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(new AnonymousClass1X0(r22)));
                                while (dataInputStream2.available() > 0) {
                                    MediaCodec.BufferInfo A002 = AnonymousClass1Xx.A00(dataInputStream2);
                                    boolean z3 = false;
                                    if (dataInputStream2.readInt() == 1) {
                                        z3 = true;
                                    }
                                    byte[] bArr2 = new byte[A002.size];
                                    dataInputStream2.read(bArr2);
                                    ByteBuffer wrap = ByteBuffer.wrap(bArr2);
                                    if (z3) {
                                        boolean z4 = true;
                                        if ((A002.flags & 1) == 0) {
                                            z4 = false;
                                        }
                                        if (!z2) {
                                            if (z4) {
                                                long j5 = A002.presentationTimeUs;
                                                if (j5 >= j3) {
                                                    j4 = j5;
                                                    z2 = true;
                                                }
                                            }
                                        }
                                        if (A002.presentationTimeUs <= j4 + 15000000) {
                                            mediaMuxer.writeSampleData(addTrack2, wrap, A002);
                                        }
                                    }
                                }
                                dataInputStream2.close();
                            }
                            boolean z5 = false;
                            for (File file5 : fileArr) {
                                if (file5 instanceof AnonymousClass1XI) {
                                    r2 = (AnonymousClass1XI) file5;
                                } else {
                                    r2 = new AnonymousClass1XI(file5);
                                }
                                DataInputStream dataInputStream3 = new DataInputStream(new BufferedInputStream(new AnonymousClass1X0(r2)));
                                while (dataInputStream3.available() > 0) {
                                    MediaCodec.BufferInfo A003 = AnonymousClass1Xx.A00(dataInputStream3);
                                    boolean z6 = false;
                                    if (dataInputStream3.readInt() == 0) {
                                        z6 = true;
                                    }
                                    byte[] bArr3 = new byte[A003.size];
                                    dataInputStream3.read(bArr3);
                                    ByteBuffer wrap2 = ByteBuffer.wrap(bArr3);
                                    if (z6) {
                                        long j6 = A003.presentationTimeUs;
                                        if (j6 >= j4 && j6 <= j4 + 15000000) {
                                            mediaMuxer.writeSampleData(addTrack, wrap2, A003);
                                            z5 = true;
                                        }
                                    }
                                }
                                dataInputStream3.close();
                            }
                            if (z5) {
                                mediaMuxer.stop();
                                mediaMuxer.release();
                            }
                        }
                        file2.delete();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    @VisibleForTesting
    public final void A03(Mode mode, final File file, final boolean z, final boolean z2) {
        if (mode == Mode.CAPTURE_TO_DISK && file != null) {
            MediaScannerConnection.scanFile(this.mContext, new String[]{file.getPath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /* class com.oculus.horizon.service_media.FileCapture.AnonymousClass3 */

                public final void onScanCompleted(String str, Uri uri) {
                    String str2;
                    OVRMediaServiceNotification oVRMediaServiceNotification;
                    Bitmap A00;
                    String string;
                    Context context;
                    int i;
                    if (z2) {
                        String str3 = null;
                        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(3, 399, FileCapture.this._UL_mInjectionContext)).A36(36310293470511114L)) {
                            str2 = uri.getLastPathSegment();
                            str3 = file.getPath();
                        } else {
                            str2 = null;
                        }
                        if (z) {
                            oVRMediaServiceNotification = (OVRMediaServiceNotification) AnonymousClass0J2.A03(0, 149, FileCapture.this._UL_mInjectionContext);
                            A00 = OVRMediaServiceNotification.A00(oVRMediaServiceNotification, str2, true);
                            string = oVRMediaServiceNotification.mContext.getString(R.string.instant_replay_saved_aui_title);
                            context = oVRMediaServiceNotification.mContext;
                            i = R.string.instant_replay_saved_aui_message;
                        } else {
                            oVRMediaServiceNotification = (OVRMediaServiceNotification) AnonymousClass0J2.A03(0, 149, FileCapture.this._UL_mInjectionContext);
                            if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, oVRMediaServiceNotification._UL_mInjectionContext)).A36(MC.oculus_share_interactive_capture_notifications.enabled)) {
                                A00 = OVRMediaServiceNotification.A00(oVRMediaServiceNotification, str2, true);
                                string = oVRMediaServiceNotification.mContext.getString(R.string.capture_recording_success_aui_title);
                                context = oVRMediaServiceNotification.mContext;
                                i = R.string.capture_recording_success_aui_message;
                            } else {
                                OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.capture_recording_success_title), oVRMediaServiceNotification.mContext.getString(R.string.capture_recording_success_message));
                                return;
                            }
                        }
                        OVRMediaServiceNotification.A04(oVRMediaServiceNotification, string, context.getString(i), A00, str3);
                    }
                }
            });
        }
    }

    public final boolean A04() {
        File[] listFiles;
        File file = new File(this.mContext.getFilesDir(), INSTANT_REPLAY_TEMP_DIR);
        final HashMap<String, AnonymousClass1X9> hashMap = this.mPendingInstantReplaysToSave;
        synchronized (this.mLock) {
            if (!file.exists() || (listFiles = file.listFiles(new FileFilter() {
                /* class com.oculus.horizon.service_media.FileCapture.AnonymousClass4 */

                public final boolean accept(File file) {
                    try {
                        return !hashMap.containsKey(file.getCanonicalPath());
                    } catch (IOException unused) {
                        return true;
                    }
                }
            })) == null) {
                return false;
            }
            Intent intent = new Intent(INSTANT_REPLAY_STATE_UPDATE_ACTION);
            intent.putExtra(INTENT_PARAM_IS_INSTANT_REPLAY_AVAILABLE, false);
            intent.setPackage("com.oculus.systemux");
            this.mContext.sendBroadcast(intent);
            for (File file2 : listFiles) {
                try {
                    file2.delete();
                } catch (SecurityException e) {
                    AnonymousClass0NO.A0B(TAG, "Can't delete instant replay", e);
                }
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r1.mIsCapturing == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A05() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.mLock
            monitor-enter(r3)
            r2 = 1
            r1 = 331(0x14b, float:4.64E-43)
            X.0QC r0 = r4._UL_mInjectionContext     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = X.AnonymousClass0J2.A03(r2, r1, r0)     // Catch:{ all -> 0x001a }
            com.oculus.horizon.capture.ScreenCapture r1 = (com.oculus.horizon.capture.ScreenCapture) r1     // Catch:{ all -> 0x001a }
            com.oculus.horizon.capture.CaptureDelegate r0 = r1.mCaptureDelegate     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0017
            boolean r1 = r1.mIsCapturing     // Catch:{ all -> 0x001a }
            r0 = 1
            if (r1 != 0) goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            return r0
        L_0x001a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.FileCapture.A05():boolean");
    }

    @Override // com.oculus.horizon.abuse_prevention.interfaces.AbuseCaptureProvider
    public final boolean A9F(@Nullable String str, File file, String str2) {
        synchronized (this.mLock) {
            this.mAbuseRecordingUUID = str2;
            this.mAbuseRecordingStartTime = System.currentTimeMillis();
        }
        return A01(this, str, file, Mode.ABUSE_CAPTURE, false);
    }

    @Override // com.oculus.horizon.abuse_prevention.interfaces.AbuseCaptureProvider
    public final boolean A9R(@Nullable String str) {
        synchronized (this.mLock) {
            if (A05()) {
                if (str == null) {
                    return false;
                }
                try {
                    this.mSurface = null;
                    this.mSurfaceCapture.A01(str);
                    ScreenCapture screenCapture = (ScreenCapture) AnonymousClass0J2.A03(1, 331, this._UL_mInjectionContext);
                    CaptureDelegate captureDelegate = screenCapture.mCaptureDelegate;
                    if (captureDelegate == null) {
                        AnonymousClass0NO.A08(ScreenCapture.TAG, "CaptureDelegate is null, setup first!");
                    } else {
                        captureDelegate.A9T();
                        screenCapture.mIsCapturing = false;
                    }
                } finally {
                    PlatformPluginManager.nativeCapturedAudioRenderer_Delete(this.mNativeAudioRenderer);
                    this.mNativeAudioRenderer = 0;
                    this.mAudioCapture = null;
                    this.mRecordingFile = null;
                    this.mCaptureMode = Mode.UNKNOWN;
                    this.mAbuseRecordingUUID = null;
                    this.mAbuseRecordingStartTime = 0;
                    A02();
                }
            }
            return true;
        }
    }

    static {
        String path = Environment.getExternalStorageDirectory().getPath();
        String str = File.separator;
        VIDEO_OUTPUT_DIR = AnonymousClass006.A09(path, str, DeviceUtils.OCULUS_DIR, str, "VideoShots");
        INSTANT_REPLAY_TEMP_DIR = AnonymousClass006.A07(DeviceUtils.OCULUS_DIR, str, "InstantReplayTemp");
    }

    @Inject
    public FileCapture(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
        this.mContext = C003108z.A02(r3);
        this.mSurfaceCapture = (SurfaceCapture) AnonymousClass117.A00(125, r3);
        this.mPlatformPluginManager = (PlatformPluginManager) AnonymousClass117.A00(160, r3);
        this.mCaptureAnalytics = (CaptureAnalytics) AnonymousClass117.A00(275, r3);
        this.mForegroundAppChecker = (ForegroundAppChecker) AnonymousClass117.A00(192, r3);
    }

    public final void A02() {
        Mode mode;
        boolean z;
        String str;
        long j;
        boolean A05 = A05();
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.mLock) {
            mode = this.mCaptureMode;
            z = this.mIsInstantReplay;
            str = this.mAbuseRecordingUUID;
            j = currentTimeMillis - this.mAbuseRecordingStartTime;
        }
        Intent intent = new Intent(CAPTURE_STATE_UPDATE_ACTION);
        intent.putExtra("is_capture_running", A05);
        intent.putExtra("is_instant_replay", z);
        intent.putExtra(INTENT_PARAM_CAPTURE_MODE, mode.mVal);
        intent.putExtra(INTENT_PARAM_ABUSE_RECORDING_UUID, str);
        intent.putExtra(INTENT_PARAM_ABUSE_RECORDING_ELAPSED_TIME, j);
        intent.setPackage("com.oculus.systemux");
        this.mContext.sendBroadcast(intent);
    }
}
