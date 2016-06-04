<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User_model extends CI_Model {
    public $user_id;
    public $username;
    public $is_logged_in;

    public function __construct () {
        parent::__construct();
        $this->updateModelState();
    }

    public function getData () {
        return array (
            "user_id" => $this->user_id,
            "username" => $this->username,
            "is_logged_in" => $this->is_logged_in
        );
    }

    public function login ($username, $password) {
        $user = $this->fetch_user_with_password($username, $password);

        if ($user !== NULL) {
            $this->session->set_userdata(array(
                'username' => $user->username,
                'id' => $user->id
            ));
            $this->updateModelState();
        } else {
            throw new Exception('user not found');
        }
    }

    public function logout () {
        $this->session->unset_userdata('id');
        $this->session->unset_userdata('username');
        $this->session->sess_destroy();
        $this->updateModelState();
    }

    /**
     * Creates a new user and logs the user in
     * @param  string $username
     * @param  string $nickname
     * @param  string $password password for the user, unhashed
     */
    public function create ($username, $nickname, $password) {
        $query = "INSERT INTO user (username, nickname, password) VALUES(?, ?, ?)";
        $query = $this->db->query($query, array($username, $nickname, MD5($password)));
        $this->login($username, $password);
    }

    private function fetch_user_with_password ($username, $password) {
        $query = "SELECT * FROM user WHERE username = ? AND password = ?";
        $query = $this->db->query($query, array($username, MD5($password)));
        $result = $query->result();

        if (isset($result[0])) {
            return $result[0];
        } else {
            return NULL;
        }
    }

    private function updateModelState () {
        if ($this->session->userdata('id') != FALSE) {
            $this->user_id = $this->session->userdata('id');
            $this->username = $this->session->userdata('username');
            $this->is_logged_in = TRUE;
        } else {
            $this->user_id = NULL;
            $this->username = NULL;
            $this->is_logged_in = FALSE;
        }
    }
}
