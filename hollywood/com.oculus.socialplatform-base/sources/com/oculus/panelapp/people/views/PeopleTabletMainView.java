package com.oculus.panelapp.people.views;

import X.AnonymousClass1uW;
import android.content.Context;
import android.util.AttributeSet;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleTabletMainViewBinding;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;

public class PeopleTabletMainView extends BaseView implements PeopleViewUpdateListener {
    public static final String TAG = LoggingUtil.tag(PeopleTabletMainView.class);
    public PeopleTabletMainViewBinding mBinding;
    public PeopleViewType mViewType = PeopleViewType.FRIENDS;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    /* renamed from: com.oculus.panelapp.people.views.PeopleTabletMainView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.panelapp.people.views.PeopleViewType[] r0 = com.oculus.panelapp.people.views.PeopleViewType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.PeopleTabletMainView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$views$PeopleViewType = r2
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.REQUESTS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SUGGESTIONS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.PEOPLE_NEARBY     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SEARCH     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.ALL_CONNECTIONS     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.ALL_NEARBY     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleTabletMainView.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mBinding.peopleSearchView.destroy();
        this.mBinding.peopleView.destroy();
    }

    public PeopleViewType getPeopleViewCurrentViewType() {
        return this.mBinding.peopleView.mCurrentViewType;
    }

    public PeopleViewType getPeopleViewType() {
        return this.mViewType;
    }

    @Override // com.oculus.panelapp.people.views.PeopleViewUpdateListener
    public void onUpdatePeopleViewType(PeopleViewType peopleViewType) {
        this.mViewType = peopleViewType;
        switch (peopleViewType.ordinal()) {
            case 0:
            case 1:
            case 4:
                this.mBinding.setShowSearch(true);
                this.mBinding.peopleSearchView.onUpdatePeopleViewType(peopleViewType);
                return;
            case 2:
            case 3:
            case 5:
            case 6:
                this.mBinding.setShowSearch(false);
                this.mBinding.peopleView.onUpdatePeopleViewType(peopleViewType);
                return;
            default:
                return;
        }
    }

    public PeopleTabletMainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(PeopleTabletPanelApp peopleTabletPanelApp, PeopleTabletMainViewBinding peopleTabletMainViewBinding) {
        super.initialize((AndroidPanelApp) peopleTabletPanelApp, (AnonymousClass1uW) peopleTabletMainViewBinding);
        this.mBinding = peopleTabletMainViewBinding;
        PeopleView peopleView = peopleTabletMainViewBinding.peopleView;
        peopleView.initialize(peopleTabletPanelApp, this);
        peopleView.onShow("");
        peopleView.setVisibility(0);
        this.mBinding.peopleSearchView.initialize(peopleTabletPanelApp, this);
        peopleTabletPanelApp.attachDefaultKeyboardHandler(peopleView);
    }
}
