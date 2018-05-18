package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositories.DomainRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Autowired
    DomainRepository domainRepository;

}
