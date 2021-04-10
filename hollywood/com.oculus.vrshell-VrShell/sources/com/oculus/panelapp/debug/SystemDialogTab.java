package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonIcon;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListTitle;
import com.oculus.systemdialog.DialogListType;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.systemdialog.DialogVideo;
import com.oculus.vrshell.panels.views.ShellButton;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogVideos;
import java.util.List;
import java.util.Random;

public final class SystemDialogTab extends LinearLayout implements DebugTabHost.DebugTab {
    public SystemDialogTab(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        initializeDialogFlow(shellDebugPanelApp);
        initializeDisabledButtonDialog(shellDebugPanelApp);
        initializeDangerousButtonDialog(shellDebugPanelApp);
        initializeBackButtonDialog(shellDebugPanelApp);
        initializeIconButtonDialog(shellDebugPanelApp);
        initializeInformationBoxDialog(shellDebugPanelApp);
        initializeListDialog(shellDebugPanelApp);
        initializeListSelectionChangeListenerDialog(shellDebugPanelApp);
        initializeVideoDialog(shellDebugPanelApp);
        initializeHeroImageDialog(shellDebugPanelApp);
        initializeScrollToBottomDialog(shellDebugPanelApp);
        initializeProgressBarDialog(shellDebugPanelApp);
        initializeProgressSpinnerDialog(shellDebugPanelApp);
        initializeFullFeatureDialog(shellDebugPanelApp);
        initializeAutoActionButtonDialog(shellDebugPanelApp);
    }

    public void initializeDisabledButtonDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.disabled_button_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass1 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getDisabledButtonDialog());
            }
        });
    }

    public DialogDefinitionCustom getDisabledButtonDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_disabled_button_dialog", "Disabled Button Dialog", "Press the back button to exit.");
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("primary", "Primary").setDisabled());
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("secondary", "Secondary").setDisabled());
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("tertiary", "Tertiary").setDisabled());
        dialogDefinitionCustom.setBackButton(new DialogButton("back"));
        return dialogDefinitionCustom;
    }

    public void initializeDangerousButtonDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.dangerous_button_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass2 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getDangerousButtonDialog());
            }
        });
    }

    public DialogDefinitionCustom getDangerousButtonDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_dangerous_button_dialog", "Dangerous Button Dialog", "This shows the primary button being configured as dangerous.");
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonPrimary("primary", "Primary", DialogPrimaryButtonStyle.DANGER));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("secondary", "Secondary"));
        return dialogDefinitionCustom;
    }

    public void initializeBackButtonDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.back_button_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass3 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getBackButtonDialog());
            }
        });
    }

    public DialogDefinitionCustom getBackButtonDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_back_button_dialog", "Back Button Dialog", "This dialog shows a back button. Click it to close this dialog.");
        dialogDefinitionCustom.setBackButton(new DialogButton("back"));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("cancel", "Cancel"));
        return dialogDefinitionCustom;
    }

    public void initializeIconButtonDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.icon_button_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass4 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getIconButtonDialog(shellDebugPanelApp));
            }
        });
    }

    public DialogDefinitionCustom getIconButtonDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_icon_button_dialog", "Icon Button Dialog", "This dialog shows an icon button. Click it to close this dialog.");
        DialogButtonText dialogButtonText = new DialogButtonText("settings_icon", "Show Settings Icon");
        dialogButtonText.setDoesNotAutoClose();
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        DialogButtonText dialogButtonText2 = new DialogButtonText("info_icon", "Show Info Icon");
        dialogButtonText2.setDoesNotAutoClose();
        dialogDefinitionCustom.setSecondaryButton(dialogButtonText2);
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("cancel", "Cancel"));
        dialogDefinitionCustom.setIconButton(new DialogButtonIcon("cancel", DialogIcon.IconButton.INFO));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass5 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("info_icon")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom.setIconButton(new DialogButtonIcon("cancel", DialogIcon.IconButton.INFO)));
                    return true;
                } else if (dialogAction.equals("settings_icon")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom.setIconButton(new DialogButtonIcon("cancel", DialogIcon.IconButton.SETTINGS)));
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        });
        return dialogDefinitionCustom;
    }

    public void initializeInformationBoxDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.information_box_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass6 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getInformationBoxDialog(shellDebugPanelApp));
            }
        });
    }

    public DialogDefinitionCustom getInformationBoxDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_information_box_dialog", "Information Box Dialog", "This dialog shows an information box. Press the back button to exit.");
        dialogDefinitionCustom.setInformationBox(new DialogInformationBox("Add additional information here...", DialogIcon.InformationBox.INFO));
        DialogButtonText dialogButtonText = new DialogButtonText("loading_icon", "Show Loading Icon");
        dialogButtonText.setDoesNotAutoClose();
        DialogButtonText dialogButtonText2 = new DialogButtonText("check_icon", "Show Check Icon");
        dialogButtonText2.setDoesNotAutoClose();
        DialogButtonText dialogButtonText3 = new DialogButtonText("info_icon", "Show Info Icon");
        dialogButtonText3.setDoesNotAutoClose();
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setSecondaryButton(dialogButtonText2);
        dialogDefinitionCustom.setTertiaryButton(dialogButtonText3);
        dialogDefinitionCustom.setBackButton(new DialogButton("cancel"));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass7 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("loading_icon")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom.setInformationBox(new DialogInformationBox("Add additional information here...", DialogIcon.InformationBox.SPINNER)));
                    return true;
                } else if (dialogAction.equals("check_icon")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom.setInformationBox(new DialogInformationBox("Add additional information here...", DialogIcon.InformationBox.CHECK_ALT)));
                    return true;
                } else if (dialogAction.equals("info_icon")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom.setInformationBox(new DialogInformationBox("Add additional information here...", DialogIcon.InformationBox.INFO)));
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        });
        return dialogDefinitionCustom;
    }

    public void initializeListDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.list_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass8 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getListDialog(shellDebugPanelApp));
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionCustom getListDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        DialogListItemImageType dialogListItemImageType;
        String str;
        String str2;
        String str3;
        DialogListItemImageType dialogListItemImageType2;
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_single_select_list_dialog", "Single Select List Dialog", "This dialog shows a single select list.");
        final DialogDefinitionCustom dialogDefinitionCustom2 = new DialogDefinitionCustom("debug_multi_select_list_dialog", "Multi Select List Dialog", "This dialog shows a multi select list.");
        final DialogDefinitionCustom dialogDefinitionCustom3 = new DialogDefinitionCustom("debug_no_select_list_dialog_with_title", "No Select List Dialog With List Title", "This dialog shows a no select list with a title.");
        DialogList dialogList = new DialogList(DialogListType.SINGLE_SELECT);
        DialogList dialogList2 = new DialogList(DialogListType.MULTI_SELECT);
        DialogList dialogList3 = new DialogList(DialogListType.NO_SELECT);
        dialogList3.setTitle(new DialogListTitle("Available Options...", true));
        for (int i = 0; i < 20; i++) {
            int i2 = i % 3;
            if (i2 == 0) {
                str3 = "Body text " + i;
                dialogListItemImageType2 = DialogListItemImageType.GLYPH;
            } else if (i2 == 1) {
                str3 = "Body text " + i + "\nSecond line...";
                dialogListItemImageType2 = DialogListItemImageType.GLYPH;
            } else {
                str2 = null;
                str = null;
                dialogListItemImageType = null;
                DialogListItem dialogListItem = new DialogListItem("item_id_" + i, "Item Number " + i, str2, str, dialogListItemImageType);
                dialogList.addListItem(dialogListItem);
                dialogList2.addListItem(dialogListItem);
                dialogList3.addListItem(dialogListItem);
            }
            dialogListItemImageType = dialogListItemImageType2;
            str = "apk://com.oculus.systemux/assets/oc_icon_battery_alt_charge_1_24.png";
            str2 = str3;
            DialogListItem dialogListItem2 = new DialogListItem("item_id_" + i, "Item Number " + i, str2, str, dialogListItemImageType);
            dialogList.addListItem(dialogListItem2);
            dialogList2.addListItem(dialogListItem2);
            dialogList3.addListItem(dialogListItem2);
        }
        dialogDefinitionCustom.setList(dialogList);
        dialogDefinitionCustom2.setList(dialogList2);
        dialogDefinitionCustom3.setList(dialogList3);
        DialogButtonText dialogButtonText = new DialogButtonText("multi", "Show Multi Select");
        DialogButtonText dialogButtonText2 = new DialogButtonText("single", "Show Single Select");
        DialogButtonText dialogButtonText3 = new DialogButtonText("noSelect", "Show No Select With Title");
        DialogButtonText dialogButtonText4 = new DialogButtonText("cancel", "Cancel");
        dialogButtonText.setDoesNotAutoClose();
        dialogButtonText2.setDoesNotAutoClose();
        dialogButtonText3.setDoesNotAutoClose();
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setSecondaryButton(dialogButtonText3);
        dialogDefinitionCustom.setTertiaryButton(dialogButtonText4);
        dialogDefinitionCustom2.setPrimaryButton(dialogButtonText2);
        dialogDefinitionCustom2.setSecondaryButton(dialogButtonText3);
        dialogDefinitionCustom2.setTertiaryButton(dialogButtonText4);
        dialogDefinitionCustom3.setPrimaryButton(dialogButtonText2);
        dialogDefinitionCustom3.setSecondaryButton(dialogButtonText);
        dialogDefinitionCustom3.setTertiaryButton(dialogButtonText4);
        AnonymousClass9 r9 = new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass9 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("single")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
                    return true;
                } else if (dialogAction.equals("multi")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom2);
                    return true;
                } else if (dialogAction.equals("noSelect")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom3);
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        };
        dialogDefinitionCustom.setDialogResultHandler(r9);
        dialogDefinitionCustom2.setDialogResultHandler(r9);
        dialogDefinitionCustom3.setDialogResultHandler(r9);
        return dialogDefinitionCustom;
    }

    public void initializeListSelectionChangeListenerDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.list_selection_change_listener_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass10 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getListSelectionChangeListenerDialog(shellDebugPanelApp));
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionCustom getListSelectionChangeListenerDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_list_selection_change_listener_dialog", "List Selection Change Listener Dialog", "This dialog updates its UI based on the list selection.");
        DialogList dialogList = new DialogList(DialogListType.SINGLE_SELECT);
        for (int i = 0; i < 20; i++) {
            dialogList.addListItem(new DialogListItem("item_id_" + i, "Item Number " + i, "Body text " + i, null, null));
        }
        dialogList.setShouldSendSelectionChangeAction(true);
        dialogDefinitionCustom.setList(dialogList);
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", "Cancel"));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass11 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String str;
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals(DialogList.DIALOG_LIST_SELECTION_CHANGE_ACTION)) {
                    List<String> dialogSelectedListItemIds = dialogResult.getDialogSelectedListItemIds();
                    DialogDefinitionCustom dialogDefinitionCustom = dialogDefinitionCustom;
                    if (dialogSelectedListItemIds.isEmpty()) {
                        str = "No selection is made.";
                    } else {
                        str = "Current selection is: " + dialogSelectedListItemIds.get(0);
                    }
                    dialogDefinitionCustom.setBody(str);
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        });
        return dialogDefinitionCustom;
    }

    public void initializeVideoDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.video_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass12 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getVideoDialog(shellDebugPanelApp));
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionCustom getVideoDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_video_dialog_1", "Video Dialog 1", "This dialog shows a video.");
        final DialogDefinitionCustom dialogDefinitionCustom2 = new DialogDefinitionCustom("debug_video_dialog_2", "Video Dialog 2", "This dialog shows a video. This is a longer description so that the video has a different position on the dialog.");
        DialogVideo dialogVideo = new DialogVideo(GuardianDialogVideos.CONTROLLER, 1.65f, "0xFF1A1A1A");
        DialogVideo dialogVideo2 = new DialogVideo(GuardianDialogVideos.FLOOR_FINDER, 1.65f, "0xFF1A1A1A");
        dialogDefinitionCustom.setVideo(dialogVideo);
        dialogDefinitionCustom2.setVideo(dialogVideo2);
        DialogButtonText dialogButtonText = new DialogButtonText("video1", "Show Video 1");
        dialogButtonText.setDoesNotAutoClose();
        DialogButtonText dialogButtonText2 = new DialogButtonText("video2", "Show Video 2");
        dialogButtonText2.setDoesNotAutoClose();
        DialogButtonText dialogButtonText3 = new DialogButtonText("cancel", "Cancel");
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText2);
        dialogDefinitionCustom.setSecondaryButton(dialogButtonText3);
        dialogDefinitionCustom2.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom2.setSecondaryButton(dialogButtonText3);
        AnonymousClass13 r2 = new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass13 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("video1")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
                    return true;
                } else if (dialogAction.equals("video2")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom2);
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        };
        dialogDefinitionCustom.setDialogResultHandler(r2);
        dialogDefinitionCustom2.setDialogResultHandler(r2);
        return dialogDefinitionCustom;
    }

    public void initializeHeroImageDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.hero_image_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass14 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getHeroImageDialog(shellDebugPanelApp));
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DialogDefinitionCustom getHeroImageDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_hero_image_dialog", "Hero Image Dialog", "This dialog shows a hero image.");
        dialogDefinitionCustom.setHeroImage(new DialogHeroImage("apk://com.oculus.vrshell.home/assets/ota_clear_space_743x418.ktx", 1.778f, "0xFF1A1A1A"));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", "Cancel"));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass15 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                if (!dialogResult.getDialogAction().equals("cancel")) {
                    return false;
                }
                shellDebugPanelApp.getDialogManager().hideDialog();
                return true;
            }
        });
        return dialogDefinitionCustom;
    }

    public void initializeScrollToBottomDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.scroll_to_bottom_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass16 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getScrollToBottomDialog());
            }
        });
    }

    public DialogDefinitionCustom getScrollToBottomDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_scroll_to_bottom_dialog", "Scroll To Bottom Dialog", "This dialog requires the user to scroll to the bottom of the body text before being able to proceed. Scroll down... Here is some additional text to ensure the scroll bar does not overlap any text a a a a a    a a a a a a a a a a a   a a a a a a  a a a a a a a a a a  a a a a  a a a a a a a a a a a a a  a a a a a a a a a a a a a  a a a a a a a a a a            a a a a a a a a a a a a a a a  a a a  a a a a a a    a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a                          d d d d d d d d d \n\n\n\n\n\n\n\n\n\nKeep scrolling...\n\n\n\n\n\n\n\n\n\nKeep scrolling...\n\n\n\n\n\n\n\n\n\nKeep scrolling...\n\n\n\n\n\n\n\n\n\nKeep scrolling...\n\n\n\n\n\n\n\n\n\nHere is the bottom. The primary button should now be clickable.");
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("proceed", "Proceed").setDisabledUntilScrolledToBottom());
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", "Cancel"));
        return dialogDefinitionCustom;
    }

    public void initializeProgressBarDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.progress_bar_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass17 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getProgressBarDialog(shellDebugPanelApp));
            }
        });
    }

    public DialogDefinitionCustom getProgressBarDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_progress_bar_dialog", "Progress Bar Dialog", "This dialog shows a progress bar.");
        DialogButtonText dialogButtonText = new DialogButtonText("update_progress", "Update Progress");
        dialogButtonText.setDoesNotAutoClose();
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("cancel", "Cancel"));
        dialogDefinitionCustom.setProgressBar(0.0f);
        final Random random = new Random();
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass18 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("update_progress")) {
                    dialogDefinitionCustom.setProgressBar(random.nextFloat());
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        });
        return dialogDefinitionCustom;
    }

    public void initializeProgressSpinnerDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.progress_spinner_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass19 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getProgressSpinnerDialog(shellDebugPanelApp));
            }
        });
    }

    public DialogDefinitionCustom getProgressSpinnerDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        final DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_progress_spinner_dialog", "Progress Spinner Dialog", "This dialog shows a progress spinner.");
        DialogButtonText dialogButtonText = new DialogButtonText("long", "Show Long Title");
        dialogButtonText.setDoesNotAutoClose();
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        DialogButtonText dialogButtonText2 = new DialogButtonText("short", "Show Short Title");
        dialogButtonText2.setDoesNotAutoClose();
        dialogDefinitionCustom.setSecondaryButton(dialogButtonText2);
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("cancel", "Cancel"));
        dialogDefinitionCustom.setProgressSpinner();
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass20 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("long")) {
                    dialogDefinitionCustom.setTitle("Progress Spinner Dialog with Long Title to show Multi Line Behavior");
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
                    return true;
                } else if (dialogAction.equals("short")) {
                    dialogDefinitionCustom.setTitle("Progress Spinner Dialog");
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
                    return true;
                } else if (!dialogAction.equals("cancel")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                }
            }
        });
        return dialogDefinitionCustom;
    }

    public void initializeFullFeatureDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.full_feature_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass21 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getFullFeatureDialog());
            }
        });
    }

    public DialogDefinitionCustom getFullFeatureDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_full_feature_dialog", "Full Feature Dialog", "This <b><b>dialog <a>shows</a></b> most</b> available UI features.<br/>New line here.<p>paragraph <b>stuff</b> and a <a href=\"action\">link</a> that closes the dialog.</p>Here is more text.<ul> <li>Test list item</li><li>Next <b>list </b>item is empty.</li><li></li><li><a href=\"action\">The final item is another link that closes the dialog</a></li></ul>");
        dialogDefinitionCustom.setInformationBox(new DialogInformationBox("Add additional information here...", DialogIcon.InformationBox.INFO));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("primary", "Primary Button"));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("secondary", "Secondary Button").setDisabled(true));
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("tertiary", "Tertiary Button"));
        dialogDefinitionCustom.setIconButton(new DialogButtonIcon("cancel", DialogIcon.IconButton.INFO));
        dialogDefinitionCustom.setBackButton(new DialogButton("back"));
        dialogDefinitionCustom.setProgressSpinner();
        return dialogDefinitionCustom;
    }

    public void initializeDialogFlow(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.dialog_flow_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass22 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getDialogFlowDialog(shellDebugPanelApp));
            }
        });
    }

    public DialogDefinitionCustom getDialogFlowDialog(ShellDebugPanelApp shellDebugPanelApp) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("debug_step1_dialog", "Step 1", "You are on step 1. Press on the buttons below to continue navigating through the dialog flow...");
        DialogDefinitionCustom dialogDefinitionCustom2 = new DialogDefinitionCustom("debug_step2_dialog", "Step 2", "You are on step 2.");
        DialogDefinitionCustom dialogDefinitionCustom3 = new DialogDefinitionCustom("debug_step3_dialog", "Step 3", "You are on step 3. Only one more step to go in the dialog flow.");
        DialogDefinitionCustom dialogDefinitionCustom4 = new DialogDefinitionCustom("debug_step4_dialog", "Step 4", "You have completed the dialog flow.");
        setupDialog(shellDebugPanelApp, dialogDefinitionCustom, null, dialogDefinitionCustom2);
        setupDialog(shellDebugPanelApp, dialogDefinitionCustom2, dialogDefinitionCustom, dialogDefinitionCustom3);
        setupDialog(shellDebugPanelApp, dialogDefinitionCustom3, dialogDefinitionCustom2, dialogDefinitionCustom4);
        setupDialog(shellDebugPanelApp, dialogDefinitionCustom4, dialogDefinitionCustom3, null);
        return dialogDefinitionCustom;
    }

    public void initializeAutoActionButtonDialog(final ShellDebugPanelApp shellDebugPanelApp) {
        ((ShellButton) findViewById(R.id.auto_action_dialog_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass23 */

            public void onClick(View view) {
                shellDebugPanelApp.getDialogManager().showDialog(SystemDialogTab.this.getAutoActionButtonDialog());
            }
        });
    }

    public DialogDefinitionCustom getAutoActionButtonDialog() {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("auto_action_dialog", "Auto-Action Dialog", "This dialog triggers a delayed auto-action after it is shown. The auto-action in this case is close. This dialog will close 6000 milliseconds after being displayed.");
        dialogDefinitionCustom.setAutoAction("close", 6000);
        return dialogDefinitionCustom;
    }

    private void setupDialog(final ShellDebugPanelApp shellDebugPanelApp, DialogDefinitionCustom dialogDefinitionCustom, final DialogDefinitionCustom dialogDefinitionCustom2, final DialogDefinitionCustom dialogDefinitionCustom3) {
        dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("close", "Close"));
        if (dialogDefinitionCustom2 != null) {
            DialogButtonText dialogButtonText = new DialogButtonText("back", "Back");
            dialogButtonText.setDoesNotAutoClose();
            dialogDefinitionCustom.setSecondaryButton(dialogButtonText);
        }
        if (dialogDefinitionCustom3 != null) {
            DialogButtonText dialogButtonText2 = new DialogButtonText("next", "Next");
            dialogButtonText2.setDoesNotAutoClose();
            dialogDefinitionCustom.setPrimaryButton(dialogButtonText2);
        }
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.debug.SystemDialogTab.AnonymousClass24 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public boolean handleDialogResult(DialogResult dialogResult) {
                String dialogAction = dialogResult.getDialogAction();
                if (dialogAction.equals("close")) {
                    shellDebugPanelApp.getDialogManager().hideDialog();
                    return true;
                } else if (dialogDefinitionCustom3 != null && dialogAction.equals("next")) {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom3);
                    return true;
                } else if (dialogDefinitionCustom2 == null || !dialogAction.equals("back")) {
                    return false;
                } else {
                    shellDebugPanelApp.getDialogManager().showDialog(dialogDefinitionCustom2);
                    return true;
                }
            }
        });
    }
}
