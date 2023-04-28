package unicordoba.software.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import unicordoba.software.models.Aplication;
import unicordoba.software.models.Server;
import unicordoba.software.repository.IAplicationRepository;
import unicordoba.software.repository.IServerRepository;

@RestController
@RequestMapping(path = "/api")
public class AplicationController {
    
    @Autowired
    private IAplicationRepository IAR;

    @Autowired
    private IServerRepository ISR;

    @GetMapping
    @RequestMapping(value = "/aplication", method = RequestMethod.GET)
    public ResponseEntity<List<Aplication>>GetAplications(){
        List<Aplication> aplications = (List<Aplication>) this.IAR.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(aplications);
    }

    @GetMapping
    @RequestMapping(value = "/aplication/{id}", method = RequestMethod.GET)
    public ResponseEntity<Aplication> GetAplicationById(@PathVariable Long id ){
        Aplication aplications = this.IAR.findById(id).orElse(null);
        if(aplications != null){
            return ResponseEntity.status(HttpStatus.OK).body(aplications);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/aplication/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Aplication> deleteAplication(@PathVariable Long id){
        Aplication aplication = this.IAR.findById(id).orElse(null);
        if(aplication == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.IAR.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(aplication);
    }

    @PostMapping
    @RequestMapping(value ="/aplication", method = RequestMethod.POST)
    public ResponseEntity<Aplication> PostAplication(@RequestBody Aplication aplicationNew) {
        Server server= this.ISR.findById(aplicationNew.getServer().getId()).get();
        if(server!=null){
            aplicationNew.setServer(server);
            Aplication aplication = this.IAR.save(aplicationNew);
            return ResponseEntity.status(HttpStatus.CREATED).body(aplication);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @RequestMapping(value = "/aplication/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Aplication> updateAplication(@PathVariable long id, @RequestBody Aplication aplicationUpdate) {
        Aplication aplication = this.IAR.findById(id).orElse(null);
        if (aplication == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (aplicationUpdate.getNombre() != null) {
            aplication.setNombre(aplicationUpdate.getNombre());
            // aplication.setName(aplicationUpdate.getName());
        }
        if (aplicationUpdate.getVersion() != null) {
            aplication.setVersion(aplicationUpdate.getVersion());
        }
        if (aplicationUpdate.getServer() != null) {
            aplication.setServer(aplicationUpdate.getServer());
        }
        this.IAR.save(aplication);
        return ResponseEntity.status(HttpStatus.OK).body(aplication);
    }
}
