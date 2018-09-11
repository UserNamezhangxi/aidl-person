package com.zx.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/9/11.
 */

public class Person implements Parcelable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    /** implements Parcelable 实现序列化  start*/
    public Person(Parcel source) {
        this.name = source.readString();// 这里取出数据必须和 writeToParcel 存入数据的先后顺序一致
        this.age = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // 将数据封装成这样的包，在接收的时候仍然需要解包
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 将name和数据封装成dest的数据格式 在os中进行传递
        dest.writeString(name);
        dest.writeInt(age);
    }

    /*代码编写格式固定*/
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        // 取出writeToParcel 塞进来的数据
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source); //通过一个参数的构造
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    /** implements Parcelable 实现序列化  end*/
}
