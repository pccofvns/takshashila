package com.pccofvns.ts.utils;

import java.time.*;
import java.util.*;

public class DateUtils {

    public static Date toDate(LocalDateTime ldt){
        if(ldt == null){
            return null;
        }
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

}
