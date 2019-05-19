CREATE TABLE companies
(
  id       int(5) UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name     varchar(50) UNIQUE  NOT NULL,
  password varchar(50)         NOT NULL,
  email    varchar(50) UNICODE NOT NULL
);

CREATE TABLE customers
(
  id       int(5) UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name     varchar(50) UNIQUE NOT NULL,
  password varchar(50)        NOT NULL
);

CREATE TABLE coupons
(
  id         int(5) PRIMARY KEY AUTO_INCREMENT,
  title      varchar(50)            NOT NULL,
  start_date datetime default NOW() NOT NULL,
  end_date   datetime default NOW() NOT NULL,
  amount  int(5) UNSIGNED NOT NULL DEFAULT 0,
  type enum('RESTAURANT', 'ELECTRICITY', 'FOOD', 'HEALTH', 'SPORTS', 'CAMPING', 'TRAVELING') NOT NULL,
  message varchar(255) NOT NULL,
  price decimal(8,2) NOT NULL,
  image_path varchar(255)
);

CREATE TABLE customer_coupon
(
  customer_id int(5) NOT NULL,
  coupon_id   int(5) NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
  FOREIGN KEY (coupon_id) REFERENCES coupons (coupon_id),
  CONSTRAINT pk_customer_coupon PRIMARY KEY (customer_id, coupon_id)
);

CREATE TABLE company_coupon
(
  company_id int(5) NOT NULL,
  coupon_id  int(5) NOT NULL,
  FOREIGN KEY (company_id) REFERENCES companies (company_id),
  FOREIGN KEY (coupon_id) REFERENCES coupons (coupon_id),
  CONSTRAINT pk_company_coupon PRIMARY KEY (company_id, coupon_id)
);