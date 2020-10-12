package com.controller;

import com.config.CiConfig;
import com.dao.mysql.impl.AnswerBeanImpl;
import com.dao.mysql.mapper.AnswerBeanMapper;
import com.domain.AnswerBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    public static final Logger logger = Logger.getLogger(HelloController.class);
    AnswerBeanMapper answerBeanMapper;

    @Autowired
    public HelloController(AnswerBeanMapper answerBeanMapper) {
        this.answerBeanMapper = answerBeanMapper;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CiConfig.class);
        AnswerBeanImpl answerBeanMapper = context.getBean(AnswerBeanImpl.class);
        logger.debug(answerBeanMapper.sayHello());
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/getAnswer")
    public AnswerBean getAnswerBean() {
        return answerBeanMapper.selectAnswerBeanByName("小螺号");
    }
}
