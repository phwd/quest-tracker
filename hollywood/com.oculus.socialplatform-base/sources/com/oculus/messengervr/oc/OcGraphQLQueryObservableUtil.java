package com.oculus.messengervr.oc;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC13251zE;
import X.C13261zF;
import android.annotation.TargetApi;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcGraphQLQueryObservableUtil {
    public static AbstractC13251zE<Map<String, Pair<String, String>>> createQueryNamesAndProfilePicsSingle(OcUserPictureUrlsQueryHandler ocUserPictureUrlsQueryHandler, Set<String> set, Map<String, String> map, long j) {
        return new C13261zF(AbstractC13251zE.A00(new AbstractC06371Zh(set, j, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$BXuYJ1NCrYHiB21kIGnHbFKLo2 */
            public final /* synthetic */ Set f$1;
            public final /* synthetic */ long f$2;
            public final /* synthetic */ Map f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r7) {
                OcUserPictureUrlsQueryHandler.this.queryOCUsersNamesAndProfilePics(new ArrayList(this.f$1), new Consumer(this.f$2, this.f$3, new HashMap(), r7) {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$2sVdHr5xIa9tEhrdqLqm8Pucg2 */
                    public final /* synthetic */ long f$0;
                    public final /* synthetic */ Map f$1;
                    public final /* synthetic */ Map f$2;
                    public final /* synthetic */ AbstractC10551og f$3;

                    {
                        this.f$0 = r1;
                        this.f$1 = r3;
                        this.f$2 = r4;
                        this.f$3 = r5;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        OcGraphQLQueryObservableUtil.lambda$createQueryNamesAndProfilePicsSingle$9(this.f$0, this.f$1, this.f$2, this.f$3, (Map) obj);
                    }
                }, new Consumer() {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$0qA5412HhQ101bPTyiWs2GUJonk2 */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AbstractC10551og.this.onError((Throwable) obj);
                    }
                });
            }
        }));
    }

    public static /* synthetic */ void lambda$createQueryNamesAndProfilePicsSingle$8(Map map, Map map2, Map.Entry entry) {
        synchronized (map) {
            map.put((String) entry.getKey(), (String) ((Pair) entry.getValue()).second);
            map2.put((String) entry.getKey(), (Pair) entry.getValue());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r0.equals(r1) != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$createQueryProfilePicsSingle$0(java.util.Map r2, java.util.Map r3, java.util.Map.Entry r4) {
        /*
            monitor-enter(r2)
            java.lang.Object r0 = r4.getKey()     // Catch:{ all -> 0x0032 }
            java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0032 }
            java.lang.Object r1 = r4.getValue()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0016
            if (r1 == 0) goto L_0x001c
            goto L_0x001e
        L_0x0016:
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x001e
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0032 }
            goto L_0x0031
        L_0x001e:
            java.lang.Object r0 = r4.getKey()     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0032 }
            r2.put(r0, r1)     // Catch:{ all -> 0x0032 }
            java.lang.Object r0 = r4.getKey()     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0032 }
            r3.put(r0, r1)     // Catch:{ all -> 0x0032 }
            goto L_0x001c
        L_0x0031:
            return
        L_0x0032:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0032 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.messengervr.oc.OcGraphQLQueryObservableUtil.lambda$createQueryProfilePicsSingle$0(java.util.Map, java.util.Map, java.util.Map$Entry):void");
    }

    public static AbstractC13251zE<Set<String>> createQueryParticipantsBlockStatusSingle(OcThreadParticipantsBlockStatusQueryHandler ocThreadParticipantsBlockStatusQueryHandler, String str) {
        return new C13261zF(AbstractC13251zE.A00(new AbstractC06371Zh(str) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$NtMVS40MEBXbf6xQVMEr44toRLQ2 */
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r3) {
                String str;
                OcThreadParticipantsBlockStatusQueryHandler.this.queryParticipantBlockStatuses(str, new Consumer(this.f$1, r3) {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$vU0lVoDRwgAkjL79Wpze9U__AA2 */
                    public final /* synthetic */ String f$0;
                    public final /* synthetic */ AbstractC10551og f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        OcGraphQLQueryObservableUtil.lambda$createQueryParticipantsBlockStatusSingle$5(this.f$0, this.f$1, (Map) obj);
                    }
                }, new Consumer() {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$UEuqX1ALbqKzXU4VnWeQCgOEpkU2 */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AbstractC10551og.this.onError((Throwable) obj);
                    }
                });
            }
        }));
    }

    public static AbstractC13251zE<Map<String, String>> createQueryProfilePicsSingle(OcUserPictureUrlsQueryHandler ocUserPictureUrlsQueryHandler, Set<String> set, Map<String, String> map) {
        return new C13261zF(AbstractC13251zE.A00(new AbstractC06371Zh(set, map) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$EtCLxhChPwIJodZx3itw7x1fzt82 */
            public final /* synthetic */ Set f$1;
            public final /* synthetic */ Map f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r4) {
                Set set;
                OcUserPictureUrlsQueryHandler.this.queryOCUsersProfilePics(new ArrayList(set), new Consumer(this.f$1, this.f$2, new HashMap(), r4) {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$S1lFEdS_E6AznbiLsDhbTjb1Kcw2 */
                    public final /* synthetic */ Set f$0;
                    public final /* synthetic */ Map f$1;
                    public final /* synthetic */ Map f$2;
                    public final /* synthetic */ AbstractC10551og f$3;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        OcGraphQLQueryObservableUtil.lambda$createQueryProfilePicsSingle$1(this.f$0, this.f$1, this.f$2, this.f$3, (Map) obj);
                    }
                }, new Consumer() {
                    /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$krWberN5xorLvPwMPi8I98uaLlQ2 */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AbstractC10551og.this.onError((Throwable) obj);
                    }
                });
            }
        }));
    }

    public static /* synthetic */ void lambda$createQueryNamesAndProfilePicsSingle$9(long j, Map map, Map map2, AbstractC10551og r4, Map map3) {
        map3.size();
        map3.entrySet().stream().forEach(new Consumer(map, map2) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$ik7qN_5bOcE8cMUPSDMs0U6QIg2 */
            public final /* synthetic */ Map f$0;
            public final /* synthetic */ Map f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OcGraphQLQueryObservableUtil.lambda$createQueryNamesAndProfilePicsSingle$8(this.f$0, this.f$1, (Map.Entry) obj);
            }
        });
        map2.size();
        r4.onSuccess(map2);
    }

    public static /* synthetic */ void lambda$createQueryParticipantsBlockStatusSingle$5(String str, AbstractC10551og r2, Map map) {
        Set set = (Set) map.entrySet().stream().filter($$Lambda$OcGraphQLQueryObservableUtil$jL_aDlzijRx4z5WFWG6m6VUlo82.INSTANCE).map($$Lambda$CSz_ibwXhtkKNl72Q8tR5oBgkWk2.INSTANCE).collect(Collectors.toSet());
        set.size();
        r2.onSuccess(set);
    }

    public static /* synthetic */ void lambda$createQueryProfilePicsSingle$1(Set set, Map map, Map map2, AbstractC10551og r4, Map map3) {
        map3.size();
        set.size();
        map3.entrySet().stream().forEach(new Consumer(map, map2) {
            /* class com.oculus.messengervr.oc.$$Lambda$OcGraphQLQueryObservableUtil$bmKaT1vPTH2SUuIqu1uZrHAKtGU2 */
            public final /* synthetic */ Map f$0;
            public final /* synthetic */ Map f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OcGraphQLQueryObservableUtil.lambda$createQueryProfilePicsSingle$0(this.f$0, this.f$1, (Map.Entry) obj);
            }
        });
        r4.onSuccess(map2);
    }
}
