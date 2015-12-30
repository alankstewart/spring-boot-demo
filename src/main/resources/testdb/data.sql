insert into Customer (id, email, firstname, lastname, version) values (1, 'alankstewart@gmail.com', 'Alan', 'Stewart', 0);
insert into Customer (id, email, firstname, lastname, version) values (2, 'carter@dmband.com', 'Carter', 'Beauford', 0);
insert into Customer (id, email, firstname, lastname, version) values (3, 'boyd@dmband.com', 'Boyd', 'Tinsley', 0);

insert into Address (id, street, suburb, state, postcode, customer_id, version) values (1, '2 Jubilee Street', 'Wahroonga', 'NSW', '2076', 1, 0);
insert into Address (id, street, suburb, state, postcode, customer_id, version) values (2, '65 Martin Place', 'Sydney', 'NSW', '2000', 1, 0);

insert into Product (id, name, description, price, version) values (1, 'iPad', 'Apple tablet device', 499.00, 0);
insert into Product (id, name, description, price, version) values (2, 'MacBook Pro', 'Apple notebook', 1299.00, 0);
insert into Product (id, name, description, price, version) values (3, 'Dock', 'Dock for iPhone/iPad', 49.00, 0);

insert into Product_Attributes (attributes_key, product_id, attributes, version) values ('connector', 1, 'socket', 0);
insert into Product_Attributes (attributes_key, product_id, attributes, version) values ('connector', 3, 'plug', 0);

insert into Orders (id, customer_id, shippingaddress_id, billingAddress_id, created, version) values (1, 1, 2, 2, '2012-12-30', 0);
insert into LineItem (id, product_id, amount, order_id, price, version) values (1, 1, 2, 1, 499.00, 0);
insert into LineItem (id, product_id, amount, order_id, price, version) values (2, 2, 1, 1, 1299.00, 0);
