package com.discovery.voyager.aplication.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AplicationUtil {

    private AplicationUtil(){}

    /**
     *  return true if Strinf is null or is empty
     * 
     * @param text
     * @return boolean 
     */
    public static boolean isStringNullOrEmpty(String text){
        if(text != null && !text.trim().isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 
     * @param beginDate
     * @return
     */
    public static String timePastFromDate(Date beginDate){
        Date currentTime =  new Date();
        long diff = currentTime.getTime() - beginDate.getTime();//as given
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff); 
        if(minutes <=0){
            return "";
        }
        return minutes+" minutes ago";
    }


}