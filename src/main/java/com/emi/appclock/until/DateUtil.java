package com.emi.appclock.until;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    public static Date dateIncreaseByMonth(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }
    public static Date dateIncreaseByDay(Date date, int days) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }
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
    public static final String ISO_YearMoth_FORMAT = "yyyyMM";
    private static boolean LENIENT_DATE = false;
    public static String YearMothToStringWithTime( ) {

        return dateToString(new Date(), ISO_YearMoth_FORMAT);
    }
    public static String YearMothToStringWithTime(Date date) {

        return dateToString(date, ISO_YearMoth_FORMAT);
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
    public static long getNewLong(String timeStr,String formatStr){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
            return sdf.parse(timeStr).getTime();
        }catch (ParseException e){
            e.getErrorOffset();
        }
        return 0;
    }
    public static long getLong(String timeStr){
        try {
            return sdf.parse(timeStr).getTime();
        }catch (ParseException e){
            e.getErrorOffset();
        }
          return 0;
    }
    public static String testBoolen(String ids,String a){
        if(StringUtil.isNullObject(ids)){
             return  "1";
        }
        String[] values = ids.split(",");
        List<String> list = Arrays.asList(values);
        if(list.contains(a)){
            return "0";
        }else {
            return  "1";
        }
    }
    public static int compareDate(String str1, Date date2) {
           Date date1= StrToDate(str1+":00");
            if (date1.getTime() > date2.getTime())
                return -1;
            else if (date1.getTime() < date2.getTime())
                return 1;
            else
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long sime=0;
        try {
            Date d=formatter.parse(inVal);
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
             return getLong(format.format(d));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sime;   //返回毫秒数
    }
    public static long fromDateToLong(String inVal) { //此方法计算时间毫秒
        long sime=0;
        try {
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
            return getLong(format.format(inVal));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sime;   //返回毫秒数
    }
    public static int getMinnuic(String stime,String etime){
        long startT=getLong(stime); //定义上机时间
        long endT=getLong(etime);  //定义下机时间

        long ss=(endT-startT)/(1000); //共计秒数
        int MM = (int)ss/60;   //共计分钟数
        return MM;
    }
    public static String dateToStr(Date date){
        //日期转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
         return s;
    }
    public static String dateToStrSS(Date date){
        //日期转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(date);
        return s;
    }
    public static String dateToStrs(Date date){
        //日期转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String s = sdf.format(date);
        return s;
    }
    public static Date StrNewToDate(String str) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = inputFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
    public static String nextMonthFirstDate() {
        //设置时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获得实体类
        Calendar ca = Calendar.getInstance();
        //设置最后一天
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        //最后一天格式化
       return format.format(ca.getTime());
    }
    public static String StrToDateYYYYMMDD(Date date) {
        //日期转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        return s;
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
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.MONTH));
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }
public static  String getStringSTR(String str){
        if(str.equals("0")){
            return str;
        }
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
        cal.add(Calendar.SECOND, x);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }
    public static String addNewDateMinut(String day, int x)//返回的是字符串型的时间，输入的
    //是String day, int x
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 24小时制
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
    public static boolean isMatch(String orginal)
    {
        String  regex="^\\+{0,1}[1-9]\\d*";
        Pattern pattern=Pattern.compile(regex);
        Matcher isNum= pattern.matcher(orginal);
        return isNum.matches();
    }
    public static  int getWeeksByChooseDay(){
      return     Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1;
    }
    public static  int getWeeksByChooseDays(){
        return     Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
   //判断选择的日期是否是本月
    public static boolean isThisMonth(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }
    public static  String getFirtMonthDay(int num){
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, num);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(calendar1.getTime());
    }
    public static  String getFirtForntMonthDay(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        String yesterdayDate=dateFormat.format(calendar.getTime());
        return yesterdayDate;
    }
    public static boolean  getMaxMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int getDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(getDay == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
             return true;
        }else{
             return false;
        }
    }
    /**
     *  处理时间
     * @param oldTime  原时间
     * @param add  增加时间
     * @return
     * @throws ParseException
     */
    public static String  TimeAdd(String oldTime,String add){
        try {
            int addMit = Integer.valueOf(add);
            DateFormat df = new SimpleDateFormat("hh:mm");
            Date date = df.parse(oldTime);
            Date expireTime = new Date(date.getTime() + addMit * 60 * 1000);
            String newTime = df.format(expireTime);
            return newTime;
        }catch (Exception e){
            return oldTime;
        }
    }

    public static void main (String [] args) {


        System.out.println("3333====="+ TimeAdd("08:30", "90"));
    }
}
