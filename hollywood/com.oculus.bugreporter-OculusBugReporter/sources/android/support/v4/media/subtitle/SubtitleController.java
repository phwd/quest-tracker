package android.support.v4.media.subtitle;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.subtitle.SubtitleTrack;
import android.view.accessibility.CaptioningManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

@RequiresApi(28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SubtitleController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int WHAT_HIDE = 2;
    private static final int WHAT_SELECT_DEFAULT_TRACK = 4;
    private static final int WHAT_SELECT_TRACK = 3;
    private static final int WHAT_SHOW = 1;
    private Anchor mAnchor;
    private final Handler.Callback mCallback;
    private CaptioningManager.CaptioningChangeListener mCaptioningChangeListener;
    private CaptioningManager mCaptioningManager;
    private Handler mHandler;
    private Listener mListener;
    private ArrayList<Renderer> mRenderers;
    private final Object mRenderersLock;
    private SubtitleTrack mSelectedTrack;
    private boolean mShowing;
    private MediaTimeProvider mTimeProvider;
    private boolean mTrackIsExplicit;
    private ArrayList<SubtitleTrack> mTracks;
    private final Object mTracksLock;
    private boolean mVisibilityIsExplicit;

    public interface Anchor {
        Looper getSubtitleLooper();

        void setSubtitleWidget(SubtitleTrack.RenderingWidget renderingWidget);
    }

    /* access modifiers changed from: package-private */
    public interface Listener {
        void onSubtitleTrackSelected(SubtitleTrack subtitleTrack);
    }

    public static abstract class Renderer {
        public abstract SubtitleTrack createTrack(MediaFormat mediaFormat);

        public abstract boolean supports(MediaFormat mediaFormat);
    }

    public SubtitleController(Context context) {
        this(context, null, null);
    }

    public SubtitleController(Context context, MediaTimeProvider timeProvider, Listener listener) {
        this.mRenderersLock = new Object();
        this.mTracksLock = new Object();
        this.mCallback = new Handler.Callback() {
            /* class android.support.v4.media.subtitle.SubtitleController.AnonymousClass1 */

            public boolean handleMessage(Message msg) {
                int i = msg.what;
                if (i == 1) {
                    SubtitleController.this.doShow();
                    return true;
                } else if (i == 2) {
                    SubtitleController.this.doHide();
                    return true;
                } else if (i == 3) {
                    SubtitleController.this.doSelectTrack((SubtitleTrack) msg.obj);
                    return true;
                } else if (i != 4) {
                    return false;
                } else {
                    SubtitleController.this.doSelectDefaultTrack();
                    return true;
                }
            }
        };
        this.mCaptioningChangeListener = new CaptioningManager.CaptioningChangeListener() {
            /* class android.support.v4.media.subtitle.SubtitleController.AnonymousClass2 */

            public void onEnabledChanged(boolean enabled) {
                SubtitleController.this.selectDefaultTrack();
            }

            public void onLocaleChanged(Locale locale) {
                SubtitleController.this.selectDefaultTrack();
            }
        };
        this.mTrackIsExplicit = false;
        this.mVisibilityIsExplicit = false;
        this.mTimeProvider = timeProvider;
        this.mListener = listener;
        this.mRenderers = new ArrayList<>();
        this.mShowing = false;
        this.mTracks = new ArrayList<>();
        this.mCaptioningManager = (CaptioningManager) context.getSystemService("captioning");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
        super.finalize();
    }

    public SubtitleTrack[] getTracks() {
        SubtitleTrack[] tracks;
        synchronized (this.mTracksLock) {
            tracks = new SubtitleTrack[this.mTracks.size()];
            this.mTracks.toArray(tracks);
        }
        return tracks;
    }

    public SubtitleTrack getSelectedTrack() {
        return this.mSelectedTrack;
    }

    private SubtitleTrack.RenderingWidget getRenderingWidget() {
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack == null) {
            return null;
        }
        return subtitleTrack.getRenderingWidget();
    }

    public boolean selectTrack(SubtitleTrack track) {
        if (track != null && !this.mTracks.contains(track)) {
            return false;
        }
        processOnAnchor(this.mHandler.obtainMessage(3, track));
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doSelectTrack(SubtitleTrack track) {
        this.mTrackIsExplicit = true;
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack != track) {
            if (subtitleTrack != null) {
                subtitleTrack.hide();
                this.mSelectedTrack.setTimeProvider(null);
            }
            this.mSelectedTrack = track;
            Anchor anchor = this.mAnchor;
            if (anchor != null) {
                anchor.setSubtitleWidget(getRenderingWidget());
            }
            SubtitleTrack subtitleTrack2 = this.mSelectedTrack;
            if (subtitleTrack2 != null) {
                subtitleTrack2.setTimeProvider(this.mTimeProvider);
                this.mSelectedTrack.show();
            }
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onSubtitleTrackSelected(track);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v4.media.subtitle.SubtitleTrack getDefaultTrack() {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.subtitle.SubtitleController.getDefaultTrack():android.support.v4.media.subtitle.SubtitleTrack");
    }

    /* access modifiers changed from: package-private */
    public static class MediaFormatUtil {
        MediaFormatUtil() {
        }

        static int getInteger(MediaFormat format, String name, int defaultValue) {
            try {
                return format.getInteger(name);
            } catch (ClassCastException | NullPointerException e) {
                return defaultValue;
            }
        }
    }

    public void selectDefaultTrack() {
        processOnAnchor(this.mHandler.obtainMessage(4));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doSelectDefaultTrack() {
        SubtitleTrack subtitleTrack;
        if (this.mTrackIsExplicit) {
            if (!this.mVisibilityIsExplicit) {
                if (this.mCaptioningManager.isEnabled() || !((subtitleTrack = this.mSelectedTrack) == null || MediaFormatUtil.getInteger(subtitleTrack.getFormat(), "is-forced-subtitle", 0) == 0)) {
                    show();
                } else {
                    SubtitleTrack subtitleTrack2 = this.mSelectedTrack;
                    if (subtitleTrack2 != null && subtitleTrack2.getTrackType() == 4) {
                        hide();
                    }
                }
                this.mVisibilityIsExplicit = false;
            } else {
                return;
            }
        }
        SubtitleTrack track = getDefaultTrack();
        if (track != null) {
            selectTrack(track);
            this.mTrackIsExplicit = false;
            if (!this.mVisibilityIsExplicit) {
                show();
                this.mVisibilityIsExplicit = false;
            }
        }
    }

    public void reset() {
        checkAnchorLooper();
        hide();
        selectTrack(null);
        this.mTracks.clear();
        this.mTrackIsExplicit = false;
        this.mVisibilityIsExplicit = false;
        this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
    }

    public SubtitleTrack addTrack(MediaFormat format) {
        SubtitleTrack track;
        synchronized (this.mRenderersLock) {
            Iterator<Renderer> it = this.mRenderers.iterator();
            while (it.hasNext()) {
                Renderer renderer = it.next();
                if (renderer.supports(format) && (track = renderer.createTrack(format)) != null) {
                    synchronized (this.mTracksLock) {
                        if (this.mTracks.size() == 0) {
                            this.mCaptioningManager.addCaptioningChangeListener(this.mCaptioningChangeListener);
                        }
                        this.mTracks.add(track);
                    }
                    return track;
                }
            }
            return null;
        }
    }

    public void show() {
        processOnAnchor(this.mHandler.obtainMessage(1));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doShow() {
        this.mShowing = true;
        this.mVisibilityIsExplicit = true;
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack != null) {
            subtitleTrack.show();
        }
    }

    public void hide() {
        processOnAnchor(this.mHandler.obtainMessage(2));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doHide() {
        this.mVisibilityIsExplicit = true;
        SubtitleTrack subtitleTrack = this.mSelectedTrack;
        if (subtitleTrack != null) {
            subtitleTrack.hide();
        }
        this.mShowing = false;
    }

    public void registerRenderer(Renderer renderer) {
        synchronized (this.mRenderersLock) {
            if (!this.mRenderers.contains(renderer)) {
                this.mRenderers.add(renderer);
            }
        }
    }

    public boolean hasRendererFor(MediaFormat format) {
        synchronized (this.mRenderersLock) {
            Iterator<Renderer> it = this.mRenderers.iterator();
            while (it.hasNext()) {
                if (it.next().supports(format)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void setAnchor(Anchor anchor) {
        Anchor anchor2 = this.mAnchor;
        if (anchor2 != anchor) {
            if (anchor2 != null) {
                checkAnchorLooper();
                this.mAnchor.setSubtitleWidget(null);
            }
            this.mAnchor = anchor;
            this.mHandler = null;
            Anchor anchor3 = this.mAnchor;
            if (anchor3 != null) {
                this.mHandler = new Handler(anchor3.getSubtitleLooper(), this.mCallback);
                checkAnchorLooper();
                this.mAnchor.setSubtitleWidget(getRenderingWidget());
            }
        }
    }

    private void checkAnchorLooper() {
    }

    private void processOnAnchor(Message m) {
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            this.mHandler.dispatchMessage(m);
        } else {
            this.mHandler.sendMessage(m);
        }
    }
}
