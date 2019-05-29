package com.django.springboot2.Enum;

/**
 * @author liulongyun
 * @create 2019/5/29 12:47
 **/
public enum SexEnum {


    MALE(1,"男"), FEMALE(2,"女");

    private int id;
    private String name;

    SexEnum(int id,String name){
        this.id=id;
        this.name=name;
    }

    public static SexEnum getEnumById(int id){

        for (SexEnum sex: SexEnum.values() ) {
            if(id == sex.id){
                return sex;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
