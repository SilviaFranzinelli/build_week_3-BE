package it.epicode.EpicEnergyService.controller;

import it.epicode.EpicEnergyService.service.CSVImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    @Autowired
    private CSVImportService importService;


    @PostMapping("/province")
    public ResponseEntity<String> importaProvince() {
        try {
            importService.importaProvinceDaCSV("path/al/file/elenco-province-italiane.csv");
            return ResponseEntity.ok("Province importate");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore importazione province");
        }
    }

    @PostMapping("/comuni")
    public ResponseEntity<String> importaComuni() {
        try {
            importService.importaComuniDaCSV("path/al/file/elenco-comuni-italiani.csv");
            return ResponseEntity.ok("Comuni importati");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore importazione comuni");
        }
    }
}

