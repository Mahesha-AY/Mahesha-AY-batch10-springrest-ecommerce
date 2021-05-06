package com.te.ecommercerest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.ecommercerest.bean.Item;
import com.te.ecommercerest.bean.ItemResponse;
import com.te.ecommercerest.service.EcommerceService;



@RestController
public class EcommerceRestController {

	@Autowired
	EcommerceService service;

	@GetMapping(path = "/getItem", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ItemResponse getItem(Integer id) {

		Item item = service.searchItem(id);
		ItemResponse response = new ItemResponse();

		if (item != null) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setItem(item);
		} else {
			response.setStatusCode(404);
			response.setMsg("item does not presesnt");
		}
		return response;

	}

	@GetMapping(path = "/getAllItems", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ItemResponse getAllItem() {
		ItemResponse response = new ItemResponse();
		List<Item> itemList = service.getAllItems();
		if (itemList != null) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setItems(itemList);
		} else {
			response.setStatusCode(404);
			response.setMsg("Items do not present");
		}
		return response;
	}

	@PostMapping(path = "/addItem", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ItemResponse addItem(@RequestBody Item item) {
		ItemResponse response = new ItemResponse();
		if (service.addItem(item)) {
			response.setStatusCode(200);
			response.setMsg("success");

		} else {
			response.setStatusCode(400);
			response.setMsg("Failure , Could not add the data");
		}
		return response;
	}

	@PutMapping(path = "/updateItem", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ItemResponse updateItem(@RequestBody Item item) {
		ItemResponse response = new ItemResponse();
		if (service.updateItem(item)) {
			response.setStatusCode(200);
			response.setMsg("success");
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure , Could not add the data");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteItem/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ItemResponse deleteItem(@PathVariable(name = "id") Integer id) {
		ItemResponse response = new ItemResponse();
		if (service.removeItem(id)) {
			response.setStatusCode(200);
			response.setMsg("success");
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure , Could not add the data");
		}
		return response;
	}
}
