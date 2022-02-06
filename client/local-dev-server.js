const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const path = require("path");


const app = express();
const port = process.env.PORT || 4000;

app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname, '/index.html'));
  });

app.use('/src', express.static(path.join(__dirname, 'src')));
app.use('/public', express.static(path.join(__dirname, 'public')))

app.use("/api", createProxyMiddleware({target: "http://127.0.0.1:8080", changeOrigin: true}));

app.listen(port);
console.log("Listening On port: " + port);