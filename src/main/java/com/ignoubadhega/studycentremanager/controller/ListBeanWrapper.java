package com.ignoubadhega.studycentremanager.controller;

import java.util.List;

import javax.validation.Valid;

import com.ignoubadhega.studycentremanager.dto.StudentDto;

public class ListBeanWrapper {

    @Valid
    List<StudentDto> list;

    public List<StudentDto> getList() {
        return list;
    }

    public void setList(List<StudentDto> list) {
        this.list = list;
    }
    
}
