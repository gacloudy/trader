DROP TABLE IF EXISTS trader.industry_type_mst;


CREATE TABLE trader.industry_type_mst (
industry_type TINYINT comment '業種コード',
industry_name varchar(30) comment '業種名',
industry_name_en varchar(30) comment '業種名(en)',
PRIMARY KEY(industry_type)
);

alter table trader.industry_type_mst comment '業種マスタ';


INSERT INTO trader.industry_type_mst VALUES(0, '金融', 'Bank');
INSERT INTO trader.industry_type_mst VALUES(1, 'メーカー', 'Manufacturer');
INSERT INTO trader.industry_type_mst VALUES(2, '自動車', 'Automotive');
INSERT INTO trader.industry_type_mst VALUES(3, '証券', 'Financial services');
INSERT INTO trader.industry_type_mst VALUES(4, 'IT', 'IT');
INSERT INTO trader.industry_type_mst VALUES(5, '商社', 'Trading Company');
INSERT INTO trader.industry_type_mst VALUES(6, '小売り', 'Retail');
INSERT INTO trader.industry_type_mst VALUES(7, '化学・薬品', 'Pharmaceutical');
INSERT INTO trader.industry_type_mst VALUES(8, '不動産', 'Real Estate');
INSERT INTO trader.industry_type_mst VALUES(9, '食料品', 'Grocery');
INSERT INTO trader.industry_type_mst VALUES(98, 'その他', 'etc');

commit;