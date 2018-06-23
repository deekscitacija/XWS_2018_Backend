LOAD DATA LOCAL INFILE  
'C:\\Users\\Tima\\Desktop\\XWS\\XWS_2018_Backend\\rezervacije1.csv' 
INTO TABLE xws_2018_db.reservation 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS