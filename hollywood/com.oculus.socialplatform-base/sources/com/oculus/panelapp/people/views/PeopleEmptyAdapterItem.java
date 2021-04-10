package com.oculus.panelapp.people.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

public class PeopleEmptyAdapterItem implements PeopleAdapterItem {
    @Nullable
    public final String mCtaText;
    @Nullable
    public final Drawable mImage;
    @Nullable
    public final String mSubtitle;
    @NonNull
    public final String mTitle;
    @NonNull
    public final PeopleEmptyAdapterItemType mType;

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public boolean equals(PeopleAdapterItem peopleAdapterItem) {
        if (this == peopleAdapterItem) {
            return true;
        }
        if (peopleAdapterItem == null || getClass() != peopleAdapterItem.getClass()) {
            return false;
        }
        return this.mTitle.equals(((PeopleEmptyAdapterItem) peopleAdapterItem).mTitle);
    }

    public String getCTAText() {
        return this.mCtaText;
    }

    public PeopleEmptyAdapterItemType getEmptyType() {
        return this.mType;
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public String getID() {
        return this.mTitle;
    }

    public Drawable getImage() {
        return this.mImage;
    }

    @Override // com.oculus.panelapp.people.views.PeopleAdapterItem
    public PeopleAdapterItemType getItemViewType() {
        return PeopleAdapterItemType.EMPTY;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle);
    }

    public PeopleEmptyAdapterItem(PeopleEmptyAdapterItemType peopleEmptyAdapterItemType, Context context) {
        this.mTitle = peopleEmptyAdapterItemType.getTitleString(context.getResources());
        this.mSubtitle = peopleEmptyAdapterItemType.getSubtitleString(context.getResources());
        this.mCtaText = peopleEmptyAdapterItemType.getCTAString(context.getResources());
        this.mImage = peopleEmptyAdapterItemType.getImage(context.getResources());
        this.mType = peopleEmptyAdapterItemType;
    }
}
