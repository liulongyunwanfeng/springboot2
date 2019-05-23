package com.django.springboot2.controller;

import com.django.springboot2.pojo.vo.Person;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PersonalController
 * @Author Administrator
 * @Date 2019/5/10
 * @Version 1.0
 * @Description TODO
 */
@Controller
@RequestMapping("/person")
public class PersonalController {

    @RequestMapping(value = "/{name}")
    public String getPersonByName(@PathVariable(value = "name") String name,Model model)throws Exception{

        Person singlePerson = new Person();
        singlePerson.setName(name);
        singlePerson.setAge(31);

        List<Person> people = new ArrayList<>();

        people.add(new Person("lly",12));
        people.add(new Person("ml",12));
        people.add(new Person("django",12));

        model.addAttribute("people",people);
        model.addAttribute("singlePerson",singlePerson);

        return "index";
    }


    @RequestMapping(value = "testbeannameresolver",produces = MediaType.APPLICATION_JSON_VALUE)
    public String testBeanNameResolver(Model model){
        Person person = new Person();
        person.setName("BeanNameResolverTest");
        person.setAge(31);
        model.addAttribute("data",person);
        return "jsonView";

    }

}
