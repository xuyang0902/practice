package com.zx.yuren.jdk.base;

/**
 * 人类
 *
 * @author xu.qiang
 * @date 18/10/22
 */
public class Person implements Comparable<Person> {

    private Integer age;

    private Integer sex;//0-男 1-女

    private String name;

    public Person() {

    }

    public Person(Integer age, Integer sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != null ? !age.equals(person.age) : person.age != null) return false;
        if (sex != null ? !sex.equals(person.sex) : person.sex != null) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Person o) {

        /*
         * 年龄从小到大 性别从男到女
         */

        if (this.equals(o)) {
            return 0;
        }

        int compareTo = this.age.compareTo(o.getAge());
        if (compareTo != 0) {
            return compareTo;
        }

        int compareTo1 = this.sex.compareTo(o.getSex());
        if (compareTo1 != 0) {
            return compareTo1;
        }

        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
