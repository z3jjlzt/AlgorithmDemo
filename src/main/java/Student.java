/**
 * Created by kkk on 2016/4/18.
 */
public class Student implements Comparable<Student> {
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        if (o.getAge() > this.getAge())
            return -1;
        else if (o.getAge() < this.getAge())
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
