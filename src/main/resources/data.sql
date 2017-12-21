INSERT INTO note (id, content, date, isactive, title, notebook_id) VALUES
  (1, 'content for 1 user', DATE '2012-06-18', TRUE, 'title1', 1),
  (2, 'content for 1 user', DATE '2012-06-18', FALSE, 'title2', 1),
  (3, 'content for 1 user', DATE '2012-06-18', TRUE, 'title3', 1),
  (4, 'content for 1 user', DATE '2012-06-18', FALSE, 'title1', 2),
  (5, 'content for 1 user', DATE '2012-06-18', TRUE, 'title2', 2),
  (6, 'content for 1 user', DATE '2012-06-18', FALSE, 'title3', 2),
  (7, 'content for 2 user', DATE '2012-06-18', TRUE, 'title1', 3),
  (8, 'content for 2 user', DATE '2012-06-18', FALSE, 'title2', 4),
  (9, 'content for 3 user', DATE '2012-06-18', TRUE, 'title3', 5),
  (10, 'content1 for 3 user', DATE '2012-06-18', FALSE, 'title1', 6);

INSERT INTO notebook (id, title, user_id) VALUES
  (1, 'title', 1),
  (2, 'title2', 1),
  (3, 'title', 2),
  (4, 'title2', 2),
  (5, 'title1', 3),
  (6, 'title2', 3),
  (7, 'title1', 4),
  (8, 'title2', 4),
  (9, 'title1', 5),
  (10, 'title2', 5),
  (11, 'title3', 5),
  (12, 'title4', 5),
  (13, 'title1', 6),
  (14, 'title1', 7);

INSERT INTO tag (id, tag, user_id) VALUES
  (1, 'priority', 1),
  (2, 'home', 1),
  (3, 'vacation', 1),
  (4, 'work', 2),
  (5, 'holiday', 2),
  (6, 'dream', 2),
  (7, 'todo', 1),
  (8, 'what', 1),
  (9, 'F@ck', 1),
  (10, '05.42am...', 2);


INSERT INTO user (id, email, password, username) VALUES
  (1, '1@epam.com', 'pass', 'testname1'),
  (2, '2@epam.com', 'pass', 'testname2'),
  (3, '3@epam.com', 'pass', 'testname3'),
  (4, '4@epam.com', 'pass', 'testname1'),
  (5, '5@epam.com', 'pass', 'testname1'),
  (6, '6@epam.com', 'pass', 'testname2'),
  (7, '7@epam.com', 'pass', 'testname5'),
  (8, 'test', '$2a$10$BkvDRdJxR/CLu9ThLBvBX.RyrB3D2sDuWuB8aEDROMRSWQOdosIRK', 'test');