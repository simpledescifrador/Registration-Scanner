package com.esys.registrationscanner.utils;

import android.app.Activity;
import android.graphics.Color;

import com.esys.registrationscanner.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import javax.inject.Inject;

public class AppAlertDialog {
    public enum AlertType {
        INFO, SUCCESS, WARNING, DANGER
    }

    private Activity mActivity;
    private MaterialAlertDialogBuilder mMaterialAlertDialogBuilder;

    @Inject
    public AppAlertDialog(final Activity activity) {
        mActivity = activity;
    }


    /**
     * Show Material Alert Dialog
     */
    public MaterialAlertDialogBuilder showMaterialDialog(String title, String message) {
        mMaterialAlertDialogBuilder = new MaterialAlertDialogBuilder(mActivity);
        mMaterialAlertDialogBuilder.setTitle(title)
                .setMessage(message);
        return mMaterialAlertDialogBuilder;
    }

    //FIX: Not Working error gradient color bg
    public FancyAlertDialog.Builder showAlertDialog(AlertType alertType, String title, String message, String positiveText,
                                                           String negativeText) {
        FancyAlertDialog.Builder alertdialogBuilder = new FancyAlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(message)
                .setAnimation(Animation.POP);

        switch (alertType) {
            case INFO:
                alertdialogBuilder.setBackgroundColor(Color.parseColor("#17a2b8"));
                alertdialogBuilder.setIcon(R.drawable.ic_info_outline_white_24dp, Icon.Visible);
                alertdialogBuilder.setPositiveBtnBackground(Color.parseColor("#17a2b8"));

                break;
            case WARNING:
                alertdialogBuilder.setBackgroundColor(Color.parseColor("#ffc107"));
                alertdialogBuilder.setIcon(R.drawable.ic_warning_white_24dp, Icon.Visible);
                alertdialogBuilder.setPositiveBtnBackground(Color.parseColor("#ffc107"));

                break;
            case SUCCESS:
                alertdialogBuilder.setBackgroundColor(Color.parseColor("#28a745"));
                alertdialogBuilder.setIcon(R.drawable.ic_check_white_24dp,Icon.Visible);
                alertdialogBuilder.setPositiveBtnBackground(Color.parseColor("#28a745"));

                break;
            case DANGER:
                alertdialogBuilder.setBackgroundColor(Color.parseColor("#dc3545"));
                alertdialogBuilder.setIcon(R.drawable.ic_error_white_24dp, Icon.Visible);
                alertdialogBuilder.setPositiveBtnBackground(Color.parseColor("#dc3545"));

                break;
        }

        alertdialogBuilder.setPositiveBtnText(positiveText);
        alertdialogBuilder.setNegativeBtnText(negativeText);
        return alertdialogBuilder;
    }
}
