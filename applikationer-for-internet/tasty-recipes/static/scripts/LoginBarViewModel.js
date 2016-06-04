/**
 * Handles things related to login bar at top of page
 * Such as logging in and logging out
 */
var LoginBarViewModel = function () {
    this.username = ko.observable();
    this.userId = ko.observable();
    this.isLoggedIn = ko.observable();

    this.loginUsername = ko.observable();
    this.loginPassword = ko.observable();
    this.loginError = ko.observable("");
    this.loginButtonText = ko.observable("Log in");

    this.loadStatus();
};

/**
 * Fetches user status from server and puts it into viewmodel observables
 */
LoginBarViewModel.prototype.loadStatus = function () {
    var that = this;
    $.getJSON('/ci/index.php/User/status')
    .then(function (response) {
        that._handleUserStatusReponse(response);
    });
};

/**
 * Shows bootstrap login modal
 */
LoginBarViewModel.prototype.showLoginPopup = function () {
    this.loginError("");
    $('.modal').modal('show');
};


/**
 * Logs a user in using viewmodel state
 */
LoginBarViewModel.prototype.login = function () {
    var that = this;

    that.loginError("");
    that.loginButtonText("Logging in...");

    $.post(
        $('form.login').attr('action'),
        {username: this.loginUsername(), password: this.loginPassword()}
    )
    .then(function (response) {
        that._handleUserStatusReponse(response);
        that.cancelLogin();
    })
    .fail(function (err) {
        that._handleLoginError(err.responseJSON);
    })
    .always(function () {
        that.loginButtonText("Log in");
    });
};

/**
 * Log user out
 */
LoginBarViewModel.prototype.logout = function () {
    var that = this;
    $.get(
        $('.login-bar a').attr('href')
    )
    .always(function (response) {
        that._handleUserStatusReponse(response);
    });
};

/**
 * Cancels a login process, closes bootstrap modal and resets input boxes
 */
LoginBarViewModel.prototype.cancelLogin = function () {
    this.loginUsername('');
    this.loginPassword('');
    $('.modal').modal('hide');
};

LoginBarViewModel.prototype._handleLoginError = function (jsonResponse) {
    if (jsonResponse.message === "user not found") {
        this.loginError("Incorrect username or password, please try again");
    } else {
        this.loginError("Something went wrong, please try again");
    }
};

LoginBarViewModel.prototype._handleUserStatusReponse = function (response) {
    this.username(response.username);
    this.userId(response.user_id);
    this.isLoggedIn(response.is_logged_in);
};
