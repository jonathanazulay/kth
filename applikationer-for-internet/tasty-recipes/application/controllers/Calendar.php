<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Calendar extends CI_Controller {

	/**
	 * Creates default viewdata and loads models
	 */
	public function __construct () {
		parent::__construct();
		$this->load->model('Recipe_model', 'recipe', TRUE);
		$this->load->model('User_model', 'user', TRUE);
		$this->viewData = array("user" => $this->user->getData());
		$this->viewData['currentPage'] = 'calendar';
	}

	/**
	 * Shows all the recipes
	 */
	public function index () {
        $this->parser->parse('fragments/beforeBody.html', $this->viewData);
        $this->parser->parse('calendar.html', $this->viewData);
		$this->parser->parse('fragments/afterBody.html', $this->viewData);
    }
}
