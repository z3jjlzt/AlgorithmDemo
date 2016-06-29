/**
 * Created by kkk on 2016/4/22.
 */
public class SingletonEnum {
    public static void main(String[] args) {
        Dog dog = Dog.DogA;

        System.out.println(dog);

    }
}

enum Dog {
    DogA(1, "dog1"),DogB(2,"dog2");

    int age;
    String name;

    Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
