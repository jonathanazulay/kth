<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Register extends CI_Controller {

	/**
	 * Creates default viewdata and loads models
	 */
	public function __construct () {
		parent::__construct();
		$this->load->model('User_model', 'user', TRUE);
		$this->viewData = array("user" => $this->user->getData());
		$this->viewData['currentPage'] = 'register';
	}

	/**
	 * Shows all the recipes
	 */
	public function index () {
        $this->parser->parse('fragments/beforeBody.html', $this->viewData);
		$this->parser->parse("register.html", $this->viewData);
		$this->parser->parse('fragments/afterBody.html', $this->viewData);
	}

    public function register () {
		if ($this->input->post('password') !== $this->input->post('repeatPassword')) {
			$this->viewData['error'] = "Passwords did not match, try again";
		} else if (empty($this->input->post('username')) || empty($this->input->post('nickname'))) {
			$this->viewData['error'] = "You need to enter a username and a nickname for your account, try again";
		} else {
			$this->user->create(
				$this->input->post('username'),
				$this->input->post('nickname'),
				$this->input->post('password')
	        );
			redirect();
		}

		$this->index();
    }
}
