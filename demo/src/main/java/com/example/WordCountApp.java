package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCountApp extends Application {

    private TextArea inputTextArea;
    private ListView<String> wordCountListView;

    @Override
    public void start(Stage primaryStage) throws Exception {

        inputTextArea = new TextArea();
        Button countButton = new Button("Посчитать");
        countButton.setOnAction(e -> countWords());
        wordCountListView = new ListView<>();

        HBox buttonBox = new HBox(countButton);
        BorderPane root = new BorderPane();
        root.setTop(inputTextArea);
        root.setCenter(wordCountListView);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void countWords() {
        String[] words = inputTextArea.getText().split("\\s+");
        Map<String, Long> wordCounts = Arrays.stream(words)
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        ObservableList<String> items = FXCollections.observableArrayList();
        wordCounts.forEach((word, count) -> items.add(word + ": " + count));

        wordCountListView.setItems(items);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
