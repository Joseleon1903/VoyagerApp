package com.discovery.voyager.aplication.constant;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class UtilityAplication{

    public static Pageable createPageableDefault(){
        return new PageRequest(0, 10);
    }

    public static Pageable createPageableCustom(int page, int rows){
        return new PageRequest(page, rows);
    }

}