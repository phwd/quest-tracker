package com.oculus.panelapp.socialandroidbackpanel.views;

public interface SocialAndroidBackPanelView {
    void destroy();

    boolean onControllerBackButton();

    void onShow(String str);
}
