package MichelaVivacqua.gestionedispositiviwithloginauthorization.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.entities.Dipendente;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.entities.Dispositivo;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.exceptions.NotFoundException;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.payloads.NewDispositivoDTO;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.repositories.DipendentiDAO;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.repositories.DispositiviDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositiviService {
    @Autowired
    private DispositiviDAO dispositiviDAO;
    @Autowired
    private DipendentiDAO dipendentiDAO;

    public DispositiviService(DispositiviDAO dispositiviDAO) {
        this.dispositiviDAO = dispositiviDAO;
    }

    public List<Dispositivo> getDispositiviList() {
        return dispositiviDAO.findAll();
    }



    public Dispositivo saveDispositivo(NewDispositivoDTO newDispositivoDTO) {

        Dispositivo dispositivo = new Dispositivo(newDispositivoDTO.tipologia(),newDispositivoDTO.stato());
//        System.out.println(dispositivo);
        return dispositiviDAO.save(dispositivo);
    }



    public Dispositivo findById(int id) {
        return dispositiviDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Dispositivo findByIdAndUpdate(int id, Dispositivo updatedDispositivo) {
        Dispositivo found = findById(id);
        found.setTipologia(updatedDispositivo.getTipologia());
        found.setStato(updatedDispositivo.getStato());
        return dispositiviDAO.save(found);
    }

    public void findByIdAndDelete(int dispositivoId) {
        dispositiviDAO.deleteById(dispositivoId);
    }

//    @Transactional
//    public Dispositivo assegnaDispositivo(int dispositivoId, int dipendenteId) {
//        Dispositivo dispositivo = dispositiviDAO.findById(dispositivoId)
//                .orElseThrow(() -> new EntityNotFoundException("Dispositivo non trovato con ID: " + dispositivoId));
//
//        Dipendente dipendente = dipendentiDAO.findById(dipendenteId)
//                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con ID: " + dipendenteId));
//
//        dispositivo.setDipendente(dipendente);
//
//        return dispositiviDAO.save(dispositivo);
//    }

    @Transactional
    public void assegnaDispositivi(int dipendenteId, List<Integer> dispositiviId) {
       Dipendente dipendente = dipendentiDAO.findById(dipendenteId)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con ID: " + dipendenteId));

       List<Dispositivo> dispositivi = dispositiviDAO.findAllById(dispositiviId);

        for (Dispositivo dispositivo : dispositivi) {
            dispositivo.setDipendente(dipendente);
        }

        dispositiviDAO.saveAll(dispositivi);
    }

    public Page<Dispositivo> getDispositivi(int page, int size, String sortBy){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dispositiviDAO.findAll(pageable);
    }
}
