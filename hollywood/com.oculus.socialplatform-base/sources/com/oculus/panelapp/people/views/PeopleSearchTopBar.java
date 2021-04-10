package com.oculus.panelapp.people.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.SearchTextWatcher;
import com.oculus.panelapp.people.databinding.PeopleSearchTopBarBinding;

public class PeopleSearchTopBar extends FrameLayout {
    public static final String TAG = LoggingUtil.tag(PeopleSearchTopBar.class);
    public final PeopleSearchTopBarBinding mBinding;

    private void initializeButtons(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener) {
        OCButton oCButton = this.mBinding.backButton;
        oCButton.mEventHandler = peopleTabletPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(peopleTabletPanelApp, peopleViewUpdateListener) {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleSearchTopBar$R0qKSTbE2P_2b8MtzAyfGPTpI42 */
            public final /* synthetic */ PeopleTabletPanelApp f$1;
            public final /* synthetic */ PeopleViewUpdateListener f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                PeopleSearchTopBar.this.lambda$initializeButtons$0$PeopleSearchTopBar(this.f$1, this.f$2, view);
            }
        });
    }

    public void destroy() {
        this.mBinding.searchBox.destroy();
        OCButton oCButton = this.mBinding.backButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
    }

    public void hideSearchBar() {
        this.mBinding.searchBox.setVisibility(4);
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, SearchTextWatcher searchTextWatcher) {
        PeopleSearchTopBarBinding peopleSearchTopBarBinding = this.mBinding;
        if (peopleSearchTopBarBinding == null) {
            Log.e(TAG, "Initialized before inflated");
            return;
        }
        peopleSearchTopBarBinding.searchBox.initialize(peopleTabletPanelApp, searchTextWatcher);
        initializeButtons(peopleTabletPanelApp, peopleViewUpdateListener);
    }

    public /* synthetic */ void lambda$initializeButtons$0$PeopleSearchTopBar(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_SEARCH_BACK_BUTTON, new String[0]);
        this.mBinding.searchBox.mSearchEditText.setText("");
        peopleViewUpdateListener.onUpdatePeopleViewType(peopleTabletPanelApp.getPeopleViewCurrentViewType());
    }

    public void onUpdatePeopleViewType(PeopleViewType peopleViewType) {
        PeopleSearchField peopleSearchField = this.mBinding.searchBox;
        boolean z = false;
        if (peopleViewType == PeopleViewType.ALL_CONNECTIONS) {
            z = true;
        }
        peopleSearchField.setIsSearchFriends(z);
    }

    public void showSearchBar() {
        this.mBinding.searchBox.setVisibility(0);
    }

    public PeopleSearchTopBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = PeopleSearchTopBarBinding.inflate(LayoutInflater.from(context), this, true);
    }
}
