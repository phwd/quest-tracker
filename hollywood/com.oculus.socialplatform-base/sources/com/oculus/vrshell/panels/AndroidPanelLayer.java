package com.oculus.vrshell.panels;

import X.AnonymousClass006;
import android.app.Presentation;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.oculus.vrshell.notifications.NotificationUri;

public final class AndroidPanelLayer {
    public static final int MAX_LAYER_HEIGHT_DIP = 1000;
    public static final int MAX_LAYER_WIDTH_DIP = 1000;
    public static final float MAX_POINTER_DELTA = 0.5f;
    public static final float MIN_POINTER_DELTA = 1.0E-4f;
    public static final int PANEL_SHAPE_EQUIRECT_180 = 13;
    public static final int PANEL_SHAPE_EQUIRECT_360 = 12;
    public static final int PANEL_SHAPE_FLAT = 1;
    public static final int PANEL_SHAPE_HIDDEN = 0;
    public static final int PANEL_SHAPE_LANDSCAPE_CYLINDER = 2;
    public static final int PANEL_SHAPE_PORTRAIT_CYLINDER = 3;
    public static final int PANEL_SHAPE_SCREEN = 14;
    public static final float SCROLLING_DECREASE_FACTOR = 0.96f;
    public static final float SCROLLING_SCALE = 0.01f;
    public static final String TAG = "AndroidPanelLayer";
    public static final float THUMBSTICK_SCROLL_FACTOR = 0.25f;
    public static final float TOUCH_SCROLL_THRESHOLD = 50.0f;
    public View mContentView;
    public final Context mContext;
    public Context mContextWrapper;
    public long mCurrentSizeRequestVersion = 0;
    public float mDensity;
    public int mHeightInPixels;
    public boolean mIsScrollingX;
    public boolean mIsScrollingY;
    public long mLastButtons;
    public float mLastCursorX = -1.0f;
    public float mLastCursorY = -1.0f;
    public float mLastTouchX;
    public float mLastTouchY;
    public LayoutInflater mLayoutInflater;
    public long mPendingSizeRequestVersion = 0;
    public PointerForwarder mPointerForwarder;
    public float mPosX;
    public float mPosY;
    public Presentation mPresentation;
    public float mRotX;
    public float mRotY;
    public float mRotZ;
    public float mSmoothPointerDx;
    public float mSmoothPointerDy;
    public Spec mSpec;
    public boolean mSupersampled;
    public final Surface mSurface;
    public boolean mSystemPositioned;
    public float mTouchOriginX;
    public float mTouchOriginY;
    public VirtualDisplay mVirtualDisplay;
    public String mVirtualDisplayName;
    public boolean mVisibility = false;
    public int mWidthInPixels;
    public AndroidPanelWindowManager mWindowManager;

    public enum BorderRadiusType {
        All(NotificationUri.ALL),
        Left("left"),
        Right("right");
        
        public final String mIPCString;

        public String getIPCString() {
            return this.mIPCString;
        }

        /* access modifiers changed from: public */
        BorderRadiusType(String str) {
            this.mIPCString = str;
        }
    }

    public enum HitTestingBehavior {
        HIT_TESTABLE,
        NOT_HIT_TESTABLE
    }

    public enum ResizeBehavior {
        NONE,
        WRAP_CONTENT_WIDTH,
        WRAP_CONTENT_HEIGHT,
        WRAP_CONTENT_WIDTH_HEIGHT
    }

    private void createPresentation() {
        int i = 2;
        VirtualDisplay createVirtualDisplay = ((DisplayManager) this.mContext.getSystemService("display")).createVirtualDisplay(this.mVirtualDisplayName, this.mWidthInPixels, this.mHeightInPixels, this.mContext.getResources().getDisplayMetrics().densityDpi, null, 0);
        this.mVirtualDisplay = createVirtualDisplay;
        this.mPresentation = new Presentation(this.mContext, createVirtualDisplay.getDisplay(), this.mSpec.themeResourceId);
        this.mContextWrapper = wrapPresentationContext();
        this.mWindowManager = new AndroidPanelWindowManager((WindowManager) this.mPresentation.getContext().getSystemService("window"));
        this.mLayoutInflater = LayoutInflater.from(this.mPresentation.getContext()).cloneInContext(this.mContextWrapper);
        this.mPresentation.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.mPresentation.getWindow().addFlags(8);
        if (Build.VERSION.SDK_INT >= 26) {
            i = 2030;
        }
        this.mPresentation.getWindow().setType(i);
    }

    private void dispatchMotionEvents(float f, float f2, float f3, float f4, long j, long j2) {
        float f5 = f2;
        float f6 = f;
        if (this.mPointerForwarder != null) {
            if ((j2 & PanelFlags.GAZE_VALID.getFlagValue()) == 0) {
                f6 = -1.0f;
                f5 = -1.0f;
            }
            handleJoystickScrollInputEvents(j, f6, f5, f3, f4);
            handleTouchScrollInputEvents(j, f6, f5, f3, f4);
            handleClickInputEvents(j, f6, f5);
            handleHoverEvents(f6, f5);
            this.mLastCursorX = f6;
            this.mLastCursorY = f5;
            this.mLastTouchX = f3;
            this.mLastTouchY = f4;
            this.mLastButtons = j;
        }
    }

    private boolean insideLayer(float f, float f2) {
        if (f2 < Spec.DEFAULT_CYLINDER_POSITION_Z || f2 > ((float) this.mHeightInPixels) || f < Spec.DEFAULT_CYLINDER_POSITION_Z || f > ((float) this.mWidthInPixels)) {
            return false;
        }
        return true;
    }

    public static boolean isButtonPressed(long j, long j2, long j3) {
        return (j2 & j3) == j3 && (j & j3) != j3;
    }

    public static boolean isButtonPressing(long j, long j2, long j3) {
        return (j2 & j3) == j3 && (j & j3) == j3;
    }

    public void destroy() {
        this.mContentView = null;
        destroyPresentation();
    }

    public void setPositionAndRotation(float f, float f2, float f3, float f4, float f5) {
        this.mPosX = f;
        this.mPosY = f2;
        this.mRotX = f3;
        this.mRotY = f4;
        this.mRotZ = f5;
        this.mSystemPositioned = false;
    }

    public void setSystemPositioned() {
        this.mSystemPositioned = true;
    }

    public enum Shape {
        Hidden(0),
        Flat(1),
        LandscapeCylinder(2),
        PortraitCylinder(3),
        Equirect360(12),
        Equirect180(13),
        Screen(14);
        
        public final int value;

        /* access modifiers changed from: public */
        Shape(int i) {
            this.value = i;
        }
    }

    private void destroyPresentation() {
        Presentation presentation = this.mPresentation;
        if (presentation != null) {
            presentation.dismiss();
        }
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay != null) {
            virtualDisplay.release();
        }
        this.mPresentation = null;
        this.mVirtualDisplay = null;
    }

    private void handleJoystickScrollInputEvents(long j, float f, float f2, float f3, float f4) {
        if (isButtonPressing(this.mLastButtons, j, PanelButton.JOYSTICK_MOVE.getFlagValue())) {
            this.mPointerForwarder.touchScroll(f, f2, f3 * 0.25f, f4 * 0.25f);
        }
    }

    private void handleTouchScrollInputEvents(long j, float f, float f2, float f3, float f4) {
        if ((PanelButton.getClickOrTriggerFlagValue() & j) == 0) {
            long j2 = this.mLastButtons;
            long flagValue = PanelButton.TOUCH.getFlagValue();
            boolean z = false;
            if (isButtonPressed(j2, j, flagValue) || isButtonPressed(j2, j, PanelButton.REMOTE_TOUCH.getFlagValue())) {
                this.mTouchOriginX = f3;
                this.mTouchOriginY = f4;
                this.mIsScrollingY = false;
                this.mIsScrollingX = false;
            }
            if (isButtonPressing(j2, j, flagValue) || isButtonPressing(j2, j, PanelButton.REMOTE_TOUCH.getFlagValue())) {
                boolean z2 = this.mIsScrollingX;
                boolean z3 = false;
                if (Math.abs(f3 - this.mTouchOriginX) > 50.0f) {
                    z3 = true;
                }
                boolean z4 = z2 | z3;
                this.mIsScrollingX = z4;
                boolean z5 = this.mIsScrollingY;
                if (Math.abs(f4 - this.mTouchOriginY) > 50.0f) {
                    z = true;
                }
                boolean z6 = z5 | z;
                this.mIsScrollingY = z6;
                if (z6) {
                    this.mSmoothPointerDy = getSmoothPointerDelta(this.mSmoothPointerDy, f4 - this.mLastTouchY);
                }
                if (z4) {
                    this.mSmoothPointerDx = getSmoothPointerDelta(this.mSmoothPointerDx, f3 - this.mLastTouchX);
                }
            }
            float f5 = this.mSmoothPointerDy;
            if (Math.abs(f5) > Spec.DEFAULT_CYLINDER_POSITION_Z) {
                float autoScrollingDelta = getAutoScrollingDelta(f5);
                this.mSmoothPointerDy = autoScrollingDelta;
                this.mPointerForwarder.touchScroll(f, f2, Spec.DEFAULT_CYLINDER_POSITION_Z, autoScrollingDelta);
            }
            float f6 = this.mSmoothPointerDx;
            if (Math.abs(f6) > Spec.DEFAULT_CYLINDER_POSITION_Z) {
                float autoScrollingDelta2 = getAutoScrollingDelta(f6);
                this.mSmoothPointerDx = autoScrollingDelta2;
                this.mPointerForwarder.touchScroll(f, f2, autoScrollingDelta2, Spec.DEFAULT_CYLINDER_POSITION_Z);
            }
        }
    }

    private void onPointerDown(long j, float f, float f2) {
        this.mPointerForwarder.pointerPressed(f, f2);
    }

    private void onPointerUp(long j) {
        this.mPointerForwarder.pointerReleased(this.mLastCursorX, this.mLastCursorY);
    }

    private void onPointerUpdate(long j, float f, float f2) {
        this.mPointerForwarder.pointerDragged(f, f2);
    }

    private Context wrapPresentationContext() {
        return new ContextWrapper(this.mPresentation.getContext()) {
            /* class com.oculus.vrshell.panels.AndroidPanelLayer.AnonymousClass1 */

            @Override // android.content.Context, android.content.ContextWrapper
            public Object getSystemService(String str) {
                if ("window".equals(str)) {
                    return AndroidPanelLayer.this.mWindowManager;
                }
                if ("layout_inflater".equals(str)) {
                    return AndroidPanelLayer.this.mLayoutInflater;
                }
                return super.getSystemService(str);
            }
        };
    }

    public View getContentView() {
        return this.mContentView;
    }

    public float getDensity() {
        return this.mDensity;
    }

    public int getHeightInPixels() {
        return this.mHeightInPixels;
    }

    public String getName() {
        return this.mSpec.name;
    }

    public float getPositionX() {
        return this.mPosX;
    }

    public float getPositionY() {
        return this.mPosY;
    }

    public Context getPresentationContext() {
        return this.mContextWrapper;
    }

    public ResizeBehavior getResizeBehavior() {
        return this.mSpec.resizeBehavior;
    }

    public float getRotationX() {
        return this.mRotX;
    }

    public float getRotationY() {
        return this.mRotY;
    }

    public float getRotationZ() {
        return this.mRotZ;
    }

    public Shape getShape() {
        return this.mSpec.shape;
    }

    public boolean getVisibility() {
        return this.mVisibility;
    }

    public int getWidthInPixels() {
        return this.mWidthInPixels;
    }

    public boolean hasPresentation() {
        if (this.mPresentation != null) {
            return true;
        }
        return false;
    }

    public boolean isHitTestable() {
        if (this.mSpec.hitTestingBehavior == HitTestingBehavior.HIT_TESTABLE) {
            return true;
        }
        return false;
    }

    public boolean isSupersampled() {
        return this.mSupersampled;
    }

    public boolean isSystemPositioned() {
        return this.mSystemPositioned;
    }

    public boolean isVisible() {
        Presentation presentation;
        View view;
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || virtualDisplay.getSurface() == null || (presentation = this.mPresentation) == null || !presentation.isShowing() || (view = this.mContentView) == null || view.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public boolean needsToBeSized() {
        if (this.mPendingSizeRequestVersion != this.mCurrentSizeRequestVersion) {
            return true;
        }
        return false;
    }

    public void onPanelSizeRequested() {
        this.mPendingSizeRequestVersion++;
    }

    public void onPanelSizedResponse() {
        long j = this.mPendingSizeRequestVersion;
        long j2 = this.mCurrentSizeRequestVersion + 1;
        this.mCurrentSizeRequestVersion = j2;
        this.mCurrentSizeRequestVersion = Math.min(j, j2);
        View view = this.mContentView;
        if (view != null) {
            view.invalidate();
        }
    }

    public void resetPanelSizeRequestVersions() {
        this.mCurrentSizeRequestVersion = this.mPendingSizeRequestVersion;
    }

    public void setContentView(View view) {
        Presentation presentation = this.mPresentation;
        if (presentation == null) {
            Log.w(TAG, String.format("To set content view on layer \"%s\", the presentation must be created first", this.mSpec.name));
            return;
        }
        this.mContentView = view;
        this.mPointerForwarder = new PointerForwarder(view, this.mWindowManager);
        presentation.setContentView(view, new ViewGroup.LayoutParams(this.mWidthInPixels, this.mHeightInPixels));
    }

    public void setSize(int i, int i2) {
        if (this.mWidthInPixels != i || this.mHeightInPixels != i2) {
            this.mWidthInPixels = i;
            this.mHeightInPixels = i2;
            destroyPresentation();
            createPresentation();
        }
    }

    public void setSpec(Spec spec) {
        String str = spec.name;
        this.mSpec = spec;
        this.mVirtualDisplayName = AnonymousClass006.A07("com.oculus.android_panel_app.AndroidPanelLayer-", str);
        this.mSystemPositioned = spec.isSystemPositioned;
        this.mDensity = spec.density;
        this.mSupersampled = spec.supersampled;
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int dipToPixelsInt = DensityUtils.dipToPixelsInt((float) spec.width, displayMetrics);
        int dipToPixelsInt2 = DensityUtils.dipToPixelsInt((float) spec.height, displayMetrics);
        ResizeBehavior resizeBehavior = spec.resizeBehavior;
        if (resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            dipToPixelsInt = DensityUtils.dipToPixelsInt(1000.0f, displayMetrics);
        }
        ResizeBehavior resizeBehavior2 = spec.resizeBehavior;
        if (resizeBehavior2 == ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior2 == ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            dipToPixelsInt2 = DensityUtils.dipToPixelsInt(1000.0f, displayMetrics);
        }
        if (spec.isSystemPositioned) {
            this.mSystemPositioned = true;
        } else {
            setPositionAndRotation(spec.posX, spec.posY, spec.rotX, spec.rotY, spec.rotZ);
        }
        setSize(dipToPixelsInt, dipToPixelsInt2);
    }

    public void show() {
        String str = this.mSpec.name;
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || this.mPresentation == null) {
            Log.w(TAG, String.format("To show layer \"%s\", both the virtual display and presentation must be created first", str));
            return;
        }
        virtualDisplay.setSurface(this.mSurface);
        this.mPresentation.getWindow().setType(2030);
        unhide();
    }

    public void unhide() {
        View view;
        String str = this.mSpec.name;
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || virtualDisplay.getSurface() == null || this.mPresentation == null || (view = this.mContentView) == null) {
            Log.w(TAG, String.format("To unhide layer \"%s\", the surface must be set on the virtual display with non-null presentation and content", str));
            return;
        }
        view.setVisibility(0);
        this.mPresentation.show();
    }

    public AndroidPanelLayer(Context context, Surface surface, Spec spec) {
        this.mContext = context;
        this.mSurface = surface;
        setSpec(spec);
    }

    public static float getAutoScrollingDelta(float f) {
        float f2 = f * 0.96f;
        if (Math.abs(f2) < 1.0E-4f) {
            return Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
        return f2;
    }

    public static float getSmoothPointerDelta(float f, float f2) {
        float f3 = (f + (f2 * 0.01f)) / 2.0f;
        if (Math.abs(f3) < 1.0E-4f) {
            return Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
        return Math.max(Math.min(f3, 0.5f), -0.5f);
    }

    private void handleClickInputEvents(long j, float f, float f2) {
        long anyActionFlagValue = PanelButton.getAnyActionFlagValue();
        boolean z = true;
        boolean z2 = false;
        if ((anyActionFlagValue & j) != 0) {
            z2 = true;
        }
        if ((this.mLastButtons & anyActionFlagValue) == 0) {
            z = false;
        }
        if (z2) {
            if (z) {
                onPointerUpdate(j, f, f2);
            } else {
                onPointerDown(j, f, f2);
            }
        } else if (z) {
            onPointerUp(j);
        }
    }

    private void handleHoverEvents(float f, float f2) {
        boolean insideLayer = insideLayer(f, f2);
        if (insideLayer(this.mLastCursorX, this.mLastCursorY)) {
            if (insideLayer) {
                this.mPointerForwarder.hoverMove(f, f2);
            } else {
                this.mPointerForwarder.hoverExit(f, f2);
            }
        } else if (insideLayer) {
            this.mPointerForwarder.hoverEnter(f, f2);
        }
    }

    public void setVisibility(boolean z) {
        this.mVisibility = z;
    }

    public void frame() {
        handleHoverEvents(-1.0f, -1.0f);
        this.mLastCursorX = -1.0f;
        this.mLastCursorY = -1.0f;
        this.mLastButtons = 0;
        this.mIsScrollingX = false;
        this.mIsScrollingY = false;
    }

    public void frame(InputFrame inputFrame) {
        dispatchMotionEvents(inputFrame.mCursorX * ((float) this.mWidthInPixels), (1.0f - inputFrame.mCursorY) * ((float) this.mHeightInPixels), inputFrame.mTouchX, inputFrame.mTouchY, inputFrame.mButtons, inputFrame.mFlags);
    }

    public static final class Spec {
        public static final float DEFAULT_CYLINDER_POSITION_Z = 0.0f;
        public static final float DEFAULT_DENSITY = 4680.0f;
        public static final float DEFAULT_PLANE_POSITION_Z = -2.0f;
        public final float density;
        public final int height;
        public final HitTestingBehavior hitTestingBehavior;
        public boolean isSystemPositioned;
        public final String name;
        public final float posX;
        public final float posY;
        public final ResizeBehavior resizeBehavior;
        public final float rotX;
        public final float rotY;
        public final float rotZ;
        public final Shape shape;
        public final boolean supersampled;
        public final int themeResourceId;
        public final int width;

        public Spec(String str, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, boolean z, ResizeBehavior resizeBehavior2, HitTestingBehavior hitTestingBehavior2, Shape shape2, int i3) {
            this.name = str;
            this.width = i;
            this.height = i2;
            this.resizeBehavior = resizeBehavior2;
            this.hitTestingBehavior = hitTestingBehavior2;
            this.shape = shape2;
            this.themeResourceId = i3;
            this.posX = f;
            this.posY = f2;
            this.rotX = f3;
            this.rotY = f4;
            this.rotZ = f5;
            this.isSystemPositioned = false;
            this.density = f6;
            this.supersampled = z;
        }

        public Spec(String str, int i, int i2, ResizeBehavior resizeBehavior2, HitTestingBehavior hitTestingBehavior2, Shape shape2, int i3) {
            this.name = str;
            this.width = i;
            this.height = i2;
            this.resizeBehavior = resizeBehavior2;
            this.hitTestingBehavior = hitTestingBehavior2;
            this.shape = shape2;
            this.themeResourceId = i3;
            this.posX = DEFAULT_CYLINDER_POSITION_Z;
            this.posY = DEFAULT_CYLINDER_POSITION_Z;
            this.rotX = DEFAULT_CYLINDER_POSITION_Z;
            this.rotY = DEFAULT_CYLINDER_POSITION_Z;
            this.rotZ = DEFAULT_CYLINDER_POSITION_Z;
            this.density = 4680.0f;
            this.supersampled = false;
            this.isSystemPositioned = true;
        }
    }
}
