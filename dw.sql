CREATE TABLE dimTime (
  id INT NOT NULL AUTO_INCREMENT,
  ngay VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE dimStation (
  maTinh VARCHAR(255) NOT NULL ,
  tenTinh VARCHAR(255) NOT NULL,
  PRIMARY KEY (maTinh)
);

CREATE TABLE FactXoSo (
  id INT NOT NULL AUTO_INCREMENT,
  date_id INT,
  maTinh VARCHAR(255) NOT NULL,
  giai VARCHAR(255),
  rs VARCHAR(2000),
  PRIMARY KEY (id),
  FOREIGN KEY (date_id) REFERENCES dimTime(id),
  FOREIGN KEY (maTinh) REFERENCES dimStation(maTinh)
);
