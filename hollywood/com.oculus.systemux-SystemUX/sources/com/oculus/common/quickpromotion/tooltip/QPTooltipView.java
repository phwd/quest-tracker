package com.oculus.common.quickpromotion.tooltip;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.quickpromotion.QuickPromotionEventHandler;
import com.oculus.common.quickpromotion.R;
import com.oculus.common.quickpromotion.databinding.QpTooltipViewBinding;
import com.oculus.ocui.OCButton;
import com.oculus.tablet.logging.ClickEventButtonId;

public class QPTooltipView extends ConstraintLayout {
    private static final int QP_TOOLTIP_WIDTH_IN_DP = 288;
    private static final String TAG = LoggingUtil.tag(QPTooltipView.class);
    private int mAnchorUIViewWidthInDP;
    private ImageView mCaretDown;
    private ImageView mCaretLeft;
    private ImageView mCaretRight;
    private ImageView mCaretUp;
    private QPTooltipDismissButtonHandler mDismissButtonHandler;
    private QuickPromotionEventHandler mEventHandler;
    private boolean mIsCaretInBottomCorner;
    private boolean mIsCaretInTopCorner;
    private TextView mTextView;
    private OCButton mTooltipDismissButton;
    private View mTooltipMain;

    public interface QPTooltipDismissButtonHandler {
        void onDismissQPTooltip();
    }

    public enum QPTooltipPosition {
        Top,
        Bottom,
        Left,
        Right,
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
    }

    public QPTooltipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(QPTooltipDismissButtonHandler qPTooltipDismissButtonHandler, QuickPromotionEventHandler quickPromotionEventHandler, QpTooltipViewBinding qpTooltipViewBinding) {
        this.mTextView = qpTooltipViewBinding.tooltipText;
        this.mCaretLeft = qpTooltipViewBinding.tooltipCaretLeft;
        this.mCaretRight = qpTooltipViewBinding.tooltipCaretRight;
        this.mCaretUp = qpTooltipViewBinding.tooltipCaretUp;
        this.mCaretDown = qpTooltipViewBinding.tooltipCaretDown;
        this.mTooltipMain = qpTooltipViewBinding.tooltipMain;
        this.mTooltipDismissButton = qpTooltipViewBinding.dismissCtaIcon;
        this.mDismissButtonHandler = qPTooltipDismissButtonHandler;
        this.mEventHandler = quickPromotionEventHandler;
    }

    public void configureTooltip(String str, QPTooltipPosition qPTooltipPosition, int i) {
        int color = getResources().getColor(R.color.oc_gray_70);
        int i2 = 0;
        boolean z = qPTooltipPosition == QPTooltipPosition.Top;
        boolean z2 = qPTooltipPosition == QPTooltipPosition.Bottom;
        boolean z3 = qPTooltipPosition == QPTooltipPosition.Left;
        boolean z4 = qPTooltipPosition == QPTooltipPosition.Right;
        boolean z5 = qPTooltipPosition == QPTooltipPosition.BottomRight;
        boolean z6 = qPTooltipPosition == QPTooltipPosition.TopRight;
        this.mIsCaretInBottomCorner = z5;
        this.mIsCaretInTopCorner = z6;
        this.mAnchorUIViewWidthInDP = i;
        this.mCaretDown.setVisibility((z || z6) ? 0 : 8);
        this.mCaretUp.setVisibility((z2 || z5) ? 0 : 8);
        this.mCaretLeft.setVisibility(z4 ? 0 : 8);
        ImageView imageView = this.mCaretRight;
        if (!z3) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        if (z4) {
            constraintSet.connect(this.mTooltipMain.getId(), 1, this.mCaretLeft.getId(), 2);
        } else if (z3) {
            constraintSet.connect(this.mTooltipMain.getId(), 2, this.mCaretRight.getId(), 1);
        } else if (z2 || this.mIsCaretInTopCorner) {
            constraintSet.connect(this.mTooltipMain.getId(), 3, this.mCaretUp.getId(), 4);
        } else if (z || this.mIsCaretInBottomCorner) {
            constraintSet.connect(this.mTooltipMain.getId(), 4, this.mCaretUp.getId(), 3);
        }
        constraintSet.applyTo(this);
        this.mTextView.setText(str);
        this.mTooltipMain.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        this.mCaretDown.getDrawable().mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        this.mCaretUp.getDrawable().mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        this.mCaretLeft.getDrawable().mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        this.mCaretRight.getDrawable().mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        this.mTooltipDismissButton.setEventHandler(this.mEventHandler);
        this.mTooltipDismissButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.common.quickpromotion.tooltip.$$Lambda$QPTooltipView$yXd4_L1Hu7XdsHXoia2DNL5CVoY */

            public final void onClick(View view) {
                QPTooltipView.this.lambda$configureTooltip$0$QPTooltipView(view);
            }
        });
    }

    public /* synthetic */ void lambda$configureTooltip$0$QPTooltipView(View view) {
        this.mEventHandler.logButtonClick(ClickEventButtonId.AUIV2_QP_TOOLTIP_DISMISS);
        this.mDismissButtonHandler.onDismissQPTooltip();
    }

    private void showBottomCornerCaret() {
        ((ViewGroup.MarginLayoutParams) this.mCaretUp.getLayoutParams()).setMarginStart(Math.abs(288 - this.mAnchorUIViewWidthInDP));
    }

    private void showTopCornerCaret() {
        ((ViewGroup.MarginLayoutParams) this.mCaretDown.getLayoutParams()).setMarginStart(Math.abs(288 - this.mAnchorUIViewWidthInDP));
    }

    public void hideTooltip() {
        animate().alpha(0.0f).setDuration((long) getResources().getInteger(17694720)).setListener(null);
        setVisibility(8);
    }

    public void showTooltip() {
        int integer = getResources().getInteger(17694721);
        setAlpha(0.0f);
        setVisibility(0);
        animate().alpha(1.0f).setDuration((long) integer).setListener(null);
        if (this.mIsCaretInBottomCorner) {
            showBottomCornerCaret();
        } else if (this.mIsCaretInTopCorner) {
            showTopCornerCaret();
        }
    }
}
