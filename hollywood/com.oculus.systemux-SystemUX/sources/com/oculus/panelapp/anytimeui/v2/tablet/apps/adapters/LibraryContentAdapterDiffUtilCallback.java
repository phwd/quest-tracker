package com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryItem;
import java.util.List;

public class LibraryContentAdapterDiffUtilCallback extends DiffUtil.Callback {
    private static final String TAG = LoggingUtil.tag(LibraryContentAdapterDiffUtilCallback.class);
    boolean mNewIsInternetConnected;
    boolean mNewIsTrackingEnabled;
    List<LibraryItem> mNewList;
    boolean mOldIsInternetConnected;
    boolean mOldIsTrackingEnabled;
    List<LibraryItem> mOldList;

    public LibraryContentAdapterDiffUtilCallback(List<LibraryItem> list, List<LibraryItem> list2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.mNewList = list;
        this.mOldList = list2;
        this.mNewIsInternetConnected = z;
        this.mOldIsInternetConnected = z2;
        this.mNewIsTrackingEnabled = z3;
        this.mOldIsTrackingEnabled = z4;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        return this.mOldList.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        return this.mNewList.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i, int i2) {
        if (this.mOldList.size() <= i || this.mNewList.size() <= i2) {
            return false;
        }
        LibraryItem libraryItem = this.mOldList.get(i);
        LibraryItem libraryItem2 = this.mNewList.get(i2);
        if (libraryItem2.getItemType() != libraryItem.getItemType()) {
            return false;
        }
        switch (libraryItem2.getItemType()) {
            case HEADER_SYSTEM_APPS:
            case HEADER_UNKNOWN_SOURCES:
            case GO_GEAR_VR_BANNER:
            case GET_MORE_APPS:
                return true;
            case NULL_STATE:
                if (libraryItem2.getFilter() == libraryItem.getFilter()) {
                    return true;
                }
                return false;
            case APP:
                return libraryItem2.getApp().packageName.equals(libraryItem.getApp().packageName);
            case UNKNOWN_SOURCE:
                return libraryItem2.getUnknownSource().getPackageName().equals(libraryItem.getUnknownSource().getPackageName());
            default:
                return false;
        }
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i, int i2) {
        if (this.mOldList.size() <= i || this.mNewList.size() <= i2) {
            return false;
        }
        LibraryItem libraryItem = this.mOldList.get(i);
        LibraryItem libraryItem2 = this.mNewList.get(i2);
        switch (libraryItem2.getItemType()) {
            case HEADER_SYSTEM_APPS:
                if (libraryItem2.getBrowserApp() == libraryItem.getBrowserApp() && libraryItem2.getTvApp() == libraryItem.getTvApp()) {
                    return true;
                }
                return false;
            case HEADER_UNKNOWN_SOURCES:
            case GO_GEAR_VR_BANNER:
            case GET_MORE_APPS:
            case NULL_STATE:
                return true;
            case APP:
                if (libraryItem2.getApp() == libraryItem.getApp() && this.mNewIsInternetConnected == this.mOldIsInternetConnected && this.mNewIsTrackingEnabled == this.mOldIsTrackingEnabled) {
                    return true;
                }
                return false;
            case UNKNOWN_SOURCE:
                return libraryItem2.getUnknownSource().equals(libraryItem.getUnknownSource());
            default:
                return false;
        }
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    @Nullable
    public Object getChangePayload(int i, int i2) {
        return this.mNewList.get(i2);
    }
}
