package com.springWebApp.springWebApp;

public class Alien {

    public int num1;
    public String num2;

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "num1=" + num1 +
                ", num2='" + num2 + '\'' +
                '}';
    }
}
