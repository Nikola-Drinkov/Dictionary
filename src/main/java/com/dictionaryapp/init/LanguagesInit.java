package com.dictionaryapp.init;

import com.dictionaryapp.enums.LanguagesEnum;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LanguagesInit implements CommandLineRunner {

    private LanguageRepository languageRepository;

    private Map<LanguagesEnum, String> languagesDescriptions = Map.of(
            LanguagesEnum.GERMAN, "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.",
            LanguagesEnum.SPANISH, "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.",
            LanguagesEnum.FRENCH, "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.",
            LanguagesEnum.ITALIAN, "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history."
    );

    public LanguagesInit(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.languageRepository.count() == 0){
            for(Map.Entry<LanguagesEnum, String> entry : languagesDescriptions.entrySet()){
                Language language = new Language();
                language.setLanguage(entry.getKey());
                language.setDescription(entry.getValue());
                languageRepository.save(language);
            }
        }
    }
}
