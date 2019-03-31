package com.zazu.examples;

public class APIExamples {
	
	/*
	CardLevel cardLevel= new CardLevel();
	cardLevel.title = "7TestUpdate1";
	*/
	
	/*
	 * PATCH
	PatchClient patchClient = new PatchClient();
	patchClient.setId(7);
	patchClient.setObject(cardLevel);
	patchClient.execute();
	*/
	
	
	
	/*
	 * POST
	PostClient postClient = new PostClient();
	postClient.setObject(cardLevel);
	postClient.execute();
	
	List<CardLevel> postCardLevel = (List) postClient.getObject();
	for (CardLevel level : postCardLevel) {
		System.out.println("Card_level_id:" + level.card_level_id);
	}
	*/
	
	/*
	 * GET
	GetClient getClient = new GetClient();
	getClient.setModel(cardLevel);
	getClient.setFilter("(card_level_id=7)"); // If needed
	getClient.execute();
	
	List<CardLevel> resource = (List) getClient.getObject();
	for (CardLevel level : resource) {
		System.out.println(level.title);
	}
	*/		
	
}
