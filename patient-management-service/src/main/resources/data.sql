INSERT INTO patient (email, first_name, last_name, address, city, state, zip_code, phone_number)
VALUES
('akron.patient@gmail.com', 'Michael', 'Brown', '742 Market Street', 'Akron', 'Ohio', '44308', '+13305550198'),
('kent.patient@gmail.com', 'Emily', 'Wilson', '215 University Drive', 'Kent', 'Ohio', '44240', '+13305550276')
ON CONFLICT (email) DO NOTHING;

