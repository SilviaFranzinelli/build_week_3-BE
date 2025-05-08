package it.epicode.EpicEnergyService.service;

import com.cloudinary.api.exceptions.NotFound;
import it.epicode.EpicEnergyService.exceptions.ResourceNotFoundException;
import it.epicode.EpicEnergyService.model.Comune;
import it.epicode.EpicEnergyService.model.Provincia;
import it.epicode.EpicEnergyService.repository.ComuneRepository;
import it.epicode.EpicEnergyService.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CSVImportService {

    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public void importaProvinceDaCSV(String path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dati = line.split(";");
                String nome = dati[1].trim();
                String sigla = dati[0].trim();
                if (provinciaRepository.findByNomeAndSigla(nome, sigla).isEmpty()) {
                    Provincia provincia = new Provincia();
                    provincia.setNome(nome);
                    provincia.setSigla(sigla);
                    provinciaRepository.save(provincia);
                }
            }
        }
    }

    public void importaComuniDaCSV(String path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dati = line.split(";");
                String nomeComune = dati[2].trim();
                String nomeProvincia = dati[3].trim();
//                String siglaProvincia = dati[2].trim();

                //Il problema è che non riesce a trovare la provincia sto maledetto
                Provincia provincia = provinciaRepository.findByNome(nomeProvincia)
                        .stream()
                        .findFirst()
                        .orElse(null);
                //ci andrebbe un errore se non trova la provincia, ma dato che non la trova di sicuro ho lasciato null

                if (comuneRepository.findByNomeAndProvincia(nomeComune, provincia).isEmpty()) {
                    Comune comune = new Comune();
                    comune.setNome(nomeComune);
                    comune.setProvincia(provincia);
                    comuneRepository.save(comune);
                }
            }
        }
    }

}
