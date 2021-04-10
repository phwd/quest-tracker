package com.oculus.panelapp.people.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleNavBarBinding;
import java.util.List;

public class PeopleNavBar extends FrameLayout {
    public PeopleNavBarBinding mBinding;
    public PeopleViewType mCurrentView;

    /* renamed from: com.oculus.panelapp.people.views.PeopleNavBar$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.panelapp.people.views.PeopleViewType[] r0 = com.oculus.panelapp.people.views.PeopleViewType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.PeopleNavBar.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$views$PeopleViewType = r2
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SUGGESTIONS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.REQUESTS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.PEOPLE_NEARBY     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleNavBar.AnonymousClass1.<clinit>():void");
        }
    }

    private void clearNavButtonSelection() {
        this.mBinding.friendsButton.setSelected(false);
        this.mBinding.friendRequestsButton.setSelected(false);
        this.mBinding.suggestedButton.setSelected(false);
        this.mBinding.peopleNearbyButton.setSelected(false);
    }

    public void destroy() {
        PeopleNavBarBinding peopleNavBarBinding = this.mBinding;
        OCButton oCButton = peopleNavBarBinding.friendsButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        OCButton oCButton2 = peopleNavBarBinding.suggestedButton;
        oCButton2.mEventHandler = null;
        oCButton2.setOnClickListener(null);
        OCButton oCButton3 = peopleNavBarBinding.friendRequestsButton;
        oCButton3.mEventHandler = null;
        oCButton3.setOnClickListener(null);
        OCButton oCButton4 = peopleNavBarBinding.peopleNearbyButton;
        oCButton4.mEventHandler = null;
        oCButton4.setOnClickListener(null);
    }

    public PeopleViewType getCurrentView() {
        return this.mCurrentView;
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, boolean z, List<PeopleViewType> list) {
        PeopleNavBarBinding peopleNavBarBinding = this.mBinding;
        if (peopleNavBarBinding != null) {
            peopleNavBarBinding.setViewModel(new PeopleNavBarViewModel(peopleTabletPanelApp, getContext()));
            if (!list.contains(PeopleViewType.SUGGESTIONS)) {
                this.mBinding.suggestedButton.setVisibility(8);
            }
            if (!list.contains(PeopleViewType.REQUESTS)) {
                this.mBinding.friendRequestsButton.setVisibility(8);
            }
            if (!list.contains(PeopleViewType.PEOPLE_NEARBY)) {
                this.mBinding.peopleNearbyButton.setVisibility(8);
            }
            PeopleViewType peopleViewType = PeopleViewType.FRIENDS;
            this.mCurrentView = peopleViewType;
            updateNavBarUI(peopleViewType);
            peopleViewUpdateListener.onUpdatePeopleViewType(this.mCurrentView);
            PeopleNavBarBinding peopleNavBarBinding2 = this.mBinding;
            OCButton oCButton = peopleNavBarBinding2.friendsButton;
            oCButton.mEventHandler = peopleTabletPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener(peopleTabletPanelApp, z, peopleViewUpdateListener) {
                /* class com.oculus.panelapp.people.views.$$Lambda$PeopleNavBar$iiEj407JGf2pUhMd6mjm_xvrkh42 */
                public final /* synthetic */ PeopleTabletPanelApp f$1;
                public final /* synthetic */ boolean f$2;
                public final /* synthetic */ PeopleViewUpdateListener f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void onClick(View view) {
                    PeopleNavBar.this.lambda$initialize$0$PeopleNavBar(this.f$1, this.f$2, this.f$3, view);
                }
            });
            OCButton oCButton2 = peopleNavBarBinding2.suggestedButton;
            oCButton2.mEventHandler = peopleTabletPanelApp;
            oCButton2.setOnClickListener(new View.OnClickListener(peopleTabletPanelApp, peopleViewUpdateListener) {
                /* class com.oculus.panelapp.people.views.$$Lambda$PeopleNavBar$sHfrJZ0IB3JRtwMizBDTMlyB1h82 */
                public final /* synthetic */ PeopleTabletPanelApp f$1;
                public final /* synthetic */ PeopleViewUpdateListener f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    PeopleNavBar.this.lambda$initialize$1$PeopleNavBar(this.f$1, this.f$2, view);
                }
            });
            OCButton oCButton3 = peopleNavBarBinding2.friendRequestsButton;
            oCButton3.mEventHandler = peopleTabletPanelApp;
            oCButton3.setOnClickListener(new View.OnClickListener(peopleTabletPanelApp, peopleViewUpdateListener) {
                /* class com.oculus.panelapp.people.views.$$Lambda$PeopleNavBar$Kc9wBzzDQISzq6XBT4ghBSOWbTc2 */
                public final /* synthetic */ PeopleTabletPanelApp f$1;
                public final /* synthetic */ PeopleViewUpdateListener f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    PeopleNavBar.this.lambda$initialize$2$PeopleNavBar(this.f$1, this.f$2, view);
                }
            });
            OCButton oCButton4 = peopleNavBarBinding2.peopleNearbyButton;
            oCButton4.mEventHandler = peopleTabletPanelApp;
            oCButton4.setOnClickListener(new View.OnClickListener(peopleTabletPanelApp, peopleViewUpdateListener) {
                /* class com.oculus.panelapp.people.views.$$Lambda$PeopleNavBar$W3hCw6WyUm_67JR2cM4TeyTsjY2 */
                public final /* synthetic */ PeopleTabletPanelApp f$1;
                public final /* synthetic */ PeopleViewUpdateListener f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    PeopleNavBar.this.lambda$initialize$3$PeopleNavBar(this.f$1, this.f$2, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initialize$0$PeopleNavBar(PeopleTabletPanelApp peopleTabletPanelApp, boolean z, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        ClickEventButtonId clickEventButtonId;
        if (z) {
            clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_FB_VR_FRIENDS_BUTTON;
        } else {
            clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_OC_FRIENDS_ONLINE_BUTTON;
        }
        peopleTabletPanelApp.logButtonClick(clickEventButtonId, new String[0]);
        PeopleViewType peopleViewType = PeopleViewType.FRIENDS;
        peopleViewUpdateListener.onUpdatePeopleViewType(peopleViewType);
        updateNavBarUI(peopleViewType);
    }

    public /* synthetic */ void lambda$initialize$1$PeopleNavBar(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_OC_SUGGESTED_BUTTON, new String[0]);
        PeopleViewType peopleViewType = PeopleViewType.SUGGESTIONS;
        peopleViewUpdateListener.onUpdatePeopleViewType(peopleViewType);
        updateNavBarUI(peopleViewType);
    }

    public /* synthetic */ void lambda$initialize$2$PeopleNavBar(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_OC_FRIEND_REQUEST_BUTTON, new String[0]);
        PeopleViewType peopleViewType = PeopleViewType.REQUESTS;
        peopleViewUpdateListener.onUpdatePeopleViewType(peopleViewType);
        updateNavBarUI(peopleViewType);
    }

    public /* synthetic */ void lambda$initialize$3$PeopleNavBar(PeopleTabletPanelApp peopleTabletPanelApp, PeopleViewUpdateListener peopleViewUpdateListener, View view) {
        peopleTabletPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_OC_NEARBY_BUTTON, new String[0]);
        PeopleViewType peopleViewType = PeopleViewType.PEOPLE_NEARBY;
        peopleViewUpdateListener.onUpdatePeopleViewType(peopleViewType);
        updateNavBarUI(peopleViewType);
    }

    public PeopleNavBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = PeopleNavBarBinding.inflate(LayoutInflater.from(context), this, true);
    }

    private void updateNavBarUI(PeopleViewType peopleViewType) {
        OCButton oCButton;
        clearNavButtonSelection();
        switch (peopleViewType.ordinal()) {
            case 2:
                this.mCurrentView = PeopleViewType.FRIENDS;
                oCButton = this.mBinding.friendsButton;
                break;
            case 3:
                this.mCurrentView = PeopleViewType.REQUESTS;
                oCButton = this.mBinding.friendRequestsButton;
                break;
            case 4:
            default:
                return;
            case 5:
                this.mCurrentView = PeopleViewType.SUGGESTIONS;
                oCButton = this.mBinding.suggestedButton;
                break;
            case 6:
                this.mCurrentView = PeopleViewType.PEOPLE_NEARBY;
                oCButton = this.mBinding.peopleNearbyButton;
                break;
        }
        oCButton.setSelected(true);
    }

    public void setButtonVisibility(PeopleViewType peopleViewType, int i) {
        OCButton oCButton;
        switch (peopleViewType.ordinal()) {
            case 2:
                oCButton = this.mBinding.friendsButton;
                break;
            case 3:
                oCButton = this.mBinding.friendRequestsButton;
                break;
            case 4:
            default:
                return;
            case 5:
                oCButton = this.mBinding.suggestedButton;
                break;
            case 6:
                oCButton = this.mBinding.peopleNearbyButton;
                break;
        }
        oCButton.setVisibility(i);
    }
}
