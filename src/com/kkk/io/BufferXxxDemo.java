package com.kkk.io;

import java.io.*;

/**
 * Created by kkk on 2016/4/22.
 */
public class BufferXxxDemo {
    public static void copy(File src,File dest){//123
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] bytes= new byte[8*1024];
            int len=-1;
            while ((len=bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copy1(File src,File dest){//6870
        try(FileInputStream fis =new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dest)) {
            byte[] bytes= new byte[8*1024];
            int len=-1;
            while ((len=fis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File src = new File("G:\\迅雷下载\\1.mp3");
        File dest = new File("G:\\dest.mp3");
       long start = System.currentTimeMillis();
        BufferXxxDemo.copy(src,dest);
       // MultiDownDemo.multiThreadDown(src,dest,3);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}

