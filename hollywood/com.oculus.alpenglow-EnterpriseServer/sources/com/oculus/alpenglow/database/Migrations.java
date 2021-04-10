package com.oculus.alpenglow.database;

import X.AnonymousClass0G5;
import X.AnonymousClass0GQ;

public class Migrations {
    public static final AnonymousClass0G5 MIGRATION_1_2 = new AnonymousClass0G5() {
        /* class com.oculus.alpenglow.database.Migrations.AnonymousClass1 */

        @Override // X.AnonymousClass0G5
        public final void A00(AnonymousClass0GQ r2) {
            r2.A2Q("ALTER TABLE application ADD COLUMN app_source INTEGER");
        }
    };
}
