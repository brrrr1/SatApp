package com.salesianos.satapp.controller;

import com.salesianos.satapp.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nota/")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

}
