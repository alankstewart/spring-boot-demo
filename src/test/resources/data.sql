insert into Customer (id, email, firstname, lastname) values (1, 'alankstewart@gmail.com', 'Alan', 'Stewart');
insert into Customer (id, email, firstname, lastname) values (2, 'carter@dmband.com', 'Carter', 'Beauford');
insert into Customer (id, email, firstname, lastname) values (3, 'boyd@dmband.com', 'Boyd', 'Tinsley');

insert into Address (id, street, suburb, state, postcode, customer_id) values (1, '2 Jubilee Street', 'Wahroonga', 'NSW', '2076', 1);
insert into Address (id, street, suburb, state, postcode, customer_id) values (2, '65 Martin Place', 'Sydney', 'NSW', '2000', 1);

insert into Product (id, name, description, price) values (1, 'iPad', 'Apple tablet device', 499.00);
insert into Product (id, name, description, price) values (2, 'MacBook Pro', 'Apple notebook', 1299.00);
insert into Product (id, name, description, price) values (3, 'Dock', 'Dock for iPhone/iPad', 49.00);

insert into Product_Attributes (attributes_key, product_id, attributes) values ('connector', 1, 'socket');
insert into Product_Attributes (attributes_key, product_id, attributes) values ('connector', 3, 'plug');

insert into Orders (id, customer_id, shippingaddress_id, billingAddress_id) values (1, 1, 2, 2);
insert into LineItem (id, product_id, amount, order_id, price) values (1, 1, 2, 1, 499.00);
insert into LineItem (id, product_id, amount, order_id, price) values (2, 2, 1, 1, 1299.00);
