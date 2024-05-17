INSERT INTO supplier (name, phone, email, address) values ('Velocity', '9999999999', 'miemail@velocity.in', 'Bangalore, IN');
INSERT INTO product (name, stock_qty, supplier_id) SELECT 'Keychain', 900, id FROM supplier WHERE name = 'Velocity';
