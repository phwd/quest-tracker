package com.oculus.ocui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocui.databinding.OctooltipBinding;
import com.oculus.socialplatform.R;

public class OCTooltip extends PopupWindow {
    public static final String TAG = LoggingUtil.tag(OCTooltip.class);
    public final int TOOLTIP_CONTAINER_MIN_PADDING = 4;
    public View mAnchorView;
    public final OctooltipBinding mBinding;
    public ImageView mCaretDown;
    public ImageView mCaretLeft;
    public ImageView mCaretRight;
    public ImageView mCaretUp;
    public final Context mContext;
    public View mTooltipBody;
    public View mTooltipContainer;
    public OCButton mTooltipDismissButton;
    public OCTooltipDismissHandler mTooltipDismissHandler;
    public ImageView mTooltipIcon;
    public OCTooltipPosition mTooltipPosition;
    public TextView mTooltipText;

    public interface OCTooltipDismissHandler {
        void onDismissTooltip();
    }

    public enum OCTooltipPosition {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    private void positionTooltip() {
        int max;
        int height;
        int width;
        ImageView imageView;
        int i;
        ImageView imageView2;
        int[] iArr = new int[2];
        this.mAnchorView.getLocationInWindow(iArr);
        this.mAnchorView.measure(0, 0);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius);
        int calculateTooltipHeight = calculateTooltipHeight();
        int calculateTooltipWidth = calculateTooltipWidth();
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R.dimen.abc_button_padding_horizontal_material);
        switch (this.mTooltipPosition.ordinal()) {
            case 1:
                max = Math.max(4, iArr[0] + ((this.mAnchorView.getWidth() - calculateTooltipWidth) / 2));
                height = iArr[1] + this.mAnchorView.getHeight() + dimensionPixelSize;
                width = ((iArr[0] + (this.mAnchorView.getWidth() / 2)) - max) - dimensionPixelOffset;
                imageView = this.mCaretUp;
                ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).setMarginStart(width);
                break;
            case 2:
                max = ((iArr[0] - dimensionPixelSize) - dimensionPixelOffset) - calculateTooltipWidth;
                height = Math.max(4, iArr[1] + ((this.mAnchorView.getHeight() - calculateTooltipHeight) / 2));
                i = ((iArr[1] + (this.mAnchorView.getHeight() / 2)) - height) - dimensionPixelOffset;
                imageView2 = this.mCaretRight;
                ((ViewGroup.MarginLayoutParams) imageView2.getLayoutParams()).setMargins(0, i, 0, 0);
                break;
            case 3:
                max = iArr[0] + this.mAnchorView.getWidth() + dimensionPixelSize;
                height = Math.max(4, iArr[1] + ((this.mAnchorView.getHeight() - calculateTooltipHeight) / 2));
                i = ((iArr[1] + (this.mAnchorView.getHeight() / 2)) - height) - dimensionPixelOffset;
                imageView2 = this.mCaretLeft;
                ((ViewGroup.MarginLayoutParams) imageView2.getLayoutParams()).setMargins(0, i, 0, 0);
                break;
            default:
                max = Math.max(4, iArr[0] + ((this.mAnchorView.getWidth() - calculateTooltipWidth) / 2));
                height = ((iArr[1] - dimensionPixelSize) - dimensionPixelOffset) - calculateTooltipHeight;
                width = ((iArr[0] + (this.mAnchorView.getWidth() / 2)) - max) - dimensionPixelOffset;
                imageView = this.mCaretDown;
                ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).setMarginStart(width);
                break;
        }
        this.mTooltipContainer.setPadding(max, height, 0, 0);
    }

    /* renamed from: com.oculus.ocui.OCTooltip$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$ocui$OCTooltip$OCTooltipPosition;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.ocui.OCTooltip$OCTooltipPosition[] r0 = com.oculus.ocui.OCTooltip.OCTooltipPosition.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.ocui.OCTooltip.AnonymousClass1.$SwitchMap$com$oculus$ocui$OCTooltip$OCTooltipPosition = r2
                com.oculus.ocui.OCTooltip$OCTooltipPosition r0 = com.oculus.ocui.OCTooltip.OCTooltipPosition.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.ocui.OCTooltip$OCTooltipPosition r0 = com.oculus.ocui.OCTooltip.OCTooltipPosition.LEFT     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.ocui.OCTooltip$OCTooltipPosition r0 = com.oculus.ocui.OCTooltip.OCTooltipPosition.RIGHT     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.ocui.OCTooltip$OCTooltipPosition r0 = com.oculus.ocui.OCTooltip.OCTooltipPosition.TOP     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocui.OCTooltip.AnonymousClass1.<clinit>():void");
        }
    }

    private int calculateTooltipHeight() {
        this.mTooltipText.measure(0, 0);
        return Math.min((this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material) << 1) + this.mTooltipText.getMeasuredHeight(), this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_list_item_height_large_material));
    }

    private int calculateTooltipWidth() {
        this.mTooltipText.measure(0, 0);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius);
        int min = (dimensionPixelSize << 1) + Math.min(this.mTooltipText.getMeasuredWidth(), this.mContext.getResources().getDimensionPixelSize(R.dimen.octooltip_text_max_width));
        if (this.mTooltipIcon.getVisibility() == 0) {
            min += this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter) + dimensionPixelSize;
        }
        if (this.mTooltipDismissButton.getVisibility() == 0) {
            min += this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material) + dimensionPixelSize;
        }
        return Math.min(min, this.mContext.getResources().getDimensionPixelSize(R.dimen.octooltip_max_width));
    }

    private void initialize() {
        setContentView(this.mBinding.mRoot);
        setAnimationStyle(0);
        setHeight(-1);
        setWidth(-1);
        setBackgroundDrawable(new ColorDrawable(0));
        setOverlapAnchor(true);
        getContentView().setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCTooltip$ar_wMYjm6LW6F4fPOmvODxULb4c2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCTooltip.this.lambda$initialize$0$OCTooltip(view, motionEvent);
            }
        });
        getContentView().setOnTouchListener(new View.OnTouchListener() {
            /* class com.oculus.ocui.$$Lambda$OCTooltip$bSsR7gsnYwFz8usoCznRadjsAaA2 */

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return OCTooltip.this.lambda$initialize$1$OCTooltip(view, motionEvent);
            }
        });
    }

    private void setCaretVisibility() {
        ImageView imageView = this.mCaretDown;
        int i = 0;
        int i2 = 8;
        if (this.mTooltipPosition == OCTooltipPosition.TOP) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
        ImageView imageView2 = this.mCaretUp;
        int i3 = 8;
        if (this.mTooltipPosition == OCTooltipPosition.BOTTOM) {
            i3 = 0;
        }
        imageView2.setVisibility(i3);
        ImageView imageView3 = this.mCaretRight;
        int i4 = 8;
        if (this.mTooltipPosition == OCTooltipPosition.LEFT) {
            i4 = 0;
        }
        imageView3.setVisibility(i4);
        ImageView imageView4 = this.mCaretLeft;
        if (this.mTooltipPosition != OCTooltipPosition.RIGHT) {
            i = 8;
        }
        imageView4.setVisibility(i);
    }

    public void configureTooltip(String str, OCTooltipPosition oCTooltipPosition, OCTooltipDismissHandler oCTooltipDismissHandler, OCEventHandler oCEventHandler, View view) {
        this.mTooltipDismissHandler = oCTooltipDismissHandler;
        this.mTooltipText.setText(str);
        OCButton oCButton = this.mTooltipDismissButton;
        oCButton.mEventHandler = oCEventHandler;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCTooltip$XdqPFEfHwaZt1Wl4q4sf5aiTSVs2 */

            public final void onClick(View view) {
                OCTooltip.this.lambda$configureTooltip$2$OCTooltip(view);
            }
        });
        this.mAnchorView = view;
        this.mTooltipPosition = oCTooltipPosition;
        setCaretVisibility();
    }

    public /* synthetic */ void lambda$configureTooltip$2$OCTooltip(View view) {
        this.mTooltipDismissHandler.onDismissTooltip();
        dismiss();
    }

    public void setDismissButtonVisibility(boolean z) {
        OCButton oCButton = this.mTooltipDismissButton;
        int i = 8;
        if (z) {
            i = 0;
        }
        oCButton.setVisibility(i);
    }

    public void setIconVisibility(boolean z) {
        ImageView imageView = this.mTooltipIcon;
        int i = 8;
        if (z) {
            i = 0;
        }
        imageView.setVisibility(i);
    }

    public OCTooltip(Context context) {
        super(context);
        this.mContext = context;
        OctooltipBinding inflate = OctooltipBinding.inflate(LayoutInflater.from(context), null, false);
        this.mBinding = inflate;
        this.mTooltipContainer = inflate.tooltipContainer;
        this.mTooltipBody = inflate.tooltipBody;
        this.mTooltipIcon = inflate.tooltipIcon;
        this.mTooltipText = inflate.tooltipText;
        this.mTooltipDismissButton = inflate.dismissCtaIcon;
        this.mCaretLeft = inflate.tooltipCaretLeft;
        this.mCaretRight = inflate.tooltipCaretRight;
        this.mCaretUp = inflate.tooltipCaretUp;
        this.mCaretDown = inflate.tooltipCaretDown;
        initialize();
    }

    private boolean isMotionEventInsideTooltip(MotionEvent motionEvent) {
        int i;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int[] iArr = new int[2];
        this.mTooltipBody.getLocationInWindow(iArr);
        int i2 = iArr[0];
        if (x <= i2 || x > i2 + this.mTooltipBody.getWidth() || y <= (i = iArr[1]) || y > i + this.mTooltipBody.getHeight()) {
            return false;
        }
        return true;
    }

    private boolean isMotionEventOutsideTooltip(MotionEvent motionEvent) {
        int i;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int[] iArr = new int[2];
        this.mTooltipBody.getLocationInWindow(iArr);
        int i2 = iArr[0];
        if (x < i2 || x > i2 + this.mTooltipBody.getWidth() || y < (i = iArr[1]) || y > i + this.mTooltipBody.getHeight()) {
            return true;
        }
        return false;
    }

    public /* synthetic */ boolean lambda$initialize$0$OCTooltip(View view, MotionEvent motionEvent) {
        if (isMotionEventInsideTooltip(motionEvent)) {
            return true;
        }
        return false;
    }

    public /* synthetic */ boolean lambda$initialize$1$OCTooltip(View view, MotionEvent motionEvent) {
        if (!isMotionEventOutsideTooltip(motionEvent)) {
            return false;
        }
        this.mTooltipDismissHandler.onDismissTooltip();
        dismiss();
        return false;
    }

    public void show() {
        positionTooltip();
        showAsDropDown(this.mAnchorView);
    }
}
