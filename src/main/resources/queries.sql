CREATE TABLE books (
                       book_id serial PRIMARY KEY,
                       bookName VARCHAR ( 50 ) NOT NULL,
                       author VARCHAR ( 50 ) NOT NULL,
                       migrated BOOLEAN DEFAULT FALSE
);

INSERT INTO books VALUES(1, 'book1', 'author1');
INSERT INTO books VALUES(2, 'book2', 'author2');


