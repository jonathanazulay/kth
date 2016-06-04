<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Recipe extends CI_Controller {

	/**
	 * Creates default viewdata and loads models
	 */
	public function __construct () {
		parent::__construct();
		$this->load->model('Recipe_model', 'recipe', TRUE);
		$this->load->model('User_model', 'user', TRUE);
		$this->viewData = array("user" => $this->user->getData());
		$this->viewData['currentPage'] = 'recipe';
	}

	/**
	 * Shows all the recipes
	 */
	public function index () {
		$this->showAll();
	}

	/**
	 * Returns comments for a recipe in JSON
	 * @return [type] [description]
	 */
	public function comments ($id) {
		$this->echo_json($this->recipe->get_all_comments($id));
	}

	/**
	 * Loads the correct recipe view from id. If not found redirects to
	 * page showing all recipes.
	 * @param $id         id of recipe
	 */
	public function show ($id) {
		if ($id != 1 && $id != 2) {
			redirect('recipe', 'location');
		}

		$this->viewData["recipe"] = $id;
		$this->viewData["comments"] = $this->recipe->get_all_comments($id);

        $this->parser->parse('fragments/beforeBody.html', $this->viewData);
		$this->parser->parse("recipe.html", $this->viewData);
		$this->parser->parse('fragments/afterBody.html', $this->viewData);
    }

	private function showAll () {
		$this->parser->parse('fragments/beforeBody.html', $this->viewData);
        $this->parser->parse('recipes.html', $this->viewData);
		$this->parser->parse('fragments/afterBody.html', $this->viewData);
	}

    public function addComment () {
		$this->recipe->insert_comment(
			$this->input->post('recipeId'),
			$this->user->user_id,
			$this->input->post('comment')
		);
    }

    public function editComment () {
		$this->recipe->edit_comment(
			$this->input->post('commentId'),
			$this->user->user_id,
			$this->input->post('comment')
		);
    }

	private function echo_json ($obj) {
        $this->output
            ->set_content_type('application/json')
            ->set_output(json_encode($obj));
    }
}
