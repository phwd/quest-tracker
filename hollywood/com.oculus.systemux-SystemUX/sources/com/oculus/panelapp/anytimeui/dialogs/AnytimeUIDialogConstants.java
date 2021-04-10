package com.oculus.panelapp.anytimeui.dialogs;

import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.HashMap;
import java.util.Map;

public final class AnytimeUIDialogConstants {
    private static final int CONTINUING_EDUCATION_PROMPT_HEIGHT = 100;
    private static final int CONTINUING_EDUCATION_PROMPT_WIDTH = 420;
    private final Map<SystemUXRoute, DialogData> UriToDialogData = new HashMap();

    public AnytimeUIDialogConstants() {
        this.UriToDialogData.put(SystemUXRoute.IPD_ADJUST, new DialogData(R.layout.ipd_adjust_dialog, 640, 640, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.NOT_HIT_TESTABLE));
        this.UriToDialogData.put(SystemUXRoute.QUEST_VIEW_RECENTER, new DialogData(R.layout.quest_view_recenter_dialog, CONTINUING_EDUCATION_PROMPT_WIDTH, 100, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat));
        this.UriToDialogData.put(SystemUXRoute.QUEST_SHOW_TASKBAR, new DialogData(R.layout.quest_show_taskbar_dialog, CONTINUING_EDUCATION_PROMPT_WIDTH, 100, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat));
        this.UriToDialogData.put(SystemUXRoute.SOCIAL_RECEIVE_INVITE_DIALOG, new DialogData(R.layout.social_receive_invite_dialog, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 580, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE));
    }

    public int GetViewIdForRoute(SystemUXRoute systemUXRoute) {
        DialogData dialogData = this.UriToDialogData.get(systemUXRoute);
        if (dialogData != null) {
            return dialogData.VIEW_ID;
        }
        throw new IllegalArgumentException("Unknown dialog: " + systemUXRoute);
    }

    public int GetWidthForViewId(int i) {
        return GetDialogDataForViewId(i).WIDTH;
    }

    public int GetHeightForViewId(int i) {
        return GetDialogDataForViewId(i).HEIGHT;
    }

    public AndroidPanelLayer.ResizeBehavior GetResizeBehaviorForViewId(int i) {
        return GetDialogDataForViewId(i).RESIZE_BEHAVIOR;
    }

    public AndroidPanelLayer.HitTestingBehavior GetHitTestingBehaviorForViewId(int i) {
        return GetDialogDataForViewId(i).HIT_TESTING_BEHAVIOR;
    }

    public AndroidPanelLayer.Shape GetShapeForViewId(int i) {
        return GetDialogDataForViewId(i).SHAPE;
    }

    /* access modifiers changed from: private */
    public static class DialogData {
        public final int HEIGHT;
        public final AndroidPanelLayer.HitTestingBehavior HIT_TESTING_BEHAVIOR;
        public final AndroidPanelLayer.ResizeBehavior RESIZE_BEHAVIOR;
        public final AndroidPanelLayer.Shape SHAPE;
        public final int VIEW_ID;
        public final int WIDTH;

        public DialogData(int i, int i2, int i3, AndroidPanelLayer.ResizeBehavior resizeBehavior, AndroidPanelLayer.HitTestingBehavior hitTestingBehavior, AndroidPanelLayer.Shape shape) {
            this.VIEW_ID = i;
            this.WIDTH = i2;
            this.HEIGHT = i3;
            this.RESIZE_BEHAVIOR = resizeBehavior;
            this.HIT_TESTING_BEHAVIOR = hitTestingBehavior;
            this.SHAPE = shape;
        }

        public DialogData(int i, int i2, int i3, AndroidPanelLayer.ResizeBehavior resizeBehavior, AndroidPanelLayer.HitTestingBehavior hitTestingBehavior) {
            this(i, i2, i3, resizeBehavior, hitTestingBehavior, AndroidPanelLayer.Shape.LandscapeCylinder);
        }
    }

    private DialogData GetDialogDataForViewId(int i) {
        for (SystemUXRoute systemUXRoute : this.UriToDialogData.keySet()) {
            DialogData dialogData = this.UriToDialogData.get(systemUXRoute);
            if (dialogData.VIEW_ID == i) {
                return dialogData;
            }
        }
        throw new IllegalArgumentException("Unknown dialog viewIdentifier requested: " + i);
    }
}
