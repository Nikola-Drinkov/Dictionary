package com.dictionaryapp.model.entity;

import com.dictionaryapp.enums.LanguagesEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LanguagesEnum language;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(targetEntity = Word.class, mappedBy = "language")
    private Set<Word> words;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguagesEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguagesEnum language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
