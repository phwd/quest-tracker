package com.oculus.alpenglow.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AssetDao {
    @Query("SELECT * FROM asset")
    List<Asset> A2t();

    @Insert(onConflict = 1)
    void A5J(Asset... assetArr);
}
