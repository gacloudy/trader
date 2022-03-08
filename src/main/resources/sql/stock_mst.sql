
DROP TABLE IF EXISTS trader.stock_mst;


CREATE TABLE trader.stock_mst (
code MEDIUMINT comment '銘柄コード',
stock_name varchar(60) comment '銘柄名',
stock_name_en varchar(60) comment '銘柄名(en)',
industry_type TINYINT comment '業種',
gathering_flg TINYINT DEFAULT 0 NOT NULL comment '集計対象',
PRIMARY KEY(code)
);


alter table trader.stock_mst comment '銘柄マスタ';

insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (2502,'アサヒグループホールディングス(株)','Asahi Group Holdings,Ltd.','9','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (2801,'キッコーマン(株)','KIKKOMAN CORPORATION','9','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (2914,'ＪＴ','JT','9','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (3382,'(株)セブン＆アイ・ホールディングス','Seven & i Holdings Co., Ltd.','98','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (3402,'東レ(株)','Toray Industries, Inc.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (3407,'旭化成(株)','Asahi Kasei Corporation','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4063,'信越化学工業(株)','Shin‐Etsu Chemical Co.,Ltd.','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4188,'(株)三菱ケミカルホールディングス','Mitsubishi Chemical Holdings Corporation','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4307,'(株)野村総合研究所','Nomura Research Institute, Ltd.','4','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4452,'花王(株)','Kao Corporation','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4502,'武田薬品工業(株)','Takeda Chemical Industries,Ltd.','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4503,'アステラス製薬(株)','Astellas Phama Inc.','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4519,'中外製薬(株)','Chugai Pharmaceutical Co.,Ltd.','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4543,'テルモ(株)','TERUMO CORPORATION','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4568,'第一三共(株)','Daiichi-Sankyo Company, Limited','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4661,'(株)オリエンタルランド','Oriental Land Co., Ltd.','98','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4901,'富士フイルムホールディングス(株)','FUJIFILM Holdings Corporation','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (4911,'(株)資生堂','Shiseido Co., Ltd.','7','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (5108,'(株)ブリヂストン','Bridgestone Corporation','98','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (5401,'日本製鉄(株)','NIPPON STEEL & SUMITOMO METAL CORPORATION','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6098,'(株)リクルートホールディングス','Recruit Holdings Co.,Ltd.','98','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6273,'ＳＭＣ(株)','SMC Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6301,'コマツ','Komatsu Ltd.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6326,'(株)クボタ','Kubota Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6367,'ダイキン工業(株)','Daikin Industries, Ltd.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6501,'(株)日立製作所','Hitachi, Ltd.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6503,'三菱電機(株)','Mitsubishi Electric Corp.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6594,'日本電産(株)','Nidec Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6752,'パナソニック(株)','Panasonic Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6758,'ソニーグループ(株)','Sony Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6861,'(株)キーエンス','KEYENCE CORPORATION','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6902,'(株)デンソー','DENSO Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6954,'ファナック(株)','FANUC Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6971,'京セラ(株)','Kyocera Corporation','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (6981,'(株)村田製作所','Murata Manufacturing Co., Ltd.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (7203,'トヨタ自動車(株)','Toyota Motor Corporation','2','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (7261,'マツダ(株)','Mazda Motor Corporation','2','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (7267,'ホンダ','Honda Motor Co., Ltd.','2','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (7741,'ＨＯＹＡ(株)','HOYA CORPORATION','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (7751,'キヤノン(株)','Canon Inc.','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8001,'伊藤忠商事(株)','ITOCHU Corporation','5','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8031,'三井物産(株)','MITSUI & CO., LTD.','5','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8035,'東京エレクトロン(株)','Tokyo Electron Limited','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8053,'住友商事(株)','SUMITOMO CORPORATION','5','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8058,'三菱商事(株)','Mitsubishi Corp.','5','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8306,'(株)三菱ＵＦＪフィナンシャル・グループ','MUFG Bank, Ltd.','0','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8316,'(株)三井住友フィナンシャルグループ','Sumitomo Mitsui Financial Group, Inc.','0','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8411,'(株)みずほフィナンシャルグループ','Mizuho Financial Group, Inc.','0','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8591,'オリックス(株)','ORIX Corporation.','98','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8604,'野村ホールディングス(株)','Nomura Holdings, Inc.','3','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8766,'東京海上ホールディングス(株)','Tokio Marine Holdings, Inc.','0','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (8801,'三井不動産(株)','Mitsui Fudosan Co., Ltd.','8','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9007,'小田急電鉄(株)','Odakyu Electric Railway Co., Ltd.','10','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9022,'東海旅客鉄道(株)','Tokai Passenger Railway Co., Ltd.','10','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9064,'ヤマトホールディングス(株)','YAMATO HOLDINGS CO., LTD.','10','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9432,'日本電信電話(株)','Nippon Telegraph and Telephone Corporation','4','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9433,'ＫＤＤＩ(株)','KDDI CORPORATION','1','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9437,'(株)ＮＴＴドコモ','NTT DOCOMO, INC.','4','0');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9613,'(株)ＮＴＴデータ','NTT DATA Corporation','4','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9983,'(株)ファーストリテイリング','Fast Retailing Co., Ltd.','6','1');
insert into trader.stock_mst(`code`,`stock_name`,`stock_name_en`,`industry_type`,`gathering_flg`) values (9984,'ソフトバンクグループ(株)','SoftBank Group Corp.','4','1');




commit;