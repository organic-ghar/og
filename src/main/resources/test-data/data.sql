-- Insert into Lookup table
INSERT INTO lookup (id, name, description, value, active, created_by, created_date)
VALUES (gen_random_uuid(), 'OFFER_TYPE', 'Discount Offer', 'Discount', true, 'admin', now()),
       (gen_random_uuid(), 'OFFER_TYPE', 'Buy 1 Get 1 Offer', 'Buy 1 Get 1', true, 'admin', now()),
       (gen_random_uuid(), 'CUSTOMER_TYPE', 'Regular customer', 'P1', true, 'admin', now()),
       (gen_random_uuid(), 'ORDER_STATUS', 'Order in progress', 'In Progress', true, 'admin', now()),
       (gen_random_uuid(), 'PAYMENT_STATUS', 'Payment pending', 'Not Paid', true, 'admin', now());

-- Insert into Category table
INSERT INTO category (id, name, description, cat_type_id, created_by, created_date)
VALUES (gen_random_uuid(), 'Fruits', 'Fresh organic fruits', (SELECT id FROM lookup WHERE name = 'OFFER_TYPE' AND value = 'Discount' LIMIT 1), 'admin', now()),
       (gen_random_uuid(), 'Vegetables', 'Organic vegetables', (SELECT id FROM lookup WHERE name = 'OFFER_TYPE' AND value = 'Buy 1 Get 1' LIMIT 1), 'admin', now());

-- Insert into Product table
INSERT INTO product (id, category_id, name, description, price, offer_type_id, active, created_by, created_date)
VALUES (gen_random_uuid(), (SELECT id FROM category WHERE name = 'Fruits' LIMIT 1), 'Apple', 'Fresh organic apples', 100, (SELECT id FROM lookup WHERE name = 'OFFER_TYPE' AND value = 'Discount' LIMIT 1), true, 'admin', now()),
       (gen_random_uuid(), (SELECT id FROM category WHERE name = 'Vegetables' LIMIT 1), 'Carrot', 'Fresh organic carrots', 50, (SELECT id FROM lookup WHERE name = 'OFFER_TYPE' AND value = 'Buy 1 Get 1' LIMIT 1), true, 'admin', now());

-- Insert into Customer table
INSERT INTO customer (id, name, cust_type_id, active, created_by, created_date)
VALUES (gen_random_uuid(), 'Sai Tejaswi', (SELECT id FROM lookup WHERE name = 'CUSTOMER_TYPE' AND value = 'P1' LIMIT 1), true, 'admin', now()),
       (gen_random_uuid(), 'Vishnu Vardhan', (SELECT id FROM lookup WHERE name = 'CUSTOMER_TYPE' AND value = 'P1' LIMIT 1), true, 'admin', now());

-- Insert into Address table
INSERT INTO address (id, customer_id, address_line1, address_line2, state, pin_code, is_default, active, created_by,
                     created_date)
VALUES (gen_random_uuid(), (SELECT id FROM customer WHERE name = 'Sai Tejaswi' LIMIT 1), 'Street 1', 'Area 1', 'Telangana', '500001', true, true, 'admin', now()),
       (gen_random_uuid(), (SELECT id FROM customer WHERE name = 'Vishnu Vardhan' LIMIT 1), 'Street 2', 'Area 2', 'Andhra Pradesh', '500002', true, true, 'admin', now());

-- Insert into Order table
INSERT INTO customer_order (id, customer_id, order_date, address_id, order_status_id, payment_status_id, created_by,
                            created_date)
VALUES (gen_random_uuid(), (SELECT id FROM customer WHERE name = 'Sai Tejaswi' LIMIT 1), now(), (SELECT id FROM address WHERE customer_id = (SELECT id FROM customer WHERE name = 'Sai Tejaswi' LIMIT 1) LIMIT 1), (SELECT id FROM lookup WHERE name = 'ORDER_STATUS' AND value = 'In Progress' LIMIT 1), (SELECT id FROM lookup WHERE name = 'PAYMENT_STATUS' AND value = 'Not Paid' LIMIT 1), 'admin', now());

-- Insert into Order Detail table
INSERT INTO order_detail (id, order_id, product_id, quantity, shipment_status_id, payment_status_id,
                          delivered_date, created_by, created_date)
VALUES (gen_random_uuid(), (SELECT id FROM customer_order WHERE customer_id = (SELECT id FROM customer WHERE name = 'Sai Tejaswi' LIMIT 1) LIMIT 1), (SELECT id FROM product WHERE name = 'Apple' LIMIT 1), 10, (SELECT id FROM lookup WHERE name = 'ORDER_STATUS' AND value = 'In Progress' LIMIT 1), (SELECT id FROM lookup WHERE name = 'PAYMENT_STATUS' AND value = 'Not Paid' LIMIT 1), now(), 'admin', now());
