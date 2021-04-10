package com.oculus.panelapp.anytimeui.v2.tablet.apps.models;

import com.oculus.library.model.App;

public class LibraryItem {
    private App mApp;
    private App mBrowserApp;
    private LibraryFilter mFilter;
    private ItemType mItemType;
    private App mTvApp;
    private UnknownSource mUnknownSource;

    public enum ItemType {
        APP,
        UNKNOWN_SOURCE,
        HEADER_SYSTEM_APPS,
        HEADER_UNKNOWN_SOURCES,
        NULL_STATE,
        LOADING_VIEW,
        GO_GEAR_VR_BANNER,
        GET_MORE_APPS
    }

    public LibraryItem(App app) {
        this.mItemType = ItemType.APP;
        this.mApp = app;
    }

    public LibraryItem(UnknownSource unknownSource) {
        this.mItemType = ItemType.UNKNOWN_SOURCE;
        this.mUnknownSource = unknownSource;
    }

    public LibraryItem(LibraryFilter libraryFilter) {
        this.mItemType = ItemType.NULL_STATE;
        this.mFilter = libraryFilter;
    }

    public LibraryItem(App app, App app2) {
        this.mItemType = ItemType.HEADER_SYSTEM_APPS;
        this.mBrowserApp = app;
        this.mTvApp = app2;
    }

    public LibraryItem(ItemType itemType) {
        this.mItemType = itemType;
    }

    public App getApp() {
        return this.mApp;
    }

    public UnknownSource getUnknownSource() {
        return this.mUnknownSource;
    }

    public LibraryFilter getFilter() {
        return this.mFilter;
    }

    public App getBrowserApp() {
        return this.mBrowserApp;
    }

    public App getTvApp() {
        return this.mTvApp;
    }

    public ItemType getItemType() {
        return this.mItemType;
    }
}
