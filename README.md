# EvernoteLab
HomeWork SpringLab December

Spring MVC + Spring Security + AOP+ Swagger + Jacoco + Integration tests

http://localhost:8080/login
Login: test
Password: test

| URL Path                                 | Request methods | Result                                                           |
|------------------------------------------|-----------------|------------------------------------------------------------------|
| /login                                   | POST            |                                                                  |
| /logout                                  | POST            |                                                                  |
|                                          |                 |                                                                  |
| /registration                            | POST            | create new user                                                  |
| /users                                   | GET             | get all users                                                    |
| /users/{id}                              | PUT             | update current user                                              |
|                                          |                 |                                                                  |
| /notebooks                               | GET             | get all notebook from user in session                            |
| /notebooks                               | POST            | create notebook                                                  |
| /notebooks/{id}                          | DELETE          | delete notebook                                                  |
| /notebooks/{id}                          | GET             | get notebook by id                                               |
| /notebooks/{id}                          | PUT             | delete notebook                                                  |
|                                          |                 |                                                                  |
| /notebooks/{id}/notes                    | GET             | get all notes by notebook                                        |
| /notes                                   | GET             | get all notes from user in session                               |
| /notes/{id}                              | DELETE          | delete note                                                      |
| /notes                                   | POST            | create note in notebook                                          |
| /notes/tag/{tag}                         | GET             | get all notes by tag                                             |
| /notes/{id}                              | GET             | get note by id                                                   |
| /notes/{id}                              | PUT             | update note                                                      |
| /notes/{note_id}/{tag_id}                | DELETE          | delete note from tag                                             |
| /notes/{note_id}/{tag_id}                | PUT             | add tag to note                                                  |
|                                          |                 |                                                                  |
| /tags                                    | GET             | get all tags from user in session                                |
| /tags                                    | POST            | create tag                                                       |
| /tags/{id}                               | GET             | get by id                                                        | 
| /tags/{id}                               | DELETE          | delete tag from user                                             | 
| /tags/{id}                               | PUT             | update tag                                                       |
|                                          |                 |                                                                  |
