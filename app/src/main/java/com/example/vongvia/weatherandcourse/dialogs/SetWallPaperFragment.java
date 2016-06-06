package com.example.vongvia.weatherandcourse.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.vongvia.weatherandcourse.R;
import com.example.vongvia.weatherandcourse.utils.AppUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by castl on 2016/5/22.
 */
public class SetWallPaperFragment extends DialogFragment {

    private ImageView iv_paper;


    public interface onSetWallPaperOK {
        void onSetWallPaper();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_set_wallpaper, null);
        iv_paper = (ImageView) view.findViewById(R.id.iv_set_back);


        Picasso.with(getActivity()).load(AppUtils.back_url).fit().centerCrop().into(iv_paper);
        builder.setView(view)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                onSetWallPaperOK listener = (onSetWallPaperOK) getActivity();
                                listener.onSetWallPaper();

                            }

                        }).setNegativeButton("取消", null);
        return builder.create();
    }


}
