# EvernoteLab
HomeWork SpringLab December


| URL Path                                 | Request methods | Result                                                           |
|------------------------------------------|-----------------|------------------------------------------------------------------|
| /user                                    | PUT, GET        | save user, get all users                                         |
|                                          |                 |                                                                  |
| /notebooks                               | GET             | get all notebook by user in session                              |
| /notebooks                               | POST            | create notebook                                                  |
| /notebooks                               | PUT             | add note to notebook                                             |
| /notebooks/{id}                          | DELETE          | delete notebook                                                  |
| /notebooks/{title}                       | GET             | get all notebook by title                                        |
|                                          |                 |                                                                  |
| /notes                                   | GET             | get all notes from user                                          |
| /notes/note/{id}/tag                     | PUT             | add tag to note                                                  |
| /notes/notebook                          | GET             | get all notes from user notebook                                 |
| /notes/{content}                         | GET             | get by content                                                   |
| /notes/{date}                            | GET             | get by date                                                      |
| /notes/{status}                          | GET             | get by status                                                    |
| /notes/{title}                           | GET             | get by title                                                     |
| /notes/{id}                              | DELETE          | delete note                                                      |
| /notes/{note_id}/{tag_id}                | DELETE          | delete tag from note                                             |
| /{tag}/notes                             | GET             | get notes by tag                                                 |
|                                          |                 |                                                                  |
| /tags                                    | GET             | get all tags from user in session                                |
| /tags                                    | POST            | create tag                                                       |
| /tags/{id}                               | DELETE          | delete tag from user                                             | 
| /tags/{title}                            | GET             | get all by title                                                 |
|                                          |                 |                                                                  |
