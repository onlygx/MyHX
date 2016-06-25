package com.example.viewpagerdemo.ui.units;
/**
 * 字符串操作工具包
 * Create by: dingwei  on  2015/12/2
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    //格式化时间样式
    public final static SimpleDateFormat dateFormater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static final String MS_FORMART = "yyyy-MM-dd  HH:mm:ss SSS";

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     * @Create by: dingwei  on  2015/12/2
     */
    public static Date toDate(String sdate) {
        return toDate(sdate, null);
    }

    public static Date toDate(String date, String dateFormat) {
        //date="2016-1-11 23:25:60";
        dateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat mSimpleDateFormat;
        if (isEmpty(date)) {
            return null;
        }
        if (!isEmpty(dateFormat)) {
            mSimpleDateFormat = new SimpleDateFormat(dateFormat);
        } else {
            mSimpleDateFormat = dateFormater;
        }
        try {
            return mSimpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date d, String format) {
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = MS_FORMART;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     * 比较两个字符串大小
     *
     * @param str1
     * @param str2
     * @return 如果str1小于str2则返回-1，如果两个字符串相等则返回0，如果str1大于str2则返回1
     * <p>
     * Create by: yangchong  on  2016/3/14
     */
    public static int compare(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        if (str1 == null)
            return -1;

        if (str2 == null)
            return 1;

        int cmpResult = str1.compareTo(str2);
        return cmpResult == 0 ? 0 : (cmpResult > 0 ? 1 : -1);
    }

    /**
     * 以友好的方式显示时间
     * Create by: dingwei  on  2015/12/2
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        return friendly_time(sdate, null);
    }

    public static String friendly_time(String sdate, String format) {
        Date time = toDate(sdate, format);
        if (time == null) {
            return sdate;
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = dateFormater2.format(cal.getTime());
        String paramDate = dateFormater2.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.format(time);
        }
        return ftime;
    }

    /**
     * 以友好的方式显示时间
     * Create by: dingwei  on  2015/12/2
     *
     * @param sdate
     * @return
     */
    public static String showTime(String sdate, String inputformatString,
                                  SimpleDateFormat outputformat) {
        Date time = toDate(sdate, inputformatString);
        if (time == null) {
            return sdate;
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.format(cal.getTime());
        String paramDate = dateFormater2.format(time);
        if (curDate.equals(paramDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("a  HH:mm");
            ftime = sdf.format(time);
        } else {
            ftime = outputformat.format(time);
        }
        return ftime;
    }

    /**
     * 判断给定字符串时间是否为今日
     * Create by: dingwei  on  2015/12/2
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.format(today);
            String timeDate = dateFormater2.format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * Create by: dingwei  on  2015/12/2
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     * Create by: dingwei  on  2015/12/2
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     * Create by: dingwei  on  2015/12/2
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }


    /**
     * 字符串转整数
     * Create by: dingwei  on  2015/12/2
     *
     * @param str
     * @param defValue
     * @return
     */
    public static Long toLong(String str, Long defValue) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    //格式化字符串
    public static String formatNumber(float number) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(number);
    }
    /**
     * 字符串转Float
     * Create by: dingwei  on  2015/12/2
     * @param str
     * @param defValue
     * @return
     */
    public static float toFloat(String str, float defValue) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
        }
        return defValue;
    }
    /**
     * 对象转整数
     * Create by: dingwei  on  2015/12/2
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     * Create by: dingwei  on  2015/12/2
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     * @Create by: dingwei  on  2015/12/2
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * float 强制转换小数点2位 四舍五入
     * setScale(1)表示保留一位小数，默认用四舍五入方式
     * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
     * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
     * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
     * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
     * Create by dingwei on 2015/12/15
     */
    public static String toTwoFloat(float ft) {
        int scale = 2;//设置位数
        BigDecimal bd = new BigDecimal((double) ft);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
        ft = bd.floatValue();
       /* DecimalFormat df = new DecimalFormat("#.00");
        String defNum = df.format(ft);
        if(defNum.equals(".00")){
            defNum = "0.00";
        }*/
        return ft+"";
    }


    public static String toTwoDouble(double ft){
        DecimalFormat df=new DecimalFormat(".##");
        double dou =  Double.valueOf(df.format(ft));
        if(dou<=0){
            return "0.00";
        }
        return dou+"";
    }


    public static float twoFloat(float ft) {
        int scale = 2;//设置位数
        BigDecimal bd = new BigDecimal((double) ft);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
        ft = bd.floatValue();
       /* DecimalFormat df = new DecimalFormat("#.00");
        String defNum = df.format(ft);
        if(defNum.equals(".00")){
            defNum = "0.00";
        }*/
        return ft;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     * @Create by: menglinghua  on  2016/1/11
     */
    public static String friendly_time2(String sdate) {
        return friendly_time2(sdate, null);
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     * @Create by: menglinghua  on  2016/1/11
     */
    public static String friendly_time2(String sdate, String format) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date(Long.parseLong(sdate));
        if (time == null) {
            return sdate;
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            ftime = "今天";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days >= 2) {
            ftime = "更早以前";
        }
        return ftime;
    }

    public static String friendly_date(long time) {
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);

        int hour = mCalendar.get(Calendar.HOUR);

        int apm = mCalendar.get(Calendar.AM_PM);
        if (apm == 0) {
            return "上午好";
        } else {
            return "下午好";
        }
    }

    public static int dayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * MD5加密
     *
     * @param s
     * @return
     * @Create by: menglinghua  on  2016/1/18
     */
    public static String getMD5Str(String s) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间戳
     *
     * @Create by: menglinghua  on  2016/1/20
     */
    public static String getPosttime() {
        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String ts = tsTemp.toString();
        return ts;
    }

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     * @Create by: menglinghua  on  2016/1/18
     */
    public static boolean isPhonenum(String phone) {
        String regex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        Pattern pattern = Pattern
                .compile(regex);
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 正则匹配密码：
     *
     * @Create by: menglinghua  on  2016/1/18
     * [a-zA-Z]:字母开头
     * \\w :可包含大小写字母，数字，下划线,@
     * {5,17} 5到17位，加上开头字母  字符串长度6到18
     */
    public static boolean isPassword(String phone) {
        Pattern pattern = Pattern
                .compile("\\w{6,18}");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 正则匹配：把手机号从字符串中匹配出来
     *
     * @Create by: menglinghua  on  2016/2/16
     */
    public static String getTelnum(String sParam) {
        if (sParam.length() <= 0)
            return "";
        /**
         *  "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，
         *  "\\d{9}"代表后面是可以是0～9的数字，有9位,并且\D代表前后都不是数字。
         */
        String regex = "(?<!\\d)1[34578]\\d{9}(?!\\d)";
        //String regex =  "\\D[1][34578]\\d{9}\\D";//这个会多匹配前后两个字符
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sParam);
        StringBuffer bf = new StringBuffer();
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }


    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * 图片地址转换成base64
     ** @param imgPath   //@param bitmap
     * @return
     * @Create by: menglinghua  on  2016/3/7
     */
    public static String imgToBase64(String imgPath) {
        Bitmap bitmap = null;
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if (bitmap == null) {
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 图片bitmap转换成base64
     *
     * @return base64
     * @Create by: menglinghua  on  2016/3/7
     */
    public static String imgToBase64(Bitmap bitmap) {
        if (bitmap == null) {
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 图片地址转换成bitmap
     *
     * @param imgPath
     * @return
     * @Create by: menglinghua  on  2016/3/7
     */
    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * base64转换成Bitmap
     *
     * @param base64Data
     * @param imgName
     * @param imgFormat  图片格式
     * @Create by: menglinghua  on  2016/3/7
     */
    public static void base64ToBitmap(String base64Data, String imgName, String imgFormat) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File("/sdcard/", imgName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            // fos.notifyAll();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 检测是否可以转换成Int
     */
    public static boolean setStrToInt(String Str) {
        try {
            Integer.parseInt(Str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}