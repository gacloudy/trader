
DROP TABLE IF EXISTS trader.analyze_result;


CREATE TABLE trader.analyze_result (
code MEDIUMINT ,
analyze_id MEDIUMINT comment '解析ID',
price_date varchar(8) ,
long_span DECIMAL(2,0) ,
short_span DECIMAL(2,0) ,
trade_result DECIMAL(4,3),
PRIMARY KEY(code, analyze_id, price_date, long_span, short_span)
);

commit;