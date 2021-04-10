package com.oculus.messengervr.fb;

import com.facebook.messengervr.msys.MessengerVrDatabaseSchemaDeployer;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.SqliteHolder;

/* renamed from: com.oculus.messengervr.fb.-$$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02  reason: invalid class name */
public final /* synthetic */ class $$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02 implements Database.SchemaDeployer {
    public static final /* synthetic */ $$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02 INSTANCE = new $$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02();

    @Override // com.facebook.msys.mci.Database.SchemaDeployer
    public final int upgrade(SqliteHolder sqliteHolder) {
        return MessengerVrDatabaseSchemaDeployer.deployPersistentSchema(sqliteHolder);
    }
}
