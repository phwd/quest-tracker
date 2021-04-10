package com.oculus.alpenglow.database;

import X.AnonymousClass0Fr;
import androidx.room.Database;

@Database(entities = {Application.class, Asset.class}, version = 2)
public abstract class AbstractApplicationDatabase extends AnonymousClass0Fr {
    public abstract ApplicationDao A08();

    public abstract AssetDao A09();
}
