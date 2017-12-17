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
INSERT INTO notebook (id, title, user_id) VALUES (4, 'title2', 2);
INSERT INTO notebook (id, title, user_id) VALUES (5, 'title1', 3);
INSERT INTO notebook (id, title, user_id) VALUES (6, 'title2', 3);
INSERT INTO notebook (id, title, user_id) VALUES (7, 'title1', 4);
INSERT INTO notebook (id, title, user_id) VALUES (8, 'title2', 4);
INSERT INTO notebook (id, title, user_id) VALUES (9, 'title1', 5);
INSERT INTO notebook (id, title, user_id) VALUES (10, 'title2', 5);
INSERT INTO notebook (id, title, user_id) VALUES (11, 'title3', 5);
INSERT INTO notebook (id, title, user_id) VALUES (12, 'title4', 5);
INSERT INTO notebook (id, title, user_id) VALUES (13, 'title1', 6);
INSERT INTO notebook (id, title, user_id) VALUES (14, 'title1', 7);

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

INSERT INTO tag (id, tag, user_id) VALUES (1, 'priority', 1);
INSERT INTO tag (id, tag, user_id) VALUES (2, 'home', 1);
INSERT INTO tag (id, tag, user_id) VALUES (3, 'vacation', 1);
INSERT INTO tag (id, tag, user_id) VALUES (4, 'work', 2);
INSERT INTO tag (id, tag, user_id) VALUES (5, 'holiday', 2);
INSERT INTO tag (id, tag, user_id) VALUES (6, 'dream', 2);
INSERT INTO tag (id, tag, user_id) VALUES (7, 'todo', 1);
INSERT INTO tag (id, tag, user_id) VALUES (8, 'what', 1);
INSERT INTO tag (id, tag, user_id) VALUES (9, 'F@ck', 1);
INSERT INTO tag (id, tag, user_id) VALUES (10, '05.42am...', 2);