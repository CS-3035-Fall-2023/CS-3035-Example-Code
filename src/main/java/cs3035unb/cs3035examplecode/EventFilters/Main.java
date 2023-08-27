package cs3035unb.cs3035examplecode.EventFilters;


/*
 * Copyright (c) 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Handling mouse events with event filters
 */

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class Main extends Application {
    private final BooleanProperty dragModeActiveProperty =
            new SimpleBooleanProperty(this, "dragModeActive", true);
    private Scene scene;
    @Override
    public void start(final Stage stage) {
        final BorderPane sceneRoot = new BorderPane();
        final Scene scene = new Scene(sceneRoot, 400, 300);

        final Node loginPanel =
                makeDraggable(createLoginPanel(scene));
        final Node confirmationPanel =
                makeDraggable(createConfirmationPanel());
        final Node progressPanel =
                makeDraggable(createProgressPanel());

        loginPanel.relocate(0, 0);
        confirmationPanel.relocate(0, 67);
        progressPanel.relocate(0, 106);

        final Pane panelsPane = new Pane();
        panelsPane.getChildren().addAll(loginPanel,
                confirmationPanel,
                progressPanel);

        BorderPane.setAlignment(panelsPane, Pos.TOP_LEFT);
        sceneRoot.setCenter(panelsPane);

        final CheckBox dragModeCheckbox = new CheckBox("Drag mode");
        BorderPane.setMargin(dragModeCheckbox, new Insets(6));
        sceneRoot.setBottom(dragModeCheckbox);

        dragModeActiveProperty.bind(dragModeCheckbox.selectedProperty());


        stage.setScene(scene);
        stage.setTitle("Draggable Panels Example");
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

    private Node makeDraggable(final Node node) {
        final DragContext dragContext = new DragContext();
        final Group wrapGroup = new Group(node);

        wrapGroup.addEventFilter(
                MouseEvent.ANY,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                        if (dragModeActiveProperty.get()) {
                            // disable mouse events for all children
                            node.toFront();
                            mouseEvent.consume();
                        }
                    }
                });

        wrapGroup.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                        if (dragModeActiveProperty.get()) {
                            // remember initial mouse cursor coordinates
                            // and node position
                            dragContext.mouseAnchorX = mouseEvent.getX();
                            dragContext.mouseAnchorY = mouseEvent.getY();
                            dragContext.initialTranslateX =
                                    node.getTranslateX();
                            dragContext.initialTranslateY =
                                    node.getTranslateY();
                        }
                    }
                });

        wrapGroup.addEventFilter(
                MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                        if (dragModeActiveProperty.get()) {
                            // shift node from its initial position by delta
                            // calculated from mouse cursor movement
                            node.setTranslateX(
                                    dragContext.initialTranslateX
                                            + mouseEvent.getX()
                                            - dragContext.mouseAnchorX);
                            node.setTranslateY(
                                    dragContext.initialTranslateY
                                            + mouseEvent.getY()
                                            - dragContext.mouseAnchorY);
                        }
                    }
                });

        return wrapGroup;
    }

    private static Node createLoginPanel(Scene scene) {
        final ToggleGroup toggleGroup = new ToggleGroup();

        final TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText("Your name");

        textField.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((TextField) event.getTarget()).setStyle("-fx-border-color: red;");
                scene.setCursor(Cursor.NE_RESIZE);
            }
        });

        textField.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((TextField) event.getTarget()).setStyle("-fx-border-color: black;");
            }
        });

        final PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(10);
        passwordField.setPromptText("Your password");

        final ChoiceBox<String> choiceBox = new ChoiceBox<String>(
                FXCollections.observableArrayList(
                        "English", "\u0420\u0443\u0441\u0441\u043a\u0438\u0439",
                        "Fran\u00E7ais"));
        choiceBox.setTooltip(new Tooltip("Your language"));
        choiceBox.getSelectionModel().select(0);

        final HBox panel =
                createHBox(6,
                        createVBox(2, createRadioButton("High", toggleGroup, true),
                                createRadioButton("Medium", toggleGroup,
                                        false),
                                createRadioButton("Low", toggleGroup, false)),
                        createVBox(2, textField, passwordField),
                        choiceBox);
        panel.setAlignment(Pos.BOTTOM_LEFT);
        configureBorder(panel);

        return panel;
    }

    private static Node createConfirmationPanel() {
        final Label acceptanceLabel = new Label("Not Available");

        final Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent event) {
                        acceptanceLabel.setText("Accepted");
                    }
                });

        final Button declineButton = new Button("Decline");
        declineButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent event) {
                        acceptanceLabel.setText("Declined");
                    }
                });

        final HBox panel = createHBox(6, acceptButton,
                declineButton,
                acceptanceLabel);
        panel.setAlignment(Pos.CENTER_LEFT);
        configureBorder(panel);

        return panel;
    }

    private static Node createProgressPanel() {
        final Slider slider = new Slider();

        final ProgressIndicator progressIndicator = new ProgressIndicator(0);
        progressIndicator.progressProperty().bind(
                Bindings.divide(slider.valueProperty(),
                        slider.maxProperty()));

        final HBox panel = createHBox(6, new Label("Progress:"),
                slider,
                progressIndicator);
        configureBorder(panel);

        return panel;
    }

    private static void configureBorder(final Region region) {
        region.setStyle("-fx-background-color: white;"
                + "-fx-border-color: black;"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 6;"
                + "-fx-padding: 6;");
    }

    private static RadioButton createRadioButton(final String text,
                                                 final ToggleGroup toggleGroup,
                                                 final boolean selected) {
        final RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setSelected(selected);

        return radioButton;
    }

    private static HBox createHBox(final double spacing,
                                   final Node... children) {
        final HBox hbox = new HBox(spacing);
        hbox.getChildren().addAll(children);
        return hbox;
    }

    private static VBox createVBox(final double spacing,
                                   final Node... children) {
        final VBox vbox = new VBox(spacing);
        vbox.getChildren().addAll(children);
        return vbox;
    }

    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }
}
