var imgs = [
    'http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg',
    'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTAzSE6QSWkDObdt0XGb9oGlO2kqllDwFaAYP_A_wd0aDUOM-rZ',
    'http://www.spokeslabs.com/jstone/ps4_images/ps4-hrdware-large18.jpg',
    'http://s3.amazonaws.com/digitaltrends-uploads-prod/2015/02/apple-tv.jpg',
    'https://ss7.vzw.com/is/image/VerizonWireless/beats-audio-headphones-red-900-00078-01-iset?$acc-lg$&amp;fmt=jpeg',
    'http://images.devshed.com/dh/stories/....enrique/HP_Pavilion_g6-1a69us_pic1.jpg'
];
var index = 0;

function login() {
    var user = document.getElementById('user').value;
    var pass = document.getElementById('pass').value;
    var error = document.getElementById('errorMsg');
    var welcome = document.getElementById('welcome');
    
    if(user.length < 5 || pass.length < 8) {
        error.innerHTML = "Please enter a username longer than 5 characters or password longer than 8 characters.";
        welcome.innerHTML = "";
    } else {
        error.innerHTML = "";
        welcome.innerHTML = "Welcome, " + user + "!";
    }
}

function forward() {
    if(index === 5) {
        index = 0;
    } else {
        index++;
        document.getElementById('slideshow').src = imgs[index];
    }
}

function backward() {
    if(index === 0) {
        index = 5;
    } else {
        index--;
        document.getElementById('slideshow').src = imgs[index];
    }
}