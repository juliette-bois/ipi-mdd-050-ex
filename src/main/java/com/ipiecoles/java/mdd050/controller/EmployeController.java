package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {
    @Autowired
    private EmployeRepository employeRepository;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    //@GetMapping("/Count");

    public long countEmploye() {
        return employeRepository.count();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employe getEmployeById(@PathVariable(value = "id") Long id) {
        return employeRepository.findOne(id);
    }

    @RequestMapping(value = "", params = "matricule")
    public Employe getEmployeByMatricule(@RequestParam(value = "matricule") String matricule) {
        return employeRepository.findByMatricule(matricule);
    }
//page=0&size=10&sortProperty=matricule&sortDirection=ASC
    @RequestMapping("")
    public Page<Employe> getEmploye(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortProperty") String sortProperty,
            @RequestParam(value = "sortDirection") String sortDirection
    ) {
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return employeiRepository.findAll(pageRequest);
    }

}
