package com.oculus.panelapp.people.views.holders;

import X.AnonymousClass1Ah;
import X.AnonymousClass2a4;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleTabletEmptyBinding;
import com.oculus.panelapp.people.util.FBLinkingUpsellLauncher;
import com.oculus.panelapp.people.views.PeopleEmptyAdapterItem;
import com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType;
import com.oculus.panelapp.people.views.PeopleEmptyViewLayoutType;
import com.oculus.panelapp.people.views.PeopleEmptyViewModel;
import com.oculus.panelapp.people.views.PeopleViewType;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.SystemUXRoute;

public class PeopleEmptyViewHolder extends AnonymousClass1Ah {
    public static final String LOGIN_WITH_FACEBOOK_ACTION = "LOGIN_WITH_FACEBOOK";
    public final PeopleTabletEmptyBinding mBinding;
    public AnonymousClass2a4 mIconLayoutConstraintSet;
    public AnonymousClass2a4 mImageLayoutConstraintSet;
    public PeopleTabletPanelApp mPanelApp;
    public Resources mResources;
    public PeopleEmptyViewModel mViewModel;

    /* renamed from: com.oculus.panelapp.people.views.holders.PeopleEmptyViewHolder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleEmptyAdapterItemType;

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
                com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType[] r0 = com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.holders.PeopleEmptyViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$views$PeopleEmptyAdapterItemType = r2
                com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType.NO_INTERNET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType.NOT_FB_LINKED     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType.NO_OC_FRIENDS_ALL_CONNECTIONS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType r0 = com.oculus.panelapp.people.views.PeopleEmptyAdapterItemType.NO_OC_FRIENDS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.holders.PeopleEmptyViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PeopleEmptyViewHolder(com.oculus.panelapp.people.databinding.PeopleTabletEmptyBinding r8, com.oculus.panelapp.people.PeopleTabletPanelApp r9) {
        /*
        // Method dump skipped, instructions count: 256
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.holders.PeopleEmptyViewHolder.<init>(com.oculus.panelapp.people.databinding.PeopleTabletEmptyBinding, com.oculus.panelapp.people.PeopleTabletPanelApp):void");
    }

    private void bindLayoutConstraints(PeopleEmptyViewLayoutType peopleEmptyViewLayoutType, String str) {
        AnonymousClass2a4 r3;
        int i;
        float f;
        if (peopleEmptyViewLayoutType == PeopleEmptyViewLayoutType.ICON) {
            if (this.mPanelApp.getCurrentViewType() == PeopleViewType.ALL_CONNECTIONS) {
                r3 = this.mIconLayoutConstraintSet;
                i = R.id.image;
                f = 0.15f;
            } else {
                r3 = this.mIconLayoutConstraintSet;
                i = R.id.image;
                f = 0.5f;
            }
            AnonymousClass2a4.A02(r3, i).A02.A05 = f;
            this.mIconLayoutConstraintSet.A09(this.mBinding.emptyView);
            this.mBinding.title.setTextAppearance(R.style.Body1);
            if (str == null || str.isEmpty()) {
                this.mBinding.title.setTypeface(null, 0);
                this.mBinding.subtitle.setVisibility(8);
                this.mBinding.title.setPadding(0, 0, 0, this.mResources.getDimensionPixelSize(R.dimen.abc_floating_window_z));
            } else {
                this.mBinding.title.setTypeface(null, 1);
                this.mBinding.subtitle.setVisibility(0);
                this.mBinding.title.setPadding(0, 0, 0, 0);
            }
            this.mBinding.subtitle.setPadding(0, 0, 0, this.mResources.getDimensionPixelSize(R.dimen.abc_floating_window_z));
        } else if (peopleEmptyViewLayoutType == PeopleEmptyViewLayoutType.IMAGE) {
            this.mImageLayoutConstraintSet.A09(this.mBinding.emptyView);
            this.mBinding.title.setTextAppearance(R.style.H3);
            this.mBinding.title.setTypeface(null, 0);
            this.mBinding.subtitle.setVisibility(0);
            this.mBinding.title.setPadding(0, 0, 0, 0);
            this.mBinding.subtitle.setPadding(0, this.mResources.getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius), 0, this.mResources.getDimensionPixelSize(R.dimen.abc_dialog_padding_material));
        }
    }

    private void bindLayoutWidth() {
        ConstraintLayout constraintLayout;
        ViewGroup.LayoutParams layoutParams;
        if (this.mPanelApp.getCurrentViewType() == PeopleViewType.ALL_CONNECTIONS || this.mPanelApp.getCurrentViewType() == PeopleViewType.SEARCH) {
            constraintLayout = this.mBinding.emptyView;
            layoutParams = new ViewGroup.LayoutParams(this.mPanelApp.mContext.getResources().getDimensionPixelSize(R.dimen.octablet_full_width), -1);
        } else {
            constraintLayout = this.mBinding.emptyView;
            layoutParams = new ViewGroup.LayoutParams(-1, -1);
        }
        constraintLayout.setLayoutParams(layoutParams);
    }

    public void bindData(PeopleEmptyAdapterItem peopleEmptyAdapterItem) {
        this.mViewModel.setTitle(peopleEmptyAdapterItem.mTitle);
        this.mViewModel.setSubtitle(peopleEmptyAdapterItem.mSubtitle);
        this.mViewModel.setCtaText(peopleEmptyAdapterItem.mCtaText);
        this.mViewModel.setImage(peopleEmptyAdapterItem.mImage);
        this.mViewModel.setType(peopleEmptyAdapterItem.mType);
        bindLayoutWidth();
        bindLayoutConstraints(this.mViewModel.getLayoutType(), peopleEmptyAdapterItem.mSubtitle);
        bindCTA(peopleEmptyAdapterItem.mType);
    }

    public /* synthetic */ void lambda$bindCTA$0$PeopleEmptyViewHolder(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_CONNECT_TO_WIFI, new String[0]);
        this.mPanelApp.actionNavigate(SystemUXRoute.WIFI, "");
    }

    public /* synthetic */ void lambda$bindCTA$1$PeopleEmptyViewHolder(View view) {
        PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.PEOPLE_TAB_LOGIN_WITH_FACEBOOK;
        peopleTabletPanelApp.logButtonClick(clickEventButtonId, new String[0]);
        FBLinkingUpsellLauncher.showUpsell(this.mPanelApp, LOGIN_WITH_FACEBOOK_ACTION, clickEventButtonId.getTelemetryButtonId());
    }

    public /* synthetic */ void lambda$bindCTA$2$PeopleEmptyViewHolder(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_ADD_FRIENDS, new String[0]);
        this.mPanelApp.updatePeopleViewType(PeopleViewType.SEARCH);
    }

    private void bindCTA(PeopleEmptyAdapterItemType peopleEmptyAdapterItemType) {
        switch (peopleEmptyAdapterItemType.ordinal()) {
            case 5:
                this.mBinding.ctaButton.setVisibility(0);
                this.mBinding.ctaButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.people.views.holders.$$Lambda$PeopleEmptyViewHolder$QKMMziQIafiDqAs_EQVUWKEEAY2 */

                    public final void onClick(View view) {
                        PeopleEmptyViewHolder.this.lambda$bindCTA$0$PeopleEmptyViewHolder(view);
                    }
                });
                return;
            case 6:
            case 7:
                this.mBinding.ctaButton.setVisibility(0);
                this.mBinding.ctaButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.people.views.holders.$$Lambda$PeopleEmptyViewHolder$3fXfVpuYjiBNdE7tFIyCDgrFSpw2 */

                    public final void onClick(View view) {
                        PeopleEmptyViewHolder.this.lambda$bindCTA$2$PeopleEmptyViewHolder(view);
                    }
                });
                return;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                this.mBinding.ctaButton.setVisibility(8);
                return;
            case 13:
                this.mBinding.ctaButton.setVisibility(0);
                this.mBinding.ctaButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.people.views.holders.$$Lambda$PeopleEmptyViewHolder$Ba7r2tu8JLW6oNWeUUGlXZ8zr82 */

                    public final void onClick(View view) {
                        PeopleEmptyViewHolder.this.lambda$bindCTA$1$PeopleEmptyViewHolder(view);
                    }
                });
                return;
        }
    }
}
