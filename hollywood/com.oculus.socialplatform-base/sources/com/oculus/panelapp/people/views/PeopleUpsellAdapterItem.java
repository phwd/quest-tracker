package com.oculus.panelapp.people.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.util.Objects;

public class PeopleUpsellAdapterItem implements PeopleAdapterItem {
    @NonNull
    public final Drawable mIcon;
    @NonNull
    public final String mTitle;
    @NonNull
    public final PeopleUpsellAdapterItemType mType;

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public boolean equals(PeopleAdapterItem peopleAdapterItem) {
        if (this == peopleAdapterItem) {
            return true;
        }
        if (peopleAdapterItem == null || getClass() != peopleAdapterItem.getClass()) {
            return false;
        }
        return this.mTitle.equals(((PeopleUpsellAdapterItem) peopleAdapterItem).mTitle);
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public String getID() {
        return this.mTitle;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public PeopleAdapterItemType getItemViewType() {
        return PeopleAdapterItemType.UPSELL_CARD;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public PeopleUpsellAdapterItemType getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle);
    }

    public PeopleUpsellAdapterItem(PeopleUpsellAdapterItemType peopleUpsellAdapterItemType, Context context) {
        this.mTitle = peopleUpsellAdapterItemType.getTitleString(context.getResources());
        this.mIcon = peopleUpsellAdapterItemType.getActionIcon(context.getResources());
        this.mType = peopleUpsellAdapterItemType;
    }
}
