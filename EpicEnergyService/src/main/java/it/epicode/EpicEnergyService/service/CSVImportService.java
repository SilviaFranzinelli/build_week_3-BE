package it.epicode.EpicEnergyService.service;

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
                String nome = dati[0].trim();
                String sigla = dati[1].trim();
                if (provinciaRepository.findByNomeAndSigla(nome, sigla).isEmpty()) {
                    provinciaRepository.save(new Provincia(nome, sigla));
                }
            }
        }
    }

    public void importaComuniDaCSV(String path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dati = line.split(";");
                String nomeComune = dati[0].trim();
                String nomeProvincia = dati[1].trim();
                String siglaProvincia = dati[2].trim();

                Provincia provincia = provinciaRepository.findByNomeAndSigla(nomeProvincia, siglaProvincia)
                        .orElseThrow(() -> new RuntimeException("Provincia non trovata: " + nomeProvincia));

                if (comuneRepository.findByNomeAndProvincia(nomeComune, provincia).isEmpty()) {
                    comuneRepository.save(new Comune(nomeComune, provincia));
                }
            }
        }
    }
}
