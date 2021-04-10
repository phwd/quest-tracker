package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public abstract class ViewHighlighter {
    public abstract void clearHighlight();

    public abstract void setHighlightedView(View view, @Nullable Rect rect, int i);

    public static ViewHighlighter newInstance() {
        if (Build.VERSION.SDK_INT >= 18) {
            return new OverlayHighlighter();
        }
        LogUtil.w("Running on pre-JBMR2: View highlighting is not supported");
        return new NoopHighlighter();
    }

    protected ViewHighlighter() {
    }

    /* access modifiers changed from: private */
    public static final class NoopHighlighter extends ViewHighlighter {
        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void clearHighlight() {
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void setHighlightedView(View view, @Nullable Rect rect, int i) {
        }

        private NoopHighlighter() {
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(18)
    public static final class OverlayHighlighter extends ViewHighlighter {
        private AtomicReference<Rect> mBoundsToHighlight = new AtomicReference<>();
        private AtomicInteger mContentColor = new AtomicInteger();
        private final Rect mEmptyRect = new Rect();
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private final ViewHighlightOverlays mHighlightOverlays = ViewHighlightOverlays.newInstance();
        private final Runnable mHighlightViewOnUiThreadRunnable = new Runnable() {
            /* class com.facebook.stetho.inspector.elements.android.ViewHighlighter.OverlayHighlighter.AnonymousClass1 */

            public void run() {
                OverlayHighlighter.this.highlightViewOnUiThread();
            }
        };
        private final Rect mHighlightedBounds = new Rect();
        private View mHighlightedView;
        private AtomicReference<View> mViewToHighlight = new AtomicReference<>();

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void clearHighlight() {
            setHighlightedViewImpl(null, null, 0);
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void setHighlightedView(View view, @Nullable Rect rect, int i) {
            setHighlightedViewImpl((View) Util.throwIfNull(view), rect, i);
        }

        private void setHighlightedViewImpl(@Nullable View view, @Nullable Rect rect, int i) {
            this.mHandler.removeCallbacks(this.mHighlightViewOnUiThreadRunnable);
            this.mViewToHighlight.set(view);
            this.mBoundsToHighlight.set(rect);
            this.mContentColor.set(i);
            this.mHandler.postDelayed(this.mHighlightViewOnUiThreadRunnable, 100);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void highlightViewOnUiThread() {
            View andSet = this.mViewToHighlight.getAndSet(null);
            Rect andSet2 = this.mBoundsToHighlight.getAndSet(null);
            if (andSet2 == null) {
                andSet2 = this.mEmptyRect;
            }
            if (andSet != this.mHighlightedView || !this.mHighlightedBounds.equals(andSet2)) {
                View view = this.mHighlightedView;
                if (view != null) {
                    this.mHighlightOverlays.removeHighlight(view);
                }
                if (andSet != null) {
                    this.mHighlightOverlays.highlightView(andSet, andSet2, this.mContentColor.get());
                }
                this.mHighlightedView = andSet;
                if (andSet2 == null) {
                    this.mHighlightedBounds.setEmpty();
                } else {
                    this.mHighlightedBounds.set(andSet2);
                }
            }
        }
    }
}
