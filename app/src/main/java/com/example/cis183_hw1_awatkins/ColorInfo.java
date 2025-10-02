package com.example.cis183_hw1_awatkins;

public class ColorInfo
{
    private int red;
    private int blu;
    private int grn;
    private String hex;
    private boolean textColor;  //true black, false white

    public ColorInfo(int red, int grn, int blu, String hex) {
        this.red = red;
        this.hex = hex;
        this.grn = grn;
        this.blu = blu;
        if(red <= 128 && blu <= 128 && grn <= 128) {textColor = false;} else {textColor = true;}
    }
    public boolean getTextColor() {return textColor;}
    public void setRed(String red) {red = red;}
    public void setGrn(String grn) {grn = grn;}
    public void setBlu(String blu) {blu = blu;}
    public void setHex(String hex) {hex = hex;}
    public int getRed() {return red;}
    public int getBlu() {return blu;}
    public int getGrn() {return grn;}
    public String getHex() {return hex;}
}
