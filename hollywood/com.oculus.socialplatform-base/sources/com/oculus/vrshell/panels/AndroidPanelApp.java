package com.oculus.vrshell.panels;

import X.AnonymousClass006;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.automation.AutomationHelpers;
import com.oculus.vrshell.panels.systemtooltip.TooltipColor;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipManager;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AndroidPanelApp implements GenericInputListener {
    public static int AUTOMATION_UNIQUE_ID_FACTORY = 1;
    public static final String IPC_COMMAND_AUTOMATION = "automation";
    public static final String IPC_COMMAND_AUTOMATION_ACTIVATEVIEW = "activateView";
    public static final String IPC_COMMAND_AUTOMATION_COMMAND = "command";
    public static final String IPC_COMMAND_AUTOMATION_COMMAND_HOVER = "hoverEnter";
    public static final String IPC_COMMAND_AUTOMATION_COMMAND_SCROLL = "scroll";
    public static final String IPC_COMMAND_AUTOMATION_IDTYPE_AUTOMATIONID = "automationId";
    public static final String IPC_COMMAND_AUTOMATION_IDTYPE_ID = "id";
    public static final String IPC_COMMAND_AUTOMATION_IDTYPE_TAG = "tag";
    public static final String IPC_COMMAND_AUTOMATION_QUERYLAYER = "queryLayer";
    public static final String IPC_COMMAND_AUTOMATION_QUERYVIEW = "queryView";
    public static final String MAIN_LAYER = "#main";
    public static final String OC_SHELL_RENDER_SCALE = "_oc_shell_render_scale";
    public static final String SCROLL_DIRECTION_HORIZONTAL = "horizontal";
    public static final String SCROLL_DIRECTION_VERTICAL = "vertical";
    public static final String TAG = "AndroidPanelApp";
    public final boolean mAutomationEnabled;
    public final Context mContext;
    public final InputFrame mCurrentInputFrame = new InputFrame();
    public boolean mDebugViewIdsEnabled;
    public final Map<String, String> mEnvironmentArgs = new HashMap();
    public final FrameCommandChannel mFrameCommandChannel = new FrameCommandChannel();
    public final KeyboardHandler mKeyboardHandler;
    public final InputFrame mLastInputFrame = new InputFrame();
    public final Map<String, AndroidPanelLayer> mLayers;
    public final Handler mMainHandler = new Handler(Looper.getMainLooper());
    public long mNativePanelApp;
    public final List<PanelFrameCallback> mPanelFrameCallbacks = new ArrayList();
    public final Map<String, Surface> mSurfaces;
    public final TooltipManager mTooltipManager;

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

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean handleAutomationMessage(Scanner scanner, String str) throws JSONException {
        String str2;
        try {
            String next = scanner.next();
            int nextInt = scanner.nextInt();
            StringBuilder sb = new StringBuilder();
            sb.append("automationResponse ");
            sb.append(nextInt);
            sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            switch (next.hashCode()) {
                case -1806818419:
                    if (next.equals(IPC_COMMAND_AUTOMATION_QUERYVIEW)) {
                        str2 = handleAutomationMessageQueryView(scanner);
                        break;
                    }
                    str2 = buildUnknownCommandMessage(next);
                    break;
                case -1194667464:
                    if (next.equals(IPC_COMMAND_AUTOMATION_ACTIVATEVIEW)) {
                        str2 = handleAutomationMessageActivateView(scanner);
                        break;
                    }
                    str2 = buildUnknownCommandMessage(next);
                    break;
                case -186250903:
                    if (next.equals(IPC_COMMAND_AUTOMATION_QUERYLAYER)) {
                        str2 = handleAutomationMessageQueryLayer(scanner);
                        break;
                    }
                    str2 = buildUnknownCommandMessage(next);
                    break;
                case 950394699:
                    if (next.equals("command")) {
                        str2 = handleAutomationMessageCommand(new JSONObject(scanner.nextLine()));
                        break;
                    }
                    str2 = buildUnknownCommandMessage(next);
                    break;
                default:
                    str2 = buildUnknownCommandMessage(next);
                    break;
            }
            if (str2 == null) {
                str2 = "{\"result\": \"invalid command parameters\"}";
            }
            sb.append(str2);
            this.mFrameCommandChannel.sendCommand(sb.toString());
            return true;
        } catch (InputMismatchException e) {
            Log.e(TAG, AnonymousClass006.A07("Received Invalid response: ", e.getMessage()));
            return true;
        }
    }

    private native void nativeHideLayer(long j, String str, int i, boolean z);

    private native void nativeQueueRawCommand(long j, String str);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeSetNextFrameAction(long j, String str, String str2);

    private native void nativeShowLayer(long j, String str, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, int i3, boolean z3);

    public abstract long createNativeInstance();

    public abstract View createViewForLayer(String str, int i, Context context);

    public final void destroyLayer(String str) {
        if (this.mNativePanelApp == 0) {
            Log.w(TAG, "Ignoring destroyLayer() because AndroidPanelApp is already destroyed.");
            return;
        }
        AndroidPanelLayer androidPanelLayer = this.mLayers.get(str);
        if (androidPanelLayer != null) {
            androidPanelLayer.mVisibility = false;
            androidPanelLayer.destroy();
            this.mLayers.remove(str);
            nativeHideLayer(getNativePointer(), str, androidPanelLayer.mSpec.shape.value, true);
        }
    }

    public String getAutomationProtocol() {
        return "automationPort 0 androidUI";
    }

    public abstract AndroidPanelLayer.Spec getLayerSpec(String str, int i);

    public String[] getLayersToAutoEnable() {
        return new String[]{"#main"};
    }

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

    public void onDeepLink(String str, String str2) {
    }

    public void onForeground() {
    }

    public void onSystemDialogResult(String str) {
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public static View findView(View view, int i, int i2) {
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

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View findViewByName(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            android.content.Context r0 = r4.mContext
            android.content.res.Resources r2 = r0.getResources()
            android.content.Context r0 = r4.mContext
            java.lang.String r1 = r0.getPackageName()
            java.lang.String r0 = "id"
            int r3 = r2.getIdentifier(r6, r0, r1)
            r2 = 0
            if (r3 == 0) goto L_0x002f
            java.util.List r0 = r4.getContentViewsForLayer(r5)
            java.util.Iterator r1 = r0.iterator()
        L_0x001d:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x002f
            java.lang.Object r0 = r1.next()
            android.view.View r0 = (android.view.View) r0
            android.view.View r2 = r0.findViewById(r3)
            if (r2 == 0) goto L_0x001d
        L_0x002f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.findViewByName(java.lang.String, java.lang.String):android.view.View");
    }

    public static int getUniqueAutomationId() {
        int i = AUTOMATION_UNIQUE_ID_FACTORY;
        AUTOMATION_UNIQUE_ID_FACTORY = i + 1;
        return i;
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
        return null;
    }

    private String handleAutomationScrollCommand(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        String string = jSONObject.getString("direction");
        int i = jSONObject.getInt("pixelAmount");
        View viewFromId = getViewFromId(jSONObject.getString("layerName"), jSONObject.getString("viewId"), jSONObject.getString("idType"));
        if (viewFromId != null) {
            if (SCROLL_DIRECTION_HORIZONTAL.equals(string)) {
                if (viewFromId.canScrollHorizontally(i)) {
                    viewFromId.scrollBy(i, 0);
                }
                jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_BOUNDS_REACHED);
                return jSONObject2.toString();
            } else if (SCROLL_DIRECTION_VERTICAL.equals(string)) {
                if (viewFromId.canScrollVertically(i)) {
                    viewFromId.scrollBy(0, i);
                }
                jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_BOUNDS_REACHED);
                return jSONObject2.toString();
            }
            jSONObject2.put("view", convertViewToObject(this.mContext.getResources(), viewFromId, false));
            jSONObject2.put("result", "success");
            return jSONObject2.toString();
        }
        jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        return jSONObject2.toString();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Size measureLayerView(View view, AndroidPanelLayer.ResizeBehavior resizeBehavior, int i, int i2) {
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            i = DensityUtils.dipToPixelsInt(1000.0f, this.mContext.getResources().getDisplayMetrics());
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            i2 = DensityUtils.dipToPixelsInt(1000.0f, this.mContext.getResources().getDisplayMetrics());
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            i2 = view.getMeasuredHeight();
        }
        if (resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH || resizeBehavior == AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
            i = view.getMeasuredWidth();
        }
        return new Size(Math.max(i, 1), Math.max(i2, 1));
    }

    private void onHoverDebugTooltip(AndroidPanelLayer androidPanelLayer, View view) {
        StringBuilder sb = new StringBuilder();
        int id = view.getId();
        if (id >= 0) {
            sb.append("Class: ");
            sb.append(view.getClass().getCanonicalName());
            sb.append("\nID: ");
            sb.append(this.mContext.getResources().getResourceEntryName(id));
            Object tag = view.getTag();
            if (tag != null) {
                sb.append("\nTag: ");
                sb.append(tag.toString());
            }
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            int i = rect.left;
            int i2 = rect.top;
            int width = view.getWidth();
            int height = view.getHeight();
            int i3 = androidPanelLayer.mWidthInPixels;
            int i4 = androidPanelLayer.mHeightInPixels;
            TooltipPosition tooltipPosition = TooltipPosition.Bottom;
            this.mTooltipManager.showTooltip(new TooltipDefinition(androidPanelLayer.mSpec.name, sb.toString(), tooltipPosition, TooltipColor.Black, "view debug tooltip"), TooltipUVCoordinates.getTooltipUVCoordinates(i, i2, width, height, i3, i4, tooltipPosition));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showPanelLayer(AndroidPanelLayer androidPanelLayer) {
        AndroidPanelLayer.Spec spec = androidPanelLayer.mSpec;
        String str = spec.name;
        int i = androidPanelLayer.mWidthInPixels;
        int i2 = androidPanelLayer.mHeightInPixels;
        boolean isHitTestable = androidPanelLayer.isHitTestable();
        int i3 = spec.shape.value;
        float f = androidPanelLayer.mPosX;
        float f2 = androidPanelLayer.mPosY;
        float f3 = androidPanelLayer.mRotX;
        float f4 = androidPanelLayer.mRotY;
        float f5 = androidPanelLayer.mRotZ;
        float f6 = androidPanelLayer.mDensity;
        boolean z = androidPanelLayer.mSystemPositioned;
        boolean z2 = androidPanelLayer.mSupersampled;
        androidPanelLayer.onPanelSizeRequested();
        androidPanelLayer.mVisibility = true;
        nativeShowLayer(getNativePointer(), str, i, i2, f, f2, f3, f4, f5, f6, z2, isHitTestable, i3, z);
    }

    private void simulateOnHover(AndroidPanelLayer androidPanelLayer, float f, float f2) {
        View findView = findView(androidPanelLayer.mContentView, (int) (((float) androidPanelLayer.mWidthInPixels) * f), (int) (((double) ((float) androidPanelLayer.mHeightInPixels)) * (1.0d - ((double) f2))));
        if (findView != null) {
            onHoverDebugTooltip(androidPanelLayer, findView);
        }
    }

    public void addPanelFrameCallback(PanelFrameCallback panelFrameCallback) {
        this.mPanelFrameCallbacks.add(panelFrameCallback);
    }

    public void attachDefaultKeyboardHandler(View view) {
        this.mKeyboardHandler.attachToView(view);
    }

    public void attachResizeLayoutListener(final AndroidPanelLayer androidPanelLayer, final AndroidPanelLayer.ResizeBehavior resizeBehavior) {
        androidPanelLayer.mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            /* class com.oculus.vrshell.panels.AndroidPanelApp.AnonymousClass1 */

            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                String str;
                String str2;
                AndroidPanelApp androidPanelApp = AndroidPanelApp.this;
                if (androidPanelApp.mNativePanelApp == 0) {
                    str = AndroidPanelApp.TAG;
                    str2 = "Ignoring onLayoutChange because AndroidPanelApp is already destroyed";
                } else {
                    AndroidPanelLayer androidPanelLayer = androidPanelLayer;
                    if (!androidPanelLayer.mVisibility || !androidPanelLayer.hasPresentation()) {
                        str = AndroidPanelApp.TAG;
                        str2 = "Ignoring onLayoutChange because the layer is already hidden or destroyed";
                    } else {
                        int i9 = androidPanelLayer.mWidthInPixels;
                        int i10 = androidPanelLayer.mHeightInPixels;
                        View view2 = androidPanelLayer.mContentView;
                        Size measureLayerView = androidPanelApp.measureLayerView(view2, resizeBehavior, i9, i10);
                        int width = measureLayerView.getWidth();
                        int height = measureLayerView.getHeight();
                        if (width != i9 || height != i10) {
                            ((ViewGroup) view2.getParent()).removeView(view2);
                            AndroidPanelApp androidPanelApp2 = AndroidPanelApp.this;
                            androidPanelApp2.nativeSetNextFrameAction(androidPanelApp2.getNativePointer(), "layerResize", androidPanelLayer.mSpec.name);
                            androidPanelLayer.setSize(width, height);
                            androidPanelLayer.setContentView(view2);
                            AndroidPanelApp.this.showPanelLayer(androidPanelLayer);
                            return;
                        }
                        return;
                    }
                }
                Log.w(str, str2);
            }
        });
    }

    public String automationActivateView(String str, String str2, String str3) throws JSONException {
        String str4;
        JSONObject jSONObject = new JSONObject();
        View viewFromId = getViewFromId(str, str2, str3);
        if (viewFromId != null) {
            performClick(viewFromId, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            jSONObject.put("view", convertViewToObject(this.mContext.getResources(), viewFromId, false));
            str4 = "success";
        } else {
            str4 = AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND;
        }
        jSONObject.put("result", str4);
        return jSONObject.toString();
    }

    public String automationQueryLayer(String str) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        AndroidPanelLayer panelLayer = getPanelLayer(str);
        if (panelLayer != null) {
            jSONObject2.put("result", "success");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("layerId", str);
            jSONObject3.put("visible", panelLayer.isVisible());
            jSONObject3.put("width", panelLayer.mWidthInPixels);
            jSONObject3.put("height", panelLayer.mHeightInPixels);
            jSONObject2.put("layer", jSONObject3);
            View view = panelLayer.mContentView;
            if (view != null) {
                jSONObject = convertViewToObject(this.mContext.getResources(), view, true);
            } else {
                jSONObject = null;
            }
            jSONObject3.put("view", jSONObject);
        } else {
            jSONObject2.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        return jSONObject2.toString();
    }

    public String automationQueryView(String str, String str2, String str3, boolean z) throws JSONException {
        String str4;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("layerId", str);
        View viewFromId = getViewFromId(str, str2, str3);
        if (viewFromId != null) {
            jSONObject.put("view", convertViewToObject(this.mContext.getResources(), viewFromId, z));
            str4 = "success";
        } else {
            str4 = AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND;
        }
        jSONObject.put("result", str4);
        return jSONObject.toString();
    }

    public void commitFrame() {
        List<String> extractFrameCommands = this.mFrameCommandChannel.extractFrameCommands();
        int size = extractFrameCommands.size();
        for (int i = 0; i < size; i++) {
            nativeQueueRawCommand(getNativePointer(), extractFrameCommands.get(i));
        }
    }

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

    public FrameCommandChannel createCommandChannel() {
        return new FrameCommandChannel();
    }

    public void destroy() {
        for (AndroidPanelLayer androidPanelLayer : this.mLayers.values()) {
            androidPanelLayer.destroy();
        }
        this.mNativePanelApp = 0;
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
        } else if (!"#main".equals(str)) {
            Log.w(TAG, AnonymousClass006.A07("Received layer input for hidden layer:  ", str));
        }
        for (AndroidPanelLayer androidPanelLayer : this.mLayers.values()) {
            if (!str.equals(androidPanelLayer.mSpec.name)) {
                androidPanelLayer.frame();
            }
        }
        if (this.mCurrentInputFrame.isButtonRelease(this.mLastInputFrame, PanelButton.getAnyBackFlagValue())) {
            onBackButton();
        }
        InputFrame inputFrame = this.mLastInputFrame;
        InputFrame inputFrame2 = this.mCurrentInputFrame;
        inputFrame.set(inputFrame2);
        if (this.mDebugViewIdsEnabled) {
            if (inputFrame2.isCursorInside()) {
                simulateOnHover(panelLayer, f, f2);
            } else {
                this.mTooltipManager.hideTooltip();
            }
        }
        this.mTooltipManager.frame(this.mFrameCommandChannel);
    }

    public FrameCommandChannel getCommandChannel() {
        return this.mFrameCommandChannel;
    }

    public List<View> getContentViewsForLayer(String str) {
        View view;
        ArrayList arrayList = new ArrayList();
        AndroidPanelLayer panelLayer = getPanelLayer(str);
        if (!(panelLayer == null || (view = panelLayer.mContentView) == null)) {
            arrayList.add(view);
        }
        return arrayList;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getEnvironmentArg(String str) {
        return this.mEnvironmentArgs.get(str);
    }

    public long getNativePointer() {
        long j = this.mNativePanelApp;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Access to native pointer after native and Java objects were destroyed.");
    }

    public final AndroidPanelLayer getPanelLayer(String str) {
        return this.mLayers.get(str);
    }

    public String handleAutomationMessageCommand(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("commandName");
        int hashCode = string.hashCode();
        if (hashCode != -907680051) {
            if (hashCode == 246067964 && string.equals(IPC_COMMAND_AUTOMATION_COMMAND_HOVER)) {
                return handleAutomationHoverCommand(jSONObject);
            }
        } else if (string.equals(IPC_COMMAND_AUTOMATION_COMMAND_SCROLL)) {
            return handleAutomationScrollCommand(jSONObject);
        }
        return buildUnknownCommandMessage(string);
    }

    public boolean handleGenericMessage(String str) {
        Scanner scanner = new Scanner(str);
        try {
            if (IPC_COMMAND_AUTOMATION.equals(scanner.next())) {
                return handleAutomationMessage(scanner, str);
            }
            return false;
        } catch (InputMismatchException e) {
            Log.e(TAG, AnonymousClass006.A07("Received Invalid response: ", e.getMessage()));
            return false;
        } catch (JSONException e2) {
            Log.e(TAG, AnonymousClass006.A07("Error constructing JSON Response: ", e2.getMessage()));
            return false;
        }
    }

    public void handleKeyboardCommand(String str, String str2) {
        this.mKeyboardHandler.handleKeyboardCommand(str, str2);
    }

    public void handleKeyboardCompose(String str, String str2) {
        this.mKeyboardHandler.handleKeyboardCompose(str, str2);
    }

    public void handleKeyboardText(String str, String str2) {
        this.mKeyboardHandler.handleKeyboardText(str, str2);
    }

    public boolean isAutomationEnabled() {
        return this.mAutomationEnabled;
    }

    public boolean isViewIdDebuggingEnabled() {
        return this.mDebugViewIdsEnabled;
    }

    public void removePanelFrameCallback(PanelFrameCallback panelFrameCallback) {
        this.mPanelFrameCallbacks.remove(panelFrameCallback);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v36, resolved type: java.util.Map<java.lang.String, java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    public AndroidPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        float f;
        for (String str : bundle.keySet()) {
            this.mEnvironmentArgs.put(str, bundle.get(str));
        }
        this.mNativePanelApp = createNativeInstance();
        this.mContext = context;
        this.mLayers = new HashMap((map.size() << 1) + 1);
        HashMap hashMap = new HashMap((map.size() << 1) + 1);
        this.mSurfaces = hashMap;
        hashMap.put("#main", surface);
        this.mSurfaces.putAll(map);
        this.mKeyboardHandler = new KeyboardHandler(this);
        this.mTooltipManager = new TooltipManager();
        this.mFrameCommandChannel.sendCommand("cursor 1.0");
        if (this.mEnvironmentArgs.containsKey(OC_SHELL_RENDER_SCALE)) {
            f = Float.parseFloat(this.mEnvironmentArgs.get(OC_SHELL_RENDER_SCALE));
        } else {
            f = 1.0f;
        }
        Resources resources = this.mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        int round = Math.round(f * 160.0f);
        displayMetrics.densityDpi = round;
        configuration.densityDpi = round;
        resources.updateConfiguration(configuration, displayMetrics);
        this.mAutomationEnabled = "true".equals(this.mEnvironmentArgs.get(AutomationHelpers.AUTOMATION_ENVIRONMENT_ARG));
        this.mDebugViewIdsEnabled = "true".equals(this.mEnvironmentArgs.get(AutomationHelpers.AUTOMATION_DEBUG_IDS_ENVIRONMENT_ARG));
        if (this.mAutomationEnabled) {
            this.mFrameCommandChannel.sendCommand("automationPort 0 androidUI");
        }
    }

    private String buildUnknownCommandMessage(String str) {
        return String.format("{\"result\": \"unknown command: %s\"}", str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View findViewByAutomationId(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            int r3 = java.lang.Integer.parseInt(r6)
            java.util.List r0 = r4.getContentViewsForLayer(r5)
            java.util.Iterator r2 = r0.iterator()
            r1 = 0
        L_0x000d:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0023
            java.lang.Object r1 = r2.next()
            android.view.View r1 = (android.view.View) r1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            android.view.View r1 = r4.findViewByAutomationIdWithParent(r1, r0)
            if (r1 == 0) goto L_0x000d
        L_0x0023:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.findViewByAutomationId(java.lang.String, java.lang.String):android.view.View");
    }

    private View findViewByAutomationIdWithParent(View view, Integer num) {
        if (num.equals(view.getTag(R.id.automation_unique_id_key))) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findViewByAutomationIdWithParent = findViewByAutomationIdWithParent(viewGroup.getChildAt(i), num);
                if (findViewByAutomationIdWithParent != null) {
                    return findViewByAutomationIdWithParent;
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View findViewByTag(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.util.List r0 = r3.getContentViewsForLayer(r4)
            java.util.Iterator r2 = r0.iterator()
            r1 = 0
        L_0x0009:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x001b
            java.lang.Object r0 = r2.next()
            android.view.View r0 = (android.view.View) r0
            android.view.View r1 = r0.findViewWithTag(r5)
            if (r1 == 0) goto L_0x0009
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.AndroidPanelApp.findViewByTag(java.lang.String, java.lang.String):android.view.View");
    }

    private String handleAutomationHoverCommand(JSONObject jSONObject) throws JSONException {
        String str;
        jSONObject.toString();
        JSONObject jSONObject2 = new JSONObject();
        View viewFromId = getViewFromId(jSONObject.getString("layerName"), jSONObject.getString("viewId"), jSONObject.getString("idType"));
        if (viewFromId != null) {
            performHover(viewFromId);
            jSONObject2.put("view", convertViewToObject(this.mContext.getResources(), viewFromId, false));
            str = "success";
        } else {
            str = AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND;
        }
        jSONObject2.put("result", str);
        return jSONObject2.toString();
    }

    private String handleAutomationMessageActivateView(Scanner scanner) throws JSONException {
        try {
            return automationActivateView(scanner.next(), scanner.next(), scanner.next());
        } catch (InputMismatchException e) {
            Log.e(TAG, AnonymousClass006.A07("Received Invalid response: ", e.getMessage()));
            return null;
        }
    }

    private String handleAutomationMessageQueryLayer(Scanner scanner) throws JSONException {
        try {
            return automationQueryLayer(scanner.next());
        } catch (InputMismatchException e) {
            Log.e(TAG, AnonymousClass006.A07("Received Invalid response: ", e.getMessage()));
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
            Log.e(TAG, AnonymousClass006.A07("Received Invalid response: ", e.getMessage()));
            return null;
        }
    }

    public static void performHover(View view) {
        MotionEvent obtain = MotionEvent.obtain(0, SystemClock.uptimeMillis(), 9, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 0);
        obtain.setSource(2);
        view.dispatchGenericMotionEvent(obtain);
        obtain.recycle();
    }

    private void tryShowLayer(final AndroidPanelLayer androidPanelLayer) {
        if (!androidPanelLayer.needsToBeSized()) {
            androidPanelLayer.show();
            this.mMainHandler.post(new Runnable() {
                /* class com.oculus.vrshell.panels.AndroidPanelApp.AnonymousClass2 */

                public void run() {
                    AndroidPanelLayer androidPanelLayer = androidPanelLayer;
                    if (androidPanelLayer.mVisibility) {
                        String str = androidPanelLayer.mSpec.name;
                        AndroidPanelApp androidPanelApp = AndroidPanelApp.this;
                        if (androidPanelApp.mNativePanelApp != 0) {
                            androidPanelApp.queueRawCommand(String.format(Locale.ROOT, "layerVisibility %s 1", str));
                        }
                    }
                }
            });
        }
    }

    public void configureLayerSurfaceGeometryBorderRadius(String str, AndroidPanelLayer.BorderRadiusType borderRadiusType, int i) {
        this.mFrameCommandChannel.sendCommand(String.format(Locale.ROOT, "layerSurfaceGeometryBorderRadius %s %s %d", str, borderRadiusType.getIPCString(), Integer.valueOf(i)));
    }

    public void formatCheckbox(Resources resources, JSONObject jSONObject, CheckBox checkBox) throws JSONException {
        jSONObject.put("checked", checkBox.isChecked());
    }

    public void formatTextView(Resources resources, JSONObject jSONObject, TextView textView) throws JSONException {
        jSONObject.put("text", textView.getText());
        TextUtils.TruncateAt ellipsize = textView.getEllipsize();
        if (ellipsize != null) {
            jSONObject.put("ellipsize", ellipsize.toString());
        }
    }

    public void formatView(Resources resources, JSONObject jSONObject, View view) throws JSONException {
        int id = view.getId();
        jSONObject.put("id", id);
        if (id != -1) {
            jSONObject.put("name", resources.getResourceEntryName(id));
        }
        if (view.getTag(R.id.automation_unique_id_key) == null) {
            int i = AUTOMATION_UNIQUE_ID_FACTORY;
            AUTOMATION_UNIQUE_ID_FACTORY = i + 1;
            view.setTag(R.id.automation_unique_id_key, Integer.valueOf(i));
        }
        jSONObject.put("automation_id", view.getTag(R.id.automation_unique_id_key));
        jSONObject.put(IPC_COMMAND_AUTOMATION_IDTYPE_TAG, view.getTag());
        jSONObject.put("type", view.getClass().toString());
        jSONObject.put("visibility", view.getVisibility());
        jSONObject.put("scrollOffsetX", view.getScrollX());
        jSONObject.put("scrollOffsetY", view.getScrollY());
        if (view.isActivated()) {
            jSONObject.put("activated_state", true);
        }
    }

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

    public final void inflateDefaultLayers() {
        String[] layersToAutoEnable = getLayersToAutoEnable();
        if (0 < layersToAutoEnable.length) {
            ensurePanelLayer(layersToAutoEnable[0], -1, (ViewConfigurator) null);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public void nextFrameAction(String str, String str2) {
        int hashCode = str.hashCode();
        if (hashCode != -1867031195) {
            if (hashCode == -1664478226 && str.equals("layerShow")) {
                AndroidPanelLayer androidPanelLayer = this.mLayers.get(str2);
                if (androidPanelLayer != null) {
                    androidPanelLayer.onPanelSizedResponse();
                    tryShowLayer(androidPanelLayer);
                    return;
                }
                Log.e(TAG, AnonymousClass006.A07("Unexpected layerShow for missing layer:  ", str2));
                return;
            }
        } else if (str.equals("layerResize")) {
            return;
        }
        Log.w(TAG, AnonymousClass006.A07("Unknown next frame action:  ", str));
    }

    public final void queueRawCommand(String str) {
        nativeQueueRawCommand(getNativePointer(), str);
    }

    private AndroidPanelLayer ensurePanelLayer(String str, AndroidPanelLayer.Spec spec) {
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

    public static void performClick(View view) {
        performClick(view, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
    }

    public static void performClick(View view, float f, float f2) {
        MotionEvent obtain = MotionEvent.obtain(0, SystemClock.uptimeMillis(), 0, f, f2, 0);
        view.dispatchTouchEvent(obtain);
        obtain.setAction(1);
        view.dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    public AndroidPanelLayer ensurePanelLayer(String str, int i, ViewConfigurator viewConfigurator) {
        getLayerSpec(str, i);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public AndroidPanelLayer ensurePanelLayer(String str, AndroidPanelLayer.Spec spec, ViewCreator viewCreator) {
        viewCreator.name();
        AndroidPanelLayer ensurePanelLayer = ensurePanelLayer(str, spec);
        View createView = viewCreator.createView(ensurePanelLayer.mContextWrapper);
        Size measureLayerView = measureLayerView(createView, ensurePanelLayer.mSpec.resizeBehavior, ensurePanelLayer.mWidthInPixels, ensurePanelLayer.mHeightInPixels);
        ensurePanelLayer.setSize(measureLayerView.getWidth(), measureLayerView.getHeight());
        ensurePanelLayer.setContentView(createView);
        showPanelLayer(ensurePanelLayer);
        return ensurePanelLayer;
    }
}
