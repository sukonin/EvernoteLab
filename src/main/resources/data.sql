INSERT INTO user (id, email, password, username) VALUES (1, '1@epam.com', 'pass', 'testname1');
INSERT INTO user (id, email, password, username) VALUES (2, '2@epam.com', 'pass', 'testname2');
INSERT INTO user (id, email, password, username) VALUES (3, '3@epam.com', 'pass', 'testname3');
INSERT INTO user (id, email, password, username) VALUES (4, '4@epam.com', 'pass', 'testname1');
INSERT INTO user (id, email, password, username) VALUES (5, '5@epam.com', 'pass', 'testname1');
INSERT INTO user (id, email, password, username) VALUES (6, '6@epam.com', 'pass', 'testname2');
INSERT INTO user (id, email, password, username) VALUES (7, '7@epam.com', 'pass', 'testname5');

INSERT INTO notebook (id, title, user_id) VALUES (1, 'title', 1);
INSERT INTO notebook (id, title, user_id) VALUES (2, 'title2', 1);
INSERT INTO notebook (id, title, user_id) VALUES (3, 'title', 2);
INSERT INTO notebook (id, title, user_id) VALUES (4, 'title', 2);
INSERT INTO notebook (id, title, user_id) VALUES (5, 'title', 3);
INSERT INTO notebook (id, title, user_id) VALUES (6, 'title', 3);
INSERT INTO notebook (id, title, user_id) VALUES (7, 'title', 4);
INSERT INTO notebook (id, title, user_id) VALUES (8, 'title', 4);
INSERT INTO notebook (id, title, user_id) VALUES (9, 'title', 5);
INSERT INTO notebook (id, title, user_id) VALUES (10, 'title', 5);
INSERT INTO notebook (id, title, user_id) VALUES (11, 'title', 5);
INSERT INTO notebook (id, title, user_id) VALUES (12, 'title', 5);
INSERT INTO notebook (id, title, user_id) VALUES (13, 'title', 6);
INSERT INTO notebook (id, title, user_id) VALUES (14, 'title', 7);

INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (1, 'content', '2012-06-18', TRUE, 'title1', 1);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (2, 'content', '2012-06-18', FALSE, 'title2', 1);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (3, 'content', '2012-06-18', TRUE, 'title3', 1);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (4, 'content', '2012-06-18', FALSE, 'title1', 2);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (5, 'content', '2012-06-18', TRUE, 'title2', 2);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (6, 'content', '2012-06-18', FALSE, 'title3', 2);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (7, 'content', '2012-06-18', TRUE, 'title1', 3);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (8, 'content', '2012-06-18', FALSE, 'title2', 4);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (9, 'content', '2012-06-18', TRUE, 'title3', 5);
INSERT INTO note (id, content, date, isactive, title, notebook_id)
VALUES (10, 'content1', '2012-06-18', FALSE, 'title1', 6);

INSERT INTO tag (id, tag) VALUES (1, 'priority');
INSERT INTO tag (id, tag) VALUES (2, 'home');
INSERT INTO tag (id, tag) VALUES (3, 'vacation');
INSERT INTO tag (id, tag) VALUES (4, 'work');
INSERT INTO tag (id, tag) VALUES (5, 'holiday');
INSERT INTO tag (id, tag) VALUES (6, 'dream');
INSERT INTO tag (id, tag) VALUES (7, 'todo');
INSERT INTO tag (id, tag) VALUES (8, 'what');
INSERT INTO tag (id, tag) VALUES (9, 'F@ck');
INSERT INTO tag (id, tag) VALUES (10, '05.42am...');