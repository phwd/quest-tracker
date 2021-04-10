package com.oculus.panelapp.messenger.views;

import X.AbstractC08911fj;
import X.AnonymousClass1gU;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCDropdown;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.models.DraftThread;
import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import com.oculus.panelapp.messenger.fetchers.MessengerContact;
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DraftThreadParticipantsDropdown {
    public static final long SEARCH_DELAY_MS = 500;
    public static final String TAG = LoggingUtil.tag(DraftThreadParticipantsDropdown.class);
    public Context mContext;
    public Handler mHandler;
    public Map<MessengerContact, Drawable> mMessengerContactProfilePictureMap = new HashMap();
    public Function<MessengerContact, ?> mOnMessengerContactItemClickListener;
    public MessengerPanelApp mPanelApp;
    public OCDropdown<MessengerContact> mParticipantDropdown;
    public Map<MessengerContact, AnonymousClass1gU<Bitmap>> mProfilePictureTargets = new HashMap();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProfilePictureTargets() {
        for (AnonymousClass1gU<Bitmap> r1 : this.mProfilePictureTargets.values()) {
            this.mPanelApp.getImageHandler().unloadTarget(r1);
        }
        this.mMessengerContactProfilePictureMap.clear();
        this.mProfilePictureTargets.clear();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private AnonymousClass1gU<Bitmap> getProfilePictureTarget(final MessengerContact messengerContact) {
        return new AnonymousClass1gU<Bitmap>(this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter), this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter)) {
            /* class com.oculus.panelapp.messenger.views.DraftThreadParticipantsDropdown.AnonymousClass2 */

            @Override // X.AbstractC08781fH
            public void onLoadCleared(@Nullable Drawable drawable) {
                DraftThreadParticipantsDropdown.this.mMessengerContactProfilePictureMap.put(messengerContact, drawable);
                DraftThreadParticipantsDropdown draftThreadParticipantsDropdown = DraftThreadParticipantsDropdown.this;
                draftThreadParticipantsDropdown.mParticipantDropdown.setIconMap(draftThreadParticipantsDropdown.mMessengerContactProfilePictureMap);
                OCDropdown<MessengerContact> oCDropdown = DraftThreadParticipantsDropdown.this.mParticipantDropdown;
                oCDropdown.setItems(oCDropdown.mAdapter.mItems);
            }

            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable AbstractC08911fj<? super Bitmap> r6) {
                DraftThreadParticipantsDropdown draftThreadParticipantsDropdown = DraftThreadParticipantsDropdown.this;
                draftThreadParticipantsDropdown.mMessengerContactProfilePictureMap.put(messengerContact, new BitmapDrawable(draftThreadParticipantsDropdown.mContext.getResources(), bitmap));
                DraftThreadParticipantsDropdown draftThreadParticipantsDropdown2 = DraftThreadParticipantsDropdown.this;
                draftThreadParticipantsDropdown2.mParticipantDropdown.setIconMap(draftThreadParticipantsDropdown2.mMessengerContactProfilePictureMap);
                OCDropdown<MessengerContact> oCDropdown = DraftThreadParticipantsDropdown.this.mParticipantDropdown;
                oCDropdown.setItems(oCDropdown.mAdapter.mItems);
            }

            @Override // X.AbstractC08781fH
            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable AbstractC08911fj r2) {
                onResourceReady((Bitmap) obj, (AbstractC08911fj<? super Bitmap>) r2);
            }
        };
    }

    private boolean onClickUserItem(MessengerContact messengerContact) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_DRAFT_THREAD_PARTICIPANT_DROPDOWN_ITEM, SurfaceType.THREAD_VIEW);
        Function<MessengerContact, ?> function = this.mOnMessengerContactItemClickListener;
        if (function == null) {
            return true;
        }
        function.apply(messengerContact);
        return true;
    }

    private void updateParticipantDropdownItems(String str) {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable(str) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantsDropdown$3yBw2WbCB5UE5aAuiGMbX8h5RlI2 */
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DraftThreadParticipantsDropdown.this.lambda$updateParticipantDropdownItems$1$DraftThreadParticipantsDropdown(this.f$1);
            }
        }, 500);
    }

    public void clear() {
        if (this.mParticipantDropdown != null) {
            clearProfilePictureTargets();
            this.mParticipantDropdown.setTitleMap(new HashMap());
            this.mParticipantDropdown.setIconMap(new HashMap());
            this.mParticipantDropdown.setItems(new ArrayList());
        }
    }

    public void hide() {
        OCDropdown<MessengerContact> oCDropdown = this.mParticipantDropdown;
        if (oCDropdown != null) {
            oCDropdown.dismiss();
        }
    }

    public /* synthetic */ void lambda$updateParticipantDropdownItems$1$DraftThreadParticipantsDropdown(final String str) {
        this.mPanelApp.getContactQuery().queryContacts(str, new IContactFetcher.ContactQueryCallback() {
            /* class com.oculus.panelapp.messenger.views.DraftThreadParticipantsDropdown.AnonymousClass1 */

            @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher.ContactQueryCallback
            public void onError() {
                Log.e(DraftThreadParticipantsDropdown.TAG, "fetching contacts failed");
            }

            @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher.ContactQueryCallback
            public void onResult(List<MessengerContact> list) {
                DraftThread draftThread = DraftThreadParticipantsDropdown.this.mPanelApp.getAPIManager().mDraftThread;
                if (draftThread != null) {
                    if (str.length() <= 0 || list.size() != 0) {
                        DraftThreadParticipantsDropdown.this.mParticipantDropdown.setContextMenuTitle((int) R.string.anytime_tablet_messenger_thread_compose_view_participant_dropdown_title);
                    } else {
                        DraftThreadParticipantsDropdown draftThreadParticipantsDropdown = DraftThreadParticipantsDropdown.this;
                        draftThreadParticipantsDropdown.mParticipantDropdown.setContextMenuTitle(draftThreadParticipantsDropdown.mContext.getResources().getString(R.string.anytime_tablet_messenger_thread_compose_view_participant_dropdown_title_no_results, str));
                    }
                    DraftThreadParticipantsDropdown.this.clearProfilePictureTargets();
                    ArrayList arrayList = new ArrayList();
                    HashMap hashMap = new HashMap();
                    Drawable drawable = DraftThreadParticipantsDropdown.this.mContext.getDrawable(R.drawable.oc_icon_profile_filled_48_d2d2d2);
                    for (MessengerContact messengerContact : list) {
                        if (!draftThread.mParticipants.contains(messengerContact)) {
                            arrayList.add(messengerContact);
                            hashMap.put(messengerContact, messengerContact.mUserName);
                            DraftThreadParticipantsDropdown draftThreadParticipantsDropdown2 = DraftThreadParticipantsDropdown.this;
                            draftThreadParticipantsDropdown2.mProfilePictureTargets.put(messengerContact, draftThreadParticipantsDropdown2.getProfilePictureTarget(messengerContact));
                            DraftThreadParticipantsDropdown.this.mMessengerContactProfilePictureMap.put(messengerContact, drawable);
                            DraftThreadParticipantsDropdown.this.mPanelApp.getImageHandler().loadCircleCroppedToTarget(messengerContact.mProfilePictureUrl, DraftThreadParticipantsDropdown.this.mProfilePictureTargets.get(messengerContact));
                        }
                    }
                    DraftThreadParticipantsDropdown.this.mParticipantDropdown.setTitleMap(hashMap);
                    DraftThreadParticipantsDropdown draftThreadParticipantsDropdown3 = DraftThreadParticipantsDropdown.this;
                    draftThreadParticipantsDropdown3.mParticipantDropdown.setIconMap(draftThreadParticipantsDropdown3.mMessengerContactProfilePictureMap);
                    DraftThreadParticipantsDropdown.this.mParticipantDropdown.setItems(arrayList);
                }
            }
        });
    }

    public void show(View view, String str) {
        OCDropdown<MessengerContact> oCDropdown = this.mParticipantDropdown;
        if (oCDropdown == null) {
            OCDropdown<MessengerContact> oCDropdown2 = new OCDropdown<>(this.mContext);
            this.mParticipantDropdown = oCDropdown2;
            oCDropdown2.setContextMenuTitle((int) R.string.anytime_tablet_messenger_thread_compose_view_participant_dropdown_title);
            this.mParticipantDropdown.setWidth((int) this.mContext.getResources().getDimension(R.dimen.messenger_thread_create_participant_dropdown_width));
            this.mParticipantDropdown.setIconSizeDp((int) this.mContext.getResources().getDimension(R.dimen.abc_dialog_padding_material));
            OCDropdown<MessengerContact> oCDropdown3 = this.mParticipantDropdown;
            oCDropdown3.setEventHandler(this.mPanelApp);
            oCDropdown3.setItems(new ArrayList());
            this.mParticipantDropdown.setOnItemClick(new Function() {
                /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantsDropdown$YHV5xZhMSuuPxAQzzbX3KHomk2 */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DraftThreadParticipantsDropdown.this.lambda$show$0$DraftThreadParticipantsDropdown((MessengerContact) obj);
                }
            });
        } else {
            oCDropdown.showAsDropDown(view);
        }
        updateParticipantDropdownItems(str);
    }

    public DraftThreadParticipantsDropdown(Context context, MessengerPanelApp messengerPanelApp) {
        this.mContext = context;
        this.mPanelApp = messengerPanelApp;
        this.mHandler = new Handler(context.getMainLooper());
    }

    public void destroy() {
        clearProfilePictureTargets();
        this.mMessengerContactProfilePictureMap = null;
        this.mProfilePictureTargets = null;
        OCDropdown<MessengerContact> oCDropdown = this.mParticipantDropdown;
        if (oCDropdown != null) {
            oCDropdown.setIconMap(new HashMap());
            this.mParticipantDropdown.setItems(new ArrayList());
            OCDropdown<MessengerContact> oCDropdown2 = this.mParticipantDropdown;
            oCDropdown2.setEventHandler(null);
            oCDropdown2.dismiss();
            this.mParticipantDropdown = null;
        }
    }

    public /* synthetic */ Object lambda$show$0$DraftThreadParticipantsDropdown(MessengerContact messengerContact) {
        return Boolean.valueOf(onClickUserItem(messengerContact));
    }

    public void setOnMessengerContactItemClickListener(Function<MessengerContact, ?> function) {
        this.mOnMessengerContactItemClickListener = function;
    }
}
