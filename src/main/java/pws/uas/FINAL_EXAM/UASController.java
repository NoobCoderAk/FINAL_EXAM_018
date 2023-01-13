/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.uas.FINAL_EXAM;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ABID
 */

@RestController
@ResponseBody
public class UASController {
    Surat data = new Surat();
    SuratJpaController control = new SuratJpaController();
    
    @GetMapping(value = "/GET", produces = APPLICATION_JSON_VALUE)
    public  List<Surat>getData(){
        List<Surat> buffer = new ArrayList<>();
        try{
            buffer = control.findSuratEntities();
        }
        catch(Exception error){
            
        }return buffer;
    }
    
    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData (HttpEntity<String>datasend) throws  JsonProcessingException
    {
        String feedback = "DO Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(),Surat.class);
        try{
            control.create(data);
            feedback = data.getJudul()+", Saved !";
        }catch(Exception Error){
            feedback = Error.getMessage();
            
        }return feedback;
    }
     @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String updateData (HttpEntity<String> datasend) throws JsonProcessingException
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    control.edit(data);
                    feedback = data.getJudul() + ", Edited !";
                }
                catch (Exception Error)
                        {
                            feedback = Error.getMessage();
                        }
                return feedback;
    }
    @DeleteMapping(value = "/DELETE", consumes = APPLICATION_JSON_VALUE)
    public String deleteData (HttpEntity<String> datasend) throws JsonProcessingException
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    control.destroy(data.getId());
                    feedback = data.getJudul() + ", Deleted !";
                }
                catch (Exception Error)
                        {
                            feedback = Error.getMessage();
                        }
                return feedback;
    }    
}
