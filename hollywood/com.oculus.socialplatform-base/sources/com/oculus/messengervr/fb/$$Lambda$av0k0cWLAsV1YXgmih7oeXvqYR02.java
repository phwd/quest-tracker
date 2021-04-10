package com.oculus.messengervr.fb;

import com.facebook.messengervr.msys.MessengerVrDatabaseSchemaDeployer;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.SqliteHolder;

/* renamed from: com.oculus.messengervr.fb.-$$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02  reason: invalid class name */
public final /* synthetic */ class $$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02 implements Database.SchemaDeployer {
    public static final /* synthetic */ $$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02 INSTANCE = new $$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02();

    @Override // com.facebook.msys.mci.Database.SchemaDeployer
    public final int upgrade(SqliteHolder sqliteHolder) {
        return MessengerVrDatabaseSchemaDeployer.deployInMemorySchema(sqliteHolder);
    }
}
