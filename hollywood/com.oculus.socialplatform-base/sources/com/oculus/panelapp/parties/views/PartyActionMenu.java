package com.oculus.panelapp.parties.views;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.ocui.OCDropdown;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.views.actions.PartyAction;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class PartyActionMenu {
    public OCDropdown<PartyAction> mOCDropdown;

    public void dismissPopup() {
        this.mOCDropdown.dismiss();
    }

    public List<PartyAction> getActions() {
        return this.mOCDropdown.mAdapter.mItems;
    }

    public void setSelectedItem(PartyAction partyAction) {
        this.mOCDropdown.setSelectedItem(partyAction);
    }

    public void togglePopup(View view) {
        this.mOCDropdown.toggle(view);
    }

    public PartyActionMenu(Context context, PartiesTabletPanelApp partiesTabletPanelApp, List<PartyAction> list) {
        HashMap hashMap = new HashMap();
        for (PartyAction partyAction : list) {
            hashMap.put(partyAction, Integer.valueOf(partyAction.getType().getActionStringID()));
        }
        OCDropdown<PartyAction> oCDropdown = new OCDropdown<>(context);
        this.mOCDropdown = oCDropdown;
        oCDropdown.setItems(list);
        this.mOCDropdown.setTitleMap(hashMap);
        OCDropdown<PartyAction> oCDropdown2 = this.mOCDropdown;
        oCDropdown2.setOnItemClick(new Function(context) {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartyActionMenu$dfbAHnmSN6rHhAFBJMtwXxN7902 */
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ((PartyAction) obj).execute(this.f$0, null);
                return null;
            }
        });
        oCDropdown2.setEventHandler(partiesTabletPanelApp);
        oCDropdown2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.panelapp.parties.views.PartyActionMenu.AnonymousClass1 */

            public void onDismiss() {
                PartyActionMenu.this.mOCDropdown.dismiss();
            }
        });
    }
}
