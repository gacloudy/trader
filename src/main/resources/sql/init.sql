DROP DATABASE trader;
CREATE DATABASE trader;

ALTER DATABASE trader CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'trader' IDENTIFIED BY 'tradertrader';

GRANT all ON trader.* TO trader;