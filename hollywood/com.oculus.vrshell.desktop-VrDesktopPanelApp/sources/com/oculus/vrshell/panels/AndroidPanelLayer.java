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
    private static final String TAG = AndroidPanelLayer.class.getSimpleName();
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

    public enum Shape {
        Hidden(0),
        Flat(1),
        LandscapeCylinder(2),
        PortraitCylinder(3),
        Equirect360(12),
        Equirect180(13),
        Screen(14);
        
        public final int value;

        private Shape(int value2) {
            this.value = value2;
        }
    }

    public enum BorderRadiusType {
        All("all"),
        Left("left"),
        Right("right");
        
        private final String mIPCString;

        private BorderRadiusType(String IPCString) {
            this.mIPCString = IPCString;
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

        public Spec(String name2, int width2, int height2, ResizeBehavior resizeBehavior2, HitTestingBehavior hitTestingBehavior2, Shape shape2, int themeResourceId2) {
            this.name = name2;
            this.width = width2;
            this.height = height2;
            this.resizeBehavior = resizeBehavior2;
            this.hitTestingBehavior = hitTestingBehavior2;
            this.shape = shape2;
            this.themeResourceId = themeResourceId2;
            this.posX = 0.0f;
            this.posY = 0.0f;
            this.rotX = 0.0f;
            this.rotY = 0.0f;
            this.rotZ = 0.0f;
            this.density = 4680.0f;
            this.supersampled = false;
            this.isSystemPositioned = true;
        }

        public Spec(String name2, int width2, int height2, float posX2, float posY2, float rotX2, float rotY2, float rotZ2, float density2, boolean supersampled2, ResizeBehavior resizeBehavior2, HitTestingBehavior hitTestingBehavior2, Shape shape2, int themeResourceId2) {
            this.name = name2;
            this.width = width2;
            this.height = height2;
            this.resizeBehavior = resizeBehavior2;
            this.hitTestingBehavior = hitTestingBehavior2;
            this.shape = shape2;
            this.themeResourceId = themeResourceId2;
            this.posX = posX2;
            this.posY = posY2;
            this.rotX = rotX2;
            this.rotY = rotY2;
            this.rotZ = rotZ2;
            this.isSystemPositioned = false;
            this.density = density2;
            this.supersampled = supersampled2;
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
        int widthInPixels = DensityUtils.dipToPixelsInt((float) spec.width, displayMetrics);
        int heightInPixels = DensityUtils.dipToPixelsInt((float) spec.height, displayMetrics);
        if (spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH || spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            widthInPixels = DensityUtils.dipToPixelsInt(1000.0f, displayMetrics);
        }
        if (spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_HEIGHT || spec.resizeBehavior == ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            heightInPixels = DensityUtils.dipToPixelsInt(1000.0f, displayMetrics);
        }
        if (spec.isSystemPositioned) {
            setSystemPositioned();
        } else {
            setPositionAndRotation(spec.posX, spec.posY, spec.rotX, spec.rotY, spec.rotZ);
        }
        setSize(widthInPixels, heightInPixels);
    }

    public Context getPresentationContext() {
        return this.mContextWrapper;
    }

    private Context wrapPresentationContext() {
        return new ContextWrapper(this.mPresentation.getContext()) {
            /* class com.oculus.vrshell.panels.AndroidPanelLayer.AnonymousClass1 */

            @Override // android.content.Context, android.content.ContextWrapper
            public Object getSystemService(String name) {
                if ("window".equals(name)) {
                    return AndroidPanelLayer.this.mWindowManager;
                }
                if ("layout_inflater".equals(name)) {
                    return AndroidPanelLayer.this.mLayoutInflater;
                }
                return super.getSystemService(name);
            }
        };
    }

    public void frame(InputFrame currentInputFrame) {
        dispatchMotionEvents(currentInputFrame.getCursorX() * ((float) this.mWidthInPixels), (1.0f - currentInputFrame.getCursorY()) * ((float) this.mHeightInPixels), currentInputFrame.getTouchX(), currentInputFrame.getTouchY(), currentInputFrame.getButtons(), currentInputFrame.getFlags());
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

    public void hide() {
        String str = TAG;
        Log.i(str, "Hiding layer " + getName());
        View view = this.mContentView;
        if (view != null) {
            view.setVisibility(8);
        }
        Presentation presentation = this.mPresentation;
        if (presentation != null) {
            presentation.hide();
        }
    }

    public void unhide() {
        View view;
        String layerName = getName();
        String str = TAG;
        Log.i(str, "Unhiding layer " + layerName);
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || virtualDisplay.getSurface() == null || this.mPresentation == null || (view = this.mContentView) == null) {
            Log.w(TAG, String.format("To unhide layer \"%s\", the surface must be set on the virtual display with non-null presentation and content", layerName));
            return;
        }
        view.setVisibility(0);
        this.mPresentation.show();
    }

    public void show() {
        String layerName = getName();
        String str = TAG;
        Log.i(str, "Showing layer " + layerName);
        VirtualDisplay virtualDisplay = this.mVirtualDisplay;
        if (virtualDisplay == null || this.mPresentation == null) {
            Log.w(TAG, String.format("To show layer \"%s\", both the virtual display and presentation must be created first", layerName));
            return;
        }
        virtualDisplay.setSurface(this.mSurface);
        this.mPresentation.getWindow().setType(2030);
        unhide();
    }

    public boolean needsToBeSized() {
        return this.mPendingSizeRequestVersion != this.mCurrentSizeRequestVersion;
    }

    public void setContentView(View contentView) {
        if (this.mPresentation == null) {
            Log.w(TAG, String.format("To set content view on layer \"%s\", the presentation must be created first", getName()));
            return;
        }
        this.mContentView = contentView;
        this.mPointerForwarder = new PointerForwarder(contentView, this.mWindowManager);
        this.mPresentation.setContentView(contentView, new ViewGroup.LayoutParams(this.mWidthInPixels, this.mHeightInPixels));
    }

    public View getContentView() {
        return this.mContentView;
    }

    public void setSize(int widthInPixels, int heightInPixels) {
        Log.i(TAG, String.format("Setting size of layer \"%s\" to have width %d and height %d", getName(), Integer.valueOf(widthInPixels), Integer.valueOf(heightInPixels)));
        if (this.mWidthInPixels != widthInPixels || this.mHeightInPixels != heightInPixels) {
            this.mWidthInPixels = widthInPixels;
            this.mHeightInPixels = heightInPixels;
            destroyPresentation();
            createPresentation();
        }
    }

    public void setPositionAndRotation(float x, float y, float rX, float rY, float rZ) {
        Log.i(TAG, String.format("Setting position of layer \"%s\" to have x at %f, y at %f, and rotation (%f,%f,%f)", getName(), Float.valueOf(x), Float.valueOf(y), Float.valueOf(rX), Float.valueOf(rY), Float.valueOf(rZ)));
        this.mPosX = x;
        this.mPosY = y;
        this.mRotX = rX;
        this.mRotY = rY;
        this.mRotZ = rZ;
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
        Log.i(TAG, String.format("Creating presentation for layer \"%s\" with width %d and height %d", getName(), Integer.valueOf(this.mWidthInPixels), Integer.valueOf(this.mHeightInPixels)));
        this.mVirtualDisplay = ((DisplayManager) this.mContext.getSystemService("display")).createVirtualDisplay(this.mVirtualDisplayName, this.mWidthInPixels, this.mHeightInPixels, this.mContext.getResources().getDisplayMetrics().densityDpi, null, 0);
        this.mPresentation = new Presentation(this.mContext, this.mVirtualDisplay.getDisplay(), this.mSpec.themeResourceId);
        this.mContextWrapper = wrapPresentationContext();
        this.mWindowManager = new AndroidPanelWindowManager((WindowManager) this.mPresentation.getContext().getSystemService("window"));
        this.mLayoutInflater = LayoutInflater.from(this.mPresentation.getContext()).cloneInContext(this.mContextWrapper);
        this.mPresentation.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.mPresentation.getWindow().addFlags(8);
        int windowType = 2030;
        if (Build.VERSION.SDK_INT > 23 && Build.VERSION.SDK_INT < 26) {
            windowType = 2;
        }
        this.mPresentation.getWindow().setType(windowType);
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

    private void dispatchMotionEvents(float cursorX, float cursorY, float touchX, float touchY, long buttons, long panelFlags) {
        if (this.mPointerForwarder != null) {
            if ((PanelFlags.GAZE_VALID.getFlagValue() & panelFlags) == 0) {
                cursorX = -1.0f;
                cursorY = -1.0f;
            }
            handleJoystickScrollInputEvents(buttons, cursorX, cursorY, touchX, touchY);
            handleTouchScrollInputEvents(buttons, cursorX, cursorY, touchX, touchY);
            handleClickInputEvents(buttons, cursorX, cursorY);
            handleHoverEvents(cursorX, cursorY);
            this.mLastCursorX = cursorX;
            this.mLastCursorY = cursorY;
            this.mLastTouchX = touchX;
            this.mLastTouchY = touchY;
            this.mLastButtons = buttons;
        }
    }

    private void handleHoverEvents(float cursorX, float cursorY) {
        boolean cursorInsideLayer = insideLayer(cursorX, cursorY);
        boolean lastCursorInsideLayer = insideLayer(this.mLastCursorX, this.mLastCursorY);
        if (lastCursorInsideLayer && cursorInsideLayer) {
            this.mPointerForwarder.hoverMove(cursorX, cursorY);
        } else if (!lastCursorInsideLayer && cursorInsideLayer) {
            this.mPointerForwarder.hoverEnter(cursorX, cursorY);
        } else if (lastCursorInsideLayer && !cursorInsideLayer) {
            this.mPointerForwarder.hoverExit(cursorX, cursorY);
        }
    }

    private void handleClickInputEvents(long buttons, float cursorX, float cursorY) {
        boolean isClickOrTriggerDownLastFrame = true;
        boolean isClickOrTriggerDown = (PanelButton.getAnyActionFlagValue() & buttons) != 0;
        if ((this.mLastButtons & PanelButton.getAnyActionFlagValue()) == 0) {
            isClickOrTriggerDownLastFrame = false;
        }
        if (isClickOrTriggerDown && isClickOrTriggerDownLastFrame) {
            onPointerUpdate(buttons, cursorX, cursorY);
        } else if (isClickOrTriggerDown) {
            onPointerDown(buttons, cursorX, cursorY);
        } else if (isClickOrTriggerDownLastFrame) {
            onPointerUp(buttons);
        }
    }

    private void handleJoystickScrollInputEvents(long buttons, float cursorX, float cursorY, float touchX, float touchY) {
        if (isButtonPressing(this.mLastButtons, buttons, PanelButton.JOYSTICK_MOVE.getFlagValue())) {
            this.mPointerForwarder.touchScroll(cursorX, cursorY, touchX * 0.25f, 0.25f * touchY);
        }
    }

    private void handleTouchScrollInputEvents(long buttons, float cursorX, float cursorY, float touchX, float touchY) {
        if ((PanelButton.getClickOrTriggerFlagValue() & buttons) == 0) {
            boolean z = false;
            if (isButtonPressed(this.mLastButtons, buttons, PanelButton.TOUCH.getFlagValue()) || isButtonPressed(this.mLastButtons, buttons, PanelButton.REMOTE_TOUCH.getFlagValue())) {
                this.mTouchOriginX = touchX;
                this.mTouchOriginY = touchY;
                this.mIsScrollingY = false;
                this.mIsScrollingX = false;
            }
            if (isButtonPressing(this.mLastButtons, buttons, PanelButton.TOUCH.getFlagValue()) || isButtonPressing(this.mLastButtons, buttons, PanelButton.REMOTE_TOUCH.getFlagValue())) {
                this.mIsScrollingX |= Math.abs(touchX - this.mTouchOriginX) > 50.0f;
                boolean z2 = this.mIsScrollingY;
                if (Math.abs(touchY - this.mTouchOriginY) > 50.0f) {
                    z = true;
                }
                this.mIsScrollingY = z2 | z;
                if (this.mIsScrollingY) {
                    this.mSmoothPointerDy = getSmoothPointerDelta(this.mSmoothPointerDy, touchY - this.mLastTouchY);
                }
                if (this.mIsScrollingX) {
                    this.mSmoothPointerDx = getSmoothPointerDelta(this.mSmoothPointerDx, touchX - this.mLastTouchX);
                }
            }
            if (Math.abs(this.mSmoothPointerDy) > 0.0f) {
                this.mSmoothPointerDy = getAutoScrollingDelta(this.mSmoothPointerDy);
                this.mPointerForwarder.touchScroll(cursorX, cursorY, 0.0f, this.mSmoothPointerDy);
            }
            if (Math.abs(this.mSmoothPointerDx) > 0.0f) {
                this.mSmoothPointerDx = getAutoScrollingDelta(this.mSmoothPointerDx);
                this.mPointerForwarder.touchScroll(cursorX, cursorY, this.mSmoothPointerDx, 0.0f);
            }
        }
    }

    private static float getAutoScrollingDelta(float oldDelta) {
        float newDelta = SCROLLING_DECREASE_FACTOR * oldDelta;
        return Math.abs(newDelta) < MIN_POINTER_DELTA ? 0.0f : newDelta;
    }

    private static float getSmoothPointerDelta(float delta, float newDelta) {
        float newSmoothDelta = ((SCROLLING_SCALE * newDelta) + delta) / 2.0f;
        if (Math.abs(newSmoothDelta) < MIN_POINTER_DELTA) {
            return 0.0f;
        }
        return Math.max(Math.min(newSmoothDelta, (float) MAX_POINTER_DELTA), -0.5f);
    }

    private static boolean isButtonPressed(long prevState, long currentState, long button) {
        return (currentState & button) == button && (prevState & button) != button;
    }

    private static boolean isButtonPressing(long prevState, long currentState, long button) {
        return (currentState & button) == button && (prevState & button) == button;
    }

    private void onPointerDown(long buttons, float cursorX, float cursorY) {
        this.mPointerForwarder.pointerPressed(cursorX, cursorY);
    }

    private void onPointerUpdate(long buttons, float cursorX, float cursorY) {
        this.mPointerForwarder.pointerDragged(cursorX, cursorY);
    }

    private void onPointerUp(long buttons) {
        this.mPointerForwarder.pointerReleased(this.mLastCursorX, this.mLastCursorY);
    }

    private boolean insideLayer(float x, float y) {
        return y >= 0.0f && y <= ((float) getHeightInPixels()) && x >= 0.0f && x <= ((float) getWidthInPixels());
    }

    public void setVisibility(boolean visibility) {
        this.mVisibility = visibility;
    }

    public boolean getVisibility() {
        return this.mVisibility;
    }
}
