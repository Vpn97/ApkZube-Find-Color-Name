package com.apkzube.colornamedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.apkzube.apkzube_findcolorname.ColorNameBuilder;
import com.apkzube.apkzube_findcolorname.exception.InvalidHexCodeException;
import com.apkzube.colornamedemo.databinding.ActivityMainBinding;

import org.json.JSONException;

import java.io.IOException;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    int defalultColor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        allocation();
        setEvent();
    }

    private void allocation() {
        mBinding.txtHexCode.setText("#000000");
        defalultColor = Color.rgb(15, 15, 15);

    }

    private void setEvent() {
        mBinding.btnGetColorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mBinding.txtColorName.setText(ColorNameBuilder.getColorName(mBinding.txtHexCode.getText().toString())); //lib call
                    mBinding.txtColorName.setTextColor(Color.parseColor(mBinding.txtHexCode.getText().toString()));
                } catch (Exception  e) {
                    mBinding.txtColorName.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        mBinding.btnGetColorName.performClick();

        mBinding.btnPickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorDialog(true);
            }
        });
    }

    private void openColorDialog(boolean b) {


        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defalultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                //Toast.makeText(this, "Color Dialog is closed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defalultColor=color;
                mBinding.txtHexCode.setText("#" + Integer.toHexString(color).substring(2));
                mBinding.btnGetColorName.performClick();
            }
        });
        ambilWarnaDialog.show();
    }

}
