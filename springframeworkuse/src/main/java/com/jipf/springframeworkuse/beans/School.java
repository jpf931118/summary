package com.jipf.springframeworkuse.beans;

import lombok.Data;

@Data
public class School {
    private String address;
    private String name;
    private Integer index;
    private Teacher teacher;
    public School() {
    }
    public School(String name_1) {
        this.name = name_1;
    }
    public School(String address, Integer index) {
        this.address = address;
        this.index = index;
    }
    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }
//    public School(Integer index, Teacher teacher) {
//        this.index = index;
//        this.teacher = teacher;
//    }
}
