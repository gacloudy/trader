DROP TABLE IF EXISTS trader.stock_date_avg;

CREATE TABLE trader.stock_date_avg (
code MEDIUMINT ,
price_date varchar(8) ,
span DECIMAL(2,0) ,
avg_price DECIMAL(7, 1) ,
PRIMARY KEY(code, price_date, span)
);