package com.oculus.vrshell.desktop;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import com.oculus.vrdesktop.IContainer;
import com.oculus.vrdesktop.IVrDesktopService;
import java.util.concurrent.atomic.AtomicBoolean;

public class PanelApp {
    private static final float MAX_POINTER_DELTA = 0.5f;
    private static final float MIN_POINTER_DELTA = 1.0E-4f;
    private static final float SCROLLING_DECREASE_FACTOR = 0.96f;
    private static final float SCROLLING_SCALE = 0.01f;
    private static final String TAG = "VrDesktopPanelApp";
    private IContainer mActivityContainer;
    private IBinder mActivityToken;
    private boolean mActivityTokenSet;
    private LaunchConfiguration mConfig;
    private final Context mContext;
    private boolean mFrameReady;
    private final Handler mHandler = new Handler();
    private AtomicBoolean mInBackground = new AtomicBoolean(false);
    private final InputMode mInputMode;
    private boolean mIsScrollingX;
    private boolean mIsScrollingY;
    private long mLastButtonState;
    private boolean mLastCursorInside;
    private float mLastTouchX;
    private float mLastTouchY;
    private final int mMouseInputDeviceId;
    private final long mNativePanelApp;
    private final float mRenderScale;
    private IVrDesktopService mService;
    private float mSmoothPointerDx;
    private float mSmoothPointerDy;
    private final Surface mSurface;
    private final IBinder mToken = new Binder();
    private final int mTouchInputDeviceId;
    private boolean mTouchMotionActive;
    private float mTouchOriginX;
    private float mTouchOriginY;

    /* access modifiers changed from: private */
    public enum InputMode {
        STANDARD,
        TV
    }

    private native long nativeCreate(String str, int i, int i2, int i3);

    private native void nativeResize(long j, int i, int i2, int i3);

    private native void nativeSetKeyboardPosition(long j, int i);

    private native void nativeSetKeyboardVisibility(long j, boolean z);

    private native void nativeSetTarget(long j, String str);

    public PanelApp(Context context, Surface surface, Bundle bundle) {
        String targetUri;
        this.mContext = context;
        this.mSurface = surface;
        IBinder serviceBinder = ServiceManager.getService(Constants.URI_SCHEME);
        if (serviceBinder != null) {
            this.mService = IVrDesktopService.Stub.asInterface(serviceBinder);
            this.mRenderScale = getRenderScaleFromEnvironment(bundle);
            if (bundle.containsKey(Constants.KEY_URI)) {
                targetUri = bundle.getString(Constants.KEY_URI);
            } else {
                targetUri = bundle.getString(Constants.KEY_TARGET);
            }
            this.mConfig = LaunchConfiguration.create(this.mContext, targetUri);
            LaunchConfiguration launchConfiguration = this.mConfig;
            if (launchConfiguration != null) {
                launchConfiguration.setFromEnvironment(bundle);
                this.mConfig.setRenderScale(this.mRenderScale);
                this.mInputMode = getInputModeFromEnvironment(bundle);
                Log.i(TAG, "Target: " + this.mConfig);
                Log.i(TAG, "Input mode: " + this.mInputMode);
                this.mTouchInputDeviceId = -1;
                this.mMouseInputDeviceId = getInputDeviceId(8194);
                this.mNativePanelApp = nativeCreate(this.mConfig.getLaunchIntent().getComponent().flattenToString(), this.mConfig.getWidth(), this.mConfig.getHeight(), this.mConfig.getPanelShape());
                nativeSetKeyboardPosition(this.mNativePanelApp, this.mConfig.getKeyboardPosition());
                return;
            }
            throw new RuntimeException("Target parameter not specified");
        }
        throw new RuntimeException("VrDesktopService not running");
    }

    public long getNativePointer() {
        return this.mNativePanelApp;
    }

    public void onShellTokenReady(IBinder token) {
        assertThread();
        this.mActivityToken = token;
        this.mActivityTokenSet = true;
        createActivityContainer();
    }

    private void onFirstFrame() {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass1 */

            public void run() {
                PanelApp.this.mFrameReady = true;
                PanelApp.this.createActivityContainer();
            }
        });
    }

    public void setKeyboardVisibility(boolean visible) {
        if (!this.mInBackground.get()) {
            nativeSetKeyboardVisibility(this.mNativePanelApp, visible);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void createActivityContainer() {
        assertThread();
        if (this.mActivityContainer == null && this.mFrameReady && this.mActivityTokenSet) {
            try {
                this.mActivityContainer = this.mService.startActivity(this.mActivityToken, this.mSurface, this.mConfig.getWidth(), this.mConfig.getHeight(), this.mConfig.getDisplayDensity(), (Intent) null);
                this.mActivityContainer.setCallerToken(this.mToken);
                startActivity(this.mConfig.getLaunchIntent());
            } catch (RemoteException e) {
                throw new RuntimeException("Unable to start the activity", e);
            }
        }
    }

    private synchronized void destroyActivityContainer() {
        if (this.mActivityContainer != null) {
            Log.d(TAG, "destroyActivityContainer: app=" + this);
            try {
                this.mActivityContainer.finish();
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to destroy the activity container", e);
            }
            this.mActivityContainer = null;
        }
    }

    private synchronized boolean isActivityRunning() {
        if (this.mActivityContainer == null) {
            return true;
        }
        try {
            return this.mActivityContainer.isActive();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to check the activity container state", e);
            return false;
        }
    }

    private void forwardInputEvent(final float cursorX, final float cursorY, final float touchX, final float touchY, final long buttons) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass2 */

            public void run() {
                PanelApp.this.forwardInputEventInternal(cursorX, cursorY, touchX, touchY, buttons);
            }
        });
    }

    private void forwardKeyboardText(final String text) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass3 */

            public void run() {
                PanelApp.this.forwardKeyboardTextInternal(text);
            }
        });
    }

    private void forwardKeyboardCommand(final String command) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass4 */

            public void run() {
                PanelApp.this.forwardKeyboardCommandInternal(command);
            }
        });
    }

    private void handleDeepLink(final String uri) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass5 */

            public void run() {
                PanelApp.this.handleDeepLinkInternal(uri);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleDeepLinkInternal(String uri) {
        assertThread();
        if (uri == null || uri.isEmpty()) {
            Log.w(TAG, "Received a deeplink command with an invalid URI: " + uri);
            return;
        }
        LaunchConfiguration config = LaunchConfiguration.create(this.mContext, uri);
        if (config == null) {
            Log.w(TAG, "Failed to parse the deeplink: " + uri);
            return;
        }
        config.setRenderScale(this.mRenderScale);
        boolean needsResizing = !config.isSameSize(this.mConfig);
        nativeSetTarget(this.mNativePanelApp, config.getLaunchIntent().getComponent().flattenToString());
        nativeSetKeyboardPosition(this.mNativePanelApp, config.getKeyboardPosition());
        this.mConfig = config;
        if (needsResizing) {
            destroyActivityContainer();
            nativeResize(this.mNativePanelApp, config.getWidth(), config.getHeight(), config.getPanelShape());
            this.mFrameReady = false;
            return;
        }
        try {
            Log.i(TAG, "Launching via deeplink: " + this.mConfig);
            startActivity(this.mConfig.getLaunchIntent());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to start an activity", e);
        }
    }

    private void onForeground() {
        this.mInBackground.set(false);
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass6 */

            public void run() {
                if (PanelApp.this.mActivityContainer != null) {
                    Log.d(PanelApp.TAG, "onForeground: resuming top activity");
                    try {
                        PanelApp.this.mActivityContainer.resumeTopActivity();
                    } catch (RemoteException e) {
                        Log.e(PanelApp.TAG, "resumeTopActivity() failed", e);
                    }
                }
            }
        });
    }

    private void onBackground() {
        this.mInBackground.set(true);
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.desktop.PanelApp.AnonymousClass7 */

            public void run() {
                if (PanelApp.this.mActivityContainer != null) {
                    Log.d(PanelApp.TAG, "onBackground: pausing");
                    try {
                        PanelApp.this.mActivityContainer.pauseActivities();
                    } catch (RemoteException e) {
                        Log.e(PanelApp.TAG, "pauseActivities() failed", e);
                    }
                }
            }
        });
    }

    private void startActivity(Intent intent) throws RemoteException {
        IContainer iContainer = this.mActivityContainer;
        if (iContainer != null) {
            iContainer.setInputFocusEnabled(true);
            this.mActivityContainer.clear();
            this.mActivityContainer.startActivity(intent);
        }
    }

    private boolean isButtonPressed(long currentState, long button) {
        return (currentState & button) == button && (this.mLastButtonState & button) != button;
    }

    private boolean isButtonPressing(long currentState, long button) {
        return (currentState & button) == button && (this.mLastButtonState & button) == button;
    }

    private boolean isAnyButtonPressed(long currentState, long buttons) {
        return (currentState & buttons) != 0 && (this.mLastButtonState & buttons) == 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void forwardInputEventInternal(float cursorX, float cursorY, float touchX, float touchY, long buttons) {
        boolean touchMotionActive;
        boolean touchMotionActive2;
        assertThread();
        long now = SystemClock.uptimeMillis();
        float x = cursorX * ((float) this.mConfig.getWidth());
        float y = (1.0f - cursorY) * ((float) this.mConfig.getHeight());
        int i = 0;
        boolean touchMotionActive3 = (buttons & ((this.mInputMode == InputMode.STANDARD ? 1 : 0) | 256)) != 0;
        boolean cursorInside = x > 0.0f && x < ((float) (this.mConfig.getWidth() - 1)) && y > 0.0f && y < ((float) (this.mConfig.getHeight() - 1));
        boolean motionEventInjected = true;
        if (touchMotionActive3) {
            if (this.mTouchMotionActive) {
                i = 2;
            }
            touchMotionActive = touchMotionActive3;
            motionEventInjected = injectMotionEvent(i, now, cursorInside, x, y, 1.0f);
        } else {
            touchMotionActive = touchMotionActive3;
            if (touchMotionActive || !this.mTouchMotionActive) {
                injectHoverEvent(now, cursorInside, x, y);
            } else {
                motionEventInjected = injectMotionEvent(1, now, cursorInside, x, y, 0.0f);
            }
        }
        if (isAnyButtonPressed(buttons, Constants.getAnyBackFlagValue())) {
            injectBackButtonPress(now);
        }
        int i2 = AnonymousClass8.$SwitchMap$com$oculus$vrshell$desktop$PanelApp$InputMode[this.mInputMode.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                touchMotionActive2 = touchMotionActive;
            } else {
                handleTvInputEvents(buttons, touchX, touchY);
                touchMotionActive2 = touchMotionActive;
            }
        } else if (!cursorInside || touchMotionActive) {
            touchMotionActive2 = touchMotionActive;
        } else {
            touchMotionActive2 = touchMotionActive;
            handleScrollInputEvents(now, buttons, x, y, touchX, touchY);
        }
        this.mLastButtonState = buttons;
        this.mLastTouchX = touchX;
        this.mLastTouchY = touchY;
        if (motionEventInjected) {
            this.mTouchMotionActive = touchMotionActive2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrshell.desktop.PanelApp$8  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$desktop$PanelApp$InputMode = new int[InputMode.values().length];

        static {
            try {
                $SwitchMap$com$oculus$vrshell$desktop$PanelApp$InputMode[InputMode.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$vrshell$desktop$PanelApp$InputMode[InputMode.TV.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private void handleTvInputEvents(long buttons, float touchX, float touchY) {
        if (isButtonPressed(buttons, Constants.PBUTTON_SWIPE_FORWARD)) {
            forwardKeyboardCommand("ARROW_LEFT");
        } else if (isButtonPressed(buttons, Constants.PBUTTON_SWIPE_BACK)) {
            forwardKeyboardCommand("ARROW_RIGHT");
        } else if (isButtonPressed(buttons, Constants.PBUTTON_SWIPE_DOWN)) {
            forwardKeyboardCommand("ARROW_DOWN");
        } else if (isButtonPressed(buttons, Constants.PBUTTON_SWIPE_UP)) {
            forwardKeyboardCommand("ARROW_UP");
        } else if (isButtonPressed(buttons, 1)) {
            int transX = Math.round(touchX) - 160;
            int transY = 160 - Math.round(touchY);
            if ((transX * transX) + (transY * transY) < 2304) {
                Log.d(TAG, "Dpad center");
            } else if (transY > transX * -1) {
                if (transY > transX) {
                    forwardKeyboardCommand("ARROW_UP");
                } else {
                    forwardKeyboardCommand("ARROW_RIGHT");
                }
            } else if (transY > transX) {
                forwardKeyboardCommand("ARROW_LEFT");
            } else {
                forwardKeyboardCommand("ARROW_DOWN");
            }
        }
    }

    private float getAutoScrollingDelta(float oldDelta) {
        float newDelta = SCROLLING_DECREASE_FACTOR * oldDelta;
        return Math.abs(newDelta) < MIN_POINTER_DELTA ? 0.0f : newDelta;
    }

    private float getSmoothPointerDelta(float delta, float newDelta) {
        float newSmoothDelta = ((SCROLLING_SCALE * newDelta) + delta) / 2.0f;
        if (Math.abs(newSmoothDelta) < MIN_POINTER_DELTA) {
            return 0.0f;
        }
        return Math.max(Math.min(newSmoothDelta, (float) MAX_POINTER_DELTA), -0.5f);
    }

    private void handleScrollInputEvents(long now, long buttons, float cursorX, float cursorY, float touchX, float touchY) {
        if (isButtonPressing(buttons, Constants.PBUTTON_JOYSTICK_MOVE)) {
            injectScrollEvent(now, cursorX, cursorY, touchX * 0.25f, touchY * 0.25f);
            return;
        }
        boolean z = false;
        if (isButtonPressed(buttons, Constants.PBUTTON_REMOTE_TOUCH)) {
            this.mTouchOriginX = touchX;
            this.mTouchOriginY = touchY;
            this.mIsScrollingY = false;
            this.mIsScrollingX = false;
        }
        if (isButtonPressing(buttons, Constants.PBUTTON_REMOTE_TOUCH)) {
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
                this.mSmoothPointerDx = getSmoothPointerDelta(this.mSmoothPointerDx, touchY - this.mLastTouchX);
            }
        }
        if (Math.abs(this.mSmoothPointerDy) > 0.0f) {
            this.mSmoothPointerDy = getAutoScrollingDelta(this.mSmoothPointerDy);
            injectScrollEvent(now, cursorX, cursorY, 0.0f, this.mSmoothPointerDy);
        }
        if (Math.abs(this.mSmoothPointerDx) > 0.0f) {
            this.mSmoothPointerDx = getAutoScrollingDelta(this.mSmoothPointerDx);
            injectScrollEvent(now, cursorX, cursorY, this.mSmoothPointerDx, 0.0f);
        }
    }

    private void injectHoverEvent(long now, boolean cursorInside, float x, float y) {
        if (cursorInside) {
            if (!this.mLastCursorInside) {
                injectMouseEvent(9, now, x, y);
            }
            injectMouseEvent(7, now, x, y);
        } else if (this.mLastCursorInside) {
            injectMouseEvent(10, now, 0.0f, 0.0f);
        }
        this.mLastCursorInside = cursorInside;
    }

    private void injectScrollEvent(long now, float x, float y, float hscroll, float vscroll) {
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        coords.x = x;
        coords.y = y;
        coords.setAxisValue(10, hscroll);
        coords.setAxisValue(9, vscroll);
        MotionEvent.PointerProperties props = new MotionEvent.PointerProperties();
        props.id = 0;
        injectEvent(MotionEvent.obtain(now, now, 8, 1, new MotionEvent.PointerProperties[]{props}, new MotionEvent.PointerCoords[]{coords}, 0, 0, 1.0f, 1.0f, this.mMouseInputDeviceId, 0, 8194, 0));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void forwardKeyboardTextInternal(String text) {
        String text2 = text.replaceAll("[‘’]", "'").replaceAll("[“”]", "\"");
        KeyCharacterMap map = KeyCharacterMap.load(-1);
        char[] charArray = text2.toCharArray();
        for (char c : charArray) {
            KeyEvent[] events = map.getEvents(new char[]{c});
            if (events == null) {
                Log.d(TAG, "No KeyEvent mapping for " + c);
            } else {
                for (KeyEvent event : events) {
                    injectKeyEvent(event);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void forwardKeyboardCommandInternal(String command) {
        int keyCode = getKeyCodeFromCommand(command);
        if (keyCode == 66) {
            setKeyboardVisibility(false);
        }
        if (keyCode != 0) {
            injectKeyEvent(0, SystemClock.uptimeMillis(), keyCode);
        }
    }

    private int getKeyCodeFromCommand(String command) {
        if ("ACTION_KEY".equals(command)) {
            return 66;
        }
        if ("BACKSPACE".equals(command)) {
            return 67;
        }
        if ("ARROW_LEFT".equals(command)) {
            return 21;
        }
        if ("ARROW_RIGHT".equals(command)) {
            return 22;
        }
        if ("ARROW_UP".equals(command)) {
            return 19;
        }
        if ("ARROW_DOWN".equals(command)) {
            return 20;
        }
        return 0;
    }

    private boolean injectMotionEvent(int action, long when, boolean cursorInside, float x, float y, float pressure) {
        if (!cursorInside && action == 0) {
            return false;
        }
        MotionEvent event = MotionEvent.obtain(when, when, action, x, y, pressure, 1.0f, 0, 1.0f, 1.0f, this.mTouchInputDeviceId, 0);
        event.setSource(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        injectEvent(event);
        return true;
    }

    private void injectMouseEvent(int action, long when, float x, float y) {
        MotionEvent event = MotionEvent.obtain(when, when, action, x, y, 1.0f, 1.0f, 0, 1.0f, 1.0f, this.mMouseInputDeviceId, 0);
        event.setSource(8194);
        injectEvent(event);
    }

    private void injectBackButtonPress(long when) {
        injectKeyEvent(0, when, 4);
        injectKeyEvent(1, when, 4);
    }

    private void injectKeyEvent(int action, long when, int code) {
        injectKeyEvent(new KeyEvent(when, when, action, code, 0));
    }

    private void injectKeyEvent(KeyEvent event) {
        event.setSource(8194);
        injectEvent(event);
    }

    private void injectEvent(InputEvent event) {
        assertThread();
        IContainer iContainer = this.mActivityContainer;
        if (iContainer != null) {
            try {
                iContainer.injectEvent(event);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to inject an input event.", e);
            }
        }
    }

    private int getInputDeviceId(int inputSource) {
        int[] deviceIds = InputDevice.getDeviceIds();
        for (int devId : deviceIds) {
            if (InputDevice.getDevice(devId).supportsSource(inputSource)) {
                return devId;
            }
        }
        return 0;
    }

    private InputMode getInputModeFromEnvironment(Bundle bundle) {
        String inputModeString = bundle.getString(Constants.KEY_INPUT_MODE);
        if (inputModeString == null) {
            return InputMode.STANDARD;
        }
        try {
            return InputMode.valueOf(inputModeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return InputMode.STANDARD;
        }
    }

    private float getRenderScaleFromEnvironment(Bundle bundle) {
        String scaleString = bundle.getString(Constants.KEY_SHELL_RENDER_SCALE);
        if (scaleString == null) {
            return 1.0f;
        }
        try {
            return Float.valueOf(scaleString).floatValue();
        } catch (NumberFormatException e) {
            Log.w(TAG, "Failed to parse _oc_shell_render_scale: " + scaleString, e);
            return 1.0f;
        }
    }

    private void assertThread() {
        if (Looper.myLooper() != this.mHandler.getLooper()) {
            throw new RuntimeException("Method not called on the expected thread");
        }
    }

    /* access modifiers changed from: private */
    public static class LaunchConfiguration {
        private int mDisplayDensity;
        private int mHeight;
        private final Intent mIntent = new Intent();
        private int mKeyboardPosition;
        private float mScale = 1.0f;
        private int mShape;
        private int mWidth;

        public static LaunchConfiguration create(Context context, String uriString) {
            if (!uriString.startsWith("vrdesktop://")) {
                uriString = "vrdesktop://" + uriString;
            }
            Uri uri = Uri.parse(uriString);
            ComponentName componentName = getLaunchComponentName(uri);
            if (componentName == null) {
                return null;
            }
            return new LaunchConfiguration(context, componentName, uri);
        }

        public Intent getLaunchIntent() {
            return this.mIntent;
        }

        public int getWidth() {
            return (int) (((float) this.mWidth) * this.mScale);
        }

        public int getHeight() {
            return (int) (((float) this.mHeight) * this.mScale);
        }

        public int getPanelShape() {
            return this.mShape;
        }

        public int getDisplayDensity() {
            return (int) (((float) this.mDisplayDensity) * this.mScale);
        }

        public int getKeyboardPosition() {
            return this.mKeyboardPosition;
        }

        public void setRenderScale(float scale) {
            this.mScale = scale;
        }

        public boolean isSameSize(LaunchConfiguration other) {
            return this.mWidth == other.mWidth && this.mHeight == other.mHeight && this.mShape == other.mShape && this.mDisplayDensity == other.mDisplayDensity && this.mScale == other.mScale;
        }

        public String toString() {
            return String.format("LaunchConfiguration{w=%d h=%d shape=%d dpi=%d scale=%f intent=%s}", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mShape), Integer.valueOf(this.mDisplayDensity), Float.valueOf(this.mScale), this.mIntent);
        }

        private LaunchConfiguration(Context context, ComponentName componentName, Uri uri) {
            this.mIntent.setComponent(componentName);
            setDefaultPanelSizeAndDensity(context, componentName.getPackageName());
            setDefaultKeyboardPosition(context, componentName.getPackageName());
            this.mShape = 2;
            for (String param : uri.getQueryParameterNames()) {
                if (Constants.QUERY_PARAM_ACTION.equals(param)) {
                    this.mIntent.setAction(uri.getQueryParameter(param));
                } else if (Constants.QUERY_PARAM_DATA.equals(param)) {
                    this.mIntent.setData(Uri.parse(uri.getQueryParameter(param)));
                } else if (Constants.isSurfaceParam(param)) {
                    setSurfaceParam(param, uri.getQueryParameter(param));
                } else {
                    this.mIntent.putExtra(param, uri.getQueryParameter(param));
                }
            }
            this.mIntent.setFlags(335544320);
        }

        private static ComponentName getLaunchComponentName(Uri uri) {
            if (Constants.URI_SCHEME.equals(uri.getScheme())) {
                return ComponentName.unflattenFromString(String.format("%s%s", uri.getAuthority(), uri.getPath()));
            }
            throw new IllegalArgumentException("Unexpected URI scheme: " + uri.getScheme());
        }

        private void setDefaultPanelSizeAndDensity(Context context, String packageName) {
            this.mWidth = Constants.DEFAULT_WIDTH;
            this.mHeight = Constants.DEFAULT_HEIGHT;
            this.mDisplayDensity = 160;
            for (String entry : context.getResources().getStringArray(R.array.default_panel_sizes)) {
                String[] parts = entry.split(",");
                if (parts.length >= 3 && parts[0].equals(packageName)) {
                    try {
                        this.mWidth = Integer.parseInt(parts[1]);
                        this.mHeight = Integer.parseInt(parts[2]);
                        if (parts.length > 3) {
                            this.mDisplayDensity = Integer.parseInt(parts[3]);
                            return;
                        }
                        return;
                    } catch (NumberFormatException e) {
                        Log.e(PanelApp.TAG, "Invalid panel size", e);
                        return;
                    }
                }
            }
        }

        private void setDefaultKeyboardPosition(Context context, String packageName) {
            for (String entry : context.getResources().getStringArray(R.array.keyboard_position)) {
                String[] parts = entry.split(",");
                if (parts.length == 2 && parts[0].equals(packageName)) {
                    try {
                        this.mKeyboardPosition = Integer.parseInt(parts[1]);
                        return;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            this.mKeyboardPosition = 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFromEnvironment(Bundle bundle) {
            this.mWidth = getIntFromEnvironment(bundle, Constants.KEY_WIDTH, this.mWidth);
            this.mHeight = getIntFromEnvironment(bundle, Constants.KEY_HEIGHT, this.mHeight);
            this.mShape = getIntFromEnvironment(bundle, Constants.KEY_SHAPE, this.mShape);
            this.mDisplayDensity = getIntFromEnvironment(bundle, Constants.KEY_DENSITY, this.mDisplayDensity);
            this.mKeyboardPosition = getIntFromEnvironment(bundle, Constants.KEY_DENSITY, this.mKeyboardPosition);
        }

        private void setSurfaceParam(String key, String stringValue) {
            try {
                int value = Integer.parseInt(stringValue);
                char c = 65535;
                switch (key.hashCode()) {
                    case -776825276:
                        if (key.equals(Constants.KEY_DENSITY)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 920581195:
                        if (key.equals(Constants.KEY_HEIGHT)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1286862845:
                        if (key.equals(Constants.KEY_SHAPE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1290589730:
                        if (key.equals(Constants.KEY_WIDTH)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1880136240:
                        if (key.equals(Constants.KEY_KEYBOARD_POSITION)) {
                            c = 4;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    this.mWidth = value;
                } else if (c == 1) {
                    this.mHeight = value;
                } else if (c == 2) {
                    this.mDisplayDensity = value;
                } else if (c == 3) {
                    this.mShape = value;
                } else if (c != 4) {
                    Log.w(PanelApp.TAG, "Unexpected surface parameter: " + key);
                } else {
                    this.mKeyboardPosition = value;
                }
            } catch (NumberFormatException e) {
                Log.w(PanelApp.TAG, "Invalid value for '" + key + "'", e);
            }
        }

        private int getIntFromEnvironment(Bundle bundle, String key, int defaultValue) {
            String stringValue = bundle.getString(key);
            if (stringValue == null) {
                return defaultValue;
            }
            try {
                int value = Integer.parseInt(stringValue);
                if (value >= 0) {
                    return value;
                }
                Log.w(PanelApp.TAG, "Expected non-negative value for '" + key + "'");
                return defaultValue;
            } catch (NumberFormatException e) {
                Log.w(PanelApp.TAG, "Invalid value for '" + key + "'", e);
                return defaultValue;
            }
        }
    }
}
