package com.oculus.tablet.utils.backtotop;

import android.widget.LinearLayout;
import com.oculus.ocui.OCButton;

public class BackToTopButtonProvider {
    public OCButton backToTopButton;
    public LinearLayout backToTopButtonHeightLayout;

    public OCButton getBackToTopButton() {
        return this.backToTopButton;
    }

    public int getLayoutHeight() {
        return this.backToTopButtonHeightLayout.getLayoutParams().height;
    }

    public void requestLayout() {
        this.backToTopButtonHeightLayout.requestLayout();
    }

    public void setLayoutHeight(int i) {
        this.backToTopButtonHeightLayout.getLayoutParams().height = i;
    }

    public BackToTopButtonProvider(LinearLayout linearLayout, OCButton oCButton) {
        this.backToTopButtonHeightLayout = linearLayout;
        this.backToTopButton = oCButton;
    }
}
