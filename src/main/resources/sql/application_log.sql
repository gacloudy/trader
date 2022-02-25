
DROP TABLE IF EXISTS trader.application_log;


CREATE TABLE trader.application_log (
date varchar(48) ,
log text,
PRIMARY KEY(date)

);


alter table trader.application_log comment 'ログ';