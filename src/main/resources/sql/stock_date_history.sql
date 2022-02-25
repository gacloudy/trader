DROP TABLE IF EXISTS trader.stock_date_history;

CREATE TABLE trader.stock_date_history (
code MEDIUMINT ,
price_date varchar(8) ,
start_price DECIMAL(7,1) ,
end_price DECIMAL(7, 1) ,
highest_price DECIMAL(7, 1) ,
lowest_price DECIMAL(7, 1) ,
amount INT ,
yesterday_price DECIMAL(7, 1),
PRIMARY KEY(code, price_date)
);