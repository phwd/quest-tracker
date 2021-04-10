package com.oculus.alpenglow.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

@Dao
public interface ApplicationDao {
    @Query("SELECT * FROM application")
    List<Application> A2t();

    @Query("SELECT * FROM application WHERE app_id = :appId")
    Application A2w(String str);

    @Query("SELECT * FROM application WHERE package_name = :packageName")
    List<Application> A2y(String str);

    @Query("SELECT * FROM application WHERE app_id = :appId")
    @Transaction
    List<AssetsForApplication> A2z(String str);

    @Insert(onConflict = 1)
    void A5I(Application... applicationArr);

    @Delete
    void A7M(Application application, List<Asset> list);

    @Query("UPDATE application SET status = :appStatus, package_name = :packageName WHERE app_id = :appId")
    void A7k(String str, String str2, int i);

    @Query("UPDATE application SET downloaded_bytes = :downloadedBytes WHERE app_id = :appId")
    void A8i(String str, long j);

    @Query("UPDATE application SET downloaded_bytes = :downloadedBytes, total_bytes = :totalBytes WHERE app_id = :appId")
    void A8j(String str, long j, long j2);

    @Query("UPDATE application SET status = :appStatus WHERE app_id = :appId")
    void A8l(String str, int i);
}
