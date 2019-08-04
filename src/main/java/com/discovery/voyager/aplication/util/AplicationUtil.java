package com.discovery.voyager.aplication.util;

import com.discovery.voyager.aplication.constant.ConstantAplication;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean patternCorrect(String text, final String regex) {

        if(ConstantAplication.EMAIL_REGEX.equalsIgnoreCase(regex)){
            text = text.toLowerCase();
        }
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }


}