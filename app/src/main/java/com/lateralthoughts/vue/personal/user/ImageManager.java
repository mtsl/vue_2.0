package com.lateralthoughts.vue.personal.user;

import android.util.Log;


import com.lateralthoughts.vue.utils.UrlConstants;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageManager {
    String TAG = "ImageManager";

    /**
     * download the image and save it in the sd card
     */
    public File saveFile(File file, String profileUrl) throws Exception {
        URL url = new URL(profileUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        FileOutputStream fileOutput = new FileOutputStream(file);
        InputStream inputStream = urlConnection.getInputStream();
        int totalSize = urlConnection.getContentLength();
        int downloadedSize = 0;
        byte[] buffer = new byte[1024];
        int bufferLength = 0;
        while ((bufferLength = inputStream.read(buffer)) > 0) {
            fileOutput.write(buffer, 0, bufferLength);
            downloadedSize += bufferLength;
        }
        fileOutput.close();
        return file;

    }

    /**
     * get the image from sd card and upload to the server. Server will return a key which we will use
     * in the user creation request.
     */
    public String uploadImage(File image) throws Exception {
        /** Try to POST the image to the server */
        String uniqueUploadUrl =
                getImageUploadURL();
        Assert.assertNotNull(uniqueUploadUrl);
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        HttpPost httppost = new HttpPost(uniqueUploadUrl);
        /** Request parameters and other properties. */
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("myFile", new FileBody(image));
        httppost.setEntity(reqEntity);
        /** Execute and get the response. */
        HttpResponse response = httpclient.execute(httppost);
        if (response.getEntity() != null &&
                response.getStatusLine().getStatusCode() == 200) {
            String responseMessage = EntityUtils.toString(response.getEntity());
            Log.i(TAG, TAG + " user creation task image upload successfully: ");
            return responseMessage;
        } else {
            Log.i(TAG, TAG + " user creation task image upload failed: ");
            return null;
        }
    }

    private String getImageUploadURL()
            throws Exception {
        URL url = new URL(UrlConstants.GET_UNIQUE_ONETIME_IMAGE_UPLOAD_RESTURL);
        HttpGet httpGet = new HttpGet(url.toString());
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getEntity() != null &&
                response.getStatusLine().getStatusCode() == 200) {
            String responseMessage = EntityUtils.toString(response.getEntity());
            return responseMessage;
        }
        return null;
    }

    public String getServingURLUsingImageKey(String imageKey) {
        return (UrlConstants.SERVER_PREFIX + "upload" + "?blob_key=" + imageKey);
    }
}
