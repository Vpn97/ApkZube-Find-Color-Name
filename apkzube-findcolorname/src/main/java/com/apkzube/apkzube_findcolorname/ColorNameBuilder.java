package com.apkzube.apkzube_findcolorname;

import android.graphics.Color;
import com.apkzube.apkzube_findcolorname.model.ColorBean;
import com.apkzube.apkzube_findcolorname.util.ColorNameCollection;
import com.apkzube.apkzube_findcolorname.validator.HexValidator;

import java.util.ArrayList;

public class ColorNameBuilder {

    public static String getColorName(String hexCode) {
        HexValidator validator = new HexValidator();
        if (validator.validateHex(hexCode)) {
            return getColorName(ColorNameCollection.colorList, hexCode);
        }
        return "Invalid Hex Code";
    }


    private static ArrayList<ColorBean> initColor() {
        return ColorNameCollection.colorList;
    }


    private static int[] getHSLFromRGB(String colorHex) {
        float min, max, delta, h, s, l;

        float r = Color.red(Color.parseColor(colorHex)) / 255f;
        float g = Color.green(Color.parseColor(colorHex)) / 255f;
        float b = Color.blue(Color.parseColor(colorHex)) / 255f;

        min = Math.min(r, Math.min(g, b));
        max = Math.max(r, Math.max(g, b));
        delta = max - min;
        l = (min + max) / 2;

        s = 0;
        if (l > 0 && l < 1) {
            s = delta / (l < 0.5 ? (2 * l) : (2 - 2 * l));
        }

        h = 0;
        if (delta > 0) {
            if (max == r && max != g) h += (g - b) / delta;
            if (max == g && max != b) h += (2 + (b - r) / delta);
            if (max == b && max != r) h += (4 + (r - g) / delta);
            h /= 6;
        }

        int[] arr = new int[3];
        arr[0] = (int) (h * 255);
        arr[1] = (int) (s * 255);
        arr[2] = (int) (l * 255);
        return arr;
    }

    private static String getColorName(ArrayList<ColorBean> colorList, String color) {
        String colorName = "";

        color.toUpperCase();
        if (color.length() < 3 || color.length() > 7) {
            return "Invalid Hex Code";
        }
        if (color.length() % 3 == 0) {
            color = "#" + color;
        }
        if (color.length() == 4) {
            color = "#" + color.substring(1, 1) + color.substring(1, 1) + color.substring(2, 1) + color.substring(2, 1) +
                    color.substring(3, 1) + color.substring(3, 1);
        }

        float ndf1 = 0, ndf2 = 0, ndf = 0;
        int cl = -1;
        float df = -1;

        int r = Color.red(Color.parseColor(color));
        int g = Color.green(Color.parseColor(color));
        int b = Color.blue(Color.parseColor(color));

        int arr[] = getHSLFromRGB(color);

        int h = arr[0];
        int s = arr[1];
        int l = arr[2];

        ColorBean colorBean;
        for (int i = 0; i < colorList.size(); i++) {

            colorBean = colorList.get(i);

            if (color == "#" + colorBean.getHexCode()) {
                return colorBean.getColorName();
            }

            ndf1 = (float) (Math.pow(r - colorList.get(i).getR(), 2) +
                    Math.pow(g - colorList.get(i).getG(), 2) +
                    Math.pow(b - colorList.get(i).getB(), 2));

            ndf2 = (float) (Math.pow(h - colorList.get(i).getH(), 2)
                    + Math.pow(s - colorList.get(i).getS(), 2) +
                    Math.pow(l - colorList.get(i).getL(), 2));
            ndf = ndf1 + ndf2 * 2;

            if (df < 0 || df > ndf) {
                df = ndf;
                cl = i;
            }
        }

        if (cl < 0) {
            return "Invalid Hex Code";
        } else {
            colorName = colorList.get(cl).getColorName();
        }

        return colorName;
    }


}
