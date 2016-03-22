var express = require('express');
//var hbs = require('express-hbs');
var app = express();

app.set('view engine', 'hbs');
app.use(express.static(__dirname+'/public'));

app.get('/', function (req, res) {
  res.render('index', { title: 'Saltiiiiiiiines'});
});

app.get('/speech', function (req, res) {
  res.render('speech', { title: 'Speech-version'});
});

app.get('/type', function (req, res) {
  res.render('typing', { title: 'Typing-version'});
});

app.listen(4000, function () {
  console.log('Listening on port 4000!');
});