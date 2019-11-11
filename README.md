# Teaching-HEIGVD-AMT-2019-Project-One

Authors : Robel Teklehaimanot & Nair Alic

## Objectives

The main objective of this project is to apply the patterns and techniques presented during the lectures, and to create a simple multi-tiered application in Java EE.

#### What we have implemented 

We have decided to develop a web application where we can manage football teams. There are two type of users: a simple coach, which can only manages the teams he's attributed. A team is made of players. A coach will be able to add, delete his players which are in his teams. There are coaches which are administrators, they can add or delete a team. They can list all the teams or players.

We have three entities.. Teams, Coaches and Players.

#### How we have implemented

To implement this app, we have used the MVC pattern. Our application is splitted into tiers. We have the model tier where we have our classes. The presentation tier is where we have all our servlets. And the integration tier (DAO), where we implemented the methods that will communicate with our database.



## Links

- Follow [this link](./doc/README_deployment.md) in order to **deploy** the web application on your machine.
- Follow [this link](./doc/README_usability.md) to know how to use the web application
- Follow [this link](./doc/README_tests.md) if you want to see **the tests** we've made.
- Follow [this link](./doc/README_bugs_limitations.md) to see the actual knows bugs and limitations.



