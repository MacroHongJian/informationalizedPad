package com.example.administrator.zyzywd.zyzywd;

import java.io.File;

public class ImageTextVertical {
    private String name;
    private int imageId = 0;
    private String bs;
    private File file;

    public ImageTextVertical(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public ImageTextVertical(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public ImageTextVertical(String name, int imageId, String... bs) {
        this.name = name;
        this.imageId = imageId;
        this.bs = bs[0];
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
