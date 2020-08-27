package br.com.kiev.bluefood.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kiev.bluefood.application.service.ImageService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	// Anotação para informar que o conteúdo destes bytes tem que ser colocado no
	// corpo do protocolo HTTP.
	@GetMapping(path = "/images/{type}/{imgName}")
	@ResponseBody
	public byte[] getBytes(@PathVariable("type") String type, @PathVariable("imgName") String imgName) {

		return imageService.getBytes(type, imgName);
	}
}
