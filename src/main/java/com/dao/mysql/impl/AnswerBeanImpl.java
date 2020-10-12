package com.dao.mysql.impl;

import com.dao.mysql.mapper.AnswerBeanMapper;
import com.domain.AnswerBean;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerBeanImpl implements AnswerBeanMapper {

    SqlSession sqlSession;

    @Autowired
    public AnswerBeanImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public AnswerBean getAnswerBeanById(int seq) {
        return sqlSession.selectOne("org.mybatis.example.MyMapper.selectAnswerBeanById", seq);
    }

    public void saveAnswerBean(List<AnswerBean> beanList) {
        sqlSession.insert("org.mybatis.example.MyMapper.interIntoAnswerBean", beanList);
        sqlSession.commit();
    }

    @Override
    public AnswerBean selectAnswerBeanByName(String username) {
        return sqlSession.selectOne("org.mybatis.example.MyMapper.selectAnswerBeanByName", username);
    }

    public String sayHello(){
        return "Hello";
    }
}
