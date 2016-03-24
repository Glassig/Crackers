var express = require('express');
var favicon = require('serve-favicon');
var app = express();

app.set('view engine', 'hbs');
app.use(express.static(__dirname+'/public'));
app.use(favicon(__dirname + '/public/favicon.ico'));

app.get('/', function (req, res) {
  res.render('index', { title: 'Saltiiiiiiiines', txt: 'Welcome to our game! You rule!'});
});

app.get('/speech', function (req, res) {
  res.render('index', { title: 'Speech-version', txt: 'This is the speech implementation! To speak is golden or something.'});
});

app.get('/typing', function (req, res) {
  res.render('index', { title: 'Typing-version', txt: 'This is the typing version. HACKER MONTAGE SPEEED HOLLYWOOD BS!!!'});
});

app.listen(4000, function () {
  console.log('Listening on port 4000!');
});