-- Insert into Lookup table
INSERT INTO lookup (id, name, description, value, active, created_by, created_date)
VALUES (1, 'OFFER_TYPE', 'Discount Offer', 'Discount', true, 'admin', now()),
       (2, 'OFFER_TYPE', 'Buy 1 Get 1 Offer', 'Buy 1 Get 1', true, 'admin', now()),
       (3, 'CUSTOMER_TYPE', 'Regular customer', 'P1', true, 'admin', now()),
       (4, 'ORDER_STATUS', 'Order in progress', 'In Progress', true, 'admin', now()),
       (5, 'PAYMENT_STATUS', 'Payment pending', 'Not Paid', true, 'admin', now());

-- Insert into Category table
INSERT INTO category (id, name, description, cat_type_id, created_by, created_date)
VALUES (1, 'Fruits', 'Fresh organic fruits', 1, 'admin', now()),
       (2, 'Vegetables', 'Organic vegetables', 1, 'admin', now());

-- Insert into Product table
INSERT INTO product (id, category_id, name, description, price, offer_type_id, active, created_by, created_date)
VALUES (1, 1, 'Apple', 'Fresh organic apples', 100, 1, true, 'admin', now()),
       (2, 2, 'Carrot', 'Fresh organic carrots', 50, 2, true, 'admin', now());

-- Insert into Customer table
INSERT INTO customer (id, name, cust_type_id, active, created_by, created_date)
VALUES (1, 'Sai Tejaswi', 3, true, 'admin', now()),
       (2, 'Vishnu Vardhan', 3, true, 'admin', now());

-- Insert into Address table
INSERT INTO address (id, customer_id, address_line1, address_line2, state, pin_code, is_default, active, created_by,
                     created_date)
VALUES (1, 1, 'Street 1', 'Area 1', 'Telangana', '500001', true, true, 'admin', now()),
       (2, 2, 'Street 2', 'Area 2', 'Andhra Pradesh', '500002', true, true, 'admin', now());

-- Insert into Order table
INSERT INTO customer_order (id, customer_id, order_date, address_id, order_status_id, payment_status_id, created_by,
                            created_date)
VALUES (1, 1, now(), 1, 4, 5, 'admin', now());

-- Insert into Order Detail table
INSERT INTO order_detail (id, order_id, product_id, quantity, shipment_status_id, payment_status_id,
                          delivered_date, created_by, created_date)
VALUES (1, 1, 1, 10, 4, 5, now(), 'admin', now());
