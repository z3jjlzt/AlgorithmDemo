package com.kkk.io;

import java.io.*;

/**
 * Created by kkk on 2016/4/21.
 */
public class ObjectStreamDemo {
    void write(Cat cat, File target) {

        try (OutputStream outputStream = new FileOutputStream(target);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(cat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void  read(File dest){
        try(InputStream inputStream = new FileInputStream(dest);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Cat cat = (Cat) objectInputStream.readObject();
            System.out.println(cat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File file = new File("e://cat.obj");
        ObjectStreamDemo objectStreamDemo = new ObjectStreamDemo();
        objectStreamDemo.write(new Cat(23,"sb"),file);
        objectStreamDemo.read(file);
    }
}

class Cat implements Serializable {
    int age;
    String name;

    public Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;

    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.kkk.io.Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

