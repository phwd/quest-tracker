package com.oculus.vrshell.panels;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.automation.AutomationHelpers;
import com.oculus.vrshell.panels.systemtooltip.TooltipColor;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipManager;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates;
import com.oculus.vrshell.sdk.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AndroidPanelApp implements GenericInputListener {
    private static int AUTOMATION_UNIQUE_ID_FACTORY = 1;
    protected static final String IPC_COMMAND_AUTOMATION = "automation";
    protected static final String IPC_COMMAND_AUTOMATION_ACTIVATEVIEW = "activateView";
    protected static final String IPC_COMMAND_AUTOMATION_QUERYLAYER = "queryLayer";
    protected static final String IPC_COMMAND_AUTOMATION_QUERYVIEW = "queryView";
    public static final String MAIN_LAYER = "#main";
    private static final String OC_SHELL_RENDER_SCALE = "_oc_shell_render_scale";
    private static final String TAG = AndroidPanelApp.class.getSimpleName();
    private final boolean mAutomationEnabled;
    private final Context mContext;
    private final InputFrame mCurrentInputFrame = new InputFrame();
    private boolean mDebugViewIdsEnabled;
    protected final Map<String, String> mEnvironmentArgs = new HashMap();
    protected final FrameCommandChannel mFrameCommandChannel;
    protected final KeyboardHandler mKeyboardHandler;
    private final InputFrame mLastInputFrame = new InputFrame();
    private final Map<String, AndroidPanelLayer> mLayers;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private long mNativePanelApp;
    private final List<PanelFrameCallback> mPanelFrameCallbacks = new ArrayList();
    private final Map<String, Surface> mSurfaces;
    protected final TooltipManager mTooltipManager;

    public interface PanelFrameCallback {
        void onBeginFrame(InputFrame inputFrame);
    }

    public interface ViewConfigurator {
        void configure(View view);
    }

    private native void nativeHideLayer(long j, String str);

    private native void nativeQueueRawCommand(long j, String str);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeSetNextFrameAction(long j, String str, String str2);

    private native void nativeShowLayer(long j, String str, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, int i3, boolean z3);

    /* access modifiers changed from: protected */
    public abstract long createNativeInstance();

    /* access modifiers changed from: protected */
    public abstract View createViewForLayer(String str, int i, Context context);

    /* access modifiers changed from: protected */
    public abstract AndroidPanelLayer.Spec getLayerSpec(String str, int i);

    public AndroidPanelApp(Application application, Context context, Surface panelSurface, Map<String, Surface> multiSurface, Bundle panelBundle) {
        Log.i(TAG, "Creating AndroidPanelApp");
        this.mFrameCommandChannel = createCommandChannel();
        for (String key : panelBundle.keySet()) {
            String value = (String) panelBundle.get(key);
            String str = TAG;
            Log.i(str, key + " = " + value);
            this.mEnvironmentArgs.put(key, value);
        }
        this.mNativePanelApp = createNativeInstance();
        this.mContext = context;
        this.mLayers = new HashMap((multiSurface.size() * 2) + 1);
        this.mSurfaces = new HashMap((multiSurface.size() * 2) + 1);
        this.mSurfaces.put(MAIN_LAYER, panelSurface);
        this.mSurfaces.putAll(multiSurface);
        this.mKeyboardHandler = new KeyboardHandler(this);
        this.mTooltipManager = new TooltipManager();
        this.mFrameCommandChannel.sendCommand("cursor 1.0");
        float scaleFactor = this.mEnvironmentArgs.containsKey("_oc_shell_render_scale") ? Float.parseFloat(this.mEnvironmentArgs.get("_oc_shell_render_scale")) : 1.0f;
        Resources resources = this.mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        int densityDpi = Math.round(160.0f * scaleFactor);
        displayMetrics.densityDpi = densityDpi;
        configuration.densityDpi = densityDpi;
        resources.updateConfiguration(configuration, displayMetrics);
        this.mAutomationEnabled = "true".equals(this.mEnvironmentArgs.get(AutomationHelpers.AUTOMATION_ENVIRONMENT_ARG));
        this.mDebugViewIdsEnabled = "true".equals(this.mEnvironmentArgs.get(AutomationHelpers.AUTOMATION_DEBUG_IDS_ENVIRONMENT_ARG));
        if (this.mAutomationEnabled) {
            this.mFrameCommandChannel.sendCommand(getAutomationProtocol());
        }
    }

    public final void inflateDefaultLayers() {
        for (String layerName : getLayersToAutoEnable()) {
            ensurePanelLayer(layerName, -1, null);
        }
    }

    public String[] getLayersToAutoEnable() {
        return new String[]{MAIN_LAYER};
    }

    public long getNativePointer() {
        long j = this.mNativePanelApp;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Access to native pointer after native and Java objects were destroyed.");
    }

    public void addPanelFrameCallback(PanelFrameCallback callback) {
        this.mPanelFrameCallbacks.add(callback);
    }

    /* access modifiers changed from: protected */
    public boolean isAutomationEnabled() {
        return this.mAutomationEnabled;
    }

    /* access modifiers changed from: protected */
    public boolean isViewIdDebuggingEnabled() {
        return this.mDebugViewIdsEnabled;
    }

    /* access modifiers changed from: protected */
    public String getAutomationProtocol() {
        return "automationPort 0 androidUI";
    }

    public void frame(String inputLayerName, float cursorX, float cursorY, long buttons, long flags, float touchX, float touchY, float battery) {
        this.mCurrentInputFrame.set(cursorX, cursorY, buttons, flags, touchX, touchY, battery);
        for (PanelFrameCallback callback : this.mPanelFrameCallbacks) {
            callback.onBeginFrame(this.mCurrentInputFrame);
        }
        AndroidPanelLayer inputLayer = getPanelLayer(inputLayerName);
        if (inputLayer != null) {
            inputLayer.frame(this.mCurrentInputFrame);
        } else {
            String str = TAG;
            Log.w(str, "Received layer input for hidden layer:  " + inputLayerName);
        }
        for (AndroidPanelLayer layer : this.mLayers.values()) {
            if (!inputLayerName.equals(layer.getName())) {
                layer.frame();
            }
        }
        if (this.mCurrentInputFrame.isButtonRelease(this.mLastInputFrame, PanelButton.getAnyActionFlagValue())) {
            onActionButton();
        }
        if (this.mCurrentInputFrame.isButtonRelease(this.mLastInputFrame, PanelButton.getAnyBackFlagValue())) {
            onBackButton();
        }
        this.mLastInputFrame.set(this.mCurrentInputFrame);
        if (this.mDebugViewIdsEnabled) {
            if (this.mCurrentInputFrame.isCursorInside()) {
                simulateOnHover(inputLayer, cursorX, cursorY);
            } else {
                this.mTooltipManager.hideTooltip();
            }
        }
        this.mTooltipManager.frame(this.mFrameCommandChannel);
    }

    /* access modifiers changed from: protected */
    public boolean handleGenericMessage(String message) {
        Scanner parser = new Scanner(message);
        try {
            if (IPC_COMMAND_AUTOMATION.equals(parser.next())) {
                return handleAutomationMessage(parser, message);
            }
            return false;
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return false;
        } catch (JSONException e2) {
            String str2 = TAG;
            Log.e(str2, "Error constructing JSON Response: " + e2.getMessage());
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b A[Catch:{ InputMismatchException -> 0x0076 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005c A[Catch:{ InputMismatchException -> 0x0076 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A[Catch:{ InputMismatchException -> 0x0076 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0067 A[Catch:{ InputMismatchException -> 0x0076 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean handleAutomationMessage(java.util.Scanner r9, java.lang.String r10) throws org.json.JSONException {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.handleAutomationMessage(java.util.Scanner, java.lang.String):boolean");
    }

    private String handleAutomationMessageQueryLayer(Scanner parser, int requestId) throws JSONException {
        try {
            return automationQueryLayer(requestId, parser.next());
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return null;
        }
    }

    private String handleAutomationMessageQueryView(Scanner parser, int requestId) throws JSONException {
        try {
            return automationQueryView(requestId, parser.next(), parser.next(), parser.next(), parser.nextInt() == 1);
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return null;
        }
    }

    private String handleAutomationMessageActivateView(Scanner parser, int requestId) throws JSONException {
        try {
            return automationActivateView(requestId, parser.next(), parser.next(), parser.next());
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void commitFrame() {
        for (String rawCommand : this.mFrameCommandChannel.extractFrameCommands()) {
            nativeQueueRawCommand(getNativePointer(), rawCommand);
        }
    }

    /* access modifiers changed from: protected */
    public void onSystemDialogResult(String dialogResultJsonString) {
    }

    public void destroy() {
        Log.i(TAG, "destroyed");
        for (AndroidPanelLayer layer : this.mLayers.values()) {
            layer.destroy();
        }
        this.mNativePanelApp = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0029, code lost:
        if (r6.equals("layerShow") == false) goto L_0x0036;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void nextFrameAction(java.lang.String r6, java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.nextFrameAction(java.lang.String, java.lang.String):void");
    }

    public void onForeground() {
    }

    public void onBackground() {
    }

    public void attachDefaultKeyboardHandler(View view) {
        this.mKeyboardHandler.attachToView(view);
    }

    /* access modifiers changed from: protected */
    public void handleKeyboardText(String cookie, String keyboardText) {
        this.mKeyboardHandler.handleKeyboardText(cookie, keyboardText);
    }

    /* access modifiers changed from: protected */
    public void handleKeyboardCompose(String cookie, String keyboardComposeText) {
        this.mKeyboardHandler.handleKeyboardCompose(cookie, keyboardComposeText);
    }

    /* access modifiers changed from: protected */
    public void handleKeyboardCommand(String cookie, String keyboardCommand) {
        this.mKeyboardHandler.handleKeyboardCommand(cookie, keyboardCommand);
    }

    /* access modifiers changed from: protected */
    public FrameCommandChannel createCommandChannel() {
        return new FrameCommandChannel();
    }

    public FrameCommandChannel getCommandChannel() {
        return this.mFrameCommandChannel;
    }

    public final void queueRawCommand(String command) {
        nativeQueueRawCommand(getNativePointer(), command);
    }

    protected static int getUniqueAutomationId() {
        int i = AUTOMATION_UNIQUE_ID_FACTORY;
        AUTOMATION_UNIQUE_ID_FACTORY = i + 1;
        return i;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener
    public boolean onActionButton() {
        return false;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener
    public boolean onBackButton() {
        return false;
    }

    public String getEnvironmentArg(String key) {
        return this.mEnvironmentArgs.get(key);
    }

    /* access modifiers changed from: protected */
    public AndroidPanelLayer ensurePanelLayer(String layerName, int viewIdentifier, ViewConfigurator viewConfigurator) {
        AndroidPanelLayer layer;
        Log.i(TAG, String.format("Calling ensurePanelLayer on layer \"%s\" with view ID %d", layerName, Integer.valueOf(viewIdentifier)));
        if (this.mSurfaces.containsKey(layerName)) {
            Surface layerSurface = this.mSurfaces.get(layerName);
            AndroidPanelLayer.Spec spec = getLayerSpec(layerName, viewIdentifier);
            if (!this.mLayers.containsKey(layerName)) {
                layer = new AndroidPanelLayer(this.mContext, layerSurface, spec);
                this.mLayers.put(layerName, layer);
            } else {
                layer = this.mLayers.get(layerName);
                layer.setSpec(spec);
            }
            View layerView = createViewForLayer(layer.getName(), viewIdentifier, layer.getPresentationContext());
            if (viewConfigurator != null) {
                viewConfigurator.configure(layerView);
            }
            Size measured = measureLayerView(layerView, layer.getResizeBehavior(), layer.getWidthInPixels(), layer.getHeightInPixels());
            layer.setSize(measured.getWidth(), measured.getHeight());
            layer.setContentView(layerView);
            showPanelLayer(layer);
            return layer;
        }
        throw new RuntimeException("Was not constructed with a layer named: " + layerName);
    }

    /* access modifiers changed from: protected */
    public void attachResizeLayoutListener(final AndroidPanelLayer layer, final AndroidPanelLayer.ResizeBehavior resizeBehavior) {
        layer.getContentView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            /* class com.oculus.vrshell.panels.AndroidPanelApp.AnonymousClass1 */

            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (AndroidPanelApp.this.mNativePanelApp == 0) {
                    Log.w(AndroidPanelApp.TAG, "Ignoring onLayoutChange because AndroidPanelApp is already destroyed");
                } else if (!layer.getVisibility() || !layer.hasPresentation()) {
                    Log.w(AndroidPanelApp.TAG, "Ignoring onLayoutChange because the layer is already hidden or destroyed");
                } else {
                    int currentWidthInPixels = layer.getWidthInPixels();
                    int currentHeightInPixels = layer.getHeightInPixels();
                    View view = layer.getContentView();
                    Size measured = AndroidPanelApp.this.measureLayerView(view, resizeBehavior, currentWidthInPixels, currentHeightInPixels);
                    int newWidthInPixels = measured.getWidth();
                    int newHeightInPixels = measured.getHeight();
                    if (newWidthInPixels != currentWidthInPixels || newHeightInPixels != currentHeightInPixels) {
                        String layerName = layer.getName();
                        Log.i(AndroidPanelApp.TAG, String.format("Layout change has caused layer \"%s\" to be resized from %d X %d to %d X %d", layerName, Integer.valueOf(currentWidthInPixels), Integer.valueOf(currentHeightInPixels), Integer.valueOf(newWidthInPixels), Integer.valueOf(newHeightInPixels)));
                        ((ViewGroup) view.getParent()).removeView(view);
                        AndroidPanelApp androidPanelApp = AndroidPanelApp.this;
                        androidPanelApp.nativeSetNextFrameAction(androidPanelApp.getNativePointer(), "layerResize", layer.getName());
                        layer.setSize(newWidthInPixels, newHeightInPixels);
                        layer.setContentView(view);
                        AndroidPanelApp.this.showPanelLayer(layer);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Size measureLayerView(View view, AndroidPanelLayer.ResizeBehavior resizeBehavior, int initialWidthInPixels, int initialHeightInPixels) {
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            int maxLayerWidthInPixels = DensityUtils.dipToPixelsInt(1000.0f, this.mContext.getResources().getDisplayMetrics());
            Log.i(TAG, String.format("measureLayerView adjusting initial width from %d to %d due to resize behavior.", Integer.valueOf(initialWidthInPixels), Integer.valueOf(maxLayerWidthInPixels)));
            initialWidthInPixels = maxLayerWidthInPixels;
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            int maxLayerHeightInPixels = DensityUtils.dipToPixelsInt(1000.0f, this.mContext.getResources().getDisplayMetrics());
            Log.i(TAG, String.format("measureLayerView adjusting initial height from %d to %d due to resize behavior.", Integer.valueOf(initialHeightInPixels), Integer.valueOf(maxLayerHeightInPixels)));
            initialHeightInPixels = maxLayerHeightInPixels;
        }
        Log.i(TAG, String.format("measureLayerView with initial width %d, initial height %d, resize behavior %s.", Integer.valueOf(initialWidthInPixels), Integer.valueOf(initialHeightInPixels), resizeBehavior.name()));
        int widthInPixels = initialWidthInPixels;
        int heightInPixels = initialHeightInPixels;
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            view.measure(View.MeasureSpec.makeMeasureSpec(widthInPixels, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            heightInPixels = view.getMeasuredHeight();
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(heightInPixels, 1073741824));
            widthInPixels = view.getMeasuredWidth();
        }
        Log.i(TAG, String.format("Measured width %d and height %d.", Integer.valueOf(widthInPixels), Integer.valueOf(heightInPixels)));
        return new Size(Math.max(widthInPixels, 1), Math.max(heightInPixels, 1));
    }

    private final void hideLayer(String layerName, boolean preserveView) {
        if (this.mNativePanelApp == 0) {
            Log.w(TAG, "Ignoring hideLayer() because AndroidPanelApp is already destroyed");
        } else if (this.mLayers.containsKey(layerName)) {
            AndroidPanelLayer layer = this.mLayers.get(layerName);
            if (!preserveView) {
                layer.setContentView(new View(this.mContext));
            }
            layer.hide();
            layer.resetPanelSizeRequestVersions();
            layer.setVisibility(false);
            nativeHideLayer(getNativePointer(), layerName);
        } else {
            throw new RuntimeException("Trying to hide layer that doesn't exist:  " + layerName);
        }
    }

    /* access modifiers changed from: protected */
    public final void hideLayerAndPreserveView(String layerName) {
        hideLayer(layerName, true);
    }

    /* access modifiers changed from: protected */
    public final void hideLayer(String layerName) {
        hideLayer(layerName, false);
    }

    /* access modifiers changed from: protected */
    public final void unhideLayer(String layerName) {
        if (this.mNativePanelApp == 0) {
            Log.w(TAG, "Ignoring unhideLayer() because AndroidPanelApp is already destroyed");
        } else if (this.mLayers.containsKey(layerName)) {
            AndroidPanelLayer layer = this.mLayers.get(layerName);
            layer.unhide();
            showPanelLayer(layer);
        } else {
            throw new RuntimeException("Trying to unhide layer that doesn't exist:  " + layerName);
        }
    }

    /* access modifiers changed from: protected */
    public final AndroidPanelLayer getPanelLayer(String name) {
        return this.mLayers.get(name);
    }

    private void tryShowLayer(final AndroidPanelLayer layer) {
        Log.i(TAG, String.format("tryShowLayer(%s)", layer.getName()));
        if (layer.needsToBeSized()) {
            Log.i(TAG, String.format("Layer %s needs to be sized, deferring showing layer.", layer.getName()));
            return;
        }
        layer.show();
        this.mMainHandler.post(new Runnable() {
            /* class com.oculus.vrshell.panels.AndroidPanelApp.AnonymousClass2 */

            public void run() {
                if (layer.getVisibility()) {
                    Log.i(AndroidPanelApp.TAG, String.format("Turning on layer visibility for layer %s.", layer.getName()));
                    if (AndroidPanelApp.this.mNativePanelApp != 0) {
                        AndroidPanelApp.this.queueRawCommand(String.format(Locale.ROOT, "layerVisibility %s 1", layer.getName()));
                        return;
                    }
                    Log.i(AndroidPanelApp.TAG, "Not sending layer visibility command, panel app has been destroyed.");
                    return;
                }
                Log.i(AndroidPanelApp.TAG, String.format("Layer is no longer visible. Not sending layer visibility for layer %s.", layer.getName()));
            }
        });
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showPanelLayer(AndroidPanelLayer layer) {
        String layerName = layer.getName();
        int layerWidthInPixels = layer.getWidthInPixels();
        int layerHeightInPixels = layer.getHeightInPixels();
        boolean isLayerHitTestable = layer.isHitTestable();
        int layerShape = layer.getShape().value;
        float posX = layer.getPositionX();
        float posY = layer.getPositionY();
        float rotX = layer.getRotationX();
        float rotY = layer.getRotationY();
        float rotZ = layer.getRotationZ();
        float density = layer.getDensity();
        boolean isSystemPositioned = layer.isSystemPositioned();
        boolean isSupersampled = layer.isSupersampled();
        Log.i(TAG, String.format("layerTransform: Showing panel layer \"%s\" in VrShell with width %d, height %d, position( %f , %f ), rotation (%f , %f , %f )  hit-testable %b, shape %d, and system-positioned %b. Density: %f. Supersampled: %b ", layerName, Integer.valueOf(layerWidthInPixels), Integer.valueOf(layerHeightInPixels), Float.valueOf(posX), Float.valueOf(posY), Float.valueOf(rotX), Float.valueOf(rotY), Float.valueOf(rotZ), Boolean.valueOf(isLayerHitTestable), Integer.valueOf(layerShape), Boolean.valueOf(isSystemPositioned), Float.valueOf(density), Boolean.valueOf(isSupersampled)));
        layer.onPanelSizeRequested();
        layer.setVisibility(true);
        nativeShowLayer(getNativePointer(), layerName, layerWidthInPixels, layerHeightInPixels, posX, posY, rotX, rotY, rotZ, density, isSupersampled, isLayerHitTestable, layerShape, isSystemPositioned);
    }

    protected static void performClick(View view, float x, float y) {
        MotionEvent event = MotionEvent.obtain(0, SystemClock.uptimeMillis(), 0, x, y, 0);
        view.dispatchTouchEvent(event);
        event.setAction(1);
        view.dispatchTouchEvent(event);
        event.recycle();
    }

    protected static void performClick(View view) {
        performClick(view, 0.0f, 0.0f);
    }

    public String automationActivateView(int requestId, String layerId, String viewId, String idType) throws JSONException {
        View queryView;
        JSONObject resultObject = new JSONObject();
        resultObject.put("requestId", requestId);
        if ("id".equals(idType)) {
            queryView = findViewByName(layerId, viewId);
        } else if ("tag".equals(idType)) {
            queryView = findViewByTag(layerId, viewId);
        } else if ("automationId".equals(idType)) {
            queryView = findViewByAutomationId(layerId, viewId);
        } else {
            Log.d(TAG, String.format("Invalid idType specified %s in call to activateView.", idType));
            queryView = null;
        }
        if (queryView != null) {
            performClick(queryView);
            resultObject.put("view", convertViewToObject(queryView, getContext().getResources(), false));
            resultObject.put(AutomationHelpers.AUTOMATION_RESULT_KEY, AutomationHelpers.AUTOMATION_RESULT_VALUE_SUCCESS);
        } else {
            resultObject.put(AutomationHelpers.AUTOMATION_RESULT_KEY, AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        Log.d(TAG, resultObject.toString(2));
        return resultObject.toString();
    }

    public String automationQueryLayer(int requestId, String layerId) throws JSONException {
        JSONObject resultObject = new JSONObject();
        resultObject.put("requestId", requestId);
        AndroidPanelLayer queryLayer = getPanelLayer(layerId);
        if (queryLayer != null) {
            resultObject.put(AutomationHelpers.AUTOMATION_RESULT_KEY, AutomationHelpers.AUTOMATION_RESULT_VALUE_SUCCESS);
            JSONObject layerDescription = new JSONObject();
            layerDescription.put("layerId", layerId);
            layerDescription.put("visible", queryLayer.isVisible());
            layerDescription.put("width", queryLayer.getWidthInPixels());
            layerDescription.put("height", queryLayer.getHeightInPixels());
            resultObject.put("layer", layerDescription);
            View layerContentView = queryLayer.getContentView();
            if (layerContentView != null) {
                layerDescription.put("view", convertViewToObject(layerContentView, getContext().getResources(), true));
            } else {
                layerDescription.put("view", (Object) null);
            }
        } else {
            resultObject.put(AutomationHelpers.AUTOMATION_RESULT_KEY, AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        Log.d(TAG, resultObject.toString(2));
        return resultObject.toString();
    }

    public String automationQueryView(int requestId, String layerId, String viewId, String idType, boolean recurseChildren) throws JSONException {
        View queryView;
        JSONObject resultObject = new JSONObject();
        resultObject.put("requestId", requestId);
        resultObject.put("layerId", layerId);
        if ("id".equals(idType)) {
            queryView = findViewByName(layerId, viewId);
        } else if ("tag".equals(idType)) {
            queryView = findViewByTag(layerId, viewId);
        } else if ("automationId".equals(idType)) {
            queryView = findViewByAutomationId(layerId, viewId);
        } else {
            Log.d(TAG, String.format("Invalid idType specified %s in call to queryView.", idType));
            queryView = null;
        }
        if (queryView != null) {
            resultObject.put("view", convertViewToObject(queryView, getContext().getResources(), recurseChildren));
            resultObject.put(AutomationHelpers.AUTOMATION_RESULT_KEY, AutomationHelpers.AUTOMATION_RESULT_VALUE_SUCCESS);
        } else {
            resultObject.put(AutomationHelpers.AUTOMATION_RESULT_KEY, AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        Log.d(TAG, resultObject.toString(2));
        return resultObject.toString();
    }

    private View findViewByTag(String layerId, String viewTag) {
        View layerContentView;
        AndroidPanelLayer layer = getPanelLayer(layerId);
        if (layer == null || (layerContentView = layer.getContentView()) == null) {
            return null;
        }
        return layerContentView.findViewWithTag(viewTag);
    }

    private View findViewByName(String layerId, String viewId) {
        int resId;
        View layerContentView;
        AndroidPanelLayer layer = getPanelLayer(layerId);
        if (layer == null || (resId = getContext().getResources().getIdentifier(viewId, "id", getContext().getPackageName())) == 0 || (layerContentView = layer.getContentView()) == null) {
            return null;
        }
        return layerContentView.findViewById(resId);
    }

    private View findViewByAutomationId(String layerId, String automationId) {
        View layerContentView;
        Log.i(TAG, String.format("findViewByAutomationId %s %s", layerId, automationId));
        AndroidPanelLayer layer = getPanelLayer(layerId);
        if (layer == null || (layerContentView = layer.getContentView()) == null) {
            return null;
        }
        return findViewByAutomationIdWithParent(layerContentView, Integer.valueOf(Integer.parseInt(automationId)));
    }

    private View findViewByAutomationIdWithParent(View parentView, Integer automationId) {
        if (automationId.equals(parentView.getTag(R.id.automation_unique_id_key))) {
            return parentView;
        }
        if (!(parentView instanceof ViewGroup)) {
            return null;
        }
        ViewGroup parentViewGroup = (ViewGroup) parentView;
        int childCount = parentViewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childViewWithAutomationId = findViewByAutomationIdWithParent(parentViewGroup.getChildAt(i), automationId);
            if (childViewWithAutomationId != null) {
                return childViewWithAutomationId;
            }
        }
        return null;
    }

    private JSONObject convertViewToObject(View rootView, Resources resources, boolean recurseChildren) throws JSONException {
        JSONObject currentView = new JSONObject();
        int resourceId = rootView.getId();
        currentView.put("id", resourceId);
        if (resourceId != -1) {
            currentView.put("name", resources.getResourceEntryName(rootView.getId()));
        }
        if (rootView.getTag(R.id.automation_unique_id_key) == null) {
            rootView.setTag(R.id.automation_unique_id_key, Integer.valueOf(getUniqueAutomationId()));
        }
        currentView.put("automation_id", rootView.getTag(R.id.automation_unique_id_key));
        currentView.put("tag", rootView.getTag());
        currentView.put("type", rootView.getClass().toString());
        currentView.put("visibility", rootView.getVisibility());
        if (rootView instanceof ViewGroup) {
            ViewGroup rootViewGroup = (ViewGroup) rootView;
            int childCount = rootViewGroup.getChildCount();
            currentView.put("childCount", childCount);
            if (recurseChildren) {
                JSONArray children = new JSONArray();
                for (int i = 0; i < childCount; i++) {
                    children.put(convertViewToObject(rootViewGroup.getChildAt(i), resources, recurseChildren));
                }
                currentView.put("children", children);
            }
        } else if ((rootView instanceof TextView) || (rootView instanceof EditText)) {
            currentView.put(TooltipDefinition.TOOLTIP_TEXT_KEY, ((TextView) rootView).getText());
            if (rootView instanceof CheckBox) {
                currentView.put("checked", ((CheckBox) rootView).isChecked());
            }
        }
        return currentView;
    }

    private void simulateOnHover(AndroidPanelLayer layer, float cursorX, float cursorY) {
        View v = findView(layer.getContentView(), (int) (((float) layer.getWidthInPixels()) * cursorX), (int) (((double) ((float) layer.getHeightInPixels())) * (1.0d - ((double) cursorY))));
        if (v != null) {
            onHoverDebugTooltip(layer, v);
        }
    }

    private static View findView(View v, int x, int y) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            int childCount = vg.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View result = findView(vg.getChildAt(i), x, y);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
        Rect rect = new Rect();
        v.getGlobalVisibleRect(rect);
        if (rect.contains(x, y)) {
            return v;
        }
        return null;
    }

    private void onHoverDebugTooltip(AndroidPanelLayer layer, View v) {
        StringBuilder tooltipText = new StringBuilder();
        int resId = v.getId();
        if (resId >= 0) {
            tooltipText.append("Class: ");
            tooltipText.append(v.getClass().getCanonicalName());
            tooltipText.append("\nID: ");
            tooltipText.append(getContext().getResources().getResourceEntryName(resId));
            Object tag = v.getTag();
            if (tag != null) {
                tooltipText.append("\nTag: ");
                tooltipText.append(tag.toString());
            }
            Rect rect = new Rect();
            v.getGlobalVisibleRect(rect);
            this.mTooltipManager.showTooltip(new TooltipDefinition(layer.getName(), tooltipText.toString(), TooltipPosition.Bottom, TooltipColor.Black, "view debug tooltip"), TooltipUVCoordinates.getTooltipUVCoordinates(rect.left, rect.top, v.getWidth(), v.getHeight(), layer.getWidthInPixels(), layer.getHeightInPixels(), TooltipPosition.Bottom));
        }
    }

    /* access modifiers changed from: protected */
    public void configureLayerSurfaceGeometryBorderRadius(String layerName, AndroidPanelLayer.BorderRadiusType borderRadiusType, int borderRadiusInPixels) {
        Log.i(TAG, String.format(Locale.ROOT, "Sending surface geometry border radius with type %s and value %d for layer %s.", borderRadiusType.getIPCString(), Integer.valueOf(borderRadiusInPixels), layerName));
        getCommandChannel().sendCommand(String.format(Locale.ROOT, "layerSurfaceGeometryBorderRadius %s %s %d", layerName, borderRadiusType.getIPCString(), Integer.valueOf(borderRadiusInPixels)));
    }
}
