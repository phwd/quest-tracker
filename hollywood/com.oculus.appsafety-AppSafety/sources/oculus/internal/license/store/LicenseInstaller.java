package oculus.internal.license.store;

import android.content.ContentValues;
import android.util.Log;
import com.oculus.license.License;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import oculus.internal.functional.Pair;
import oculus.internal.functional.Try;
import oculus.internal.license.LicenseFormatException;
import oculus.internal.license.SignerNotFoundException;
import oculus.internal.license.parsing.LicenseBlobParser;
import oculus.internal.license.parsing.LicenseParser;
import oculus.internal.license.parsing.LicenseParsingException;
import oculus.internal.license.store.LicenseInstaller;
import oculus.internal.license.store.LicenseStoreSchema;

public class LicenseInstaller {
    public static final String TAG = "LicenseInstaller";
    private final LicenseBlobParser blobParser;
    private final LicenseParser licenseParser;

    public LicenseInstaller(LicenseBlobParser blobParser2, LicenseParser licenseParser2) {
        this.blobParser = blobParser2;
        this.licenseParser = licenseParser2;
    }

    public Collection<License> install(DbWrapper db, Collection<ByteBuffer> blobs) {
        db.beginTransaction();
        Map<Boolean, List<Try<License>>> results = insert(db, blobs);
        db.setTransactionSuccessful();
        db.endTransaction();
        return unwrapInstalledLicenses(results.get(Boolean.TRUE));
    }

    public Collection<License> installOrThrow(DbWrapper db, Collection<ByteBuffer> blobs) throws LicenseStoreException {
        db.beginTransaction();
        Map<Boolean, List<Try<License>>> results = insert(db, blobs);
        try {
            results.get(Boolean.FALSE).stream().findFirst().get().orElseThrow();
            db.endTransaction();
            return null;
        } catch (NoSuchElementException e) {
            List<Try<License>> successes = results.get(Boolean.TRUE);
            Log.i(TAG, String.format("Inserted %d of %d licenses", Integer.valueOf(successes.size()), Integer.valueOf(blobs.size())));
            db.setTransactionSuccessful();
            Collection<License> unwrapInstalledLicenses = unwrapInstalledLicenses(successes);
            db.endTransaction();
            return unwrapInstalledLicenses;
        } catch (LicenseStoreException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new LicenseStoreException(e3);
        } catch (Throwable th) {
            db.endTransaction();
            throw th;
        }
    }

    static /* synthetic */ License lambda$unwrapInstalledLicenses$0(Try t) {
        return (License) t.orElse((Object) null);
    }

    private Collection<License> unwrapInstalledLicenses(List<Try<License>> results) {
        return (Collection) results.stream().map($$Lambda$LicenseInstaller$eyCI5lNOPG2U7wASM71ep87KazQ.INSTANCE).collect(Collectors.toList());
    }

    private Map<Boolean, List<Try<License>>> insert(DbWrapper db, Collection<ByteBuffer> blobs) {
        Map<Boolean, List<Try<License>>> results = (Map) blobs.stream().map(new Function() {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$QmC30PUAJo7VHh9bHvOuvnTJEQI */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LicenseInstaller.this.lambda$insert$8$LicenseInstaller((ByteBuffer) obj);
            }
        }).map(new Function() {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$p_HVAkIGadx02Qavyarhx6t7Hy0 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Try) obj).tryMap(new Try.F1() {
                    /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$Lli0XkNXCG5jLGO2Qk6obAycpw */

                    public final Object get(Object obj) {
                        return LicenseInstaller.lambda$insert$9(DbWrapper.this, (LicenseInstaller.InstallBundle) obj);
                    }
                });
            }
        }).map(new Function() {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$9Tf6cVJDHWDUN2QsAYDbxMP7f88 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Try) obj).tryMap(new Try.F1() {
                    /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$Q11P4j64QMm4r12GArawuerMXL8 */

                    public final Object get(Object obj) {
                        return LicenseInstaller.lambda$insert$11(DbWrapper.this, (LicenseInstaller.InstallBundle) obj);
                    }
                });
            }
        }).flatMap(new Function() {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$PUjMevR93VVPWPD2IzV3Q0G7RjE */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LicenseInstaller.lambda$insert$19(DbWrapper.this, (Try) obj);
            }
        }).collect(Collector.of($$Lambda$LicenseInstaller$yFIiisxZCZcWjQm_ikjOyG1Y8NY.INSTANCE, $$Lambda$LicenseInstaller$J6bcMm6s7XFh4v4P61srXm0bU28.INSTANCE, $$Lambda$LicenseInstaller$OoQJnoIAsNBewD4hlddzD3FDuY.INSTANCE, $$Lambda$LicenseInstaller$gN40lYIYuzBYsMdaXWOan1h72A.INSTANCE, new Collector.Characteristics[0]));
        results.get(Boolean.FALSE).forEach($$Lambda$LicenseInstaller$NJJTU0brGsintiyuavilCU_ic.INSTANCE);
        Log.i(TAG, String.format("Inserted %d of %d licenses", Integer.valueOf(results.get(Boolean.TRUE).size()), Integer.valueOf(blobs.size())));
        return results;
    }

    static /* synthetic */ Pair lambda$insert$1() {
        return new Pair(new HashSet(), new ArrayList());
    }

    static /* synthetic */ void lambda$insert$4(Pair p, Try t) {
        t.onSuccess(new Consumer(p) {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$mfrkmLltK6q6w1djlvFyl4h1Ss */
            private final /* synthetic */ Pair f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Set) this.f$0.left).add((License) obj);
            }
        });
        t.onFailure(new Consumer(p, t) {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$V38IucJhODXD3FfEjBnhab3vyI */
            private final /* synthetic */ Pair f$0;
            private final /* synthetic */ Try f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Exception exc = (Exception) obj;
                ((List) this.f$0.right).add(this.f$1);
            }
        });
    }

    static /* synthetic */ Pair lambda$insert$5(Pair p1, Pair p2) {
        return new Pair(Boolean.valueOf(((Set) p1.left).addAll((Collection) p2.left)), Boolean.valueOf(((List) p1.right).addAll((Collection) p2.right)));
    }

    static /* synthetic */ Map lambda$insert$6(Pair p) {
        Map<Boolean, List<Try<License>>> results = new HashMap<>();
        results.put(Boolean.TRUE, (List) ((Set) p.left).stream().map($$Lambda$27023KRqM4wDJXKhE8qVk8SZ38.INSTANCE).collect(Collectors.toList()));
        results.put(Boolean.FALSE, (List) p.right);
        return results;
    }

    public /* synthetic */ Try lambda$insert$8$LicenseInstaller(ByteBuffer blob) {
        return Try.Try(new Try.F0(blob) {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$SSnHZmzLgMldrO00VW6KkJHMXNc */
            private final /* synthetic */ ByteBuffer f$1;

            {
                this.f$1 = r2;
            }

            public final Object get() {
                return LicenseInstaller.this.lambda$insert$7$LicenseInstaller(this.f$1);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0042, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        if (r5 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ oculus.internal.license.store.LicenseInstaller.InstallBundle lambda$insert$9(oculus.internal.license.store.DbWrapper r8, oculus.internal.license.store.LicenseInstaller.InstallBundle r9) throws java.lang.Exception {
        /*
            com.oculus.license.License r0 = r9.parsedLicense
            long r0 = r0.id
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.String r3 = "revoked_licenses"
            r1[r2] = r3
            r3 = 1
            java.lang.String r4 = "fbid"
            r1[r3] = r4
            r4 = 2
            r1[r4] = r0
            java.lang.String r4 = "SELECT EXISTS (SELECT * FROM %s WHERE %s = %d)"
            java.lang.String r1 = java.lang.String.format(r4, r1)
            r4 = 0
            android.database.Cursor r5 = r8.rawQuery(r1, r4)
            r5.moveToFirst()     // Catch:{ all -> 0x0040 }
            int r6 = r5.getInt(r2)     // Catch:{ all -> 0x0040 }
            if (r6 == r3) goto L_0x0030
            r5.close()
            return r9
        L_0x0030:
            oculus.internal.license.store.LicenseInstaller$InstallException r6 = new oculus.internal.license.store.LicenseInstaller$InstallException
            java.lang.String r7 = "License %d is revoked and cannot be installed"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r2] = r0
            java.lang.String r2 = java.lang.String.format(r7, r3)
            r6.<init>(r2, r4, r0)
            throw r6
        L_0x0040:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r3 = move-exception
            if (r5 == 0) goto L_0x004d
            r5.close()     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x004d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.license.store.LicenseInstaller.lambda$insert$9(oculus.internal.license.store.DbWrapper, oculus.internal.license.store.LicenseInstaller$InstallBundle):oculus.internal.license.store.LicenseInstaller$InstallBundle");
    }

    static /* synthetic */ InstallBundle lambda$insert$11(DbWrapper db, InstallBundle b) throws Exception {
        Long fbid = Long.valueOf(b.parsedLicense.id);
        try {
            db.delete(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, "fbid=" + fbid.toString(), null);
            db.insertOrThrow(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, null, b.licenseTableRecord);
            return b;
        } catch (Exception e) {
            throw new InstallException(String.format("Failed to insert license %d", fbid), e, fbid);
        }
    }

    static /* synthetic */ Stream lambda$insert$16(DbWrapper db, InstallBundle b) throws Exception {
        Collection<ContentValues> rs = b.packageFilterTableRecords;
        if (rs.isEmpty()) {
            return Stream.of(Try.Try(new Try.F0() {
                /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$PsguFETyCGxYIadMlXmJ25E6IEU */

                public final Object get() {
                    return LicenseInstaller.InstallBundle.this.parsedLicense;
                }
            }));
        }
        return rs.stream().map(new Function(b) {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$F13N8DqvScAoEstUWijYQ6OtR7E */
            private final /* synthetic */ LicenseInstaller.InstallBundle f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Try.Try(new Try.F0((ContentValues) obj, this.f$1) {
                    /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$SK6HAmecUonx9C48cqiA2W1nyus */
                    private final /* synthetic */ ContentValues f$1;
                    private final /* synthetic */ LicenseInstaller.InstallBundle f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final Object get() {
                        return LicenseInstaller.lambda$insert$14(DbWrapper.this, this.f$1, this.f$2);
                    }
                });
            }
        });
    }

    static /* synthetic */ License lambda$insert$14(DbWrapper db, ContentValues r, InstallBundle b) throws Exception {
        try {
            db.insertOrThrow(LicenseStoreSchema.PackageFilterTableSchema.TABLE_NAME, null, r);
            return b.parsedLicense;
        } catch (Exception e) {
            Long fbid = Long.valueOf(b.parsedLicense.id);
            throw new InstallException(String.format("Failed to insert package filter(s) for license %d", fbid), e, fbid);
        }
    }

    static /* synthetic */ License lambda$insert$17(InstallBundle r) throws Exception {
        return null;
    }

    static /* synthetic */ Stream lambda$insert$19(DbWrapper db, Try t) {
        return (Stream) t.tryMap(new Try.F1() {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$ydPTndUcb42g01OlIBA_LZGdlEw */

            public final Object get(Object obj) {
                return LicenseInstaller.lambda$insert$16(DbWrapper.this, (LicenseInstaller.InstallBundle) obj);
            }
        }).orElseGet(new Supplier(t) {
            /* class oculus.internal.license.store.$$Lambda$LicenseInstaller$kulNPrb4yHR4kSKh_nKXhjC4Nfw */
            private final /* synthetic */ Try f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return Stream.of(this.f$0.tryMap($$Lambda$LicenseInstaller$raLPIC3_bjiE3Cq0RJTIwnqSApc.INSTANCE));
            }
        });
    }

    /* access modifiers changed from: private */
    public static class InstallException extends Exception {
        Long fbid;

        InstallException(String message, Throwable cause, Long fbid2) {
            super(message, cause);
            this.fbid = fbid2;
        }
    }

    /* access modifiers changed from: private */
    public class InstallBundle {
        ContentValues licenseTableRecord;
        Collection<ContentValues> packageFilterTableRecords;
        License parsedLicense;

        InstallBundle(License parsedLicense2, ContentValues licenseTableRecord2, Collection<ContentValues> packageFilterTableRecords2) {
            this.parsedLicense = parsedLicense2;
            this.licenseTableRecord = licenseTableRecord2;
            this.packageFilterTableRecords = packageFilterTableRecords2;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: formatRecords */
    public InstallBundle lambda$insert$7$LicenseInstaller(ByteBuffer blob) throws LicenseFormatException, SignatureException, SignerNotFoundException, LicenseParsingException {
        blob.rewind();
        return formatRecords(this.licenseParser.parse(StandardCharsets.UTF_8.decode(this.blobParser.parseLicense(blob)).toString()), blob);
    }

    private InstallBundle formatRecords(License license, ByteBuffer blob) {
        return new InstallBundle(license, LicenseStoreSchema.formatLicenseTableRecord(license, blob), LicenseStoreSchema.formatPackageFilterTableRecords(license));
    }
}
