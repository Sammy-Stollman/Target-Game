package com.example.cookieclicker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class HelloApplication extends Application {
    //Image img = new Image("https://corporate.target.com/_media/TargetCorp/Press/B-roll%20and%20Press%20Materials/Logos/Target_Bullseye-Logo_Red_transparent.png?preset=640w");
    //Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif");
    //Image img = new Image("https://witdirectoryphoto.z13.web.core.windows.net/mark.thompson.jpg");
    Image img = new Image("https://i.redd.it/yvwiwgcgdwr91.gif");
    public static Circle circle;
    public static Pane canvas;
    private double speed = 12;
    private float size = 60;

    private int counter = 0;
    private Label label = new Label("");

    @Override
    public void start(Stage primaryStage) {
        canvas = new Pane();
        Scene scene = new Scene(canvas, 1000, 800);

        label.setFont(Font.font("Verdana", FontWeight.BOLD, 100));
        label.setTextFill(Color.rgb(242, 90, 90,.4));


        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();


        circle = new Circle(size, Color.TOMATO);
        circle.relocate(0, 0);
        circle.setFill(new ImagePattern(img));

        canvas.getChildren().addAll(circle);

        EventHandler<MouseEvent> circleMeHandler = e -> handleEventforCircle(e); //handleEventforCircle is called when circle is clicked
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, circleMeHandler);

        EventHandler<MouseEvent> mouseMeHandler = e -> handleMouse(e, scene); //handleMouse which changes cursor shape is called when mouse goes over circle
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseMeHandler);

        EventHandler<MouseEvent> mouseHandler = e -> handleMouse1(e,scene); //handleMouse1 which changes cursor back is called when mouse leaves circle
        circle.addEventHandler(MouseEvent.MOUSE_EXITED, mouseHandler);

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {

            double deltaX = 3;
            double deltaY = 3;

            @Override
            public void handle(final ActionEvent t) {
//                if(counter==10){
//                    img = new Image("https://corporate.target.com/_media/TargetCorp/Press/B-roll%20and%20Press%20Materials/Logos/Target_Bullseye-Logo_Red_transparent.png?preset=640w");
//                    circle.setFill(new ImagePattern(img));
//                }
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

        label.relocate(200, 100);
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
        label.setText(Integer.toString(counter));
    }
    public void handleMouse(MouseEvent e, Scene scene){
        scene.setCursor(Cursor.CROSSHAIR);
    }

    public void handleMouse1(MouseEvent e, Scene scene){
        scene.setCursor(Cursor.DEFAULT);
    }
} 