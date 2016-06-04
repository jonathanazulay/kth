<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Welcome extends CI_Controller {
	/**
	* Creates default viewData and loads models
	*/
	public function __construct () {
		parent::__construct();
		$this->load->model('User_model', 'user', TRUE);
		$this->viewData = array("user" => $this->user->getData());
		$this->viewData['currentPage'] = 'start';
	}

	/**
	* Renders welcome page (popular recipes and welcoming text)
	*/
	public function index() {
		$this->parser->parse('fragments/beforeBody.html', $this->viewData);
		$this->parser->parse('home.html', $this->viewData);
		$this->parser->parse('fragments/afterBody.html', $this->viewData);
	}
}
