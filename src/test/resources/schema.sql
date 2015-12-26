drop sequence if exists customer_seq;
create sequence customer_seq start with 4;

drop sequence if exists address_seq;
create sequence address_seq start with 3;

drop sequence if exists product_seq;
create sequence product_seq start with 4;

drop sequence if exists orders_seq;
create sequence orders_seq start with 2;

drop sequence if exists line_item_seq;
create sequence line_item_seq start with 3;

drop table if exists Customer;
create table Customer(id bigint default customer_seq.nextval  primary key, email varchar(255), firstname varchar(255), lastname varchar(255), version int);

drop table if exists Address;
create table Address(id bigint default address_seq.nextval primary key, street varchar(255), city varchar(255), country varchar(255), customer_id bigint, version int);

drop table if exists Product;
create table Product(id bigint default product_seq.nextval primary key, name varchar(255), description varchar(255), country varchar(255), price decimal, version int);

drop table if exists Product_Attributes;
create table Product_Attributes(attributes_key varchar(255), product_id bigint, attributes varchar(255), version int);

drop table if exists Orders;
create table Orders(id bigint default orders_seq.nextval primary key, customer_id bigint, shippingaddress_id bigint, version int);

drop table if exists LineItem;
create table LineItem(id bigint default line_item_seq.nextval primary key, product_id bigint, amount int, order_id bigint, price decimal, version int);
