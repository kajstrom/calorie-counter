# calorie-counter

generated using Luminus version "2.9.12.78"

FIXME

## Todo

### Phase 1
- ~~Registration page and link from front page~~
- ~~Registration form~~
- ~~Change users table to have an auto generated id~~
- ~~Registration validation and error message display~~
- ~~Setup tempura for translations (determining correct language done later)~~
- ~~Registration back-end~~
- ~~Displaying back-end validation error messages.~~
- ~~"Redirect" to front page after registration~~
- ~~Display message about successful registration after registration~~
- ~~Redirect to front-page does not update route!~~
- ~~Enforce email uniqueness in registration~~
- ~~Login form~~
- Login validation
- Login back-end
- Hide login form for logged in users
- Fix notifications being displayed only in the front page

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Development environment

### Initial setup

Create volume for postgres:
    
    docker volume create --name calorie-postgres-data -d local 

Then start the container with

    docker-compose up

### After the containers have been created

Starting detached

    docker-compose up -d
    
Stopping without removing

    docker-compose stop

### Database migrations

Running migrations

    lein run migrate

Rollback

    lein run rollback

Create a new migration from REPL

    (user/create-migration "description")

### More information

Links used to create the dev env

- https://medium.com/phytochemia-tech-blog/how-docker-can-improve-your-development-environments-731cdfda0b9a
- https://glennsarti.github.io/blog/puppet-in-docker/

## Running

To start a web server for the application, run (in separate consoles):

    lein run 
    lein figwheel

## License

Copyright © 2018 FIXME
