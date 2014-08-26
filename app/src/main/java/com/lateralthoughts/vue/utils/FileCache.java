package com.lateralthoughts.vue.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Date;

public class FileCache {

    private File cacheDir;
    private long twoDaysOldTime = 2 * 24 * 60 * 60 * 1000;
    private File mVueAppCameraPicsDir;
    private File mVueUserProfileImageDir;

    public FileCache(Context context) {
        // Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(
            /* android.os.Environment.getExternalStorageDirectory() */
                    context.getExternalCacheDir(), "LazyList");
        } else {
            cacheDir = context.getCacheDir();
			/*mVueAppCameraPicsDir = new File(context.getFilesDir(),
					VueConstants.VUE_APP_CAMERAPICTURES_FOLDER);*/
        }

        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url) {
        int hashCode = url.hashCode();
        String filename = String.valueOf(hashCode);
        File f = new File(cacheDir, filename + ".jpg");
        return f;
    }

    public File getVueAppUserProfilePictureFile(String imageName) {
        File f = new File(mVueUserProfileImageDir, imageName + ".jpg");
        return f;
    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }


}