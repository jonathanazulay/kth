<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User extends CI_Controller {
    /**
     * Initializes models
     */
    public function __construct() {
        parent::__construct();
        $this->load->model('User_model', 'user', TRUE);
    }

    /**
     * Logins a user using username and password and
     * redirects to specified URL
     */
    public function login () {
        try {
            $this->user->login(
                $this->input->post('username'),
                $this->input->post('password')
            );
            $this->status();
        } catch (Exception $e) {
            $this->output->set_status_header(400);
            $this->echo_json(
                array("success" => false, "message" => $e->getMessage())
            );
        }
    }

    /**
     * Logouts the logged in user
     */
    public function logout () {
        $this->user->logout();
        $this->status();
    }

    /**
     * Outputs the user status in JSON format
     */
    public function status () {
        $userData = $this->user->getData();
        $this->echo_json($userData);
    }

    private function echo_json ($obj) {
        $this->output
            ->set_content_type('application/json')
            ->set_output(json_encode($obj));
    }
}
