package org.viettel.ocs.rulemgt.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UserUtil {
    
    private UserUtil() {}

    public static String getUserName(HttpServletRequest request){
        // return request.getHeader("username") == null ?
        //         AlarmConst.ADMIN : request.getHeader("username");
        return request.getHeader("username");
    }
}
