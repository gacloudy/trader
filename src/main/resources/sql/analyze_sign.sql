
DROP TABLE IF EXISTS trader.analyze_sign;


CREATE TABLE trader.analyze_sign (
analyze_id MEDIUMINT comment '解析ID',
sign varchar(4) ,
benefit DECIMAL(4,3) ,
cut DECIMAL(4,3) ,
max_count DECIMAL(2,0) ,
diff DECIMAL(4,3) ,
PRIMARY KEY(analyze_id)
);



commit;