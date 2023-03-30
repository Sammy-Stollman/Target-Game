package com.example.cookieclicker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class HelloApplication extends Application {

    public static Circle circle;
    public static Pane canvas;
    private int counter = 0;
    private Label label = new Label("Target hits: ");

    @Override
    public void start(Stage primaryStage) {
        canvas = new Pane();
        Scene scene = new Scene(canvas, 800, 800);

        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Button circle = new Button();
        // circle.setShape(new Circle(15, Color.RED));
        // circle.setMaxSize(3,3);

        circle = new Circle(15, Color.RED);
        circle.relocate(100, 100);

        canvas.getChildren().addAll(circle);
        EventHandler<MouseEvent> circleMeHandler = e -> handleEventforCircle(e);

        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, circleMeHandler);



        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

            double deltaX = 3;
            double deltaY = 3;

            @Override
            public void handle(final ActionEvent t) {
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);

                final Bounds bounds = canvas.getBoundsInLocal();
                final boolean atRightBorder = circle.getLayoutX()  >=  (bounds.getMaxX() - circle.getRadius());
                final boolean atLeftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                final boolean atBottomBorder = circle.getLayoutY()  >= (bounds.getMaxY() - circle.getRadius());
                final boolean atTopBorder = circle.getLayoutY() <=  (bounds.getMinY() + circle.getRadius());

                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
            }
        }));
        label.relocate(0, 0);
        canvas.getChildren().add(label);

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }
    public void counter(){
        counter++;

    }
    public static void main(String[] args) {
        launch(args);
    }

    public void handleEventforCircle(MouseEvent e) {
        counter();
        label.setText("Target hits: "+Integer.toString(counter));
    }
} 