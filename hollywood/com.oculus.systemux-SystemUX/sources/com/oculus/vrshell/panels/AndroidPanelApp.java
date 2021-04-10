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
import android.widget.TextView;
import com.google.common.primitives.Ints;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.panelapp.social.SocialBundleConstants;
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
    protected static final String IPC_COMMAND_AUTOMATION_COMMAND = "command";
    protected static final String IPC_COMMAND_AUTOMATION_COMMAND_HOVER = "hoverEnter";
    protected static final String IPC_COMMAND_AUTOMATION_COMMAND_SCROLL = "scroll";
    protected static final String IPC_COMMAND_AUTOMATION_IDTYPE_AUTOMATIONID = "automationId";
    protected static final String IPC_COMMAND_AUTOMATION_IDTYPE_ID = "id";
    protected static final String IPC_COMMAND_AUTOMATION_IDTYPE_TAG = "tag";
    protected static final String IPC_COMMAND_AUTOMATION_QUERYLAYER = "queryLayer";
    protected static final String IPC_COMMAND_AUTOMATION_QUERYVIEW = "queryView";
    public static final String MAIN_LAYER = "#main";
    private static final String OC_SHELL_RENDER_SCALE = "_oc_shell_render_scale";
    protected static final String SCROLL_DIRECTION_HORIZONTAL = "horizontal";
    protected static final String SCROLL_DIRECTION_VERTICAL = "vertical";
    private static final String TAG = "AndroidPanelApp";
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

    public interface ViewCreator {
        View createView(Context context);

        String name();
    }

    private native void nativeHideLayer(long j, String str, int i, boolean z);

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
    public String getAutomationProtocol() {
        return "automationPort 0 androidUI";
    }

    /* access modifiers changed from: protected */
    public abstract AndroidPanelLayer.Spec getLayerSpec(String str, int i);

    @Override // com.oculus.vrshell.panels.GenericInputListener
    public boolean onActionButton() {
        return false;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener
    public boolean onBackButton() {
        return false;
    }

    public void onBackground() {
    }

    /* access modifiers changed from: protected */
    public void onDeepLink(String str, String str2) {
    }

    public void onForeground() {
    }

    /* access modifiers changed from: protected */
    public void onSystemDialogResult(String str) {
    }

    public AndroidPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        Log.i(TAG, "Creating AndroidPanelApp");
        this.mFrameCommandChannel = createCommandChannel();
        for (String str : bundle.keySet()) {
            this.mEnvironmentArgs.put(str, (String) bundle.get(str));
        }
        this.mNativePanelApp = createNativeInstance();
        this.mContext = context;
        this.mLayers = new HashMap((map.size() * 2) + 1);
        this.mSurfaces = new HashMap((map.size() * 2) + 1);
        this.mSurfaces.put(MAIN_LAYER, surface);
        this.mSurfaces.putAll(map);
        this.mKeyboardHandler = new KeyboardHandler(this);
        this.mTooltipManager = new TooltipManager();
        this.mFrameCommandChannel.sendCommand("cursor 1.0");
        float parseFloat = this.mEnvironmentArgs.containsKey(OC_SHELL_RENDER_SCALE) ? Float.parseFloat(this.mEnvironmentArgs.get(OC_SHELL_RENDER_SCALE)) : 1.0f;
        Resources resources = this.mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        int round = Math.round(parseFloat * 160.0f);
        displayMetrics.densityDpi = round;
        configuration.densityDpi = round;
        resources.updateConfiguration(configuration, displayMetrics);
        this.mAutomationEnabled = SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(this.mEnvironmentArgs.get(AutomationHelpers.AUTOMATION_ENVIRONMENT_ARG));
        this.mDebugViewIdsEnabled = SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(this.mEnvironmentArgs.get(AutomationHelpers.AUTOMATION_DEBUG_IDS_ENVIRONMENT_ARG));
        if (this.mAutomationEnabled) {
            this.mFrameCommandChannel.sendCommand(getAutomationProtocol());
        }
    }

    public final void inflateDefaultLayers() {
        for (String str : getLayersToAutoEnable()) {
            ensurePanelLayer(str, -1, (ViewConfigurator) null);
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

    public void addPanelFrameCallback(PanelFrameCallback panelFrameCallback) {
        this.mPanelFrameCallbacks.add(panelFrameCallback);
    }

    public void removePanelFrameCallback(PanelFrameCallback panelFrameCallback) {
        this.mPanelFrameCallbacks.remove(panelFrameCallback);
    }

    /* access modifiers changed from: protected */
    public boolean isAutomationEnabled() {
        return this.mAutomationEnabled;
    }

    /* access modifiers changed from: protected */
    public boolean isViewIdDebuggingEnabled() {
        return this.mDebugViewIdsEnabled;
    }

    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        this.mCurrentInputFrame.set(f, f2, j, j2, f3, f4, f5);
        int size = this.mPanelFrameCallbacks.size();
        for (int i = 0; i < size; i++) {
            this.mPanelFrameCallbacks.get(i).onBeginFrame(this.mCurrentInputFrame);
        }
        AndroidPanelLayer panelLayer = getPanelLayer(str);
        if (panelLayer != null) {
            panelLayer.frame(this.mCurrentInputFrame);
        } else if (!MAIN_LAYER.equals(str)) {
            String str2 = TAG;
            Log.w(str2, "Received layer input for hidden layer:  " + str);
        }
        for (AndroidPanelLayer androidPanelLayer : this.mLayers.values()) {
            if (!str.equals(androidPanelLayer.getName())) {
                androidPanelLayer.frame();
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
                simulateOnHover(panelLayer, f, f2);
            } else {
                this.mTooltipManager.hideTooltip();
            }
        }
        this.mTooltipManager.frame(this.mFrameCommandChannel);
    }

    /* access modifiers changed from: protected */
    public boolean handleGenericMessage(String str) {
        Scanner scanner = new Scanner(str);
        try {
            if (IPC_COMMAND_AUTOMATION.equals(scanner.next())) {
                return handleAutomationMessage(scanner, str);
            }
            return false;
        } catch (InputMismatchException e) {
            String str2 = TAG;
            Log.e(str2, "Received Invalid response: " + e.getMessage());
            return false;
        } catch (JSONException e2) {
            String str3 = TAG;
            Log.e(str3, "Error constructing JSON Response: " + e2.getMessage());
            return false;
        }
    }

    private String buildUnknownCommandMessage(String str) {
        return String.format("{\"result\": \"unknown command: %s\"}", str);
    }

    private boolean handleAutomationMessage(Scanner scanner, String str) throws JSONException {
        String str2;
        Log.d(TAG, "handleAutomationMessage: " + str);
        try {
            String next = scanner.next();
            int nextInt = scanner.nextInt();
            StringBuilder sb = new StringBuilder();
            sb.append("automationResponse ");
            sb.append(nextInt);
            sb.append(" ");
            char c = 65535;
            switch (next.hashCode()) {
                case -1806818419:
                    if (next.equals(IPC_COMMAND_AUTOMATION_QUERYVIEW)) {
                        c = 1;
                        break;
                    }
                    break;
                case -1194667464:
                    if (next.equals(IPC_COMMAND_AUTOMATION_ACTIVATEVIEW)) {
                        c = 2;
                        break;
                    }
                    break;
                case -186250903:
                    if (next.equals(IPC_COMMAND_AUTOMATION_QUERYLAYER)) {
                        c = 0;
                        break;
                    }
                    break;
                case 950394699:
                    if (next.equals(IPC_COMMAND_AUTOMATION_COMMAND)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                str2 = handleAutomationMessageQueryLayer(scanner);
            } else if (c == 1) {
                str2 = handleAutomationMessageQueryView(scanner);
            } else if (c == 2) {
                str2 = handleAutomationMessageActivateView(scanner);
            } else if (c != 3) {
                str2 = buildUnknownCommandMessage(next);
            } else {
                str2 = handleAutomationMessageCommand(new JSONObject(scanner.nextLine()));
            }
            if (str2 != null) {
                sb.append(str2);
            } else {
                sb.append("{\"result\": \"invalid command parameters\"}");
            }
            this.mFrameCommandChannel.sendCommand(sb.toString());
        } catch (InputMismatchException e) {
            Log.e(TAG, "Received Invalid response: " + e.getMessage());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String handleAutomationMessageCommand(org.json.JSONObject r5) throws org.json.JSONException {
        /*
            r4 = this;
            java.lang.String r0 = "commandName"
            java.lang.String r0 = r5.getString(r0)
            int r1 = r0.hashCode()
            r2 = -907680051(0xffffffffc9e5e6cd, float:-1883353.6)
            r3 = 1
            if (r1 == r2) goto L_0x0020
            r2 = 246067964(0xeaab2fc, float:4.208059E-30)
            if (r1 == r2) goto L_0x0016
            goto L_0x002a
        L_0x0016:
            java.lang.String r1 = "hoverEnter"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x002a
            r1 = r3
            goto L_0x002b
        L_0x0020:
            java.lang.String r1 = "scroll"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x002a
            r1 = 0
            goto L_0x002b
        L_0x002a:
            r1 = -1
        L_0x002b:
            if (r1 == 0) goto L_0x0039
            if (r1 == r3) goto L_0x0034
            java.lang.String r5 = r4.buildUnknownCommandMessage(r0)
            return r5
        L_0x0034:
            java.lang.String r5 = r4.handleAutomationHoverCommand(r5)
            return r5
        L_0x0039:
            java.lang.String r5 = r4.handleAutomationScrollCommand(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.handleAutomationMessageCommand(org.json.JSONObject):java.lang.String");
    }

    private String handleAutomationScrollCommand(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        String string = jSONObject.getString("direction");
        int i = jSONObject.getInt("pixelAmount");
        View viewFromId = getViewFromId(jSONObject.getString("layerName"), jSONObject.getString("viewId"), jSONObject.getString("idType"));
        if (viewFromId == null) {
            jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        } else if (SCROLL_DIRECTION_HORIZONTAL.equals(string)) {
            if (viewFromId.canScrollHorizontally(i)) {
                viewFromId.scrollBy(i, 0);
                jSONObject2.put(QuickPromotionController.EVENT_VIEW, convertViewToObject(getContext().getResources(), viewFromId, false));
                jSONObject2.put("result", "success");
            } else {
                jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_BOUNDS_REACHED);
            }
        } else if (!SCROLL_DIRECTION_VERTICAL.equals(string)) {
            jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        } else if (viewFromId.canScrollVertically(i)) {
            viewFromId.scrollBy(0, i);
            jSONObject2.put(QuickPromotionController.EVENT_VIEW, convertViewToObject(getContext().getResources(), viewFromId, false));
            jSONObject2.put("result", "success");
        } else {
            jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_BOUNDS_REACHED);
        }
        return jSONObject2.toString();
    }

    protected static void performHover(View view) {
        MotionEvent obtain = MotionEvent.obtain(0, SystemClock.uptimeMillis(), 9, 0.0f, 0.0f, 0);
        obtain.setSource(2);
        view.dispatchGenericMotionEvent(obtain);
        obtain.recycle();
    }

    private String handleAutomationHoverCommand(JSONObject jSONObject) throws JSONException {
        String str = TAG;
        Log.d(str, "handleAutomationHoverCommand: " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        View viewFromId = getViewFromId(jSONObject.getString("layerName"), jSONObject.getString("viewId"), jSONObject.getString("idType"));
        if (viewFromId != null) {
            performHover(viewFromId);
            jSONObject2.put(QuickPromotionController.EVENT_VIEW, convertViewToObject(getContext().getResources(), viewFromId, false));
            jSONObject2.put("result", "success");
        } else {
            jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        return jSONObject2.toString();
    }

    private String handleAutomationMessageQueryLayer(Scanner scanner) throws JSONException {
        try {
            return automationQueryLayer(scanner.next());
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return null;
        }
    }

    private String handleAutomationMessageQueryView(Scanner scanner) throws JSONException {
        try {
            String next = scanner.next();
            String next2 = scanner.next();
            String next3 = scanner.next();
            boolean z = true;
            if (scanner.nextInt() != 1) {
                z = false;
            }
            return automationQueryView(next, next2, next3, z);
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return null;
        }
    }

    private String handleAutomationMessageActivateView(Scanner scanner) throws JSONException {
        try {
            return automationActivateView(scanner.next(), scanner.next(), scanner.next());
        } catch (InputMismatchException e) {
            String str = TAG;
            Log.e(str, "Received Invalid response: " + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void commitFrame() {
        List<String> extractFrameCommands = this.mFrameCommandChannel.extractFrameCommands();
        int size = extractFrameCommands.size();
        for (int i = 0; i < size; i++) {
            nativeQueueRawCommand(getNativePointer(), extractFrameCommands.get(i));
        }
    }

    public void destroy() {
        Log.i(TAG, "destroyed");
        for (AndroidPanelLayer androidPanelLayer : this.mLayers.values()) {
            androidPanelLayer.destroy();
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
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.nextFrameAction(java.lang.String, java.lang.String):void");
    }

    public void attachDefaultKeyboardHandler(View view) {
        this.mKeyboardHandler.attachToView(view);
    }

    /* access modifiers changed from: protected */
    public void handleKeyboardText(String str, String str2) {
        this.mKeyboardHandler.handleKeyboardText(str, str2);
    }

    /* access modifiers changed from: protected */
    public void handleKeyboardCompose(String str, String str2) {
        this.mKeyboardHandler.handleKeyboardCompose(str, str2);
    }

    /* access modifiers changed from: protected */
    public void handleKeyboardCommand(String str, String str2) {
        this.mKeyboardHandler.handleKeyboardCommand(str, str2);
    }

    /* access modifiers changed from: protected */
    public FrameCommandChannel createCommandChannel() {
        return new FrameCommandChannel();
    }

    public FrameCommandChannel getCommandChannel() {
        return this.mFrameCommandChannel;
    }

    public final void queueRawCommand(String str) {
        nativeQueueRawCommand(getNativePointer(), str);
    }

    protected static int getUniqueAutomationId() {
        int i = AUTOMATION_UNIQUE_ID_FACTORY;
        AUTOMATION_UNIQUE_ID_FACTORY = i + 1;
        return i;
    }

    public String getEnvironmentArg(String str) {
        return this.mEnvironmentArgs.get(str);
    }

    /* access modifiers changed from: protected */
    public AndroidPanelLayer ensurePanelLayer(String str, int i, ViewConfigurator viewConfigurator) {
        Log.i(TAG, String.format("ensurePanelLayer(layer: %s, view ID: %d)", str, Integer.valueOf(i)));
        AndroidPanelLayer ensurePanelLayer = ensurePanelLayer(str, getLayerSpec(str, i));
        View createViewForLayer = createViewForLayer(ensurePanelLayer.getName(), i, ensurePanelLayer.getPresentationContext());
        if (viewConfigurator != null) {
            viewConfigurator.configure(createViewForLayer);
        }
        Size measureLayerView = measureLayerView(createViewForLayer, ensurePanelLayer.getResizeBehavior(), ensurePanelLayer.getWidthInPixels(), ensurePanelLayer.getHeightInPixels());
        ensurePanelLayer.setSize(measureLayerView.getWidth(), measureLayerView.getHeight());
        ensurePanelLayer.setContentView(createViewForLayer);
        showPanelLayer(ensurePanelLayer);
        return ensurePanelLayer;
    }

    /* access modifiers changed from: protected */
    public AndroidPanelLayer ensurePanelLayer(String str, AndroidPanelLayer.Spec spec, ViewCreator viewCreator) {
        Log.i(TAG, String.format("ensurePanelLayer(layer: %s, width: %d, height: %d, resize: %s, view creator: %s)", str, Integer.valueOf(spec.width), Integer.valueOf(spec.height), spec.resizeBehavior.name(), viewCreator.name()));
        AndroidPanelLayer ensurePanelLayer = ensurePanelLayer(str, spec);
        View createView = viewCreator.createView(ensurePanelLayer.getPresentationContext());
        Size measureLayerView = measureLayerView(createView, ensurePanelLayer.getResizeBehavior(), ensurePanelLayer.getWidthInPixels(), ensurePanelLayer.getHeightInPixels());
        ensurePanelLayer.setSize(measureLayerView.getWidth(), measureLayerView.getHeight());
        ensurePanelLayer.setContentView(createView);
        showPanelLayer(ensurePanelLayer);
        return ensurePanelLayer;
    }

    private AndroidPanelLayer ensurePanelLayer(String str, AndroidPanelLayer.Spec spec) {
        Log.i(TAG, String.format("ensurePanelLayer(layer: %s, width: %d, height: %d, resize behavior: %s)", str, Integer.valueOf(spec.width), Integer.valueOf(spec.height), spec.resizeBehavior.name()));
        if (this.mSurfaces.containsKey(str)) {
            Surface surface = this.mSurfaces.get(str);
            if (!this.mLayers.containsKey(str)) {
                AndroidPanelLayer androidPanelLayer = new AndroidPanelLayer(this.mContext, surface, spec);
                this.mLayers.put(str, androidPanelLayer);
                return androidPanelLayer;
            }
            AndroidPanelLayer androidPanelLayer2 = this.mLayers.get(str);
            androidPanelLayer2.setSpec(spec);
            return androidPanelLayer2;
        }
        throw new RuntimeException(String.format("No surface for layer \"%s\".", str));
    }

    /* access modifiers changed from: protected */
    public void attachResizeLayoutListener(final AndroidPanelLayer androidPanelLayer, final AndroidPanelLayer.ResizeBehavior resizeBehavior) {
        androidPanelLayer.getContentView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            /* class com.oculus.vrshell.panels.AndroidPanelApp.AnonymousClass1 */

            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (AndroidPanelApp.this.mNativePanelApp == 0) {
                    Log.w(AndroidPanelApp.TAG, "Ignoring onLayoutChange because AndroidPanelApp is already destroyed");
                } else if (!androidPanelLayer.getVisibility() || !androidPanelLayer.hasPresentation()) {
                    Log.w(AndroidPanelApp.TAG, "Ignoring onLayoutChange because the layer is already hidden or destroyed");
                } else {
                    int widthInPixels = androidPanelLayer.getWidthInPixels();
                    int heightInPixels = androidPanelLayer.getHeightInPixels();
                    View contentView = androidPanelLayer.getContentView();
                    Size measureLayerView = AndroidPanelApp.this.measureLayerView(contentView, resizeBehavior, widthInPixels, heightInPixels);
                    int width = measureLayerView.getWidth();
                    int height = measureLayerView.getHeight();
                    if (width != widthInPixels || height != heightInPixels) {
                        String name = androidPanelLayer.getName();
                        Log.i(AndroidPanelApp.TAG, String.format("Layout change has caused layer \"%s\" to be resized from %d X %d to %d X %d", name, Integer.valueOf(widthInPixels), Integer.valueOf(heightInPixels), Integer.valueOf(width), Integer.valueOf(height)));
                        ((ViewGroup) contentView.getParent()).removeView(contentView);
                        AndroidPanelApp androidPanelApp = AndroidPanelApp.this;
                        androidPanelApp.nativeSetNextFrameAction(androidPanelApp.getNativePointer(), "layerResize", androidPanelLayer.getName());
                        androidPanelLayer.setSize(width, height);
                        androidPanelLayer.setContentView(contentView);
                        AndroidPanelApp.this.showPanelLayer(androidPanelLayer);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Size measureLayerView(View view, AndroidPanelLayer.ResizeBehavior resizeBehavior, int i, int i2) {
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            int dipToPixelsInt = DensityUtils.dipToPixelsInt(1000.0f, this.mContext.getResources().getDisplayMetrics());
            Log.i(TAG, String.format("measureLayerView adjusting initial width from %d to %d due to resize behavior.", Integer.valueOf(i), Integer.valueOf(dipToPixelsInt)));
            i = dipToPixelsInt;
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            int dipToPixelsInt2 = DensityUtils.dipToPixelsInt(1000.0f, this.mContext.getResources().getDisplayMetrics());
            Log.i(TAG, String.format("measureLayerView adjusting initial height from %d to %d due to resize behavior.", Integer.valueOf(i2), Integer.valueOf(dipToPixelsInt2)));
            i2 = dipToPixelsInt2;
        }
        Log.i(TAG, String.format("measureLayerView with initial width %d, initial height %d, resize behavior %s.", Integer.valueOf(i), Integer.valueOf(i2), resizeBehavior.name()));
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i, Ints.MAX_POWER_OF_TWO), View.MeasureSpec.makeMeasureSpec(0, 0));
            i2 = view.getMeasuredHeight();
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(i2, Ints.MAX_POWER_OF_TWO));
            i = view.getMeasuredWidth();
        }
        Log.i(TAG, String.format("Measured width %d and height %d.", Integer.valueOf(i), Integer.valueOf(i2)));
        return new Size(Math.max(i, 1), Math.max(i2, 1));
    }

    /* access modifiers changed from: protected */
    public final void destroyLayer(String str) {
        Log.i(TAG, String.format("destroyLayer(%s)", str));
        if (this.mNativePanelApp == 0) {
            Log.w(TAG, "Ignoring destroyLayer() because AndroidPanelApp is already destroyed.");
            return;
        }
        AndroidPanelLayer androidPanelLayer = this.mLayers.get(str);
        if (androidPanelLayer == null) {
            Log.i(TAG, "Ignoring destroyLayer() because layer does not exist.");
            return;
        }
        androidPanelLayer.setVisibility(false);
        androidPanelLayer.destroy();
        this.mLayers.remove(str);
        nativeHideLayer(getNativePointer(), str, androidPanelLayer.getShape().value, true);
    }

    /* access modifiers changed from: protected */
    public final AndroidPanelLayer getPanelLayer(String str) {
        return this.mLayers.get(str);
    }

    private void tryShowLayer(final AndroidPanelLayer androidPanelLayer) {
        Log.i(TAG, String.format("tryShowLayer(%s)", androidPanelLayer.getName()));
        if (androidPanelLayer == null) {
            Log.w(TAG, "Unable to show null layer.");
        } else if (androidPanelLayer.needsToBeSized()) {
            Log.i(TAG, String.format("Layer %s needs to be sized, deferring showing layer.", androidPanelLayer.getName()));
        } else {
            androidPanelLayer.show();
            this.mMainHandler.post(new Runnable() {
                /* class com.oculus.vrshell.panels.AndroidPanelApp.AnonymousClass2 */

                public void run() {
                    if (androidPanelLayer.getVisibility()) {
                        Log.i(AndroidPanelApp.TAG, String.format("Turning on layer visibility for layer %s.", androidPanelLayer.getName()));
                        if (AndroidPanelApp.this.mNativePanelApp != 0) {
                            AndroidPanelApp.this.queueRawCommand(String.format(Locale.ROOT, "layerVisibility %s 1", androidPanelLayer.getName()));
                            return;
                        }
                        Log.i(AndroidPanelApp.TAG, "Not sending layer visibility command, panel app has been destroyed.");
                        return;
                    }
                    Log.i(AndroidPanelApp.TAG, String.format("Layer is no longer visible. Not sending layer visibility for layer %s.", androidPanelLayer.getName()));
                }
            });
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showPanelLayer(AndroidPanelLayer androidPanelLayer) {
        String name = androidPanelLayer.getName();
        int widthInPixels = androidPanelLayer.getWidthInPixels();
        int heightInPixels = androidPanelLayer.getHeightInPixels();
        boolean isHitTestable = androidPanelLayer.isHitTestable();
        int i = androidPanelLayer.getShape().value;
        float positionX = androidPanelLayer.getPositionX();
        float positionY = androidPanelLayer.getPositionY();
        float rotationX = androidPanelLayer.getRotationX();
        float rotationY = androidPanelLayer.getRotationY();
        float rotationZ = androidPanelLayer.getRotationZ();
        float density = androidPanelLayer.getDensity();
        boolean isSystemPositioned = androidPanelLayer.isSystemPositioned();
        boolean isSupersampled = androidPanelLayer.isSupersampled();
        Log.i(TAG, String.format("layerTransform: Showing panel layer \"%s\" in VrShell with width %d, height %d, position( %f , %f ), rotation (%f , %f , %f )  hit-testable %b, shape %d, and system-positioned %b. Density: %f. Supersampled: %b ", name, Integer.valueOf(widthInPixels), Integer.valueOf(heightInPixels), Float.valueOf(positionX), Float.valueOf(positionY), Float.valueOf(rotationX), Float.valueOf(rotationY), Float.valueOf(rotationZ), Boolean.valueOf(isHitTestable), Integer.valueOf(i), Boolean.valueOf(isSystemPositioned), Float.valueOf(density), Boolean.valueOf(isSupersampled)));
        androidPanelLayer.onPanelSizeRequested();
        androidPanelLayer.setVisibility(true);
        nativeShowLayer(getNativePointer(), name, widthInPixels, heightInPixels, positionX, positionY, rotationX, rotationY, rotationZ, density, isSupersampled, isHitTestable, i, isSystemPositioned);
    }

    protected static void performClick(View view, float f, float f2) {
        MotionEvent obtain = MotionEvent.obtain(0, SystemClock.uptimeMillis(), 0, f, f2, 0);
        view.dispatchTouchEvent(obtain);
        obtain.setAction(1);
        view.dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    protected static void performClick(View view) {
        performClick(view, 0.0f, 0.0f);
    }

    private View getViewFromId(String str, String str2, String str3) {
        if ("id".equals(str3)) {
            return findViewByName(str, str2);
        }
        if (IPC_COMMAND_AUTOMATION_IDTYPE_TAG.equals(str3)) {
            return findViewByTag(str, str2);
        }
        if (IPC_COMMAND_AUTOMATION_IDTYPE_AUTOMATIONID.equals(str3)) {
            return findViewByAutomationId(str, str2);
        }
        Log.d(TAG, String.format("Invalid idType specified %s.", str3));
        return null;
    }

    public String automationActivateView(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        View viewFromId = getViewFromId(str, str2, str3);
        if (viewFromId != null) {
            performClick(viewFromId);
            jSONObject.put(QuickPromotionController.EVENT_VIEW, convertViewToObject(getContext().getResources(), viewFromId, false));
            jSONObject.put("result", "success");
        } else {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        return jSONObject.toString();
    }

    public String automationQueryLayer(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        AndroidPanelLayer panelLayer = getPanelLayer(str);
        if (panelLayer != null) {
            jSONObject.put("result", "success");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("layerId", str);
            jSONObject2.put(AssistantDialogContract.Dialog.LifeCycle.ACTION_DIALOG_VISIBLE, panelLayer.isVisible());
            jSONObject2.put("width", panelLayer.getWidthInPixels());
            jSONObject2.put("height", panelLayer.getHeightInPixels());
            jSONObject.put("layer", jSONObject2);
            View contentView = panelLayer.getContentView();
            if (contentView != null) {
                jSONObject2.put(QuickPromotionController.EVENT_VIEW, convertViewToObject(getContext().getResources(), contentView, true));
            } else {
                jSONObject2.put(QuickPromotionController.EVENT_VIEW, (Object) null);
            }
        } else {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        return jSONObject.toString();
    }

    public String automationQueryView(String str, String str2, String str3, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("layerId", str);
        View viewFromId = getViewFromId(str, str2, str3);
        if (viewFromId != null) {
            jSONObject.put(QuickPromotionController.EVENT_VIEW, convertViewToObject(getContext().getResources(), viewFromId, z));
            jSONObject.put("result", "success");
        } else {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        return jSONObject.toString();
    }

    /* access modifiers changed from: protected */
    public List<View> getContentViewsForLayer(String str) {
        View contentView;
        ArrayList arrayList = new ArrayList();
        AndroidPanelLayer panelLayer = getPanelLayer(str);
        if (!(panelLayer == null || (contentView = panelLayer.getContentView()) == null)) {
            arrayList.add(contentView);
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View findViewByTag(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            java.util.List r3 = r2.getContentViewsForLayer(r3)
            java.util.Iterator r3 = r3.iterator()
            r0 = 0
        L_0x0009:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x001b
            java.lang.Object r0 = r3.next()
            android.view.View r0 = (android.view.View) r0
            android.view.View r0 = r0.findViewWithTag(r4)
            if (r0 == 0) goto L_0x0009
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.findViewByTag(java.lang.String, java.lang.String):android.view.View");
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View findViewByName(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            android.content.Context r0 = r3.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.content.Context r1 = r3.getContext()
            java.lang.String r1 = r1.getPackageName()
            java.lang.String r2 = "id"
            int r5 = r0.getIdentifier(r5, r2, r1)
            r0 = 0
            if (r5 == 0) goto L_0x0033
            java.util.List r4 = r3.getContentViewsForLayer(r4)
            java.util.Iterator r4 = r4.iterator()
        L_0x0021:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0033
            java.lang.Object r0 = r4.next()
            android.view.View r0 = (android.view.View) r0
            android.view.View r0 = r0.findViewById(r5)
            if (r0 == 0) goto L_0x0021
        L_0x0033:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.findViewByName(java.lang.String, java.lang.String):android.view.View");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View findViewByAutomationId(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            int r4 = java.lang.Integer.parseInt(r4)
            java.util.List r3 = r2.getContentViewsForLayer(r3)
            java.util.Iterator r3 = r3.iterator()
            r0 = 0
        L_0x000d:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x0023
            java.lang.Object r0 = r3.next()
            android.view.View r0 = (android.view.View) r0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            android.view.View r0 = r2.findViewByAutomationIdWithParent(r0, r1)
            if (r0 == 0) goto L_0x000d
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.findViewByAutomationId(java.lang.String, java.lang.String):android.view.View");
    }

    private View findViewByAutomationIdWithParent(View view, Integer num) {
        if (num.equals(view.getTag(R.id.automation_unique_id_key))) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View findViewByAutomationIdWithParent = findViewByAutomationIdWithParent(viewGroup.getChildAt(i), num);
            if (findViewByAutomationIdWithParent != null) {
                return findViewByAutomationIdWithParent;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void formatView(Resources resources, JSONObject jSONObject, View view) throws JSONException {
        int id = view.getId();
        jSONObject.put("id", id);
        if (id != -1) {
            jSONObject.put("name", resources.getResourceEntryName(id));
        }
        if (view.getTag(R.id.automation_unique_id_key) == null) {
            view.setTag(R.id.automation_unique_id_key, Integer.valueOf(getUniqueAutomationId()));
        }
        jSONObject.put("automation_id", view.getTag(R.id.automation_unique_id_key));
        jSONObject.put(IPC_COMMAND_AUTOMATION_IDTYPE_TAG, view.getTag());
        jSONObject.put("type", view.getClass().toString());
        jSONObject.put("visibility", view.getVisibility());
        jSONObject.put("scrollOffsetX", view.getScrollX());
        jSONObject.put("scrollOffsetY", view.getScrollY());
    }

    /* access modifiers changed from: protected */
    public void formatViewGroup(Resources resources, JSONObject jSONObject, ViewGroup viewGroup, boolean z) throws JSONException {
        int childCount = viewGroup.getChildCount();
        jSONObject.put("childCount", childCount);
        if (z) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < childCount; i++) {
                jSONArray.put(convertViewToObject(resources, viewGroup.getChildAt(i), z));
            }
            jSONObject.put("children", jSONArray);
        }
    }

    /* access modifiers changed from: protected */
    public void formatTextView(Resources resources, JSONObject jSONObject, TextView textView) throws JSONException {
        jSONObject.put("text", textView.getText());
    }

    /* access modifiers changed from: protected */
    public void formatCheckbox(Resources resources, JSONObject jSONObject, CheckBox checkBox) throws JSONException {
        jSONObject.put("checked", checkBox.isChecked());
    }

    /* access modifiers changed from: protected */
    public JSONObject convertViewToObject(Resources resources, View view, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        formatView(resources, jSONObject, view);
        if (view instanceof ViewGroup) {
            formatViewGroup(resources, jSONObject, (ViewGroup) view, z);
        }
        if (view instanceof TextView) {
            formatTextView(resources, jSONObject, (TextView) view);
        }
        if (view instanceof CheckBox) {
            formatCheckbox(resources, jSONObject, (CheckBox) view);
        }
        return jSONObject;
    }

    private void simulateOnHover(AndroidPanelLayer androidPanelLayer, float f, float f2) {
        View findView = findView(androidPanelLayer.getContentView(), (int) (((float) androidPanelLayer.getWidthInPixels()) * f), (int) (((double) ((float) androidPanelLayer.getHeightInPixels())) * (1.0d - ((double) f2))));
        if (findView != null) {
            onHoverDebugTooltip(androidPanelLayer, findView);
        }
    }

    private static View findView(View view, int i, int i2) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View findView = findView(viewGroup.getChildAt(i3), i, i2);
                if (findView != null) {
                    return findView;
                }
            }
            return null;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        if (rect.contains(i, i2)) {
            return view;
        }
        return null;
    }

    private void onHoverDebugTooltip(AndroidPanelLayer androidPanelLayer, View view) {
        StringBuilder sb = new StringBuilder();
        int id = view.getId();
        if (id >= 0) {
            sb.append("Class: ");
            sb.append(view.getClass().getCanonicalName());
            sb.append("\nID: ");
            sb.append(getContext().getResources().getResourceEntryName(id));
            Object tag = view.getTag();
            if (tag != null) {
                sb.append("\nTag: ");
                sb.append(tag.toString());
            }
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            this.mTooltipManager.showTooltip(new TooltipDefinition(androidPanelLayer.getName(), sb.toString(), TooltipPosition.Bottom, TooltipColor.Black, "view debug tooltip"), TooltipUVCoordinates.getTooltipUVCoordinates(rect.left, rect.top, view.getWidth(), view.getHeight(), androidPanelLayer.getWidthInPixels(), androidPanelLayer.getHeightInPixels(), TooltipPosition.Bottom));
        }
    }

    /* access modifiers changed from: protected */
    public void configureLayerSurfaceGeometryBorderRadius(String str, AndroidPanelLayer.BorderRadiusType borderRadiusType, int i) {
        Log.i(TAG, String.format(Locale.ROOT, "Sending surface geometry border radius with type %s and value %d for layer %s.", borderRadiusType.getIPCString(), Integer.valueOf(i), str));
        getCommandChannel().sendCommand(String.format(Locale.ROOT, "layerSurfaceGeometryBorderRadius %s %s %d", str, borderRadiusType.getIPCString(), Integer.valueOf(i)));
    }
}
