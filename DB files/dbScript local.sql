drop  table users

Create table users(
	user_id bigint generated always as identity,
	first_name varchar(50),
	last_name varchar(50),
	email varchar(100),
	password varchar(200),
	is_active Boolean DEFAULT True,
	is_verified Boolean DEFAULT FALSE,
	verification_code varchar(6),
	profile_pic_url varchar(200),
	is_deleted boolean default false,
	date_created TIMESTAMP default CURRENT_TIMESTAMP,
	created_by varchar(100),
	modified_by varchar(100),
	date_modified TIMESTAMP default CURRENT_TIMESTAMP
)
select * from users

drop table mail_configuration

Create table mail_configuration(
	configuration_id bigint generated always as identity,
	sender varchar,
	receiver varchar,
	username varchar,
	password varchar,
	mail_server varchar,
	is_active boolean
)

INSERT INTO mail_configuration (sender, receiver, username, password, mail_server, is_active)
VALUES ('sherazakhtarmalik@gmail.com', 'sheraz321089@gmail.com', 'sheraz', 'alsjflkjads', 'Gmail', true);

update mail_configuration set username = 'sherazakhtarmalik@gmail.com',password = 'gmph ildd dfpr dnyo'

select * from users

alter table users 
add verification_code varchar

update mail_configuration set sender = 'devbotsolutions.com'

alter table mail_configuration add mail_template varchar

alter table users add verification_code varchar

 update mail_configuration set mail_template = '<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify Your Email</title>
    <style>
        body {
            font-family: ''Segoe UI'', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f7;
            margin: 0;
            padding: 0;
        }
        .email-container {
            max-width: 600px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .email-header {
            background-color: #4A90E2;
            color: white;
            text-align: center;
            padding: 24px;
        }
        .email-body {
            padding: 24px;
            color: #333;
        }
        .email-body h2 {
            margin-top: 0;
        }
        .verify-button {
            display: inline-block;
            margin-top: 24px;
            background-color: #4A90E2;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }
        .email-footer {
            font-size: 12px;
            text-align: center;
            padding: 16px;
            color: #888;
        }
        @media (max-width: 600px) {
            .email-container {
                margin: 20px;
            }
        }
    </style>
</head>
<body>

<div class="email-container">
    <div class="email-header">
        <h1>Verify Your Email</h1>
    </div>
    <div class="email-body">
        <h2>Hello 👋</h2>
        <p>Thanks for signing up! Please verify your email address to activate your account.</p>
        <p>Here is your Verification Code:</p>
        <h3>{verificationCode}</h3>
        <p>If you didn’t create this account, you can safely ignore this email.</p>
    </div>
    <div class="email-footer">
        &copy; 2025 DevbotSolutions. All rights reserved.
    </div>
</div>

</body>
</html>
'
delete from users where email = 'sheraz321089@gmail.com'











select * from users

update users set email = 'test@test.com'

update users set password = '$2a$10$qUi9VZ.AgvyHl36Ty/XYvOu00IRBKdtnam3/vVJtniqLhwNQvafR6'
							"$2a$10$dC7oY4zMKXADLs45O.bd/eS51sZAaKiS5DVJPMThn/0VNIzDYrH3S"

drop table expenses
CREATE TABLE expenses (
    expense_id bigint  generated always as identity,
    title VARCHAR(255) ,
	details varchar(500) ,
    amount DECIMAL(10, 2),
    category varchar(100),
	user_id bigint,
    is_group BOOLEAN DEFAULT FALSE,
	is_deleted boolean default false,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(100),
	date_modified timestamp default current_timestamp,
	modified_by varchar(100)
);


select * from expenses




drop table categories


CREATE TABLE categories (
    category_id bigint  generated always as identity,
    name VARCHAR(255) ,
	user_id bigint ,
    is_global Boolean default false,
	is_deleted boolean default false,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(100),
	date_modified timestamp default current_timestamp,
	modified_by varchar(100)
);






CREATE TABLE groups (
    group_id bigint  generated always as identity,
    name VARCHAR(255) ,
	user_id bigint ,
	is_deleted boolean default false,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(100),
	date_modified timestamp default current_timestamp,
	modified_by varchar(100)
);


CREATE TABLE group_memebrs (
    group_memeber_id bigint  generated always as identity,
    name VARCHAR(255) ,
	email VARCHAR(255) ,
	phone VARCHAR(50) ,
	is_deleted boolean default false,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(100),
	date_modified timestamp default current_timestamp,
	modified_by varchar(100)
);







select * 
from users
Where user_id >2 and is_active = TRUE and is_verified = TRUE




CREATE OR REPLACE FUNCTION fnPageinationExample(
    pageNumber INTEGER,
    pageSize INTEGER
)
RETURNS TABLE (
    user_id BIGINT,
    first_name VARCHAR,
    last_name VARCHAR,
    email VARCHAR,
    is_active BOOLEAN,
    is_verified BOOLEAN,
    date_created TIMESTAMP,
    total_pages INTEGER
) 
LANGUAGE plpgsql
AS $$
DECLARE
    total_count BIGINT;
BEGIN
    SELECT COUNT(*) INTO total_count FROM users;

   
    RETURN QUERY
    SELECT 
        u.user_id,
        u.first_name,
        u.last_name,
        u.email,
        u.is_active,
        u.is_verified,
        u.date_created,
        CEIL(total_count::NUMERIC / pageSize)::INT AS total_pages
    FROM users u
    ORDER BY u.user_id
    LIMIT pageSize 
    OFFSET (pageNumber - 1) * pageSize;
END;
$$;




select fnPageinationExample (1,10)


select * from users

select * from expenses








alter table mail_configuration
add mail_template varchar
update mail_configuration set mail_template = '
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify Your Email</title>
    <style>
        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f7;
            margin: 0;
            padding: 0;
        }
        .email-container {
            max-width: 600px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .email-header {
            background-color: #4A90E2;
            color: white;
            text-align: center;
            padding: 24px;
        }
        .email-body {
            padding: 24px;
            color: #333;
        }
        .email-body h2 {
            margin-top: 0;
        }
        .verify-button {
            display: inline-block;
            margin-top: 24px;
            background-color: #4A90E2;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }
        .email-footer {
            font-size: 12px;
            text-align: center;
            padding: 16px;
            color: #888;
        }
        @media (max-width: 600px) {
            .email-container {
                margin: 20px;
            }
        }
    </style>
</head>
<body>

<div class="email-container">
    <div class="email-header">
        <h1>Verify Your Email</h1>
    </div>
    <div class="email-body">
        <h2>Hello 👋</h2>
        <p>Thanks for signing up! Please verify your email address to activate your account.</p>
        <p>Here is your verification code:</p>
        <h3>{verificationCode}</h3>
        <p>If you didn’t create this account, you can safely ignore this email.</p>
    </div>
    <div class="email-footer">
        &copy; 2025 DevbotSolutions.com. All rights reserved.
    </div>
</div>

</body>
</html>
'


select * from users


update users set password = '$2a$10$nlK8P1aIgxvdFcDCR1VVS.dVDlrqi6HKq1AUSNtvOa1M28zfWeDTK'





select * from expenses
go
select * from categories
go
select * from mail_configuration
go
select * from users





"expense_id"	"title"	"details"	"amount"	"category"	"user_id"	"is_group"	"is_deleted"	"date_created"	"created_by"	"date_modified"	"modified_by"

"category_id"	"name"	"user_id"	"is_global"	"is_deleted"	"date_created"	"created_by"	"date_modified"	"modified_by"


"configuration_id"	"sender"	"receiver"	"username"	"password"	"mail_server"	"is_active"

"user_id"	"first_name"	"last_name"	"email"	"password"	"is_active"	"is_verified"	"verification_code"	"profile_pic_url"	"is_deleted"	"date_created"	"created_by"	"modified_by"	"date_modified"




select expense_details.amount_to_pay,* from expense_details

update expense_details set amount_to_pay = 33 where expense_details.expense_details_id = 11

select * from expense_settlements





