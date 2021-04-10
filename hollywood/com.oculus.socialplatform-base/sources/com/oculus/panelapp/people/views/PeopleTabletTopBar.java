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
import com.oculus.panelapp.people.databinding.PeopleTabletTopBarBinding;

public class PeopleTabletTopBar extends FrameLayout {
    public static final String TAG = LoggingUtil.tag(PeopleTabletTopBar.class);
    public final PeopleTabletTopBarBinding mBinding;

    private void initializeButtons(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener) {
        PeopleTabletTopBarBinding peopleTabletTopBarBinding = this.mBinding;
        OCButton oCButton = peopleTabletTopBarBinding.searchButton;
        oCButton.mEventHandler = peopleTabletPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(peopleViewUpdateListener) {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleTabletTopBar$gtVLl9YUW56D0E_EOlmldezPm02 */
            public final /* synthetic */ PeopleViewUpdateListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                PeopleTabletTopBar.lambda$initializeButtons$0(PeopleTabletPanelApp.this, this.f$1, view);
            }
        });
        OCButton oCButton2 = peopleTabletTopBarBinding.connectionsButton;
        oCButton2.mEventHandler = peopleTabletPanelApp;
        oCButton2.setOnClickListener(new View.OnClickListener(peopleViewUpdateListener) {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleTabletTopBar$zFHjXha39bSfqVGm7E1qIGmLRpQ2 */
            public final /* synthetic */ PeopleViewUpdateListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                PeopleTabletTopBar.lambda$initializeButtons$1(PeopleTabletPanelApp.this, this.f$1, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initializeButtons$0(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_SEARCH_BUTTON, new String[0]);
        peopleViewUpdateListener.onUpdatePeopleViewType(PeopleViewType.SEARCH);
    }

    public static /* synthetic */ void lambda$initializeButtons$1(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_ALL_CONNECTIONS_BUTTON, new String[0]);
        peopleViewUpdateListener.onUpdatePeopleViewType(PeopleViewType.ALL_CONNECTIONS);
    }

    public void destroy() {
        PeopleTabletTopBarBinding peopleTabletTopBarBinding = this.mBinding;
        OCButton oCButton = peopleTabletTopBarBinding.searchButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        OCButton oCButton2 = peopleTabletTopBarBinding.connectionsButton;
        oCButton2.mEventHandler = null;
        oCButton2.setOnClickListener(null);
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener) {
        PeopleTabletTopBarBinding peopleTabletTopBarBinding = this.mBinding;
        if (peopleTabletTopBarBinding == null) {
            Log.e(TAG, "Initialized before inflated");
            return;
        }
        peopleTabletTopBarBinding.setViewModel(new PeopleTabletTopBarViewModel(peopleTabletPanelApp, getContext()));
        initializeButtons(peopleTabletPanelApp, peopleViewUpdateListener);
    }

    public PeopleTabletTopBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = PeopleTabletTopBarBinding.inflate(LayoutInflater.from(context), this, true);
    }
}
