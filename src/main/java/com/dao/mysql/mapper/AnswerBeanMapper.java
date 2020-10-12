package com.dao.mysql.mapper;

import com.domain.AnswerBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AnswerBeanMapper {
    @Select("select * from answer_bean where username = #{username}")
    AnswerBean selectAnswerBeanByName(@Param("username") String username);
}
