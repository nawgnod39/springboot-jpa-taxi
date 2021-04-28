package com.example.hammertaxi.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
//데이터 전송 
public class Location {
    public String FROM_LOCATION;
    public String TO_LOCATION;
    public Integer UserKey;
    public Date ReqDTTM;

    public String getFROM_LOCATION() {
        return FROM_LOCATION;
    }

    public String getTO_LOCATION() {
        return TO_LOCATION;
    }

    public Integer getUserKey() {
        return UserKey;
    }

    public Date getReqDTTM() {
        return ReqDTTM;
    }

    public void setFROM_LOCATION(String FROM_LOCATION) {
        this.FROM_LOCATION = FROM_LOCATION;
    }
    

    public void setTO_LOCATION(String TO_LOCATION) {
        this.TO_LOCATION = TO_LOCATION;
    }

    public void setUserKey(Integer userKey) { 
        UserKey = userKey;
    }

    public void setReqDTTM(Date reqDTTM) {
        ReqDTTM = reqDTTM;
    }

    public Location(String FROM_LOCATION, String TO_LOCATION, Integer userKey, Date reqDTTM) {
        this.FROM_LOCATION = FROM_LOCATION;
        this.TO_LOCATION = TO_LOCATION;
        UserKey = userKey;
        ReqDTTM = reqDTTM;
    }
}
