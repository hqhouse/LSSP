package com.emi.appclock.until;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatesUtil {

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of December in the year 2002
     */

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    /**
     * 判断日期是否在本月内
     */
    public static String getMMBleDaye(String str){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=df.format(new Date());
        String MM=date.substring(5, 7);//截取系统月份
        String mm=str.substring(5, 7);//要判断的月份
        if(MM.equals(mm)){
            System.out.println("在本月份");
            return "1";
        }else{
            System.out.println("不在本月份");
            return "0";
        }
    }
    public static String timePastTenSecond(int str) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt=new Date();

            Calendar newTime = Calendar.getInstance();
            newTime.setTime(dt);
            newTime.add(Calendar.SECOND,str);//日期加10秒

            Date dt1=newTime.getTime();
            String retval = sdf.format(dt1);

            return retval;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
   public static Date getYesterDay(Date date) {
        if (date == null) {
            System.out.println("入参为空");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //calendar.add(Calendar.DAY_OF_MONTH, +1);//明天
        //calendar.add(Calendar.DAY_OF_MONTH, 0);//今天
        calendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
        return calendar.getTime();
    }
    public static String getNextDay(Date date) {
        if (date == null) {
            System.out.println("入参为空");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, +1);//明天
        return dateToString(calendar.getTime(),"yyyy-MM-dd");
    }
    public static Date getNextDays(Date date) {
        if (date == null) {
            System.out.println("入参为空");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, +1);//明天
        return calendar.getTime();
    }
    public static int days(Date date) {
        int year= Integer.parseInt(getYear(date));
        int month= Integer.parseInt(getMonth(date));
        int days = 0;
        if (month != 2) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;

            }
        } else {
            // 闰年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                days = 29;
            else
                days = 28;

        }
        return days;
    }
    //返回当月星期天数
    public static int getSundays(Date date,String tempstr){
        int year= Integer.parseInt(getYear(date));
        int month= Integer.parseInt(getMonth(date));
        int sundays=0;
        SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
        Calendar setDate= Calendar.getInstance();
        //从第一天开始
        int day;
        for(day=1;day<=days(date);day++){
            setDate.set(Calendar.DATE,day);
            String str=sdf.format(setDate.getTime());
            if(str.equals(tempstr)){
                sundays++;
            }
        }
        return sundays;
    }
    public static int getDayOfWeekCount(Date date , int day) {
                 Calendar instance = Calendar.getInstance();
                instance.setTime(date);
                 int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
                 int count = 0;
                 //也就是遍历当月每天 计算出符合的数量即可
                 for (int i = 1; i <= actualMaximum; i++) {
                         Calendar tp = Calendar.getInstance();
                         tp.setTime(date);
                        tp.set(Calendar.DAY_OF_MONTH , i);
                         if ( tp.get(Calendar.DAY_OF_WEEK) == day ) {
                                 System.out.printf("符合指定的日期数据为:%tc \n" , tp.getTime());
                                count++;
                            }
                   }
                 return count;
           }
    public static final String ISO_YearMoth_FORMAT = "yyyyMM";
    private static boolean LENIENT_DATE = false;
    public static String YearMothToStringWithTime( ) {

        return dateToString(new Date(), ISO_YearMoth_FORMAT);
    }

    /**
     *  java.util.Date
     * @param dateText
     * @param format
     * @param lenient
     * @return
     */
    public static Date stringToDate(String dateText, String format,
                                    boolean lenient) {

        if (dateText == null) {

            return null;
        }

        DateFormat df = null;

        try {

            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);

            return df.parse(dateText);
        } catch (ParseException e) {

            return null;
        }
    }
    /**
     * java.util.Date
     */
    public static Date stringToDate(String dateString) {
        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /**
     * @return
     * @param pattern
     * @param date
     */
    public static String dateToString(Date date, String pattern) {

        if (date == null) {

            return null;
        }

        try {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }
    /**
     *
     * @param date
     * @return string
     */
    public static String getYear(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat(
                "yyyy");
        String cur_year = formater.format(date);
        return cur_year;
    }

    /**
     *
     * @param date
     * @return string
     */
    public static String getMonth(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat(
                "MM");
        String cur_month = formater.format(date);
        return cur_month;
    }

    public static Date convertToDate(String str) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dt = null;
        try {
            dt = fmt.parse(str);
        } catch (ParseException ex) {
            dt = new Date();
        }
        return dt;
    }

    private static final String formatStr = "HH:mm";
    private static SimpleDateFormat sdf=new SimpleDateFormat(formatStr);

    public static boolean isInZone(long tStart,long tEnd,long t){
        return tStart <= t && t <= tEnd;
    }
    public static int getInt(long tStart,long tEnd){
        if(tEnd>tStart){
            return 0;
        }else{
            return 1;
        }
    }
    public static long getLong(String timeStr){
        try {
            return sdf.parse(timeStr).getTime();
        }catch (ParseException e){
            e.getErrorOffset();
        }
          return 0;
    }
    public static String  getReult(String stime,String etime){
        SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm");// 24小时制
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(newFormat.parse(stime));
            c2.setTime(newFormat.parse(etime));
        } catch (Exception e) {
            e.getMessage();
        }
        int result = c1.compareTo(c2);
        if (result<0){
            return "0";
        }else if(result==0)
        {
            return "1";
        }else{
            return "2";
        }
    }
    public static String getcurrentDate(){
        SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm");// 24小时制
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return newFormat.format(date);
    }
    public static String getcurrentHHMM(Date date){
        try {
            SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm");// 24小时制
            return newFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static int  getWeekDay(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        int week=cal.get(Calendar.DAY_OF_WEEK)-1;
        return week;
    }
    public static String  addMinct(String time,int number){
        try {
            Calendar nowTime = Calendar.getInstance();//得到当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            nowTime.setTime(sdf.parse(time));//设置成这个时间
            nowTime.add(Calendar.MINUTE, number);//增加6分钟
           return sdf.format(nowTime.getTime());
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public static long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
        Date date = null;   //定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
        try {
            date = inputFormat.parse(inVal); //将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();   //返回毫秒数
    }
    public static int getMinnuic(String stime,String etime){
        long startT=fromDateStringToLong(stime); //定义上机时间
        long endT=fromDateStringToLong(etime);  //定义下机时间

        long ss=(endT-startT)/(1000); //共计秒数
        int MM = (int)ss/60;   //共计分钟数
        return MM;
    }
    public static String dateToStr(Date date){
        //日期转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s = sdf.format(date);
         return s;
    }
    public static String dateToStrs(Date date){
        //日期转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String s = sdf.format(date);
        return s;
    }
    public static Date StrToDate(String str) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public synchronized static Date convertToDateTD(String date) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {

            return format.parse(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return null;
        }
    }

    /****
     * 传入具体日期 ，返回具体日期增加一个月。
     * @param date 日期(2017-04-13)
     * @return 2017-05-13
     * @throws ParseException
     */
    public static   String subMonth(String date,int num) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DATE, num);
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            return reStr;
        }catch (Exception E)
        {
            E.printStackTrace();
            return "";
        }
    }
    public static int dateToStringWith(String dbtime1,String dbtime2) {
        try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(dbtime1);
        Date date2 = format.parse(dbtime2);
            int a = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return a;
        }catch (Exception E)
        {
            E.printStackTrace();
            return 0;
        }
    }
    public static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }
    /**
     * 这个月是否是生日
     * @param date
     * @return
     */
    public static boolean isBirthTime(Date date) {
        String pattern="MM";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }
public static  String getStringSTR(String str){
        if(!StringUtil.isNullObject(str)) {
            int index = str.indexOf(".");
            String s = str.substring(index + 1, str.length());//获取小数部分的子字符串
            int in = Integer.parseInt(s);//将代表小数部分的字符串转化为整数
            if (in == 0) //如果小数部分等于0，则去掉小数部分和小数点
            {
                str = str.substring(0, str.indexOf("."));
            }
        }
         return str;
}

    /**
     * 括号前面的字符2019-11-26(上午)
     * @param day
     * @return
     */
    public static String getPatternYYmm(String day){
    Pattern p = Pattern.compile(".*?(?=\\()");
    Matcher m = p.matcher(day);
    if(m.find()) {
        return m.group();
    }
    return null;
}
    public static String addDateMinut(String day, int x)//返回的是字符串型的时间，输入的
    //是String day, int x
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
        //引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
        //量day格式一致
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main (String [] args) {
    /*    Date date=stringToDate("2019-11-22 01:00:31");
        System.out.println("1111111111"+isThisTime(date));*/
     /*   System.out.println("1111111111=========="+getStringSTR("0.0"));*/
       /* GregorianCalendar ca = new GregorianCalendar();
        ca.setTime(new Date());
        System.out.println("11111111111====="+(ca.get(GregorianCalendar.AM_PM)== Calendar.PM ? 1 : 0));*/
/*        Date day1=convertToDate("2019-11-26(上午)");
        Date day2=convertToDate("2019-11-27 14:00:00");
        S day1=convertToDate("2019-11-26(上午)")
        System.out.println("11111111111====="+differentDaysByDate(day1,day2));*/
        /*String day1="2019-11-26";
        String day2="2019-11-27";
        System.out.println("11111111111111111111="+dateToStringWith(day1,day2));*/
     /*   String day1="2019-11-26(上午)";
        String str="()";
        System.out.println("11111111111====="+day1.split(str)[0]);*/
     //     Date date= convertToDate(dateToString(new Date(),"yyyy-MM-dd")+" 10:25");3.5tian
 /*       AttenLeave attenLeave=new AttenLeave();
        attenLeave.setStarttime("2019-11-29 10:58:35");
        System.out.println("22222222====="+DateUtil.convertToDate(attenLeave.getStarttime()));
        System.out.println("11111111111====="+getcurrentHHMM(DateUtil.convertToDate(attenLeave.getStarttime())));*/
/*        AttenLeave attenLeave=new AttenLeave();
        attenLeave.setStarttime("2019-11-29 10:58:35");
        System.out.println("22222222====="+addMinct("10:58",30));*/

      /*  double f1 = new BigDecimal((float)2/450).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double f2=4.56;
        DecimalFormat fmt=new DecimalFormat("0.##");
        String templ=fmt.format(f2);
        String a=templ.substring(templ.length()-2, templ.length()-1);
        if(Integer.parseInt(a)>=5){
            int aa=(int)f2;
            String ma=String.valueOf(aa+0.5);
            System.out.println("222==="+aa) ;
            System.out.println("ddd==="+ma) ;

        }else{
            System.out.println("ddd==="+YearMothToStringWithTime( ));
        }*/
        System.out.println(DatesUtil.getDayOfWeekCount(new Date(), 1));
    }
}
