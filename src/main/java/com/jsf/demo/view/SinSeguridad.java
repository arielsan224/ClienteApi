package com.jsf.demo.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component
@Data
public class SinSeguridad implements Serializable {

  private String hola = "probando si llega...";

  @PostConstruct
  public void init() {
    System.out.print("holis...");
  }

}
