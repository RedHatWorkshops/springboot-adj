package io.openshift.booster.model;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.openshift.booster.model.Adjective;

@RestController
public class AdjectiveServiceController {

    private List<Adjective> adjectives = new ArrayList<>();
    

    @RequestMapping("/api/adjective")
    public Adjective getAdjective() {
        
        return adjectives.get(new Random().nextInt(adjectives.size()));
    }


    @PostConstruct
    public void init() {


    	try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("adjectives.txt");
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                reader.lines()
                        .forEach(adj -> adjectives.add(new Adjective(adj.trim())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}