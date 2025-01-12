use simple_journal_entry_db;

drop table if exists comic;
create table comics (
    id bigint not null primary key auto_increment,
    name varchar(100) not null comment "漫画名",
    is_published tinyint(1) not null comment "公開フラグ",
    created_at datetime not null ,
    updated_at datetime not null
);