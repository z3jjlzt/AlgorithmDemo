package com.kkk.io;

import java.io.*;

/**
 * Created by kkk on 2016/4/21.
 */
//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//
//
public class MultiDownDemo {
    /**
     * @param src   源文件
     * @param dest 目标文件
     * @param n      开启的线程数
     */
    public static void multiThreadDown(File src, File dest, int n) {
        long size = src.length();
        if (!src.exists()){
            throw new IllegalArgumentException(src+"  not exists");
        }

        if (!dest.exists())
            try {
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try (RandomAccessFile raf = new RandomAccessFile(dest, "rwd")) {
            raf.setLength(size);//设置目标文件大小
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long perSize = size / n;//每个线程下载文件的大小
        for (int i = 0; i < n - 1; i++) {//前n-1个大小固定，start，end确定
            new Thread(new downRunnable(src, dest, i * perSize, (i + 1) * perSize)).start();
        }
        //最后一个线程大小一般小于persize
        new Thread(new downRunnable(src, dest, (n - 1) * perSize, size)).start();
    }

    public static void main(String[] args) {
        File dest = new File("G:\\迅雷下载\\1.mp3");
        File target = new File("G:\\tar.mp3");
        MultiDownDemo.multiThreadDown(dest, target, 4);
    }

}

class downRunnable implements Runnable {
    File dest, target;
    long start;//起始下载位置
    long end;

    public downRunnable(File dest, File target, long start, long end) {
        this.dest = dest;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.println("start :" + start + " end :  " + end);
        try (RandomAccessFile destRaf = new RandomAccessFile(dest, "r");
             RandomAccessFile targetRaf = new RandomAccessFile(target, "rwd")
        ) {
            byte[] bytes = new byte[1024 * 8];
            int len;
            destRaf.seek(start);
            targetRaf.seek(start);
            while (start < end) {
                if (end - start >= bytes.length) {
                    destRaf.read(bytes);
                    targetRaf.write(bytes, 0, bytes.length);
                    start += bytes.length;
                } else {
                    len = destRaf.read(bytes, 0, (int) (end - start));
                    targetRaf.write(bytes, 0, bytes.length);
                    start += len;
                }
            }

            System.out.println("done" + start);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}