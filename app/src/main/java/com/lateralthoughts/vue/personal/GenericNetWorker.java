package com.lateralthoughts.vue.personal;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;


public class GenericNetWorker<T> {
    Class<T> type;
    String TAG = "GenericNetWorker";
    boolean enableLogs = true;

    public GenericNetWorker(Class<T> type) {
        this.type = type;
    }

    public T createObject(T object, String urlString) {
        printLog("createObject entered");
        T resultObject = null;
        HttpURLConnection urlConnection = null;
        try {
            URL urlToRequest = new URL(urlString);
            printLog("url : " + urlString);
            urlConnection =
                    (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type",
                    "application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            String mapperString = mapper.writeValueAsString(object);
            printLog("request String :" + mapperString);
            urlConnection.setFixedLengthStreamingMode(
                    mapperString.getBytes().length);
            DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.writeBytes(mapperString);
            printout.flush();
            printout.close();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                printLog(" Failed Response Code " + statusCode);
            } else {
                InputStream in =
                        new BufferedInputStream(urlConnection.getInputStream());
                String responseMessage = getStringFromInputStream(in);
                printLog("response: " + responseMessage);
                if (responseMessage.length() > 0) {
                    try {
                        resultObject = (new ObjectMapper()).readValue(responseMessage, type);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // handle timeout
            e.printStackTrace();
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                printLog("Connection Disconnected");
            }
        }
        return resultObject;
    }

    public T getObject(String urlString, long id) {
        printLog("getObject entered");
        T retrievedObject = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString + "/" + id);
            printLog("url : " + url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                // throw some exception
                printLog(" Failed Response Code " + statusCode);
            } else {
                InputStream in =
                        new BufferedInputStream(urlConnection.getInputStream());
                String responseMessage = getStringFromInputStream(in);
                printLog("response: " + responseMessage);
                if (responseMessage.length() > 0) {
                    try {
                        retrievedObject = (new ObjectMapper()).readValue(responseMessage, type);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            // handle invalid URL
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // handle timeout
            e.printStackTrace();
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                printLog("Connection Disconnected");
            }
        }
        return retrievedObject;
    }

    public T updateObject(T updatedObject, Object id, String urlString) {
        printLog("updateObject entered");
        T retrievedObject = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString + "/" + id.toString());
            printLog("url : " + url.toString());
            urlConnection =
                    (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type",
                    "application/json;charset=UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            String mapperString = mapper.writeValueAsString(updatedObject);
            printLog("request String :" + mapperString);
            urlConnection.setFixedLengthStreamingMode(
                    mapperString.getBytes().length);
            DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.writeBytes(mapperString);
            printout.flush();
            printout.close();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                // throw some exception
                printLog(" Failed Response Code " + statusCode);
            } else {
                InputStream in =
                        new BufferedInputStream(urlConnection.getInputStream());
                String responseMessage = getStringFromInputStream(in);
                printLog("response: " + responseMessage);
                if (responseMessage.length() > 0) {
                    try {
                        retrievedObject = (new ObjectMapper()).readValue(responseMessage, type);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            // handle invalid URL
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // handle timeout
            e.printStackTrace();
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                printLog("Connection Disconnected");
            }
        }

        return retrievedObject;
    }

    public void deleteObject(long id, String urlString) {
        printLog("deleteObject entered");
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString + "/" + id);
            printLog("url : " + url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                // throw some exception
                printLog("" + statusCode);
            } else {
                InputStream in =
                        new BufferedInputStream(urlConnection.getInputStream());
                String responseMessage = getStringFromInputStream(in);
                printLog("response: " + responseMessage);
            }
        } catch (MalformedURLException e) {
            // handle invalid URL
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // handle timeout
            e.printStackTrace();
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                printLog("Connection Disconnected");
            }
        }
    }

    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    private void printLog(String message) {
        if (enableLogs)
            Log.i(TAG, TAG + " " + message);
    }
}
