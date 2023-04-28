package unicordoba.software.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; 
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(as = Server.class)
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip ;
    private String nombre;
    private String memoria;
    private String disco; 
    private String procesador;
    private String ubicacion;
    private String so;

    @OneToMany(mappedBy = "server", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("server")
    private List<Aplication> aplications= new ArrayList<Aplication>();
}
