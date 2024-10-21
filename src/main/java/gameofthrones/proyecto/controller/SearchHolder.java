package gameofthrones.proyecto.controller;

import gameofthrones.proyecto.model.CharactersItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SearchHolder {
    private static SearchHolder instance;
    private ArrayList<CharactersItem> characterItems;

    private SearchHolder() {
        characterItems = new ArrayList<>();
    }

    public static SearchHolder getInstance() {
        if (instance == null) {
            instance = new SearchHolder();
        }
        return instance;
    }

    public ArrayList<CharactersItem> getCharacterItems() {
        return characterItems;
    }

    public void setCharacterItems(ObservableList<CharactersItem> items) {
        ArrayList<CharactersItem> characters = new ArrayList<>(items);
        characterItems = characters;
    }
}

