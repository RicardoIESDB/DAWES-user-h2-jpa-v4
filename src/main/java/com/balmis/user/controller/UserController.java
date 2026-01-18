package com.balmis.user.controller;

import com.balmis.user.model.User;
import com.balmis.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // http://localhost:8080/userh2jpav4/h2-console          = Consola de H2

    // http://localhost:8080/userh2jpav4                     = /static/index.html
    // http://localhost:8080/userh2jpav4/static-noexiste     = gestionado por GlobalExceptionHandler
    // http://localhost:8080/userh2jpav4/users/api-noexiste  = gestionado por GlobalExceptionHandler

    // http://localhost:8080/userh2jpav1/users
    @GetMapping("/users")
    public List<User> showUsers() {
        return userService.findAll();
    }


    // http://localhost:8080/userh2jpav4/users/2
    @GetMapping("/users/{id}")
    public User detailsUser(@PathVariable int id) {
        return userService.findById(id);
    }

    // http://localhost:8080/userh2jpav4/users/mayor/7
    @GetMapping("/users/mayor/{id}")
    public List<User> showUsersMayores(@PathVariable int id) {
        return userService.findByIdGrThan(id);
    }

    // http://localhost:8080/userh2jpav4/users/count
    @GetMapping("/users/count")
    public Map<String, Object> countUsers() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("users", userService.count());

        return obj;  // Se mapea automáticamente a JSON usando Jackson        
    }

    @GetMapping("/users/age/hasta/{edad}")
    public List<User> mostrarUsuariosConEdadHasta(@PathVariable int edad) {
        return userService.usuariosConEdadHasta(edad);
    }

    @GetMapping("/users/name/contiene/{cadena}")
    public List<User> mostrarUsuariosPorNombre(@PathVariable String cadena) {
        return userService.findByNameContains(cadena);
    }

    @GetMapping("/users/email/dominio")
    public List<User> mostrarUsuariosDeEmailConDominio(@RequestParam(name = "valor") String dominio) {
        return userService.buscarUsuarioPorDominio(dominio);
    }
}
