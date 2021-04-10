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
    public List<SocialUserAction> mActions;
    public Context mContext;
    public OCDropdown<SocialUserAction> mOCDropdown;
    public SocialPanelApp mPanelApp;
    public SocialUserAction.Source mSource;

    public void dismissPopup() {
        this.mOCDropdown.dismiss();
    }

    public /* synthetic */ Object lambda$new$0$SocialPopupMenu(Object obj) {
        ((SocialUserAction) obj).execute(this.mPanelApp, this.mContext, null, this.mSource);
        return null;
    }

    public SocialPopupMenu(Context context, SocialPanelApp socialPanelApp, List<SocialUserAction> list, View view, SocialUserAction.Source source) {
        this.mActions = list;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
        this.mSource = source;
        HashMap hashMap = new HashMap();
        for (SocialUserAction socialUserAction : list) {
            hashMap.put(socialUserAction, Integer.valueOf(socialUserAction.getType().getActionStringID()));
        }
        OCDropdown<SocialUserAction> oCDropdown = new OCDropdown<>(this.mContext);
        this.mOCDropdown = oCDropdown;
        oCDropdown.setItems(list);
        this.mOCDropdown.setTitleMap(hashMap);
        OCDropdown<SocialUserAction> oCDropdown2 = this.mOCDropdown;
        oCDropdown2.setOnItemClick(new Function() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPopupMenu$hPYnV8r8dgw1UfnOgreZvMrQb502 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialPopupMenu.this.lambda$new$0$SocialPopupMenu(obj);
            }
        });
        oCDropdown2.setEventHandler(this.mPanelApp);
        oCDropdown2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.panelapp.social.SocialPopupMenu.AnonymousClass1 */

            public void onDismiss() {
                SocialPopupMenu.this.mPanelApp.acquireSocialViewModel().setOpenMenu(null);
            }
        });
        this.mOCDropdown.toggle(view);
    }
}
