package com.oculus.horizon.capture;

import X.AbstractC11021xQ;
import X.AbstractC11041xS;
import X.AbstractC11051xT;
import X.AbstractC11091xe;
import X.AbstractC11131xk;
import X.AnonymousClass006;
import X.AnonymousClass0NO;
import X.AnonymousClass1X9;
import X.AnonymousClass1lF;
import X.AnonymousClass1xD;
import X.AnonymousClass1xF;
import X.AnonymousClass1xG;
import X.AnonymousClass1xK;
import X.AnonymousClass1xN;
import X.AnonymousClass1xP;
import X.AnonymousClass1xh;
import X.AnonymousClass1xu;
import X.AnonymousClass1y4;
import X.AnonymousClass1z3;
import X.AnonymousClass25D;
import X.C11011xI;
import X.C11061xY;
import X.C11081xd;
import X.C11191xr;
import X.C11211xt;
import X.C11281ya;
import X.C11311yd;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.facebook.GraphRequest;
import com.oculus.horizon.capture.ScreenCapture;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Nullable;

public class VideoCapture implements CaptureDelegate {
    public static final int AUDIO_RECORDER_DEQUEUE_INPUT_BUFFER_TIMEOUT_MS = 100;
    public static final int AUDIO_RECORDER_INTERVAL_MS = 100;
    public static final int SAMPLING_RATE = 48000;
    public static final String TAG = "VideoCapture";
    public boolean mAudioEnabled;
    @Nullable
    public Handler mAudioHandler;
    @Nullable
    public volatile AbstractC11041xS mAudioPipelineRecorderOutput;
    @Nullable
    public C11311yd mAudioRecordingTrackConfig;
    public final AnonymousClass1xh mCaptureCallback = new AnonymousClass1xh() {
        /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass2 */

        @Override // X.AnonymousClass1xh
        public final void A5o(C11081xd r4) {
            AnonymousClass0NO.A0B(VideoCapture.TAG, "MP: Failed in recording video", r4);
            VideoCapture videoCapture = VideoCapture.this;
            videoCapture.mStopped = true;
            videoCapture.mVideoStateListener.A60(r4);
            videoCapture.mVideoOutputFile = null;
            videoCapture.mCurrentTimestampUs = 0;
            videoCapture.mRecordingController.release();
        }

        @Override // X.AnonymousClass1xh
        public final void A5p() {
            VideoCapture.this.mStopped = true;
            VideoCapture.this.mCaptureLogger.A00(CaptureLogger.EVENT_CAPTURE_FINISHED);
            VideoCapture videoCapture = VideoCapture.this;
            videoCapture.mVideoStateListener.A72(videoCapture.mVideoOutputFile);
            VideoCapture videoCapture2 = VideoCapture.this;
            videoCapture2.mVideoOutputFile = null;
            videoCapture2.mCurrentTimestampUs = 0;
            videoCapture2.mRecordingController.release();
        }

        @Override // X.AnonymousClass1xh
        public final void A5q(long j) {
            VideoCapture.this.mCaptureLogger.A00(CaptureLogger.EVENT_CAPTURE_STARTED);
        }

        @Override // X.AnonymousClass1xh
        public final long now() {
            return SystemClock.elapsedRealtime();
        }
    };
    public final CaptureLogger mCaptureLogger;
    public long mCurrentTimestampUs;
    public final AbstractC11021xQ mOutputProvider = new AbstractC11021xQ() {
        /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass5 */

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // X.AbstractC11031xR
        public final /* bridge */ /* synthetic */ void A19(AbstractC11051xT r3) {
            final AbstractC11051xT r32 = r3;
            if (r32 instanceof AnonymousClass1xN) {
                ((AnonymousClass1xK) r32).A01 = new Object() {
                    /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass5.AnonymousClass1 */
                };
                return;
            }
            throw new RuntimeException("Incorrect VideoOutput used for recording");
        }
    };
    public final AbstractC11131xk mRecorderPreparedStateCallback = new AbstractC11131xk() {
        /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass3 */

        @Override // X.AbstractC11131xk
        public final void A62(Throwable th) {
            AnonymousClass0NO.A0B(VideoCapture.TAG, "MP: Error in preparing video recorder", th);
            VideoCapture videoCapture = VideoCapture.this;
            IOException iOException = new IOException(th);
            videoCapture.mStopped = true;
            videoCapture.mVideoStateListener.A60(iOException);
            videoCapture.mVideoOutputFile = null;
            videoCapture.mCurrentTimestampUs = 0;
            videoCapture.mRecordingController.release();
        }

        @Override // X.AbstractC11131xk
        public final void onSuccess() {
            VideoCapture videoCapture = VideoCapture.this;
            videoCapture.mRecordingController.A9L(new C11191xr(videoCapture.mVideoOutputFile), videoCapture.mCaptureCallback);
        }
    };
    public final AnonymousClass1xD mRecordingController;
    public final AbstractC11091xe mRecordingLogger = new AbstractC11091xe() {
        /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass4 */

        @Override // X.AbstractC11091xe
        public final void A5M(int i) {
            VideoCapture.this.mCaptureLogger.A01(CaptureLogger.EVENT_QPL_EVENT_FAILED, i, null);
        }

        @Override // X.AbstractC11091xe
        public final void A5N(int i) {
            VideoCapture.this.mCaptureLogger.A01(CaptureLogger.EVENT_QPL_EVENT_FINISHED, i, null);
        }

        @Override // X.AbstractC11091xe
        public final void A5O(int i, String str) {
            VideoCapture.this.mCaptureLogger.A01(CaptureLogger.EVENT_QPL_EVENT_POINT, i, str);
        }

        @Override // X.AbstractC11091xe
        public final void A5P(int i) {
            VideoCapture.this.mCaptureLogger.A01(CaptureLogger.EVENT_QPL_EVENT_STARTED, i, null);
        }

        @Override // X.AbstractC11091xe
        public final void A5Q(String str, String str2, long j, String str3, @Nullable AnonymousClass1lF r8, @Nullable String str4, @Nullable Map<String, String> map) {
            CaptureLogger captureLogger = VideoCapture.this.mCaptureLogger;
            if (r8 != null) {
                captureLogger.A02(str, r8, GraphRequest.DEBUG_PARAM);
            } else {
                captureLogger.A03(map);
            }
        }

        @Override // X.AbstractC11091xe
        public final void A5R(String str, String str2, long j, String str3, AnonymousClass1lF r7, String str4, String str5) {
            VideoCapture.this.mCaptureLogger.A02(str, r7, str4);
        }

        @Override // X.AbstractC11091xe
        public final void A5S(String str, String str2, long j, String str3, @Nullable Map<String, String> map) {
            VideoCapture.this.mCaptureLogger.A03(map);
        }
    };
    public final AnonymousClass1z3 mRecordingMC;
    public volatile boolean mStopped;
    @Nullable
    public File mVideoOutputFile;
    public final ScreenCapture.VideoStateListener mVideoStateListener;
    public final Runnable pollRunnable = new Runnable() {
        /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass1 */

        public final void run() {
            VideoCapture videoCapture;
            Handler handler;
            VideoCapture.this.mVideoStateListener.A2z();
            if (!VideoCapture.this.mStopped && (handler = (videoCapture = VideoCapture.this).mAudioHandler) != null) {
                handler.postDelayed(videoCapture.pollRunnable, 100);
            }
        }
    };

    public VideoCapture(ScreenCapture.VideoStateListener videoStateListener, Context context, AnonymousClass1X9 r12) {
        this.mVideoStateListener = videoStateListener;
        this.mAudioEnabled = true;
        this.mRecordingMC = new AnonymousClass25D() {
            /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass6 */
        };
        this.mCaptureLogger = new CaptureLogger(context);
        AnonymousClass7 r1 = new Object() {
            /* class com.oculus.horizon.capture.VideoCapture.AnonymousClass7 */
        };
        AbstractC11091xe r8 = this.mRecordingLogger;
        AbstractC11021xQ r7 = this.mOutputProvider;
        AnonymousClass1z3 r6 = this.mRecordingMC;
        Handler handler = new Handler(Looper.getMainLooper());
        C11061xY r4 = new C11061xY(new C11211xt(handler, r8), r8, r6);
        AnonymousClass1xu r0 = new AnonymousClass1xu(handler, r8, r6, r12);
        C11211xt r3 = r4.A03;
        r3.A03 = r0;
        C11281ya r2 = new C11281ya(handler, r1, r8, r6);
        r3.A04.put(r2.A4Z(), r2);
        AnonymousClass1xP r22 = new AnonymousClass1xP(handler, r7, r8, r6);
        r3.A04.put(r22.A4Z(), r22);
        this.mRecordingController = r4;
    }

    @Override // com.oculus.horizon.capture.CaptureDelegate
    public final void A5l(byte[] bArr, int i) {
        if (this.mStopped) {
            return;
        }
        if (this.mAudioHandler == null || Looper.myLooper() == this.mAudioHandler.getLooper()) {
            if (this.mAudioPipelineRecorderOutput != null) {
                this.mAudioPipelineRecorderOutput.A4p(bArr, i, this.mCurrentTimestampUs);
            }
            long j = this.mCurrentTimestampUs;
            long j2 = (long) i;
            int i2 = this.mAudioRecordingTrackConfig.A01.A04;
            int i3 = 1;
            if (i2 == 1 || i2 == 2) {
                i3 = 2;
            } else if (i2 != 3) {
                i3 = 4;
                if (i2 != 4) {
                    throw new IllegalArgumentException(AnonymousClass006.A01("Bad audio format ", i2));
                }
            }
            this.mCurrentTimestampUs = j + ((((j2 / ((long) i3)) / ((long) 1)) * 1000000) / 48000);
        }
    }

    @Override // com.oculus.horizon.capture.CaptureDelegate
    public final void A9K(File file, int i, int i2, int i3, int i4, int i5) throws IllegalStateException, IOException {
        this.mVideoOutputFile = file;
        ArrayList arrayList = new ArrayList();
        AnonymousClass1xG r3 = new AnonymousClass1xG();
        r3.A04 = Integer.valueOf(i4);
        r3.A03 = i;
        r3.A01 = i2;
        r3.A00 = i3;
        if (i5 > 0) {
            r3.A02 = i5;
        }
        AnonymousClass1z3 r2 = this.mRecordingMC;
        String A3M = r2.A3M();
        if (A3M != null) {
            r3.A05 = A3M;
        }
        arrayList.add(new AnonymousClass1y4(new AnonymousClass1xF(r3)));
        if (this.mAudioEnabled) {
            C11311yd r0 = new C11311yd(new C11011xI(48000), r2);
            this.mAudioRecordingTrackConfig = r0;
            arrayList.add(r0);
        }
        this.mRecordingController.A7U(arrayList, this.mRecorderPreparedStateCallback, new Handler(Looper.getMainLooper()));
    }

    @Override // com.oculus.horizon.capture.CaptureDelegate
    public final void A9T() {
        this.mRecordingController.A9U(false);
    }
}
