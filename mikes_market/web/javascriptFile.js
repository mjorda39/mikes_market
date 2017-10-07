/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkUsername()
{
    var username = document.getElementById("username").value;
    //window.alert(username);
    
    $.ajax({
    type: "POST",
    url: "usernameCheck",
    data: { username: username},
    success: dataReturned
});
}

function dataReturned(data, status)
{
    //window.alert(data);
    var user_err = document.getElementById("username_err");
    user_err.innerHTML = data;
}


