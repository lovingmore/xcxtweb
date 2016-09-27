/**
 * 
 */
package com.sh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * @author liruji
 *
 */
public class DateUtil {

	public static final String DATE_PATTON_1 = "yyyy-MM-dd";
	public static final String DATE_PATTON_2 = "yyyy/MM/dd";
	public static final String DATE_PATTON_3 = "yyyyMMdd";
	public static final String DATE_TIME_PATTON_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_PATTON_2 = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_TIME_PATTON_3 = "yyyyMMddHHmmss";
    public static SimpleDateFormat TimeFormatlingdian = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    
    public static Date formatToTradeEndTime(Date datetime) {
        try {
            return TimeFormatlingdian.parse(TimeFormatlingdian.format(datetime));
        } catch (ParseException e) {
            return null;
        }
    }

	/**
	 * 将字符串转换成日期，不包含时分秒
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTON_1);
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串根据具体格式转成日期
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date strToDate(String str, String format){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转换成日期，包含时分秒
	 * @param str
	 * @return
	 */
	public static Date strToDateTime(String str){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTON_1);
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 日期装成字符串，不包含时分秒
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date){
		String str = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTON_1);
			str = sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 日期根据具体时间格式转换成字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format){
		String str = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			str = sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 时间转换成字符串，包含时分秒
	 * @param date
	 * @return
	 */
	public static String datetimeToStr(Date date){
		String str = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTON_1);
			str = sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	
	/**  
     * 得到几天后的日期 
     */          
    public static Date getDateAfter(Date d, int days) {  
    	try{
    		
	        Calendar now = Calendar.getInstance();    
	        now.setTime(d);    
	        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);    
	        return now.getTime();    
    	}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    } 
    
    /**  
     * 得到几个月后的日期 （不包含当天）
     */          
    public static Date getDateAfterMonth(Date d, int month) {  
    	try{
    		
	        Calendar now = Calendar.getInstance();    
	        now.setTime(d);    
	        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month); 
	        now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 1 );
	        return now.getTime();    
    	}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
    
    /**  
     * 得到几天前的日期 
     */          
    public static Date getDateBefore(Date d, int days) {   
    	try{
	    	Calendar now = Calendar.getInstance();    
	    	now.setTime(d);    
	    	now.set(Calendar.DATE, now.get(Calendar.DATE) - days);    
	    	return now.getTime();    
    	}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 获得几小时后的日期
     * @param date
     * @param hour
     * @return
     */
    public static Date getHourAfter(Date date, int hours){
    	try{
	    	Calendar now = Calendar.getInstance();    
	        now.setTime(date);    
	        now.set(Calendar.HOUR, now.get(Calendar.HOUR)+hours);    
	        return now.getTime();
    	}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 获得N分钟后的日期
     * @param date
     * @param hour
     * @return
     */
	public static Date getMinuteAfter(Date date, int minutes){
		try{
			Calendar now = Calendar.getInstance();    
		    now.setTime(date);    
		    now.set(Calendar.MINUTE, now.get(Calendar.MINUTE)+minutes);
		    return now.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * 获得N秒后的日期
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date getSecondAfter(Date date, int seconds){
		try{
			Calendar now = Calendar.getInstance();    
			now.setTime(date); 
			now.set(Calendar.SECOND, now.get(Calendar.SECOND) + seconds);
			return now.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * 获得N毫秒后的日期
     * @param date
     * @param hour
     * @return
     */
	public static Date getMillisecondAfter(Date date, int milliseconds){
		try{
			
			Calendar now = Calendar.getInstance();    
			now.setTime(date); 
			now.set(Calendar.MILLISECOND, now.get(Calendar.MILLISECOND) + milliseconds);
			return now.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * 获取转换某一天的具体时间点
	 * @param date
	 * @param hour
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	public static Date getDateToDateTime(Date date, int hour, int minutes, int seconds){
		
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, minutes);
			calendar.set(Calendar.SECOND, seconds);
			return calendar.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    
    /**
     * 判断是否为同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2){
    	try{
    		
    		if(date1 == null || date2 == null){
    			return false;
    		}else{
    			return dateToStr(date1).equals(dateToStr(date2));
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
	}
    /**
     * 获取2个时间时间差秒
     * @param d1
     * @param d2
     * @return
     */
    public static long  getdhmsc(Date d1,Date d2,String Type){
    	
    	long number = 0;
    	if(d1==null||d2==null){
    		return 0;
    	}
        try {
            long diff = (d2.getTime() - d1.getTime())/1000;
            if(Type.equals("s")){//秒
            	number = diff ;
            }else  if(Type.equals("m")){//分钟
            	number = diff / 60;
            }else  if(Type.equals("h")){//小时
            	number = diff / (60 * 60) ;
            }else  if(Type.equals("d")){//天
            	number = diff / (24 * 60 * 60);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	 return number;
        }
        return number;
    }
    
    /**
     * 获取当周的周一(周日算当周最后一天来算)
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
    	if (dayofweek == 0)
    		dayofweek = 7;
    	c.add(Calendar.DATE, -dayofweek + 1);
    	return c.getTime();
	}
    
    /**
     * 判断是否在这周之前，周一作为一周开始
     * @param date需要比较的日期
     * @return
     */
    public static boolean isBeforeCurrentWeek(Date date){
    	if(date == null) return false;
    	Date monday = getMondayOfWeek(new Date());
    	return monday.after(date);
    }
    
    public static void main(String[] args) throws ParseException {
//    	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
//    	   java.util.Date begin;
//		   begin = dfs.parse("2004-01-02 11:30:24");
//	  
//    	   java.util.Date end = dfs.parse("2004-03-26 13:31:40");   
//    	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒   
//    	   long day1=between/(24*3600);   
//    	   long hour1=between%(24*3600)/3600;   
//    	   long minute1=between%3600/60;   
//    	   long second1=between%60/60;   
//    	   System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒"); 
    	
    	Date date = strToDateTime("2015-10-26 15:07:01");
    	/*System.out.println(dateToStr(getMondayOfWeek(date)));
    	System.out.println(isBeforeCurrentWeek(date));*/
    	System.out.println(dateTimeToDate(date));
	}
    /**
     * 获取日期当前月份的最后一天(不包含时分秒)
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date){
    	Calendar c = Calendar.getInstance();
    	Date sDate = strToDate(dateToStr(date));
    	c.setTime(sDate);
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    	c.set(Calendar.HOUR_OF_DAY, 23);
    	c.set(Calendar.MINUTE, 59);
    	c.set(Calendar.SECOND, 59);
    	return c.getTime();
    }
    
    public static int getMonths(Date date1, Date date2){
    	int iMonth = 0;     
    	int flag = 0;     
    	try{     
    	    Calendar objCalendarDate1 = Calendar.getInstance();     
    	    objCalendarDate1.setTime(date1);     
    	    Calendar objCalendarDate2 = Calendar.getInstance();     
    	    objCalendarDate2.setTime(date2);     
    	    if (objCalendarDate2.equals(objCalendarDate1))
    	    	return 0;     
    	    if (objCalendarDate1.after(objCalendarDate2)){     
    	        Calendar temp = objCalendarDate1;     
    	        objCalendarDate1 = objCalendarDate2;     
    	        objCalendarDate2 = temp;     
    	    }     
    	    if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))     
    	        flag = 1;     
    	      
    	    if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))     
    	        iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))* 12 + objCalendarDate2.get(Calendar.MONTH))- objCalendarDate1.get(Calendar.MONTH);     
    	    else    
    	        iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);     
    	    } catch (Exception e){     
    	          e.printStackTrace();     
    	    }     
    	         return iMonth;     
    	    }  
    /**
     * 获取两个日期之间的月份数
     * @param date1 前
     * @param date2 后
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(Date date1, Date date2)
			throws ParseException {

		int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);
		
		int y = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		int m = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
		result = (y*12) + m ;
		return result;

	}

	/**
	 * 计算二个时间相差的天数</br>
	 * 
	 * @param date1
	 *            日期
	 * @param date2
	 *            日期
	 * @return 相差的天数
	 */
	public static int getDays(String date1, String date2) {
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (int) day;
	}
	
	/**
	 * 去掉时间的时分秒
	 * @param dateTime
	 * @return
	 */
	public static Date dateTimeToDate(Date dateTime){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**  
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式  
     * @param date2 被比较的时间  为空(null)则为当前时间  
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年  
     * @return  
     */ 
    public static int compareDate(String date1,String date2,int stype){  
        int n = 0;  
          
        String[] u = {"天","月","年"};  
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";  
          
        date2 = date2==null?getCurrentDate():date2;  
          
        DateFormat df = new SimpleDateFormat(formatStyle);  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        try {  
            c1.setTime(df.parse(date1));  
            c2.setTime(df.parse(date2));  
        } catch (Exception e3) {  
            System.out.println("wrong occured");  
        }  
        //List list = new ArrayList();  
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果  
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来  
            n++;  
            if(stype==1){  
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1  
            }  
            else{  
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1  
            }  
        }  
          
        n = n-1;  
          
        if(stype==2){  
            n = (int)n/365;  
        }     
          
        System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);        
        return n;  
    }  
    
    /**  
     * 得到当前日期  
     * @return  
     */ 
    public static String getCurrentDate() {  
        Calendar c = Calendar.getInstance();  
        Date date = c.getTime();  
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");  
        return simple.format(date);  
    }
    
    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
