function showPassword() {
    var x = document.getElementById("inputPassword");
    x.type = "text";
    // if (x.type === "password") {
    //     x.type = "text";
    // } else {
    //     x.type = "password";
    // }
}

function hidePassword() {
    var x = document.getElementById("inputPassword");
    x.type = "password";
    // if (x.type === "password") {
    //     x.type = "text";
    // } else {
    //     x.type = "password";
    // }
}

function showhidePasswordLogin() {
    debugger;
    var x = document.getElementById("inputPassword");
    var span = document.getElementById("loginspan");
    if (x.type === "password") {
        x.type = "text";
        span.className = "glyphicon glyphicon-eye-close";
    } else {
        x.type = "password";
        span.className = "glyphicon glyphicon-eye-open";
    }
}

function showhidePassword() {
    var x = document.getElementById("pass");
    var span = document.getElementById("passspan");
    if (x.type === "password") {
        x.type = "text";
        span.className = "glyphicon glyphicon-eye-close";
    } else {
        x.type = "password";
        span.className = "glyphicon glyphicon-eye-open";
    }
}


function validatePassword() {
    debugger;
    var password = document.getElementById("pass");
    var confirm_password = document.getElementById("confirm_password");
    var submit_btn = document.getElementById("submituser");
    if (password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
        submit_btn.disabled = true;
    } else {
        confirm_password.setCustomValidity('');
        submit_btn.disabled = false;
    }
    confirm_password.reportValidity();
}

function checkPassword() {
    debugger;
    var password = document.getElementById("pass");
    var confirm_password = document.getElementById("confirm_password");
    var chgdiv = document.getElementById("chgdiv");
    var chgspan = document.getElementById("chgspan");
    var submit_btn = document.getElementById("submitpassword");
    if (password.value == null || confirm_password.value == null || password.value != confirm_password.value) {
        chgdiv.className = "form-group has-error has-feedback";
        chgspan.className = "glyphicon glyphicon-remove form-control-feedback";
        submit_btn.disabled = true;
    } else {
        chgdiv.className = "form-group has-success has-feedback";
        chgspan.className = "glyphicon glyphicon-ok form-control-feedback";
        submit_btn.disabled = false;
    }
    // confirm_password.reportValidity();
}


