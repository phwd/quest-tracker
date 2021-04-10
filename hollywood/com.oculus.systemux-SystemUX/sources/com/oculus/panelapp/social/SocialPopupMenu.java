package com.oculus.panelapp.social;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.ocui.OCDropdown;
import com.oculus.panelapp.social.actions.SocialUserAction;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class SocialPopupMenu {
    private List<SocialUserAction> mActions;
    private Context mContext;
    private OCDropdown<SocialUserAction> mOCDropdown;
    private SocialPanelApp mPanelApp;
    private SocialUserAction.Source mSource;

    public SocialPopupMenu(Context context, SocialPanelApp socialPanelApp, List<SocialUserAction> list, View view, SocialUserAction.Source source) {
        this.mActions = list;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
        this.mSource = source;
        HashMap hashMap = new HashMap();
        for (SocialUserAction socialUserAction : list) {
            hashMap.put(socialUserAction, Integer.valueOf(socialUserAction.getType().getActionStringID()));
        }
        this.mOCDropdown = new OCDropdown<>(this.mContext);
        this.mOCDropdown.setItems(list);
        this.mOCDropdown.setTitleMap(hashMap);
        this.mOCDropdown.setOnItemClick(new Function() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPopupMenu$HRCwyx2scSg1Wdtk1rWgCD4pYJ0 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialPopupMenu.this.lambda$new$6$SocialPopupMenu(obj);
            }
        });
        this.mOCDropdown.setEventHandler(this.mPanelApp);
        this.mOCDropdown.setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.panelapp.social.SocialPopupMenu.AnonymousClass1 */

            public void onDismiss() {
                SocialPopupMenu.this.mPanelApp.acquireSocialViewModel().setOpenMenu(null);
            }
        });
        this.mOCDropdown.toggle(view);
    }

    public /* synthetic */ Object lambda$new$6$SocialPopupMenu(Object obj) {
        ((SocialUserAction) obj).execute(this.mPanelApp, this.mContext, null, this.mSource);
        return null;
    }

    public void dismissPopup() {
        this.mOCDropdown.dismiss();
    }
}
