package com.envoss.tamansurga.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.envoss.tamansurga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public static final String TAG = "X-LOG";
    private static final boolean DEBUG_MODE = true;

    public static void toastErr(Context context, String msg){
        Toast.makeText(context, "Something Wrong, " + msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastInfo(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static class Baseprogress{
        private static ProgressDialog mProgressDialog;

        public static void showProgressDialog(Context context, String msg) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setCancelable(false);
            }

            mProgressDialog.setMessage(msg);
            mProgressDialog.show();
        }

        public static void setMessage(String msg){
            mProgressDialog.setMessage(msg);
        }

        public static void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }
    }

}
