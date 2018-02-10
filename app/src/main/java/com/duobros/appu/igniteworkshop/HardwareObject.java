package com.duobros.appu.igniteworkshop;

/**
 * Created by MY LAPTOP on 2/9/2018.
 */

class HardwareObject {
    private String hardware;
    private String hardwareMeaning;
    public HardwareObject(String hardware, String hardwareMeaning) {
        this.hardware = hardware;
        this.hardwareMeaning = hardwareMeaning;
    }
    public String getHardware() {
        return hardware;
    }
    public String getHardwareMeaning() {
        return hardwareMeaning;
    }
}
