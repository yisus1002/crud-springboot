package unicordoba.software.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import unicordoba.software.models.Server;
import unicordoba.software.repository.IServerRepository;
import unicordoba.software.repository.ServerSpecs;

@RestController
@RequestMapping(path = "/api")
public class ServerController {

    @Autowired
    private IServerRepository ISR;
    

    @RequestMapping(value = "/servers", method = RequestMethod.GET)
    public ResponseEntity<List<Server>> GetServerGreaterThanOneAplication(
        @RequestParam(value = "minApplications") Long minApplications){

        Specification<Server> serverSpecs = ServerSpecs.getServerByMoreOneAplication(minApplications);
        
        List<Server> servers = (List<Server>) this.ISR.findAll(serverSpecs);
     
        return ResponseEntity.status(HttpStatus.OK).body(servers);
    }


    @GetMapping
    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public ResponseEntity<List<Server>> GetServers(){
        List<Server> servers = (List<Server>) this.ISR.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(servers);
    }

    @GetMapping
    @RequestMapping(value = "/server/{id}", method = RequestMethod.GET)
    public ResponseEntity<Server> GetServersById(@PathVariable Long id){
        Server server = this.ISR.findById(id).orElse(null);
        if(server != null){
            return ResponseEntity.status(HttpStatus.OK).body(server);
        } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping
    @RequestMapping(value = "/server/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Server> DeletedServersById(@PathVariable Long id){
        Server server = this.ISR.findById(id).orElse(null);
        if(server != null){
            this.ISR.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(server);
        } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @RequestMapping(value = "/server", method = RequestMethod.POST)
    public ResponseEntity<Server> PostServer(@RequestBody Server serverNew) {

        Server server = this.ISR.save(serverNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(server);
    }

    @PutMapping
    @RequestMapping(value ="/server/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Server> PutServer(@PathVariable long id, @RequestBody Server server){
        Server serve=this.ISR.findById(id).orElse(null);
        if(server!=null){
            if(server.getDisco()!=null){
                serve.setDisco(server.getDisco());
            }
            if(server.getMemoria()!=null){
                serve.setMemoria(server.getMemoria());
            }
            if(server.getIp()!=null){
                serve.setIp(server.getIp());
            }
            if(server.getProcesador()!=null){
                serve.setProcesador(server.getProcesador());
            }
            if(server.getUbicacion()!=null){
                serve.setUbicacion(server.getUbicacion());
            }
            if(server.getSo()!=null){
                serve.setSo(server.getSo());
            }
            if(server.getNombre()!=null){
                serve.setNombre(server.getNombre());
            }
            this.ISR.save(serve);
            return ResponseEntity.status(HttpStatus.OK).body(serve);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
