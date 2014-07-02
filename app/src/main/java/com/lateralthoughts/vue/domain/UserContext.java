package com.lateralthoughts.vue.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class UserContext {

    public static class LocationInfo {
        @JsonProperty("timeStamp")
        public String _timeStamp;

        @JsonProperty("latitude")
        public String _latitude;

        @JsonProperty("longitude")
        private String _longitude;

        public String getTimeStamp() {
            return _timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            _timeStamp = timeStamp;
        }

        public String getLatitude() {
            return _latitude;
        }

        public String getLongitude() {
            return _longitude;
        }

        public void setLatitude(String s) {
            _latitude = s;
        }

        public void setLongitude(String s) {
            _longitude = s;
        }
    }

    @JsonProperty("UserId")
    private String _userId;

    @JsonProperty("Locations")
    private ArrayList<LocationInfo> _locations;

    public void addLocationInfo(LocationInfo info) {
        if(null == _locations) {
            _locations = new ArrayList<LocationInfo>();
        }
        _locations.add(_locations.size(), info);
    }

    @JsonProperty("InstalledApps")
    private ArrayList<String> _installedApps = new ArrayList<String>();
    public void addInstalledApp(String app) {
        _installedApps.add(_installedApps.size(), app);
    }
}
