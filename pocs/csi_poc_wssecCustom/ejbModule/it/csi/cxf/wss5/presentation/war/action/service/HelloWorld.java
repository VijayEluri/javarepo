package it.csi.cxf.wss5.presentation.war.action.service;

import javax.ejb.Remote;
import javax.jws.WebService;

@Remote
@WebService
public interface HelloWorld {
    String sayHi(String text);
}
