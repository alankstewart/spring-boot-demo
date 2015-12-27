drop sequence if exists customer_seq;
create sequence customer_seq minvalue 1 start with 4 increment by 50;

drop sequence if exists address_seq;
create sequence address_seq minvalue 1 start with 3 increment by 50;

drop sequence if exists product_seq;
create sequence product_seq minvalue 1 start with 4 increment by 50;

drop sequence if exists orders_seq;
create sequence orders_seq minvalue 1 start with 2 increment by 50;

drop sequence if exists line_item_seq;
create sequence line_item_seq minvalue 1 start with 3 increment by 50;

drop table if exists Customer;
create table Customer(id bigint auto_increment  primary key, email varchar(255), firstname varchar(255), lastname varchar(255), version int);

drop table if exists Address;
create table Address(id bigint auto_increment primary key, street varchar(255), suburb varchar(255), state varchar(3), postcode varchar(4), customer_id bigint, version int);

drop table if exists Product;
create table Product(id bigint auto_increment primary key, name varchar(255), description varchar(255), state varchar(255), price decimal, version int);

drop table if exists Product_Attributes;
create table Product_Attributes(attributes_key varchar(255), product_id bigint, attributes varchar(255), version int);

drop table if exists Orders;
create table Orders(id bigint auto_increment primary key, customer_id bigint, shippingaddress_id bigint, billingAddress_id bigint, version int);

drop table if exists LineItem;
create table LineItem(id bigint auto_increment primary key, product_id bigint, amount int, order_id bigint, price decimal, version int);
