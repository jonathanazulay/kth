<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Recipe_model extends CI_Model {
    /**
     * Returns all comments for a recipe
     * @param  int    $recipe ID of recipe to fetch comments for
     * @return array          Contains all comments
     */
    public function get_all_comments($recipe) {
        $query = "
            SELECT
                comment.id, comment.comment, user.id AS userId, user.username AS username
            FROM
                comment
            INNER JOIN
                user ON comment.userId = user.id WHERE recipeId = ?
            ORDER BY comment.id DESC
        ";
        $query = $this->db->query($query, array($recipe));
        $result = $query->result();
        foreach ($result as $comment) {
            $comment->comment = html_escape($comment->comment);
        }
        return $result;
    }

    /**
     * Inserts a comment for a specified recipe
     * @param  int    $recipe   ID of recipe to add comment for
     * @param  int    $fromUser ID of author
     * @param  string $content  [description]
     * @return [type]           [description]
     */
    public function insert_comment($recipeId, $authorId, $content) {
        $query = "INSERT INTO comment (recipeId, userId, comment) VALUES(?, ?, ?)";
        $this->db->query($query, array($recipeId, $authorId, $content));
    }

    /**
     * Edits a comment
     * @param  [type] $comment [description]
     * @param  [type] $user    [description]
     * @param  [type] $content [description]
     * @return [type]          [description]
     */
    public function edit_comment ($commentId, $userId, $content) {
        $query = "UPDATE comment SET comment = ? WHERE id = ? AND userId = ?";
        $this->db->query($query, array($content, $commentId, $userId));
    }
}
