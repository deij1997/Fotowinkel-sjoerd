/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on("click", "#login", function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (this.responseText == 0) {
                document.getElementById("loginErrorMsg").classList.remove("hide");
            }
            if (this.responseText == 1) {
                window.location.replace("index.jsp");
            }
        }

    };
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    xhttp.open("POST", "LoginServlet?" + "username=" + username + "&password=" + password, true);
    xhttp.send();
    return false;
});

$(document).on("click", "#register", function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var message = document.getElementById("loginErrorMsgR");
            if (this.responseText == 0) {
                message.classList.remove("hide");
                message.innerHTML = "Email is al in gebruik";
            }
            if (this.responseText == 3) {
                message.classList.remove("hide");
                message.innerHTML = "Niet alle velden zijn ingevuld";
            }
            if (this.responseText == 1) {
                window.location.replace("index.jsp");
            }
            if (this.responseText == 2) {
                message.classList.remove("hide");
                message.innerHTML = "Email was niet correct";
            }
            if (this.responseText == 4) {
                message.classList.remove("hide");
                message.innerHTML = "Een fout was opgetreden";
            }
            if (this.responseText == 5) {
                message.classList.remove("hide");
                message.innerHTML = "Uw wachtwoord is niet sterk genoeg<br \>Deze moet minimaal 8 karakters bevatten, waarvan 1 hoofdletter en een getal";
            }
        }

    };
    var username = document.getElementById("usernameR").value;
    var password = document.getElementById("passwordR").value;
    xhttp.open("POST", "RegisterServlet?" + "usernameR=" + username + "&passwordR=" + password, true);
    xhttp.send();
    return false;
});



