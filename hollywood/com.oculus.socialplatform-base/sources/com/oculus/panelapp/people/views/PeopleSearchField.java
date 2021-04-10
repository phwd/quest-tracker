package com.oculus.panelapp.people.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.SearchTextWatcher;
import com.oculus.panelapp.people.databinding.PeopleSearchFieldBinding;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.KeyboardHandler;

public class PeopleSearchField extends FrameLayout {
    public static final String TAG = LoggingUtil.tag(PeopleSearchField.class);
    @VisibleForTesting(otherwise = 3)
    public final PeopleSearchFieldBinding mBinding;
    @VisibleForTesting(otherwise = 3)
    public final OCButton mClearButton;
    @VisibleForTesting(otherwise = 3)
    public final SearchEditText mSearchEditText;
    @VisibleForTesting(otherwise = 3)
    public TextWatcher mTextWatcher;

    private void initializeButtons(PeopleTabletPanelApp peopleTabletPanelApp) {
        OCButton oCButton = this.mClearButton;
        oCButton.mEventHandler = peopleTabletPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(peopleTabletPanelApp) {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleSearchField$yz24hZo2mCQoPLcMFyjwwCX5oXU2 */
            public final /* synthetic */ PeopleTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                PeopleSearchField.this.lambda$initializeButtons$0$PeopleSearchField(this.f$1, view);
            }
        });
        this.mSearchEditText.addTextChangedListener(this.mTextWatcher);
        this.mSearchEditText.mCallback = new KeyboardHandler.KeyboardListener(peopleTabletPanelApp) {
            /* class com.oculus.panelapp.people.views.$$Lambda$PeopleSearchField$fnIwpPYWaQhtZrGZpDrCrndMZRU2 */
            public final /* synthetic */ PeopleTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
            public final void onKeyboardActionKey() {
                PeopleSearchField.this.lambda$initializeButtons$1$PeopleSearchField(this.f$1);
            }
        };
    }

    public void destroy() {
        this.mSearchEditText.removeTextChangedListener(this.mTextWatcher);
        this.mSearchEditText.destroy();
        OCButton oCButton = this.mClearButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, final SearchTextWatcher searchTextWatcher) {
        PeopleSearchFieldBinding peopleSearchFieldBinding = this.mBinding;
        if (peopleSearchFieldBinding == null) {
            Log.e(TAG, "Initialized before inflated");
            return;
        }
        this.mTextWatcher = new TextWatcher() {
            /* class com.oculus.panelapp.people.views.PeopleSearchField.AnonymousClass1 */

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                searchTextWatcher.onTextChanged(charSequence.toString());
                OCButton oCButton = PeopleSearchField.this.mClearButton;
                int i4 = 8;
                if (!TextUtils.isEmpty(charSequence)) {
                    i4 = 0;
                }
                oCButton.setVisibility(i4);
            }
        };
        peopleSearchFieldBinding.searchEditText.mEventHandler = peopleTabletPanelApp;
        initializeButtons(peopleTabletPanelApp);
    }

    public /* synthetic */ void lambda$initializeButtons$0$PeopleSearchField(PeopleTabletPanelApp peopleTabletPanelApp, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_SEARCH_CLEAR_BUTTON, new String[0]);
        this.mSearchEditText.setText("");
        this.mSearchEditText.requestFocus();
    }

    public /* synthetic */ void lambda$initializeButtons$1$PeopleSearchField(PeopleTabletPanelApp peopleTabletPanelApp) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_SEARCH_SUBMIT_BUTTON, LoggingConstants.SEARCH_STRING, this.mSearchEditText.getText().toString());
    }

    public void setIsSearchFriends(boolean z) {
        SearchEditText searchEditText = this.mSearchEditText;
        int i = R.string.people_tablet_search_box_hint;
        if (z) {
            i = R.string.people_tablet_search_friends_box_hint;
        }
        searchEditText.setHint(i);
    }

    public PeopleSearchField(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        PeopleSearchFieldBinding inflate = PeopleSearchFieldBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        this.mSearchEditText = inflate.searchEditText;
        this.mClearButton = inflate.searchClearBtn;
    }
}
