package gameofthrones.proyecto.controller;

import gameofthrones.proyecto.model.CharactersItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchHolder {
    private static SearchHolder instance;
    private ObservableList<CharactersItem> characterItems;

    private SearchHolder() {
        characterItems = FXCollections.observableArrayList();
    }

    public static SearchHolder getInstance() {
        if (instance == null) {
            instance = new SearchHolder();
        }
        return instance;
    }

    public ObservableList<CharactersItem> getCharacterItems() {
        return characterItems;
    }

    public void setCharacterItems(ObservableList<CharactersItem> items) {
        characterItems.addAll(items);
    }
}

