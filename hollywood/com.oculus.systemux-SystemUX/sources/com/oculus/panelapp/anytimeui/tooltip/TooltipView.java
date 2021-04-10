package com.oculus.panelapp.anytimeui.tooltip;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.vrshell.panels.systemtooltip.TooltipColor;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import org.json.JSONException;
import org.json.JSONObject;

public class TooltipView extends LinearLayout {
    private static final String TAG = LoggingUtil.tag(TooltipView.class);
    private ImageView mCaretDown;
    private ImageView mCaretLeft;
    private ImageView mCaretRight;
    private ImageView mCaretUp;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private TextView mSubtextView;
    private TextView mTextView;
    private View mTooltipBody;

    public TooltipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "TooltipView created");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.i(TAG, "Initializing TooltipView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mTextView = (TextView) findViewById(R.id.tooltip_text);
        this.mSubtextView = (TextView) findViewById(R.id.tooltip_subtext);
        this.mCaretLeft = (ImageView) findViewById(R.id.tooltip_caret_left);
        this.mCaretRight = (ImageView) findViewById(R.id.tooltip_caret_right);
        this.mCaretUp = (ImageView) findViewById(R.id.tooltip_caret_up);
        this.mCaretDown = (ImageView) findViewById(R.id.tooltip_caret_down);
        this.mTooltipBody = findViewById(R.id.tooltip_body);
    }

    public void configureTooltip(String str) {
        int i;
        String str2 = TAG;
        Log.i(str2, "Configuring TooltipView - " + str);
        String str3 = "";
        TooltipPosition tooltipPosition = TooltipPosition.Top;
        TooltipColor tooltipColor = TooltipColor.Black;
        String str4 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str3 = jSONObject.getString("text");
            tooltipPosition = TooltipPosition.getTooltipPosition(jSONObject.getString(TooltipDefinition.TOOLTIP_POSITION_KEY));
            String optString = jSONObject.optString(TooltipDefinition.TOOLTIP_COLOR_KEY, null);
            if (optString != null) {
                tooltipColor = TooltipColor.getTooltipColor(optString);
            }
            str4 = jSONObject.optString(TooltipDefinition.TOOLTIP_SUBTEXT_KEY, null);
        } catch (JSONException unused) {
            String str5 = TAG;
            Log.w(str5, "Unable to parse tooltip configuration json - " + str);
        }
        boolean z = tooltipPosition == TooltipPosition.Top;
        boolean z2 = tooltipPosition == TooltipPosition.Bottom;
        boolean z3 = tooltipPosition == TooltipPosition.Left;
        boolean z4 = tooltipPosition == TooltipPosition.Right;
        this.mCaretDown.setVisibility(z ? 0 : 8);
        this.mCaretUp.setVisibility(z2 ? 0 : 8);
        this.mCaretLeft.setVisibility(z4 ? 0 : 8);
        this.mCaretRight.setVisibility(z3 ? 0 : 8);
        if (z3 || z4) {
            setOrientation(0);
        } else if (z || z2) {
            setOrientation(1);
        }
        if (TextUtils.isEmpty(str4)) {
            this.mSubtextView.setVisibility(8);
        } else {
            this.mSubtextView.setText(str4);
            TextView textView = this.mSubtextView;
            textView.setWidth(getTooltipTextViewWidth(str4, textView));
        }
        this.mTextView.setText(str3);
        TextView textView2 = this.mTextView;
        textView2.setWidth(getTooltipTextViewWidth(str3, textView2));
        if (AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipColor[tooltipColor.ordinal()] != 1) {
            i = getResources().getColor(R.color.oc_black);
        } else {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(R.attr.ocTooltipBackground, typedValue, true);
            i = typedValue.data;
        }
        this.mTooltipBody.getBackground().mutate().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        this.mCaretDown.getDrawable().mutate().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        this.mCaretUp.getDrawable().mutate().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        this.mCaretLeft.getDrawable().mutate().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        this.mCaretRight.getDrawable().mutate().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.tooltip.TooltipView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipColor = new int[TooltipColor.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.vrshell.panels.systemtooltip.TooltipColor[] r0 = com.oculus.vrshell.panels.systemtooltip.TooltipColor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.tooltip.TooltipView.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipColor = r0
                int[] r0 = com.oculus.panelapp.anytimeui.tooltip.TooltipView.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipColor     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrshell.panels.systemtooltip.TooltipColor r1 = com.oculus.vrshell.panels.systemtooltip.TooltipColor.Gray     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.tooltip.TooltipView.AnonymousClass1.$SwitchMap$com$oculus$vrshell$panels$systemtooltip$TooltipColor     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrshell.panels.systemtooltip.TooltipColor r1 = com.oculus.vrshell.panels.systemtooltip.TooltipColor.Black     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.tooltip.TooltipView.AnonymousClass1.<clinit>():void");
        }
    }

    private int getTooltipTextViewWidth(String str, TextView textView) {
        StaticLayout tooltipTextStaticLayout = getTooltipTextStaticLayout(str, textView);
        int lineCount = tooltipTextStaticLayout.getLineCount();
        float f = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            float lineWidth = tooltipTextStaticLayout.getLineWidth(i);
            if (lineWidth > f) {
                f = lineWidth;
            }
        }
        return (int) Math.ceil((double) f);
    }

    private StaticLayout getTooltipTextStaticLayout(String str, TextView textView) {
        return StaticLayout.Builder.obtain(str, 0, str.length(), textView.getPaint(), getResources().getDimensionPixelSize(R.dimen.tooltip_text_max_width)).setBreakStrategy(2).build();
    }
}
