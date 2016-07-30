package com.cl.slack.phoneoperation.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>Description: Log util
 * 日志的级别：debug，info，warn，error
 * debug： 这个是级别最低的级别，  因此这个级别的信息，可以随意的使用，任何觉得有利于在调试时更详细的了解系统运行状态的信息，比如变量的值等等，都输出来看看也无妨。
 * info：这个应该用来反馈系统的当前状态给最终用户的，所以，在这里输出的信息，应该对最终用户具有实际意义，也就是最终用户要能够看得明白是什么意思才行。
 * warn ： 警告，是这个时候进行一些修复性的工作，应该还可以把系统恢复到正常状态中来，系统应该可以继续运行下去。
 * error ： 所谓错误，就是说可以进行一些修复性的工作，但无法确定系统会正常的工作下去，系统在以后的某个阶段，很可能会因为当前的这个问题，导致一个无法修复的错误（例如宕机），但也可能一直工作到停止也不出现严重问题。
 * </p>
 * Created by slack on 2016/7/30 12:15 .
 */
public class L {

    public static boolean IS_DEBUG = true;
    public static boolean IS_WRITE_FILE = true;
    static String TAG = "slack";
    static String FILE_PATH = new File(Environment.getExternalStorageDirectory(), "operateLogs").getAbsolutePath();
    static SimpleDateFormat df = new SimpleDateFormat("[yy-MM-dd hh:mm:ss]: ");
    static String className;// 类名
    static String methodName;// 方法名
    static int lineNumber;// 行数

    private L() {
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    private static String createLog(String msg) {
        // Throwable instance must be created before any methods
//        return "(" + className + " " + methodName +
//                "#" + lineNumber + ") " + msg;

        return msg;
    }


    private static void writeFile(String message) {
        final String m = getNow() + message + "\r\n";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = makeFilePath(FILE_PATH, getToDay());
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    raf.seek(file.length());
                    raf.write(m.getBytes());
                    raf.close();
                } catch (Exception e) {
                    Log.i("slack", "Error on write File:" + e);
                }
            }
        }).start();
    }

    // 生成文件
    private static File makeFilePath(String filePath, String fileName) throws IOException {
        File file = null;
        makeRootDirectory(filePath);

        file = new File(filePath,fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    // 生成文件夹
    private static void makeRootDirectory(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }

    }

    private static String getYearMonthDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "" + c.get(Calendar.MONTH) + "" + c.get(Calendar.DAY_OF_MONTH);
    }

    private static String getToDay() {
        return new SimpleDateFormat("yyMMdd").format(System.currentTimeMillis());
    }

    private static String getNow() {
        return new SimpleDateFormat("[yy-MM-dd  hh:mm:ss]：").format(System.currentTimeMillis());
    }

    //    ------------------------ public ----------------------------------
    public static void e(String message) {
//        getMethodNames(new Throwable().getStackTrace());
        e(TAG, message);
    }

    public static void e(String tag, String message) {
//        getMethodNames(new Throwable().getStackTrace());
        if (IS_DEBUG) {
            Log.e(tag, createLog(message));
        }
        if (IS_WRITE_FILE) {
            writeFile(message);
        }

    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void w(String tag, String message) {
        if (IS_DEBUG) {
            Log.w(tag, createLog(message));
        }
        if (IS_WRITE_FILE) {
            writeFile(message);
        }

    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        if (IS_DEBUG) {
            Log.i(tag, createLog(message));
        }
        if (IS_WRITE_FILE) {
            writeFile(message);
        }

    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (IS_DEBUG) {
            Log.e(tag, createLog(message));
        }
        if (IS_WRITE_FILE) {
            writeFile(message);
        }

    }

    public static void v(String message) {
        v(TAG, message);
    }

    public static void v(String tag, String message) {
        if (IS_DEBUG) {
            Log.e(tag, createLog(message));
        }
        if (IS_WRITE_FILE) {
            writeFile(message);
        }

    }

}
