package com.shiant.study.web.util;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public interface IWebUtils {

	MappingJackson2JsonView JSON = new MappingJackson2JsonView();
	String HOME = "home";
	boolean SERVER_STATUS_SUCCESS = true;
	boolean SERVER_STATUS_FAILED = false;
}
