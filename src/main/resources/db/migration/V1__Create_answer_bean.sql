drop table if exists answer_bean;
create table answer_bean
(
    SEQ            tinyint auto_increment not null primary key,
    USERNAME       varchar(100),
    VOTENUMBER     int,
    ARTICLECONTEXT text
)