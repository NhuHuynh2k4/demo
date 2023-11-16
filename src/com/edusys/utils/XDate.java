/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author ADMIN
 */
public class XDate {
    static  SimpleDateFormat formater = new SimpleDateFormat();
    
    public  static Date toDate(String date , String pattern){
      try{
              formater.applyPattern(pattern);
              return formater.parse(date);
      }
      catch(ParseException ex){
          throw new RuntimeException(ex);
      }
    }
    
    public  static String toString(Date date,String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
        
    }
    public static Date addDays(Date date, long days){
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
    public static Date now() {
        return new Date(); //new Date lấy giờ hiện tại
    }
}
