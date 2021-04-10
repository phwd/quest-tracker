package com.oculus.vrshell.panels;

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
import com.oculus.android.exoplayer2.text.ttml.TtmlNode;
import com.oculus.vrshell.notifications.NotificationUri;

public final class AndroidPanelLayer {
    public static final int MAX_LAYER_HEIGHT_DIP = 1000;
    public static final int MAX_LAYER_WIDTH_DIP = 1000;
    private static final float MAX_POINTER_DELTA = 0.5f;
    private static final float MIN_POINTER_DELTA = 1.0E-4f;
    static final int PANEL_SHAPE_EQUIRECT_180 = 13;
    static final int PANEL_SHAPE_EQUIRECT_360 = 12;
    static final int PANEL_SHAPE_FLAT = 1;
    static final int PANEL_SHAPE_HIDDEN = 0;
    static final int PANEL_SHAPE_LANDSCAPE_CYLINDER = 2;
    static final int PANEL_SHAPE_PORTRAIT_CYLINDER = 3;
    static final int PANEL_SHAPE_SCREEN = 14;
    private static final float SCROLLING_DECREASE_FACTOR = 0.96f;
    private static final float SCROLLING_SCALE = 0.01f;
    private static final String TAG = "AndroidPanelLayer";
    public static final float THUMBSTICK_SCROLL_FACTOR = 0.25f;
    public static final float TOUCH_SCROLL_THRESHOLD = 50.0f;
    private View mContentView;
    private final Context mContext;
    private Context mContextWrapper;
    private long mCurrentSizeRequestVersion = 0;
    private float mDensity;
    private int mHeightInPixels;
    private boolean mIsScrollingX;
    private boolean mIsScrollingY;
    private long mLastButtons;
    private float mLastCursorX = -1.0f;
    private float mLastCursorY = -1.0f;
    private float mLastTouchX;
    private float mLastTouchY;
    private LayoutInflater mLayoutInflater;
    private long mPendingSizeRequestVersion = 0;
    private PointerForwarder mPointerForwarder;
    private float mPosX;
    private float mPosY;
    private Presentation mPresentation;
    private float mRotX;
    private float mRotY;
    private float mRotZ;
    private float mSmoothPointerDx;
    private float mSmoothPointerDy;
    private Spec mSpec;
    private boolean mSupersampled;
    private final Surface mSurface;
    private boolean mSystemPositioned;
    private float mTouchOriginX;
    private float mTouchOriginY;
    private VirtualDisplay mVirtualDisplay;
    private String mVirtualDisplayName;
    private boolean mVisibility = false;
    private int mWidthInPixels;
    private AndroidPanelWindowManager mWindowManager;

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

    private static boolean isButtonPressed(long j, long j2, long j3) {
        return (j2 & j3) == j3 && (j & j3) != j3;
    }

    private static boolean isButtonPressing(long j, long j2, long j3) {
        return (j2 & j3) == j3 && (j & j3) == j3;
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

        private Shape(int i) {
            this.value = i;
        }
    }

    public enum BorderRadiusType {
        All(NotificationUri.ALL),
        Left(TtmlNode.LEFT),
        Right(TtmlNode.RIGHT);
        
        private final String mIPCString;

        private BorderRadiusType(String str) {
            this.mIPCString = str;
        }

        public String getIPCString() {
            return this.mIPCString;
        }
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

        public Spec(String str, int i, int i2, ResizeBehavior resizeBehavior2, HitTestingBehavior hitTestingBehavior2, Shape shape2, int i3) {
            this.name = str;
            this.width = i;
            this.height = i2;
            this.resizeBehavior = resizeBehavior2;
            this.hitTestingBehavior = hitTestingBehavior2;
            this.shape = shape2;
            this.themeResourceId = i3;
            this.posX = 0.0f;
            this.posY = 0.0f;
            this.rotX = 0.0f;
            this.rotY = 0.0f;
            this.rotZ = 0.0f;
            this.density = 4680.0f;
            this.supersampled = false;
            this.isSystemPositioned = true;
        }

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
    }

    public AndroidPanelLayer(Context context, Surface surface, Spec spec) {
        this.mContext = context;
        this.mSurface = surface;
        setSpec(spec);
    }

    public String getName() {
        return this.mSpec.name;
    }

    public int getWidthInPixels() {
        return this.mWidthInPixels;
    }

    public int getHeightInPixels() {
        return this.mHeightInPixels;
    }

    public float getPositionX() {
        return this.mPosX;
    }

    public float getPositionY() {
        return this.mPosY;
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

    public float getDensity() {
        return this.mDensity;
    }

    public boolean isSystemPositioned() {
        return this.mSystemPositioned;
    }

    public boolean isSupersampled() {
        return this.mSupersampled;
    }

    public Shape getShape() {
        return this.mSpec.shape;
    }

    public boolean isHitTestable() {
        return this.mSpec.hitTestingBehavior == HitTestingBehavior.HIT_TESTABLE;
    }

    public ResizeBehavior getResizeBehavior() {
        return this.mSpec.resizeBehavior;
    }

    public void setSpec(Spec spec) {
        Log.i(TAG, String.format("Setting spec for layer \"%s\"", spec.name));
        this.mSpec = spec;
        this.mVirtualDisplayName = "com.oculus.android_panel_app.AndroidPanelLayer-" + spec.name;
        this.mSystemPositioned = spec.isSystemPositioned;
        this.mDensity = spec.density;
        this.mSupersampled = spec.supersampled;
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int dipToPixelsInt = DensityUtils.dipToPixelsInt((float) spec.width, displayMetrics);
        int dipToPixelsInt2 = DensityUtils.dipToPixelsInt((float) spec.height, displayMetrics);
        if (spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH || spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            dipToPixelsInt = DensityUtils.dipToPixelsInt(1000.0f, displayMetrics);
        }
        if (spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_HEIGHT || spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            dipToPixelsInt2 = DensityUtils.dipToPixelsInt(1000.0f, displayMetrics);
        }
        if (spec.isSystemPositioned) {
            setSystemPositioned();
        } else {
            setPositionAndRotation(spec.posX, spec.posY, spec.rotX, spec.rotY, spec.rotZ);
        }
        setSize(dipToPixelsInt, dipToPixelsInt2);
    }

    public Context getPresentationContext() {
        return this.mContextWrapper;
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

    public void frame(InputFrame inputFrame) {
        dispatchMotionEvents(inputFrame.getCursorX() * ((float) this.mWidthInPixels), (1.0f - inputFrame.getCursorY()) * ((float) this.mHeightInPixels), inputFrame.getTouchX(), inputFrame.getTouchY(), inputFrame.getButtons(), inputFrame.getFlags());
    }

    public void frame() {
        handleHoverEvents(-1.0f, -1.0f);
        this.mLastCursorX = -1.0f;
        this.mLastCursorY = -1.0f;
        this.mLastButtons = 0;
        this.mIsScrollingX = false;
        this.mIsScrollingY = false;
    }

    public void destroy() {
        this.mContentView = null;
        destroyPresentation();
    }

    public boolean isVisible() {
        Presentation presentation;
        View view;
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        return (virtualDisplay == null || virtualDisplay.getSurface() == null || (presentation = this.mPresentation) == null || !presentation.isShowing() || (view = this.mContentView) == null || view.getVisibility() != 0) ? false : true;
    }

    public void unhide() {
        View view;
        String name = getName();
        String str = TAG;
        Log.i(str, "Unhiding layer " + name);
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || virtualDisplay.getSurface() == null || this.mPresentation == null || (view = this.mContentView) == null) {
            Log.w(TAG, String.format("To unhide layer \"%s\", the surface must be set on the virtual display with non-null presentation and content", name));
            return;
        }
        view.setVisibility(0);
        this.mPresentation.show();
    }

    public void show() {
        String name = getName();
        String str = TAG;
        Log.i(str, "Showing layer " + name);
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || this.mPresentation == null) {
            Log.w(TAG, String.format("To show layer \"%s\", both the virtual display and presentation must be created first", name));
            return;
        }
        virtualDisplay.setSurface(this.mSurface);
        this.mPresentation.getWindow().setType(2030);
        unhide();
    }

    public boolean needsToBeSized() {
        return this.mPendingSizeRequestVersion != this.mCurrentSizeRequestVersion;
    }

    public void setContentView(View view) {
        if (this.mPresentation == null) {
            Log.w(TAG, String.format("To set content view on layer \"%s\", the presentation must be created first", getName()));
            return;
        }
        this.mContentView = view;
        this.mPointerForwarder = new PointerForwarder(view, this.mWindowManager);
        this.mPresentation.setContentView(view, new ViewGroup.LayoutParams(this.mWidthInPixels, this.mHeightInPixels));
    }

    public View getContentView() {
        return this.mContentView;
    }

    public void setSize(int i, int i2) {
        Log.i(TAG, String.format("Setting size of layer \"%s\" to have width %d and height %d", getName(), Integer.valueOf(i), Integer.valueOf(i2)));
        if (this.mWidthInPixels != i || this.mHeightInPixels != i2) {
            this.mWidthInPixels = i;
            this.mHeightInPixels = i2;
            destroyPresentation();
            createPresentation();
        }
    }

    public void setPositionAndRotation(float f, float f2, float f3, float f4, float f5) {
        Log.i(TAG, String.format("Setting position of layer \"%s\" to have x at %f, y at %f, and rotation (%f,%f,%f)", getName(), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5)));
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

    private void createPresentation() {
        int i = 2;
        Log.i(TAG, String.format("Creating presentation for layer \"%s\" with width %d and height %d", getName(), Integer.valueOf(this.mWidthInPixels), Integer.valueOf(this.mHeightInPixels)));
        this.mVirtualDisplay = ((DisplayManager) this.mContext.getSystemService("display")).createVirtualDisplay(this.mVirtualDisplayName, this.mWidthInPixels, this.mHeightInPixels, this.mContext.getResources().getDisplayMetrics().densityDpi, null, 0);
        this.mPresentation = new Presentation(this.mContext, this.mVirtualDisplay.getDisplay(), this.mSpec.themeResourceId);
        this.mContextWrapper = wrapPresentationContext();
        this.mWindowManager = new AndroidPanelWindowManager((WindowManager) this.mPresentation.getContext().getSystemService("window"));
        this.mLayoutInflater = LayoutInflater.from(this.mPresentation.getContext()).cloneInContext(this.mContextWrapper);
        this.mPresentation.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.mPresentation.getWindow().addFlags(8);
        if (Build.VERSION.SDK_INT <= 23 || Build.VERSION.SDK_INT >= 26) {
            i = 2030;
        }
        this.mPresentation.getWindow().setType(i);
    }

    private void destroyPresentation() {
        Log.i(TAG, String.format("Destroying presentation for layer \"%s\".", getName()));
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

    public boolean hasPresentation() {
        return this.mPresentation != null;
    }

    private void dispatchMotionEvents(float f, float f2, float f3, float f4, long j, long j2) {
        if (this.mPointerForwarder != null) {
            if ((j2 & PanelFlags.GAZE_VALID.getFlagValue()) == 0) {
                f = -1.0f;
                f2 = -1.0f;
            }
            handleJoystickScrollInputEvents(j, f, f2, f3, f4);
            handleTouchScrollInputEvents(j, f, f2, f3, f4);
            handleClickInputEvents(j, f, f2);
            handleHoverEvents(f, f2);
            this.mLastCursorX = f;
            this.mLastCursorY = f2;
            this.mLastTouchX = f3;
            this.mLastTouchY = f4;
            this.mLastButtons = j;
        }
    }

    private void handleHoverEvents(float f, float f2) {
        boolean insideLayer = insideLayer(f, f2);
        boolean insideLayer2 = insideLayer(this.mLastCursorX, this.mLastCursorY);
        if (insideLayer2 && insideLayer) {
            this.mPointerForwarder.hoverMove(f, f2);
        } else if (!insideLayer2 && insideLayer) {
            this.mPointerForwarder.hoverEnter(f, f2);
        } else if (insideLayer2 && !insideLayer) {
            this.mPointerForwarder.hoverExit(f, f2);
        }
    }

    private void handleClickInputEvents(long j, float f, float f2) {
        boolean z = true;
        boolean z2 = (PanelButton.getAnyActionFlagValue() & j) != 0;
        if ((this.mLastButtons & PanelButton.getAnyActionFlagValue()) == 0) {
            z = false;
        }
        if (z2 && z) {
            onPointerUpdate(j, f, f2);
        } else if (z2) {
            onPointerDown(j, f, f2);
        } else if (z) {
            onPointerUp(j);
        }
    }

    private void handleJoystickScrollInputEvents(long j, float f, float f2, float f3, float f4) {
        if (isButtonPressing(this.mLastButtons, j, PanelButton.JOYSTICK_MOVE.getFlagValue())) {
            this.mPointerForwarder.touchScroll(f, f2, f3 * 0.25f, f4 * 0.25f);
        }
    }

    private void handleTouchScrollInputEvents(long j, float f, float f2, float f3, float f4) {
        if ((PanelButton.getClickOrTriggerFlagValue() & j) == 0) {
            boolean z = false;
            if (isButtonPressed(this.mLastButtons, j, PanelButton.TOUCH.getFlagValue()) || isButtonPressed(this.mLastButtons, j, PanelButton.REMOTE_TOUCH.getFlagValue())) {
                this.mTouchOriginX = f3;
                this.mTouchOriginY = f4;
                this.mIsScrollingY = false;
                this.mIsScrollingX = false;
            }
            if (isButtonPressing(this.mLastButtons, j, PanelButton.TOUCH.getFlagValue()) || isButtonPressing(this.mLastButtons, j, PanelButton.REMOTE_TOUCH.getFlagValue())) {
                this.mIsScrollingX |= Math.abs(f3 - this.mTouchOriginX) > 50.0f;
                boolean z2 = this.mIsScrollingY;
                if (Math.abs(f4 - this.mTouchOriginY) > 50.0f) {
                    z = true;
                }
                this.mIsScrollingY = z2 | z;
                if (this.mIsScrollingY) {
                    this.mSmoothPointerDy = getSmoothPointerDelta(this.mSmoothPointerDy, f4 - this.mLastTouchY);
                }
                if (this.mIsScrollingX) {
                    this.mSmoothPointerDx = getSmoothPointerDelta(this.mSmoothPointerDx, f3 - this.mLastTouchX);
                }
            }
            if (Math.abs(this.mSmoothPointerDy) > 0.0f) {
                this.mSmoothPointerDy = getAutoScrollingDelta(this.mSmoothPointerDy);
                this.mPointerForwarder.touchScroll(f, f2, 0.0f, this.mSmoothPointerDy);
            }
            if (Math.abs(this.mSmoothPointerDx) > 0.0f) {
                this.mSmoothPointerDx = getAutoScrollingDelta(this.mSmoothPointerDx);
                this.mPointerForwarder.touchScroll(f, f2, this.mSmoothPointerDx, 0.0f);
            }
        }
    }

    private static float getAutoScrollingDelta(float f) {
        float f2 = f * SCROLLING_DECREASE_FACTOR;
        if (Math.abs(f2) < MIN_POINTER_DELTA) {
            return 0.0f;
        }
        return f2;
    }

    private static float getSmoothPointerDelta(float f, float f2) {
        float f3 = (f + (f2 * SCROLLING_SCALE)) / 2.0f;
        if (Math.abs(f3) < MIN_POINTER_DELTA) {
            return 0.0f;
        }
        return Math.max(Math.min(f3, (float) MAX_POINTER_DELTA), -0.5f);
    }

    private void onPointerDown(long j, float f, float f2) {
        this.mPointerForwarder.pointerPressed(f, f2);
    }

    private void onPointerUpdate(long j, float f, float f2) {
        this.mPointerForwarder.pointerDragged(f, f2);
    }

    private void onPointerUp(long j) {
        this.mPointerForwarder.pointerReleased(this.mLastCursorX, this.mLastCursorY);
    }

    private boolean insideLayer(float f, float f2) {
        return f2 >= 0.0f && f2 <= ((float) getHeightInPixels()) && f >= 0.0f && f <= ((float) getWidthInPixels());
    }

    public void setVisibility(boolean z) {
        this.mVisibility = z;
    }

    public boolean getVisibility() {
        return this.mVisibility;
    }
}
