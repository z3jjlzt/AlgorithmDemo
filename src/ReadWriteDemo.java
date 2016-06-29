import java.io.*;

/**
 * Created by kkk on 2016/4/21.
 */
public class ReadWriteDemo {
    /**
     * @param dir 遍历dir下所有的文件
     */
   static void func(File dir){
        File[] files = dir.listFiles();
        if(files!=null){
            for(File f:files){
                if(f.isDirectory()){
                    func(f);
                }
                System.out.println(f.toString());
            }
        }
    }
    void copy(File dest,File target){
        if(!dest.exists())
            return;
        try(InputStream inputStream = new FileInputStream(dest);
        OutputStream outputStream = new FileOutputStream(target)) {
            byte[] bytes = new byte[1024*1024];
            int len=-1;
            while ((len=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void write(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(OutputStream outputStream= new FileOutputStream(file)) {//必须在try后面初始化 前提是，这些可关闭的资源必须实现 java.lang.AutoCloseable 接口。
            String s= "io write test";
            outputStream.write(s.getBytes());
            outputStream.flush();//输出缓存中的
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void read(File file){
        if (!file.exists()){
            return;
        }
        try(InputStream inputStream = new FileInputStream(file)){
            byte[] bytes = new byte[2];//每次读取字节数
            int len =-1;//每次读取长度
            StringBuffer stringBuffer = new StringBuffer();
            while ((len=inputStream.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,len));
            }
            System.out.println(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {

//        //创建一个File对象
//        File file = new File("E://ss助手d");
//        //判断是否存在
//        boolean isExist = file.exists();
//        //循环
//        if(isExist)
//            func(file);
//
//        System.out.println(isExist);
        File file = new File("e://a.txt");
        File dest = new File("e://1.jpg");
        File target = new File("f://1.txt");
        new ReadWriteDemo().copy(file,target);
    }

}
