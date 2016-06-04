var editComment = function (el) {
    var $commentEl = $(el).parent().parent();
    $('.comment-edit').css('display', '');
    $('.comment-content').css('display', '');
    $commentEl.find('.comment-edit').css('display', 'block');
    $commentEl.find('.comment-content').css('display', 'none');
}

$(function () {
    var loginBarViewModel = new LoginBarViewModel();
    ko.applyBindings(loginBarViewModel, $('.login-bar')[0]);
    ko.applyBindings(loginBarViewModel, $('.login.modal-content')[0]);

    if ($('.comment-section').length !== 0) {
        var commentViewModel = new CommentViewModel();
        commentViewModel.loginVM = loginBarViewModel;
        ko.applyBindings(commentViewModel, $('.comment-section')[0]);
    }
});
