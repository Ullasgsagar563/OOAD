create database HSM1;
use HSM1;
-- Create table for User
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Create table for Ward
CREATE TABLE wards (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    available_beds INT NOT NULL
);

-- Create table for Service
CREATE TABLE services (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);

-- Create table for Payment
CREATE TABLE payments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    amount DOUBLE NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_date DATE NOT NULL
);

-- Create table for Patient
CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    medical_history TEXT NOT NULL
);

-- Create table for Hospital
CREATE TABLE hospitals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

-- Create table for Doctor
CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Create table for Consultancy
CREATE TABLE consultancies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_id INT NOT NULL,
    doctor_name VARCHAR(255) NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

-- Create table for Appointment
CREATE TABLE appointments (
    appointmentId INT AUTO_INCREMENT PRIMARY KEY,
    doctorName VARCHAR(255),
    patientName VARCHAR(255),
    appointmentDate DATETIME
);

-- Create table for Admin (inherits from User)
CREATE TABLE admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
-- Add entries to the users table
INSERT INTO users (username, password, role) VALUES
('user1', 'password1', 'user'),
('user2', 'password2', 'user');

-- Add entries to the wards table
INSERT INTO wards (name, capacity, available_beds) VALUES
('Ward A', 20, 15),
('Ward B', 15, 10);

-- Add entries to the services table
INSERT INTO services (name, price) VALUES
('Service 1', 50.00),
('Service 2', 75.00);

-- Add entries to the payments table
INSERT INTO payments (patient_id, amount, payment_method, payment_date) VALUES
(1, 100.00, 'Cash', '2024-04-06'),
(2, 150.00, 'Credit Card', '2024-04-07');

-- Add entries to the patients table
INSERT INTO patients (name, age, gender, phone_number, address, medical_history) VALUES
('John Doe', 45, 'Male', '1234567890', '123 Main St', 'None'),
('Jane Smith', 35, 'Female', '9876543210', '456 Oak St', 'Allergies');

-- Add entries to the hospitals table
INSERT INTO hospitals (name, address, phone_number) VALUES
('Hospital A', '789 Elm St', '555-1234'),
('Hospital B', '101 Pine St', '555-5678');

-- Add entries to the doctors table
INSERT INTO doctors (name, specialization, phone_number, email) VALUES
('Dr. Johnson', 'Cardiologist', '555-1111', 'johnson@example.com'),
('Dr. Smith', 'Pediatrician', '555-2222', 'smith@example.com');

-- Add entries to the consultancies table
INSERT INTO consultancies (doctor_id, doctor_name, patient_name, date, time) VALUES
(1, 'Dr. Johnson', 'John Doe', '2024-04-06', '10:00'),
(2, 'Dr. Smith', 'Jane Smith', '2024-04-07', '11:00');

-- Add entries to the appointments table
INSERT INTO appointments (doctorName, patientName, appointmentDate) VALUES
('DoctorName1', 'PatientName1', '2024-04-10 08:00:00'),
('DoctorName2', 'PatientName2', '2024-04-15 10:00:00');

-- Add entries to the admins table
INSERT INTO admins (username, password) VALUES
('admin1', 'adminpass1'),
('admin2', 'adminpass2');
