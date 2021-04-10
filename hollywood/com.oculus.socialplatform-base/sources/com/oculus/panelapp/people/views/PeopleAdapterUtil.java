package com.oculus.panelapp.people.views;

import android.content.Context;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.ArrayList;
import java.util.List;

public class PeopleAdapterUtil {

    /* renamed from: com.oculus.panelapp.people.views.PeopleAdapterUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.panelapp.people.views.PeopleViewType[] r0 = com.oculus.panelapp.people.views.PeopleViewType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.people.views.PeopleAdapterUtil.AnonymousClass1.$SwitchMap$com$oculus$panelapp$people$views$PeopleViewType = r2
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.PEOPLE_NEARBY     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.SUGGESTIONS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.REQUESTS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleAdapterUtil.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x001c A[LOOP:0: B:5:0x0016->B:7:0x001c, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.panelapp.people.views.PeopleAdapterItem> buildUserCardRow(java.util.List<com.oculus.horizoncontent.social.SocialUser> r4, com.oculus.panelapp.people.views.PeopleViewType r5, android.content.Context r6) {
        /*
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L_0x004f
            int r0 = r5.ordinal()
            switch(r0) {
                case 2: goto L_0x002e;
                case 3: goto L_0x002b;
                case 4: goto L_0x0012;
                case 5: goto L_0x002b;
                case 6: goto L_0x002e;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.util.Iterator r2 = r4.iterator()
        L_0x0016:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0039
            java.lang.Object r1 = r2.next()
            com.oculus.horizoncontent.social.SocialUser r1 = (com.oculus.horizoncontent.social.SocialUser) r1
            com.oculus.panelapp.people.views.PeopleUserAdapterItem r0 = new com.oculus.panelapp.people.views.PeopleUserAdapterItem
            r0.<init>(r1)
            r3.add(r0)
            goto L_0x0016
        L_0x002b:
            com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r1 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.FIND_FRIENDS
            goto L_0x0030
        L_0x002e:
            com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r1 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.CREATE_PARTY
        L_0x0030:
            com.oculus.panelapp.people.views.PeopleUpsellAdapterItem r0 = new com.oculus.panelapp.people.views.PeopleUpsellAdapterItem
            r0.<init>(r1, r6)
            r3.add(r0)
            goto L_0x0012
        L_0x0039:
            com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.FRIENDS
            if (r5 != r0) goto L_0x0048
            com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r1 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.ALL_FRIENDS
        L_0x003f:
            com.oculus.panelapp.people.views.PeopleUpsellAdapterItem r0 = new com.oculus.panelapp.people.views.PeopleUpsellAdapterItem
            r0.<init>(r1, r6)
            r3.add(r0)
            return r3
        L_0x0048:
            com.oculus.panelapp.people.views.PeopleViewType r0 = com.oculus.panelapp.people.views.PeopleViewType.PEOPLE_NEARBY
            if (r5 != r0) goto L_0x004f
            com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType r1 = com.oculus.panelapp.people.views.PeopleUpsellAdapterItemType.ALL_NEARBY
            goto L_0x003f
        L_0x004f:
            return r3
            switch-data {2->0x002e, 3->0x002b, 4->0x0012, 5->0x002b, 6->0x002e, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleAdapterUtil.buildUserCardRow(java.util.List, com.oculus.panelapp.people.views.PeopleViewType, android.content.Context):java.util.List");
    }

    public static List<PeopleAdapterItem> buildUserCardRowForVerticalScroll(List<SocialUser> list, PeopleViewType peopleViewType, Context context) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            arrayList.add(new PeopleCardsRowAdapterItem(buildUserCardRow(list, peopleViewType, context)));
        }
        return arrayList;
    }
}
