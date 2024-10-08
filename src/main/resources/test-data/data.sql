-- Insert into Lookup table
INSERT INTO lookup (id, name, description, value, active, created_by, created_date)
VALUES (gen_random_uuid(), 'OFFER_TYPE', 'Discount Offer', 'Discount', true, 'admin', now()),
       (gen_random_uuid(), 'OFFER_TYPE', 'Buy 1 Get 1 Offer', 'Buy 1 Get 1', true, 'admin', now()),
       (gen_random_uuid(), 'NONE', 'N/A', 'N/A', false, 'admin', now()),
       (gen_random_uuid(), 'CUSTOMER_TYPE', 'Regular customer', 'P1', true, 'admin', now()),
       (gen_random_uuid(), 'ORDER_STATUS', 'Order in progress', 'In Progress', true, 'admin', now()),
       (gen_random_uuid(), 'PAYMENT_STATUS', 'Payment pending', 'Not Paid', true, 'admin', now());

-- Insert into Category table
INSERT INTO category (id, name, description, parent_id, cat_type_id, created_by, created_date)
VALUES (gen_random_uuid(), 'Fruits', 'Fresh organic fruits', null, null, 'admin', now()),
       (gen_random_uuid(), 'Fruits and Vegetables', 'Fresh organic produce', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Grains and Cereals', 'Organic grains and cereals', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Pulses and Legumes', 'Organic pulses and legumes', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Spices and Condiments', 'Organic spices and condiments', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Oils and Ghee', 'Cold-pressed oils and ghee', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Dairy Products', 'Organic dairy products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Beverages', 'Organic beverages', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Snacks and Sweets', 'Organic snacks and sweets', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Bakery and Bread', 'Organic bakery products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Meat and Poultry', 'Organic meat and poultry', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Personal Care', 'Organic personal care products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Home and Living', 'Eco-friendly home and living products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Baby Products', 'Organic baby products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Health and Wellness', 'Organic health and wellness products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Packaged Foods', 'Organic packaged foods', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Pet Care', 'Organic pet care products', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Gifts and Hampers', 'Organic gift baskets and hampers', null, null, 'admin', NOW()),
       (gen_random_uuid(), 'Gardening', 'Organic gardening supplies', null, null, 'admin', NOW());

-- Insert into Category table (First-level subcategories for 'Fruits and Vegetables')
INSERT INTO category (id, name, description, parent_id, cat_type_id, created_by, created_date)
VALUES (gen_random_uuid(), 'Fresh Fruits', 'All types of fresh fruits',
        (SELECT id FROM category WHERE name = 'Fruits and Vegetables'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Fresh Vegetables', 'All types of fresh vegetables',
        (SELECT id FROM category WHERE name = 'Fruits and Vegetables'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Herbs', 'Fresh herbs',
        (SELECT id FROM category WHERE name = 'Fruits and Vegetables'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Exotic Fruits and Vegetables', 'Exotic fruits and vegetables',
        (SELECT id FROM category WHERE name = 'Fruits and Vegetables'), null, 'admin', NOW());

-- Insert into Category table (First-level subcategories for 'Grains and Cereals')
INSERT INTO category (id, name, description, parent_id, cat_type_id, created_by, created_date)
VALUES (gen_random_uuid(), 'Rice', 'Organic rice varieties',
        (SELECT id FROM category WHERE name = 'Grains and Cereals'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Wheat', 'Organic wheat varieties',
        (SELECT id FROM category WHERE name = 'Grains and Cereals'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Millets', 'Ragi, Bajra, Jowar, etc.',
        (SELECT id FROM category WHERE name = 'Grains and Cereals'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Quinoa', 'Organic quinoa',
        (SELECT id FROM category WHERE name = 'Grains and Cereals'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Oats', 'Organic oats',
        (SELECT id FROM category WHERE name = 'Grains and Cereals'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Barley', 'Organic barley',
        (SELECT id FROM category WHERE name = 'Grains and Cereals'), null, 'admin', NOW());

-- Insert into Category table (First-level subcategories for 'Pulses and Legumes')
INSERT INTO category (id, name, description, parent_id, cat_type_id, created_by, created_date)
VALUES (gen_random_uuid(), 'Lentils', 'Red, Green, Brown lentils',
        (SELECT id FROM category WHERE name = 'Pulses and Legumes'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Chickpeas', 'Organic chickpeas',
        (SELECT id FROM category WHERE name = 'Pulses and Legumes'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Green Gram', 'Moong dal varieties',
        (SELECT id FROM category WHERE name = 'Pulses and Legumes'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Black Gram', 'Urad dal varieties',
        (SELECT id FROM category WHERE name = 'Pulses and Legumes'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Kidney Beans', 'Rajma varieties',
        (SELECT id FROM category WHERE name = 'Pulses and Legumes'), null, 'admin', NOW()),
       (gen_random_uuid(), 'Peas', 'Organic peas',
        (SELECT id FROM category WHERE name = 'Pulses and Legumes'), null, 'admin', NOW());

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
