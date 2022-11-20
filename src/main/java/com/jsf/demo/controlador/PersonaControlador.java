package com.jsf.demo.controlador;


import com.jsf.demo.modelo.Persona;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class PersonaControlador {
    @RequestMapping("/hello")
    private String hello() {
        return "hello";
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    @RequestMapping("/paginap/{id}")
    private ModelAndView getPersona(@PathVariable Integer id, Model model)   {
        String uri = "http://localhost:8070/personas/" + id;
        RestTemplate restTemplate = new RestTemplate();


        //restTemplate = new RestTemplateBuilder().basicAuthentication("prueba","Prueba").build();
        Persona persona = restTemplate.getForObject(uri, Persona.class);

        /*restTemplate.exchange
                (uri, HttpMethod.POST, new HttpEntity<Persona>(createHeaders("prueba", "prueba")), Persona.class);*/


        ModelAndView modelAndView = new ModelAndView(new InternalResourceView("/buscar.xhtml"));
        //ModelAndView modelAndView = new ModelAndView(new InternalResourceView("/WEB-INF/my/path/myPage.xhtml"), model);
        //return modelAndView
        modelAndView.addObject("persona", persona);


        return modelAndView;
    }
    @RequestMapping("/tpersonas")
    private ModelAndView getTodo()   {
        String uri = "http://localhost:8070/personas/todo";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Persona[]> response =
                restTemplate.getForEntity(
                        uri,
                        Persona[].class);
        List<Persona> persona = Arrays.asList(response.getBody());

        ModelAndView modelAndView = new ModelAndView(new InternalResourceView("/personas.xhtml"));
        modelAndView.addObject("persona", persona);


        return modelAndView;
    }
}
