# OOProject
Project created by Benjamin-Marco Barault and Dylan Fernandes for our Object Oriented Programming class.

### How to set up the server
First things first navigate to OOPAssignment/NodeServer/config/env and change both the development.js file and production.js file to your liking. By default development.js runs everything (the database and application server) on the same machine. You have to change production.js if you decided to turn it on.

Next you will want to do the following:

1. Pull the code from github
2. Install all the dependencies using these commands in the NodeServer/ directory
```
npm install
bower install
```
Afterwards you can run the server by naviagting to the NodeServer/ directory and running:

```
node server.js
```

_NOTE:_ Depending on your OS you may have to run nodejs instead of node. Also if you want the server to run permanently (or until you cancel it) then look into forever or pm2 on https://npmjs.com

### Error Codes
- 100 - As far as we know everything went smoothly
- 200 - You are trying to over-write something you don't have permission to over-write
- 300 - The resource you are looking for is missing or does not exist
- 400 - You do not have rights to access this resource
- 500 - Something went wrong with your request
- 600 - Token is either invalid or expired
- 700 - Invalid use of our API
- 800 - You gave us some invalid information
