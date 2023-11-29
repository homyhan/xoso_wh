use wh;
-- CREATE TABLE `wh`.`test` (
--   `idtest` INT NOT NULL AUTO_INCREMENT,
--   `ngay` VARCHAR(255) NOT NULL,
--   `rs` VARCHAR(255) NOT NULL,
--   PRIMARY KEY (`idtest`));


CREATE TABLE KETQUA (
  idtest INT NOT NULL AUTO_INCREMENT,
  ngay VARCHAR(255) NOT NULL,
  tinh VARCHAR(255),
  giai VARCHAR(255),
  rs VARCHAR(2000),
  maTinh VARCHAR(255),
  PRIMARY KEY (idtest)
);
  
SELECT * FROM wh.KETQUA;
-- drop table KETQUA;

