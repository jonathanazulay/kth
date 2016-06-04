/**
 * Handles adding and editing comment and showing the comments
 */
var CommentViewModel = function () {
    this.recipeId = new ko.observable(parseInt(window.location.href.slice(-1), 10));
    this.comments = new ko.observableArray();
    this.newComment = new ko.observable();
    this.fetchComments();
    this.bindEventHandlers();
};

CommentViewModel.prototype.fetchComments = function () {
    var that = this;
    $.getJSON('/ci/index.php/recipe/comments/' + this.recipeId())
    .then(function (response) {
        that._handleComments(response);
    });
};

CommentViewModel.prototype.bindEventHandlers = function () {
    var that = this;

    this.editComment = function () {
        ko.utils.arrayForEach(that.comments(), function (comment) {
            comment.editMode(false);
        });
        this.editMode(true);
    };

    this.saveComment = function () {
        $.post('/ci/index.php/recipe/editComment/', {
            commentId: this.commentId,
            comment: this.commentContent
        })
        .then(function () {
            that.fetchComments();
        });
    };

    this.addComment = function () {
        $.post('/ci/index.php/recipe/addComment/', {
            recipeId: that.recipeId(),
            comment: that.newComment()
        })
        .then(function () {
            that.fetchComments();
        });
        that.newComment('');
    };
};

CommentViewModel.prototype._handleComments = function (response) {
    var that = this;
    this.comments.removeAll();
    for (var i = 0; i < response.length; i += 1) {
        this.comments.push({
            commentId: response[i].id,
            commentAuthor: response[i].username,
            commentAuthorId: response[i].userId,
            commentContent: response[i].comment,
            editMode: ko.observable(false)
        });
    }
};
