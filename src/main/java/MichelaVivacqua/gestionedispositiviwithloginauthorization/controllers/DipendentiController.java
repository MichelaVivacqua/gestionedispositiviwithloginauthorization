package MichelaVivacqua.gestionedispositiviwithloginauthorization.controllers;

import MichelaVivacqua.gestionedispositiviwithloginauthorization.entities.Dipendente;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.exceptions.BadRequestException;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.payloads.NewDipendenteDTO;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.payloads.NewDipendenteRespDTO;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;

//    1. POST http://localhost:3001/dipendenti (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteRespDTO saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation){

        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        System.out.println(body);
       return new NewDipendenteRespDTO(this.dipendentiService.saveDipendente(body).getId());}



    // 2. GET http://localhost:3001/dipendenti/{{dipendenteId}}
    @GetMapping("/{dipendenteId}")
    private Dipendente findDipendenteById(@PathVariable int dipendenteId){
        return this.dipendentiService.findById(dipendenteId);
    }

//    3. GET http://localhost:3001/dipendenti
        @GetMapping
        private List<Dipendente> getAllDipendenti(){
            return this.dipendentiService.getDipendentiList();
        }

    //    3.1 Paginazione e ordinamento http://localhost:3001/dipendenti/page
    @GetMapping("/page")
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "2") int size,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendentiService.getDipendenti(page, size, sortBy);
    }


    // 4. PUT http://localhost:3001/dipendenti/{{dipendenteId}} (+ body)
    @PutMapping("/{dipendenteId}")
    private Dipendente findByIdAndUpdate(@PathVariable int dipendenteId, @RequestBody Dipendente body){
        return this.dipendentiService.findByIdAndUpdate(dipendenteId, body);
    }



    // 5. DELETE http://localhost:3001/dipendenti/{dipendenteId}
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendenteById(@PathVariable int dipendenteId) {
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }


//    UPLOAD DI FOTO PER DIPENDENTE
//    http://localhost:3001/dipendenti/upload/{dipendenteId}
    @PostMapping("/upload/{dipendenteId}")
    public Dipendente uploadPropic (@RequestParam("propic") MultipartFile image, @PathVariable int dipendenteId) throws IOException {
        return this.dipendentiService.uploadDipendenteImage(image,dipendenteId);
    }

}
