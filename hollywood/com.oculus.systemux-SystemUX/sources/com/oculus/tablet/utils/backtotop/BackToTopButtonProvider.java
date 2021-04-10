package com.oculus.tablet.utils.backtotop;

import android.widget.LinearLayout;
import com.oculus.ocui.OCButton;

public class BackToTopButtonProvider {
    private OCButton backToTopButton;
    private LinearLayout backToTopButtonHeightLayout;

    public BackToTopButtonProvider(LinearLayout linearLayout, OCButton oCButton) {
        this.backToTopButtonHeightLayout = linearLayout;
        this.backToTopButton = oCButton;
    }

    public void setLayoutHeight(int i) {
        this.backToTopButtonHeightLayout.getLayoutParams().height = i;
    }

    public int getLayoutHeight() {
        return this.backToTopButtonHeightLayout.getLayoutParams().height;
    }

    public void requestLayout() {
        this.backToTopButtonHeightLayout.requestLayout();
    }

    public OCButton getBackToTopButton() {
        return this.backToTopButton;
    }
}
