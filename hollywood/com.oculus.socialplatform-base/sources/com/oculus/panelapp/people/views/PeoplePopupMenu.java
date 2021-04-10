package com.oculus.panelapp.people.views;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.ocui.OCDropdown;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class PeoplePopupMenu {
    public Context mContext;
    public OCDropdown<PeopleUserAction> mOCDropdown;
    public PeopleTabletPanelApp mPanelApp;

    public void dismissPopup() {
        this.mOCDropdown.dismiss();
    }

    public /* synthetic */ Object lambda$new$0$PeoplePopupMenu(Object obj) {
        this.mPanelApp.executeAction((PeopleUserAction) obj, this.mContext, null);
        return null;
    }

    public void togglePopup(View view) {
        this.mOCDropdown.toggle(view);
    }

    public PeoplePopupMenu(Context context, PeopleTabletPanelApp peopleTabletPanelApp, List<PeopleUserAction> list) {
        this.mContext = context;
        this.mPanelApp = peopleTabletPanelApp;
        HashMap hashMap = new HashMap();
        for (PeopleUserAction peopleUserAction : list) {
            hashMap.put(peopleUserAction, Integer.valueOf(peopleUserAction.getType().getActionStringID()));
        }
        OCDropdown<PeopleUserAction> oCDropdown = new OCDropdown<>(this.mContext);
        this.mOCDropdown = oCDropdown;
        oCDropdown.setItems(list);
        this.mOCDropdown.setTitleMap(hashMap);
        OCDropdown<PeopleUserAction> oCDropdown2 = this.mOCDropdown;
        oCDropdown2.setOnItemClick(new Function() {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeoplePopupMenu$Zg2QSFncC4nHkK9JfxejID5hM942 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PeoplePopupMenu.this.lambda$new$0$PeoplePopupMenu(obj);
            }
        });
        oCDropdown2.setEventHandler(this.mPanelApp);
        oCDropdown2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.panelapp.people.views.PeoplePopupMenu.AnonymousClass1 */

            public void onDismiss() {
                PeoplePopupMenu.this.mOCDropdown.dismiss();
            }
        });
    }
}
