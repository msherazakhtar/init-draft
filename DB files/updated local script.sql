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
	reciever varchar,
	username varchar,
	password varchar,
	mail_server varchar,
	is_active boolean
)

INSERT INTO mail_configuration (sender, reciever, username, password, mail_server, is_active)
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

drop table group_members
CREATE TABLE group_members (
    group_member_id bigint  generated always as identity,
    name VARCHAR(255) ,
	email VARCHAR(255) ,
	phone VARCHAR(50) ,
	group_id bigint,
	is_deleted boolean default false,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(100),
	date_modified timestamp default current_timestamp,
	modified_by varchar(100)
);



select * from group_members



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


"configuration_id"	"sender"	"reciever"	"username"	"password"	"mail_server"	"is_active"

"user_id"	"first_name"	"last_name"	"email"	"password"	"is_active"	"is_verified"	"verification_code"	"profile_pic_url"	"is_deleted"	"date_created"	"created_by"	"modified_by"	"date_modified"




select expense_details.amount_to_pay,* from expense_details

update expense_details set amount_to_pay = 33 where expense_details.expense_details_id = 11

select * from expense_settlements


select * from groups


select * from group_members where group_id = 2
delete from group_members where group_id = 2


select * from mail_configuration

select * from expenses

select * from groups



 SELECT e.expense_id,
        e.title,
        e.details,
        e.amount,
        e.category,g.name group_name,e.expense_date 
    FROM
        expenses  e
		join groups g on g.group_id = e.group_id
    WHERE
        e.user_id = 1
        AND Date(e.expense_date) BETWEEN Date('06/01/2025') AND Date('12/30/2026') 
        AND e.is_deleted = false 
    ORDER BY
        e.date_created DESC 

select * from expense_details where expense_id = 4


update expenses set is_deleted =  true


select * from expense_settlements



alter table expense_settlements
add receiver_expense_detail_id 




select * from groups

select * from group_members


select * from expense_details where expense_id = 28


select * from users
update users set email = 'sherazakhtarmalik@gmail.com' where email = 'sherazakhtarmaliK@gmail.com'



select * from expense_details order by 1 desc

select * from expense_settlements


select * from expenses
select * from groups
SELECT 
    g.group_id,
    g.name,
    g.user_id,
    g.date_created,
    g.created_by,
    g.date_modified,
    g.modified_by,
    COALESCE(e.total_expense, 0)  AS total_expense,
    COALESCE(m.total_members, 0)  AS total_members
FROM groups g

LEFT JOIN (
    SELECT group_id, SUM(amount) AS total_expense
    FROM expenses
    WHERE is_deleted = false
    GROUP BY group_id
) e ON e.group_id = g.group_id

LEFT JOIN (
    SELECT group_id, COUNT(*) AS total_members
    FROM group_members
    WHERE is_deleted = false
    GROUP BY group_id
) m ON m.group_id = g.group_id

WHERE g.user_id = 2
  AND g.is_deleted = false
ORDER BY g.date_created DESC;




select * from expense_details where expense_id = 42


 Select distinct es.expense_settlement_id,es.settlement_date,mp.name paid_by,
            mr.name paid_to,g.name group_name,es.settlement_amount,e.title
            from expense_settlements es
            join group_members mp on mp.group_member_id = es.paid_by
            join group_members mr on mr.group_member_id = es.paid_to
            join groups g on g.group_id = mp.group_id
            join expenses e on e.expense_id = es.expense_id
            where 
             coalesce(es.is_deleted,false) = false and g.user_id = 2
            order by es.settlement_date desc

select * from group_members where group_id = 11
select * from users

Select * from expenses

Select g.group_id,g.name,Sum(e.amount),
(select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false)total_members
from expenses e
join groups g on g.group_id = e.group_id
left join group_members gm on gm.group_id = g.group_id AND gm.is_deleted = false
where e.is_deleted = false and Date(e.expense_date) BETWEEN Date(start_date) AND Date(end_date) 
and g.group_id = group_id or group_id = ''
group by g.group_id,g.name


select * from group_members where group_id = 2 AND is_deleted = false
SELECT column_name, data_type 
FROM information_schema.columns 
WHERE table_name = 'expenses' 
AND column_name = 'expense_date';





Select e.user_id,g.group_id,g.name,Sum(e.amount),
(select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false)total_members
from expenses e
join groups g on g.group_id = e.group_id
left join group_members gm on gm.group_id = g.group_id AND gm.is_deleted = false
where e.is_deleted = false and Date(e.expense_date) BETWEEN Date('01-01-2026') AND Date('06-12-2026') 
group by e.user_id,g.group_id,g.name



Select e.user_id,g.group_id,g.name,Sum(e.amount),
Cast((select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false) as integer)total_members
from expenses e
join groups g on g.group_id = e.group_id
left join group_members gm on gm.group_id = g.group_id AND gm.is_deleted = false
where e.is_deleted = false and Date(e.expense_date) BETWEEN Date('01-01-2026') AND Date('06-12-2026') 

group by e.user_id,g.group_id,g.name


Select g.group_id,g.name group_name,Sum(e.amount) total_expense, 
Cast((select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false) as Integer)total_members
from expenses e
join groups g on g.group_id = e.group_id 
join group_members gm on gm.group_id = g.group_id AND gm.is_deleted = false 
where e.is_deleted = false and Date(e.expense_date) BETWEEN Date('03-31-2026') AND Date('04-29-2026') and e.user_id = 2
and g.is_deleted = false--and (CAST(e.group_id AS VARCHAR) =  or :groupId = '-1')  
group by g.group_id,g.name

select * from groups where is_deleted = false
select * from users



Select * from expense_settlements

Select * from expenses limit 1
Select * from expense_details limit 1
Select * from groups limit 1
select * from group_members limit 1








-- ============================================================
-- BARABAR: Group Expense Report
-- Params: :group_id, :start_date, :end_date
-- ============================================================

-- 1. SUMMARY: Total amount & amount per head for the period
SELECT
    g.name                          AS group_name,
    COUNT(DISTINCT e.expense_id)    AS total_expenses,
    SUM(e.amount)                   AS total_amount,
    AVG(e.amount_per_head)          AS avg_amount_per_head,
    SUM(e.amount_per_head)          AS total_per_head
FROM expenses e
JOIN groups g ON g.group_id = e.group_id
WHERE e.group_id   = 5
  AND e.is_deleted = false
  AND Date(e.expense_date) BETWEEN Date('03-31-2026') AND Date('04-29-2026')
GROUP BY g.name;

select * from expenses
-- 2. EXPENSE BREAKDOWN: Each expense with per-member split
SELECT
    e.expense_id,
    e.title,
    e.category,
    e.expense_date,
    e.amount                        AS total_amount,
    e.amount_per_head,
    gm.name                         AS member_name,
    ed.is_settled
FROM expenses e
JOIN expense_details ed  ON ed.expense_id      = e.expense_id
JOIN group_members gm    ON gm.group_member_id = ed.group_member_id
WHERE e.group_id    =5
  AND e.is_deleted  = false
  AND COALESCE (ed.is_deleted,false) = false
  AND Date(e.expense_date) BETWEEN Date('03-31-2026') AND Date('04-29-2026')
ORDER BY e.expense_date, e.expense_id, gm.name;

select * from expenses where group_id = 16
select * from expense_details where expense_id = 45
select * from group_members where group_id =16
-- 3. WHO OWES WHOM: Net balance per member for the period
WITH member_balances AS (
    SELECT
        gm.group_member_id,
        gm.name                         AS member_name,
        gm.email,
        -- What they are owed by others (they paid on behalf)
        COALESCE(SUM(ed.amount_to_get), 0) AS total_to_receive,
        -- What they owe to others
        COALESCE(SUM(ed.amount_to_pay), 0) AS total_to_pay,
        -- Net: positive = others owe them, negative = they owe others
        COALESCE(SUM(ed.amount_to_get), 0)
            - COALESCE(SUM(ed.amount_to_pay), 0) AS net_balance,
        -- Unsettled only
        COALESCE(SUM(CASE WHEN ed.is_settled = false THEN ed.pending_amount ELSE 0 END), 0)
            AS total_pending
    FROM group_members gm
    JOIN expense_details ed ON ed.group_member_id = gm.group_member_id
    JOIN expenses e          ON e.expense_id       = ed.expense_id
    WHERE e.group_id    = 9
      AND e.is_deleted  = false
      AND COALESCE (ed.is_deleted,false) = false
      AND Date(e.expense_date) BETWEEN Date('03-31-2026') AND Date('04-29-2026')
    GROUP BY gm.group_member_id, gm.name, gm.email
)
SELECT
    member_name,
    email,
    total_to_receive,
    total_to_pay,
    total_pending,
    net_balance,
    CASE
        WHEN net_balance > 0 THEN 'Gets back ' || net_balance::TEXT
        WHEN net_balance < 0 THEN 'Owes '      || ABS(net_balance)::TEXT
        ELSE 'Settled up'
    END AS status_label
FROM member_balances
ORDER BY net_balance DESC;


Select g.group_id,g.name group_name,Sum(e.amount) total_expense, 
Cast((select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false) as Integer)total_members
from expenses e
join groups g on g.group_id = e.group_id 
join group_members gm on gm.group_id = g.group_id AND gm.is_deleted = false 
where e.is_deleted = false and Date(e.expense_date) BETWEEN Date('03-31-2026') AND Date('04-29-2026') and e.user_id = 1
and g.is_deleted = false--and (CAST(e.group_id AS VARCHAR) =  or :groupId = '-1')  
group by g.group_id,g.name


SELECT e.expense_id,
       e.title,
       e.details,
       e.amount,
       e.category,
       g.name AS group_name,
       e.expense_date,
       CEIL(COUNT(*) OVER()::float /10) AS total_pages    -- adds total count to every row
FROM expenses e
LEFT JOIN groups g ON g.group_id = e.group_id

ORDER BY e.date_created DESC
LIMIT 1
OFFSET (1 - 1) * 1







select * from mail_configuration where mail_server = 'Gmail'
select * from users


Delete from users where user_id  = 24

