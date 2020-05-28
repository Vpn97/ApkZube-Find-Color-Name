package com.apkzube.apkzube_findcolorname.model;

/**
 * Color Detection
 * Created by Vishal Nagvadiya on 24-05-2020.
 */

public class ColorBean {
    private String colorName;
    private String hexCode;
    private int r;
    private  int g;
    private  int b;
    private  float h;
    private  float s;
    private  float l;

    public ColorBean(String colorName, String hexCode, int r, int g, int b, int h, int s, int l) {
        this.colorName = colorName;
        this.hexCode = hexCode;
        this.r = r;
        this.g = g;
        this.b = b;
        this.h = h;
        this.s = s;
        this.l = l;
    }

    public ColorBean(String colorName, String hexCode) {
        this.colorName = colorName;
        this.hexCode = hexCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getS() {
        return s;
    }

    public void setS(float s) {
        this.s = s;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }
}
