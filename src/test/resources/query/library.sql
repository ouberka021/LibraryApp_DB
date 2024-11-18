SELECT count(*) from books;
SELECT count(*) from users;
SELECT count(*) from book_borrow where is_returned = 0;
select name from book_categories;

select name,isbn,year,author,description from books where name = 'Agile Testing';
select  full_name from users where email = 'librarian30@library' ;
select status from users where email = 'Toi@library';

# Live session queries
-- How many active/Inactive users?
select * from users;
SELECT count(*) FROM users
        where status = 'Active' and user_group_id <> 1; -- 721 active user  UI- 721

SELECT count(*) FROM users
where status = 'INACTIVE'; -- 312 in-active user -- UI - 312