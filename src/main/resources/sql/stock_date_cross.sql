DROP TABLE IF EXISTS trader.stock_date_cross;

CREATE TABLE trader.stock_date_cross (
code MEDIUMINT ,
price_date varchar(8) ,
long_span DECIMAL(2,0) ,
short_span DECIMAL(2,0) ,
sign varchar(4) ,
PRIMARY KEY(code, price_date, long_span, short_span)
);