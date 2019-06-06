package cn.humanetplan.mydemo.Bean;

/**
 * author   : 肖波
 * e-mail   : xiaoboabc168@163.com
 * date     :  2019/6/6.
 */

public class Person {

    private int age;
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
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

}
