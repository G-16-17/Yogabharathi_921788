package com.cognizant.truyum;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

@SpringBootApplication
public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	private static MenuItemService menuItemService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
		menuItemService = context.getBean(MenuItemService.class);

		LOGGER.info("Inside Main.....");

		// testGetMenuItemListAdmin();

		// testGetMenuItemListCustomer();

		testModifyMenuItem();
	}

	private static void testGetMenuItemListAdmin() {
		LOGGER.info("Start");
		List<MenuItem> menuItemsList = menuItemService.getMenuItemListAdmin();
		LOGGER.debug("Items= {}", menuItemsList);
		LOGGER.info("End");
	}

	private static void testGetMenuItemListCustomer() {
		LOGGER.info("Start");
		List<MenuItem> menuItemsList = menuItemService.getMenuItemListCustomer();
		LOGGER.debug("Items= {}", menuItemsList);
		LOGGER.info("End");

	}

	private static void testModifyMenuItem() {
		LOGGER.info("Start");
		MenuItem menuItem = menuItemService.getMenuItem(1003);
		menuItem.setName("Cheese pizza");
		menuItemService.modifyMenuItem(menuItem);
		LOGGER.debug("Items= {}", menuItem);
		LOGGER.info("End");

	}
}