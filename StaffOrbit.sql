CREATE DATABASE stafforbit_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'staff_user1'@'localhost' IDENTIFIED BY 'staff_pass';
GRANT ALL PRIVILEGES ON staffsync_db.* TO 'staff_user'@'localhost';
FLUSH PRIVILEGES;

use stafforbit_db;
show tables;