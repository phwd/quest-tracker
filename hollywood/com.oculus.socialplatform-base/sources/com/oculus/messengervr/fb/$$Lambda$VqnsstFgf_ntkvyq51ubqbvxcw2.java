package com.oculus.messengervr.fb;

import com.facebook.messengervr.msys.MessengerVrDatabaseSchemaDeployer;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.SqliteHolder;

/* renamed from: com.oculus.messengervr.fb.-$$Lambda$VqnsstFgf_-ntkvyq51ubqbvxcw2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2 implements Database.SchemaDeployer {
    public static final /* synthetic */ $$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2 INSTANCE = new $$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2();

    @Override // com.facebook.msys.mci.Database.SchemaDeployer
    public final int upgrade(SqliteHolder sqliteHolder) {
        return MessengerVrDatabaseSchemaDeployer.deployCrossDatabaseSchema(sqliteHolder);
    }
}
